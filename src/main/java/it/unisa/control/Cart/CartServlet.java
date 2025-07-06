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
		
        response.setContentType("application/json"); // Respond with JSON
        response.setCharacterEncoding("UTF-8");

        // 1. Set character encoding for the request body
        request.setCharacterEncoding("UTF-8");

        // 2. Read the entire JSON payload from the request body
        String jsonBuffer = "";
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuffer += line;
                System.out.println(1);
            }
        }
        String jsonPayload = jsonBuffer.toString();

        System.out.println("Received JSON payload: " + jsonPayload); // For debugging
        Gson gson = new Gson();
        ArticoloCompletoBean artCompleto = gson.fromJson(jsonPayload, ArticoloCompletoBean.class);
        System.out.println(artCompleto.toString());
        

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
