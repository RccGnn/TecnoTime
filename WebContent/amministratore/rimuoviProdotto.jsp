<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  %>
<!DOCTYPE html>
<html>

<%
    // Ottieni anno corrente
    java.util.Calendar cal = java.util.Calendar.getInstance();
    int currentYear = cal.get(java.util.Calendar.YEAR);
    
    String retrive = (String) request.getAttribute("errorRetrive");
    String success = (String) request.getAttribute("eliminazioneSuccess");
    String error = (String) request.getAttribute("eliminazioneError");    
    ArrayList<ArticoloCompletoBean> lista = (ArrayList<PromozioneBean>) request.getAttribute("lista");
  %>

<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/login_registrazione.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/configuratore.css">
<title>TecnoTime Admin - E-commerce</title>
</head>
<body>
	<jsp:include page="header-amministratore.jsp" />



	<form class="form-container" action="AdmimEliminaProdotto"
		method="post">
		<h2>Rimuovi un prodotto</h2>

		<div>
			<div class="form-group">
				<label for="Cerca un prodotto per eliminarlo "></label>
			</div>
			<input type="text"
				class="search-bar" id="search-bar" name="nomeProdotto"
				autocomplete="off" placeholder="Cerca prodotto..."
				oninput="search()">
			<div id="search-results" class="search-results-dropdown"></div>
		</div>

		<button type="submit" class="remove-button">Rimuovi il
			Prodotto selezionato</button>

		<a href="amministratore/modifiche-offerte.jsp">
			<button type="button">Torna a Modifiche Offerte</button>
		</a>
	</form>

	<jsp:include page="footer-amministratore.jsp" />
	<script src="<%=request.getContextPath()%>/js/navbar.js" defer></script>
</body>
</html>