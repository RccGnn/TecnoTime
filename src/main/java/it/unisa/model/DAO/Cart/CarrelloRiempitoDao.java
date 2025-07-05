package it.unisa.model.DAO.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.beans.*;
import it.unisa.model.connections.*;


import java.util.ArrayList;

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
 * 		Account_username | listaArticoli | quantitaArticoli
 * 		   	  ok			    ok				  ok
 * 		      ok			   null			     null
 *	  )
 * - Vengono eseguiti i metodo forniti dalla classe solo sui campi che non sono null
 */
public class CarrelloRiempitoDao{

	private static final String[] whitelist = { 
			"usernameCarrello",
			"quantita",
			"codiceIdentificativo",
			"categoria",
			"nome",
			"dataUltimaPromozione",
			"enteErogatore",
			"disponibilita"
			};
	
	private static final String TABLE_NAME = "CarrelloRiempito";

	private synchronized void createView() throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		String createViewSQL  = "USE tecnotimedb; " +
                "CREATE OR REsPLACE VIEW CarrelloRiempito AS " +
                "SELECT " +
                "car.usernameCarrello, " +
                "con.quantita, " +
                "a.* " +
                "FROM Contiene AS con " +
                "LEFT JOIN Carrello AS car USING (usernameCarrello) " +
                "LEFT JOIN Articolo AS a USING (codiceIdentificativo);";

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

		ArrayList<ArticoloCompletoBean> catalogo = carrelloRiempito.getListaArticoli();
		if (catalogo != null && !catalogo.isEmpty()) {
			
			ArticoloCompletoDao catDao = new ArticoloCompletoDao();
			for (ArticoloCompletoBean c : catalogo)
				catDao.doSave(c);
		} 
		
		ArrayList<ContieneBean> contiene = carrelloRiempito.getQuantitaArticoli();
		if (contiene != null && !contiene.isEmpty()) {
			
			ContieneDao conDao = new ContieneDao();
			for (ContieneBean c : contiene)
				conDao.doSave(c);
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

		String selectSQL = "SELECT * FROM " + CarrelloRiempitoDao.TABLE_NAME + " WHERE usernameCarrello = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setString(1, key);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				carrelloRiempito.setAccount_username(rs.getString("usernameCarrello"));
				
				// Quando non ci sono articoli, i campi restanti sono null - assicurato dal metodo emptyCart
				// E' sempre possibile però assegnare un lista di articoli null
				
				ContieneDao conDao = new ContieneDao();
				ArrayList<ContieneBean> contiene = conDao.doRetrieveByCart(rs.getString("usernameCarrello"), "");
				// Utilizzo l'entità Contiene recuperando tutti i prodotti appartenent ad un deteminato carrello (username carrello = username account)
				ArticoloCompletoDao catDao = new ArticoloCompletoDao();
				ArrayList<ArticoloCompletoBean> catalogo = null;
				
				if (contiene != null && !contiene.isEmpty()) {

					for (ContieneBean c : contiene) {
						contiene.add(c);
						catalogo.add(catDao.doRetrieveByKey(c.getArticolo_codiceIdentificativo()));
					}		
				}
				
				carrelloRiempito.setListaArticoli(catalogo);
				carrelloRiempito.setQuantitaArticoli(contiene);
				
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
	
	
	public synchronized ArrayList<CarrelloRiempitoBean> doRetrieveAll(String order) throws SQLException {
		createView();
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<CarrelloRiempitoBean> carrelliRiempiti = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + CarrelloRiempitoDao.TABLE_NAME;

		if (order != null && !order.trim().equals("") && DaoUtils.checkWhitelist(CarrelloRiempitoDao.whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					CarrelloRiempitoBean carrelloRiempito = new CarrelloRiempitoBean();
					carrelloRiempito.setAccount_username(rs.getString("usernameCarrello"));

					// Quando non ci sono articoli, i campi restanti sono null - assicurato dal metodo emptyCart
					// E' sempre possibile però assegnare un lista di articoli null
						
					ContieneDao conDao = new ContieneDao();
					ArrayList<ContieneBean> contiene = conDao.doRetrieveByCart(rs.getString("usernameCarrello"), "");
					// Utilizzo l'entità Contiene recuperando tutti i prodotti appartenent ad un deteminato carrello (username carrello = username account)
					ArticoloCompletoDao catDao = new ArticoloCompletoDao();
					ArrayList<ArticoloCompletoBean> catalogo = null;
					
					if (contiene != null && !contiene.isEmpty()) {
						for (ContieneBean c : contiene) {
							contiene.add(c);
							catalogo.add(catDao.doRetrieveByKey(c.getArticolo_codiceIdentificativo()));
						}		
					}

					carrelloRiempito.setListaArticoli(catalogo);
					carrelloRiempito.setQuantitaArticoli(contiene);
						
					carrelliRiempiti.add(carrelloRiempito);
				} while (rs.next());
			} else {
				carrelliRiempiti = null;
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
		return carrelliRiempiti;
	}
	
	/**
	 * Svuota un carrello, eliminandone i prodotti
	 * @param carrelloRiempito {@code CarrelloRiempitoBean} - Carrello che si intende svuotare
	 * @return {@code CarrelloRiempitoBean}
	 * @throws SQLException
	 */
	public synchronized CarrelloRiempitoBean doEmpty(CarrelloRiempitoBean carrelloRiempito) throws SQLException {
		
		CarrelloRiempitoBean c = new CarrelloRiempitoBean();
		c.setAccount_username(carrelloRiempito.getAccount_username());
		return c;
	}
	
}