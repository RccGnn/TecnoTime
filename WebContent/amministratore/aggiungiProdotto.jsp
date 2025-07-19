
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="icon" type="image/svg+xml" href="<%= request.getContextPath() %>/images/TecnoTimeIcon.svg">
  <title>Aggiungi Prodotto – TecnoTime</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/articolo-single.css">
</head>
<body>

<jsp:include page="header-amministratore.jsp"/>

<main>
<form action="AggiungiProdottoServletYAA" method="POST"  enctype="multipart/form-data" class="productsingle-details">
    <h1 class="productsingle-title">Aggiungi Nuovo Prodotto</h1>

    <label for="nome">Nome Prodotto:</label>
    <input type="text" name="nome" id="nome" required>

    <label for="prezzo">Prezzo (€):</label>
    <input type="text" name="prezzo" id="prezzo" placeholder="99.99" inputmode="decimal" pattern="^\d+([.,]\d{1,2})?$" required>

    <label for="descrizione">Descrizione:</label>
    <textarea name="descrizione" id="descrizione" rows="4" required></textarea>

    <label for="quantita">Quantità:</label>
    <input type="number" name="quantita" id="quantita" min="1" required>

    <label for="tipologia">Tipologia:</label>
    <select name="tipologia" id="tipologia" required>
      <option value="articoloProdotto">Articolo Prodotto</option>
      <option value="servizio">Servizio</option>
      <option value="licenza">Licenza</option>
      <option value="preassemblato">Preassemblato</option>
      <option value="portaConTendina">Porta con Tendina</option>
    </select>

    <label for="immagini">Immagini (PNG, JPG, JPEG, SVG):</label>
    <input type="file" name="immagini" id="immagini" accept=".png,.jpg,.jpeg,.svg" multiple required>

    <button type="submit" id="productsingle-add-btn">Aggiungi Prodotto</button>
  </form>
</main>

<jsp:include page="footer-amministratore.jsp" />
    
<script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
</body>
</html>
