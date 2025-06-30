package it.unisa.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import it.unisa.model.DAO.Articoli.CatalogoDao;
import it.unisa.model.DAO.Articoli.ProdottoDigitaleDao;
import it.unisa.model.beans.ArticoloBean;
import it.unisa.model.beans.CatalogoBean;
import it.unisa.model.beans.ProdottoDigitaleBean;
import it.unisa.model.beans.ProdottoFisicoBean;
import it.unisa.model.beans.ServizioBean;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CatalogoDao  a = new CatalogoDao();
		
		// Creo e inizializzo il CatalogoBean
        CatalogoBean catalogo = new CatalogoBean();

        // 1) Articolo
        ArticoloBean articolo = new ArticoloBean();
        articolo.setCodiceIdentificativo("ART002");
        articolo.setCategoria("Software");
        articolo.setNome("Microsoft Office 365");
        articolo.setDataUltimaPromozione(LocalDate.now());
        articolo.setEnteErogatore("Microsoft");
        articolo.setDisponibilita(true);
        catalogo.setArticolo(articolo);

        // 2) Prodotto Fisico
        catalogo.setPdFisico(null);

        // 3) Prodotto Digitale
        catalogo.setPdDigitale(null);

        // 4) Servizio
        ServizioBean servizio = new ServizioBean();
        servizio.setCodiceServizio("SRV700");
        servizio.setDescrizione("Installazione e configurazione Office 365");
        servizio.setPrezzo(49.99f);
        servizio.setDurata(2); // ore
        servizio.setArticolo_codiceIdentificativo(articolo.getCodiceIdentificativo());
        catalogo.setServizio(servizio);

        // Stampa a console
        System.out.println(catalogo);
        
		try {
			a.doSave(catalogo);
			response.getWriter().println(catalogo.toString());
		} catch (Exception e){
			System.err.print(e.getMessage());
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
