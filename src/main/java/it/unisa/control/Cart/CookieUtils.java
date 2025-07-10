package it.unisa.control.Cart;

import it.unisa.model.DAO.Account.AccountDao;
import it.unisa.model.beans.AccountBean;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class CookieUtils {
	public static Cookie FirstTime(AccountBean guestAccount ) {
		Cookie cartcookie = new Cookie("GuestUserid", guestAccount.getUsername());
		cartcookie.setPath("/");
        cartcookie.setMaxAge(86400);      
        return cartcookie;
	}
	public static String Visited(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String guestId = null;

			if (cookies != null) {
			    for (Cookie c : cookies) {
			        if (c.getName().equals("GuestUserid")) {
			            guestId = c.getValue();  // Es: GUEST#12345
			            return guestId;
			        }
			    }
			}
			
		return "";
	}
	
	public static void SvuotaCookie(HttpServletRequest request,HttpServletResponse response ) {
		Cookie[] cookies = request.getCookies();	
		
		if (cookies != null) {
			for (Cookie c : cookies) {
				if(c.getName().equals("JSESSIONID")) {
					continue;
				}
				 c.setValue("");
			     c.setMaxAge(0);
			     c.setPath(c.getPath() != null ? c.getPath() : "/");
			     AccountDao dao = new AccountDao();
			     if (c.getName().equals("username"))
			     		try {
			     			dao.doDelete(c.getValue());
			     		} catch (Exception e) {
				        	e.printStackTrace();
				        }
			     response.addCookie(c);
			     System.out.println("Cancellando cookie: " + c.getName() + " con path: " + c.getPath());
				}
			}
		
		}	
	}




