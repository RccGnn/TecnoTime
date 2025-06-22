package it.unisa.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import it.unisa.ajaxexample.model.Capoluogo;

/**
 * Servlet implementation class CercaProvincia
 */
@WebServlet("/CercaProvincia")
public class CercaProvincia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CercaProvincia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super();
        response.setContentType("application/json");
        String CAP = request.getParameter("CAP");
        Capoluogo capoluogo = null;
        if (CAP != null && !CAP.equals("")) {
            capoluogo = daoCapoluogo.findCapolougoByCAP(CAP);
        }
        String risultato = null;
        if (capoluogo != null) {
            risultato = capoluogo.getNome() + " (" + capoluogo.getRegione() + ")";
        } else {
            risultato = "non trovato";
        }
        JSONObject json = new JSONObject();
        json.put("functionName", "aggiornaDatiCapoluogoJSON");
        json.put("result", risultato);
        out.print(json.toString());
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
		doGet(request, response);
	}

}
