<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    </body>
    </html>