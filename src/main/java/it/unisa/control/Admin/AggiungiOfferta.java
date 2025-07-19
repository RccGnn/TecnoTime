package it.unisa.control.Admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import it.unisa.model.DAO.Promotion.PromozioneDao;
import it.unisa.model.beans.PromozioneBean;

/**
 * Servlet implementation class AggiungiOfferta
 */
@WebServlet("/AggiungiOfferta")
public class AggiungiOfferta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiOfferta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nome= request.getParameter("nomesconto");
		String sconto = request.getParameter("discountType");
		String prodotto= request.getParameter("prodotto");
		String descrizione= request.getParameter("descrizione");
		
		PromozioneBean promo = new PromozioneBean();
		PromozioneDao promoDao= new PromozioneDao();
		PromozioneBean verifica = new PromozioneBean();
		
		
		if(nome!=null || sconto != null || descrizione != null) {
			promo.setNomesconto(nome);
			promo.setDescrizione(descrizione);
			promo.setPercentualeSconto(Double.parseDouble(sconto));
			promo.setDataInizio(Date.valueOf(LocalDate.now()));
			
			try {
				verifica=promoDao.doRetrieveByKey(nome);
				if(verifica!=null) {
					request.setAttribute("scontoPresente", "Lo sconto selezionato è gia presente nel sistema");
					RequestDispatcher disp = request.getRequestDispatcher("amministratore/aggiungiOfferta.jsp");
					disp.forward(request, response);
					return;
				}
				promoDao.doSave(promo);
			}catch(Exception e){
				e.printStackTrace();				
			}
		}else {
			request.setAttribute("scontoErrore", "errore generico nell applicazione dello sconto. Riprova più tardi");
			RequestDispatcher disp = request.getRequestDispatcher("amministratore/aggiungiOfferta.jsp");
			disp.forward(request, response);
			return;
		}
		
		request.setAttribute("scontoapplicato","sconto applicato con successo");
		
		RequestDispatcher disp = request.getRequestDispatcher("amministratore/aggiungiOfferta.jsp");
		disp.forward(request, response);
	}

}
