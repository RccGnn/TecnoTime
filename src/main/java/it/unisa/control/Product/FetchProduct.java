package it.unisa.control.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.DAO.ComponentiFisici.AlimentatoreDao;
import it.unisa.model.DAO.ComponentiFisici.CaseDao;
import it.unisa.model.DAO.ComponentiFisici.ProcessoreDao;
import it.unisa.model.DAO.ComponentiFisici.RamDao;
import it.unisa.model.DAO.ComponentiFisici.SchedaMadreDao;
import it.unisa.model.DAO.ComponentiFisici.SchedaVideoDAO;
import it.unisa.model.Filters.Alimentatore;
import it.unisa.model.Filters.BuildChecker;
import it.unisa.model.Filters.Case;
import it.unisa.model.Filters.Processore;
import it.unisa.model.Filters.Ram;
import it.unisa.model.Filters.SchedaMadre;
import it.unisa.model.Filters.SchedaVideo;
import it.unisa.model.beans.ArticoloCompletoBean;

/**
 * Servlet implementation class FetchProduct
 */
@WebServlet("/FetchProduct")
public class FetchProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String rawNameM = request.getParameter("nameM");
	 
		 	ArrayList<Processore> cp= new ArrayList<Processore>();  // array da filtrare
 			ArrayList<Ram> ra = new ArrayList<Ram>();  	//array da filtrare
 			
 			ArrayList<ArticoloCompletoBean> artcpu = new ArrayList<ArticoloCompletoBean>();
 			ArrayList<ArticoloCompletoBean> artram = new ArrayList<ArticoloCompletoBean>();
 			
			ArrayList<SchedaMadre> mobos = new ArrayList<SchedaMadre>();
	 		ArrayList<Processore> cpus = new ArrayList<Processore>();
	 		ArrayList<Ram> rams = new ArrayList<Ram>();
	 		ArrayList<Alimentatore> psus= new ArrayList<Alimentatore>();
	 		ArrayList<SchedaVideo> gpus = new ArrayList<SchedaVideo>();
	 		ArrayList<Case> cases= new ArrayList<Case>();
	 		
	 		ProcessoreDao cpuDao = new ProcessoreDao();
	 		RamDao ramDao = new RamDao();
	 		AlimentatoreDao psuDao = new AlimentatoreDao();
	 		SchedaVideoDAO gpuDao = new SchedaVideoDAO();
	 		SchedaMadreDao moboDao= new SchedaMadreDao();
	 		CaseDao caseDao = new CaseDao();
	 		SchedaMadre mobo = null;
	 		
	 		try {
	 			mobos=moboDao.doRetrieveAll("");
	 			cpus=cpuDao.doRetrieveAll("");
	 			psus=psuDao.doRetrieveAll("");
	 			cases=caseDao.doRetrieveAll("");
	 			gpus=gpuDao.doRetrieveAll("");
	 			rams=ramDao.doRetrieveAll("");
	 			cpus=cpuDao.doRetrieveAll("");
	 		}catch(SQLException e ) {
	 			e.printStackTrace();
	 		}
 		
		 

	        if (rawNameM != null && !rawNameM.trim().isEmpty()) {
	            String cleanNameM = rawNameM.split(" - ")[0].trim(); // Rimuove " - € prezzo" se presente
	            
	            boolean mb = false;
	    		
	            
	    		
	            
		           ArticoloCompletoDao daoartcpu = new ArticoloCompletoDao();
		           ArticoloCompletoBean artbeancpu= new ArticoloCompletoBean();
		           ArticoloCompletoBean artRam = new ArticoloCompletoBean();
		           ArticoloCompletoDao daoartram = new ArticoloCompletoDao();
		           
	    		for (SchedaMadre artmobo : mobos) {
	    		    if (artmobo.nome().equalsIgnoreCase(cleanNameM)) {
	    		        mb = true;
	    		        try {
			    			mobo=moboDao.doRetrieveByKey(cleanNameM);
			    		}catch (SQLException e) {
			    			e.printStackTrace();
			    		}
	    		        for (Processore p : cpus) {
	    	    		    if (BuildChecker.isCompatible(p, mobo)) {
	    	    		    	try{
	    	    		    		artbeancpu=daoartcpu.doRetrieveByName(p.nome());
	    	    		    	}catch (SQLException e) {
	    			    			e.printStackTrace();
	    			    		}
	    	    		    	artcpu.add(artbeancpu);
	    	    		        
	    	    		    }
	    		        }
	    		        for(Ram r: rams) {
	    		        	if (BuildChecker.isCompatible(mobo, r)) {
	    		        		try{
	    		        			artRam=daoartram.doRetrieveByName(r.nome());
	    	    		    	}catch (SQLException e) {
	    			    			e.printStackTrace();
	    			    		}
	    	    		    	artram.add(artRam);
	    	    		        
	    	    		    }
	    	    		        
	    	    		        
	    		        	}
	    		        }	
	    		    }
	        }
	        
	        Gson gson = new Gson();
	        ArrayList<Object> obj = new  ArrayList<Object>();
	        obj.add(artcpu);
	        obj.add(artram);
	        String out = gson.toJson(obj);
	        response.getWriter().println(out);
	            
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
