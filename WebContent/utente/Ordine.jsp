<%@page import="it.unisa.model.DAO.DaoUtils"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.text.DecimalFormat"	%>
<%@ page import="java.util.Collections"	%>
<%@ page import="it.unisa.model.beans.ArticoloCompletoBean" %>
<%@ page import="it.unisa.model.beans.CarrelloRiempitoBean" %>
<%@ page import="java.util.ArrayList" %>

<%
	java.util.Calendar cal = java.util.Calendar.getInstance();
	int currentYear = cal.get(java.util.Calendar.YEAR);
	int currentMonth = cal.get(java.util.Calendar.MONTH) + 1; // gennaio=0

	CarrelloRiempitoBean carrello = (CarrelloRiempitoBean) request.getAttribute("carrello"); 
	String cID = carrello.getCarrello_Id();
	String username = carrello.getAccount_username(); 
%>
	
<!DOCTYPE html>
<html lang="it">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" type="image/svg+xml" href="<%= request.getContextPath() %>/images/TecnoTimeIcon.svg">
	<title>TecnoTime: Ordine</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/ordine.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/login_registrazione.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/cart.css">
	
</head>

<body>
	<jsp:include page="header-utente.jsp"/>

	<%	ArrayList<ArticoloCompletoBean> listaCarrello = DaoUtils.discountedItemList(carrello.getListaArticoli());
      	double totale = 0;
      	DecimalFormat df = new DecimalFormat("0.00 €");
     %>
	<main>
		<div class="form-container">
		
		<h2>Resoconto ordine</h2>
	        
	          	<div class="order-summary-section">
	
					<%	ArrayList<ArticoloCompletoBean> occorrenze = new ArrayList<>();
				  		for(ArticoloCompletoBean articolo : listaCarrello) {
	              			if (occorrenze.contains(articolo))  { // Se un articolo è nella lista occorrenze allora è già stato mostrato
	               				continue;
	            			}
	            			occorrenze.add(articolo);
	            			int count = Collections.frequency(listaCarrello, articolo);
	            			String aID = articolo.getCodiceIdentificativo();  
					%>
	              			<div class="cart-item-card" id="cart-item<%=aID%>">
			              	<%	String url = "";
			              	 	if (articolo.getImmagini() == null || articolo.getImmagini().isEmpty())
			              			url = "images/alt-prodotti.png";
			              	 	else
			              			url = articolo.getImmagini().get(0).getUrl();
			              	%>
								<img src="<%= url %>" alt="<%= articolo.getNome() %>" class="cart-item-img"/>
		                		
		                		<div class="cart-item-details">
		                  			<div class="cart-item-name"><%= articolo.getNome() %></div>
		                		</div>
		                		
		                		<div class="cart-item-price">
				                <%	double prezzo = 0;
				                	int quantita = Collections.frequency(listaCarrello, articolo);
				                	if (articolo.getPdDigitale() != null) {
				                		prezzo = articolo.getPdDigitale().getPrezzo();
				                	} else if (articolo.getPdFisico() != null) {
				                		prezzo = articolo.getPdFisico().getPrezzo();
				                	} else {
				                  		prezzo = articolo.getServizio().getPrezzo();
				                	}
				                  	totale += prezzo * count;
				                %>
				                
									Prezzo Unitario: <%= df.format(prezzo) %> <br>
				                	Qtà: <%= quantita %>
		                		</div>
	              			</div>
	              		<% } %>
	          </div>
	          	 
	          <div class="order-summary-section">
	          
	          
	          </div>
      		<h2>Inserire dati per il pagamento</h2>

      		<form id="checkoutForm" method="GET" action="CheckoutServlet">
        		<!-- Numero carta (16 cifre) -->
        		<label for="ncard">Numero carta:</label>
        		<input type="text" id="ncard" name="ncard"
               		placeholder="____ ____ ____ ____"
               		maxlength="19" required>

		        <!-- Data di scadenza -->
		        <label for="expiringDate">Data scadenza (MM/YYYY):</label>
		        <input type="month" id="expiringDate" name="expiringDate"
		               min="${currentYear}-${currentMonth < 10 ? '0'+currentMonth : currentMonth}"
		               required>

		        <!-- CVC (3 cifre) -->
		        <label for="codecard">Codice di sicurezza (CVC):</label>
		        <input type="text" id="codecard" name="codecard"
		               placeholder="___"
		               maxlength="3" required>

      				<div class="cart-header"> Totale: <%= df.format(totale) %>  </div>
      			          			 
				<button type="submit" id="payBtn">PAGAAAH</button>
      		</form>
      		
    	</div>
	</main>

	<jsp:include page="footer-utente-registrato.jsp"/>
	<script src="<%= request.getContextPath() %>/js/ordine.js" defer></script>
	<script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
</body>
</html>
