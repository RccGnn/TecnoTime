package it.unisa.model.DAO.Articoli;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.beans.*;
import it.unisa.model.connections.*;


import java.util.ArrayList;

/**
 * Il meccanismo di funzionamento della classe è il seguente:
 * - CatalogoBean è semplicemente un insieme dei quattro bean di Articolo, ProdottoFisico, ProdottoDigitale e Servizio
 * - Articolo (entità superclasse nel EER) non è mai null
 * - Solo uno tra gli ultimi tre bean elencati prima può essere non nullo per un'istanza di CatalogoBean 
 * 	 (Ne discerne che le possibili combinazioni sono: 
 * 		
 * 		Articolo | ProdottoFisico | ProdottoDigitale | Servizio
 * 		   ok			 ok				  null			 null
 * 		   ok			null			   ok			 null
 * 		   ok			null			  null			  ok
 *	  )
 * - Vengono eseguiti i metodo forniti dalla classe solo sui campi che non sono null
 */
public class CatalogoDao{

	private static final String TABLE_NAME = "Catalogo";

	private synchronized void createView() throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		String createViewSQL = "CREATE OR REPLACE VIEW "+ this.TABLE_NAME +" AS "
				+ "SELECT "
				+ "	a.*,"
				+ " 	pf.seriale, "
				+ "	pf.prezzo AS prezzo_prodotto_fisico, "
				+ "	pf.descrizione AS descrizione_prodotto_fisico, "
				+ "	pf.isPreassemblato, "
				+ "	pf.quantitaMagazzino, "
				+ " 	s.codiceServizio, "
				+ "	s.prezzo AS prezzo_servizio, "
				+ "	s.descrizione AS descrizione_servizio, "
				+ "	s.durata, "
				+ "    pd.codiceSoftware, "
				+ " pd.prezzo AS prezzo_prodotto_digitale, "
				+ "	pd.descrizione AS descrizione_prodotto_digitale, "
				+ " pd.chiaviDisponibili, "
				+ " FROM Articolo AS a "
				+ "		LEFT JOIN Prodotto_Fisico AS pf USING (codiceIdentificativo) "
				+ "    	LEFT JOIN Servizio AS s USING (codiceIdentificativo) "
				+ "    	LEFT JOIN Prodotto_Digitale AS pd USING (codiceIdentificativo); ";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(createViewSQL);
			ps.executeQuery(createViewSQL);
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
	
	public synchronized void doSave(CatalogoBean articoloCatalogo) throws SQLException {

		// Il doSave di articolo è obbligatorio
		ArticoloDao artDao = new ArticoloDao();
		artDao.doSave(articoloCatalogo.getArticolo());
			
		// Verifico ora quale tra le sottoclassi devo memorizzare

		try {
			ProdottoFisicoDao pdfDao = new ProdottoFisicoDao();
			if (articoloCatalogo.getPdFisico() != null)
				pdfDao.doSave(articoloCatalogo.getPdFisico());
				
			ProdottoDigitaleDao pddDao = new ProdottoDigitaleDao();
			if (articoloCatalogo.getPdDigitale() != null)
				pddDao.doSave(articoloCatalogo.getPdDigitale());
				
			ServizioDao srvDao = new ServizioDao();
			if (articoloCatalogo.getServizio() != null)
				srvDao.doSave(articoloCatalogo.getServizio());			
		} catch (SQLException e) { // ATOMICITA'
			// L'inserimento della sottoclasse è fallito, quindi si deve eliminare anche l'Articolo inserito precedentemente
			artDao.doDelete(articoloCatalogo.getArticolo().getCodiceIdentificativo());
			throw e; 
		}
	}
	
	public synchronized CatalogoBean doRetrieveByKey(String key) throws SQLException {

		createView();
		
		Connection connection = null;
		PreparedStatement ps = null;

		CatalogoBean articoloCatalogo = new CatalogoBean();

		String selectSQL = "SELECT * FROM " + CatalogoDao.TABLE_NAME + " WHERE codiceIdentificativo = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setString(1, key);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// Articolo è la superclasse, è sempre presente
				ArticoloDao artDao = new ArticoloDao();
				ArticoloBean artBean = artDao.doRetrieveByKey(key);
				articoloCatalogo.setArticolo(artBean);
				
				// Verifico ora se le sottoclassi sono presenti; se non lo sono, imposto null
			
				String seriale = rs.getString("seriale");
				if (seriale != null && !seriale.trim().equals("")) {
					ProdottoFisicoDao pdfDao = new ProdottoFisicoDao();
					ArrayList<Object> temp = new ArrayList<>(2);
					temp.add(seriale);
					temp.add(key);
					articoloCatalogo.setPdFisico(pdfDao.doRetrieveByKey(temp));
				} else {
					articoloCatalogo.setPdFisico(null);
				}
				
				String codiceSoftware = rs.getString("codiceSoftware");
				if (codiceSoftware != null && !codiceSoftware.trim().equals("")) {
					ProdottoDigitaleDao pddDao = new ProdottoDigitaleDao();
					ArrayList<Object> temp = new ArrayList<>(2);
					temp.add(codiceSoftware);
					temp.add(key);
					articoloCatalogo.setPdDigitale(pddDao.doRetrieveByKey(temp));
				} else {
					articoloCatalogo.setPdDigitale(null);
				}
				
				String codiceServizio = rs.getString("codiceServizio");
				if ( codiceServizio != null &&  !codiceServizio.trim().equals("")) {
					ServizioDao srvDao = new ServizioDao();
					ArrayList<Object> temp = new ArrayList<>(2);
					temp.add(codiceServizio);
					temp.add(key);
					articoloCatalogo.setServizio(srvDao.doRetrieveByKey(temp));
				} else {
					articoloCatalogo.setServizio(null);
				}
				
			} else {
				articoloCatalogo = null;
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
		return articoloCatalogo;
	}
	
	/*
	 * L'attributo codiceIdentificativo usato come chiave esterna per le sottoclassi dell'entità Articolo
	 * è vincolato dalla proprietà ON DELETE (UPDATE) CASCADE, quindi basta eliminare Articolo per eliminare
	 * anche la sottoclasse 
	 */
	public synchronized boolean doDelete(String key) throws SQLException {
		ArticoloDao artDao = new ArticoloDao();
		return artDao.doDelete(key);
	}
	
	
	public synchronized ArrayList<CatalogoBean> doRetrieveAll(String order) throws SQLException {
		createView();
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<CatalogoBean> catalogo = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + CatalogoDao.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					CatalogoBean articoloCatalogo = new CatalogoBean();

					// Articolo è la superclasse, è sempre presente
					String key = rs.getString("codiceIdentificativo");
					ArticoloDao artDao = new ArticoloDao();
					ArticoloBean artBean = artDao.doRetrieveByKey(key);
					articoloCatalogo.setArticolo(artBean);
					
					// Verifico ora se le sottoclassi sono presenti; se non lo sono, imposto null
				
					String seriale = rs.getString("seriale");
					if (seriale != null && !seriale.trim().equals("")) {
						ProdottoFisicoDao pdfDao = new ProdottoFisicoDao();
						ArrayList<Object> temp = new ArrayList<>(2);
						temp.add(seriale);
						temp.add(key);
						articoloCatalogo.setPdFisico(pdfDao.doRetrieveByKey(temp));
					} else {
						articoloCatalogo.setPdFisico(null);
					}
					
					String codiceSoftware = rs.getString("codiceSoftware");
					if (codiceSoftware != null && !codiceSoftware.trim().equals("")) {
						ProdottoDigitaleDao pddDao = new ProdottoDigitaleDao();
						ArrayList<Object> temp = new ArrayList<>(2);
						temp.add(codiceSoftware);
						temp.add(key);
						articoloCatalogo.setPdDigitale(pddDao.doRetrieveByKey(temp));
					} else {
						articoloCatalogo.setPdDigitale(null);
					}
					
					String codiceServizio = rs.getString("codiceServizio");
					if ( codiceServizio != null &&  !codiceServizio.trim().equals("")) {
						ServizioDao srvDao = new ServizioDao();
						ArrayList<Object> temp = new ArrayList<>(2);
						temp.add(codiceServizio);
						temp.add(key);
						articoloCatalogo.setServizio(srvDao.doRetrieveByKey(temp));
					} else {
						articoloCatalogo.setServizio(null);
					}
					
					catalogo.add(articoloCatalogo);
					
				} while (rs.next());
			} else {
				catalogo = null;
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
		return catalogo;
	}
	
}