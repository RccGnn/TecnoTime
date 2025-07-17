package it.unisa.model.DAO.ComponentiFisici;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.Filters.Ram;
import it.unisa.model.Filters.SchedaMadre;
import it.unisa.model.beans.ArticoloBean;
import it.unisa.model.connections.DriverManagerConnectionPool;

public class RamDao {
	
	private static final String TABLE_NAME = "RAM";
  
	public synchronized void doSave(Ram ram) throws SQLException {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO "+ RamDao.TABLE_NAME
				+ "(nomecompleto,marca,capacita,SupportoRam)"
				+ "VALUES (? , ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);	

			ps.setString(1, ram.nome());
		    ps.setString(2, ram.marca());
		    ps.setInt(3, ram.capacita());
		    ps.setString(4, ram.SupportoRam());
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
	public synchronized Ram doRetrieveByKey(String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		
		 String sql = "SELECT * FROM " + RamDao.TABLE_NAME +" WHERE nomecompleto = ?";

		    try {	
		    	connection = DriverManagerConnectionPool.getConnection();
		    	ps= connection.prepareStatement(sql);
		        ps.setString(1, nome);
		        ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
		                return new Ram (
		                    rs.getString("nomecompleto"),
		                    rs.getString("marca"),
		                    rs.getInt("capacita"),
		                    rs.getString("SupportoRam")
		                );
		            } else {
		                return null; // Nessuna ram trovata
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
