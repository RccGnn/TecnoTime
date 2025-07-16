package it.unisa.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import it.unisa.model.DAO.Account.AccountDao;
import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.DAO.Articoli.ArticoloDao;
import it.unisa.model.DAO.Articoli.ProdottoDigitaleDao;
import it.unisa.model.DAO.Articoli.ProdottoFisicoDao;
import it.unisa.model.DAO.Cart.CarrelloRiempitoDao;
import it.unisa.model.DAO.ComponentiFisici.CaseDao;
import it.unisa.model.DAO.ComponentiFisici.ProcessoreDao;
import it.unisa.model.DAO.ComponentiFisici.RamDao;
import it.unisa.model.DAO.ComponentiFisici.SchedaMadreDao;
import it.unisa.model.DAO.ComponentiFisici.SchedaVideoDAO;
import it.unisa.model.DAO.Order.ElementoOrdineDao;
import it.unisa.model.DAO.Order.OrdineCompletoDao;
import it.unisa.model.DAO.Order.OrdineDao;
import it.unisa.model.Filters.BuildChecker;
import it.unisa.model.Filters.Case;
import it.unisa.model.Filters.Processore;
import it.unisa.model.Filters.Ram;
import it.unisa.model.Filters.SchedaMadre;
import it.unisa.model.Filters.SchedaVideo;
import it.unisa.model.beans.AccountBean;
import it.unisa.model.beans.ArticoloBean;
import it.unisa.model.beans.ArticoloCompletoBean;
import it.unisa.model.beans.CarrelloBean;
import it.unisa.model.beans.CarrelloRiempitoBean;
import it.unisa.model.beans.ElementoOrdineBean;
import it.unisa.model.beans.OrdineBean;
import it.unisa.model.beans.OrdineCompletoBean;
import it.unisa.model.beans.ProdottoDigitaleBean;
import it.unisa.model.beans.ProdottoFisicoBean;
import it.unisa.model.beans.Ruoli;
import it.unisa.model.beans.ServizioBean;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SchedaMadreDao mbDao = new SchedaMadreDao();
		ProcessoreDao cpuDao = new ProcessoreDao();
		RamDao ramDao= new RamDao();
		CaseDao caseDao = new CaseDao();
		SchedaVideoDAO gpuDao= new SchedaVideoDAO();
		Case tipoCase=null;
		Ram ram= null;
		Processore cpu=null;
		SchedaMadre mb=null;
		SchedaVideo gpu=null;
		
		try {
			tipoCase=caseDao.doRetrieveByKey("NZXT H7 Flow");
			ram=ramDao.doRetrieveByKey("Corsair Vengeance DDR5 32GB (2x16GB)");
			mb=mbDao.doRetrieveByKey("Asus ROG Maximus Z790 HERO");
			cpu=cpuDao.doRetrieveByKey("Intel Core i9-13900K");
			gpu=gpuDao.doRetrieveByKey("NVIDIA GEFORCE RTX 4090");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		boolean res = false;
		int minimo=0;
		if(cpu!=null && mb!=null) {
			res=BuildChecker.isCompatible(cpu, mb) && BuildChecker.isCompatible(mb, ram) && BuildChecker.isCompatible(tipoCase, mb);
			minimo=BuildChecker.minimumWatt(mb, gpu, cpu);
		}else {
			System.out.println("cpu o mb null");
		}
		System.out.println("il controllo è "+res+ " il minimo alimentatore è"+minimo+ "watt");
	
	
	}

}
