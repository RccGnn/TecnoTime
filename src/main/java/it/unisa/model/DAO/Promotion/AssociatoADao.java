package it.unisa.model.DAO.Promotion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.DAO.BeanDaoInterfaceArray;
import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.beans.AssociatoABean;
import it.unisa.model.connections.DriverManagerConnectionPool;

public class AssociatoADao implements BeanDaoInterfaceArray<AssociatoABean>{

	private static final String[] whitelist = {
			"IDPromozione",
			"username",
			"codicePromozione"
		};

		private static final String TABLE_NAME = "Associato_a";

		@Override
		public synchronized void doSave(AssociatoABean associato) throws SQLException {

			Connection connection = null;
			PreparedStatement ps = null;

			String insertSQL = "INSERT INTO " + AssociatoADao.TABLE_NAME
					+ " (IDPromozione, username, codicePromozione) "
					+ " VALUES (?, ?, ?)";

			try {
				connection = DriverManagerConnectionPool.getConnection();

				ps = connection.prepareStatement(insertSQL);

				ps.setInt(1, associato.getIDPromozione());
				ps.setString(2, associato.getUsername());
				ps.setString(3, associato.getCodicePromozione());

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
		 * key (Promozione.IDPromozione, Account.username)
		 */
		public synchronized AssociatoABean doRetrieveByKey(ArrayList<?> key) throws SQLException {
			Connection connection = null;
			PreparedStatement ps = null;

			AssociatoABean associato = new AssociatoABean();

			String selectSQL = "SELECT * FROM " + AssociatoADao.TABLE_NAME
					+ " WHERE IDPromozione = ? AND username = ? ";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				ps = connection.prepareStatement(selectSQL);

				ps.setObject(1, key.get(0));
				ps.setObject(2, key.get(1));

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					associato.setIDPromozione(rs.getInt("IDPromozione"));
					associato.setUsername(rs.getString("username"));
					associato.setCodicePromozione(rs.getString("codicePromozione"));

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
		/**
		 * key (Promozione.IDPromozione, Account.username)
		 */
		public synchronized boolean doDelete(ArrayList<?> key) throws SQLException {
			Connection connection = null;
			PreparedStatement ps = null;

			int result = 0;

			String deleteSQL = "DELETE FROM " + AssociatoADao.TABLE_NAME
					+ " WHERE IDPromozione = ? AND username = ? ";
			
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
		public synchronized ArrayList<AssociatoABean> doRetrieveAll(String order) throws SQLException {
			Connection connection = null;
			PreparedStatement ps = null;

			ArrayList<AssociatoABean> listaAssociatoA= new ArrayList<>();

			String selectSQL = "SELECT * FROM " + AssociatoADao.TABLE_NAME;

			if (order != null && !order.trim().isEmpty() && DaoUtils.checkWhitelist(whitelist, order)) {
				selectSQL += " ORDER BY " + order;
			}

			try {
				connection = DriverManagerConnectionPool.getConnection();
				ps = connection.prepareStatement(selectSQL);

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					AssociatoABean associato = new AssociatoABean();

					associato.setIDPromozione(rs.getInt("IDPromozione"));
					associato.setUsername(rs.getString("username"));
					associato.setCodicePromozione(rs.getString("codicePromozione"));

					listaAssociatoA.add(associato);
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
			return listaAssociatoA;
		}
		
}
