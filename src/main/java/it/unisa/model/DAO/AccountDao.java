package it.unisa.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.model.beans.AccountBean;
import it.unisa.model.beans.Ruoli;

import java.util.Date;
import java.util.ArrayList;

public class AccountDao implements BeanDaoInterface<AccountBean> {

	private static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/TecnoTimeDB");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "Account";

	@Override
	public synchronized void doSave(AccountBean account) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + this.TABLE_NAME
				+ " (username, hashedPassword, email, numeroTelefono, nazione, provincia, citta, via, numeroCivico, CAP, ruolo, dataNascita) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(insertSQL);		
			ps.setString(1, account.getUsername());
		    ps.setString(2, account.gethashedPassword());
		    ps.setString(3, account.getEmail());
		    ps.setString(4, account.getNumeroTelefono());
		    ps.setString(5, account.getNazione());
		    ps.setString(6, account.getProvincia());
		    ps.setString(7, account.getCitta());
		    ps.setString(8, account.getVia());
		    ps.setString(9, account.getNumeroCivico());
		    ps.setString(10, account.getCAP());
		    // Se ruolo è un enum:
		    ps.setString(11, account.getRuolo().name());
		    // Se dataNascita è LocalDate:
		    ps.setDate(12, (java.sql.Date) account.getDataNascita());


			preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

	@Override
	public synchronized AccountBean doRetrieveByKey(String key) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		AccountBean account = new AccountBean();

		String selectSQL = "SELECT * FROM " + this.TABLE_NAME + " WHERE username = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, key);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				account.setUsername(rs.getString("username"));
				account.sethashedPassword(rs.getString("hashedPassword"));
				account.setEmail(rs.getString("email"));
				account.setNumeroTelefono(rs.getString("numeroTelefono"));
				account.setNazione(rs.getString("nazione"));
				account.setProvincia(rs.getString("provincia"));
				account.setCitta(rs.getString("citta"));
				account.setVia(rs.getString("via"));
				account.setNumeroCivico(rs.getString("numeroCivico"));
				account.setCAP(rs.getString("CAP"));
				account.setRuolo(Ruoli.valueOf(rs.getString("ruolo")));
				account.setDataNascita((java.util.Date)rs.getDate("dataNascita"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return account;
	}

	@Override
	public synchronized boolean doDelete(String key) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + this.TABLE_NAME + " WHERE username = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, key);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized ArrayList<AccountBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<AccountBean> products = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + AccountDao.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				AccountBean account = new AccountBean();

				account.setUsername(rs.getString("username"));
				account.sethashedPassword(rs.getString("hashedPassword"));
				account.setEmail(rs.getString("email"));
				account.setNumeroTelefono(rs.getString("numeroTelefono"));
				account.setNazione(rs.getString("nazione"));
				account.setProvincia(rs.getString("provincia"));
				account.setCitta(rs.getString("citta"));
				account.setVia(rs.getString("via"));
				account.setNumeroCivico(rs.getString("numeroCivico"));
				account.setCAP(rs.getString("CAP"));
				account.setRuolo(Ruoli.valueOf(rs.getString("ruolo"))); // enum Ruoli con valueOf
				account.setDataNascita((java.util.Date)rs.getDate("dataNascita")); // se usi LocalDate
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;
	}

}