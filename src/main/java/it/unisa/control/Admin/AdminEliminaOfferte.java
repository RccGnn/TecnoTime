package it.unisa.control.Admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.DAO.Promotion.PromozioneDao;
import it.unisa.model.beans.PromozioneBean;

/**
 * Servlet implementation class AdminEliminaOfferte
 */
@WebServlet("/AdminEliminaOfferte")
public class AdminEliminaOfferte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEliminaOfferte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PromozioneDao dao = new PromozioneDao();
		ArrayList<PromozioneBean> lista = new ArrayList<PromozioneBean>(); 
		try {
			lista= dao.doRetrieveAll("nomesconto ASC");
			if(lista == null) {
				request.setAttribute("errorRetrive", "Nessuno sconto presente nel db. Aggiungere prima un nuovo sconto ");
				RequestDispatcher disp = request.getRequestDispatcher("amministratore/rimuoviOfferta.jsp");
				disp.forward(request, response);
				return;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		for (PromozioneBean promso : lista) {
			System.out.println(promso);
		}
		
		
		request.setAttribute("lista", lista);
		RequestDispatcher disp = request.getRequestDispatcher("amministratore/rimuoviOfferta.jsp");
		disp.forward(request, response);
		return;
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String nome=request.getParameter("nomesconto");
		System.out.println(nome);
		PromozioneDao dao = new PromozioneDao();
		boolean result=false;
		try {
			result=dao.doDelete(nome);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		if(result)
			request.setAttribute("eliminazioneSuccess","Offerta  " + nome+ " eliminata dal DB con successo");
		else
			request.setAttribute("EliminazioneError", "errore generico.Impossibile rimuovere l'offerta"+ nome + ". Riprova più tardi");
		
		RequestDispatcher disp = request.getRequestDispatcher("amministratore/rimuoviOfferta.jsp");
		disp.forward(request, response);
	}

}
