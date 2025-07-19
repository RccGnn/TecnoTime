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
        String username = (request.getParameter("username") != null && !request.getParameter("username").trim().equals("")) ? request.getParameter("username") : null; 
        
        boolean user = false, admin = false;
        if (request.getSession().getAttribute("user") != null)
        	user = (boolean) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("admin") != null)
        	admin = (boolean) request.getSession().getAttribute("admin");
        
        System.out.println("Price:" + priceMax + "-" + priceMin + "\nDate: "+ dateLowerBound +"-"+dateUpperBound + "\nUsername: " + username);
        ArrayList<OrdineCompletoBean> listaOrdini = null;
        OrdineCompletoDao dao = new OrdineCompletoDao();
        ArrayList<Object> resultToJson = new ArrayList<Object>(2);
        
        try {
            if (user) {
            	resultToJson.add(false); // Flag per indicare se la richiesta proviene dalle pagine dell'admin
            	String accountUsername = (String) request.getSession().getAttribute("username");
            	listaOrdini = dao.doRetrieveAllByUsername(accountUsername);
            }
            if (admin) {
            	resultToJson.add(true);            	
            	if (username != null && !username.trim().equals("")) {
            		// Recupera gli ordini di un solo account
            		listaOrdini = dao.doRetrieveAllByUsername(username);
            		
            	} else {
            		// recupera tutti gli ordini di tutti gli account
            		listaOrdini = dao.doRetrieveAllOrders();
            	}

            }        	
        } catch (SQLException e){
        	response.sendError(500, "Errore nella ricerca degli ordini");
        }
        
        if (priceMin <= priceMax)
        	Filters.priceOrderFilter(listaOrdini, priceMin, priceMax);
        
        if (dateLowerBound != null || dateUpperBound != null)
        	Filters.dateOrderFilter(listaOrdini, dateLowerBound, dateUpperBound);
        
        resultToJson.add(listaOrdini);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(resultToJson);
        
        System.out.println("output" + jsonOutput);
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
