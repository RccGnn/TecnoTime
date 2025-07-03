package it.unisa.model.DAO.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.DAO.BeanDaoInterface;
import it.unisa.model.beans.ContieneBean;
import it.unisa.model.connections.*;
import it.unisa.model.DAO.*;

import java.util.ArrayList;

/**
 * Questa classe rappresenta una tabella creata in per memorizzare le 
 * isatnze delle relazione Contiene (N, N) tra l'entità Carrello e 
 * l'entità Articolo
 */
public class ContieneDao implements BeanDaoInterfaceArray<ContieneBean> {

	private static final String[] whitelist = {"codiceIdentificativo, usernameCarrello, quantita"};
	
	private static final String TABLE_NAME = "Contiene";

	@Override
	public synchronized void doSave(ContieneBean contiene) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO " + ContieneDao.TABLE_NAME
				+ " (codiceIdentificativo, usernameCarrello, quantita) "
				+ " VALUES (?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);

			ps.setString(1, contiene.getArticolo_codiceIdentificativo());
			ps.setString(2, contiene.getAccount_username());
			ps.setInt(3, contiene.getQuantità());
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
	public synchronized ContieneBean doRetrieveByKey(ArrayList<?> key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ContieneBean contiene = new ContieneBean();

		String selectSQL = "SELECT * FROM " + ContieneDao.TABLE_NAME + " WHERE codiceIdentificativo = ? AND usernameCarrello = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			
			ps.setObject(1, key.get(0));
			ps.setObject(2, key.get(1));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				contiene.setArticolo_codiceIdentificativo(rs.getString("codiceIdentificativo"));
				contiene.setAccount_username(rs.getString("usernameCarrello"));
				contiene.setQuantità(rs.getInt("quantita"));
			} else {
				contiene = null;
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
		return contiene;
	}
	
	
	@Override
	public synchronized boolean doDelete(ArrayList<?> key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ContieneDao.TABLE_NAME + " WHERE codiceIdentificativo = ? AND usernameCarrello = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(deleteSQL);
			
			ps.setObject(1, key.get(0));
			ps.setObject(2, key.get(1));
			
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
	public synchronized ArrayList<ContieneBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<ContieneBean> contieneList = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + ContieneDao.TABLE_NAME;

		if (order != null && !order.trim().equals("") && DaoUtils.checkWhitelist(whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do{
					ContieneBean contiene = new ContieneBean();
					
					contiene.setArticolo_codiceIdentificativo(rs.getString("usernameCarrello"));
					contiene.setAccount_username((rs.getString("usernameCarrello")) );
					contiene.setQuantità(rs.getInt("quantita"));
					
					contieneList.add(contiene);
				} while (rs.next());
			} else {
				contieneList = null;
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
		return contieneList;
	}

}