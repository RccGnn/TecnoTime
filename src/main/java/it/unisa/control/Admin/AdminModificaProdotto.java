package it.unisa.control.Admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.beans.ArticoloCompletoBean;

/**
 * Servlet implementation class AdminModificaProdotto
 */
@WebServlet("/AdminModificaProdotto")
public class AdminModificaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idArticolo = (request.getParameter("productId") != null && !request.getParameter("productId").trim().equals("")) ? request.getParameter("productId") : null;
		int quantità = (request.getParameter("quantity") != null && !request.getParameter("quantity").trim().equals("")) ? Integer.parseInt(request.getParameter("quantity")) : -1;
		double prezzo = (request.getParameter("price") != null && !request.getParameter("price").trim().equals("")) ? Double.parseDouble(request.getParameter("price")) : Double.MAX_VALUE;
		String descrizione = (request.getParameter("description") != null && !request.getParameter("description").trim().equals("")) ? request.getParameter("description") : null;
		
		ArticoloCompletoDao dao = new ArticoloCompletoDao();
		ArticoloCompletoBean articoloModifcato = null;
		
		System.out.println("idArticolo: "+idArticolo+"\nprezzo:"+prezzo+"\nQuantità: "+quantità+"\nDescrizione: "+descrizione+"\nArticoloModificato: ");
		try {
			dao.updateByKey(idArticolo, prezzo, quantità, descrizione);
			articoloModifcato = dao.doRetrieveByKey(idArticolo);
			
		} catch (SQLException e) {
			response.sendError(500, "Errore nella modifica del prezzo");
		}
		
		request.setAttribute("messaggio", "Modifica avvenuta correttamente!");
		request.setAttribute("articolo", articoloModifcato);
		RequestDispatcher rd = request.getRequestDispatcher("amministratore/modificaProdotto.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletrequestuest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
