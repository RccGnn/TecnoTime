package it.unisa.model.DAO.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.beans.*;
import it.unisa.model.connections.*;


import java.util.ArrayList;
import java.util.Collections;

/**
 * Il meccanismo di funzionamento della classe è il seguente:
 * - CarrelloRiempitoBean è un'astrazione che permette di visualizzare gli articoli nel carrello di un account,
 *   compreso il numero dei singoli articoli ed l'username dell'account a cui il carrello fa riferimento
 * - Account_username è l'unico campo che non è mai null (rappresenta l'account a cui il carrello fa riferimento)
 * - in listaArticoli vengono memorizzate tutti gli articoli acquistati da un utente (è possibile che siano 
 * 	 presenti più istanze dello stesso articolo; questa caretteristica viene sfruttata per memorizzare il 
 * 	numero di volte che un cliente intende acquistare l'articolo).
 * - LA CLASSE NON SI OCCUPA DI CONTROLLARE SE UN CLIENTE ACQUISTA PIU' ARTICOLI DI QUANTI NE SIANO DISPONIBILI
 * 	 
 * 	 (Le possibili combinazioni sono: 
 * 		
 * 		Account_username | listaArticoli
 * 		   	  ok			    ok	
 * 		      ok			   null	
 *	  )
 * - Vengono eseguiti i metodo forniti dalla classe solo sui campi che non sono null
 */
public class CarrelloRiempitoDao extends CarrelloDao{
	
	private static final String TABLE_NAME = "CarrelloRiempito";

	private synchronized void createView() throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		String createViewSQL =
                "CREATE OR REPLACE VIEW CarrelloRiempito AS " +
                "SELECT " +
                "car.usernameCarrello, " +
                "car.Carrello_Id, " +
                "con.codiceIdentificativo, " +
                "con.quantita " +
                "FROM Carrello AS car " +
                "LEFT JOIN Contiene AS con USING (usernameCarrello, Carrello_Id) ";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(createViewSQL);
			ps.executeUpdate();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}
	
	
	// OVERRIDE Metodo doSave - Serve per salvare anche il carrello
	/*
	 * 	Account_username viene mantenuta a parte per comodità, non va salvata sul database
	 */
	public synchronized void doSave(CarrelloRiempitoBean carrelloRiempito, boolean flag) throws SQLException {

		if (flag)
			super.doSave(carrelloRiempito);
		// Gli articoli memorizzati nel carrello sono già salvati nel database, non si devono salvare a loro volta
		ArrayList<ArticoloCompletoBean> catalogo = carrelloRiempito.getListaArticoli();
		
		String username = carrelloRiempito.getAccount_username();
		String Carrello_Id = carrelloRiempito.getCarrello_Id();
		if (catalogo == null || catalogo.isEmpty() || username == null || Carrello_Id == null)
			return;
		
		// Ciò che si memorizza sono le quantità: le entità contiene della lista quantità Articoli

		// Si usa una lista d'appoggio per contare gli elementi distinti
		ArrayList<String> temp = new ArrayList<>(); 
		
		// Inizializzo il Dao per salvare le occorrenze di Contiene
		ContieneDao conDao = new ContieneDao();
		int occorrenze = 0;
		
		ArrayList key = new ArrayList<>();
		key.add(carrelloRiempito.getAccount_username());
		key.add(carrelloRiempito.getCarrello_Id());
		doEmpty(key);
		// Per ogni elemento in catalogo, conto le occorrenze e le salvo evitando i duplicati
		int i = 1;
		for (ArticoloCompletoBean articolo : catalogo) {
			
			// Se è la prima volta che articolo viene incontrato, allora si inserisce in temp.
			// In questo modo non sarà più considerato per memorizzare un'entità contiene
			if (!temp.contains(articolo.getCodiceIdentificativo())) {
				temp.add(articolo.getCodiceIdentificativo());
				occorrenze = Collections.frequency(catalogo, articolo);
				System.out.println("Articolo: "+articolo.toString()+"\nOccorrenze: "+occorrenze);
				// Costruisco il bean da memorizzare
				ContieneBean conBean = new ContieneBean();
				conBean.setAccount_username(username);
				conBean.setCarrello_Id(Carrello_Id);
				conBean.setArticolo_codiceIdentificativo(articolo.getCodiceIdentificativo());
				conBean.setQuantità(occorrenze);
				conDao.doSave(conBean);
			}
		}
	}
	
	/**
	 * Permette di recuperare il carrello di un'utente
	 * @param key {@code ArrayList<?>} - 
	 * 		1. Username dell'utente di cui si vuole recuperare il carrello
	 * 		2. ID del Carrello
	 * @return {@code CarrelloRiempitoBean} carrello (può anche essere vuoto)
	 * @throws SQLException
	 */
	public synchronized CarrelloRiempitoBean doRetrieveByKey(ArrayList<?> key) throws SQLException {

		createView();
		
		Connection connection = null;
		PreparedStatement ps = null;

		CarrelloRiempitoBean carrelloRiempito = new CarrelloRiempitoBean();

		String selectSQL = "";
		if(key.size()==1) {
			selectSQL="SELECT * FROM " + CarrelloRiempitoDao.TABLE_NAME + " WHERE usernameCarrello = ?";
		}else if(key.size()==2){
			selectSQL = "SELECT * FROM " + CarrelloRiempitoDao.TABLE_NAME + " WHERE usernameCarrello = ? AND Carrello_Id = ?";
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setObject(1, key.get(0));
			if(key.size() == 2)
				ps.setObject(2, key.get(1));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				// Il carrello viene letto una volta
				carrelloRiempito.setAccount_username(rs.getString("usernameCarrello")); // Si imposta il carrello
				carrelloRiempito.setCarrello_Id(rs.getString("Carrello_Id"));
				
				// Si devono leggere (pontenzialmente) più righe del rs per la lista di Articoli
				ArrayList<ArticoloCompletoBean> catalogo = new ArrayList<>();
				ArticoloCompletoDao artDao =  new ArticoloCompletoDao();
				do {
					String codiceIdentificativo = rs.getString("codiceIdentificativo");
					int quantita = rs.getInt("quantita");
					ArticoloCompletoBean articolo = artDao.doRetrieveByKey(codiceIdentificativo);
					
					while(quantita-- > 0)
						catalogo.add(articolo);
					
				} while(rs.next());
				
				carrelloRiempito.setListaArticoli(catalogo); // Si imposta la lista di articoli
			} else {
				carrelloRiempito = null;
			}

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return carrelloRiempito;
	}
	
	
	/*
	 * L'attributo usernameCarrello usato come chiave esterna per l'entità Contiene e Carrello è vincolato 
	 * dalla proprietà ON DELETE (UPDATE) CASCADE, quindi basta eliminare Carrello (doDelete) per eliminare
	 * anche tutti i prodotti al suo interno
	*/
	
	/**
	 * Svuota un carrello, eliminandone i prodotti
	 * @param key {@code ArrayList<?>} - 
	 * 		1. Username dell'utente di cui si vuole recuperare il carrello
	 * 		2. ID del Carrello
	 * @return {@code CarrelloRiempitoBean} - carrello vuoto
	 * @throws SQLException
	 */
	public synchronized CarrelloRiempitoBean doEmpty(ArrayList<?> key) throws SQLException {
		
createView();
		
		Connection connection = null;
		PreparedStatement ps = null;

		CarrelloRiempitoBean carrelloRiempito = new CarrelloRiempitoBean();

		// Ottengo tutti i prodotti e le loro quantità contenute in un carrello (rs può avere da 0 a più righe)
		String selectSQL = "SELECT * FROM " + CarrelloRiempitoDao.TABLE_NAME + " WHERE usernameCarrello = ? AND Carrello_Id = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setObject(1, key.get(0));
			ps.setObject(2, key.get(1));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				// Il carrello viene letto una volta
				String username = rs.getString("usernameCarrello"); // Si memorizza la chiave primaria del carrello
				String carrello_id = rs.getString("Carrello_Id");
				carrelloRiempito.setAccount_username(username); // Si imposta il carrello
				carrelloRiempito.setCarrello_Id(carrello_id); // Si imposta il carrello				
				
				// Si devono leggere (pontenzialmente) più righe del rs per la lista di Articoli
				ContieneDao conDao = new ContieneDao();
				
				do { // In pratica: per ogni prodotto nel carrello, si elimina la relativa istanza dell'entità Contiene corrispondente
					String codiceIdentificativo = rs.getString("codiceIdentificativo");
					ArrayList keyContiene = new ArrayList<>(3);
					keyContiene.add(codiceIdentificativo);
					keyContiene.add(username);
					keyContiene.add(carrello_id);
					
					conDao.doDelete(keyContiene);

				} while(rs.next());
				
				carrelloRiempito.setListaArticoli(new ArrayList<>(1)); // Si imposta la lista di articoli
			} else {
				carrelloRiempito = null;
			}

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return carrelloRiempito;
	}
	
}