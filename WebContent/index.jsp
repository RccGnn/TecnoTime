<%@ page  language="java" contentType="text/html; charset=UTF-8" %>
<%
    // parte servlet e bean
    
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
            <input type="text" class="search-bar" placeholder="Cerca...">
        </div>
        <div class="header-right">
            <a href="#cart" class="icon-link">
                <img src="cart-icon.png" alt="Carrello" class="icon">
            </a>
            <a href="#login" class="icon-link">
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
            <li><a href="#AssemPort">PREASSEMBLATI & PORTATILI</a></li>
            <li><a href="#Guide">GUIDE</a></li>
        </ul>
    </nav>

    <!-- Contenuto principale -->
    <main>
        <section id="products">
            <h2>I Nostri Prodotti</h2>
            <div class="product" id="product1">
                <h3>Prodotto 1</h3>
                <iframe width="853" height="480" src="https://www.youtube.com/embed/CBlVu0tonUo"
                        title="T-47 Airspeeder Booom!!!" frameborder="0"
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                        referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
                <p>Descrizione del prodotto 1.</p>
                <button>Aggiungi al carrello</button>
            </div>
            <div class="product" id="product2">
                <h3>Prodotto 2</h3>
                <p>Descrizione del prodotto 2.</p>
                <button>Aggiungi al carrello</button>
            </div>
            <div class="product" id="product3">
                <h3>Prodotto 3</h3>
                <p>Descrizione del prodotto 3.</p>
                <button>Aggiungi al carrello</button>
            </div>
        </section>

        <section id="about">
            <h2>Chi Siamo</h2>
            <p>Informazioni su TecnoTime.</p>
        </section>

        <section id="contact">
            <h2>Contatti</h2>
            <p>Email: info@tecnotime.com</p>
        </section>
    </main>

    <!-- Footer -->
    <footer>
        <p>&copy; 2025 TecnoTime. Tutti i diritti riservati.</p>
    </footer>
</body>
</html>
