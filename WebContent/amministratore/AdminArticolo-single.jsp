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
  <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/header.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/footer.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/index.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/offerte.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/articoli.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/articolo-single.css">
</head>
<body>

	<jsp:include page="header-amministratore.jsp"/>


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
					
				</c:when>
				<c:when test="${articolo.pdDigitale != null}">
					<p class="productsingle-price">
						<fmt:formatNumber value="${articolo.pdDigitale.prezzo}"
							type="currency" currencySymbol="€" />
					</p>
					<p class="productsingle-description">${articolo.pdDigitale.descrizione}</p>
				</c:when>
				<c:when test="${articolo.servizio != null}">
					<p class="productsingle-price">
						<fmt:formatNumber value="${articolo.servizio.prezzo}"
							type="currency" currencySymbol="€" />
					
				</c:when>
			</c:choose>
		<label for="prezzo">Prezzo:</label>
					<input type="text" name="prezzo" id="prezzo"
      				 placeholder="99.99"
       				inputmode="decimal"
    				  pattern="^\d+([.,]\d{1,2})?$"
       				class="productsingle-price-input" />
							placeholder="${articolo.pdDigitale.prezzo}" />
							
					 <label for="descrizione">Descrizione:</label>    <!-- descrizione modificabile -->
					<textarea name="descrizione" id="descrizione" rows="4">$
    						{articolo.pdDigitale.descrizione}</textarea> 


			<form id="productsingle-add-form" method="post" action="CartServlet">
				<input type="hidden" name="action" value="add"> <input
					type="hidden" name="productId"
					value="${articolo.codiceIdentificativo}">
				<!-- controllare come si ottiene il product id -->
				<label for="productsingle-quantity">Quantità:</label> <input
					type="number" id="productsingle-quantity" name="quantity" value="1"
					min="1">
				<!-- aggiungere eventuale controllo per vedere la quantità disponibile del prodotto considerato -->
				<button id="productsingle-add-btn" type="submit">Aggiungi
					al carrello</button>
				<!-- id per fare la submit per aggiungere al carrello -->
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

	<jsp:include page="footer-amministratore.jsp"/>
  <script src="<%= request.getContextPath() %>/js/articolo-single.js" defer></script>
</body>
</html>