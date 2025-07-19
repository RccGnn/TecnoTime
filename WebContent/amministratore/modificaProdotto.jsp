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
        	<label for="prezzo">Prezzo del'articolo: </label>
        	<input type="number" step="0.01" class="productsingle-price" value="${articolo.pdFisico.prezzo}">

        	<label for="descrizione">Prezzo del prodotto: </label>
        	<textarea class="productsingle-description">
        		${articolo.pdFisico.descrizione}
        	</textarea>
        </c:when>
        <c:when test="${articolo.pdDigitale != null}">
        	<label for="prezzo">Prezzo del'articolo: </label>
        	<input type="number" step="0.01" class="productsingle-price" value="${articolo.pdDigitale.prezzo}">

        	<label for="descrizione">Prezzo del prodotto: </label>
        	<textarea class="productsingle-description">
        		${articolo.pdDigitale.descrizione}
        	</textarea>
        </c:when>
        <c:when test="${articolo.servizio != null}">
			<label for="prezzo">Prezzo del'articolo: </label>
        	<input type="number" step="0.01" class="productsingle-price" value="${articolo.servizio.prezzo}">

        	<label for="descrizione">Prezzo del prodotto: </label>
        	<textarea class="productsingle-description">
        		${articolo.servizio.descrizione}
        	</textarea>
        </c:when>
      </c:choose>
      
      <form id="modifyProduct" method="post" action="CartServlet">
        <input type="hidden" name="action"    value="add">
        <input type="hidden" name="productId" value="${articolo.codiceIdentificativo}">  <!-- controllare come si ottiene il product id -->
        
        <label for="productsingle-quantity">Quantità:</label>
      <c:choose>
        <c:when test="${articolo.pdFisico != null}">        	
        	<input type="number" id="productsingle-quantity" name="quantity" value="1" min="0">  <!-- aggiungere eventuale controllo per vedere la quantità disponibile del prodotto considerato -->
        </c:when>
        <c:when test="${articolo.pdDigitale != null}">
        	<input type="number" id="productsingle-quantity" name="quantity" value="1" min="0">  <!-- aggiungere eventuale controllo per vedere la quantità disponibile del prodotto considerato -->
        </c:when>
        <c:when test="${articolo.servizio != null}">
        	<output id="productsingle-quantity"> <i><b>Unlimited (Tecnotime own services)</b></i> </output><br> <!-- aggiungere eventuale controllo per vedere la quantità disponibile del prodotto considerato -->
        </c:when>
      </c:choose>
        
        <button id="productsingle-add-btn" type="submit">Conferma modifiche</button>  <!-- id per fare la submit per aggiungere al carrello -->
      </form>
    </div>
  </main>

  <section class="productsingle-reviews">
  </section>

  <jsp:include page="footer-amministratore.jsp"/>
  <script src="<%= request.getContextPath() %>/js/articolo-single.js" defer></script>
</body>
</html>