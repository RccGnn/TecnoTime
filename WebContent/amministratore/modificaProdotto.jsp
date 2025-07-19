
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="it.unisa.model.beans.ArticoloCompletoBean"%>

<c:set var="articolo" value="${requestScope.articolo}" />

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="icon" type="image/svg+xml" href="<%= request.getContextPath() %>/images/TecnoTimeIcon.svg">
  <title>Modifica ${articolo.nome} – TecnoTime</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/articolo-single.css">
</head>
<body>
<jsp:include page="header-amministratore.jsp"/>

<main class="productsingle-page">
  <div class="productsingle-gallery">
    <div class="productsingle-main-image-wrapper">
      <img id="productsingle-main-image"
     	src="${articolo.immagini[0].url != null 
            ? articolo.immagini[0].url 
            : '../images/alt-prodotti.png'}"
     	alt="${articolo.nome}">
    </div>
    <div class="productsingle-thumbs">
      <c:forEach var="imgBean" items="${articolo.immagini}">
        <img class="productsingle-thumb" src="${imgBean.url}" alt="Foto aggiuntiva">
      </c:forEach>
    </div>
  </div>

  <form action="ModificaProdottoServletYAA" method="post" class="productsingle-details">
    <input type="hidden" name="codiceIdentificativo" value="${articolo.codiceIdentificativo}" />

    <h1 class="productsingle-title">${articolo.nome}</h1>

    <label for="prezzo">Prezzo (€):</label>
    <input type="text" name="prezzo" id="prezzo"
           value="<c:choose>
                    <c:when test='${articolo.pdFisico != null}'>${articolo.pdFisico.prezzo}</c:when>
                    <c:when test='${articolo.pdDigitale != null}'>${articolo.pdDigitale.prezzo}</c:when>
                    <c:when test='${articolo.servizio != null}'>${articolo.servizio.prezzo}</c:when>
                  </c:choose>"
           inputmode="decimal" pattern="^\d+([.,]\d{1,2})?$" required />

    <label for="descrizione">Descrizione:</label>
    <textarea name="descrizione" id="descrizione" rows="4" required>
      <c:choose>
        <c:when test="${articolo.pdFisico != null}">${articolo.pdFisico.descrizione}</c:when>
        <c:when test="${articolo.pdDigitale != null}">${articolo.pdDigitale.descrizione}</c:when>
        <c:when test="${articolo.servizio != null}">${articolo.servizio.descrizione}</c:when>
      </c:choose>
    </textarea>

    <button type="submit" id="productsingle-add-btn">Salva Modifiche</button>
  </form>
</main>

<jsp:include page="footer-amministratore.jsp" />
    
<script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
</body>
</html>