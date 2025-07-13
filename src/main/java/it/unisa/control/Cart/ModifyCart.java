package it.unisa.control.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.Gson;

import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.DAO.Cart.CarrelloRiempitoDao;
import it.unisa.model.beans.ArticoloCompletoBean;
import it.unisa.model.beans.CarrelloRiempitoBean;

/**
 * Servlet implementation class ModifyCart
 */
@WebServlet("/ModifyCart")
public class ModifyCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final int MAX_ARTICLES_PURCHASE = 10;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String carrelloId = request.getParameter("carrelloId");
		
		// Modifica numero di elementi
		String choice = request.getParameter("choice") != null ? request.getParameter("choice") : "";
		String productId = request.getParameter("productId") != null ? request.getParameter("productId") : "";
		
		response.setContentType("application/Json");
		CarrelloRiempitoDao cdao = new CarrelloRiempitoDao();
		ArticoloCompletoDao adao = new ArticoloCompletoDao();
		ArrayList<String> keys = new ArrayList<String>();
		keys.add(username);
		keys.add(carrelloId);
		
		if (choice.isEmpty() || productId.isEmpty()) {
			// RIMUOVI TUTTO
			if (username == null || carrelloId == null || username.trim().isEmpty() || carrelloId.trim().isEmpty()) {
				response.getWriter().println(false);
			}
			
			try {
				cdao.doEmpty(keys);
			} catch (Exception e) {
				response.getWriter().println(false);
			}
			response.getWriter().println(true);
		
		} else {
			// AGGIUNGI-RIMUOVI
			
			Gson gson = new Gson();
			ArrayList<Object> result = new ArrayList<>(2);
			ArticoloCompletoBean articolo = null;
			CarrelloRiempitoBean carrello = null;
			
			try { // Recupera articolo e carrello
				carrello = cdao.doRetrieveByKey(keys);
				articolo = adao.doRetrieveByKey(productId);
			} catch (Exception e) {
				response.sendError(500, "Errore nel caricamento del carrello");
			}

			ArrayList<ArticoloCompletoBean> temp = carrello.getListaArticoli();
			int quantitaCorrente = Collections.frequency(temp, articolo);
			
			// RIMUOVI ARTICOLO
			if (choice.equals("rem")) {
				while(temp.remove(articolo)); // rimuovi tutte le istanze di articolo da temp
				carrello.setListaArticoli(temp);
				try {
					if (temp.isEmpty())
						cdao.doEmpty(keys);
					else
						cdao.doSave(carrello, false);
				} catch (Exception e) {
					response.sendError(500, "Errore nella rimozione dell'articolo dal carrello");
				}
				result.add("rem");
				result.add(articolo);
				result.add(quantitaCorrente);
				String product = gson.toJson(result);
				response.getWriter().println(product);
				return;
			}
			
			// 0 - fallimento, non non modificare la lista
			// + e - Non eliminano il prodotto dal carrello ma ne modificano solo la quantità
			
			// Non scendere sotto 1 prodotto
			if (choice.equals("giu") && quantitaCorrente == 1) {
				result.add("err");
				result.add(articolo);
				String product = gson.toJson(result);
				response.getWriter().println(product);
				return;
			}

			// Non salire sopra il numero di elementi in magazzino
			int maxQuantita = 0;
			if (articolo.getPdFisico() != null) {
				maxQuantita = articolo.getPdFisico().getQuantitaMagazzino();
			} else if (articolo.getPdDigitale() != null) {
				maxQuantita = articolo.getPdDigitale().getNumeroChiavi();
			}
			
			// Imposta il numero massimo di articoli (per tipo) che un account può acquistare una sola volta
			if (maxQuantita > MAX_ARTICLES_PURCHASE) // Verifica che un account può effettivamente acquistare un numero di articoli pari a MAX_ARTICLES_PURCHASE
				maxQuantita = MAX_ARTICLES_PURCHASE;
			
			if (choice.equals("su") && quantitaCorrente + 1 > maxQuantita) { // Non alire sopra MAX_ARTICLES_PURCHASE
				result.add("err");
				result.add(articolo);
				String product = gson.toJson(result);
				response.getWriter().println(product);
				return;
			}
			
			// Rimuovi un singolo prodotto
			if (choice.equals("giu")) { 
				temp.remove(articolo);
				carrello.setListaArticoli(temp);
				result.add("down"); // 1 successo - giu
				
			// Aggiungi un singolo prodotto
			} else if (choice.equals("su")) {
				temp.add(articolo);
				carrello.setListaArticoli(temp);
				result.add("up"); // 2 successo - su
			}

			try { // Salva il carrello modificato
				cdao.doSave(carrello, false);
			} catch (Exception e) {
				response.sendError(500, "Errore nel caricamento del sul database");
			}
			result.add(articolo);
	
			// Se l'inserimento/rimozione va a buon fine, restituisci il prodotto per operazioni js
			String product = gson.toJson(result);
			System.out.print(product);
			response.getWriter().println(product);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
