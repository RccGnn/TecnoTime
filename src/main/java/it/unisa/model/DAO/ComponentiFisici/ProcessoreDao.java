package it.unisa.model.DAO.ComponentiFisici;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.DAO.Articoli.ProdottoFisicoDao;
import it.unisa.model.Filters.Processore;
import it.unisa.model.beans.ArticoloBean;
import it.unisa.model.beans.ProdottoFisicoBean;
import it.unisa.model.connections.DriverManagerConnectionPool;

public class ProcessoreDao {
	
	private static final String TABLE_NAME = "PROCESSORE";
	
	

	private static final String[] whitelist = 
		{"nomecompleto","marca","socket","datarilascio","Watt"};
	
	public synchronized void doSave(Processore p) throws SQLException {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO "+ ProcessoreDao.TABLE_NAME
				+ "(nomecompleto,marca,socket,datarilascio,Watt)"
				+ "VALUES (?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);	

			ps.setString(1, p.nome());
		    ps.setString(2, p.marca());
		    ps.setString(3, p.socket());
		    ps.setDate(4, p.datarilascio());
		    ps.setInt(5, p.watt());
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
	
	
	public synchronized Processore doRetrieveByKey(String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		
		 String sql = "SELECT * FROM " + ProcessoreDao.TABLE_NAME +" WHERE nomecompleto = ?";

		    try {	
		    	connection = DriverManagerConnectionPool.getConnection();
		    	ps= connection.prepareStatement(sql);
		        ps.setString(1, nome);
		        ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
		                return new Processore(
		                    rs.getString("nomecompleto"),
		                    rs.getString("marca"),
		                    rs.getString("socket"),
		                    rs.getDate("datarilascio"),
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
	
	public synchronized ArrayList<Processore>  doRetrieveAll (String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<Processore> cpu = new ArrayList<Processore>();

		String selectSQL = "SELECT * FROM " + ProcessoreDao.TABLE_NAME;

		if (order != null && !order.trim().equals("") && DaoUtils.checkWhitelist(whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

		  if (rs.next()) {
                Processore cpus= new Processore(
	                    rs.getString("nomecompleto"),
	                    rs.getString("marca"),
	                    rs.getString("socket"),
	                    rs.getDate("datarilascio"),
	                    rs.getInt("Watt")
	                );
					cpu.add(cpus);
			} else {
				cpu = null;
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
		return cpu;
	}
	
	
}