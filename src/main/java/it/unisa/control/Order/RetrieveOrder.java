package it.unisa.control.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.unisa.model.DAO.Promotion.PromozioneDao;
import it.unisa.model.beans.PromozioneBean;

/**
 * Servlet implementation class RetrieveOrder
 */
@WebServlet("/RetrieveOrder")
public class RetrieveOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String offerName = request.getParameter("offername");
		
		PromozioneDao dao = new PromozioneDao();
		PromozioneBean promo = null;
		try {
			promo = dao.doRetrieveByKey(offerName);
		} catch (SQLException e) {
			response.sendError(500, "Errore nel recupero dell'offerta");
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput = gson.toJson(promo);

		response.setContentType("application/json");
		PrintWriter prw = response.getWriter();
		System.out.println("OUTPUTTTTTTTTTTTTTTT: " + jsonOutput);
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
