<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/login_registrazione.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/configuratore.css">
<title>TecnoTime Admin - E-commerce</title>
</head>
<body>
 <jsp:include page="header-amministratore.jsp" />
	
	
	<h2>Rimuovi un prodotto</h2>

<form action="rimuoviProdottoServlet" method="post" onsubmit="return confermaRimozione()">
    <div class="search-container">
	    <div class="form-group">
				<label for="Cerca un prodotto per eliminarlo "></label>
		</div>
        <img src="<%= request.getContextPath() %>/images/magnifying_glass.png" alt="Ricerca" class="search-icon" />
        <input type="text" class="search-bar" id="search-bar" name="nomeProdotto" autocomplete="off" placeholder="Cerca prodotto..." oninput="search()">
        <div id="search-results" class="search-results-dropdown"></div>
    </div>

    <button type="submit" class="remove-button">Rimuovi il Prodotto selezionato </button>

		<a href="amministratore/modifiche-offerte.jsp">
			<button type="button">Torna a Modifiche Offerte</button>
		</a>
	</form>

	<jsp:include page="footer-amministratore.jsp" />
	<script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
</body>
</html>