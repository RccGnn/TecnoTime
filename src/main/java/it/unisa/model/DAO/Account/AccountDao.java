package it.unisa.model.DAO.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.DAO.BeanDaoInterface;
import it.unisa.model.beans.AccountBean;
import it.unisa.model.beans.Ruoli;
import it.unisa.model.connections.*;
import it.unisa.model.DAO.*;

import java.util.ArrayList;
import java.sql.Date;


public class AccountDao implements BeanDaoInterface<AccountBean> {

	private static final String[] whitelist = 
		{"username", "hashedPassword", "nome", "cognome", "sesso", "email", "numeroTelefono", 
		 "nazione", "provincia", "citta", "via", "numeroCivico", "CAP", "ruolo", "dataNascita", "asc", "disc"};
	
	private static final String TABLE_NAME = "Account";

	public synchronized String UpdateandRetrieve_AccountId() {
	    Connection connection = null;
	    PreparedStatement ps = null;
	    String newGuestUsername = "";

	    String getLastGuestNumberSQL = "SELECT MAX(CAST(SUBSTRING_INDEX(username, '#', -1) AS UNSIGNED)) "
	            + "AS lastNum FROM " + AccountDao.TABLE_NAME + " WHERE username LIKE 'GUEST#%'";

	    int nextGuestNumber = 1;

		    try {
		        connection = DriverManagerConnectionPool.getConnection();
		        ps = connection.prepareStatement(getLastGuestNumberSQL);
		        ResultSet rs = ps.executeQuery();
	
		        if (rs.next()) {
		            nextGuestNumber = rs.getInt("lastNum") + 1;
		        }
		        newGuestUsername = "GUEST#" + nextGuestNumber;
	
		    } catch (Exception e) {
		        return null;
		    } finally {
		        try {
		            if (ps != null)
		                ps.close();
		        } catch (Exception e) {
		        	return null;
		        }
		        try {
		            if (connection != null)
		                connection.close();
		        } catch (Exception e) {
		        	return null;
		        }
		    }
		    
	    return newGuestUsername;
	}
	
	

	@Override
	public synchronized void doSave(AccountBean account) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		String insertSQL="";
		
		if(!account.getUsername().toLowerCase().equals("guest")) {
			 insertSQL = "INSERT INTO " + AccountDao.TABLE_NAME
					+ " (username, hashedPassword, nome, cognome, sesso, email, numeroTelefono, nazione, provincia, citta, via, numeroCivico, CAP, ruolo, dataNascita) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			 
				 try {
						connection = DriverManagerConnectionPool.getConnection();
						
						ps = connection.prepareStatement(insertSQL);	
			
						ps.setString(1, account.getUsername());
					    ps.setString(2, account.gethashedPassword());
					    ps.setString(3, account.getNome());
					    ps.setString(4, account.getCognome());
					    ps.setString(5, Character.toString(account.getSesso()));
					    ps.setString(6, account.getEmail());
					    ps.setString(7, account.getNumeroTelefono());
					    ps.setString(8, account.getNazione());
					    ps.setString(9, account.getProvincia());
					    ps.setString(10, account.getCitta());
					    ps.setString(11, account.getVia());
					    ps.setString(12, account.getNumeroCivico());
					    ps.setString(13, account.getCAP());
					    ps.setString(14, DaoUtils.getRuoloAccountString(account)); // Ruolo scelto in base all'email dell'account
					    ps.setDate(15, Date.valueOf(account.getDataNascita()));
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
		
		else {
			insertSQL = "INSERT INTO " + AccountDao.TABLE_NAME
					+ " (username, hashedPassword, nome, cognome, sesso, email, numeroTelefono, nazione, provincia, citta, via, numeroCivico, CAP, ruolo, dataNascita) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				System.out.println("se vai qui seii un coglione");
				ps = connection.prepareStatement(insertSQL);	
				
				account.setUsername(UpdateandRetrieve_AccountId());
				
				ps.setString(1, account.getUsername());
			    ps.setString(2, account.gethashedPassword());
			    ps.setString(3, account.getNome());
			    ps.setString(4, account.getCognome());
			    ps.setString(5, Character.toString(account.getSesso()));
			    ps.setString(6, account.getEmail());
			    ps.setString(7, account.getNumeroTelefono());
			    ps.setString(8, account.getNazione());
			    ps.setString(9, account.getProvincia());
			    ps.setString(10, account.getCitta());
			    ps.setString(11, account.getVia());
			    ps.setString(12, account.getNumeroCivico());
			    ps.setString(13, account.getCAP());;
			    ps.setString(14, Ruoli.guest.name()); // Ruolo scelto in base all'email dell'account
			    ps.setDate(15, Date.valueOf(account.getDataNascita()));
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

	@Override
	public synchronized AccountBean doRetrieveByKey(String key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		AccountBean account = new AccountBean();

		String selectSQL = "SELECT * FROM " + AccountDao.TABLE_NAME + " WHERE username = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setString(1, key);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				account.setUsername(rs.getString("username"));
				account.sethashedPassword(rs.getString("hashedPassword"));
				account.setNome(rs.getString("nome"));
				account.setCognome(rs.getString("cognome"));
				account.setSesso(rs.getString("sesso").charAt(0));
				account.setEmail(rs.getString("email"));
				account.setNumeroTelefono(rs.getString("numeroTelefono"));
				account.setNazione(rs.getString("nazione"));
				account.setProvincia(rs.getString("provincia"));
				account.setCitta(rs.getString("citta"));
				account.setVia(rs.getString("via"));
				account.setNumeroCivico(rs.getString("numeroCivico"));
				account.setCAP(rs.getString("CAP"));
				account.setRuolo(Ruoli.valueOf(rs.getString("ruolo")));
				account.setDataNascita((rs.getDate("dataNascita")).toLocalDate());
			} else {
				account = null;
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
		return account;
	}
	
	
	@Override
	public synchronized boolean doDelete(String key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + AccountDao.TABLE_NAME + " WHERE username = ?";

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
	public synchronized ArrayList<AccountBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<AccountBean> accounts = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + AccountDao.TABLE_NAME;

		if (order != null && !order.trim().equals("") && DaoUtils.checkWhitelist(whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do{
					AccountBean account = new AccountBean();

					account.setUsername(rs.getString("username"));
					account.sethashedPassword(rs.getString("hashedPassword"));
					account.setNome(rs.getString("nome"));
					account.setCognome(rs.getString("cognome"));
					account.setSesso(rs.getString("sesso").charAt(0));
					account.setEmail(rs.getString("email"));
					account.setNumeroTelefono(rs.getString("numeroTelefono"));
					account.setNazione(rs.getString("nazione"));
					account.setProvincia(rs.getString("provincia"));
					account.setCitta(rs.getString("citta"));
					account.setVia(rs.getString("via"));
					account.setNumeroCivico(rs.getString("numeroCivico"));
					account.setCAP(rs.getString("CAP"));
					account.setRuolo(Ruoli.valueOf(rs.getString("ruolo")));
					account.setDataNascita(rs.getDate("dataNascita").toLocalDate());
					
					accounts.add(account);
				} while (rs.next());
			} else {
				accounts = null;
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
		return accounts;
	}

}