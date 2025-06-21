package it.unisa.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import it.unisa.model.DAO.AccountDao;
import it.unisa.model.DAO.BeanDaoInterface;
import it.unisa.model.beans.AccountBean;

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
		
		
		String username= request.getParameter("username");
        String pwd = request.getParameter("password");
        
        BeanDaoInterface<AccountBean> dao= new AccountDao();
        //controllo con if se i dati nel db sono corretti mando al model i dati nel caso siano incorretti manda un messaggio di errore alla request

        try {
        	 AccountBean account = dao.doRetrieveByKey(username);
        	 if(account !=null && username.equals(account.getUsername())) {
        		 if(PasswordUtils.checkPasswordHashed(pwd, account.gethashedPassword())==true) {
        			 request.setAttribute("flag","passok");
        		 }else {
        			 request.setAttribute("error", "username o password errati"); 
        		 }
             }
        }catch(SQLException e) {
        	request.setAttribute("serverError", "Login Fallito. Riprova"); //eventuale pagina errore
        }
       
     
         RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginPage.jsp");
        dispatcher.forward(request, response);
       
        /* request.setAttribute("error", Boolean.TRUE);
       
         HttpSession session = request.getSession();
        session.setAttribute("user", user);

        // Redirect a pagina protetta
        response.sendRedirect(request.getContextPath() + "/home.jsp");*/
	}

}
