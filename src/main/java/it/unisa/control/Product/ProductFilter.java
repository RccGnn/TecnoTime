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
import it.unisa.model.DAO.Promotion.PromozioneCompletaDao;
import it.unisa.model.beans.*;

import com.google.gson.*;

/**
 * Servlet implementation class ProductFilter
 */
@WebServlet("/ProductFilter")
public class ProductFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Indica il numero di prodotti che possono essere visualizzati per pagina
	private static final int articlesPerPage = 12;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double min = (req.getParameter("min") != null && !req.getParameter("min").trim().equals("")) ? Double.parseDouble(req.getParameter("min")) : 0;
        double max = (req.getParameter("max") != null && !req.getParameter("max").trim().equals("")) ? Double.parseDouble(req.getParameter("max")) : Double.MAX_VALUE;
        String nome = (req.getParameter("name") != null && !req.getParameter("name").trim().equals("")) ? req.getParameter("name") : null; 
        String sort = req.getParameter("sort");
        String contesto = (req.getParameter("contex") != null && !req.getParameter("contex").trim().equals("")) ? req.getParameter("contex") : null;
        double durata = (req.getParameter("duration") != null && !req.getParameter("duration").trim().equals("")) ? Double.parseDouble(req.getParameter("duration")) : -1;
        boolean searchBar = Boolean.parseBoolean(req.getParameter("fromSearchBar")); // parseBoolean interpreta come false qualsiasi stringa diversa da true (case insensitive)
        String categoria = (req.getParameter("categoriaInput") != null && !req.getParameter("categoriaInput").trim().equals("")) ? req.getParameter("categoriaInput") : null; 
        String brand = (req.getParameter("marcaInput") != null && !req.getParameter("marcaInput").trim().equals("")) ? req.getParameter("marcaInput") : null;
        int page = (req.getParameter("page") != null && !req.getParameter("page").trim().equals("")) ? Integer.parseInt(req.getParameter("page")) : 1;
        boolean modify = Boolean.parseBoolean(req.getParameter("modify")); // parseBoolean interpreta come false qualsiasi stringa diversa da true (case insensitive)
        
        ArticoloCompletoDao dao = new ArticoloCompletoDao();
        PromozioneCompletaDao promDao = new PromozioneCompletaDao();
        
        //System.out.println("MIN: "+min +"\nMAX:"+ max +"\nNOME:"+ nome +"\nSORT:"+ sort+"\nCONTEX: "+contesto+"\nDuration: "+durata);
        // Ordinamento dei prodotti - sfrutta doRetrieveAll(), inoltre esso già effettua controlli sulla stringa passata come parametro esplicito
        
        double length = 0, limit = 0, numeroPagine = 0, offset = 0;

        try {
        	
	        ArrayList<ArticoloCompletoBean> catalogo = null;

	        // Ricerca effettuata dalla barra di navigazione centrale
        	if(searchBar) {
        		
        		if (nome != null)
        			catalogo = dao.doRetrieveAllSearch(sort, nome);	 // Filtra solo sul nome (input utente)
        		
        		if (catalogo == null)
        			catalogo = new ArrayList<ArticoloCompletoBean>(0);
        		
        	// Ricerca effettuata dalle altre pagine dei prodotti
        	} else {
        		
        		length = dao.countQueries(); // conta il numero delle query
        		numeroPagine = (int) Math.ceil((double) length / (double) ProductFilter.articlesPerPage); // numero di pagine
        		int modulo = (int) Math.ceil(((double) length / (double) 2));
            	if (numeroPagine < 4) {
            		catalogo = dao.doRetrieveAll(sort);
            	} else {
            		catalogo = dao.doRetrieveAllLimit(sort, (int) numeroPagine, (int) page);            		
            	}
        		
		        // Filtra per il contesto
		        Filters.contexFilter(catalogo, contesto);
        		System.out.println(catalogo.size());		        

        		// Filtra per marca
		        if (brand != null)
		        	Filters.brandFilter(catalogo, brand);
		        
		        // Filtra facendo un match sul nome
		        if (nome != null)
		        	Filters.nameFilter(catalogo, nome);
		    
		        // Filtra per il prezzo
		        Filters.priceFilter(catalogo, min, max);
		        
		        // Filtra per la categoria
		        if (categoria != null)
		        	Filters.categoryFilter(catalogo, categoria);
		        
		        // Se si tratta di un servizio, filtra per durata del servizio
		        // - Di norma questo filtro non dovrebbe sortire effetto se il contesto != "articoliServizi.jsp"
		        if (durata != -1)
		        	Filters.durationFilter(catalogo, durata);	
		        		
		        limit = articlesPerPage * page; // limite (indice) dell'ultimo elemento accettabile per pagina
		        offset = (page - 1) * articlesPerPage;

		        if(limit > offset && offset >= modulo && limit >= modulo) {
		        	offset = offset % modulo;
		        	limit = limit % modulo;
		        }
		        
		        numeroPagine = (int) Math.ceil((double)catalogo.size() / (double)ProductFilter.articlesPerPage); // numero di pagine
		        System.out.println("Lunghezza: " + length + "\numeroPagine: "+numeroPagine + "\nPagina: " + page + "\noffset: " + offset + "\nLimit: "+ limit +"\nSize: "+ catalogo.size());
		        
		        catalogo = Filters.pageFilter(catalogo, offset % modulo, limit % modulo);
		        
	        }
        	
        	
        	catalogo = DaoUtils.dropboxImagesDecoderUrl(catalogo);
	        ArrayList<PromozioneCompletaBean> promozioni = promDao.doRetrieveByKeyProducts("");
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        ArrayList<Object> res = new ArrayList<>();
	        res.add(catalogo);
	        res.add(promozioni);
	        res.add(numeroPagine);
	        res.add(modify); // La richiesta è arrivata dall'admin, article-single page deve premettere di modificare l'articolo
	        
            // Serializza l'intera lista di ArticoloCompletoBean e promozioni in una singola stringa JSON
            String jsonOutput = gson.toJson(res);
	        //System.out.println("JSON Output finale inviato:\n" + jsonOutput);

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