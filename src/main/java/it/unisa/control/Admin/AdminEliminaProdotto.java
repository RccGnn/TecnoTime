package it.unisa.control.Admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.DAO.Promotion.PromozioneDao;
import it.unisa.model.beans.ArticoloCompletoBean;

/**
 * Servlet implementation class AdminEliminaProdotto
 */
@WebServlet("/AdminEliminaProdotto")
public class AdminEliminaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<ArticoloCompletoBean> listaArticoli = new ArrayList<>();
		ArticoloCompletoDao artDao = new ArticoloCompletoDao();
		
		try {
			listaArticoli = artDao.doRetrieveAll("");
			if(listaArticoli == null || listaArticoli.isEmpty()) {
				request.setAttribute("errorRetrive", "Nessun articolo presente nel db. Aggiungere prima un nuovo articolo. ");
				RequestDispatcher disp = request.getRequestDispatcher("amministratore/rimuoviProdotto.jsp");
				disp.forward(request, response);
				return;
			}
		} catch (SQLException e) {
			response.sendError(500, "Errore nel recupero degli articoli");
		}
		
		request.setAttribute("listaArticoli", listaArticoli);
		RequestDispatcher rd = request.getRequestDispatcher("amministratore/rimuoviProdotto.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String codiceIdentificativo = request.getParameter("codiceIdentificativo");
		System.out.println(codiceIdentificativo);
		ArticoloCompletoDao dao = new ArticoloCompletoDao();
		boolean result = false;
		try {
			result = dao.doDelete(codiceIdentificativo);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		if(result)
			request.setAttribute("eliminazioneSuccess", "Articolo [" + codiceIdentificativo + "] eliminato dal DB con successo!");
		else
			request.setAttribute("eliminazioneError", " [ERRORE]: Impossibile rimuovere l'articolo ["+ codiceIdentificativo + "]. Riprova più tardi.");
		
		doGet(request, response);
	}

}
