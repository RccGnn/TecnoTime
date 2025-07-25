package it.unisa.model.DAO.ComponentiFisici;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.Filters.Case;
import it.unisa.model.Filters.Processore;
import it.unisa.model.Filters.SchedaMadre;
import it.unisa.model.Filters.SchedaVideo;
import it.unisa.model.connections.DriverManagerConnectionPool;

public class SchedaVideoDAO {
	
private static final String TABLE_NAME = "SCHEDA_VIDEO";
	
	private static final String[] whitelist = 
	{"nomecompleto","marca","PCI","vram","tipoRam","Watt"};
		
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
		    ps.setInt(6, vc.watt());
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
		                    rs.getInt("watt")
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
	
	public synchronized ArrayList<SchedaVideo>  doRetrieveAll (String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<SchedaVideo> gpus = new ArrayList<SchedaVideo>();

		String selectSQL = "SELECT * FROM " + SchedaVideoDAO.TABLE_NAME;

		if (order != null && !order.trim().equals("") && DaoUtils.checkWhitelist(whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

		  if (rs.next()) {
			  do {
               SchedaVideo gpu = new SchedaVideo(
	                    rs.getString("nomecompleto"),
	                    rs.getString("marca"),
	                    rs.getFloat("PCI"),
	                    rs.getInt("vram"),
	                    rs.getString("tipoRam"),
	                    rs.getInt("watt")
	                   
	                );
               gpus.add(gpu);
			  } while (rs.next());
			} else {
				gpus = null;
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
		return gpus;
	}
}

