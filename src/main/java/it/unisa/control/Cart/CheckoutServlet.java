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
import java.util.Enumeration;

import it.unisa.model.DAO.Cart.CarrelloRiempitoDao;
import it.unisa.model.beans.ArticoloCompletoBean;
import it.unisa.model.beans.CarrelloRiempitoBean;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("username");
		String carID= (String) session.getAttribute("carrello_id");
		ArrayList<String> key = new ArrayList<>();
		key.add(user);
		key.add(carID);
		CarrelloRiempitoDao dao = new CarrelloRiempitoDao();
		CarrelloRiempitoBean cart = new CarrelloRiempitoBean();
		try {
			cart=dao.doRetrieveByKey(key);
		}catch(SQLException e){
			e.getMessage();
		}
		String [] cookieValues = CookieUtils.getUsernameCartIdfromCookies(request);
		key.clear();
		key.add(cookieValues[0]);
		key.add(cookieValues[1]);
		CarrelloRiempitoBean cartcookie = new CarrelloRiempitoBean();
		try {
			cartcookie=dao.doRetrieveByKey(key);
		}catch(SQLException e){
			e.getMessage();
		}
		
		ArrayList<ArticoloCompletoBean> lista=  cart.getListaArticoli();
		lista.addAll(cartcookie.getListaArticoli());
		cart.setListaArticoli(lista);
		try {
			dao.doSave(cart, false);
			dao.doEmpty(key);
		}catch(SQLException e){
			e.getMessage();
		}
		
		CookieUtils.SvuotaCookie(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index-utente.jsp");
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
