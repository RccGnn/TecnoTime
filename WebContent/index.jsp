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
    <link rel="icon" type="image/svg+xml" href="images/TecnoTimeIcon.svg">
    <title>TecnoTime - E-commerce</title>
    <!-- Link al CSS esterno -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/header.css">
  	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/footer.css">
  	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/index.css">
</head>
<body>
    <%if ((Boolean)session.getAttribute("user") !=null &&(Boolean)session.getAttribute("user")){
	  %><jsp:include page="utente/header-utente.jsp"/>
<% }else if ((Boolean)session.getAttribute("admin") !=null &&(Boolean)session.getAttribute("admin")) {
      %><jsp:include page="amministratore/header-amministratore.jsp"/>
<% }else{ 
	  %> <jsp:include page="header.jsp"/><%
   }%>

	
    <!-- Contenuto principale -->
    <main>
        <!-- Sezione Hero: Animazione + Slogan -->
        <section class="hero">
            <div class="hero-content">
                <div class="hero-animation">
                    <video autoplay loop muted playsinline class="hero-img" aria-label="Animazione hero">
                    <source src="video/hero_animation.mp4" type="video/mp4">
                    <source src="video/hero_animation.webm" type="video/webm">
                    <source src="video/hero_animation.ogv" type="video/ogg">
                </video>
                </div>
            </div>
        </section>

        <!-- Sezione Offerte e Prodotti in evidenza -->
		<section class="deals-section">
		  <h2>Offerte Lampo e Prodotti in Evidenza</h2>
		  <!-- --- Desktop: griglia --- -->
		  <div class="deals-grid">
		    <div class="product-card">
		      <span class="label">Offerta Lampo</span>
		      <img src="images/offerte.png" alt="OFFERTE" class="product-image">
		      <p class="product-name">TIME OFFERTE</p>
		      <p class="product-description">Approfittane ora!</p>
		      <a href="offerte.jsp" class="btn-secondary">Scopri</a>
		    </div>
		    <div class="product-card">
		      <span class="label">OFFERTISSIMA</span>
		      <img src="images/ryzen.jpeg" alt="Ryzen 7 9800x3d" class="product-image">
		      <p class="product-name">Ryzen 7 9800x3d</p>
		      <p class="product-description">King dei Processori</p>
		      <a href="offerte.jsp" class="btn-secondary">Scopri</a>
		    </div>
		    <div class="product-card">
		      <span class="label">PER BREVE TEMPO</span>
		      <img src="images/rtx5090.jpg" alt="ASUS ROG ASTRAL 5090" class="product-image">
		      <p class="product-name">ASUS ROG ASTRAL 5090</p>
		      <p class="product-description">NVIDIA FLAGSHIP</p>
		      <a href="offerte.jsp" class="btn-secondary">Scopri</a>
		    </div>
		  </div>
		
		  <!-- --- Mobile/Tablet: slider “camera roll” --- -->
		  <div class="mobile-slider">
		    <div class="slide" data-index="0">
		      <span class="label">Offerta Lampo</span>
		      <img src="images/offerte.png" alt="OFFERTE" class="product-image">
		      <p class="product-name">TIME OFFERTE</p>
		      <p class="product-description">Approfittane ora!</p>
		      <a href="offerte.jsp" class="btn-secondary">Scopri</a>
		    </div>
		    <div class="slide" data-index="1">
		      <span class="label">OFFERTISSIMA</span>
		      <img src="images/ryzen.jpeg" alt="Ryzen 7 9800x3d" class="product-image">
		      <p class="product-name">Ryzen 7 9800x3d</p>
		      <p class="product-description">King dei Processori</p>
		      <a href="offerte.jsp" class="btn-secondary">Scopri</a>
		    </div>
		    <div class="slide" data-index="2">
		      <span class="label">PER BREVE TEMPO</span>
		      <img src="images/rtx5090.jpg" alt="ASUS ROG ASTRAL 5090" class="product-image">
		      <p class="product-name">ASUS ROG ASTRAL 5090</p>
		      <p class="product-description">NVIDIA FLAGSHIP</p>
		      <a href="offerte.jsp" class="btn-secondary">Scopri</a>
		    </div>
		  </div>
		</section>

        <!-- Sezione Build in Evidenza -->
        <section class="featured-builds">
            <h2>Build in Evidenza</h2>
            <div class="build-scroll">
                <div class="build-card">
                    <h3>AMD Ryzen + NVIDIA RTX</h3>
                    <p>Ryzen 7 5800X, RTX 4070, 32GB DDR4 RAM, 1TB SSD NVME, 75OW</p>
                </div>
                <div class="build-card">
                    <h3>Intel i7 + NVIDIA RTX</h3>
                    <p>Intel i7-12700K, RTX 4080, 32GB DDR5, 2TB SSD NVME, 850W</p>
                </div>
                <div class="build-card">
                    <h3>AMD Ryzen + Radeon</h3>
                    <p>Ryzen 5 7600X, RX 7800 XT, 32GB DDR5 RAM, 1TB SSD NVME, 750W</p>
                </div>
                <div class="build-card">
                    <h3>AMD Ryzen + NVIDIA RTX</h3>
                    <p>Ryzen 9 9950x3d, RTX 5090, 128GB DDR5 RAM, 4TB SSD NVME, 1000W</p>
                </div>
                <div class="build-card">
                    <h3>Intel i7 + NVIDIA RTX</h3>
                    <p>Intel i7-14700K, RTX 5080, 64GB DDR5 RAM, 2TB SSD NVME, 850W</p>
                </div>
                <div class="build-card">
                    <h3>AMD Ryzen + Radeon</h3>
                    <p>Ryzen 7 9800x3d, RX 9070 XT, 32GB DDR5 RAM, 2TB SSD NVME, 850W</p>
                </div>
                <div class="build-card">
                    <h3>Intel i5 + Radeon</h3>
                    <p>Intel i5-13600K, RX 7900 XTX, 64GB DDR5 RAM, 2TB SSD NVME, 850W</p>
                </div>
                <div class="build-card">
                    <h3>Intel Ultra 9 + NVIDIA RTX</h3>
                    <p>Intel Core Ultra 9 285K, RTX 5090, 128GB DDR5 RAM, 4TB SSD NVME, 1000W</p>
                </div>
            </div>
        </section>
    </main>
    
    <jsp:include page="footer.jsp" />
    
	<script src="js/navbar.js" defer></script>
	<script src="js/deals-slider.js" defer></script>
</body>
</html>
