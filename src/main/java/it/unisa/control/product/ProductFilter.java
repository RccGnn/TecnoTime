package it.unisa.control.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import it.unisa.model.DAO.Articoli.CatalogoDao;
import it.unisa.model.beans.*;

import com.google.gson.*;
/**
 * Servlet implementation class ProductFilter
 */
@WebServlet("/ProductFilter")
public class ProductFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DecimalFormat df = new DecimalFormat("#.00");
	
	private ArrayList<CatalogoBean> nameFilter(ArrayList<CatalogoBean> catalogo, String nomeComparator) {

		ArrayList<CatalogoBean> filtered = new ArrayList<>();
		if (nomeComparator == null || nomeComparator.trim().isEmpty() || catalogo == null) return filtered;

		for (CatalogoBean c : catalogo) {

			String nome = c.getArticolo().getNome();			
			if(nome != null && nome.toLowerCase().contains(nomeComparator.toLowerCase()))
				filtered.add(c);
		}
		return filtered;
	}

	private ArrayList<CatalogoBean> priceFilter(ArrayList<CatalogoBean> catalogo, double min, double max) {
		
		min = Math.abs(min);
		max = Math.abs(max);
		ArrayList<CatalogoBean> filtered = new ArrayList<>();
		if (min > max || catalogo == null) return filtered;
		
		for (CatalogoBean c : catalogo) {

			double price = 0;
			// L'articolo considerato è un prodotto digitale
			if (c.getPdDigitale() != null)
				price = c.getPdDigitale().getPrezzo();
			// L'articolo considerato è un prodotto fisico
			else if (c.getPdFisico() != null)
				price = c.getPdFisico().getPrezzo();
			// L'articolo considerato è un servizio
			else if (c.getServizio() != null)
				price = c.getServizio().getPrezzo();
			
			if(price >= min && price <= max)
				filtered.add(c);
		}
		return filtered;
	}
	
	/**
	 * Restituisce un'array di letterali (anch'essi array) json del tipo [immagine, nome, prezzo, descrizione]
	 * @param 	catalogo	lista di articoli
	 * @return	{@code ArrayList<JsonObject>} lista di array jsonj di articoli
	 */
	private ArrayList<JsonObject> Jsonify(ArrayList<CatalogoBean> catalogo) {
		ArrayList<JsonObject> listaJson = new ArrayList<>();
		if (catalogo == null || catalogo.size() == 0)	return listaJson;
		
		for (CatalogoBean c : catalogo) {
			
			double price = 0;
			String descrizione = null;
			if (c.getPdDigitale() != null) {
				price = c.getPdDigitale().getPrezzo();
				descrizione = c.getPdDigitale().getDescrizione();
			}
			else if (c.getPdFisico() != null) {
				price = c.getPdFisico().getPrezzo();
				descrizione = c.getPdFisico().getDescrizione();
			}
			else if (c.getServizio() != null) {
				price = c.getServizio().getPrezzo();
				descrizione = c.getServizio().getDescrizione();
			}
			
			JsonObject obj = new JsonObject();
			obj.addProperty("nome", c.getArticolo().getNome());
			obj.addProperty("descrizione", descrizione);
			obj.addProperty("prezzo", df.format(price));
			ArrayList<ImmagineBean> imgs = c.getImmagini();
			if(imgs == null || imgs.isEmpty())
				obj.addProperty("immagine", "");
			else
				obj.addProperty("immagine", DecoderDropboxUrl(imgs.get(0).getUrl()));
			
			System.out.println(obj.toString());
			listaJson.add(obj);
		}
		
		return listaJson;
	}
	
	 /* GET:
	  * 	- min (prezzo) - float 
	  * 	- max (prezzo) - float
	  * 	- name (nome articolo) - String
	  * 	- sort (ordine) prezzo/nome asc/disc - String
	 */
	// Sfrutta il fatto che getParameter ritorna il valore null se il parametro non esiste
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double min = (req.getParameter("min") != null) ? Double.parseDouble(req.getParameter("min")) : 0;
        double max = (req.getParameter("max") != null) ? Double.parseDouble(req.getParameter("max")) : Double.MAX_VALUE;
        String nome = (req.getParameter("name") != null) ? req.getParameter("name") : null; 
        String sort = req.getParameter("sort");
        
        CatalogoDao dao = new CatalogoDao();
        System.out.println("MIN: "+min +"\nMAX:"+ max +"\nNOME:"+ nome +"\nSORT:"+ sort );
        // Ordinamento dei prodotti - sfrutta doRetrieveAll(), inoltre esso già effettua controlli sulla stringa passata come parametro esplicito
        
        try {
	        ArrayList<CatalogoBean> catalogo = dao.doRetrieveAll(sort); 
	        
	        // Filtra per il prezzo
	        catalogo = priceFilter(catalogo, min, max);
	        
	        // Filtra facendo un match sul nome
	        if (nome != null && !nome.trim().equals(""))
	        	catalogo = nameFilter(catalogo, nome);
	        
	        ArrayList<JsonObject> listaJson = Jsonify(catalogo);
	        		
	        resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(listaJson.toString());
			
			out.flush();
			
        } catch (SQLException e) {
        	System.err.println(e.getMessage());
        	resp.sendError(500, "Errore sul DB: " + e.getMessage()); // Pagina errore
        }        
        
        /*
         * }catch (SQLException e) {
		    	request.setAttribute("serverError", "Errore d'accesso:"); //eventuale pagina errore
		    }catch (NullPointerException e) {
		    	request.setAttribute("serverError", "Errore recupero dati  Riprova piu tardi"); //eventuale pagina errore        	
		    }finally {
		    	request.setAttribute("serverError", "Errore generico riprova piu tardi"); //eventuale pagina errore        	
		    }
         */

      }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	private String DecoderDropboxUrl(String url) {
		
		String newUrl;
		if(url == null || url.trim().equals(""))
			newUrl = url;
		else
			newUrl = url.replace("&dl=0", "&raw=1");
		
		return newUrl;
	}
} 