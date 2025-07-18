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
		
		ProdottoFisicoBean pf = new ProdottoFisicoBean();
		
		pf.setArticolo_codiceIdentificativo("ART1000");
		pf.setCategoria("RAM");
		pf.setCodiceIdentificativo("ART1000");
		pf.setDataUltimaPromozione(new Date(System.currentTimeMillis()));
		pf.setDescrizione("ALALALALALALALLALALALAL");
		pf.setDisponibilita(false);
		pf.setEnteErogatore("intel");
		pf.setNome("LUCCA PALLA");
		pf.setPreassemblato(false);
		pf.setPrezzo(13.30f);
		pf.setQuantitaMagazzino(10);
		pf.setSeriale("PDF-1000");
		
		ProdottoFisicoDao dao = new ProdottoFisicoDao();
		
		try {
			dao.doSave(pf);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}
