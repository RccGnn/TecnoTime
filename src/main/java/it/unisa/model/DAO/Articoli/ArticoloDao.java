package it.unisa.model.DAO.Articoli;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import it.unisa.model.DAO.BeanDaoInterface;
import it.unisa.model.beans.ArticoloBean;
import it.unisa.model.connections.*;


import java.util.ArrayList;


public class ArticoloDao implements BeanDaoInterface<ArticoloBean> {

	private static final String TABLE_NAME = "Articolo";

	@Override
	public synchronized void doSave(ArticoloBean articolo) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO "+ ArticoloDao.TABLE_NAME
				+ "(codiceIdentificativo, categoria, nome, dataUltimaPromozione, enteErogatore, disponibilita) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);	

			ps.setString(1, articolo.getCodiceIdentificativo());
		    ps.setString(2, articolo.getCategoria());
		    ps.setString(3, articolo.getNome());
		    ps.setDate(4, Date.valueOf(articolo.getDataUltimaPromozione()));
		    ps.setString(5, articolo.getEnteErogatore());
		    ps.setBoolean(6, articolo.isDisponibilita());
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
	public synchronized ArticoloBean doRetrieveByKey(String key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArticoloBean articolo = new ArticoloBean();

		String selectSQL = "SELECT * FROM " + ArticoloDao.TABLE_NAME + " WHERE codiceIdentificativo = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setString(1, key);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				articolo.setCodiceIdentificativo(rs.getString("codiceIdentificativo"));
				articolo.setCategoria(rs.getString("categoria"));
				articolo.setNome(rs.getString("nome"));
				articolo.setDataUltimaPromozione(rs.getDate("dataUltimaPromozione").toLocalDate());
				articolo.setEnteErogatore(rs.getString("enteErogatore"));
				articolo.setDisponibilita(rs.getBoolean("disponibilita"));
			} else {
				articolo = null;
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
		return articolo;
	}
	
	
	@Override
	public synchronized boolean doDelete(String key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ArticoloDao.TABLE_NAME + " WHERE codiceIdentificativo = ?";

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
	public synchronized ArrayList<ArticoloBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<ArticoloBean> articoli = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + ArticoloDao.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					ArticoloBean articolo = new ArticoloBean();

					articolo.setCodiceIdentificativo(rs.getString("codiceIdentificativo"));
					articolo.setCategoria(rs.getString("categoria"));
					articolo.setNome(rs.getString("nome"));
					articolo.setDataUltimaPromozione(rs.getDate("dataUltimaPromozione").toLocalDate());
					articolo.setEnteErogatore(rs.getString("enteErogatore"));
					articolo.setDisponibilita(rs.getBoolean("disponibilita"));
					
					articoli.add(articolo);
				} while (rs.next());
			} else {
				articoli = null;
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
		return articoli;
	}

}