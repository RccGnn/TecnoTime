package it.unisa.control.Cart;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import it.unisa.model.DAO.Cart.CarrelloRiempitoDao;
import it.unisa.model.beans.CarrelloRiempitoBean;

/**
 * Servlet implementation class CarrelloDefinitivo
 */
@WebServlet("/CarrelloDefinitivo")
public class CarrelloDefinitivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarrelloDefinitivo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			 request.setAttribute("carrello", carrello);
		  } catch (Exception e) {
	      	e.printStackTrace();
	      	response.sendError(500);
	      }
		RequestDispatcher dispatcher = request.getRequestDispatcher("/carrello.jsp");
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
