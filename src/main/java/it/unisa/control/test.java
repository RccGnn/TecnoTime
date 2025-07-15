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
import it.unisa.model.DAO.Order.ElementoOrdineDao;
import it.unisa.model.DAO.Order.OrdineCompletoDao;
import it.unisa.model.DAO.Order.OrdineDao;
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
    
    private final String TEST_SERIALE = "TEST-SER-001";
    private final String TEST_CODICE_IDENTIFICATIVO = "TEST-ART-001";

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			OrdineCompletoDao dao = new OrdineCompletoDao();
			ArrayList<OrdineCompletoBean> ord = dao.doRetrieveAllByUsername("username");
			
			ord.forEach(o -> System.out.println(o.toString()));
			

		} catch(SQLException e) {
			e.printStackTrace();
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
