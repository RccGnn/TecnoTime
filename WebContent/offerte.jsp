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
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/header.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/footer.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/index.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/offerte.css">
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
	        <a href="DisplaySubMenu1?sub=Processore&offerta=true">
	          <img src="images/cpu.svg" alt="Processori">
	          <h3>Processori</h3>
	        </a>
	      </div>
	      <div class="category-box-offerte">
	        <a href="DisplaySubMenu1?sub=Scheda_Video&offerta=true">
	          <img src="images/gpu.svg" alt="Schede Video">
	          <h3>Schede Video</h3>
	        </a>
	      </div>
	      <div class="category-box-offerte">
	        <a href="DisplaySubMenu1?sub=RAM&offerta=true">
	          <img src="images/ram.svg" alt="RAM">
	          <h3>Memorie</h3>
	        </a>
	      </div>
	      <div class="category-box-offerte">
	        <a href="DisplaySubMenu1?sub=Schede_Madri&offerta=true">
	          <img src="images/mobo.svg" alt="Schede Madri">
	          <h3>Schede Madri</h3>
	        </a>
	      </div>
	      <div class="category-box-offerte">
	        <a href="DisplaySubMenu1?sub=Memoria_Archiviazione&offerta=true">
	          <img src="images/storage.svg" alt="Archiviazione">
	          <h3>Archiviazione</h3>
	        </a>
	      </div>
	      <div class="category-box-offerte">
	        <a href="DisplaySubMenu1?sub=alimentatore&offerta=true">
	          <img src="images/psu.svg" alt="Alimentatori">
	          <h3>Alimentatori</h3>
	        </a>
	      </div>
	      
	      <div class="category-box-offerte">
	        <a href="DisplaySubMenu1?sub=Accessori_altro&offerta=true">
	          <img src="images/fan.svg" alt="Ventole">
	          <h3>Ventole</h3>
	        </a>
	      </div>
	      
	      <div class="category-box-offerte">
	        <a href="DisplaySubMenu1?sub=Accessori_altro&offerta=true">
	          <img src="images/cooler.svg" alt="Dissipatori">
	          <h3>Dissipatori</h3>
	        </a>
	      </div>
	      
	      <div class="category-box-offerte">
	        <a href="DisplaySubMenu1?sub=Case_PC&offerta=true">
	          <img src="images/case.svg" alt="Case">
	          <h3>Case</h3>
	        </a>
	      </div>
	    </div>
	  </section>
	
	</main>

	<jsp:include page="footer.jsp" />
    <script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
</body>
</html>