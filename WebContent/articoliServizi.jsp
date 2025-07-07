<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="icon" type="image/svg+xml" href="images/TecnoTimeIcon.svg">
  <title>Prodotti - TecnoTime</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
  <jsp:include page="header.jsp"/>

  <main class="products-page">
    <!-- ↑ Contenitore flessibile principale -->
    <!-- Mobile: bottone hamburger per filtri -->
    <button id="filter-toggle-btn" class="filter-toggle-btn">Filtri ☰</button>

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
          <label for="duration">Durata minima licenza (giorni):</label>
          <input  onchange="displaySlider(); sortedProducts();" type="range" min="1" max = 366 id="duration"
          value = 0>
          <output id="slider"> </output>
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
        <!--  
        <div class="product-card">
          <img src="${p.imageURL}" alt="${p.name}" class="product-image"/>
          <h3 class="product-name">${p.name}</h3>
          <p class="product-price">€ ${p.price}</p>
          <p class="product-description">${p.description}</p>
          <button class="add-to-cart-btn">Aggiungi al carrello</button>
        </div>
        -->
    </section>
  </main>

  <jsp:include page="footer.jsp"/>

  <script src="js/navbar.js" defer></script>
  <script src="js/ajaxArticoli.js" defer></script>
</body>
</html>