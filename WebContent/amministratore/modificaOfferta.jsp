<%@	page import="it.unisa.model.beans.PromozioneBean"%>
<%@	page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
// Ottieni anno corrente
java.util.Calendar cal = java.util.Calendar.getInstance();
int currentYear = cal.get(java.util.Calendar.YEAR);

String retrive = (String) request.getAttribute("errorRetrive");
String success = (String) request.getAttribute("eliminazioneSuccess");
String error = (String) request.getAttribute("eliminazioneError");
ArrayList<PromozioneBean> lista = (ArrayList<PromozioneBean>) request.getAttribute("lista");
%>

<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/svg+xml"
	href="<%=request.getContextPath()%>/images/TecnoTimeIcon.svg">
<title>TecnoTime - Modifica Offerte</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/configuratore.css">
</head>
<body>
	<jsp:include page="header-amministratore.jsp" />

	<main>
		<div class="form-container">
			<h2>RIMUOVI OFFERTA</h2>
			<form id="removeOfferForm" method="POST"
				action="<%=request.getContextPath()%>/AdminEliminaOfferta">

				<%
				if (retrive != null) {
				%>
				<div>
					<%=retrive%>
				</div>
				<%
				}
				%>

				<fieldset>
					<legend>Seleziona Offerta</legend>
					<div class="input-with-icon">
						<label for="offerSelect">Offerta da modificare:</label>
						<select
							id="offerSelect" name="nomesconto" required onchange="displayOfferFields()">
							<option value="">-- Seleziona --</option>
							<c:forEach var="off" items="${lista}">
								<option value="${off.nomesconto}">${off.nomesconto}</option>
							</c:forEach>
						</select>
					</div>
				</fieldset>
				
				<fieldset>
					<legend>Seleziona campi da modificare</legend>
					<div class="input-with-icon">
						<label for="offerProduct">Nome sconto: </label>
						<input type="text" name="offerProduct" id="nome" required>						
						
						
						<label for="offerDescription">Descrizione dell'offerta:</label>
						<textarea name="offerDescription" id="descrizione" rows="4" required>
						</textarea>
						
						<label for="offerPercent">Percentuale di sconto applicata:</label>
						<input type="number" step="0.01" min="1" name="offerProduct" id="sconto" required>						
					</div>
				</fieldset>

				<%
				if (success != null) {
				%>
				<div>
					<%=success%></div>
				<%
				} else if (error != null) {
				%>
				<div>
					<%=error%></div>
				<%
				}
				%>

				<button type="submit">Rimuovi Offerta</button>

				<a href="amministratore/modifiche-offerte.jsp">
					<button type="button">Torna a Modifiche Offerte</button>
				</a>

				<c:if test="${not empty successMessage}">
					<div class="success-message">${successMessage}</div>
				</c:if>
				<c:if test="${not empty errorMessage}">
					<div class="error-message">${errorMessage}</div>
				</c:if>
			</form>
		</div>
	</main>
	<jsp:include page="footer-amministratore.jsp" />
	<script src="<%=request.getContextPath()%>/js/navbar.js" defer></script>
	<script src="<%=request.getContextPath()%>/js/ajaxAdminModificaOfferte.js" defer></script>
</body>
</html>