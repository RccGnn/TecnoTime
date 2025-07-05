package it.unisa.model.DAO.Articoli;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.DAO.BeanDaoInterfaceArray;
import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.beans.ArticoloBean;
import it.unisa.model.beans.ServizioBean;
import it.unisa.model.connections.*;


import java.util.ArrayList;


public class ServizioDao extends ArticoloDao {

	private static final String TABLE_NAME = "Servizio";

	private static final String[] whitelist = 
		{"codiceServizio", "prezzo", "descrizione", "durata", "codiceIdentificativo"};
	
	public synchronized void doSave(ServizioBean servizio) throws SQLException {

		super.doSave(servizio);
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO "+ ServizioDao.TABLE_NAME
				+ "(codiceServizio, durata, codiceIdentificativo, prezzo, descrizione) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);	

			ps.setString(1, servizio.getCodiceServizio());
			ps.setDouble(2, servizio.getDurata());
		    ps.setString(3, servizio.getArticolo_codiceIdentificativo());
		    ps.setDouble(4, servizio.getPrezzo());
		    ps.setString(5, servizio.getDescrizione());
		    
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


	/**
	 * Key = ({@code String}: Articolo.codiceIdentificativo, {@code String}: codiceServizio)
	 */
	public synchronized ServizioBean doRetrieveByKey(ArrayList<?> key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ServizioBean servizio = new ServizioBean();

		String selectSQL = "SELECT * FROM " + ServizioDao.TABLE_NAME + " WHERE codiceServizio = ? AND codiceIdentificativo = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setObject(1, key.get(0));
			ps.setObject(2, key.get(1));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				String chiave = rs.getString("codiceIdentificativo");
				// Si sfrutto il fatto che ad ogni articolo corrisponde una sola sottoclasse
				ArticoloBean art = super.doRetrieveByKey(chiave);

				servizio.setCodiceIdentificativo(art.getCodiceIdentificativo());
				servizio.setCategoria(art.getCategoria());
				servizio.setNome(art.getNome());
				servizio.setDataUltimaPromozione(art.getDataUltimaPromozione());
				servizio.setEnteErogatore(art.getEnteErogatore());
				servizio.setDisponibilita(art.isDisponibilita());

				servizio.setCodiceServizio(rs.getString("codiceServizio"));
				servizio.setDurata(rs.getDouble("durata"));
				servizio.setArticolo_codiceIdentificativo(rs.getString("codiceIdentificativo"));
				servizio.setPrezzo(rs.getDouble("prezzo"));
				servizio.setDescrizione(rs.getString("descrizione"));
				
			} else {
				servizio = null;
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
		return servizio;
	}
	
	
	/**
	 * Key = ({@code String}: Articolo.codiceIdentificativo, {@code String}: codiceServizio)
	 */
	public synchronized boolean doDelete(ArrayList<?> key) throws SQLException {
		return super.doDelete((String) key.get(0));
	}

	public synchronized ArrayList doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<ServizioBean> servizi = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + ServizioDao.TABLE_NAME;

		if (order != null && !order.trim().equals("") && DaoUtils.checkWhitelist(whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					ServizioBean servizio = new ServizioBean();

					String chiave = rs.getString("codiceIdentificativo");
					// Si sfrutto il fatto che ad ogni articolo corrisponde una sola sottoclasse
					ArticoloBean art = super.doRetrieveByKey(chiave);

					servizio.setCodiceIdentificativo(art.getCodiceIdentificativo());
					servizio.setCategoria(art.getCategoria());
					servizio.setNome(art.getNome());
					servizio.setDataUltimaPromozione(art.getDataUltimaPromozione());
					servizio.setEnteErogatore(art.getEnteErogatore());
					servizio.setDisponibilita(art.isDisponibilita());

					servizio.setCodiceServizio(rs.getString("codiceServizio"));
					servizio.setDurata(rs.getDouble("durata"));
					servizio.setArticolo_codiceIdentificativo(rs.getString("codiceIdentificativo"));
					servizio.setPrezzo(rs.getDouble("prezzo"));
					servizio.setDescrizione(rs.getString("descrizione"));
					
					servizi.add(servizio);
				} while (rs.next());
			} else {
				servizi = null;
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
		return servizi;
	}

}