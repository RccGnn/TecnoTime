package it.unisa.model.DAO.Articoli;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.DAO.BeanDaoInterface;
import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.beans.*;
import it.unisa.model.connections.*;


import java.util.ArrayList;

/**
 * Il meccanismo di funzionamento della classe è il seguente:
 * - ArticoloCompletoBean è semplicemente un insieme dei quattro bean di Articolo, ProdottoFisico, ProdottoDigitale e Servizio
 * - Articolo (entità superclasse nel EER) non è mai null
 * - Solo uno tra gli ultimi tre bean elencati prima può essere non nullo per un'istanza di ArticoloCompletoBean 
 * 	 (Ne discerne che le possibili combinazioni sono: 
 * 		
 * 		Articolo | ProdottoFisico | ProdottoDigitale | Servizio
 * 		   ok			 ok				  null			 null
 * 		   ok			null			   ok			 null
 * 		   ok			null			  null			  ok
 *	  )
 * - Vengono eseguiti i metodo forniti dalla classe solo sui campi che non sono null
 */
public class ArticoloCompletoDao implements BeanDaoInterface<ArticoloCompletoBean>{

	public synchronized int countQueries() throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		createView();
		
		String countSQL = "SELECT COUNT(codiceIdentificativo) AS nQuery FROM " + ArticoloCompletoDao.TABLE_NAME;
		int result = 0;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(countSQL);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				result = rs.getInt("nQuery");
		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
		return result;
	}
	
	private static final String[] whitelist = 
		{
		    "codiceIdentificativo",
		    "categoria",
		    "nome",
		    "dataUltimaPromozione",
		    "enteErogatore",
		    "disponibilita",
		    "prezzo",
		    "seriale",
		    "descrizione_prodotto_fisico",
		    "isPreassemblato",
		    "quantitaMagazzino",
		    "codiceServizio",
		    "descrizione_servizio",
		    "durata",
		    "codiceSoftware",
		    "descrizione_prodotto_digitale",
		    "chiaviDisponibili"
		};
	
	private static final String TABLE_NAME = "Catalogo";
	private static final String FISICO_TABLE_NAME = "Prodotto_Fisico";
	private static final String DIGITALE_TABLE_NAME = "Prodotto_Digitale";
	private static final String SERVIZIO_TABLE_NAME = "Servizio";
	
	private synchronized void createView() throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		String createViewSQL = "CREATE OR REPLACE VIEW "+ ArticoloCompletoDao.TABLE_NAME +" AS "
				+ "SELECT  "
				+ "	a.*, "
				+ "    COALESCE ( "
				+ "		pf.prezzo, "
				+ "		s.prezzo,  "
				+ "		pd.prezzo  "
				+ "	) AS prezzo, "
				+ "    pf.seriale, "
				+ "	pf.descrizione AS descrizione_prodotto_fisico, "
				+ "	pf.isPreassemblato, "
				+ "	pf.quantitaMagazzino, "
				+ "    s.codiceServizio, "
				+ "	s.descrizione AS descrizione_servizio, "
				+ "	s.durata, "
				+ "    pd.codiceSoftware, "
				+ "    pd.descrizione AS descrizione_prodotto_digitale, "
				+ "    pd.chiaviDisponibili "
				+ "	 "
				+ "FROM Articolo AS a "
				+ "	LEFT JOIN Prodotto_Fisico AS pf USING (codiceIdentificativo) "
				+ "    LEFT JOIN Servizio AS s USING (codiceIdentificativo) "
				+ "    LEFT JOIN Prodotto_Digitale AS pd USING (codiceIdentificativo);";

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
	
	public synchronized void doSave(ArticoloCompletoBean articoloCatalogo) throws SQLException {
					
		try {
			
			// Se sono presenti immagini, vengono memorizzate
			ArrayList<ImmagineBean> imgs = articoloCatalogo.getImmagini();
			if (imgs!= null && !imgs.isEmpty()) {
				ImmagineDao imgDao = new ImmagineDao();
				for (ImmagineBean img : imgs)
					imgDao.doSave(img);					
			}
			
			// Verifico ora quale tra le sottoclassi devo memorizzare
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
			// L'inserimento della sottoclasse o delle immagini è fallito, quindi si deve eliminare anche l'Articolo inserito precedentemente
			ArticoloDao artDao = new ArticoloDao();
			artDao.doDelete(articoloCatalogo.getCodiceIdentificativo());
			throw e; 
		}
	}
	
	public synchronized ArticoloCompletoBean doRetrieveByKey(String key) throws SQLException {

		createView();
		
		Connection connection = null;
		PreparedStatement ps = null;

		ArticoloCompletoBean articoloCatalogo = new ArticoloCompletoBean();

		String selectSQL = "SELECT * FROM " + ArticoloCompletoDao.TABLE_NAME + " WHERE codiceIdentificativo = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setString(1, key);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				// Verifica se sono presenti immagini per l'articolo
				ImmagineDao imgDao = new ImmagineDao();
				ArrayList<ImmagineBean> imgList = imgDao.doRetrieveByCodiceIdentificativo(key);
				if (imgList != null && !imgList.isEmpty())
					articoloCatalogo.setImmagini(imgList);
				else
					articoloCatalogo.setImmagini(null);
				
				// Verifica ora se le sottoclassi sono presenti osservando la view Catalogo; se non lo sono, imposto null
			
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
				
				articoloCatalogo.setCodiceIdentificativo(key);
				articoloCatalogo.setNome(rs.getString("nome"));
				
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
	 * ed Immagine è vincolato dalla proprietà ON DELETE (UPDATE) CASCADE, quindi basta eliminare Articolo per eliminare
	 * anche la sottoclasse 
	 */
	public synchronized boolean doDelete(String key) throws SQLException {
		ArticoloDao artDao = new ArticoloDao();
		return artDao.doDelete(key);
	}
	
	
	public synchronized ArrayList<ArticoloCompletoBean> doRetrieveAll(String order) throws SQLException {
		createView();
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<ArticoloCompletoBean> catalogo = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + ArticoloCompletoDao.TABLE_NAME;

		if (order != null && !order.trim().equals("") && DaoUtils.checkWhitelist(ArticoloCompletoDao.whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					ArticoloCompletoBean articoloCatalogo = new ArticoloCompletoBean();

					// Per primo recupero il codiceIdentificativo, la chiave primaria
					String key = rs.getString("codiceIdentificativo");
					articoloCatalogo.setCodiceIdentificativo(key);
					articoloCatalogo.setNome(rs.getString("nome"));
					
					// Verifica se sono presenti immagini per l'articolo
					ImmagineDao imgDao = new ImmagineDao();
					ArrayList<ImmagineBean> imgList = imgDao.doRetrieveByCodiceIdentificativo(key);
					if (imgList != null && !imgList.isEmpty())
						articoloCatalogo.setImmagini(imgList);
					else
						articoloCatalogo.setImmagini(null);
					
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
	
	
	/**
	 * Imposta il numero di elementi in magazzino (chiavi disponibili) di un articolo (fisico / digitale) ed imposta
	 * la disponibilità a false se il valore {@code quantità} passato è <= 0.
	 */
	public synchronized void updateQuantity(ArticoloCompletoBean articolo, int quantita) throws SQLException{
		// I servizi non sono limitati in quantita
		if (articolo.getServizio() != null) {
			return ;
		}
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		final String TABELLA_FISICO = "Prodotto_Fisico";
		final String TABELLA_DIGITALE = "Prodotto_Digitale";
		final String TABELLA_ARTICOLO = "Articolo";

		String updateQuery = "";
		String updateArticleQuery = "UPDATE " + TABELLA_ARTICOLO + " SET disponibilita = false WHERE codiceIdentificativo = ? " ;		
		
		// Modifica la quantità di prodotto fisico
		if (articolo.getPdFisico() != null) {

			try {
				updateQuery = "UPDATE " + TABELLA_FISICO + " SET quantitaMagazzino = ? WHERE seriale = ?";
				connection = DriverManagerConnectionPool.getConnection();

				if (quantita <= 0) {
					// Imposta il numero di elementi in magazzino disponibili
					ps = connection.prepareStatement(updateQuery);
					ps.setInt(1, 0);
					ps.setString(2, articolo.getPdFisico().getSeriale());
					ps.executeUpdate();
					
					// Disabilita il prodotto
					ps = connection.prepareStatement(updateArticleQuery);
					ps.setString(1, articolo.getCodiceIdentificativo());
					ps.executeUpdate();
					
				} else {
					// Imposta il numero di elementi in magazzino disponibili
					ps = connection.prepareStatement(updateQuery);
					ps.setInt(1, quantita);
					ps.setString(2, articolo.getPdFisico().getSeriale());
					ps.executeUpdate();
					
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
			
		}
		
		// Modifica la quantità di prodotto digitale
		if (articolo.getPdDigitale() != null) {

			try {
				updateQuery = "UPDATE " + TABELLA_DIGITALE + " SET numeroChiavi = ? WHERE codiceSoftware = ?";
				connection = DriverManagerConnectionPool.getConnection();

				if (quantita <= 0) {
					// Imposta il numero di chiavi disponibili
					ps = connection.prepareStatement(updateQuery);
					ps.setInt(1, 0);
					ps.setString(2, articolo.getPdDigitale().getCodiceSoftware());
					ps.executeUpdate();
					
					// Disabilita il prodotto
					ps = connection.prepareStatement(updateArticleQuery);
					ps.setString(1, articolo.getCodiceIdentificativo());
					ps.executeUpdate();
					
				} else {
					// Imposta il numero di chiavi disponibili
					ps = connection.prepareStatement(updateQuery);
					ps.setInt(1, quantita);
					ps.setString(2, articolo.getPdDigitale().getCodiceSoftware());
					ps.executeUpdate();
					
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
			
		}

	}
	
	
	public synchronized ArrayList<ArticoloCompletoBean> doRetrieveAllSearch(String order, String name) throws SQLException {
		createView();
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<ArticoloCompletoBean> catalogo = new ArrayList<>();

		name = name.toLowerCase().trim() + "%";
		System.out.println(name);
		String selectSQL = 	"SELECT * FROM " + ArticoloCompletoDao.TABLE_NAME + " WHERE LOWER(nome) LIKE '%" + name + "%' LIMIT 6"; 

		if (order != null && !order.trim().equals("") && DaoUtils.checkWhitelist(ArticoloCompletoDao.whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					ArticoloCompletoBean articoloCatalogo = new ArticoloCompletoBean();

					// Per primo recupero il codiceIdentificativo, la chiave primaria
					String key = rs.getString("codiceIdentificativo");
					articoloCatalogo.setCodiceIdentificativo(key);
					articoloCatalogo.setNome(rs.getString("nome"));
					
					// Verifica se sono presenti immagini per l'articolo
					ImmagineDao imgDao = new ImmagineDao();
					ArrayList<ImmagineBean> imgList = imgDao.doRetrieveByCodiceIdentificativo(key);
					if (imgList != null && !imgList.isEmpty())
						articoloCatalogo.setImmagini(imgList);
					else
						articoloCatalogo.setImmagini(null);
					
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
	
	public synchronized ArrayList<ArticoloCompletoBean> doRetrieveAllLimit(String order, int pageSize, int currentPage) throws SQLException {
		createView();
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<ArticoloCompletoBean> catalogo = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + ArticoloCompletoDao.TABLE_NAME;

		if (order != null && !order.trim().equals("") && DaoUtils.checkWhitelist(ArticoloCompletoDao.whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}

		// Conta quante query ci sono nel database
		int numeroQuery = countQueries();
		int numeroPagine = (int) Math.ceil(numeroQuery / pageSize); // Restituisce il numero di pagine
		
		System.out.println("Numero Pagine: " + numeroPagine + "\nNumeroQUery:" + numeroQuery);
		if (numeroQuery <= pageSize) {
			// Non fare niente in quanto tutti i prodotti sono contenuti in un unica pagina
		}
		if (numeroQuery > pageSize && currentPage <= numeroPagine / 2) { // Mi trovo nella prima metà delle pagine
			selectSQL += " LIMIT " + numeroQuery/2 + ";";
		}
		if (numeroQuery > pageSize && currentPage > numeroPagine / 2) { // Mi trovo nella seconda metà delle pagine
			selectSQL += " LIMIT " + numeroQuery/2 + " OFFSET " + (int) Math.floor(numeroQuery/2) + ";";
		}
		
		System.out.println("QUery: "+selectSQL); 
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					ArticoloCompletoBean articoloCatalogo = new ArticoloCompletoBean();

					// Per primo recupero il codiceIdentificativo, la chiave primaria
					String key = rs.getString("codiceIdentificativo");
					articoloCatalogo.setCodiceIdentificativo(key);
					articoloCatalogo.setNome(rs.getString("nome"));
					
					// Verifica se sono presenti immagini per l'articolo
					ImmagineDao imgDao = new ImmagineDao();
					ArrayList<ImmagineBean> imgList = imgDao.doRetrieveByCodiceIdentificativo(key);
					if (imgList != null && !imgList.isEmpty())
						articoloCatalogo.setImmagini(imgList);
					else
						articoloCatalogo.setImmagini(null);
					
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

	public synchronized void updateByKey(String key, double prezzo, int quantità, String descrizione) throws SQLException {

	    Connection connection = null;
	    PreparedStatement ps = null;

	    try {
	        ArticoloCompletoDao comDao = new ArticoloCompletoDao(); 
	        ArticoloCompletoBean articoloDaModificare = comDao.doRetrieveByKey(key);

	        connection = DriverManagerConnectionPool.getConnection();

	        String updateSQL = "";
	        
	        if (articoloDaModificare.getPdFisico() != null) {
	            updateSQL = "UPDATE " + ArticoloCompletoDao.FISICO_TABLE_NAME + 
	                        " SET prezzo = ?, descrizione = ?, quantitaMagazzino = ? WHERE codiceIdentificativo = ?";
	            ps = connection.prepareStatement(updateSQL);
	            ps.setDouble(1, prezzo);
	            ps.setString(2, descrizione);
	            ps.setInt(3, quantità);
	            ps.setString(4, key);
	        } else if (articoloDaModificare.getPdDigitale() != null) {
	            updateSQL = "UPDATE " + ArticoloCompletoDao.DIGITALE_TABLE_NAME + 
	                        " SET prezzo = ?, descrizione = ?, chiaviDisponibili = ? WHERE codiceIdentificativo = ?";
	            ps = connection.prepareStatement(updateSQL);
	            ps.setDouble(1, prezzo);
	            ps.setString(2, descrizione);
	            ps.setInt(3, quantità);
	            ps.setString(4, key);
	        } else if (articoloDaModificare.getServizio() != null) {
	            updateSQL = "UPDATE " + ArticoloCompletoDao.SERVIZIO_TABLE_NAME + 
	                        " SET prezzo = ?, descrizione = ? WHERE codiceIdentificativo = ?";
	            ps = connection.prepareStatement(updateSQL);
	            ps.setDouble(1, prezzo);
	            ps.setString(2, descrizione);
	            ps.setString(3, key);
	        } else {
	            throw new SQLException("Tipo di articolo non riconosciuto per la modifica: " + key);
	        }

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
	
	
}