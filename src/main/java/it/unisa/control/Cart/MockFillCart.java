package it.unisa.control.Cart;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import it.unisa.model.DAO.DaoUtils;
import it.unisa.model.DAO.Cart.CarrelloRiempitoDao;
import it.unisa.model.beans.CarrelloRiempitoBean;

/**
 * Servlet implementation class MockFillCart
 */
@WebServlet("/MockFillCart")
public class MockFillCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MockFillCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		CarrelloRiempitoDao dao = new CarrelloRiempitoDao();
		CarrelloRiempitoBean bean = new CarrelloRiempitoBean();
		ArrayList<String> key = new ArrayList<>();
		
		String[] tupla = CookieUtils.getUsernameCartIdfromCookies(request); // Ottieni username e carrello_id
		
		key.add(tupla[0]);
		key.add(tupla[1]);
		try {
			bean = dao.doRetrieveByKey(key);				
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//System.out.println("Carrello: "+bean.toString()+"\nSize: "+bean.getListaArticoli().size());
        bean.setListaArticoli(DaoUtils.dropboxImagesDecoderUrl(bean.getListaArticoli()));
        
		request.setAttribute("carrello", bean);	
		RequestDispatcher rd = request.getRequestDispatcher("/carrello.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
