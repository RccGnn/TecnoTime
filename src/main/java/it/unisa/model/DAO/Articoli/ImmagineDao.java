package it.unisa.model.DAO.Articoli;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.DAO.BeanDaoInterfaceArray;
import it.unisa.model.beans.ImmagineBean;
import it.unisa.model.connections.*;


import java.util.ArrayList;


public class ImmagineDao implements BeanDaoInterfaceArray<ImmagineBean> {

	private static final String TABLE_NAME = "Immagine";

	@Override
	public synchronized void doSave(ImmagineBean immagine) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO "+ ImmagineDao.TABLE_NAME
				+ "(url, codiceIdentificativo) "
				+ "VALUES (?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);	

			ps.setString(1, immagine.getUrl());
		    ps.setString(2, immagine.getArticolo_codiceIdentificativo());
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
	public synchronized ImmagineBean doRetrieveByKey(ArrayList<?> key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ImmagineBean immagine = new ImmagineBean();

		String selectSQL = "SELECT * FROM " + ImmagineDao.TABLE_NAME + " WHERE indice = ? AND codiceIdentificativo = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setObject(1, key.get(0));
			ps.setObject(2, key.get(1));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				immagine.setIndice(rs.getInt("indice"));
				immagine.setUrl(rs.getString("url"));
				immagine.setArticolo_codiceIdentificativo(rs.getString("codiceIdentificativo"));
			} else {
				immagine = null;
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
		return immagine;
	}
	
	
	@Override
	public synchronized boolean doDelete(ArrayList<?> key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ImmagineDao.TABLE_NAME + " WHERE indice = ? AND codiceIdentificativo = ?";

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
	public synchronized ArrayList<ImmagineBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<ImmagineBean> immagini = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + ImmagineDao.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					ImmagineBean immagine = new ImmagineBean();

					immagine.setIndice(rs.getInt("indice"));
					immagine.setUrl(rs.getString("url"));
					immagine.setArticolo_codiceIdentificativo(rs.getString("codiceIdentificativo"));
					
					immagini.add(immagine);
				} while (rs.next());
			} else {
				immagini = null;
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
		
		
		return immagini;
	}

	/**
	 * Permette di ottenere tutte le immagini relative ad un determinato articolo
	 * @param Articolo_codiceIdentificativo	{@code String} - codiceIdentificativo (FK) dell'entità Articolo di riferimento
	 * @return	{@code ArrayList<ImmagineBean>} con tutte le immagini che hanno come FK Articolo_codiceIdentificativo
	 * @throws SQLException
	 */
	public synchronized ArrayList<ImmagineBean> doRetrieveByCodiceIdentificativo(String Articolo_codiceIdentificativo) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<ImmagineBean> immagini = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + ImmagineDao.TABLE_NAME;

		if (Articolo_codiceIdentificativo != null && !Articolo_codiceIdentificativo.equals("")) {
			selectSQL += " WHERE codiceIdentificativo = ?";
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setString(1, Articolo_codiceIdentificativo);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					ImmagineBean immagine = new ImmagineBean();

					immagine.setIndice(rs.getInt("indice"));
					immagine.setUrl(rs.getString("url"));
					immagine.setArticolo_codiceIdentificativo(rs.getString("codiceIdentificativo"));
					
					immagini.add(immagine);
				} while (rs.next());
			} else {
				immagini = null;
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
	
		return immagini;
	}
}