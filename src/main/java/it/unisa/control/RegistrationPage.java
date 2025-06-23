package it.unisa.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import it.unisa.model.DAO.AccountDao;
import it.unisa.model.DAO.BeanDaoInterface;
import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.beans.AccountBean;
import it.unisa.model.beans.Ruoli;

/**
 * Servlet implementation class RegistrationPage
 */
@WebServlet("/RegistrationPage")
public class RegistrationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationPage() {
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
		//doGet(request, response);
		
		AccountBean account = new AccountBean();
		
		
		
	String error="";
		
		String firstName= request.getParameter("firstName");
		String lastName= request.getParameter("lastName");
		String birthDateStr= request.getParameter("birthDate");
		String address= request.getParameter("address");
		String postalCodeStr= request.getParameter("postalCode");		
		String email= request.getParameter("email");
		String password=request.getParameter("password");
		String telNumb=request.getParameter("telNumb");
		String gender= request.getParameter("gender");
		String nation= request.getParameter("nation");
		String username= request.getParameter("username");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String aptnumber=request.getParameter("aptnumber");
		String role = ""; // Da ricavare
		char genderChr = ' ';
		
		
		// invece di fare tutti i controlli, il model crea l'oggetto user con la classe DAO
		if (firstName == null || firstName.trim().equals("")) {
			error += "Insert name<br>";
			account=null;
		} else {
			firstName = firstName.trim();
			firstName= DecoderHtml.encodeHtml(firstName);
			request.setAttribute("fistName", firstName);
			account.setNome(firstName);			
		}

		if (lastName == null || lastName.trim().equals("")) {
			error += "Insert lastName<br>";
			account=null;
		} else {
			lastName = lastName.trim();
			lastName= DecoderHtml.encodeHtml(lastName);
			request.setAttribute("lastName", lastName);
			account.setCognome(lastName);
		}
		
		LocalDate birthDate=null;
		if (birthDateStr == null || birthDateStr.trim().equals("")) {
			error += "Insert birthdate<br>";
			account=null;
		} else {
			birthDateStr = birthDateStr.trim();
			birthDateStr= DecoderHtml.encodeHtml(birthDateStr);
			try {
				 birthDate=LocalDate.parse(birthDateStr);
			}catch (DateTimeParseException e){
				  error += "Invalid date format<br>";
			}
			request.setAttribute("birthDate", birthDate);
			account.setDataNascita(birthDate);
		}
				
		if (address == null || address.trim().equals("")) {
			error += "Insert address<br>";
			account=null;
		} else {
			address = address.trim();
			address= DecoderHtml.encodeHtml(address);
			request.setAttribute("address", address);
			account.setVia(address);
		}
		
		
		if (postalCodeStr == null || postalCodeStr.trim().equals("")) {
			error += "Invalid postalCode<br>";
			account=null;
		} else {
			postalCodeStr = postalCodeStr.trim();
			if(postalCodeStr.matches("\\d{5}")) {
				request.setAttribute("postalCode", postalCodeStr);
				account.setCAP(postalCodeStr);
			}else {
				error+="Invalid postalCode<br>";
				account=null;
			}
		}
		
		if(password==null || password.trim().equals("")) {
			error += "must insert a password";
			account=null;
		}else {
			password= password.trim();
			password=DecoderHtml.encodeHtml(password);         
		    if(pwdValidator.isValid(password)==false) {         	
		    request.setAttribute("pwderror","password non valida" );  
		    account=null;
		    }else {    
		    	password=PasswordUtils.hashPassword(password);       //la stringa di ritorno deve essere memorizzata nel db con l'oggetto user  	
		    	account.sethashedPassword(password);
			}
		}
		
		if (email == null || email.trim().equals("")) {
			error += "Invalid email selected <br>";
			account=null;
		} else {
			if(!PasswordUtils.checkEmail(username, email)) {
				error += "Email alredy in use <br>";
				account.setEmail(""); // E' necessario per il controllo dell'email
			} else {
				email = email.trim();
				email= DecoderHtml.encodeHtml(email);
				request.setAttribute("email", email);
				account.setEmail(email);
			}
		}
		
		if (gender == null || gender.trim().equals("")) {
			error += "Insert gender<br>";
			account=null;
		} else {
			gender = gender.trim();
			gender= DecoderHtml.encodeHtml(gender);
		    genderChr = gender.charAt(0);  //prende solo il primo carattere della stringa e lo salva in una variabile char
			request.setAttribute("gender", genderChr );
			account.setSesso(genderChr);
		}
		
	
		if (telNumb == null || telNumb.trim().equals("")||telNumb.matches("//d+")){
		    error += "Insert a correct telephone number<br>";
		    account=null;
		} else {
		    telNumb = telNumb.trim();
		    telNumb= DecoderHtml.encodeHtml(telNumb);
		    request.setAttribute("telNumb", telNumb);
			account.setNumeroTelefono(telNumb);
		}
		
		
		if (nation == null || nation.trim().equals("")) {
			error += "Invalid nation selected <br>";
			account=null;
		} else {
			nation = nation.trim();
			nation= DecoderHtml.encodeHtml(nation);
			request.setAttribute("nation",nation);
			account.setNazione(nation);
		}
		
		if (username == null || username.trim().equals("")) {
			error += "Invalid username selected <br>";
			account=null;
		} else {
			if(!PasswordUtils.checkUsername(username)) {
				error += "Username alredy in use <br>";
				account.setUsername("");
			} else {
				username = username.trim();
				username= DecoderHtml.encodeHtml(username);
				request.setAttribute("username",username);
				account.setUsername(username);	
			}
		}
		
		if (province == null || province.trim().equals("")) {
			error += "Invalid province selected <br>";
			account=null;
		} else {
			province = province.trim();
			province= DecoderHtml.encodeHtml(province);
			request.setAttribute("province",province);
			account.setProvincia(province);
		}
		
		if (city == null || city.trim().equals("")) {
			error += "Invalid city selected <br>";
			account=null;
		} else {
			city = city.trim();
			city= DecoderHtml.encodeHtml(city);
			request.setAttribute("city",city);
			account.setCitta(city);
		}
		
		if (aptnumber == null || aptnumber.trim().equals("")) {
			error += "Invalid apartment number <br>";
			account=null;
		} else {
			aptnumber = aptnumber.trim();
			aptnumber= DecoderHtml.encodeHtml(aptnumber);
			request.setAttribute("aptnumber",aptnumber);
			account.setNumeroCivico(aptnumber);
		}

		
		role = DaoUtils.getRuoloAccountString(account);
		account.setRuolo(DaoUtils.getRuoloAccount(account));
		
		if (role == null || role.trim().equals("")) {
			error += "Invalid role <br>";
			account=null;
		} else {
			role = role.trim();			
			request.setAttribute("role",role);
		}

		if (!error.equals("")) {
			request.setAttribute("error", error);
			account=null;
		}
		

		BeanDaoInterface<AccountBean> dao = new AccountDao();
		try {
			dao.doSave(account);
		} catch (java.sql.SQLException e) {
			e.getMessage();
			System.out.println("Errore SQL: " + e);
		} catch (Exception e) {
			e.getMessage();
			System.out.println(e);
		}
		
	/*	RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/RegistrationPage.jsp");
		dispatcher.forward(request, response); */
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Registration.jsp");
        dispatcher.forward(request, response); 		
        

	}
	
}
