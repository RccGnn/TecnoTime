package it.unisa.model.DAO.Promotion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.DAO.BeanDaoInterfaceArray;
import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.beans.RiguardaBean;
import it.unisa.model.connections.DriverManagerConnectionPool;

public class RiguardaDao implements BeanDaoInterfaceArray<RiguardaBean>{
	private static final String[] whitelist = {
			"IDPromozione",
			"codiceIdentificativo",
		};

		private static final String TABLE_NAME = "Riguarda";

		@Override
		public synchronized void doSave(RiguardaBean associato) throws SQLException {

			Connection connection = null;
			PreparedStatement ps = null;

			String insertSQL = "INSERT INTO " + RiguardaDao.TABLE_NAME
					+ " (IDPromozione, codiceIdentificativo) "
					+ " VALUES (?, ?)";

			try {
				connection = DriverManagerConnectionPool.getConnection();

				ps = connection.prepareStatement(insertSQL);

				ps.setString(1, associato.getIDPromozione());
				ps.setString(2, associato.getCodiceIdentificativo());

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
		 * key (Promozione.IDPromozione, Account.codiceIdentificativo)
		 */
		public synchronized RiguardaBean doRetrieveByKey(ArrayList<?> key) throws SQLException {
			Connection connection = null;
			PreparedStatement ps = null;

			RiguardaBean associato = new RiguardaBean();

			String selectSQL = "SELECT * FROM " + RiguardaDao.TABLE_NAME
					+ " WHERE IDPromozione = ? AND codiceIdentificativo = ? ";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				ps = connection.prepareStatement(selectSQL);

				ps.setObject(1, key.get(0));
				ps.setObject(2, key.get(1));

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					associato.setIDPromozione(rs.getString("IDPromozione"));
					associato.setCodiceIdentificativo(rs.getString("codiceIdentificativo"));

				} else {
					associato = null;
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
			return associato;
		}

		@Override
		public synchronized boolean doDelete(ArrayList<?> key) throws SQLException {
			Connection connection = null;
			PreparedStatement ps = null;

			int result = 0;

			String deleteSQL = "DELETE FROM " + RiguardaDao.TABLE_NAME
					+ " WHERE IDPromozione = ? AND codiceIdentificativo = ? ";
			
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
		public synchronized ArrayList<RiguardaBean> doRetrieveAll(String order) throws SQLException {
			Connection connection = null;
			PreparedStatement ps = null;

			ArrayList<RiguardaBean> listaRiguarda= new ArrayList<>();

			String selectSQL = "SELECT * FROM " + RiguardaDao.TABLE_NAME;

			if (order != null && !order.trim().isEmpty() && DaoUtils.checkWhitelist(whitelist, order)) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = DriverManagerConnectionPool.getConnection();
				ps = connection.prepareStatement(selectSQL);

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					RiguardaBean associato = new RiguardaBean();

					associato.setIDPromozione(rs.getString("IDPromozione"));
					associato.setCodiceIdentificativo(rs.getString("codiceIdentificativo"));

					listaRiguarda.add(associato);
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
			return listaRiguarda;
		}
}
