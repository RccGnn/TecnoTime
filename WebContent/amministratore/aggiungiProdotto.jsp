
<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/svg+xml"
	href="<%=request.getContextPath()%>/images/TecnoTimeIcon.svg">
<title>Aggiungi Prodotto – TecnoTime</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/articolo-single.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/login_registrazione.css">
</head>
<body>


	<% 
	String success = (String) request.getAttribute("success");
%>
	<jsp:include page="header-amministratore.jsp" />

	<main class="form-container">
		
		<form action="<%= request.getContextPath() %>/AdminAggiungiProdotto"
			method="POST" enctype="multipart/form-data"
			class="productsingle-details">
			
			<h1 class="productsingle-title">Aggiungi Nuovo Prodotto</h1>

			<label for="tipologia">Tipologia di prodotto: </label> 
			<select
				onchange="modifyForm()" name="tipologia" id="tipologia"
				 required>
				<option value="" disabled selected>Seleziona una tipologia.</option>
				<option value="processore">Processore</option>
				<option value="scheda_madre">Scheda madre</option>
				<option value="scheda_video">Scheda video</option>
				<option value="RAM">RAM</option>
				<option value="_case">Case</option>
				<option value="alimentatori">Alimentatori</option>
				<option value="servizio">Servizio</option>
				<option value="licenza">Licenza (Prodotto digitale)</option>
				<option value="altro">Accessori o altro</option>
			</select>
			
			<label for="nome">Nome (completo) del prodotto:</label> 
			<input
				type="text" name="nome" id="nome" required> 
				
			<label for="prezzo">Prezzo (€):</label> 
			<input type="text" name="prezzo" id="prezzo" placeholder="99.99" inputmode="decimal"
				pattern="^\d+([.,]\d{1,2})?$" required> 
				
			<label for="descrizione">Descrizione:</label>
			<textarea name="descrizione" id="descrizione" rows="4" placeholder="Inizia a scrivere..." required></textarea>

			<label for="immagini">Immagini (url da dropbox):</label> 
			<input placeholder="Non obbligatorio..." type="text" name="immagini" id="immagini">


			<!-- CATEGORIE SPECIFICHE DELLA TIPOLOGIA DI PRODOTTO -->
			<h1 class="productsingle-title">Aggiungi specifiche: </h1>

			<div id="optionElements"></div>

			<button type="submit" id="productsingle-add-btn">Aggiungi
				Prodotto</button>

			<%if(success!=null){ %>
			<div><%= success %></div>
			<%} %>

		</form>
	</main>

	<jsp:include page="footer-amministratore.jsp" />

	<script src="<%=request.getContextPath()%>/js/navbar.js" defer></script>
	<script
		src="<%=request.getContextPath()%>/js/ajaxAdminAggiungiProdotti.js"
		defer></script>
</body>
</html>
