<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // Ottieni anno corrente
    java.util.Calendar cal = java.util.Calendar.getInstance();
    int currentYear = cal.get(java.util.Calendar.YEAR);
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/svg+xml" href="<%= request.getContextPath() %>/images/TecnoTimeIcon.svg">
    <title>TecnoTime - modifOfferte</title>
    <!-- Link al CSS esterno -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/header.css">
  	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/footer.css">
  	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/index.css">
  	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/offerte.css">
  	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/articoli.css">
</head>
<body>
    <jsp:include page="header-amministratore.jsp" />
    
    <main>
    <section class="offers-categories">
	    <h2>OPZIONI DI MODIFICA</h2>
	    <div class="category-grid-offerte">
	      <div class="category-box-offerte">
	        <a href="aggiungiOfferta.jsp">
	          <img src="<%= request.getContextPath() %>/images/offerte.png" alt="ADDSALE">
	          <h3>AGGIUNGI OFFERTA</h3>
	        </a>
	      </div>
	      <div class="category-box-offerte">
	        <a href="<%= request.getContextPath()%>/AdminEliminaOfferte">
	          <img src="<%= request.getContextPath() %>/images/offerte.png" alt="REMOVESALE">
	          <h3>RIMUOVI OFFERTA</h3>
	        </a>
	      </div>
	      <div class="category-box-offerte">
	        <a href="<%= request.getContextPath()%>/AdminModificaOfferte">
	          <img src="<%= request.getContextPath() %>/images/offerte.png" alt="MODIFYSALE">
	          <h3>MODIFICA OFFERTA</h3>
	        </a>
	      </div>
	    </div>
	</section>  
	</main>
	
	<jsp:include page="footer-amministratore.jsp" />
    
	<script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
</body>
</html>