package it.unisa.model.DAO.ComponentiFisici;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.Filters.Archiviazione;
import it.unisa.model.Filters.Ram;
import it.unisa.model.connections.DriverManagerConnectionPool;

public record ArchiviazioneDao() {
	private static final String TABLE_NAME = "ARCHIVIAZIONE";
	  
	public synchronized void doSave(Archiviazione ssd) throws SQLException {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO "+ ArchiviazioneDao.TABLE_NAME
				+ "(nomecompleto,marca,PCI,capacita)"
				+ "VALUES (? , ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);	

			ps.setString(1, ssd.nome());
		    ps.setString(2, ssd.marca());
		    ps.setFloat(3, ssd.PCI());
		    ps.setInt(4, ssd.capacita());
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
	public synchronized Archiviazione doRetrieveByKey(String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		
		 String sql = "SELECT * FROM " + ArchiviazioneDao.TABLE_NAME +" WHERE nomecompleto = ?";

		    try {	
		    	connection = DriverManagerConnectionPool.getConnection();
		    	ps= connection.prepareStatement(sql);
		        ps.setString(1, nome);
		        ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
		                return new Archiviazione (
		                    rs.getString("nomecompleto"),
		                    rs.getString("marca"),
		                    rs.getFloat("PCI"),
		                    rs.getInt("capacita")
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
