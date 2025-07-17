<%@page import="it.unisa.model.DAO.Order.OrdineCompletoDao"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="it.unisa.model.beans.ElementoOrdineBean"%>
<%@ page import="it.unisa.model.beans.OrdineCompletoBean"%>
<%@ page import="java.util.ArrayList"%>

<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // Ottieni anno corrente
    java.util.Calendar cal = java.util.Calendar.getInstance();
    int currentYear = cal.get(java.util.Calendar.YEAR);
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/svg+xml" href="images/TecnoTimeIcon.svg">
    <title>Ordini effettuati - TecnoTime</title>
    <!-- Link al CSS esterno -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
</head>
<body>
<%		// TEMPORANEO
	OrdineCompletoDao ordDao = new OrdineCompletoDao();
	ArrayList<OrdineCompletoBean> listaOrdini = ordDao.doRetrieveAllByUsername("username"); //(ArrayList<OrdineCompletoBean>) request.getAttribute("listaOrdini");
	DecimalFormat df = new DecimalFormat("0.00 €");
%>

<%
	String username = (String) request.getAttribute("username");
%>
   <%if ((Boolean)session.getAttribute("user") !=null &&(Boolean)session.getAttribute("user")){
	  %><jsp:include page="utente/header-utente.jsp"/>
<% }else if ((Boolean)session.getAttribute("admin") !=null &&(Boolean)session.getAttribute("admin")) {
      %><jsp:include page="amministratore/header-amministratore.jsp"/>
<% }else{ 
	  %> <jsp:include page="header.jsp"/><%
   }%>
   
   
   <div class="filter-bar-container">
   		<input type="hidden" id="username" value ="<%= username %>">
        <div class="date-filter-group">
            <label for="dateLowerBound">Data (Lower Bound):</label>
            <input type="date" id="dateLowerBound" name="dateLowerBound" placeholder="YYYY-MM-DD">

            <label for="dateUpperBound">Data (Upper Bound):</label>
            <input type="date" id="dateUpperBound" name="dateUpperBound" placeholder="YYYY-MM-DD">
        </div>

        <div class="price-filter-group">
            <label for="priceLowerBound">Prezzo (MIN):</label>
            <input type="range" id="priceLowerBound" name="priceLowerBound" placeholder="€">

            <label for="priceUpperBound">Prezzo (MAX):</label>
            <input type="range" id="priceUpperBound" name="priceUpperBound" placeholder="€">
        </div>
    </div>
    
   	<div class="orders-list-page">
		<h1>I miei ordini</h1>
	   <% if (listaOrdini == null || listaOrdini.isEmpty()) { %>
	   		<p class="no-orders-message">Non hai ancora effettuato ordini.</p>
	   <% } else { %>
		   <% for(OrdineCompletoBean ordine : listaOrdini) {
		   		ArrayList<ElementoOrdineBean> listaElementi = ordine.getElementiOrdine(); %>
				<div class="orders-summary-section">
					<div class="order-header">
						<div class="order-details-group">
							<div class="order-detail-item">
								<span class="detail-label">Data ordine:</span>
								<span class="detail-value"><%= new java.text.SimpleDateFormat("dd MMMM yyyy").format(ordine.getDataTransazione()) %></span>
							</div>
							<div class="order-detail-item">
								<span class="detail-label">Totale:</span>
								<span class="detail-value"><%= df.format(ordine.getTotale()) %></span>
							</div>
						</div>
						<div class="order-details-group">
							<div class="order-detail-item">
								<span class="detail-label">ID Ordine:</span>
								<span class="detail-value"><%= ordine.getNumeroTransazione() %></span>
							</div>
						</div>
					</div>
					
					<div class="order-items-list">
						<%	for(ElementoOrdineBean elemento : listaElementi) {		%>
					        <div class="order-item-card">
							<%	String url = "";
								if (elemento.getUrlImmagineArticolo() == null || elemento.getUrlImmagineArticolo().isEmpty())
							    	url = request.getContextPath() + "/images/alt-prodotti.png";
								else
									url = request.getContextPath() + "/" + elemento.getUrlImmagineArticolo(); // Ensure correct path for images
							%>
								<img src="<%= url %>" alt="<%= elemento.getNomeArticolo() %>" class="order-item-img"/>
						                		
								<div class="order-item-details">
									<div class="order-item-name"><%= elemento.getNomeArticolo() %></div>
									<div class="order-item-qty">Qtà: <%= elemento.getQuantitaArticolo() %></div>
									<div class="order-item-price-per-unit">Prezzo Unitario: <%= df.format(elemento.getPrezzoUnitario()) %></div>
								</div>
								<div class="order-item-total-price">
									<%= df.format(elemento.getPrezzoUnitario() * elemento.getQuantitaArticolo()) %>
								</div>
							</div>
							<% } %>
					</div>
				</div>
			<%} %>
		<% } %>
	</div>
	<jsp:include page="footer.jsp" />
    
	<script src="js/navbar.js" defer></script>
	<script src="js/deals-slider.js" defer></script>
</html>