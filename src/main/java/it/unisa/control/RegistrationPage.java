package it.unisa.control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
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
		String passwordConfirm=request.getParameter("passwordConfirm");
		String role = ""; // Da ricavare
		char genderChr = ' ';
		
		
		// invece di fare tutti i controlli, il model crea l'oggetto user con la classe DAO
		if (firstName == null || firstName.trim().equals("")) {
			error += "Insert name<br>";
		} else {
			firstName = firstName.trim();
			firstName= DecoderHtml.encodeHtml(firstName);
			request.setAttribute("fistName", firstName);
			account.setNome(firstName);			
		}

		if (lastName == null || lastName.trim().equals("")) {
			error += "Insert lastName<br>";
		} else {
			lastName = lastName.trim();
			lastName= DecoderHtml.encodeHtml(lastName);
			request.setAttribute("lastName", lastName);
			account.setCognome(lastName);
		}
		
		LocalDate birthDate=null;
		if (birthDateStr == null || birthDateStr.trim().equals("")) {
			error += "Insert birthdate<br>";
		} else {
			birthDateStr = birthDateStr.trim();
			birthDateStr= DecoderHtml.encodeHtml(birthDateStr);
			try {
				 birthDate=LocalDate.parse(birthDateStr);
				 request.setAttribute("birthDate", birthDate);
				 account.setDataNascita(birthDate);
			}catch (DateTimeParseException e){
				  error += "Invalid date format<br>";
			}
			
			
		}
				
		if (address == null || address.trim().equals("")) {
			error += "Insert address<br>";
		} else {
			address = address.trim();
			address= DecoderHtml.encodeHtml(address);
			request.setAttribute("address", address);
			account.setVia(address);
		}
		
		
		if (postalCodeStr == null || postalCodeStr.trim().equals("")) {
			error += "Invalid postalCode<br>";
		} else {
			postalCodeStr = postalCodeStr.trim();
			if(postalCodeStr.matches("\\d{5}")) {
				request.setAttribute("postalCode", postalCodeStr);
				account.setCAP(postalCodeStr);
			}else {
				error+="Invalid postalCode<br>";
			}
		}
		
		if(password==null || password.trim().equals("")) {
			error += "must insert a password";
		}else {
			password= password.trim();
			password=DecoderHtml.encodeHtml(password);         
		    if(pwdValidator.isValid(password,passwordConfirm)==false) {         	
		    	request.setAttribute("pwderror","password non valida" );  
		    }else {    
		    	password=PasswordUtils.hashPassword(password);       //la stringa di ritorno deve essere memorizzata nel db con l'oggetto user  	
		    	account.sethashedPassword(password);
			}
		}
		//implementare box di conferma password nella jsp
		
		if (email == null || email.trim().equals("")) {
			error += "Invalid email selected <br>";
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
		} else {
			gender = gender.trim();
			gender= DecoderHtml.encodeHtml(gender);
		    genderChr = gender.charAt(0);  //prende solo il primo carattere della stringa e lo salva in una variabile char
			request.setAttribute("gender", genderChr );
			account.setSesso(genderChr);
		}
		
	
		if (telNumb == null || telNumb.trim().equals("")||!telNumb.matches("\\d+")){
		    error += "Insert a correct telephone number<br>";
		} else {
		    telNumb = telNumb.trim();
		    telNumb= DecoderHtml.encodeHtml(telNumb);
		    request.setAttribute("telNumb", telNumb);
			account.setNumeroTelefono(telNumb);
		}
		
		
		if (nation == null || nation.trim().equals("")) {
			error += "Invalid nation selected <br>";
		} else {
			nation = nation.trim();
			nation= DecoderHtml.encodeHtml(nation);
			request.setAttribute("nation",nation);
			account.setNazione(nation);
		}
		
		if (username == null || username.trim().equals("")) {
			error += "Invalid username selected <br>";
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
		} else {
			province = province.trim();
			province= DecoderHtml.encodeHtml(province);
			request.setAttribute("province",province);
			account.setProvincia(province);
		}
		
		if (city == null || city.trim().equals("")) {
			error += "Invalid city selected <br>";
		} else {
			city = city.trim();
			city= DecoderHtml.encodeHtml(city);
			request.setAttribute("city",city);
			account.setCitta(city);
		}
		
		if (aptnumber == null || aptnumber.trim().equals("")) {
			error += "Invalid apartment number <br>";
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
		} else {
			role = role.trim();			
			request.setAttribute("role",role);
		}
		
		

		if (!error.equals("")) {
			request.setAttribute("error", error);
			return;
		}
		

		BeanDaoInterface<AccountBean> dao = new AccountDao();
		try {
		    dao.doSave(account);
		} catch (SQLException e) {
		    String sqlState = e.getSQLState();
		    if (sqlState != null && sqlState.startsWith("23")) {
		        // Username già presente
		        request.setAttribute("error", "Username già esistente, scegline un altro.");
		        request.getRequestDispatcher("/Registration.jsp").forward(request, response);
		    } else {
		        // Altri errori
		        request.setAttribute("error", "Errore imprevisto durante la registrazione.");
		        request.getRequestDispatcher("/Registration.jsp").forward(request, response);
		    }
		}

	/*	RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/RegistrationPage.jsp");
		dispatcher.forward(request, response); */
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Registration.jsp");
        dispatcher.forward(request, response); 		
        

	}
	
}
