package it.unisa.control.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
//import java.text.DecimalFormat;
import java.util.ArrayList;

import it.unisa.control.Decoder;
import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.beans.*;

import com.google.gson.*;

/**
 * Servlet implementation class ProductFilter
 */
@WebServlet("/ProductFilter")
public class ProductFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private DecimalFormat df = new DecimalFormat("#.00");
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double min = (req.getParameter("min") != null && !req.getParameter("min").equals("")) ? Double.parseDouble(req.getParameter("min")) : 0;
        double max = (req.getParameter("max") != null && !req.getParameter("max").equals("")) ? Double.parseDouble(req.getParameter("max")) : Double.MAX_VALUE;
        String nome = (req.getParameter("name") != null && !req.getParameter("name").equals("")) ? req.getParameter("name") : null; 
        String sort = req.getParameter("sort");
        String contesto = (req.getParameter("contex") != null && !req.getParameter("contex").equals("")) ? req.getParameter("contex") : null;
        double durata = (req.getParameter("duration") != null && !req.getParameter("duration").equals("")) ? Double.parseDouble(req.getParameter("duration")) : -1;
        		
        ArticoloCompletoDao dao = new ArticoloCompletoDao();
        System.out.println("MIN: "+min +"\nMAX:"+ max +"\nNOME:"+ nome +"\nSORT:"+ sort+"\nCONTEX: "+contesto+"\nDuration: "+durata);
        // Ordinamento dei prodotti - sfrutta doRetrieveAll(), inoltre esso già effettua controlli sulla stringa passata come parametro esplicito
        
        try {
	        ArrayList<ArticoloCompletoBean> catalogo = dao.doRetrieveAll(sort); 
	        
	        // Filtra per il contesto
	        Filters.contexFilter(catalogo, contesto);

	        // Filtra per il prezzo
	        Filters.priceFilter(catalogo, min, max);
	        //System.out.println(catalogo.toString());
	        
	        // Filtra facendo un match sul nome
	        if (nome != null)
	        Filters.nameFilter(catalogo, nome);
	     
	        // Se si tratta di un servizio, filtra per durata del servizio
	        // - Di norma questo filtro non dovrebbe sortire effetto se il contesto != "articoliServizi.jsp"
	        if (durata != -1)
	         Filters.durationFilter(catalogo, durata);
	        //System.out.println(catalogo.toString());
	        
	        for (ArticoloCompletoBean c : catalogo) {
				// Modifica le immagini in un formato visibile
	        	ArrayList<ImmagineBean> imgs = c.getImmagini();
	        	if (imgs != null) {
					imgs.forEach(img -> img.setUrl(Decoder.DecoderDropboxUrl(img.getUrl())) );
					c.setImmagini(imgs);
				} else {
					c.setImmagini(new ArrayList<ImmagineBean>(0));
				}
			}
	        
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        // Serializza l'intera lista di ArticoloCompletoBean in una singola stringa JSON
            String jsonOutput = gson.toJson(catalogo);

	        System.out.println("JSON Output finale inviato:\n" + jsonOutput);

	        PrintWriter out = resp.getWriter();
			out.print(jsonOutput); // Scrivi la stringa JSON nel PrintWriter
			out.flush();
			
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

	
	 /* GET:
	  * 	- min (prezzo) - float 
	  * 	- max (prezzo) - float
	  * 	- name (nome articolo) - String
	  * 	- sort (ordine) prezzo/nome asc/disc - String
	 */
	// Sfrutta il fatto che getParameter ritorna il valore null se il parametro non esiste
    
} 