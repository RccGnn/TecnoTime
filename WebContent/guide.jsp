<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/svg+xml" href="images/TecnoTimeIcon.svg">
    <title>Guide - TecnoTime</title>
    <!-- Riferimento al CSS comune -->
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <jsp:include page="header.jsp" />

   	<main> 
   		<div class="responsive-yt-wrapper" id="yt-player-wrapper">
		  <div id="ytplayer-placeholder">
		    <img src="https://img.youtube.com/vi/DC-Xn2C_L1U/hqdefault.jpg" alt="Anteprima video">
		    <div class="yt-play-button-overlay">&#9658;</div>
		  </div>
		</div>

		<script src="js/youtubeguide-lazy.js" defer></script>

   	</main>
   	
   	<jsp:include page="footer.jsp" />
    <script src="js/navbar.js" defer></script>
</body>
</html>
