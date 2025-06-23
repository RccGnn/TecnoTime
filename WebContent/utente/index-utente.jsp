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
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <jsp:include page="header-utente-registrato.jsp" />
	
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
        <section class="product-scroll">
            <h2>Offerte Lampo e Prodotti in Evidenza</h2>
            <div class="scroll-container">
                <div class="product-card">
                    <div class="product-left">
                        <span class="label">Offerta Lampo</span>
                        <p>Gaming Mouse RGB a metà prezzo!</p>
                    </div>
                    <div class="product-right">
                        <img src="images/mouse-hyperx.png" alt="Mouse" class="product-image">
                        <p class="product-name">Mouse HyperX RGB</p>
                    </div>
                </div>
                <!-- Altri prodotti simili qui -->
            </div>
        </section>

        <!-- Sezione Build in Evidenza -->
        <section class="featured-builds">
            <h2>Build in Evidenza</h2>
            <div class="build-scroll">
                <div class="build-card">
                    <h3>AMD Ryzen + NVIDIA RTX</h3>
                    <p>Ryzen 7 5800X, RTX 4070, 32GB RAM</p>
                </div>
                <div class="build-card">
                    <h3>Intel i7 + NVIDIA RTX</h3>
                    <p>Intel i7-12700K, RTX 4080, 32GB DDR5</p>
                </div>
                <div class="build-card">
                    <h3>AMD Ryzen + Radeon</h3>
                    <p>Ryzen 5 7600X, RX 7800 XT, 32GB RAM</p>
                </div>
            </div>
        </section>
    </main>
    
    <jsp:include page="footer-utente-registrato.jsp" />
    
	<script src="../js/navbar.js" defer></script>
</body>
</html>