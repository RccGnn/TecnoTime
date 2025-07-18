package it.unisa.control.Cart;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import it.unisa.control.CookieUtils;
import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.DAO.Cart.CarrelloRiempitoDao;
import it.unisa.model.beans.CarrelloRiempitoBean;

/**
 * Servlet implementation class UserFillCart
 */
@WebServlet("/UserFillCart")
public class UserFillCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CarrelloRiempitoDao dao = new CarrelloRiempitoDao();
		CarrelloRiempitoBean bean = new CarrelloRiempitoBean();
		ArrayList<String> key = new ArrayList<>();
		
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
		
		key.add(values[0]);
		key.add(values[1]);
		
		try {
			bean = dao.doRetrieveByKey(key);				
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("carrello", bean);	
		RequestDispatcher rd;
		if(bean == null || bean.getListaArticoli() == null || bean.getListaArticoli().isEmpty()) {
			rd = request.getRequestDispatcher("/utente/index-utente.jsp");			
		} else {
			rd = request.getRequestDispatcher("/utente/Ordine.jsp");
			bean.setListaArticoli(DaoUtils.dropboxImagesDecoderUrl(bean.getListaArticoli()));
		}
        
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
