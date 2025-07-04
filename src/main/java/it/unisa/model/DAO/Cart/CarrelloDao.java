package it.unisa.model.DAO.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.DAO.BeanDaoInterface;
import it.unisa.model.beans.CarrelloBean;
import it.unisa.model.connections.*;
import it.unisa.model.DAO.*;

import java.util.ArrayList;


public class CarrelloDao implements BeanDaoInterface<CarrelloBean> {

	private static final String[] whitelist = {"usernameCarrello"};
	
	private static final String TABLE_NAME = "Carrello";

	@Override
	public synchronized void doSave(CarrelloBean carrello) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO " + CarrelloDao.TABLE_NAME
				+ " (usernameCarrello) "
				+ " VALUES (?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);	

			ps.setString(1, carrello.getAccount_username());
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
	public synchronized CarrelloBean doRetrieveByKey(String key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		CarrelloBean carrello = new CarrelloBean();

		String selectSQL = "SELECT * FROM " + CarrelloDao.TABLE_NAME + " WHERE usernameCarrello = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setString(1, key);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				carrello.setAccount_username(rs.getString("usernameCarrello"));
			} else {
				carrello = null;
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
		return carrello;
	}
	
	
	@Override
	public synchronized boolean doDelete(String key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + CarrelloDao.TABLE_NAME + " WHERE usernameCarrello = ?";

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
	public synchronized ArrayList<CarrelloBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<CarrelloBean> carrelli = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + CarrelloDao.TABLE_NAME;

		if (order != null && !order.trim().equals("") && DaoUtils.checkWhitelist(whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do{
					CarrelloBean carrello = new CarrelloBean();

					carrello.setAccount_username((rs.getString("usernameCarrello")) );
					
					carrelli.add(carrello);
				} while (rs.next());
			} else {
				carrelli = null;
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
		return carrelli;
	}

}