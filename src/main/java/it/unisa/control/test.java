package it.unisa.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import it.unisa.model.DAO.Articoli.*;
import it.unisa.model.DAO.Articoli.ProdottoFisicoDao;
import it.unisa.model.beans.*;
import it.unisa.model.beans.ProdottoFisicoBean;

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
			
				ProdottoFisicoDao i = new ProdottoFisicoDao();
				try {
					ProdottoFisicoBean art = i.doRetrieveByKey("PF1001");
					PrintWriter pw = response.getWriter();
					pw.println("+\n"+art.toString()+"\n");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				try {
					ArrayList<ProdottoFisicoBean> art = i.doRetrieveAll("");
					PrintWriter pw = response.getWriter();
					
					for (ProdottoFisicoBean ab : art) {
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
