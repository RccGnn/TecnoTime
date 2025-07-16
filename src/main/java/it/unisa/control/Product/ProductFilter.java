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

import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.DAO.Promotion.AssociatoADao;
import it.unisa.model.DAO.Promotion.PromozioneCompletaDao;
import it.unisa.model.beans.*;

import com.google.gson.*;

/**
 * Servlet implementation class ProductFilter
 */
@WebServlet("/ProductFilter")
public class ProductFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double min = (req.getParameter("min") != null && !req.getParameter("min").trim().equals("")) ? Double.parseDouble(req.getParameter("min")) : 0;
        double max = (req.getParameter("max") != null && !req.getParameter("max").trim().equals("")) ? Double.parseDouble(req.getParameter("max")) : Double.MAX_VALUE;
        String nome = (req.getParameter("name") != null && !req.getParameter("name").trim().equals("")) ? req.getParameter("name") : null; 
        String sort = req.getParameter("sort");
        String contesto = (req.getParameter("contex") != null && !req.getParameter("contex").trim().equals("")) ? req.getParameter("contex") : null;
        double durata = (req.getParameter("duration") != null && !req.getParameter("duration").trim().equals("")) ? Double.parseDouble(req.getParameter("duration")) : -1;
        boolean searchBar = Boolean.parseBoolean(req.getParameter("fromSearchBar")); // parseBoolean interpreta come false qualsiasi stringa diversa da true (case insensitive)
        String categoria = (req.getParameter("categoriaInput") != null && !req.getParameter("categoriaInput").trim().equals("")) ? req.getParameter("categoriaInput") : null; 
        
        ArticoloCompletoDao dao = new ArticoloCompletoDao();
        PromozioneCompletaDao promDao = new PromozioneCompletaDao();
        
        //System.out.println("MIN: "+min +"\nMAX:"+ max +"\nNOME:"+ nome +"\nSORT:"+ sort+"\nCONTEX: "+contesto+"\nDuration: "+durata);
        // Ordinamento dei prodotti - sfrutta doRetrieveAll(), inoltre esso già effettua controlli sulla stringa passata come parametro esplicito
        
        try {
	        ArrayList<ArticoloCompletoBean> catalogo = dao.doRetrieveAll(sort); 

	        // Ricerca effettuata dalla barra di navigazione centrale
        	if(searchBar) {
        		if (nome != null)
        			Filters.nameFilter(catalogo, nome);	 // Filtra solo sul nome (input utente)
        		else
        			catalogo = new ArrayList<ArticoloCompletoBean>(0);
        	
        	// Ricerca effettuata dalle altre pagine dei prodotti
        	} else {
		        // Filtra per il contesto
		        Filters.contexFilter(catalogo, contesto);

		        // Filtra per il prezzo
		        Filters.priceFilter(catalogo, min, max);
		        
		        // Filtra facendo un match sul nome
		        if (nome != null)
		        	Filters.nameFilter(catalogo, nome);
		    
		        System.out.println("Categoria:" +categoria);
		        // Filtra per la categoria
		        if (categoria != null)
		        	Filters.categoryFilter(catalogo, categoria);
		        
		        // Se si tratta di un servizio, filtra per durata del servizio
		        // - Di norma questo filtro non dovrebbe sortire effetto se il contesto != "articoliServizi.jsp"
		        if (durata != -1)
		        	Filters.durationFilter(catalogo, durata);	        	
	        }
	        
	        catalogo = DaoUtils.dropboxImagesDecoderUrl(catalogo);
	        ArrayList<PromozioneCompletaBean> promozioni = promDao.doRetrieveByKeyProducts("");
	        
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        ArrayList<Object> res = new ArrayList<>();
	        res.add(catalogo);
	        res.add(promozioni);
	        
            // Serializza l'intera lista di ArticoloCompletoBean e promozioni in una singola stringa JSON
            String jsonOutput = gson.toJson(res);
	        System.out.println("JSON Output finale inviato:\n" + jsonOutput);

	        resp.setContentType("application/json");
	        PrintWriter prw = resp.getWriter();
	        prw.print(jsonOutput); // Scrivi la stringa JSON nel PrintWriter
	        prw.flush();
			
        } catch (SQLException e) {
        	System.err.println(e.getMessage());
        	resp.sendError(500, "Errore sul DB: " + e.getMessage()); // Pagina errore
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