package it.unisa.model.DAO.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.beans.OrdineBean;
import it.unisa.model.connections.*;
import it.unisa.model.DAO.*;

import java.util.ArrayList;

public class OrdineDao implements BeanDaoInterfaceArray<OrdineBean> {

	private static final String[] whitelist = {
		    "numeroTransazione",
		    "totale",
		    "dataTransazione",
		    "oraTransazione",
		    "username",
		    "nazione",
		    "provincia",
		    "citta",
		    "via",
		    "numeroCivico",
		    "CAP"
		};
	
	private static final String TABLE_NAME = "Ordine";

	@Override
	public synchronized void doSave(OrdineBean ordine) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO " + OrdineDao.TABLE_NAME
				+ " (numeroTransazione, totale, dataTransazione, oraTransazione, username, nazione, provincia, citta, via, numeroCivico, CAP) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);

			ps.setInt(1, ordine.getNumeroTransazione());
			ps.setDouble(2, ordine.getTotale());
			ps.setDate(3, ordine.getDataTransazione());
			ps.setTime(4, ordine.getOraTransazione());
			ps.setString(5, ordine.getUsername());
			ps.setString(6, ordine.getNazione());
			ps.setString(7, ordine.getProvincia());
			ps.setString(8, ordine.getCitta());
			ps.setString(9, ordine.getVia());
			ps.setString(10, ordine.getNumeroCivico());
			ps.setString(11, ordine.getCAP());
			
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
	/**
	 * key = (numeroTransazione, Account.username)
	 */
	public synchronized OrdineBean doRetrieveByKey(ArrayList<?> key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		OrdineBean ordine = new OrdineBean();

		String selectSQL = "SELECT * FROM " + OrdineDao.TABLE_NAME + " WHERE numeroTransazione = ? AND username = ? ";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			
			ps.setObject(1, key.get(0));
			ps.setObject(2, key.get(1));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				ordine.setNumeroTransazione(rs.getInt("numeroTransazione"));
				ordine.setTotale(rs.getDouble("totale"));
				ordine.setDataTransazione(rs.getDate("dataTransazione"));
				ordine.setOraTransazione(rs.getTime("oraTransazione"));
				ordine.setUsername(rs.getString("username"));
				ordine.setNazione(rs.getString("nazione"));
				ordine.setProvincia(rs.getString("provincia"));
				ordine.setCitta(rs.getString("citta"));
				ordine.setVia(rs.getString("via"));
				ordine.setNumeroCivico(rs.getString("numeroCivico"));
				ordine.setCAP(rs.getString("CAP"));
				
			} else {
				ordine = null;
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
		return ordine;
	}
	
	
	@Override
	/**
	 * key = (numeroTransazione, Account.username)
	 */
	public synchronized boolean doDelete(ArrayList<?> key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + OrdineDao.TABLE_NAME + " WHERE numeroTransazione = ? AND username = ? ";

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
	public synchronized ArrayList<OrdineBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<OrdineBean> ordineList = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + OrdineDao.TABLE_NAME;

		if (order != null && !order.trim().equals("") && DaoUtils.checkWhitelist(whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do{
					OrdineBean ordine = new OrdineBean();
					
					ordine.setNumeroTransazione(rs.getInt("numeroTransazione"));
					ordine.setTotale(rs.getDouble("totale"));
					ordine.setDataTransazione(rs.getDate("dataTransazione"));
					ordine.setOraTransazione(rs.getTime("oraTransazione"));
					ordine.setUsername(rs.getString("username"));
					ordine.setNazione(rs.getString("nazione"));
					ordine.setProvincia(rs.getString("provincia"));
					ordine.setCitta(rs.getString("citta"));
					ordine.setVia(rs.getString("via"));
					ordine.setNumeroCivico(rs.getString("numeroCivico"));
					ordine.setCAP(rs.getString("CAP"));
					
					ordineList.add(ordine);
				} while (rs.next());
			} else {
				ordineList = null;
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
		return ordineList;
	}

}