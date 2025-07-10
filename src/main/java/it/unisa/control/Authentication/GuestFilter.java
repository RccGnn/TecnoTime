package it.unisa.control.Authentication;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import com.google.gson.Gson;

import it.unisa.control.Cart.CookieUtils;
import it.unisa.model.DAO.Account.AccountDao;
import it.unisa.model.DAO.Cart.CarrelloRiempitoDao;
import it.unisa.model.beans.AccountBean;
import it.unisa.model.beans.ArticoloCompletoBean;
import it.unisa.model.beans.CarrelloRiempitoBean;

/**
 * Servlet Filter implementation class GuestFilter
 */
@WebFilter("/index.jsp")
public class GuestFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public GuestFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		String path = req.getServletPath();
		
			//utente non registrato
		if (session == null || session.getAttribute("user") == null || session.getAttribute("admin")==null) {
		
						Cookie[] cookies = req.getCookies();
						
						String username = null;
						String carrelloId = null;
						
						CookieUtils.SvuotaCookie(req, res);
						if (cookies != null) {
							String nome = "";
							for (Cookie c : cookies) {
								nome = c.getName();
								if (nome.toLowerCase().equals("username".toLowerCase()))
									username = c.getValue();
								if (nome.toLowerCase().equals("carrello_id".toLowerCase()))
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
						        	res.addCookie(userCookie); // Imposta il cookie con l'username guest
									
									carrello.setAccount_username(guest.getUsername()); // Associa l'username di guest al carrello appena creato
									String carrelloIdGuest = UUID.randomUUID().toString().substring(0, 10);
									carrello.setCarrello_Id(carrelloIdGuest);
									Cookie cartCookie = new Cookie("carrello_id", carrelloIdGuest); // Imposta il cookie con l'id del carrello guest
									cartCookie.setPath("/");
									cartCookie.setMaxAge(60*60*24); // ss*mm*hh
									res.addCookie(cartCookie); // Imposta il cookie con l'username guest
									
									
									System.out.println("UsernameGuest: "+usernameGuest+"\nCarrelloIDGuest: "+carrelloIdGuest);
									// Recupera il prodotto da inserire nel carrello
									
									carDao.doSave(carrello, true); // Salva il carrello
									chain.doFilter(request, response);
						        } catch (Exception e) {
						        	e.printStackTrace();
						        	res.sendError(500);
						        }
						        
					        
						 } else {
					            // Se i cookie ci sono già, prosegui la richiesta
					            System.out.println("Username esistente: " + username + ", CarrelloID: " + carrelloId);
					            chain.doFilter(request, response);
					            return;
					        }

					    } else {
					        // Utente già loggato
					        System.out.println("Utente autenticato");
					        chain.doFilter(request, response);
					    }
}


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
