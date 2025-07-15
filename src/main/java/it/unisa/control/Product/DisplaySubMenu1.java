package it.unisa.control.Product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class DisplaySubMenu1
 */
@WebServlet("/DisplaySubMenu1")
public class DisplaySubMenu1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String subField = request.getParameter("sub"); // Sottocategoria da filtrare
		String destination = request.getParameter("destination"); // JSP di destinazione
		
		request.setAttribute("subField", subField);
		
		RequestDispatcher rd = null;
		// SubMenù di prodotto fisico
		if (destination.equals("fisico")) {
			rd = request.getRequestDispatcher("articoliProdotti.jsp");
		} else if (destination.equals("digitale")) {
			rd = request.getRequestDispatcher("articoliLicenze.jsp");
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
