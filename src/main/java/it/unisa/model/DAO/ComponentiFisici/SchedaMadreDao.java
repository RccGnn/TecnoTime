package it.unisa.model.DAO.ComponentiFisici;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.Filters.SchedaMadre;
import it.unisa.model.Filters.SchedaVideo;
import it.unisa.model.beans.CarrelloBean;
import it.unisa.model.connections.DriverManagerConnectionPool;

public class SchedaMadreDao {
	
private static final String TABLE_NAME = "SCHEDA_MADRE";

	private static final String[] whitelist = 
	{"nomecompleto","marca","PCI","vram","tipoRam","Watt"};
	
	public synchronized void doSave(SchedaMadre mb) throws SQLException {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO "+ SchedaMadreDao.TABLE_NAME
				+ "(nomecompleto,marca,socket,dimensione,PCI,SupportoRam,Watt)"
				+ "VALUES (? , ?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);	

			ps.setString(1, mb.nome());
		    ps.setString(2, mb.marca());
		    ps.setString(3, mb.socket());
		    ps.setString(4, mb.dimensione());
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
		                    rs.getString("dimensione"),
		                    rs.getFloat("PCI"),
		                    rs.getString("SupportoRam"),
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

	
	public synchronized ArrayList<SchedaMadre>  doRetrieveAll (String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<SchedaMadre> mobos = new ArrayList<SchedaMadre>();

		String selectSQL = "SELECT * FROM " + SchedaMadreDao.TABLE_NAME;

		if (order != null && !order.trim().equals("") && DaoUtils.checkWhitelist(whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}
		
		
		

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

		  if (rs.next()) {
			 
			  do{
                SchedaMadre mobo =new SchedaMadre(
	                    rs.getString("nomecompleto"),
	                    rs.getString("marca"),
	                    rs.getString("socket"),
	                    rs.getString("dimensione"),
	                    rs.getFloat("PCI"),
	                    rs.getString("SupportoRam"),
	                    rs.getInt("watt")
	                   
	                );
               mobos.add(mobo);
               
			  } while (rs.next());
			} else {
				mobos= null;
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
		return mobos;
	}
}

