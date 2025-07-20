package it.unisa.model.DAO.Articoli;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.beans.ArticoloBean;
import it.unisa.model.beans.ProdottoDigitaleBean;
import it.unisa.model.connections.*;

import java.util.ArrayList;

public class ProdottoDigitaleDao extends ArticoloDao{

	private static final String TABLE_NAME = "Prodotto_Digitale";

	private static final String[] whitelist = 
		{"codiceSoftware", "descrizione", "prezzo", "chiaviDisponibili", "codiceIdentificativo"};
	
	public synchronized void doSave(ProdottoDigitaleBean prodottoDigitale) throws SQLException {

		super.doSave(prodottoDigitale);
		
		Connection connection = null;
		PreparedStatement ps = null;
		

		String insertSQL = "INSERT INTO "+ ProdottoDigitaleDao.TABLE_NAME
				+ "(codiceSoftware, descrizione, prezzo, chiaviDisponibili, codiceIdentificativo) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			ps = connection.prepareStatement(insertSQL);	

			ps.setString(1, prodottoDigitale.getCodiceSoftware());
			ps.setString(2, prodottoDigitale.getDescrizione());
			ps.setFloat(3,  prodottoDigitale.getPrezzo());
		    ps.setInt(4, prodottoDigitale.getNumeroChiavi());
		    ps.setString(5, prodottoDigitale.getArticolo_codiceIdentificativo());
		    
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
	 * Key = ({@code String}: codiceSoftware, {@code String}: Articolo.codiceIdentificativo)
	 */
	public synchronized ProdottoDigitaleBean doRetrieveByKey(ArrayList<?> key) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		ProdottoDigitaleBean prodottoDigitale = new ProdottoDigitaleBean();

		String selectSQL = "SELECT * FROM " + ProdottoDigitaleDao.TABLE_NAME + " WHERE codiceSoftware = ? AND codiceIdentificativo = ?";

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

				prodottoDigitale.setCodiceIdentificativo(art.getCodiceIdentificativo());
				prodottoDigitale.setCategoria(art.getCategoria());
				prodottoDigitale.setNome(art.getNome());
				prodottoDigitale.setDataUltimaPromozione(art.getDataUltimaPromozione());
				prodottoDigitale.setEnteErogatore(art.getEnteErogatore());
				prodottoDigitale.setDisponibilita(art.isDisponibilita());
				
				prodottoDigitale.setCodiceSoftware(rs.getString("codiceSoftware"));
				prodottoDigitale.setNumeroChiavi(rs.getInt("chiaviDisponibili"));
				prodottoDigitale.setArticolo_codiceIdentificativo(rs.getString("codiceIdentificativo"));
				prodottoDigitale.setPrezzo(rs.getFloat("prezzo"));
				prodottoDigitale.setDescrizione(rs.getString("descrizione"));
				
			} else {
				prodottoDigitale = null;
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
		return prodottoDigitale;
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

		ArrayList<ProdottoDigitaleBean> prodottiDigitali = new ArrayList<>();

		String selectSQL = "SELECT * FROM " + ProdottoDigitaleDao.TABLE_NAME;

		if (order != null && !order.trim().equals("") && DaoUtils.checkWhitelist(whitelist, order)) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					ProdottoDigitaleBean prodottoDigitale = new ProdottoDigitaleBean();

					String chiave = rs.getString("codiceIdentificativo");
					// Si sfrutta il fatto che ad ogni articolo corrisponde una sola sottoclasse
					ArticoloBean art = super.doRetrieveByKey(chiave);

					prodottoDigitale.setCodiceIdentificativo(art.getCodiceIdentificativo());
					prodottoDigitale.setCategoria(art.getCategoria());
					prodottoDigitale.setNome(art.getNome());
					prodottoDigitale.setDataUltimaPromozione(art.getDataUltimaPromozione());
					prodottoDigitale.setEnteErogatore(art.getEnteErogatore());
					prodottoDigitale.setDisponibilita(art.isDisponibilita());

					prodottoDigitale.setCodiceSoftware(rs.getString("codiceSoftware"));
					prodottoDigitale.setNumeroChiavi(rs.getInt("chiaviDisponibili"));
					prodottoDigitale.setArticolo_codiceIdentificativo(rs.getString("codiceIdentificativo"));
					prodottoDigitale.setPrezzo(rs.getFloat("prezzo"));
					prodottoDigitale.setDescrizione(rs.getString("descrizione"));
					
					prodottiDigitali.add(prodottoDigitale);
				} while (rs.next());
			} else {
				prodottiDigitali = null;
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
		return prodottiDigitali;
	}


}