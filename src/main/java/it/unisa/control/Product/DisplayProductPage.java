package it.unisa.control.Product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import it.unisa.control.Decoder;
import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.beans.ArticoloCompletoBean;

/**
 * Servlet implementation class DisplayProductPage
 */
@WebServlet("/DisplayProductPage")
public class DisplayProductPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		boolean modify = Boolean.parseBoolean(request.getParameter("modify")); // parseBoolean interpreta come false qualsiasi stringa diversa da true (case insensitive)
        
		if (id == null) response.sendError(500, "id inserito non valido");
		ArticoloCompletoDao artDao = new ArticoloCompletoDao();
		
		try {
			ArticoloCompletoBean artBean = artDao.doRetrieveByKey(id);
			if (artBean.getImmagini() != null) { // Immagini di dropbox
				artBean.getImmagini().forEach(img -> img.setUrl(Decoder.DecoderDropboxUrl(img.getUrl())));
			}

			request.setAttribute("articolo", artBean);
			RequestDispatcher rd = null;
			if (modify) { // Utente amministratore; reindirizzazemento alla modifica di un prodotto
				rd = request.getRequestDispatcher("/amministratore/modificaProdotto.jsp");
			} else {
				rd = request.getRequestDispatcher("/articolo-single.jsp");
			}
			rd.forward(request, response);

		} catch (Exception e) {
			response.sendError(500, "Articolo non trovato");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
