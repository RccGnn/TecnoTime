package it.unisa.control;

import jakarta.servlet.DispatcherType;
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
 * Servlet implementation class ProductFilter
 */
@WebServlet("/ProductFilter")
public class ProductFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prezzo_min=request.getParameter("prezzo_minimo");
		String prezzo_max=request.getParameter("prezzo_massimo");
		String marca=request.getParameter("marca");
		String categoria=request.getParameter("categoria");
		
		
		List<ArticoloBean> prodotti= new ArrayList<>();
		ArticoloDao dao = new ArticoloDao();
		List<ArticoloBean> pfilter= new ArrayList<>();
		  
		    try {
		    	prodotti = dao.doRetrieveAll("");
		    		for (ArticoloBean p : prodotti) {
		    			if(marca != null && marca.equals(p.getEnteErogatore()))
		    				pfilter.add(p);
		    			if(prezzo_min!=null && prezzo_max!=null && (prezzo_min<=p.getPrezzo()||prezzo_max<=p.getPrezzo)
		    				pfilter.add(p);
		    			if(categoria!=null && categoria.equals(p.getCategoria()))
		    				pfilter.add(p);
		    		}
		    			if(pfilter==null) 
		    				request.setAttribute("error", "Il filtro non ha prodotto risultati");
		    			else
		    				request.setAttribute("filtri", pfilter);
		    }catch (SQLException e) {
		    	request.setAttribute("serverError", "Errore d'accesso:"); //eventuale pagina errore
		    }catch (NullPointerException e) {
		    	request.setAttribute("serverError", "Errore recupero dati  Riprova piu tardi"); //eventuale pagina errore        	
		    }finally {
		    	request.setAttribute("serverError", "Errore generico riprova piu tardi"); //eventuale pagina errore        	
		    }
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/AllProductPage.jsp");
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
