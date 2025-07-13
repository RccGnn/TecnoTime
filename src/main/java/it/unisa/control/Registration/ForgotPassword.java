package it.unisa.control.Registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import it.unisa.control.PasswordUtils;
import it.unisa.model.DAO.Account.AccountDao;
import it.unisa.model.beans.AccountBean;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getParameter("username").trim();
		String pwd= (String) request.getParameter("pwd");
		pwd=PasswordUtils.hashPassword(pwd);
		AccountDao accDao= new AccountDao();
		AccountBean account = new AccountBean();

		boolean result = false;
		try {
			account=accDao.doRetrieveByKey(username);
			if(account!=null) {
				account.sethashedPassword(pwd);
				result = accDao.UpdateAccountpwd(account);
			}
			else {
				request.setAttribute("error", Boolean.TRUE);	
				RequestDispatcher dispatcher = request.getRequestDispatcher("/forgotpassword.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if (result)
			request.setAttribute("confirmMessage", "Password modificata con successo!");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginPage.jsp");
		dispatcher.forward(request, response);
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}
}
