<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.Collections"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="it.unisa.model.beans.ArticoloCompletoBean" %>
<%@ page import="it.unisa.model.beans.CarrelloRiempitoBean" %>
<%@ page import="java.util.ArrayList" %>

<% 	CarrelloRiempitoBean carrello = (CarrelloRiempitoBean) request.getAttribute("carrello"); 
	String cID = carrello.getCarrello_Id();
	String username = carrello.getAccount_username(); %>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="icon" type="image/svg+xml" href="images/TecnoTimeIcon.svg">
  <title>Carrello - TecnoTime</title>
  <link rel="stylesheet" href="styles.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
  <jsp:include page="header.jsp"/>

  <div class="cart-page-container">
	<form method="GET" action="CheckoutServlet">
		<input type="hidden" name="cartId" value="<%= cID %>"/>
		<input type="hidden" name="username" value="<%= username %>"/>

      <% ArrayList<ArticoloCompletoBean> listaCarrello = carrello.getListaArticoli();
      	 double totale = 0;
      	 DecimalFormat df = new DecimalFormat("0.00 €");
      	 if(carrello == null || listaCarrello == null || listaCarrello.isEmpty()) { %>
	        <div class="empty-cart-container">
	            <i class="fas fa-shopping-cart empty-cart-icon"></i>
	            <h1>Il tuo carrello è vuoto</h1>
	            <p>Aggiungi prodotti per vederli qui.</p>
	            <a href="ProductServlet" class="btn-primary">Vai ai Prodotti</a>
	        </div>    	  
      <% } else { %>
      
	        <div class="cart-layout">
	        
	          <div class="cart-items-section">
	            <div class="cart-header">
	              <h1>IL TUO CARRELLO ( <%=carrello.getListaArticoli().size()%> )</h1>
	              <div>
	                  <button type="button" id="remove-all-btn" class="remove-all-btn" onclick="removeAll()">RIMUOVI TUTTO</button>
	              </div>
	            </div>
	
				<%ArrayList<ArticoloCompletoBean> occorrenze = new ArrayList<>(); 
				  for(ArticoloCompletoBean articolo : listaCarrello) {
	              	if (occorrenze.contains(articolo))  { // Se un articolo è nella lista occorrenze allora è già stato mostrato
	               		continue;
	            	}
	            	occorrenze.add(articolo);
	            	int count = Collections.frequency(listaCarrello, articolo);
	            	String aID = articolo.getCodiceIdentificativo();  
	            %>
	              <div class="cart-item-card">
	              <% String url = "";
	              	 if (articolo.getImmagini() == null || articolo.getImmagini().isEmpty())
	              		 url = "images/alt-prodotti.png";
	              	 else
	              		 url = articolo.getImmagini().get(0).getUrl();
	              %>
	                <img src="<%= url %>" alt="<%= articolo.getNome() %>" class="cart-item-img"/>
	                <div class="cart-item-details">
	                  <span class="cart-item-name"><%= articolo.getNome() %></span>
	                  
	                  <div class="quantity-form">
<<<<<<< HEAD
	  
	                    <input type="hidden" name="productId[]" value="<%= aID %>"/>                
	                    <input type="hidden" name="quantity[]" value="<%= count %>"/>
=======
	                    <input type="hidden" name="productId<%=i%>" value="<%= aID %>"/>
	                    <input type="hidden" name="quantity<%=i%>" value="<%= count %>"/>
>>>>>>> 8e0e5546e6d8227976a2311ca5eebe2d2c2fcc70
	                    <label for="quantity-<%= aID %>"> Qtà: </label>
	                    <div class="quantity-control">
    					  <button type="button" class="quantity-select" onclick="decrease(this)" id="decrement<%= aID %> ">-</button>
						  <output id="current-quantity-<%= aID %>" class="quantity-display"> 
						    <%= count %>
						  </output>
					      <button type="button" class="quantity-select" onclick="increase(this)" id="increment<%= aID %>">+</button>
						</div>
	                  </div>
	                  
	                  <div class="remove-form">
	                    <input type="hidden" name="action" value="remove"/>
	                    <input type="hidden" name="productId" value="<%= aID %>"/>
	                    <button type="button" class="remove-item-btn">RIMUOVI</button>
	                  </div>
	                </div>
	                <div class="cart-item-price">
	                  
	                <%	double prezzo = 0;
	                  	if (articolo.getPdDigitale() != null)
	                  		prezzo = articolo.getPdDigitale().getPrezzo();
	                  	else if (articolo.getPdFisico() != null)
	                  		prezzo = articolo.getPdFisico().getPrezzo();
	                  	else 
	                  		prezzo = articolo.getServizio().getPrezzo();
	                  	totale += prezzo * count;
	                %>
	                  <span class="current-price"> <%= df.format(prezzo) %> </span>
	               	    
	                </div>
	              </div>
	            <% } %>
	            
	          </div>
	
	          <div class="order-summary-section">
	            <h2>RIEPILOGO ORDINE</h2>
	            <div class="summary-line">
	              <span>Subtotale</span> 
	              <span> <%= df.format(totale) %> </span>
	            </div>
	            <div class="summary-line">
	              <span>Sconto Indicativo</span>
	              <span class="discount-color">-€0,00</span>
	            </div>
	            <div class="summary-line">
	              <span>Spedizione</span>
	              <span>calcolata al checkout</span>
	            </div>
	            <div class="summary-line">
	              <span>Imposte</span>
	              <span>calcolata al checkout</span>
	            </div>
	            <hr class="summary-divider"/>
	            <div class="summary-line total">
	              <span>Totale</span>
	              <span> <%= df.format(totale) %></span>
	            </div>
	            <div>
	             <a href="utente/Ordine.jsp" class="checkout-btn">CHECKOUT</a>
	            </div>
	            <div class="account-links">
	              <span>Hai già un account? <a href="LoginPage.jsp">Accedi</a></span>
	              <span>Non hai ancora un account? <a href="Registration.jsp">Unisciti a noi</a></span>
	            </div>
	            <div class="extra-info">
	              <i class="fas fa-shield-alt"></i>
	              <span>RESI SENZA RISCHI ENTRO 60 GIORNI</span>
	            </div>
	            <div class="payment-methods">
	              <i class="fab fa-cc-amex"></i>
	              <i class="fab fa-cc-visa"></i>
	              <i class="fab fa-cc-discover"></i>
	              <i class="fab fa-cc-paypal"></i>
	              <i class="fab fa-google-pay"></i>
	              <i class="fab fa-apple-pay"></i>
	            </div>
	          </div>
	          
	        </div>

		<% 	 
		   } 
		%>
		</form>
  </div>

  <jsp:include page="footer.jsp"/>
  <script src="js/cart.js" defer></script>
  <script src="js/navbar.js" defer></script>
</body>
</html>
