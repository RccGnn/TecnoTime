package it.unisa.control.Cart;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.DAO.Account.AccountDao;
import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.DAO.Cart.CarrelloRiempitoDao;
import it.unisa.model.DAO.Order.OrdineCompletoDao;
import it.unisa.model.beans.AccountBean;
import it.unisa.model.beans.ArticoloCompletoBean;
import it.unisa.model.beans.CarrelloRiempitoBean;
import it.unisa.model.beans.ElementoOrdineBean;
import it.unisa.model.beans.OrdineCompletoBean;

import java.util.Random;
import java.sql.Time;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private boolean creaOrdine(AccountBean account, CarrelloRiempitoBean carrello) {
		OrdineCompletoDao dao = new OrdineCompletoDao();
		OrdineCompletoBean ordine = new OrdineCompletoBean();
		Random rnd = new Random();

		// Imposta i campi di ordine presi da account
		ordine.setCAP(account.getCAP());
		ordine.setCitta(account.getCitta());
		// Imposta data odierna
		ordine.setDataTransazione(new Date(System.currentTimeMillis()));
		// Imposta ora attuale
		ordine.setOraTransazione(new Time(System.currentTimeMillis()));
		ordine.setNazione(account.getNazione());
		ordine.setNumeroCivico(account.getNumeroCivico());
		ordine.setProvincia(account.getProvincia());
		ordine.setUsername(account.getUsername());
		ordine.setVia(account.getVia());
		// Numero casuale da 0 a 999,999,999
		int nTransazione = rnd.nextInt(0, 999999999);
		ordine.setNumeroTransazione(nTransazione);
		// Dal carrello ricava il totale
		ordine.setTotale(DaoUtils.totaleCarrello(carrello));


		// Addesso si devono impostare gli elementi dell'ordine
		ElementoOrdineBean ordbean = null;
		ArrayList<ElementoOrdineBean> listaElementi = new ArrayList<>();
		ArrayList<ArticoloCompletoBean> listaArticoli = carrello.getListaArticoli();
		
		// Riempi gli elementi dell'ordine
		int i = 1;
		ArrayList<ArticoloCompletoBean> occorrenze = new ArrayList<ArticoloCompletoBean>();
		for (ArticoloCompletoBean articolo : listaArticoli) {
			// Se l'articolo è già stato iterato, allora non si re-itera
			if (occorrenze.contains(articolo))
				continue;
			occorrenze.add(articolo);
			
			ordbean = new ElementoOrdineBean();
			
			ordbean.setCodiceArticolo(articolo.getCodiceIdentificativo());
			ordbean.setNomeArticolo(articolo.getNome());
			ordbean.setNumero(i);
			ordbean.setNumeroTransazione(nTransazione);
			
			// Quantità e prezzo
			int quantita = Collections.frequency(listaArticoli, articolo), diff = 0; // Scala i prodotti acquistati dall'utente nel database
			double prezzo = 0;
			if(articolo.getPdFisico() != null) {
				prezzo = articolo.getPdFisico().getPrezzo();
				diff = articolo.getPdFisico().getQuantitaMagazzino() - quantita;
			} else if (articolo.getPdDigitale() != null) {
				prezzo = articolo.getPdDigitale().getPrezzo();
				diff = articolo.getPdFisico().getQuantitaMagazzino() - quantita;
			} else if (articolo.getServizio() != null) {
				prezzo = articolo.getServizio().getPrezzo();
				diff = -1;
			}
			ordbean.setPrezzoUnitario(prezzo);
			ordbean.setQuantitaArticolo(quantita);
			if(diff != -1) { // Aggiorna la quantità di articoli
				ArticoloCompletoDao artDao = new ArticoloCompletoDao();
				try {
					artDao.updateQuantity(articolo, diff);					
				} catch (SQLException e) {
					return false;
				}
			}
			
			// Immagine
			String url = "";
			if (articolo.getImmagini() == null || articolo.getImmagini().isEmpty()) {
				url = "/TecnoTime/images/alt-prodotti.png";
			} else {
				url = articolo.getImmagini().get(0).getUrl();
			}
			ordbean.setUrlImmagineArticolo(url);
			
			// Aggiungi l'elemento alla lista
			listaElementi.add(ordbean);
			i++;
		}
		
		ordine.setElementiOrdine(listaElementi);
		boolean flag = true;
		try {
			dao.doSave(ordine);
		} catch (SQLException e) {
			flag = false;
		}
		
		return flag;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("username");
		String carID = (String) session.getAttribute("carrello_id");
		ArrayList<String> key = new ArrayList<>(2);
		key.add(user);
		key.add(carID);
		CarrelloRiempitoDao dao = new CarrelloRiempitoDao();
		AccountDao accDao = new AccountDao();
		
		try {
			// Genera l'ordine per l'utente (e al contempo elimina gli articoli)
			creaOrdine(accDao.doRetrieveByKey(user), dao.doRetrieveByKey(key));
			// Svuota il carrello
			dao.doEmpty(key);
		}catch(SQLException e){
			e.getMessage();
		}
		
		CookieUtils.SvuotaCookie(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("utente/checkout.jsp");
		dispatcher.forward(request, response);
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
