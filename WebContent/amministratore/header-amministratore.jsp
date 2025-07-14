<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/svg+xml"
	href="<%= request.getContextPath() %>/images/TecnoTimeIcon.svg">
<title>TecnoTime - E-commerce</title>
<!-- Link al CSS esterno -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
</head>
<body>
	<!-- Header con logo, ricerca, icone -->
	<header class="main-header">
		<div class="header-left">
			<img src="<%= request.getContextPath() %>/images/TecnoTime.png"
				alt="Logo TecnoTime" class="logo">
			<%if (request.getRequestURI().contains("amministratore")) {%>
			<a href="index-amministratore.jsp" class="icon-link"> <span
				class="brand-name">TECNOTIME</span>
			</a>
			<%}else if(session.getAttribute("admin")!=null && (Boolean)session.getAttribute("admin")==true ){ %>
			<a href="utente/index-amministratore.jsp" class="icon-link"> <span
				class="brand-name">TECNOTIME</span>
			</a>
			<% }else{ %>
			<a href="index.jsp" class="icon-link"> <span class="brand-name">TECNOTIME</span>
			</a>
			<%}
       	%>
			<span class="brand-name">TECNOTIME</span>
		</div>
		<div class="header-center">
			<div class="search-container">
				<img
					src="<%= request.getContextPath() %>/images/magnifying_glass.png"
					alt="Ricerca" class="search-icon"> <input type="text"
					class="search-bar" id="search-bar" oninput="search()"
					placeholder="Cerca...">
				<!-- Spazio per mostrare i risultati della ricerca -->
				<div id="search-results" class="search-results-dropdown"></div>
			</div>
		</div>
		<div class="header-right">
			<a href="<%= request.getContextPath() %>/carrello.jsp"
				class="icon-link"> <!-- l'amministratore non dovrebbe avere la funzionalita carrello -->
				<img src="<%= request.getContextPath() %>/images/shopping_cart.png"
				alt="Carrello" class="icon"> <span class="Carrello">Carrello</span>
			</a> <a href="${pageContext.request.contextPath}/LogoutServlet"
				class="icon-link"> <img
				src="<%= request.getContextPath() %>/images/user.png" alt="Utente"
				class="icon"> <span class="login-text">Logout</span>
			</a>

			<div class="admin-menu">
				<button class="admin-menu-btn" id="adminMenuBtn"
					aria-label="Menu modifiche">
					<span class="admin-bar"></span> <span class="admin-bar"></span> <span
						class="admin-bar"></span>
				</button>
				<div class="admin-dropdown-content" id="adminDropdown">
					<a href="modifiche-prodotti.jsp">Modifiche Prodotti</a> <a
						href="modifiche-offerte.jsp">Modifiche Offerte</a>
				</div>
			</div>
		</div>
	</header>

	<!-- Menu di navigazione con dropdown -->
	<nav class="main-nav">
		<button class="hamburger" id="hamburgerBtn" aria-label="Apri menu">
			<span class="bar"></span> <span class="bar"></span> <span class="bar"></span>
		</button>
		<ul id="main-menu">
			<li><a href="#about">CHI SIAMO</a></li>
			<li><a
				href="<%= request.getContextPath() %>/articoliProdotti.jsp">PRODOTTI</a>
				<ul class="dropdown">
					<li class="mobile-only"><a
						href="<%= request.getContextPath() %>/articoliProdotti.jsp">Tutti i Prodotti</a></li>
					<li><a href="#product1">Processori</a></li>
					<li><a href="#product2">Schede Video</a></li>
					<li><a href="#product3">Schede Madri</a></li>
					<li><a href="#product4">Memorie</a></li>
					<li><a href="#product5">Archiviazione</a></li>
				</ul></li>
			<li><a
				href="<%= request.getContextPath() %>/articoliLicenze.jsp">LICENZE</a>
				<ul class="dropdown">
					<li class="mobile-only"><a
						href="<%= request.getContextPath() %>/articoliLicenze.jsp">Tuttele Licenze</a></li>
					<li><a href="#license1">Windows 11 Home</a></li>
					<li><a href="#license2">Windows 11 Pro</a></li>
					<li><a href="#license3">Windows 10 Home</a></li>
					<li><a href="#license4">Windows 10 Pro</a></li>
					<li><a href="#license5">Office Package</a></li>
				</ul></li>
			<li><a
				href="<%= request.getContextPath() %>/articoliServizi.jsp">SERVIZI</a></li>
			<li><a
				href="<%= request.getContextPath() %>/articoliPreassemblati.jsp">PREASSEMBLATI E PORTATILI</a></li>
			<li><a href="<%= request.getContextPath() %>/guide.jsp"class="icon-link">GUIDE</a></li>
		</ul>
	</nav>
	<script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
	<script src="<%= request.getContextPath() %>/js/ajaxRicerca.js" defer></script>
</body>
</html>
