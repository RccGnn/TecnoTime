package it.unisa.control.Cart;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import it.unisa.control.CookieUtils;
import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.DAO.Cart.CarrelloRiempitoDao;
import it.unisa.model.beans.ArticoloCompletoBean;
import it.unisa.model.beans.CarrelloRiempitoBean;

/**
 * Servlet implementation class ArticleSingleCartServlet
 */
@WebServlet("/ArticleSingleCartServlet")
public class ArticleSingleCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productId = request.getParameter("productId");
		String qtyParam = request.getParameter("quantity");
		int quantita = (qtyParam != null && !qtyParam.trim().isEmpty()) ? Integer.parseInt(qtyParam) : -1;
		
		if (quantita == -1 || (productId != null && productId.trim().equals("")) ) {
			response.sendError(500, "Errore nella ricerca del prodotto!");
			return;
		}
		
		ArticoloCompletoDao dao = new ArticoloCompletoDao();
		ArticoloCompletoBean articoloDaAggiungere = null;
		
		try {
			articoloDaAggiungere = dao.doRetrieveByKey(productId);			
		} catch (SQLException e) {
			response.sendError(500, "Errore nel retieve del prodotto!");
			return;
		}
		
		// Costruisci la chiave da usare per il doRetrieve
        ArrayList<ArticoloCompletoBean> lista = new ArrayList<ArticoloCompletoBean>();				
		ArrayList<String> keys= new ArrayList<String>(2);

		String values[] = new String[2];
		HttpSession s = request.getSession();
		// Recupera username e carrello_id dalla sessione
		if (s != null && s.getAttribute("username") != null) {
			values[0] = (String) s.getAttribute("username");
			values[1] = (String) s.getAttribute("carrello_id");
		} else {
			// Altrimenti recupera questi dati dai cookies
			values = CookieUtils.getUsernameCartIdfromCookies(request);
		}

		keys.add(values[0]);
		keys.add(values[1]);
		
		try {
			
			System.out.println("Username: "+keys.get(0)+"-ID: "+keys.get(1));
			CarrelloRiempitoDao carDao = new CarrelloRiempitoDao();
		    CarrelloRiempitoBean carrello = carDao.doRetrieveByKey(keys);
		    
		    // Aggiungi l'articolo
		    lista = carrello.getListaArticoli();
		    for(int i = quantita; i > 0; i--)
		    	lista.add(articoloDaAggiungere);
		    carrello.setListaArticoli(lista);
			System.out.println("Lista Carrello: " + lista); 
			carDao.doSave(carrello, false); // Aggiorna il carrello ma NON salvare di nuovo il carrello Stesso
			
        } catch (Exception e) {
        	e.printStackTrace();
        	response.sendError(500, "Errore salvataggio nel carrello");
        	return;
        }
		
		// Invia il carrello
		Gson gson = new Gson();
		String message = gson.toJson("Inserimento di " + quantita + ":\""+ articoloDaAggiungere.getNome() +"\" andato a buon fine");
		response.setContentType("application/json");
		response.getWriter().print(message);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
