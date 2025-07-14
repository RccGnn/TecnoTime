package it.unisa.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
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
import it.unisa.model.DAO.Order.OrdineDao;
import it.unisa.model.beans.AccountBean;
import it.unisa.model.beans.ArticoloBean;
import it.unisa.model.beans.ArticoloCompletoBean;
import it.unisa.model.beans.CarrelloBean;
import it.unisa.model.beans.CarrelloRiempitoBean;
import it.unisa.model.beans.ElementoOrdineBean;
import it.unisa.model.beans.OrdineBean;
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
			
			ElementoOrdineDao elementoOrdineDao = new ElementoOrdineDao();

	        // Create a new ElementoOrdineBean instance
	        ElementoOrdineBean elementoOrdine = new ElementoOrdineBean();

	        // Manually set the fields for the ElementoOrdineBean
	        // These values are hardcoded for demonstration.
	        elementoOrdine.setNumero(1); // Example element number within an order
	        elementoOrdine.setNumeroTransazione(51516); // Must match an existing numeroTransazione from Ordine
	        elementoOrdine.setCodiceArticolo("ART600"); // Example article code
	        elementoOrdine.setNomeArticolo("Smartphone X"); // Example article name
	        elementoOrdine.setUrlImmagineArticolo("http://example.com/images/smartphone_x.jpg"); // Example image URL
	        elementoOrdine.setPrezzoUnitario(499.99); // Example unit price
	        elementoOrdine.setQuantitaArticolo(2); // Example quantity
	        
	        //elementoOrdineDao.doSave(elementoOrdine);
	        
	        ArrayList<ElementoOrdineBean> array = elementoOrdineDao.doRetrieveAll("");
	        System.out.println(array.toString());
		} catch (Exception e) {
			System.err.append(e.getMessage());
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
