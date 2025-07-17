package it.unisa.model.DAO.ComponentiFisici;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.Filters.Case;
import it.unisa.model.Filters.Ram;
import it.unisa.model.Filters.SchedaMadre;
import it.unisa.model.connections.DriverManagerConnectionPool;

public class CaseDao {
	private static final String TABLE_NAME = "_case";
	  
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
}
