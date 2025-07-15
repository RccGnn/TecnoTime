package it.unisa.model.DAO.Articoli;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.beans.ArticoloBean;
import it.unisa.model.beans.ProdottoFisicoBean;
import it.unisa.model.connections.*;


import java.util.ArrayList;

public class ProdottoFisicoDao extends ArticoloDao {

	private static final String TABLE_NAME = "Prodotto_Fisico";

	private static final String[] whitelist = 
		{"seriale", "prezzo", "descrizione", "isPreassemblato", "quantitaMagazzino", "codiceIdentificativo"};
	

	public synchronized void doSave(ProdottoFisicoBean prodottoFisico) throws SQLException {

		super.doSave(prodottoFisico);
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		String insertSQL = "INSERT INTO "+ ProdottoFisicoDao.TABLE_NAME
				+ "(seriale, isPreassemblato, quantitaMagazzino, codiceIdentificativo, prezzo, descrizione) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);	

			ps.setString(1, prodottoFisico.getSeriale());
			ps.setBoolean(2, prodottoFisico.isPreassemblato());
			ps.setInt(3,  prodottoFisico.getQuantitaMagazzino());
		    ps.setString(4, prodottoFisico.getArticolo_codiceIdentificativo());
		    ps.setFloat(5, prodottoFisico.getPrezzo());
		    ps.setString(6, prodottoFisico.getDescrizione());
		    
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
	
	/**
	 * Key = ({@code String}: seriale, {@code String}: Articolo.codiceIdentificativo)
	 */
	public synchronized ProdottoFisicoBean doRetrieveByKey(ArrayList<?> key) throws SQLException {
		
		Connection connection = null;
		PreparedStatement ps = null;

		ProdottoFisicoBean prodottoFisico = new ProdottoFisicoBean();
		
		String selectSQL = "SELECT * FROM " + ProdottoFisicoDao.TABLE_NAME + " WHERE seriale = ? AND codiceIdentificativo = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			ps.setObject(1, key.get(0));
			ps.setObject(2, key.get(1));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				String chiave = rs.getString("codiceIdentificativo");
				// Si sfrutto il fatto che ad ogni articolo corrisponde una sola sottoclasse
				ArticoloBean art = super.doRetrieveByKey(chiave);

				prodottoFisico.setCodiceIdentificativo(art.getCodiceIdentificativo());
				prodottoFisico.setCategoria(art.getCategoria());
				prodottoFisico.setNome(art.getNome());
				prodottoFisico.setDataUltimaPromozione(art.getDataUltimaPromozione());
				prodottoFisico.setEnteErogatore(art.getEnteErogatore());
				prodottoFisico.setDisponibilita(art.isDisponibilita());
				
				prodottoFisico.setSeriale(rs.getString("seriale"));
				prodottoFisico.setPreassemblato(rs.getBoolean("isPreassemblato"));
				prodottoFisico.setQuantitaMagazzino(rs.getInt("quantitaMagazzino"));
				prodottoFisico.setArticolo_codiceIdentificativo(rs.getString("codiceIdentificativo"));
				prodottoFisico.setPrezzo(rs.getFloat("prezzo"));
				prodottoFisico.setDescrizione(rs.getString("descrizione"));
				
			} else {
				prodottoFisico = null;
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
		return prodottoFisico;
	}
	
	
	/**
	 * Key = ({@code String}: Articolo.codiceIdentificativo)
	 */
	public synchronized boolean doDelete(String key) throws SQLException {
		return super.doDelete(key);
	}


	public synchronized ArrayList doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ArrayList<ProdottoFisicoBean> prodottiFisici = new ArrayList<ProdottoFisicoBean>();

		String selectSQL = "SELECT * FROM " + ProdottoFisicoDao.TABLE_NAME;

		if (order != null && !order.trim().equals("") && DaoUtils.checkWhitelist(whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					ProdottoFisicoBean prodottoFisico = new ProdottoFisicoBean();
					
					String chiave = rs.getString("codiceIdentificativo");
					// Si sfrutta il fatto che ad ogni articolo corrisponde una sola sottoclasse
					ArticoloBean art = super.doRetrieveByKey(chiave);

					prodottoFisico.setCodiceIdentificativo(art.getCodiceIdentificativo());
					prodottoFisico.setCategoria(art.getCategoria());
					prodottoFisico.setNome(art.getNome());
					prodottoFisico.setDataUltimaPromozione(art.getDataUltimaPromozione());
					prodottoFisico.setEnteErogatore(art.getEnteErogatore());
					prodottoFisico.setDisponibilita(art.isDisponibilita());
					
					prodottoFisico.setSeriale(rs.getString("seriale"));
					prodottoFisico.setPreassemblato(rs.getBoolean("isPreassemblato"));
					prodottoFisico.setQuantitaMagazzino(rs.getInt("quantitaMagazzino"));
					prodottoFisico.setArticolo_codiceIdentificativo(rs.getString("codiceIdentificativo"));
					prodottoFisico.setPrezzo(rs.getFloat("prezzo"));
					prodottoFisico.setDescrizione(rs.getString("descrizione"));

					prodottiFisici.add(prodottoFisico);
				} while (rs.next());
			} else {
				prodottiFisici = null;
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
		return prodottiFisici;
	}

}