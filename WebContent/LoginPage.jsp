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
    <title>Login - TecnoTime</title>
    <!-- Riferimento al CSS comune -->
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <!-- Header -->
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

    <!-- Navbar di esempio -->
    <nav class="main-nav">
        <ul>
            <li><a href="index.jsp#about">CHI SIAMO</a></li>
            <li>
                <a href="index.jsp#products">PRODOTTI</a>
                <ul class="dropdown">
                    <li><a href="index.jsp#product1">Processori</a></li>
                    <li><a href="index.jsp#product2">Schede Video</a></li>
                    <li><a href="index.jsp#product3">Schede Madri</a></li>
                    <li><a href="index.jsp#product4">Memorie</a></li>
                    <li><a href="index.jsp#product5">Archiviazione</a></li>
                </ul>
            </li>
            <li>
                <a href="index.jsp#license">LICENZE</a>
                <ul class="dropdown">
                    <li><a href="index.jsp#license1">Windows 11 Home</a></li>
                    <li><a href="index.jsp#license2">Windows 11 Pro</a></li>
                    <li><a href="index.jsp#license3">Windows 10 Home</a></li>
                    <li><a href="index.jsp#license4">Windows 10 Pro</a></li>
                    <li><a href="index.jsp#license5">Office Package</a></li>
                </ul>
            </li>
            <li><a href="index.jsp#services">SERVIZI</a></li>
            <li><a href="index.jsp#AssemPort">PREASSEMBLATI e PORTATILI</a></li>
            <li><a href="index.jsp#Guide">GUIDE</a></li>
        </ul>
    </nav>

    <main>
    <div class="login-container">
        <h2>Effettua il Login</h2>
        <form action="LoginPage" method="post">
            <div>
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div>
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Accedi</button>
        </form>

        <div class="register-link">
            <span>Non hai un account?</span><br>
            <a href="Registration.jsp">Registrati</a>
        </div>
    </div>
	</main>
    
    <!-- Footer -->
    <div class="footer-social">
        <a title="X" href="https://x.com/TecnoTime" target="_blank" rel="noreferrer">
            <img src="images/x.svg" alt="X" class="social-icon">
        </a>
        <a title="Instagram" href="https://www.instagram.com/TecnoTime" target="_blank" rel="noreferrer">
            <img src="images/instagram.svg" alt="Instagram" class="social-icon">
        </a>
        <a title="Facebook" href="https://www.facebook.com/TecnoTime" target="_blank" rel="noreferrer">
            <img src="images/facebook.svg" alt="Facebook" class="social-icon">
        </a>
        <a title="YouTube" href="https://www.youtube.com/TecnoTime" target="_blank" rel="noreferrer">
            <img src="images/youtube.svg" alt="YouTube" class="social-icon">
        </a>
        <a title="TikTok" href="https://www.tiktok.com/@TecnoTime" target="_blank" rel="noreferrer">
            <img src="images/tiktok.svg" alt="TikTok" class="social-icon">
        </a>
    </div>

    <footer>
        <div class="footer-content">
            <section id="about" class="footer-center">
                <h2>Chi Siamo</h2>
                <p>Siamo una piccola realtà di appassionati di gaming e custom PC: il nostro obiettivo è
                   offrirti il miglior rapporto qualità-prezzo per ogni configurazione.</p>
            </section>
            <div class="footer-logo">
                <img src="images/TecnoTime.png" alt="Logo Footer">
            </div>
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
