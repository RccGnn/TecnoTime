<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/svg+xml" href="<%= request.getContextPath() %>/images/TecnoTimeIcon.svg">
    <title>Guide - TecnoTime</title>
    <!-- Riferimento al CSS comune -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
</head>
<body>
      <%if ((Boolean)session.getAttribute("user") !=null &&(Boolean)session.getAttribute("user")){
	  %><jsp:include page="utente/header-utente.jsp"/>
	<% }else if ((Boolean)session.getAttribute("admin") !=null &&(Boolean)session.getAttribute("admin")) {
	  %><jsp:include page="amministratore/header-amministratore.jsp"/>
	<% }else{ 
	  %> <jsp:include page="header.jsp"/><%
   	}%>

	<main class="offers-page">
	
	  <!-- Blocco 1: Griglia di categorie -->
	  <section class="offers-categories">
	    <h2>Categorie in Offerta</h2>
	    <div class="category-grid-offerte">
	      <div class="category-box-offerte">
	        <a href="offerteProcessori.jsp">
	          <img src="images/alt-prodotti.png" alt="Processori">
	          <h3>Processori</h3>
	        </a>
	      </div>
	      <div class="category-box-offerte">
	        <a href="offerteSchedeVideo.jsp">
	          <img src="images/alt-prodotti.png" alt="Schede Video">
	          <h3>Schede Video</h3>
	        </a>
	      </div>
	      <div class="category-box-offerte">
	        <a href="offerteRam.jsp">
	          <img src="images/alt-prodotti.png" alt="RAM">
	          <h3>Memorie</h3>
	        </a>
	      </div>
	      <div class="category-box-offerte">
	        <a href="offerteSchedaMadre.jsp">
	          <img src="images/alt-prodotti.png" alt="Scheda Madre">
	          <h3>Scheda Madre</h3>
	        </a>
	      </div>
	      <div class="category-box-offerte">
	        <a href="offerteArchiviazione.jsp">
	          <img src="images/alt-prodotti.png" alt="Archiviazione">
	          <h3>Case</h3>
	        </a>
	      </div>
	      <div class="category-box-offerte">
	        <a href="offerteAlimentatori.jsp">
	          <img src="images/alt-prodotti.png" alt="Alimentatori">
	          <h3>Alimentatori</h3>
	        </a>
	      </div>
	      
	      <div class="category-box-offerte">
	        <a href="offerteVentole.jsp">
	          <img src="images/alt-prodotti.png" alt="Ventole">
	          <h3>Ventole</h3>
	        </a>
	      </div>
	      
	      <div class="category-box-offerte">
	        <a href="offerteDissipatori.jsp">
	          <img src="images/alt-prodotti.png" alt="Dissipatori">
	          <h3>Dissipatori</h3>
	        </a>
	      </div>
	      
	      <div class="category-box-offerte">
	        <a href="offerteCase.jsp">
	          <img src="images/alt-prodotti.png" alt="Case">
	          <h3>Case</h3>
	        </a>
	      </div>
	      
	      
	    </div>
	  </section>
	
	  <!-- Blocco 2: Sidebar filtri -->
	  <button id="toggleFilters">Filtri</button>
	  <aside class="filter-offerte-sidebar">
	    <h2>Filtri</h2>
	
	    <!-- Fascia di prezzo (slider) -->
	    <div class="filter-offerte-section">
	      <label for="priceRange">Fascia di Prezzo:</label>
	      <input type="range" id="priceRange" min="0" max="1000" step="10" value="1000" />
	      <span id="priceLabel">€0 - €1000</span>
	    </div>
	
	    <!-- Selezione categoria -->
	    <div class="filter-offerte-section">
	      <label for="filterCategory">Categoria:</label>
	      <select id="filterCategory">
	        <option value="">Tutte</option>
	        <option value="Processori">Processori</option>
	        <option value="Schede Video">Schede Video</option>
	        <!-- Altre categorie -->
	      </select>
	    </div>
	
	    <!-- Selezione marca -->
	    <div class="filter-section-offerte">
	      <label for="filterBrand">Marca:</label>
	      <select id="filterBrand">
	        <option value="">Tutti</option>
	        <option value="Intel">Intel</option>
	        <option value="AMD">AMD</option>
	        <option value="NVIDIA">NVIDIA</option>
	        <option value="CORSAIR">Corsair</option>
	        <option value="ASUS">ASUS</option>
	      </select>
	    </div>
	
	    <!-- Switch offerte esclusive eventuali offerte dedicate a utenti che sottoscrivo un abbonamento nella sez servizi-->
	    <div class="filter-section exclusive-switch">
	      <label for="exclusiveSwitch">Offerte esclusive registrati/abbonati:</label>
	      <label class="switch">
	        <input type="checkbox" id="exclusiveSwitch">
	        <span class="slider"></span>
	      </label>
	    </div>
	  </aside>
	
	  <!-- Blocco 3: Contenitore prodotti filtrati con data category per categoria, la marca brand e se e' esclusiva per clienti abbonati -->
	  <section class="filtered-products">
	    <h2>Prodotti Filtrati</h2>
	    <input type="text" id="searchInput" placeholder="Cerca per nome prodotto">
	    <div class="products-container">
	      <!-- Esempio di prodotto (il contenuto reale potrebbe essere generato dinamicamente) -->
	      <div class="product-item" data-category="Processori" data-brand="Intel" data-exclusive="false" data-price="250">
	        <img src="images/alt-prodotti.png" alt="Prodotto 1">
	        <h4>Intel Core i7-14700k</h4>
	        <p class="price">€250 <span class="old-price">€299</span></p>
	      </div>
	      <div class="product-item" data-category="Schede Video" data-brand="AMD" data-exclusive="true" data-price="400">
	        <img src="images/alt-prodotti.png" alt="Prodotto 2">
	        <h4>AMD Radeon RX 7800 XT</h4>
	        <p class="price">€400 <span class="old-price">€450</span></p>
	      </div>
	      <!-- Altri prodotti -->
	    </div>
	  </section>
	
	</main>

	<jsp:include page="footer.jsp" />
    <script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
    <script src="<%= request.getContextPath() %>/js/offerte.js" defer></script>
</body>
</html>