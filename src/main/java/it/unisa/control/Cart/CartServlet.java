package it.unisa.control.Cart;

import jakarta.servlet.RequestDispatcher;
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
import java.util.random.RandomGenerator;

import com.google.gson.Gson;

import it.unisa.model.DAO.Account.AccountDao;
import it.unisa.model.DAO.Cart.CarrelloDao;
import it.unisa.model.DAO.Cart.CarrelloRiempitoDao;
import it.unisa.model.beans.AccountBean;
import it.unisa.model.beans.ArticoloCompletoBean;
import it.unisa.model.beans.CarrelloBean;
import it.unisa.model.beans.CarrelloRiempitoBean;
import it.unisa.model.beans.Ruoli;

import com.google.gson.Gson;

import it.unisa.model.beans.ArticoloCompletoBean;

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
			
		// * Assumendo di lavorare solo con guests
		Cookie[] cookies = request.getCookies();
		System.out.println(cookies);
		
		String username = null;
		String carrelloId = null;
		
		if (cookies != null) {
			String nome = "";
			for (Cookie c : cookies) {
				nome = c.getName();
				if (nome.toLowerCase().equals("username".toLowerCase()))
					username = c.getValue();
				else if (nome.toLowerCase().equals("carrello_id".toLowerCase()))
					carrelloId = c.getValue();
			}
		}
		
		System.out.println("User: "+username+"\nID: "+carrelloId);
		if (username == null || carrelloId == null) {
			// Se il client corrente non possiede cookie, allora vuol dire che si tratta di
			// un guest NON registrato che aggiunge un prodotto al carrello per la prima volta
			AccountBean guest = new AccountBean("GUEST");
	        AccountDao accDao = new AccountDao();
	        CarrelloRiempitoDao carDao = new CarrelloRiempitoDao();
	        CarrelloRiempitoBean carrello = new CarrelloRiempitoBean();

	        String usernameGuest = accDao.UpdateandRetrieve_AccountId(); // Si ottiene l'username del guest
	        
	        try {
	        	Cookie userCookie = new Cookie("username", usernameGuest);
	        	accDao.doSave(guest); // Salva il nuovo guest sul db
	        	userCookie.setPath("/");
	        	userCookie.setMaxAge(60*60*24); // ss*mm*hh
	        	response.addCookie(userCookie); // Imposta il cookie con l'username guest
				
				carrello.setAccount_username(guest.getUsername()); // Associa l'username di guest al carrello appena creato
				String carrelloIdGuest = UUID.randomUUID().toString().substring(0, 10);
				carrello.setCarrello_Id(carrelloIdGuest);
				Cookie cartCookie = new Cookie("carrello_id", carrelloIdGuest); // Imposta il cookie con l'id del carrello guest
				cartCookie.setPath("/");
				cartCookie.setMaxAge(60*60*24); // ss*mm*hh
				response.addCookie(cartCookie); // Imposta il cookie con l'username guest
				
				
				System.out.println("Username111: "+usernameGuest+"\nCarrelloID: "+carrelloIdGuest);
				// Recupera il prodotto da inserire nel carrello
				BufferedReader reader = request.getReader();
		        Gson gson = new Gson();
		        ArticoloCompletoBean articoloDaAggiungere = gson.fromJson(reader, ArticoloCompletoBean.class);
		        System.out.println("CArrello: "+articoloDaAggiungere);
				
				
				try {
					
				    ArrayList<ArticoloCompletoBean> lista = carrello.getListaArticoli();
				    if (lista == null)
					    lista = new ArrayList<ArticoloCompletoBean>();
				    
				    System.out.println("Lista: "+lista);
				    lista.add(articoloDaAggiungere);
				    
				    carrello.setListaArticoli(lista); // Aggiungi la nuova lista dei prodotti
				    carDao.doSave(carrello, true); // Salva il carrello
				
				    
				    String cartjson = gson.toJson(carrello);
			        response.setContentType("application/json");
			        response.getWriter().println(cartjson);
			        
				} catch (Exception e) {
					e.printStackTrace();
					response.sendError(500);
				}

	        } catch (Exception e) {
	        	e.printStackTrace();
	        	response.sendError(500);
	        }
	        
	        
	        
	        
		} else {
			// Se invece il client ha dei cookie, recupero il carrello
			cookies = request.getCookies();
			
			// Usa username ed Id del carrello del guest
			
			System.out.println("Username: "+username+"\nCarrelloID: "+carrelloId);
			// Recupera il prodotto da inserire nel carrello
			BufferedReader reader = request.getReader();
	        Gson gson = new Gson();
	        ArticoloCompletoBean articoloDaAggiungere = gson.fromJson(reader, ArticoloCompletoBean.class);
	        System.out.println("CArrello: "+articoloDaAggiungere);
			
			// Recupera il carrello
			CarrelloRiempitoDao carDao = new CarrelloRiempitoDao();
			
			try {
				ArrayList key = new ArrayList<>(2);
				key.add(username);
				key.add(carrelloId);
			    CarrelloRiempitoBean cart = carDao.doRetrieveByKey(key); // Recupera il carrello del guest
			    
			    ArrayList<ArticoloCompletoBean> lista = cart.getListaArticoli();
			    if (lista == null)
				    lista = new ArrayList<ArticoloCompletoBean>();
			    
			    cart.setListaArticoli(lista); // Aggiungi la nuova lista dei prodotti
			    carDao.doSave(cart);
			
			    String cartjson = gson.toJson(cart);
		        response.setContentType("application/json");
		        response.getWriter().println(cartjson);
		        
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				AccountBean guest= new AccountBean("GUEST");
		        AccountDao dao = new AccountDao();
		        
		        try {
		            dao.doSave(guest);
		        }catch(Exception e) {
		            System.out.println(e.getMessage());
		        }  
		        
		RandomGenerator rand= RandomGenerator.getDefault();
		int id=rand.nextInt(800);
		BufferedReader reader = request.getReader();
		Gson gson = new Gson();
		ArticoloCompletoBean articolo = gson.fromJson(reader, ArticoloCompletoBean.class);
		CarrelloRiempitoBean cart = new CarrelloRiempitoBean();
		CarrelloBean carrello= new CarrelloBean();
		
		carrello.setAccount_username(guest.getUsername());
		carrello.setCarrello_id(id);
		cart.setAccount_username(guest.getUsername());
		cart.setCarrello_id(id);
		ArrayList<ArticoloCompletoBean> listaProdotti= new ArrayList<>();
		listaProdotti.add(articolo);
		cart.setListaArticoli(listaProdotti);
		CarrelloDao carrelDao= new CarrelloDao();
		CarrelloRiempitoDao riempitoDao = new CarrelloRiempitoDao();
		
		try {
			carrelDao.doSave(carrello);
			riempitoDao.doSave(cart);
		}  catch(Exception e) {
			e.getMessage();
		}
		
		response.setContentType("application/json");
		String cartjson= gson.toJson(cart);
		response.getWriter().write(cartjson);
		
		
		
		/*HttpSession session = request.getSession(false); //session anonima 
		if (session == null || (session.getAttribute("user") == null && session.getAttribute("admin")==null)) {
		        
		        System.out.println(guest.toString());
		            
		        Cookie [] Allcookie =request.getCookies();
		        for(Cookie c : Allcookie) {
		        	c.getName()==;
		       } 
		        response.addCookie(cartcookie);
		}*/
		}
	}
