package it.unisa.model.DAO.ComponentiFisici;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.Filters.Processore;
import it.unisa.model.Filters.SchedaMadre;
import it.unisa.model.Filters.SchedaVideo;
import it.unisa.model.connections.DriverManagerConnectionPool;

public class SchedaVideoDAO {
	
private static final String TABLE_NAME = "SCHEDA_VIDEO";
	
	
	public synchronized void doSave(SchedaVideo vc) throws SQLException {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO "+ SchedaVideoDAO.TABLE_NAME
				+ "(nomecompleto,marca,PCI,vram,tipoRam,Watt)"
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);	

			ps.setString(1, vc.nome());
		    ps.setString(2, vc.marca());
		    ps.setFloat(3, vc.PCI());
		    ps.setInt(4, vc.vram());
		    ps.setString(5, vc.tipoRam());
		    ps.setInt(6, vc.Watt());
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
	
	public synchronized SchedaVideo doRetrieveByKey(String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		
		 String sql = "SELECT * FROM " + SchedaVideoDAO.TABLE_NAME +" WHERE nomecompleto = ?";

		    try {	
		    	connection = DriverManagerConnectionPool.getConnection();
		    	ps= connection.prepareStatement(sql);
		        ps.setString(1, nome);
		        ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
		                return new SchedaVideo(
		                    rs.getString("nomecompleto"),
		                    rs.getString("marca"),
		                    rs.getFloat("PCI"),
		                    rs.getInt("vram"),
		                    rs.getString("tipoRam"),
		                    rs.getInt("Watt")
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
