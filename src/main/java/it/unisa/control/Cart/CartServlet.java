package it.unisa.control.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import it.unisa.model.DAO.Cart.CarrelloRiempitoDao;
import it.unisa.model.beans.ArticoloCompletoBean;
import it.unisa.model.beans.CarrelloRiempitoBean;


/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
				// Recupera il prodotto da inserire nel carrello
			    BufferedReader reader = request.getReader();
		        Gson gson = new Gson();
		        ArticoloCompletoBean articoloDaAggiungere = gson.fromJson(reader, ArticoloCompletoBean.class);
					
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
			    lista.add(articoloDaAggiungere);
			    carrello.setListaArticoli(lista);
				System.out.println("Lista Carrello: " + lista); 
				carDao.doSave(carrello, false); // Aggiorna il carrello ma NON salvare di nuovo il carrello Stesso
				
				// Invia il carrello
				String cartjson = gson.toJson(carrello);
				response.setContentType("application/json");
				response.getWriter().println(cartjson);
			  
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	response.sendError(500);
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
