package it.unisa.control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.*;

/**
 * Servlet implementation class RegistrationFormValidator
 */
@WebServlet("/RegistrationFormValidator")
public class RegistrationFormValidator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationFormValidator() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String control = request.getParameter("control");
		
		JsonObject check = new JsonObject();
		String errMessage = "";
		
		if (!control.contains("@")) {
			if (Validator.checkUsername(control)) {
				errMessage = "None";
			} else {
				errMessage = "Username già in uso";
			}	
		} else {
			if (Validator.checkEmail(control)) {
				errMessage = "None";
			} else {
				errMessage = "Email già in uso";
			}
		}
		
		check.addProperty("errore", errMessage);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(check.toString());
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
