package it.unisa.control.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import it.unisa.model.DAO.Account.AccountDao;
import it.unisa.model.beans.AccountBean;
import it.unisa.model.beans.ArticoloCompletoBean;
import it.unisa.model.beans.CarrelloRiempitoBean;
import it.unisa.model.beans.Ruoli;

import com.google.gson.Gson;

import it.unisa.model.beans.ArticoloCompletoBean;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        ArticoloCompletoBean articolo = gson.fromJson(reader, ArticoloCompletoBean.class);
        CarrelloRiempitoBean cart = new CarrelloRiempitoBean();
        ArrayList<ArticoloCompletoBean> listaProdotti= new ArrayList<>();
        listaProdotti.add(articolo);
        cart.setListaArticoli(listaProdotti);

        HttpSession session = request.getSession(); //session anonima 
        AccountBean guest= new AccountBean("GUEST");
        System.out.println(guest.toString());
        AccountDao dao = new AccountDao();
        try {
            dao.doSave(guest);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        Cookie cartcookie = new Cookie("cartcookie", "cart");
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
