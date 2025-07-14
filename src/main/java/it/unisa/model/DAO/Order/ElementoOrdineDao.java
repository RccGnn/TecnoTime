package it.unisa.model.DAO.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.beans.ElementoOrdineBean;
import it.unisa.model.connections.DriverManagerConnectionPool;
import it.unisa.model.DAO.BeanDaoInterfaceArray;
import it.unisa.model.DAO.DaoUtils;

public class ElementoOrdineDao implements BeanDaoInterfaceArray<ElementoOrdineBean> {

	private static final String[] whitelist = {
		"numero",
		"numeroTransazione",
		"codiceArticolo",
		"NomeArticolo",
		"urlImmagineArticolo",
		"prezzoUnitario",
		"quantitaArticolo"
	};

	private static final String TABLE_NAME = "Elemento_Ordine";

	@Override
	public synchronized void doSave(ElementoOrdineBean elementoOrdine) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;

		String insertSQL = "INSERT INTO " + ElementoOrdineDao.TABLE_NAME
				+ " (numero, numeroTransazione, codiceArticolo, NomeArticolo, urlImmagineArticolo, prezzoUnitario, quantitaArticolo) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();

			ps = connection.prepareStatement(insertSQL);

			ps.setInt(1, elementoOrdine.getNumero());
			ps.setInt(2, elementoOrdine.getNumeroTransazione());
			ps.setString(3, elementoOrdine.getCodiceArticolo());
			ps.setString(4, elementoOrdine.getNomeArticolo());
			ps.setString(5, elementoOrdine.getUrlImmagineArticolo());
			ps.setDouble(6, elementoOrdine.getPrezzoUnitario());
			ps.setInt(7, elementoOrdine.getQuantitaArticolo());

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
	 * key (numero, Ordine.numeroTransazione, Articolo.codiceArticolo)
	 */
	public synchronized ElementoOrdineBean doRetrieveByKey(ArrayList<?> key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ElementoOrdineBean elementoOrdine = new ElementoOrdineBean();

		String selectSQL = "SELECT * FROM " + ElementoOrdineDao.TABLE_NAME
				+ " WHERE numero = ? AND numeroTransazione = ? AND codiceArticolo = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ps.setObject(1, key.get(0));
			ps.setObject(2, key.get(1));
			ps.setObject(3, key.get(2));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				elementoOrdine.setNumero(rs.getInt("numero"));
				elementoOrdine.setNumeroTransazione(rs.getInt("numeroTransazione"));
				elementoOrdine.setCodiceArticolo(rs.getString("codiceArticolo"));
				elementoOrdine.setNomeArticolo(rs.getString("NomeArticolo"));
				elementoOrdine.setUrlImmagineArticolo(rs.getString("urlImmagineArticolo"));
				elementoOrdine.setPrezzoUnitario(rs.getDouble("prezzoUnitario"));
				elementoOrdine.setQuantitaArticolo(rs.getInt("quantitaArticolo"));

			} else {
				elementoOrdine = null;
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
		return elementoOrdine;
	}

	@Override
	public synchronized boolean doDelete(ArrayList<?> key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ElementoOrdineDao.TABLE_NAME
				+ " WHERE numero = ? AND numeroTransazione = ? AND codiceArticolo = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(deleteSQL);

			ps.setObject(1, key.get(0));
			ps.setObject(2, key.get(1));
			ps.setObject(3, key.get(2));

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
	public synchronized ArrayList<ElementoOrdineBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<ElementoOrdineBean> elementiOrdineList = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + ElementoOrdineDao.TABLE_NAME;

		if (order != null && !order.trim().isEmpty() && DaoUtils.checkWhitelist(whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ElementoOrdineBean elementoOrdine = new ElementoOrdineBean();

				elementoOrdine.setNumero(rs.getInt("numero"));
				elementoOrdine.setNumeroTransazione(rs.getInt("numeroTransazione"));
				elementoOrdine.setCodiceArticolo(rs.getString("codiceArticolo"));
				elementoOrdine.setNomeArticolo(rs.getString("NomeArticolo"));
				elementoOrdine.setUrlImmagineArticolo(rs.getString("urlImmagineArticolo"));
				elementoOrdine.setPrezzoUnitario(rs.getDouble("prezzoUnitario"));
				elementoOrdine.setQuantitaArticolo(rs.getInt("quantitaArticolo"));

				elementiOrdineList.add(elementoOrdine);
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
		return elementiOrdineList;
	}

}