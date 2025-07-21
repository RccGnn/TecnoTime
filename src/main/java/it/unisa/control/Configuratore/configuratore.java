package it.unisa.control.Configuratore;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
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

import it.unisa.model.Filters.Case;
/**
 * Servlet implementation class configuratore
 */
@WebServlet("/configuratore")
public class configuratore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public configuratore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	String rawMobo = request.getParameter("motherboard");
	        String processor = request.getParameter("processor");
	        String Case =  request.getParameter("_case");
	        String rame = request.getParameter("ram");
	        String gpus = request.getParameter("gpu");
	        String ssd = request.getParameter("storage");
	        String psus = request.getParameter("psu"); 
	        String ventole = request.getParameter("fans");
	        
	        
	        
	        System.out.println(Case);        
	        ArticoloCompletoBean mobo = new ArticoloCompletoBean();
	        ArticoloCompletoBean cpu = new ArticoloCompletoBean();
	        ArticoloCompletoBean ram = new ArticoloCompletoBean();
	        ArticoloCompletoBean gpu = new ArticoloCompletoBean();
	        ArticoloCompletoBean psu = new ArticoloCompletoBean();
	        ArticoloCompletoBean fan = new ArticoloCompletoBean();
	        ArticoloCompletoBean storage = new ArticoloCompletoBean();
	        ArticoloCompletoBean _case = new ArticoloCompletoBean();
	       
	        
	        
	        ArticoloCompletoDao mobod = new ArticoloCompletoDao();
	        ArticoloCompletoDao cpud = new ArticoloCompletoDao();
	        ArticoloCompletoDao ramd = new ArticoloCompletoDao();
	        ArticoloCompletoDao gpud = new ArticoloCompletoDao();
	        ArticoloCompletoDao psud = new ArticoloCompletoDao();
	        ArticoloCompletoDao fand= new ArticoloCompletoDao();
	        ArticoloCompletoDao storaged = new ArticoloCompletoDao();
	        ArticoloCompletoDao _cased  = new ArticoloCompletoDao();
	       
	        
	        try {
	        	mobo=mobod.doRetrieveByName(rawMobo);
	        	cpu=cpud.doRetrieveByName(processor);
	        	ram=ramd.doRetrieveByName(rame);
	        	gpu=gpud.doRetrieveByName(gpus);
	        	psu=psud.doRetrieveByName(psus);
	        	fan=fand.doRetrieveByName(ventole);
	        	storage=storaged.doRetrieveByName(ssd);
	        	_case=_cased.doRetrieveByName(Case);
	        }catch(SQLException e) {
	        	e.printStackTrace();
	        }
	        
	        
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
	        Case contenitore = null;
	        
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
	    	}
	        
	    	RequestDispatcher disp = request.getRequestDispatcher("/configuratore.jsp");
	    	disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
