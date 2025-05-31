package it.unisa.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginPage() {
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
		
		String login = request.getParameter("username");
        String pwd = request.getParameter("password");
        
        //usare una funzione di hashing per la password
        String message="password non valida";
        if(pwdValidator.isValid(pwd)==false) {
        	request.setAttribute("pwderror", message);
        }
        
        //controllo con if se i dati nel db sono corretti mando al model i dati nel caso siano incorretti manda un messaggio di errore alla request
        
       /* request.setAttribute("error", Boolean.TRUE);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginPage.jsp");
        dispatcher.forward(request, response);
         
         HttpSession session = request.getSession();
        session.setAttribute("user", user);

        // Redirect a pagina protetta
        response.sendRedirect(request.getContextPath() + "/home.jsp");*/
	}

}
