package it.unisa.control.Authentication;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter implementation class AuthenticatorFilter
 */
public class AuthenticatorFilter extends HttpFilter implements Filter {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		
		if (session == null || (session.getAttribute("user") == null && session.getAttribute("admin")==null)) {  // se non ce nessun token d'accesso attivo
		    res.sendRedirect(req.getContextPath()+"/LoginPage.jsp");
		    // messagio d'errore
		    return;
		} else {
				Boolean user= (Boolean) session.getAttribute("user");
				Boolean admin= (Boolean) session.getAttribute("admin");
				System.out.println(admin);
				System.out.println(user);
				String path = req.getServletPath();
				if (path.contains("/utente/")&& user !=null && user==false){
					res.sendRedirect("/LoginPage.jsp");		
				}else if(path.contains("/amministratore/")&& admin !=null && admin==false) {
					res.sendRedirect("/LoginPage.jsp");
				}
		}	
		// pass the request along the filter chain
		//if (req.getRequestURI().endsWith("login.jsp") || req.getRequestURI().endsWith("login")) {
		    chain.doFilter(request, response); // non bloccare login
		    //return;
		//}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
