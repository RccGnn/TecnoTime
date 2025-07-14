package it.unisa.model.DAO.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.beans.*;
import it.unisa.model.connections.*;


import java.util.ArrayList;

/**
 * Il meccanismo di funzionamento della classe è il seguente:
 * - OrdineCompletoBean è un'astrazione che permette di visualizzare gli ordini di un account,
 *   compresi tutti gli elementi dell'ordine (articolo, nome, prezzo d'acquisto, quantità 
 *   acquistata) dell'account a cui l'ordine fa riferimento.
 * - elementiOrdine è una lista di ElementoOrdineBean atta a memorizzare le informazioni 
 * 	 d'acquisto di tutti gli articoli dell'ordine
 * - LA CLASSE NON SI OCCUPA DI CONTROLLARE SE UN CLIENTE ACQUISTA PIU' ARTICOLI DI QUANTI NE SIANO DISPONIBILI
 * 	 
 * 	 (Le possibili combinazioni sono: 
 * 		
 * 			Ordine	  |    elementiOrdine
 * 		   	  ok			    ok	
 * 		      ok			   null	
 *	  )
 * - Vengono eseguiti i metodo forniti dalla classe solo sui campi che non sono null
 */
public class OrdineCompletoDao extends OrdineDao{
	
	private static final String TABLE_NAME = "OrdineCompleto";

	private synchronized void createView() throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		String createViewSQL = " CREATE or REPLACE VIEW "+ OrdineCompletoDao.TABLE_NAME  +" AS "
				+	" SELECT " 
				+	" ord.*, "
				+   " elem.numero, "
				+	" elem.codiceArticolo, "
				+   " elem.nomeArticolo, "
				+	" elem.urlImmagineArticolo, "
				+	" elem.quantitaArticolo, "
				+	" elem.prezzoUnitario "
				+	" FROM "
				+	" Ordine as ord "
				+	" LEFT JOIN Elemento_Ordine as elem USING (numeroTransazione); ";
		
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
	
	
	// OVERLOAD Metodo doSave - Serve per salvare anche il carrello
	/*
	 * 	Account_username viene mantenuta a parte per comodità, non va salvata sul database
	 */
	public synchronized void doSave(OrdineCompletoBean ordineCompleto) throws SQLException {

		// Salva l'ordine
		super.doSave(ordineCompleto);

		// Adesso si deve solo salvare ogni elemento dell'ordine
		ArrayList<ElementoOrdineBean> listaElementi = ordineCompleto.getElementiOrdine();
		
		// Inizializzo il Dao per salvare le occorrenze di ElementoOrdine
		ElementoOrdineDao elemDao = new ElementoOrdineDao();
		// Memorizza ogni elemento dell'ordine
		for (ElementoOrdineBean elemOrd : listaElementi) {
			elemDao.doSave(elemOrd);
		}
	}
	
	/*
	 * 		// Salva l'ordine
		super.doSave(ordineCompleto);

		// Adesso si deve solo salvare ogni elemento dell'ordine
		ArrayList<ElementoOrdineBean> listaElementi = ordineCompleto.getElementiOrdine();
		
		// Recupera il numero della transazione per salvare gli elementi
		int numeroTransazione = ordineCompleto.getNumeroTransazione();
		
		// Inizializzo il Dao per salvare le occorrenze di ElementoOrdine
		ElementoOrdineDao elemDao = new ElementoOrdineDao();
		ArrayList<ElementoOrdineBean> elemLista = ordineCompleto.getElementiOrdine();
		ArrayList<Object> key = null;
		int i = 1;
		// Memorizza ogni elemento dell'ordine
		for (ElementoOrdineBean elemOrd : elemLista) {
			key = new ArrayList<Object>(3);
			key.add(i); // Salva gli elementi delle transazioni in ordine
			key.add(numeroTransazione);
			key.add(elemOrd.getCodiceArticolo());
			elemDao.doSave(elemOrd);
		}

	 */
	/**
	 * Permette di recuperare l'ordine di un'utente e tutti i suoi elementi
	 * @param key {@code int} - numeroTransazione
	 * @return {@code OrdineCompletoBean} ordine
	 * @throws SQLException
	 */
	public synchronized OrdineCompletoBean doRetrieveByKey(int key) throws SQLException {

		createView();
		
		Connection connection = null;
		PreparedStatement ps = null;

		OrdineCompletoBean ordineCompleto = new OrdineCompletoBean();

		String selectSQL = "SELECT * FROM " + OrdineCompletoDao.TABLE_NAME + " WHERE numeroTransazione = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setObject(1, key);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				// L'ordine viene letto una volta
				ordineCompleto.setNumeroTransazione(rs.getInt("numeroTransazione"));
				ordineCompleto.setTotale(rs.getDouble("totale"));
				ordineCompleto.setDataTransazione(rs.getDate("dataTransazione"));
				ordineCompleto.setOraTransazione(rs.getTime("oraTransazione"));
				ordineCompleto.setUsername(rs.getString("username"));
				ordineCompleto.setNazione(rs.getString("nazione"));
				ordineCompleto.setProvincia(rs.getString("provincia"));
				ordineCompleto.setCitta(rs.getString("citta"));
				ordineCompleto.setVia(rs.getString("via"));
				ordineCompleto.setNumeroCivico(rs.getString("numeroCivico"));
				ordineCompleto.setCAP(rs.getString("CAP"));
				
				// Si devono leggere (pontenzialmente) più righe del rs per la lista di ElementiOrdine
				ArrayList<ElementoOrdineBean> elementiOrdine = new ArrayList<>();
				ElementoOrdineDao elemDao =  new ElementoOrdineDao();
				
				ArrayList<Object> keys = null;
				do {
					// Leggi i campi necessari
					int numero = rs.getInt("numero");
					int numeroTransazione = rs.getInt("numeroTransazione");
					String codiceArticolo = rs.getString("codiceArticolo");
					// Impostali nella chiave
					keys = new ArrayList<Object>(3);
					keys.add(numero); // Salva gli elementi delle transazioni in ordine
					keys.add(numeroTransazione);
					keys.add(codiceArticolo);
					
					ElementoOrdineBean elemento = elemDao.doRetrieveByKey(keys);
					elementiOrdine.add(elemento);
					
				} while(rs.next());
				
				ordineCompleto.setElementiOrdine(elementiOrdine); // Si imposta la lista di articoli
			} else {
				ordineCompleto = null;
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
		return ordineCompleto;
	}
	
	
}