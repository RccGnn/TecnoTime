package it.unisa.control.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.DAO.Order.OrdineCompletoDao;
import it.unisa.model.beans.OrdineCompletoBean;

/**
 * Servlet implementation class OrderAdmin
 */
@WebServlet("/OrderAdmin")
public class OrderAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	    String username = request.getParameter("username");
	    ArrayList<OrdineCompletoBean> ordiniUtente = null;

	    if (username != null && !username.trim().isEmpty()) {
	        OrdineCompletoDao dao = new OrdineCompletoDao();
	       
	        try {
	        	 ordiniUtente = dao.doRetrieveAllByUsername(username.trim());
	        }catch (SQLException e ) {
	        	e.printStackTrace();
	        }
	    }
		request.setAttribute("ordiniutente", ordiniUtente);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
