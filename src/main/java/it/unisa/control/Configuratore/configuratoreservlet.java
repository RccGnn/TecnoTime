package it.unisa.control.Configuratore;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.model.DAO.Articoli.ArticoloCompletoDao;
import it.unisa.model.beans.ArticoloCompletoBean;

/**
 * Servlet implementation class configuratoreservlet
 */
@WebServlet("/configuratoreservlet")
public class configuratoreservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public configuratoreservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArticoloCompletoDao dao = new ArticoloCompletoDao();
		ArrayList<ArticoloCompletoBean>  processori = new ArrayList<ArticoloCompletoBean>();
		ArrayList<ArticoloCompletoBean>  schedeMadri = new ArrayList<ArticoloCompletoBean>();
		ArrayList<ArticoloCompletoBean>  ram = new ArrayList<ArticoloCompletoBean>();
		ArrayList<ArticoloCompletoBean>  schedeVideo = new ArrayList<ArticoloCompletoBean>();
		ArrayList<ArticoloCompletoBean>  archiviazione = new ArrayList<ArticoloCompletoBean>();
		ArrayList<ArticoloCompletoBean>  alimentatori = new ArrayList<ArticoloCompletoBean>();
		ArrayList<ArticoloCompletoBean>  ventole = new ArrayList<ArticoloCompletoBean>();
		ArrayList<ArticoloCompletoBean>  casePc = new ArrayList<ArticoloCompletoBean>();
		
		try {
			processori= dao.doRetrieveCategory("Processori", "");
			schedeMadri=dao.doRetrieveCategory("schede madri", "");
			ram=dao.doRetrieveCategory("RAM", "");
			schedeVideo = dao.doRetrieveCategory("scheda video","");
			archiviazione= dao.doRetrieveCategory("Memoria Archiviazione", "");
			alimentatori = dao.doRetrieveCategory("alimentatore", "");
			casePc = dao.doRetrieveCategory("Case PC", "");
			ventole = dao.doRetrieveCategory("ventole", "");
		}catch(SQLException e ) {
			e.printStackTrace();
		}
		
		request.setAttribute("ventole", ventole);
		request.setAttribute("alimentatori", alimentatori);
		request.setAttribute("archiviazione", archiviazione);
		request.setAttribute("schedeVideo", schedeVideo);
		request.setAttribute("ram", ram);
		request.setAttribute("schedeMadri", schedeMadri);
		request.setAttribute("processori", processori);
		request.setAttribute("_case",casePc);
		
		RequestDispatcher disp = request.getRequestDispatcher("/configuratore.jsp");
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
