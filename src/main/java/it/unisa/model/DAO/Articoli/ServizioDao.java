package it.unisa.model.DAO.Articoli;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.DAO.BeanDaoInterface;
import it.unisa.model.beans.ServizioBean;
import it.unisa.model.connections.*;


import java.util.ArrayList;


public class ServizioDao implements BeanDaoInterface<ServizioBean> {

	private static final String TABLE_NAME = "Servizio";

	@Override
	public synchronized void doSave(ServizioBean servizio) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO "+ ServizioDao.TABLE_NAME
				+ "(codiceIdentificativo, durata, codiceIdentificativo) "
				+ "VALUES (?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);	

			ps.setString(1, servizio.getCodiceServizio());
			ps.setInt(2, servizio.getDurata());
		    ps.setString(3, servizio.getArticolo_codiceIdentificativo());
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

	@Override
	public synchronized ServizioBean doRetrieveByKey(String key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ServizioBean servizio = new ServizioBean();

		String selectSQL = "SELECT * FROM " + ServizioDao.TABLE_NAME + " WHERE codiceServizio = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setString(1, key);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				servizio.setCodiceServizio(rs.getString("codiceServizio"));
				servizio.setDurata(rs.getInt("durata"));
				servizio.setArticolo_codiceIdentificativo(rs.getString("codiceIdentificativo"));
				
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
	
	
	@Override
	public synchronized boolean doDelete(String key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ServizioDao.TABLE_NAME + " WHERE codiceServizio = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(deleteSQL);
			ps.setString(1, key);

			result = ps.executeUpdate();

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized ArrayList<ServizioBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<ServizioBean> servizi = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + ServizioDao.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					ServizioBean servizio = new ServizioBean();

					servizio.setCodiceServizio(rs.getString("codiceServizio"));
					servizio.setDurata(rs.getInt("durata"));
					servizio.setArticolo_codiceIdentificativo(rs.getString("codiceIdentificativo"));
					
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