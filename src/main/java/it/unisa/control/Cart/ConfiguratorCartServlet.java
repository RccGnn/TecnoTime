package it.unisa.control.Cart;

import jakarta.servlet.RequestDispatcher;
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
import it.unisa.model.DAO.ComponentiFisici.AlimentatoreDao;
import it.unisa.model.DAO.ComponentiFisici.CaseDao;
import it.unisa.model.DAO.ComponentiFisici.ProcessoreDao;
import it.unisa.model.DAO.ComponentiFisici.RamDao;
import it.unisa.model.DAO.ComponentiFisici.SchedaMadreDao;
import it.unisa.model.DAO.ComponentiFisici.SchedaVideoDAO;
import it.unisa.model.Filters.Alimentatore;
import it.unisa.model.Filters.BuildChecker;
import it.unisa.model.Filters.Processore;
import it.unisa.model.Filters.Ram;
import it.unisa.model.Filters.SchedaMadre;
import it.unisa.model.Filters.SchedaVideo;
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

		// Controllo parametri non nulli
		for (Boolean flag : flags) {
			if(!flag) {
				response.sendError(500, "Errore nella lettura dei parametri");
				return;
			}
		}
		
		 	String rawMobo = request.getParameter("motherboard");
	        String processor = request.getParameter("processor");
	        String Case =  request.getParameter("_case");
	        String rame = request.getParameter("ram");
	        String gpus = request.getParameter("gpu");
	        String ssd = request.getParameter("storage");
	        String psus = request.getParameter("psu"); 
	        String ventole = request.getParameter("fans");
	        
	        ProcessoreDao p = new ProcessoreDao();
	        AlimentatoreDao a = new AlimentatoreDao();
	        SchedaVideoDAO g = new SchedaVideoDAO();
	        RamDao r = new RamDao();
	        CaseDao c = new CaseDao();
	        SchedaMadreDao m = new SchedaMadreDao();
	        
	        Processore cpur = null;
	        SchedaMadre mb = null;
	        SchedaVideo gpur= null;
	        Ram ramr = null;
	        Alimentatore psur = null;
	        it.unisa.model.Filters.Case contenitore = null;
	        
	        try {
	        	cpur=p.doRetrieveByKey(processor);
	        	psur=a.doRetrieveByKey(psus);
	        	gpur=g.doRetrieveByKey(gpus);
	        	ramr=r.doRetrieveByKey(rame);
	        	contenitore=c.doRetrieveByKey(Case);
	        	mb=m.doRetrieveByKey(rawMobo);
	        	
	        }catch ( SQLException e) {
	        	e.printStackTrace();
	        }
	        
	      Boolean result = BuildChecker.buildValidator(contenitore, mb, cpur, ramr, gpur,psur);
	      
	      System.out.println(result);
	    	if(result) {
	    		request.setAttribute("result", Boolean.TRUE);
	    	}
	    	else {
	    		request.setAttribute("result", Boolean.FALSE);
	    		RequestDispatcher disp = request.getRequestDispatcher("/configuratore.jsp");
		    	disp.forward(request, response);
		    	return;
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
