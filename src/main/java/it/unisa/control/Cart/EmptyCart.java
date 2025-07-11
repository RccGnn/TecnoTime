package it.unisa.control.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import it.unisa.model.DAO.Cart.CarrelloRiempitoDao;

/**
 * Servlet implementation class EmptyCart
 */
@WebServlet("/EmptyCart")
public class EmptyCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmptyCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String carrelloId = request.getParameter("carrelloId");
		
		if (username == null || carrelloId == null || username.trim().isEmpty() || carrelloId.trim().isEmpty()) {
			response.getWriter().println(false);
		}
		
		CarrelloRiempitoDao dao = new CarrelloRiempitoDao();
		ArrayList<String> keys = new ArrayList<String>();
		keys.add(username);
		keys.add(carrelloId);
		try {
			dao.doEmpty(keys);
		} catch (Exception e) {
			response.getWriter().println(false);
		}
		response.getWriter().println(true);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
