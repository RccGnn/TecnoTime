package it.unisa.model.DAO.ComponentiFisici;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.Filters.Processore;
import it.unisa.model.Filters.Ram;
import it.unisa.model.Filters.SchedaMadre;
import it.unisa.model.beans.ArticoloBean;
import it.unisa.model.connections.DriverManagerConnectionPool;

public class RamDao {
	
	private static final String TABLE_NAME = "RAM";
	
	private static final String[] whitelist = 
		{"nomecompleto","marca","capacita","SupportoRam"};
  
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
	
	public synchronized ArrayList<Ram>  doRetrieveAll (String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<Ram> rams = new ArrayList<Ram>();

		String selectSQL = "SELECT * FROM " + RamDao.TABLE_NAME;

		if (order != null && !order.trim().equals("") && DaoUtils.checkWhitelist(whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();
		 
		  if (rs.next()) {
			  do {
               Ram ram= new Ram(
	                    rs.getString("nomecompleto"),
	                    rs.getString("marca"),
	                    rs.getInt("capacita"),
	                    rs.getString("SupportoRam")
	                   
	                );
					rams.add(ram);
		  } while (rs.next());
			} else {
				rams = null;
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
		return rams;
	}
	
	
	
}
