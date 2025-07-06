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
 * - quantitaArticoli e listaArticoli sono legati nel seguente modo: in listaArticoli vengono memorizzate tutte le
 *   informazioni dell'articolo in questione mentre in quantità articolo viene memorizzate il numero di volte che
 *   un cliente intende acquistare l'articolo
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
                "con.codiceIdentificativo, " +
                "con.quantita " +
                "FROM Contiene AS con " +
                "LEFT JOIN Carrello AS car USING (usernameCarrello) ";

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
	
	/*
	 * 	Account_username viene mantenuta a parte per comodità, non va salvata sul database
	 */
	public synchronized void doSave(CarrelloRiempitoBean carrelloRiempito) throws SQLException {

		// Non si deve fare super.doSave(carrelloRiempito)
		// Gli articoli memorizzati nel carrello sono già salvati nel database, non si devono salvare a loro volta
		ArrayList<ArticoloCompletoBean> catalogo = carrelloRiempito.getListaArticoli();
		
		String username = carrelloRiempito.getAccount_username();
		if (catalogo == null || catalogo.isEmpty() || username == null)
			return;
		
		// Ciò che si memorizza sono le quantità: le entità contiene della lista quantità Articoli

		// Si usa una lista d'appoggio per contare gli elementi distinti
		ArrayList<ArticoloCompletoBean> temp = new ArrayList<>(); 
		
		// Inizializzo il Dao per salvare le occorrenze di Contiene
		ContieneDao conDao = new ContieneDao();
		int occorrenze = 0;
		
		// Per ogni elemento in catalogo, conto le occorrenze e le salvo evitando i duplicati
		for (ArticoloCompletoBean articolo : catalogo) {

			// Se è la prima volta che articolo viene incontrato, allora si inserisce in temp.
			// In questo modo non sarà più considerato per memorizzare un'entità contiene
			if (!temp.contains(articolo)) {
				temp.add(articolo);
				occorrenze = Collections.frequency(catalogo, articolo);
				// Costruisco il bean da memorizzare
				ContieneBean conBean = new ContieneBean();
				conBean.setAccount_username(username);
				conBean.setArticolo_codiceIdentificativo(articolo.getCodiceIdentificativo());
				conBean.setQuantità(occorrenze);
				conDao.doSave(conBean);
			}
		}
	}
	
	/**
	 * Permette di recuperare il carrello di un'utente
	 * @param key {@code String} - Username dell'utente di cui si vuole recuperare il carrello
	 * @return {@code CarrelloRiempitoBean} carrello (può anche essere vuoto)
	 * @throws SQLException
	 */
	public synchronized CarrelloRiempitoBean doRetrieveByKey(String key) throws SQLException {

		createView();
		
		Connection connection = null;
		PreparedStatement ps = null;

		CarrelloRiempitoBean carrelloRiempito = new CarrelloRiempitoBean();

		// Ottengo tutti i prodotti e le loro quantità contenute in un carrello (rs può avere da 0 a più righe)
		String selectSQL = "SELECT * FROM " + CarrelloRiempitoDao.TABLE_NAME + " WHERE usernameCarrello = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setString(1, key);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				// Il carrello viene letto una volta
				carrelloRiempito.setAccount_username(rs.getString("usernameCarrello")); // Si imposta il carrello
				
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
	 * dalla proprietà ON DELETE (UPDATE) CASCADE, quindi basta eliminare Carrello per eliminare
	 * anche tutti i prodotti al suo interno
	 */
	public synchronized boolean doDelete(String key) throws SQLException {
		CarrelloDao carDao = new CarrelloDao();
		return carDao.doDelete(key);
	}
	
	/**
	 * Svuota un carrello, eliminandone i prodotti
	 * @param key {@code String} - username dell'entità Carrello che si intende svuotare
	 * @return {@code CarrelloRiempitoBean} - carrello vuoto
	 * @throws SQLException
	 */
	public synchronized CarrelloRiempitoBean doEmpty(String key) throws SQLException {
		
createView();
		
		Connection connection = null;
		PreparedStatement ps = null;

		CarrelloRiempitoBean carrelloRiempito = new CarrelloRiempitoBean();

		// Ottengo tutti i prodotti e le loro quantità contenute in un carrello (rs può avere da 0 a più righe)
		String selectSQL = "SELECT * FROM " + CarrelloRiempitoDao.TABLE_NAME + " WHERE usernameCarrello = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setString(1, key);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				// Il carrello viene letto una volta
				String username = rs.getString("usernameCarrello"); // Si memorizza la chiave primaria del carrello
				carrelloRiempito.setAccount_username(username); // Si imposta il carrello
				
				// Si devono leggere (pontenzialmente) più righe del rs per la lista di Articoli
				ContieneDao conDao = new ContieneDao();
				
				do { // In pratica: per ogni prodotto nel carrello, si elimina la relativa istanza dell'entità Contiene corrispondente
					String codiceIdentificativo = rs.getString("codiceIdentificativo");
					ArrayList<String> keyContiene = new ArrayList<>(2);
					keyContiene.add(codiceIdentificativo);
					keyContiene.add(username);
					conDao.doDelete(keyContiene);

				} while(rs.next());
				
				carrelloRiempito.setListaArticoli(new ArrayList<>()); // Si imposta la lista di articoli
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