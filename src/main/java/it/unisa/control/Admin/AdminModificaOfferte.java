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
 * Servlet implementation class AdminModificaOfferte
 */
@WebServlet("/AdminModificaOfferte")
public class AdminModificaOfferte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminModificaOfferte() {
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
				RequestDispatcher disp = request.getRequestDispatcher("amministratore/modificaOfferta.jsp");
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
		RequestDispatcher disp = request.getRequestDispatcher("amministratore/modificaOfferta.jsp");
		disp.forward(request, response);
		return;
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String nome = request.getParameter("nome");
		String descrizione = request.getParameter("descrizione");
		Double prezzo = Double.parseDouble(request.getParameter("sconto"));
		
		
		PromozioneDao dao = new PromozioneDao();
		boolean result = false;
		try {
			result = dao.doUpdate(nome, descrizione, prezzo);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		if(result)
			request.setAttribute("modifySuccess", "Offerta " + nome+ " modifica con successo!");
		else
			request.setAttribute("modifyError", " [ERRORE]: Impossibile modificare l'offerta "+ nome + ". Riprova più tardi.");
		
		doGet(request, response);
		//RequestDispatcher disp = request.getRequestDispatcher("amministratore/modificaOfferta.jsp");
		//disp.forward(request, response);
	}

}
