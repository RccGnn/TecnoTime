package it.unisa.model.DAO.ComponentiFisici;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.Filters.Alimentatore;
import it.unisa.model.Filters.SchedaMadre;
import it.unisa.model.connections.DriverManagerConnectionPool;

public class AlimentatoreDao {
	
private static final String TABLE_NAME = "ALIMENTATORI";
	
	
	public synchronized void doSave(Alimentatore psu) throws SQLException {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO "+ AlimentatoreDao.TABLE_NAME
				+ "(nomecompleto,marca,watt)"
				+ "VALUES (? , ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);	

			ps.setString(1, psu.nomecompleto());
		    ps.setString(2, psu.marca());
		    ps.setInt(3,psu.watt());
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
	
	public synchronized Alimentatore doRetrieveByKey(String nomecompleto) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		
		 String sql = "SELECT * FROM " + AlimentatoreDao.TABLE_NAME +" WHERE nomecompleto = ?";

		    try {	
		    	connection = DriverManagerConnectionPool.getConnection();
		    	ps= connection.prepareStatement(sql);
		        ps.setString(1, nomecompleto);
		        ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
		                return new Alimentatore(
		                    rs.getString("nomecompleto"),
		                    rs.getString("marca"),
		                    rs.getInt("watt")
		                );
		            } else {
		                return null; // Nessun alimentatore trovato
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
