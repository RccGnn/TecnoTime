package it.unisa.control.Cart;

import it.unisa.model.beans.AccountBean;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

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
}
