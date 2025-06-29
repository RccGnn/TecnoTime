package it.unisa.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import it.unisa.model.DAO.Articoli.ArticoloDao;
import it.unisa.model.DAO.Articoli.ImmagineDao;
import it.unisa.model.DAO.Articoli.ServizioDao;
import it.unisa.model.beans.ArticoloBean;
import it.unisa.model.beans.ImmagineBean;
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
			
				ServizioDao i = new ServizioDao();
				try {
					ServizioBean art = i.doRetrieveByKey("SRV001");
					PrintWriter pw = response.getWriter();
					pw.println("+\n"+art.toString()+"\n");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				try {
					ArrayList<ServizioBean> art = i.doRetrieveAll("");
					PrintWriter pw = response.getWriter();
					
					for (ServizioBean ab : art) {
						pw.println("+\n"+ab.toString());
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
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
