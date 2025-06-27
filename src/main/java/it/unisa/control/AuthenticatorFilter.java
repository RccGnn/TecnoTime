package it.unisa.control;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.http.HttpRequest;

import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.beans.AccountBean;

/**
 * Servlet Filter implementation class AuthenticatorFilter
 */
//@WebFilter("/AuthenticatorFilter")
public class AuthenticatorFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AuthenticatorFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		if (session == null || ((Boolean)session.getAttribute("user") == false && (Boolean)session.getAttribute("admin")==false)) {
		    res.sendRedirect(req.getContextPath()+"/LoginPage.jsp");		
		    return;
		}else {
				Boolean user= (Boolean) session.getAttribute("user");
				Boolean admin= (Boolean) session.getAttribute("admin");
				String path = req.getServletPath();
				if (path.contains("/utente/")&& user==false){
					res.sendRedirect("LoginPage.jsp");		
				}else if(path.contains("/amministratore/")&& admin==false) {
					res.sendRedirect("LoginPage.jsp");	    
				}
		}	
		// pass the request along the filter chain
		if (req.getRequestURI().endsWith("login.jsp") || req.getRequestURI().endsWith("login")) {
		    chain.doFilter(request, response); // non bloccare login
		    return;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
