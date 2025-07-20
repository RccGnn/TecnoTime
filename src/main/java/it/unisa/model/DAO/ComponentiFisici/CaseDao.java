package it.unisa.model.DAO.ComponentiFisici;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.Filters.Alimentatore;
import it.unisa.model.Filters.Case;
import it.unisa.model.Filters.Ram;
import it.unisa.model.Filters.SchedaMadre;
import it.unisa.model.connections.DriverManagerConnectionPool;

public class CaseDao {
	private static final String TABLE_NAME = "_case";
	
	private static final String[] whitelist = 
		{"nomecompleto","dimensione"};
	  
	public synchronized void doSave(Case tipoCase) throws SQLException {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO "+ CaseDao.TABLE_NAME
				+ "(nomecompleto,dimensione)"
				+ "VALUES (? , ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);	

			ps.setString(1, tipoCase.nomecompleto());
		    ps.setString(2, tipoCase.dimensione());
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
	
	public synchronized Case doRetrieveByKey(String nomecompleto) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		
		 String sql = "SELECT * FROM " + CaseDao.TABLE_NAME +" WHERE nomecompleto = ?";

		    try {	
		    	connection = DriverManagerConnectionPool.getConnection();
		    	ps= connection.prepareStatement(sql);
		        ps.setString(1, nomecompleto);
		        ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
		                return new Case(
		                    rs.getString("nomecompleto"),
		                    rs.getString("dimensione")
		                );
		            } else {
		                return null; // Nessun processore trovato
		            }
		    }finally {
				try {
					if (ps != null)
						ps.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
	}
	
	public synchronized ArrayList<Case>  doRetrieveAll (String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<Case> cases = new ArrayList<Case>();

		String selectSQL = "SELECT * FROM " + CaseDao.TABLE_NAME;

		if (order != null && !order.trim().equals("") && DaoUtils.checkWhitelist(whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

		  if (rs.next()) {
               Case case1 = new Case(
	                    rs.getString("nomecompleto"),
	                    rs.getString("dimensione")
	                   
	                );
               cases.add(case1);
			} else {
				cases = null;
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
		return cases;
	}
}

