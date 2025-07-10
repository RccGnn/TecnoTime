package it.unisa.control.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import com.google.gson.Gson;

import it.unisa.model.DAO.Account.AccountDao;
import it.unisa.model.DAO.Cart.CarrelloRiempitoDao;
import it.unisa.model.beans.AccountBean;
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
					
		        ArrayList<ArticoloCompletoBean> lista = new ArrayList<ArticoloCompletoBean>();
				    
				System.out.println("Lista: "+lista);
				
				ArrayList<String> keys= new ArrayList<String>();
				
				Cookie[] cookies = request.getCookies();
			        if (cookies != null) {
			            for (Cookie c : cookies) {
			                if ("username".equals(c.getName())) {
			                	keys.add(c.getValue());
			                }else if("carrello_id".equals(c.getName())) {
			                	keys.add(c.getValue());
			                }
			            }
			        }
			try {    
				 CarrelloRiempitoDao carDao= new CarrelloRiempitoDao();
			    CarrelloRiempitoBean carrello = carDao.doRetrieveByKey(keys);
			    lista=carrello.getListaArticoli();
			    lista.add(articoloDaAggiungere);
				System.out.println("Lista: "+lista); //TO DO cambiare la logica della servlet sovrascrive sempre il carrello non aggiunge
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
