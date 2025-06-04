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
    <!-- Header con logo, ricerca, icone -->
<header class="main-header">
    <div class="header-left">
        <img src="images/TecnoTime.png" alt="Logo TecnoTime" class="logo">
        <span class="brand-name">TECNOTIME</span>
    </div>
    <div class="header-center">
        <div class="search-container">
            <img src="images/magnifying_glass.png" alt="Ricerca" class="search-icon">
            <input type="text" class="search-bar" placeholder="Cerca...">
        </div>
    </div>
    <div class="header-right">
        <a href="#cart" class="icon-link">
            <img src="images/shopping_cart.png" alt="Carrello" class="icon">
             <span class="Carrello">Carrello</span>
        </a>
        <a href="LoginPage.jsp" class="icon-link">
            <img src="images/user.png" alt="Utente" class="icon">
            <span class="login-text">Login / Registrati</span>
        </a>
    </div>
</header>

    <!-- Menu di navigazione con dropdown -->
    <nav class="main-nav">
        <ul>
            <li><a href="#about">CHI SIAMO</a></li>
            <li>
                <a href="#products">PRODOTTI</a>
                <ul class="dropdown">
                    <li><a href="#product1">Processori</a></li>
                    <li><a href="#product2">Schede Video</a></li>
                    <li><a href="#product3">Schede Madri</a></li>
                    <li><a href="#product4">Memorie</a></li>
                    <li><a href="#product5">Archiviazione</a></li>
                </ul>
            </li>
            <li>
                <a href="#license">LICENZE</a>
                <ul class="dropdown">
                    <li><a href="#license1">Windows 11 Home</a></li>
                    <li><a href="#license2">Windows 11 Pro</a></li>
                    <li><a href="#license3">Windows 10 Home</a></li>
                    <li><a href="#license4">Windows 10 Pro</a></li>
                    <li><a href="#license5">Office Package</a></li>
                </ul>
            </li>
            <li><a href="#services">SERVIZI</a></li>
            <li><a href="#AssemPort">PREASSEMBLATI e PORTATILI</a></li>
            <li><a href="#Guide">GUIDE</a></li>
        </ul>
    </nav>
	
    <!-- Contenuto principale -->
    <main>
        <!-- Sezione Hero: Animazione + Slogan -->
        <section class="hero">
            <div class="hero-content">
                <div class="hero-animation">
                    <video autoplay loop muted playsinline class="hero-img" aria-label="Animazione hero">
  					<source src="video/hero_animation.mp4" type="video/mp4">
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
    
    <!-- Footer con social -->
    <div class="footer-social">
  		<a title="X" href="https://x.com/HoldBeer2?t=WXAnWo664YaUjLkYw0qBXQ&s=09" target="_blank" rel="noreferrer">
    		<img src="images/x.svg" alt="X" class="social-icon">
  		</a>
  		<a title="Instagram" href="https://www.instagram.com/mariomask02/?hl=ha-ng" target="_blank" rel="noreferrer">
    		<img src="images/instagram.svg" alt="Instagram" class="social-icon">
  		</a>
  		<a title="Facebook" href="https://www.facebook.com/people/Mario-Mascheri/pfbid0JCYXq6ZKrCJxth6DJUKfGfx2tecUK7aA4H1yUMmH9LHH3W21A88b5NfrHSuUNTBHl/" target="_blank" rel="noreferrer">
    		<img src="images/facebook.svg" alt="Facebook" class="social-icon">
  		</a>
  		<a title="YouTube" href="https://www.youtube.com/@lordmario4672" target="_blank" rel="noreferrer">
    		<img src="images/youtube.svg" alt="YouTube" class="social-icon">
  		</a>
  		<a title="TikTok" href="https://www.tiktok.com/@TecnoTime" target="_blank" rel="noreferrer">
    		<img src="images/tiktok.svg" alt="TikTok" class="social-icon">
  		</a>	
	</div>
	
  	<!-- Pre-footer -->
	<div class="pre-footer">
	    <div class="banner-container">
	        <img src="images/banner_pubblicitario.png" alt="Banner Pubblicitario" class="banner-png">
	    </div>
	    <button class="config-btn" onclick="window.location.href='configuratore.html'">Configura il tuo PC</button>
	</div>

	<!-- Footer -->
	<footer>
	    <div class="footer-content">
	        <!-- Chi siamo -->
	        <section id="about" class="footer-center">
	            <h2>Chi Siamo</h2>
	            <p>Siamo un piccola realtà che nasce dall'idea di ragazzi pronti alla sfida di creare
	            configurazioni per ogni fascia sempre con il miglior rapporto qualità-prezzo</p>
	        </section>
	
	        <!-- Logo al centro -->
	        <div class="footer-logo">
	            <img src="images/TecnoTime.png" alt="Logo Footer">
	        </div>
	
	        <!-- Contatti a destra -->
	        <section class="footer-right">
	            <h2>Contatti</h2>
	            <p>Email: info@tecnotime.com<br>
	            Telefono: +39 089 256 3945<br>
	            Cellulare: +39 348 924 3567
	            </p>
	        </section>
	    </div>
	    <p class="copy">&copy; <%= currentYear %> TecnoTime. Tutti i diritti riservati.</p>
	</footer>
	<script src="js/navbar.js" defer></script>
</body>
</html>

