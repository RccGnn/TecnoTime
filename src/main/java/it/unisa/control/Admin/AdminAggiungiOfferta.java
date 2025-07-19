package it.unisa.control.Admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import it.unisa.model.DAO.Promotion.PromozioneDao;
import it.unisa.model.beans.PromozioneBean;

/**
 * Servlet implementation class AdminOfferte
 */
@WebServlet("/AdminOfferte")
public class AdminAggiungiOfferta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAggiungiOfferta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nome= request.getParameter("nomesconto");
		String sconto = request.getParameter("percentuale");
		String prodotto= request.getParameter("prodotto");
		String descrizione= request.getParameter("descrizione");
		
		PromozioneBean promo = new PromozioneBean();
		PromozioneDao promoDao= new PromozioneDao();
		
		if(nome!=null || sconto != null || descrizione != null) {
			promo.setNomesconto(nome);
			promo.setDescrizione(descrizione);
			promo.setPercentualeSconto(Double.parseDouble(sconto));
			
			try {
				promoDao.doSave(promo);
			}catch(Exception e){
				e.printStackTrace();				
			}
		}else {
			request.setAttribute("scontoErrore", "errore generico nell applicazione dello sconto. Riprova più tardi");
			RequestDispatcher disp = request.getRequestDispatcher("/.jsp");
			disp.forward(request, response);
		}
		
		request.setAttribute("scontoapplicato","sconto applicato con successo");
		
		RequestDispatcher disp = request.getRequestDispatcher("/.jsp");
		disp.forward(request, response);
	}

}
