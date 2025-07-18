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
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/header.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/footer.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/index.css">
</head>
<body>

	<!-- Header con logo, ricerca, icone -->
	<header class="main-header">
		<div class="header-left">
			<%if (request.getRequestURI().contains("utente")) {%>
			<a href="index-utente.jsp" class="icon-link"> <img src="<%= request.getContextPath() %>/images/TecnoTime.png"
				alt="Logo TecnoTime" class="logo">
			</a>
			<%}else if(session.getAttribute("user")!=null && (Boolean)session.getAttribute("user")==true ){ %>
			<a href="utente/index-utente.jsp" class="icon-link"> <img src="<%= request.getContextPath() %>/images/TecnoTime.png"
				alt="Logo TecnoTime" class="logo">
			</a>
			<% }else{ %>
			<a href="index.jsp" class="icon-link"> <img src="<%= request.getContextPath() %>/images/TecnoTime.png"
				alt="Logo TecnoTime" class="logo">
			</a>
			<%}
       	%>
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
			<a href=<%=request.getContextPath()+"/MockFillCart"%>
				class="icon-link"> <img
				src="<%= request.getContextPath() %>/images/shopping_cart.png"
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
					<a href="<%= request.getContextPath() %>/utente/riepilogoOrdini-utente.jsp">Riepilogo Ordini</a> 
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
					<% 	String destination;
						destination = "&destination=fisico";%>
					<li class="mobile-only"><a
						href="<%= request.getContextPath() %>/articoliProdotti.jsp">Tutti
							i Prodotti</a></li>
					<li><a href="DisplaySubMenu1?sub=Processore<%=destination%>">Processori</a></li>
					<li><a href="DisplaySubMenu1?sub=Scheda_Video<%=destination%>">Schede Video</a></li>
					<li><a href="DisplaySubMenu1?sub=Scheda_Madre<%=destination%>">Schede Madri</a></li>
					<li><a href="DisplaySubMenu1?sub=RAM<%=destination%>">Memorie</a></li>
					<li><a href="DisplaySubMenu1?sub=Archiviazione<%=destination%>">Archiviazione</a></li>
				</ul></li>
			<li><a
				href="<%= request.getContextPath() %>/articoliLicenze.jsp">LICENZE</a>
				<ul class="dropdown">
					<li class="mobile-only"><a
						href="<%= request.getContextPath() %>/articoliLicenze.jsp">Tutte
							le Licenze</a></li>
					<% 	destination = "&destination=digitale";%>
					<li class="mobile-only"><a href="articoliLicenze.jsp">Tutte le Licenze</a></li>
					<li><a href="DisplaySubMenu2?sub=Windows_11_Home<%=destination%>">Windows 11 Home</a></li>
					<li><a href="DisplaySubMenu2?sub=Windows_11_Pro<%=destination%>">Windows 11 Pro</a></li>
					<li><a href="DisplaySubMenu2?sub=Windows_10_Home<%=destination%>">Windows 10 Home</a></li>
					<li><a href="DisplaySubMenu2?sub=Windows_10_Pro<%=destination%>">Windows 10 Pro</a></li>
					<li><a href="DisplaySubMenu2?sub=Office_Package<%=destination%>">Office Package</a></li>
				</ul></li>
			<li><a
				href="<%= request.getContextPath() %>/articoliServizi.jsp">SERVIZI</a></li>
			<li><a
				href="<%= request.getContextPath() %>/articoliPreassemblati.jsp">PREASSEMBLATI E PORTATILI</a></li>
			<li><a href="<%= request.getContextPath() %>/guide.jsp"
				class="icon-link">GUIDE</a></li>
		</ul>
	</nav>
	<input type="hidden" id="ContextPath" value="<%= request.getContextPath() %>" />
	<!-- serve ad avere sempre nel js un riferimento al contextpath  -->
	<script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
	<script src="<%= request.getContextPath() %>/js/ajaxRicerca.js" defer></script>
</body>
</html>
