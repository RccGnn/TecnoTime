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
    <link rel="icon" type="image/svg+xml" href="<%= request.getContextPath() %>/images/TecnoTimeIcon.svg">
    <title>Ordini effettuati - TecnoTime</title>
    <!-- Link al CSS esterno -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/ordine.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/filters.css">
</head>
<body>

	<jsp:include page="header-utente.jsp"/>
	<div class ="rieplOrdini">
    <h1>I miei ordini</h1>
    
   <div class="filter-bar-container">
        <div class="date-filter-group">
            <label for="dateLowerBound">Data (Lower Bound):</label>
            <input onchange="sortedOrders()" type="date" id="dateLowerBound" name="dateLowerBound" placeholder="YYYY-MM-DD">

            <label for="dateUpperBound">Data (Upper Bound):</label>
            <input onchange="sortedOrders()" type="date" id="dateUpperBound" name="dateUpperBound" placeholder="YYYY-MM-DD">
        </div>

        <div class="date-filter-group">
            <label for="priceLowerBound">Prezzo (MIN):</label>
            <input onchange="displaySlider(this), sortedOrders()" type="range" id="priceLowerBound" name="priceLowerBound" placeholder="€" value=0 max=9999>
			<output id="slider1"> </output>
			
            <label for="priceUpperBound">Prezzo (MAX):</label>
            <input onchange="displaySlider(this), sortedOrders()" type="range" id="priceUpperBound" name="priceUpperBound" placeholder="€" value=0 max=9999>
            <output id="slider2"> </output>
        </div>
    </div>
    
   	<div class="orders-list-page">
	   <!--
		-->
	</div>
	</div>
	<jsp:include page="footer-utente-registrato.jsp" />
    
	<script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
	<script src="<%= request.getContextPath() %>/js/deals-slider.js" defer></script>
	<script src="<%= request.getContextPath() %>/js/ajaxOrdini.js" defer></script>
</html>