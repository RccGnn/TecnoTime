package it.unisa.control.Authentication;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import it.unisa.control.PasswordUtils;
import it.unisa.model.DAO.BeanDaoInterface;
import it.unisa.model.DAO.BeanDaoInterfaceArray;
import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.DAO.Account.AccountDao;
import it.unisa.model.DAO.Cart.CarrelloDao;
import it.unisa.model.beans.AccountBean;
import it.unisa.model.beans.CarrelloBean;

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
        
        username.trim();
        pwd.trim(); 
        AccountBean account = new AccountBean(); 
        BeanDaoInterface<AccountBean> dao= new AccountDao();   
        BeanDaoInterfaceArray<CarrelloBean> daoCarrello = new CarrelloDao();  //creo il dao per memorizzare il carrello nella sessione
        ArrayList<String> key =new ArrayList<>(); //creo un arraylist per memorizzare la chiave del dao
        CarrelloBean carrello= new CarrelloBean();
        
        try {
        	 account = dao.doRetrieveByKey(username);
        	 if(account != null) { 
        		 if(PasswordUtils.checkPasswordHashed(pwd, account.gethashedPassword())==true) {
        			 request.setAttribute("flag","passok");
        		 }else {
        			 request.setAttribute("error", "username o password errati"); 
        			 System.out.println("password errate");
        	         RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginPage.jsp");
        	         dispatcher.forward(request, response);      
        	         return;
        		 }
        		 
        		 System.out.println("UsernameAAAAAAAAA: " + username);
        		 key.add(username);   
        		 carrello=daoCarrello.doRetrieveByKey(key);  //recupera il carrello associato all username nel db
             }
        }catch (SQLException e) {
        	request.setAttribute("serverError", "Errore d'accesso: Login Fallito. Riprova"); //eventuale pagina errore
        }catch (NullPointerException e) {
        	request.setAttribute("serverError", "Errore recupero dati (null): Login Fallito. Riprova"); //eventuale pagina errore        	
        }finally {
        	request.setAttribute("serverError", "Errore"); //eventuale pagina errore        	
        }
        
        HttpSession session = request.getSession();
        if(DaoUtils.getRuoloAccountString(account)=="amministratore") {
        	System.out.println("amministratore");
        	//creazione sessione admin
            session.setAttribute("admin", Boolean.TRUE);
            session.setAttribute("username", account.getUsername());
            session.setAttribute("carrelloid", carrello.getCarrello_Id());
            response.sendRedirect(request.getContextPath() + "/amministratore/index-amministratore.jsp");	// Redirect a pagina protetta
        }
        else if(DaoUtils.getRuoloAccountString(account)=="utente_registrato") {
        	System.out.println("utente");
        	 //creazione sessione utente 
            session.setAttribute("user", Boolean.TRUE);  
            session.setAttribute("username", account.getUsername());
            session.setAttribute("carrelloid", carrello.getCarrello_Id());
            response.sendRedirect(request.getContextPath() + "/utente/index-utente.jsp");	// Redirect a pagina protetta
            System.out.println(request.getContextPath() + "/utente/index-utente.jsp");
        }        
	}

}
