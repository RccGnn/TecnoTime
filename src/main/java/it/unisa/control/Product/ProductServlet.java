package it.unisa.control.Product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unisa.model.DAO.Articoli.ArticoloDao;
import it.unisa.model.beans.ArticoloBean;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<ArticoloBean> prodotti= new ArrayList<>();
		ArticoloDao dao = new ArticoloDao();
		  
		    try {
		    	prodotti = dao.doRetrieveAll("");
		    }catch (SQLException e) {
		    	request.setAttribute("serverError", "Errore d'accesso:"); //eventuale pagina errore
		    }catch (NullPointerException e) {
		    	request.setAttribute("serverError", "Errore recupero dati  Riprova piu tardi"); //eventuale pagina errore        	
		    }finally {
		    	request.setAttribute("serverError", "Errore generico riprova piu tardi"); //eventuale pagina errore        	
		    }
		    
        request.setAttribute("prodotti",prodotti);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/AllProductPage.jsp");
        dispatcher.forward(request, response);
        
       /* request.setAttribute("nome", prodotto.getNome());
        request.setAttribute("marca/ente", prodotto.getEnteErogatore());
        request.setAttribute("prezzo", prodotto.getPrezzo());
        request.setAttribute("codiceIdentificativo", prodotto.getCodiceIdentificativo());
        request.setAttribute("disponibilità", prodotto.isDisponibile());
        request.setAttribute("seriale", prodotto.getSeriale());
        request.setAttribute("isPreassemblato", prodotto.isPreassemblato());
        request.setAttribute("quantità", prodotto.getQuantità())
        request.setAttribute("descrizione", prodotto.getDescrizione());
        request.setAttribute("CodiceID", prodotto.getCodiceIdentificativo());
        request.setAttribute("categoria", prodotto.getCategoria());*/
        
	}
		
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
