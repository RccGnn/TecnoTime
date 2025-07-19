<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <title>TecnoTime - E-commerce</title>
    <!-- Link al CSS esterno -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/header.css">
  	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/footer.css">
  	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/index.css">
</head>
<body>
<!-- Footer -->
    <div class="footer-social">
        <a title="X" href="https://x.com/TecnoTimeee" target="_blank" rel="noreferrer">
            <img src="<%= request.getContextPath() %>/images/x.svg" alt="X" class="social-icon">
        </a>
        <a title="Instagram" href="https://www.instagram.com/mariomask02/" target="_blank" rel="noreferrer">
            <img src="<%= request.getContextPath() %>/images/instagram.svg" alt="Instagram" class="social-icon">
        </a>
        <a title="Facebook" href="https://www.facebook.com/TecnoTime" target="_blank" rel="noreferrer">
            <img src="<%= request.getContextPath() %>/images/facebook.svg" alt="Facebook" class="social-icon">
        </a>
        <a title="YouTube" href="https://www.youtube.com/@TecnoTimeee" target="_blank" rel="noreferrer">
            <img src="<%= request.getContextPath() %>/images/youtube.svg" alt="YouTube" class="social-icon">
        </a>
        <a title="TikTok" href="https://www.tiktok.com/@tecnotimeee" target="_blank" rel="noreferrer">
            <img src="<%= request.getContextPath() %>/images/tiktok.svg" alt="TikTok" class="social-icon">
        </a>
    </div>
    
    <!-- Pre-footer -->
	<div class="pre-footer">
	    <div class="banner-container">
	        <img src="<%= request.getContextPath() %>/images/banner_pubblicitario.png" alt="Banner Pubblicitario" class="banner-png">
	    </div>
	    <button class="config-btn" onclick="window.location.href='configuratore.html'">Configura il tuo PC</button>
	</div>

    <footer>
        <div class="footer-content">
            <section id="about" class="footer-center">
                <h2>Chi Siamo</h2>
                <p>Siamo una piccola realtà di appassionati di gaming e custom PC: il nostro obiettivo è
                   offrirti il miglior rapporto qualità-prezzo per ogni configurazione.</p>
            </section>
            <div class="footer-logo">
                <img src="<%= request.getContextPath() %>/images/TecnoTime.png" alt="Logo Footer">
            </div>
            <section class="footer-right">
                <h2>Contatti</h2>
                <p>Email: info@tecnotime.it<br>
                   Telefono: +39 089 256 3945<br>
                   Cellulare: +39 348 924 3567
                </p>
            </section>
        </div>
        <p class="copy">&copy; <%= currentYear %> TecnoTime. Tutti i diritti riservati.</p>
    </footer>
    <script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
</body>
</html>
