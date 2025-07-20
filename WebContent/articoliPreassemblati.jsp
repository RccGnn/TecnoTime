<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="icon" type="image/svg+xml" href="<%= request.getContextPath() %>/images/TecnoTimeIcon.svg">
  <title>Preassemblati e portatili - TecnoTime</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/header.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/footer.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/index.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/offerte.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/articoli.css">
</head>
<body>

<%if ((Boolean)session.getAttribute("user") !=null &&(Boolean)session.getAttribute("user")){
	  %><jsp:include page="utente/header-utente.jsp"/>
<% }else if ((Boolean)session.getAttribute("admin") !=null &&(Boolean)session.getAttribute("admin")) {
      %><jsp:include page="amministratore/header-amministratore.jsp"/>
<% }else{ 
	  %> <jsp:include page="header.jsp"/><%
   }%>

  <main class="products-page">
    <!-- ↑ Contenitore flessibile principale -->
    <!-- Mobile: bottone hamburger per filtri -->
    <button id="filter-toggle-btn" class="filter-toggle-btn">Filtri ☰</button>

	<div id="notification" class="notification"> Articolo aggiunto al carrello! </div>
    <!-- SIDEBAR FILTRI -->
    <aside id="filters-sidebar" class="filters-sidebar">
           <h2>Filtra Prodotti</h2>
        <div class="filter-group">
          <label for="min">Prezzo min (€):</label>
          <input  onchange="sortedProducts()" type="number" id="min" name="min" min="0"
                 value="${param.min != null ? param.min : 0}">
        </div>
        <div class="filter-group">
          <label for="max">Prezzo max (€):</label>
          <input  onchange="sortedProducts()" type="number" id="max" name="max" min="0"
                 value="${param.max != null ? param.max : 10000}">
        </div>
        <div class="filter-group">
          <label for="name">Cerca per nome:</label>
          <input  onchange="sortedProducts()" type="text" id="name" name="name"
                 value="${param.nome != null ? param.nome : ''}">
        </div>
        <div class="filter-group">
          <label for="sort">Ordina per:</label>
          <select  onchange="sortedProducts()" id="sort" name="sort">
            <option value="prezzo asc"  ${param.sort=='price asc' ? 'selected':''}> Prezzo ↑</option>
            <option value="prezzo desc" ${param.sort=='price desc' ? 'selected':''}> Prezzo ↓</option>
            <option value="nome asc"   ${param.sort=='name asc' ? 'selected':''}> Nome A→Z</option>
            <option value="nome desc"  ${param.sort=='name desc' ? 'selected':''}> Nome Z→A</option>
          </select>
        </div>
    </aside>

    <!-- GRID PRODOTTI -->
    <section class="products-container">
    </section>
    
    <!-- Pagination Component -->
    <div id="pagination-nav" aria-label="Pagination" class="pagination-nav">
    </div>
  </main>

  <jsp:include page="footer.jsp"/>

  <script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
  <script src="<%= request.getContextPath() %>/js/ajaxArticoli.js" defer></script>
</body>
</html>