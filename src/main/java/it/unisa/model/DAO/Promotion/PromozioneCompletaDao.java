package it.unisa.model.DAO.Promotion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.beans.PromozioneCompletaBean;
import it.unisa.model.beans.RiguardaBean;
import it.unisa.model.connections.DriverManagerConnectionPool;

public class PromozioneCompletaDao extends PromozioneDao{

	private static final String TABLE_NAME = "PromozioneCompletaBean";

	
	private synchronized void createView() throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		String createViewSQL = " CREATE or REPLACE VIEW " + PromozioneCompletaDao.TABLE_NAME + " AS "
				+ " SELECT "
				+	" 	promo.*, "
				+	" 	rig.codiceIdentificativo "
				+	" FROM "
				+	" 	Promozione AS promo "
				+	" LEFT JOIN Riguarda AS rig USING (IDPromozione) ";
		
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
	
	public synchronized void doSave(PromozioneCompletaBean promozione) throws SQLException {
		
		// Salva la promozione
		super.doSave(promozione);
		
		// Se la promozione riguarda un articolo, allora salvalo 
		if (promozione.getRiguarda() != null) {
			RiguardaDao dao = new RiguardaDao();
			try {
				dao.doSave(promozione.getRiguarda());				
			} catch (SQLException e) {
				throw e;
			}
			
		}
		
	}


	
	/**
	 * Restituisce tutte le promozioni che riguardano Articoli.
	 * @param key
	 * @return
	 * @throws SQLException
	 */
	public synchronized ArrayList<PromozioneCompletaBean> doRetrieveByKeyProducts(String key) throws SQLException {

		createView();
		
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<PromozioneCompletaBean> promozioni = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + PromozioneCompletaDao.TABLE_NAME + " ";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				PromozioneCompletaBean promozione = null;	
				RiguardaBean riguarda = null;
				do {
					// Se il campo codiceIdentificativo non è null, memorizza la promozione
					if (rs.getString("codiceIdentificativo") == null)
						continue;
	
					// I campi di promozione
					promozione = new PromozioneCompletaBean();
					promozione.setIDPromozione(rs.getInt("IDPromozione"));
					promozione.setDataInizio(rs.getDate("dataInizio"));
					promozione.setDescrizione(rs.getString("descrizione"));
					promozione.setPercentualeSconto(rs.getDouble("percentualeSconto"));

					// L'oggetto dei campi riguarda
					riguarda = new RiguardaBean();
					riguarda.setIDPromozione(rs.getString("IDPromozione"));
					riguarda.setCodiceIdentificativo(rs.getString("codiceIdentificativo"));
					
					// Aggiungi l'oggetto riguarda
					promozione.setRiguarda(riguarda);
					
					promozioni.add(promozione);
				} while (rs.next());
				
			} else {
				promozioni = null;
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
		return promozioni;
	}
	
	
}
