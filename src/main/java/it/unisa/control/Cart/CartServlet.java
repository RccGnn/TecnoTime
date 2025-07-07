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
        request.setAttribute("carrello", cart);
        CarrelloDao carrelDao= new CarrelloDao();
        CarrelloRiempitoDao riempitoDao = new CarrelloRiempitoDao();
        
        try {
        	carrelDao.doSave(carrello);
        	riempitoDao.doSave(cart);
        }  catch(Exception e) {
        	e.getMessage();
        }
        
  
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
