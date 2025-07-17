package it.unisa.control.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.unisa.model.DAO.Order.OrdineCompletoDao;
import it.unisa.model.beans.OrdineCompletoBean;

/**
 * Servlet implementation class OrderFilter
 */
@WebServlet("/OrderFilter")
public class OrderFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		double priceMin = (request.getParameter("min") != null && !request.getParameter("min").trim().equals("")) ? Double.parseDouble(request.getParameter("min")) : 0;
        double priceMax = (request.getParameter("max") != null && !request.getParameter("max").trim().equals("")) ? Double.parseDouble(request.getParameter("max")) : Double.MAX_VALUE;
        String dateLowerBound = (request.getParameter("dateLowerBound") != null && !request.getParameter("dateLowerBound").trim().equals("")) ? request.getParameter("dateLowerBound") : null; 
        String dateUpperBound = (request.getParameter("dateUpperBound") != null && !request.getParameter("dateUpperBound").trim().equals("")) ? request.getParameter("dateUpperBound") : null; 
        
        boolean user = (boolean) request.getSession().getAttribute("user");
        boolean admin = (boolean) request.getSession().getAttribute("admin");
        
        ArrayList<OrdineCompletoBean> listaOrdini = null;
        OrdineCompletoDao dao = new OrdineCompletoDao();
        try {
            if (user) {
            	String username = (String) request.getSession().getAttribute("username");
            	listaOrdini = dao.doRetrieveAllByUsername(username);
            }
            if (admin) {
            	// recupera tutti gli ordini di tutti gli account
            }        	
        } catch (SQLException e){
        	response.sendError(500, "Errore nella ricerca degli ordini");
        }
        
        Filters.priceOrderFilter(listaOrdini, priceMin, priceMax);
        
        Filters.dateOrderFilter(listaOrdini, dateLowerBound, dateUpperBound);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(listaOrdini);
        
        response.setContentType("application/json");
        PrintWriter prw = response.getWriter();
        prw.print(jsonOutput); // Scrivi la stringa JSON nel PrintWriter
        prw.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
