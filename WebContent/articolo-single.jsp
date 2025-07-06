<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="it.unisa.model.beans.ArticoloBean" %>

<%
    // Supponiamo che la servlet o il controller abbiano messo in request un bean ArticoloBean chiamato "articolo"
    ArticoloCompletoBean art = (ArticoloCompletoBean) request.getAttribute("articolo");
    if (art == null) {
        art = new ArticoloBean(); // placeholder
    }
%>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" type="image/svg+xml" href="images/TecnoTimeIcon.svg">
  <title>${art.getNome()} – TecnoTime</title>  <!-- controllare come recuperare il nome dell'articolo -->
  <link rel="stylesheet" href="styles.css">
</head>
<body>
  <jsp:include page="header.jsp"/>

  <main class="productsingle-page">
    <div class="productsingle-gallery">
      <div class="productsingle-main-image-wrapper">
        <img id="productsingle-main-image" src="${art.getImageUrl()}" alt="${art.getNome()}">
      </div>
      <div class="productsingle-thumbs">
        <c:forEach var="imgUrl" items="${art.getAdditionalImages()}">
          <img class="productsingle-thumb" src="${imgUrl}" alt="Foto aggiuntiva">  <!--  aggiugere la sorgente delle foto o eventuali foto aggiuntive dal DB -->
        </c:forEach>
      </div>
    </div>

    <div class="productsingle-details">
      <h1 class="productsingle-title">${art.getNome()}</h1>
      <p class="productsingle-price"><fmt:formatNumber value="${art.getPrezzo()}" type="currency" currencySymbol="€"/></p>
      <p class="productsingle-description">${art.getDescrizione()}</p>

      <form id="productsingle-add-form" method="post" action="CartServlet">
        <input type="hidden" name="action"    value="add">
        <input type="hidden" name="productId" value="${art.getCodiceIdentificativo()}">  <!-- controllare come si ottiene il product id -->
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