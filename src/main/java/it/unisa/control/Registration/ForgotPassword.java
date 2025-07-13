package it.unisa.control.Registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import it.unisa.control.PasswordUtils;
import it.unisa.model.DAO.BeanDaoInterface;
import it.unisa.model.DAO.Account.AccountDao;
import it.unisa.model.beans.AccountBean;
import it.unisa.model.beans.BeanMarker;

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
		// TODO Auto-generated method stub	
		String username = (String) request.getAttribute("username");
		String pwd= (String) request.getAttribute("pwd");
		PasswordUtils.hashPassword(pwd);
		BeanDaoInterface<AccountBean> accDao= new AccountDao();
		AccountBean account = new AccountBean();
		
		try {
			account=accDao.doRetrieveByKey(username);
			if(account!=null) {
				account.sethashedPassword(pwd);
			}
			else {
				request.setAttribute("error", Boolean.TRUE);	
			}
		}catch(Exception e){
			e.getMessage();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/recuperapassword.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}

}
