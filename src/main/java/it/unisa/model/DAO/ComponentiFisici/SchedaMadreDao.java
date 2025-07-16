package it.unisa.model.DAO.ComponentiFisici;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.Filters.SchedaMadre;
import it.unisa.model.Filters.SchedaVideo;
import it.unisa.model.connections.DriverManagerConnectionPool;

public class SchedaMadreDao {
	
private static final String TABLE_NAME = "SCHEDA_MADRE";
	
	
	public synchronized void doSave(SchedaMadre mb) throws SQLException {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO "+ SchedaMadreDao.TABLE_NAME
				+ "(nomecompleto,marca,socket,wifi,PCI,SupportoRam,Watt)"
				+ "VALUES (? , ?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);	

			ps.setString(1, mb.nome());
		    ps.setString(2, mb.marca());
		    ps.setString(3, mb.socket());
		    ps.setBoolean(4, mb.wifi());
		    ps.setFloat(5, mb.PCI());
		    ps.setString(6,mb.tipoRamSupportata());
		    ps.setInt(7,mb.watt());
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
	
	public synchronized SchedaMadre doRetrieveByKey(String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		
		 String sql = "SELECT * FROM " + SchedaMadreDao.TABLE_NAME +" WHERE nomecompleto = ?";

		    try {	
		    	connection = DriverManagerConnectionPool.getConnection();
		    	ps= connection.prepareStatement(sql);
		        ps.setString(1, nome);
		        ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
		                return new SchedaMadre(
		                    rs.getString("nomecompleto"),
		                    rs.getString("marca"),
		                    rs.getString("socket"),
		                    rs.getBoolean("wifi"),
		                    rs.getFloat("PCI"),
		                    rs.getString("SupportoRam"),
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
