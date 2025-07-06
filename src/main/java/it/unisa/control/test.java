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

import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.DAO.Articoli.ArticoloDao;
import it.unisa.model.DAO.Articoli.ProdottoDigitaleDao;
import it.unisa.model.DAO.Articoli.ProdottoFisicoDao;
import it.unisa.model.DAO.Cart.CarrelloRiempitoDao;
import it.unisa.model.beans.ArticoloBean;
import it.unisa.model.beans.ArticoloCompletoBean;
import it.unisa.model.beans.CarrelloBean;
import it.unisa.model.beans.CarrelloRiempitoBean;
import it.unisa.model.beans.ProdottoDigitaleBean;
import it.unisa.model.beans.ProdottoFisicoBean;
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
		ProdottoFisicoDao pf = new ProdottoFisicoDao();
		try {
			/*
			CarrelloRiempitoDao dao = new CarrelloRiempitoDao();
			CarrelloRiempitoBean bean = dao.doRetrieveByKey("mrossi");
		    ArrayList<ArticoloCompletoBean> arr = bean.getListaArticoli();
		    arr.forEach(c -> System.out.println(c.toString()));
			
			bean = dao.doEmpty("mrossi");
			arr = bean.getListaArticoli();
		    arr.forEach(c -> System.out.println(c.toString()));
			*/
			CarrelloRiempitoDao dao = new CarrelloRiempitoDao();
			ArrayList<ArticoloCompletoBean> artBean = new ArrayList<>();
			ArticoloCompletoDao aDao = new ArticoloCompletoDao();
			
			ArticoloCompletoBean art = aDao.doRetrieveByKey("ART100");
			
			artBean.add(art);
			
			
			CarrelloRiempitoBean bean = new CarrelloRiempitoBean();
		    bean.setAccount_username("mrossi");
		    bean.setListaArticoli(artBean);
		    dao.doSave(bean);
			
			
			/*
			ArrayList<String> a = new ArrayList<>();
			a.add("ART400");
			a.add("PF100A");
			ProdottoFisicoBean pfb =  pf.doRetrieveByKey(a);
			System.out.println(pfb.toString());
			 */
	/*
			ArrayList<ProdottoFisicoBean> pfb =  pf.doRetrieveAll("");
			System.out.println("LALALA");
			
			pfb.forEach(c -> {
				System.out.println(c.toString());			
			});
	*/	
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
