<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page import="it.unisa.model.beans.ArticoloCompletoBean"%>

<c:set var="articolo" value="${requestScope.articolo}" />

<!DOCTYPE html>
<html lang="it">

<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" type="image/svg+xml" href="${pageContext.request.contextPath}/images/TecnoTimeIcon.svg">
  <title>${articolo.nome} – TecnoTime</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
  <jsp:include page="header.jsp"/>

  <main class="productsingle-page">
    <div class="productsingle-gallery">
      <div class="productsingle-main-image-wrapper">
        <img id="productsingle-main-image" src="${articolo.immagini[0].url != null ? articolo.immagini[0].url : "/images/alt-prodotti.png"}" alt="${articolo.nome}">
      </div>
      <div class="productsingle-thumbs">
        <c:forEach var="imgBean" items="${articolo.immagini}">
          <img class="productsingle-thumb" src="${imgBean.url}" alt="Foto aggiuntiva">  <!--  aggiugere la sorgente delle foto o eventuali foto aggiuntive dal DB -->
        </c:forEach>
      </div>
    </div>

    <div class="productsingle-details">
      <h1 class="productsingle-title">${articolo.nome}</h1>
      <c:choose>
        <c:when test="${articolo.pdFisico != null}">
          <p class="productsingle-price"><fmt:formatNumber value="${articolo.pdFisico.prezzo}" type="currency" currencySymbol="€"/></p>
          <p class="productsingle-description">${articolo.pdFisico.descrizione}</p>
        </c:when>
        <c:when test="${articolo.pdDigitale != null}">
          <p class="productsingle-price"><fmt:formatNumber value="${articolo.pdDigitale.prezzo}" type="currency" currencySymbol="€"/></p>
          <p class="productsingle-description">${articolo.pdDigitale.descrizione}</p>
        </c:when>
        <c:when test="${articolo.servizio != null}">
          <p class="productsingle-price"><fmt:formatNumber value="${articolo.servizio.prezzo}" type="currency" currencySymbol="€"/></p>
          <p class="productsingle-description">${articolo.servizio.descrizione}</p>
        </c:when>
      </c:choose>
      
      <form id="productsingle-add-form" method="post" action="CartServlet">
        <input type="hidden" name="action"    value="add">
        <input type="hidden" name="productId" value="${articolo.codiceIdentificativo}">  <!-- controllare come si ottiene il product id -->
        <label for="productsingle-quantity">Quantità:</label>
        <input type="number" id="productsingle-quantity" name="quantity" value="1" min="1">  <!-- aggiungere eventuale controllo per vedere la quantità disponibile del prodotto considerato -->
        <button id="productsingle-add-btn" type="submit">Aggiungi al carrello</button>  <!-- id per fare la submit per aggiungere al carrello -->
      </form>
    </div>
  </main>

  <section class="productsingle-reviews">
    <h2>Recensioni dei clienti</h2>
    <article class="productsingle-review">
      <h3>Mario R.</h3>
      <p>⭐⭐⭐⭐☆</p>
      <p>Ottimo prodotto, spedizione rapida!</p>
    </article>
    <article class="productsingle-review">
      <h3>Stefanini B.</h3>
      <p>⭐⭐⭐⭐⭐</p>
      <p>Funziona esattamente come descritto.</p>
    </article>
     <!-- da popolare con DB -->
  </section>

  <jsp:include page="footer.jsp"/>
  <script src="js/articolo-single.js" defer></script>
</body>
</html>