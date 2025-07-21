package it.unisa.control.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.control.CookieUtils;
import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.DAO.Cart.CarrelloRiempitoDao;
import it.unisa.model.beans.ArticoloCompletoBean;
import it.unisa.model.beans.CarrelloRiempitoBean;

/**
 * Servlet implementation class ConfiguratorCartServlet
 */
@WebServlet("/ConfiguratorCartServlet")
public class ConfiguratorCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// La funzione restituisce falso se non può leggere un parametro dalla request
	private boolean addParameter(HttpServletRequest request, String paramName, ArrayList<String> paramList) {
		boolean flag = true;
		String temp = request.getParameter(paramName);
		if(temp != null && !temp.trim().equals("")) {
			paramList.add(temp);
		} else {
			flag = false;
		}
		
		return flag;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> paramList = new ArrayList<>();
		ArrayList<Boolean> flags = new ArrayList<>();
		
		// Leggi tutti i parametri
		flags.add(addParameter(request, "_case", paramList));
		flags.add(addParameter(request, "processor", paramList));
		flags.add(addParameter(request, "motherboard", paramList));
		flags.add(addParameter(request, "ram", paramList));
		flags.add(addParameter(request, "gpu", paramList));
		flags.add(addParameter(request, "storage", paramList));
		flags.add(addParameter(request, "psu", paramList));
		flags.add(addParameter(request, "fans", paramList));

		for (Boolean flag : flags) {
			if(!flag) {
				response.sendError(500, "Errore nella lettura dei parametri");
				return;
			}
		}
		
		
		// Costruisci la chiave da usare per il doRetrieve di carrello
        ArrayList<ArticoloCompletoBean> lista = new ArrayList<ArticoloCompletoBean>();				
		ArrayList<String> keys= new ArrayList<String>(2);

		String values[] = new String[2];
		HttpSession s = request.getSession();
		// Recupera username e carrello_id dalla sessione
		if (s != null && s.getAttribute("username") != null) {
			values[0] = (String) s.getAttribute("username");
			values[1] = (String) s.getAttribute("carrello_id");
		} else {
			// Altrimenti recupera questi dati dai cookies
			values = CookieUtils.getUsernameCartIdfromCookies(request);
		}

		keys.add(values[0]);
		keys.add(values[1]);
		
		try {
			
			System.out.println("Username: "+keys.get(0)+"-ID: "+keys.get(1));
			CarrelloRiempitoDao carDao = new CarrelloRiempitoDao();
		    CarrelloRiempitoBean carrello = carDao.doRetrieveByKey(keys);
		    
		    ArticoloCompletoDao artDao = new ArticoloCompletoDao();
		    ArticoloCompletoBean articolo = new ArticoloCompletoBean();

		    // Aggiungi gli articoli
		    lista = carrello.getListaArticoli();		    
		    for(String name: paramList) {
		    	try {
					articolo = artDao.doRetrieveByName(name);
					lista.add(articolo);
				} catch (SQLException e) {
					response.sendError(500, "Errore lettura dei parametri");
					return;
				}
		    }
		    
		    carrello.setListaArticoli(lista);
			System.out.println("Lista Carrello: " + lista); 
			carDao.doSave(carrello, false); // Aggiorna il carrello ma NON salvare di nuovo il carrello Stesso
			
        } catch (Exception e) {
        	e.printStackTrace();
        	response.sendError(500, "Errore salvataggio nel carrello");
        	return;
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
