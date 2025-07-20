<%@ page import="java.util.ArrayList"%>
<%@ page import="it.unisa.model.beans.ArticoloCompletoBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/styles/login_registrazione.css">
<title>TecnoTime Admin - E-commerce</title>
</head>
<body>
	<jsp:include page="header-amministratore.jsp" />

	<main class="form-container">
		<form action="AdminEliminaProdotto" method="post">
			<h2>Rimuovi un prodotto</h2>

			<c:if test="${not empty errorRetrive}">
				<div class="error-message">${errorRetrive}</div>
			</c:if>
			<c:if test="${not empty eliminazioneSuccess}">
				<div class="success-message">${eliminazioneSuccess}</div>
			</c:if>
			<c:if test="${not empty eliminazioneError}">
				<div class="error-message">${eliminazioneError}</div>
			</c:if>

			<div class="form-group">
				<input type="text" list="articoli" name="codiceIdentificativo"
					class="search-bar" placeholder="Inserisci nome prodotto..." />
				<datalist id="articoli">
					<c:forEach items="${listaArticoli}" var="articolo">
						<option value="${articolo.codiceIdentificativo}">${articolo.nome}</option>
					</c:forEach>
				</datalist>
			</div>

			<div class="button-row">
				<button type="submit" class="remove-button">Rimuovi il
					Prodotto selezionato</button>
				<button type="button" class="remove-button"
					onclick="location.href='amministratore/modifiche-offerte.jsp'">
					Torna a Modifiche Offerte</button>
			</div>
		</form>
	</main>

	<jsp:include page="footer-amministratore.jsp" />
	<script src="${pageContext.request.contextPath}/js/navbar.js" defer></script>
</body>
</html>
