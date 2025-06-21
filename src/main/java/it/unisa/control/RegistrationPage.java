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

import it.unisa.model.beans.AccountBean;

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
		
	String error="";
		
		String firstName= request.getParameter("firstName");
		String lastName= request.getParameter("lastName");
		String birthDateStr= request.getParameter("birthDate");
		String address= request.getParameter("Address");
		String postalCodeStr= request.getParameter("postalCode");		
		String email= request.getParameter("E-mail");
		String password=request.getParameter("password");
		String telNumb=request.getParameter("telNumb");
		String gender= request.getParameter("gender");
		String nation= request.getParameter("nation");
		String username= request.getParameter("username");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String aptnumber=request.getParameter("apartament number");
		String rule = request.getParameter("rule");
		char genderChr;
		
		String message="";
		
		// invece di fare tutti sti controlli il model crea l'oggetto user con la classe DAO
		if (firstName == null || firstName.trim().equals("")) {
			error += "Insert name<br>";
		} else {
			firstName = firstName.trim();
			firstName= DecoderHtml.encodeHtml(firstName);
			request.setAttribute("name", firstName);	
			message+="first name is"+firstName+"<br>";
		}

		if (lastName == null || lastName.trim().equals("")) {
			error += "Insert lastName<br>";
		} else {
			lastName = lastName.trim();
			lastName= DecoderHtml.encodeHtml(lastName);
			message+=lastName;
			request.setAttribute("message", message);
			request.setAttribute("lastName", lastName);
		}
		
		LocalDate birthDate=null;
		if (birthDateStr == null || birthDateStr.trim().equals("")) {
			error += "Insert birthdate<br>";
		} else {
			birthDateStr = birthDateStr.trim();
			birthDateStr= DecoderHtml.encodeHtml(birthDateStr);
			try {
				 birthDate=LocalDate.parse(birthDateStr);
			}catch (DateTimeParseException e){
				  error += "Invalid date format<br>";
			}
			request.setAttribute("birthDate", birthDate);
		}
				
		if (address == null || address.trim().equals("")) {
			error += "Insert address<br>";
		} else {
			address = address.trim();
			address= DecoderHtml.encodeHtml(address);
			request.setAttribute("address", address);
		}
		
		
		if (postalCodeStr == null || postalCodeStr.trim().equals("")) {
			error += "Invalid postalCode<br>";
		} else {
			postalCodeStr = postalCodeStr.trim();
			if(postalCodeStr.matches("\\d{5}")) {
				request.setAttribute("postalCode", postalCodeStr);
			}else {
				error+="Invalid postalCode<br>";
			}
		}
		
		if(password==null || email.trim().equals("")) {
			error += "must insert a password";
		}else {
			password= password.trim();
			password=DecoderHtml.encodeHtml(password);         
		    if(pwdValidator.isValid(password)==false) {         	
		    request.setAttribute("pwderror","password non valida" );         
		    }else {    
		    	PasswordUtils.hashPassword(password);       //la stringa di ritorno deve essere memorizzata nel db con l'oggetto user  	         }
			}
		
		
		if (email == null || email.trim().equals("")) {
			error += "Insert email<br>";
		} else {
			email = email.trim();
			email= DecoderHtml.encodeHtml(email);
			request.setAttribute("email", email);
		}
		
		if (gender == null || gender.trim().equals("")) {
			error += "Insert gender<br>";
		} else {
			gender = gender.trim();
			gender= DecoderHtml.encodeHtml(gender);
		    genderChr = gender.charAt(0);  //prende solo il primo carattere della stringa e lo salva in una variabile char
			request.setAttribute("gender", genderChr );
		}
		
		int tel=0;
		if (telNumb == null || telNumb.trim().equals("")||telNumb.matches("//d+")){
		    error += "Insert a correct telephone number<br>";
		} else {
		    telNumb = telNumb.trim();
		    telNumb= DecoderHtml.encodeHtml(telNumb);
		    request.setAttribute("telNumb", telNumb);
		}
		
		
		if (nation == null || nation.trim().equals("")) {
			error += "Invalid nation selected <br>";
		} else {
			nation = nation.trim();
			nation= DecoderHtml.encodeHtml(nation);
			request.setAttribute("nation",nation);
		}
		
		if (username == null || username.trim().equals("")) {
			error += "Invalid username selected <br>";
		} else {
			username = username.trim();
			username= DecoderHtml.encodeHtml(nation);
			request.setAttribute("username",username);
		}
		
		if (province == null || province.trim().equals("")) {
			error += "Invalid province selected <br>";
		} else {
			province = province.trim();
			province= DecoderHtml.encodeHtml(nation);
			request.setAttribute("province",province);
		}
		
		if (city == null || city.trim().equals("")) {
			error += "Invalid city selected <br>";
		} else {
			nation = nation.trim();
			nation= DecoderHtml.encodeHtml(nation);
			request.setAttribute("city",city);
		}
		
		if (aptnumber == null || aptnumber.trim().equals("")) {
			error += "Invalid nation selected <br>";
		} else {
			aptnumber = aptnumber.trim();
			aptnumber= DecoderHtml.encodeHtml(nation);
			request.setAttribute("aptnumber",aptnumber);
		}
		
		if (rule == null || rule.trim().equals("")) {
			error += "Invalid nation selected <br>";
		} else {
			rule = nation.trim();
			rule= DecoderHtml.encodeHtml(nation);
			request.setAttribute("rule",rule);
		}
		
			
		if (!error.equals("")) {
			request.setAttribute("error", error);
		}
		
		AccountBean account = new AccountBean(password, username, firstName, lastName, genderChr, email, nation, telNumb, province, city, address, aptnumber, postalCodeStr, birthDate, null);
		
	/*	RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/RegistrationPage.jsp");
		dispatcher.forward(request, response); */
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Registration.jsp");
        dispatcher.forward(request, response); 
	
	}
}
