<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // Ottieni anno corrente
    java.util.Calendar cal = java.util.Calendar.getInstance();
    int currentYear = cal.get(java.util.Calendar.YEAR);
    
    String error  = (String) request.getAttribute("scontoErrore");
    String success = (String) request.getAttribute("scontoapplicato");
    String errorSconto = (String) request.getAttribute("scontoPresente");
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/svg+xml" href="<%= request.getContextPath() %>/images/TecnoTimeIcon.svg">
    <title>TecnoTime - aggOfferte</title>
  	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/configuratore.css">
</head>
<body>
    <jsp:include page="header-amministratore.jsp" />

<main>
    <div class="form-container">
	    <h2>AGGIUNGI OFFERTA</h2>
	    <form id="addOfferForm" method="POST" action= "<%= request.getContextPath() %>/AggiungiOfferta">
	
	      <fieldset>
	        <legend>Nome Offerta</legend>
	        <div class="input-with-icon">
	          <label for="offerName">Nome Offerta:</label>
	          <span class="input-icon-container">
	            <img src="<%= request.getContextPath() %>/images/offer.svg" alt="" class="input-icon">
	          </span>
	          <input type="text" id="offerName" name="nomesconto" placeholder="Es: Super Sconto Estivo" maxlength="100" required>
	        </div>
	      </fieldset>
	
	      <fieldset>
	        <legend>Percentuale di Sconto</legend>
	        <p>Seleziona una percentuale fissa o inserisci un valore personalizzato:</p>
	        <div class="radio-group">
	          <div>
	            <input type="radio" id="perc20" name="discountType" value="20" required>
	            <label for="perc20">20%</label>
	          </div>
	          <div>
	            <input type="radio" id="perc40" name="discountType" value="40" required>
	            <label for="perc40">40%</label>
	          </div>
	          <div>
	            <input type="radio" id="perc60" name="discountType" value="60" required>
	            <label for="perc60">60%</label>
	          </div>
	          <div class="input-with-icon" style="margin-top:1rem;">
	            <label for="customDiscount">Personalizzato:</label>
	            <input type="number" id="customDiscount" name="customDiscount" placeholder="Es: 25" min="1" max="100">
	          </div>
	        </div>
	      </fieldset>
	
	      <fieldset>
	        <legend>Descrizione</legend>
	        <div class="input-with-icon">
	          <label for="description">Descrizione Offerta:</label>
	          <span class="input-icon-container">
	            <img src="<%= request.getContextPath() %>/images/desc.svg" alt="" class="input-icon">
	          </span>
	          <textarea id="description" name="descrizione" rows="5" cols="50" placeholder="Dettagli dell'offerta..." maxlength="500"></textarea>
	        </div>
	      </fieldset>
	      
	      <!-- INIZIO sezione AJAX di ricerca prodotto + checkbox abbonati -->
			<fieldset>
			  <legend>Prodotto in Offerta</legend>
			  <div class="input-with-icon">
			    <label for="searchProduct">Cerca prodotto:</label>
			    <input 
			      type="text" 
			      id="searchProduct" 
			      name="productQuery" 
			      placeholder="Digita nome prodotto..." 
			      autocomplete="off"
			    >
			  </div>
			  <div id="productResults" class="search-results">
			    <!-- ajaxArticoli.js carica i prodotti man mano che si digita -->
			  </div>
			  <div class="checkbox-container">
			    <input 
			      type="checkbox" 
			      id="exclusiveOffer" 
			      name="exclusiveOffer" 
			      value="true"
			    >
			    <label for="exclusiveOffer">Offerta esclusiva riservata agli abbonati</label>
			  </div>
			</fieldset>

	      
	      
	      <% if(error!=null) {%>
	      		<span> <%=error %></span>
	      	<% } else if( success != null) {%>
				<span> <%= success %></span>
			<%} else if (errorSconto != null) {%>
				<span> <%= errorSconto %></span>
			<%} %>
			
	      <button type="submit">Aggiungi Offerta</button>
	      
	      <c:if test="${not empty successMessage}">
	        <div class="success-message">${successMessage}</div>
	      </c:if>
	      <c:if test="${not empty errorMessage}">
	        <div class="error-message">${errorMessage}</div>
	      </c:if>
	      
		  <a href="modifiche-offerte.jsp">
		  <button type="button">Torna a Modifiche Offerte</button>
		  </a>
	    </form>
	  </div>
    </main>
	<jsp:include page="footer-amministratore.jsp" />
    <script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
</body>
</html>