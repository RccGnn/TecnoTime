<%@page import="it.unisa.model.DAO.Order.OrdineCompletoDao"%>
<%@page import="it.unisa.model.beans.OrdineCompletoBean"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String username =request.getParameter("username");
%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/svg+xml"
	href="<%=request.getContextPath()%>/images/TecnoTimeIcon.svg">
<title>GestOrdini Utenti - TecnoTime</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/ordine.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/filters.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/admin-ordini.css">
</head>
<jsp:include page="header-amministratore.jsp" />

<main>
	<div class="rieplOrdini">
		<h1>Gestione Ordini Utente</h1>

		<div class="admin-search-form">
			<div class="date-filter-group">
				<label for="dateLowerBound">Data (Lower Bound):</label> 
				<input
					type="date" id="dateLowerBound" name="dateLowerBound"
					placeholder="YYYY-MM-DD" value="1970-1-1"> <label
					for="dateUpperBound">Data (Upper Bound):</label> <input type="date"
					id="dateUpperBound" name="dateUpperBound" placeholder="YYYY-MM-DD"
					value="1970-1-1">
			</div>

			<div class="date-filter-group">
				<label for="priceLowerBound">Prezzo (MIN):</label> 
				<input
					onchange="displaySlider(this)" type="range" id="priceLowerBound"
					name="priceLowerBound" placeholder="€" value=0 max=9999>
				<output id="slider1"> </output>

				<label for="priceUpperBound">Prezzo (MAX):</label> 
				<input
					onchange="displaySlider(this)" type="range" id="priceUpperBound"
					name="priceUpperBound" placeholder="€" value=0 max=9999>
				<output id="slider2"> </output>
			</div>
			<label for="username">Cerca utente:</label> 
			<input type="text"
				id="username" name="username" placeholder="Inserisci username">
			<button type="submit" onclick="sortedOrders()">Cerca</button>
		</div>

		<div class="orders-list-page">
			<!--  -->
		</div>
	</div>

</main>
<jsp:include page="footer-amministratore.jsp" />
<script src="<%=request.getContextPath()%>/js/navbar.js" defer></script>
<script src="<%=request.getContextPath()%>/js/ajaxOrdini.js" defer></script>
</body>
</html>
