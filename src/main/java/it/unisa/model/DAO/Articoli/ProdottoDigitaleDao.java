package it.unisa.model.DAO.Articoli;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.DAO.BeanDaoInterfaceArray;
import it.unisa.model.beans.ProdottoDigitaleBean;
import it.unisa.model.connections.*;


import java.util.ArrayList;

public class ProdottoDigitaleDao implements BeanDaoInterfaceArray<ProdottoDigitaleBean> {

	private static final String TABLE_NAME = "Prodotto_Digitale";

	@Override
	public synchronized void doSave(ProdottoDigitaleBean prodottoDigitale) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO "+ ProdottoDigitaleDao.TABLE_NAME
				+ "(codiceSoftware, descrizione, prezzo, chiaviDisponibili, codiceIdentificativo) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);	

			ps.setString(1, prodottoDigitale.getCodiceSoftware());
			ps.setString(2, prodottoDigitale.getDescrizione());
			ps.setFloat(3,  prodottoDigitale.getPrezzo());
		    ps.setInt(4, prodottoDigitale.getNumeroChiavi());
		    ps.setString(5, prodottoDigitale.getArticolo_codiceIdentificativo());
		    
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
	public synchronized ProdottoDigitaleBean doRetrieveByKey(ArrayList<?> key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ProdottoDigitaleBean prodottoDigitale = new ProdottoDigitaleBean();

		String selectSQL = "SELECT * FROM " + ProdottoDigitaleDao.TABLE_NAME + " WHERE codiceSoftware = ? AND codiceIdentificativo = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setObject(1, key.get(0));
			ps.setObject(2, key.get(1));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				prodottoDigitale.setCodiceSoftware(rs.getString("codiceSoftware"));
				prodottoDigitale.setNumeroChiavi(rs.getInt("chiaviDisponibili"));
				prodottoDigitale.setArticolo_codiceIdentificativo(rs.getString("codiceIdentificativo"));
				prodottoDigitale.setPrezzo(rs.getFloat("prezzo"));
				prodottoDigitale.setDescrizione(rs.getString("descrizione"));
				
			} else {
				prodottoDigitale = null;
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
		return prodottoDigitale;
	}
	
	
	@Override
	public synchronized boolean doDelete(ArrayList<?> key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProdottoDigitaleDao.TABLE_NAME + " WHERE codiceSoftware = ? AND codiceIdentificativo = ?";

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
	public synchronized ArrayList<ProdottoDigitaleBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<ProdottoDigitaleBean> prodottiDigitali = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + ProdottoDigitaleDao.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					ProdottoDigitaleBean prodottoDigitale = new ProdottoDigitaleBean();

					prodottoDigitale.setCodiceSoftware(rs.getString("codiceSoftware"));
					prodottoDigitale.setNumeroChiavi(rs.getInt("chiaviDisponibili"));
					prodottoDigitale.setArticolo_codiceIdentificativo(rs.getString("codiceIdentificativo"));
					prodottoDigitale.setPrezzo(rs.getFloat("prezzo"));
					prodottoDigitale.setDescrizione(rs.getString("descrizione"));
					
					prodottiDigitali.add(prodottoDigitale);
				} while (rs.next());
			} else {
				prodottiDigitali = null;
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
		return prodottiDigitali;
	}

}