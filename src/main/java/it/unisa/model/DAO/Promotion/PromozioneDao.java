package it.unisa.model.DAO.Promotion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.DAO.BeanDaoInterface;
import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.beans.PromozioneBean;
import it.unisa.model.connections.*;


import java.util.ArrayList;


public class PromozioneDao implements BeanDaoInterface<PromozioneBean> {

	private static final String TABLE_NAME = "Promozione";

	private static final String[] whitelist = 
		{"IDPromozione", "dataInizio", "durata", "percentualeSconto"};
	
	@Override
	public synchronized void doSave(PromozioneBean promozione) throws SQLException {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO "+ PromozioneDao.TABLE_NAME
				+ "(IDPromozione, nomesconto, descrizione, dataInizio, percentualeSconto) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);	

			
			ps.setString(2, promozione.getNomesconto());
			ps.setString(3, promozione.getDescrizione());
		    ps.setDate(3, promozione.getDataInizio());
		    ps.setDouble(4, promozione.getPercentualeSconto());
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
	public synchronized PromozioneBean doRetrieveByKey(String key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		PromozioneBean promozione = new PromozioneBean();

		String selectSQL = "SELECT * FROM " + PromozioneDao.TABLE_NAME + " WHERE nomesconto = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setString(2, key);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				promozione.setIDPromozione(rs.getInt("IDPromozione"));
				promozione.setDataInizio(rs.getDate("dataInizio"));
				promozione.setDescrizione(rs.getString("descrizione"));;
				promozione.setPercentualeSconto(rs.getDouble("percentualeSconto"));
			} else {
				promozione = null;
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
		return promozione;
	}
	
	
	@Override
	public synchronized boolean doDelete(String key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + PromozioneDao.TABLE_NAME + " WHERE nomesconto = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(deleteSQL);
			ps.setString(2, key);

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
	public synchronized ArrayList<PromozioneBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<PromozioneBean> listaPromozioni = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + PromozioneDao.TABLE_NAME;

		if (order != null && !order.trim().equals("") && DaoUtils.checkWhitelist(whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					PromozioneBean promozione = new PromozioneBean();
					
					promozione.setIDPromozione(rs.getInt("IDPromozione"));
					promozione.setDataInizio(rs.getDate("dataInizio"));
					promozione.setDescrizione(rs.getString("descrizione"));
					promozione.setPercentualeSconto(rs.getDouble("percentualeSconto"));
				
					listaPromozioni.add(promozione);
				} while (rs.next());
			} else {
				listaPromozioni = null;
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
		return listaPromozioni;
	}

}