<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<link rel="icon" type="image/svg+xml" href="<%= request.getContextPath() %>/images/TecnoTimeIcon.svg">
	<title>Chi Siamo - TecnoTime</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/header.css">
  	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/footer.css">
  	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/index.css">
  	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/chiSiamo.css">
  	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/guide.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<!-- assembly-guide da sostituire -->
	<main class="assembly-guide">
	<section>
	    <h1>Breve introduzione a TecnoTime</h1>
	    <p class="intro">Scopri i volti dietro il logo e la loro storia</p>
	</section>

	<section>
	<h2>Su di noi...</h2>
	    <p class="intro2">Perchè l'azienda è stata formata</p>
		<p> TecnoTime nasce nel 2025 nelle aule di un’università sperduta tra le 
			montagne salernitane da un minuto team di studenti d’informatica accomunati 
			dalla passione per i computer, il gaming e dall'odio verso le form di registrazione 
			del sito dell'ADISURC che prima ti chiede lo SPID e poi ti fa inserire tutti i dati ricavabili dallo SPID.
		</p>
	</section>

	<section>
	<h2>Da dove veniamo...</h2>
	    <p class="intro2">Background e il ruolo del team che ha fondato l’azienda</p>
		<p> Nonostante l’azienda sia nata solo recentemente, questa si contraddistingue per il suo team di sviluppatori 
		d’eccellenza formati tra le aulee dell’università precedentemente menzionata. Con figure di spicco con un’insieme 
		di esperienze che variano da esperti del backend a veri e propri artisti del frontend, l’azienda assicura la migliore 
		esperienza di utilizzo possibile ai suoi clienti.
		</p>
	</section>

	<section>
	<h2>Il nostro obiettivo...</h2>
		<p> L’obiettivo che TecnoTime si propone di realizzare in un futuro molto prossimo è quello di diventare un punto di 
		riferimento online per appassionati e professionisti dell’informatica e del gaming, facendo leva sulla vasta gamma di
		prodotti e servizi in vendita.
		</p>
	</section>

	<section class="team-page">
	  <section class="team-intro">
	    <h1>Il Nostro Team</h1>
	    <p>Scopri chi siamo e cosa facciamo ogni giorno per offrirti il meglio.</p>
	  </section>
	
	  <section class="team-employees">
	    <!-- Per ogni membro creare un “card” -->
	    <div class="team-employees__employee" style="background-image:url('images/mario_mascheri.jpg')">
	      <div class="team-employees__info">
	        <h3 class="team-employees__name">Mario Mascheri</h3>
	        <p class="team-employees__position">Sviluppatore Front-end</p>
	      </div>
	    </div>
	    <div class="team-employees__employee" style="background-image:url('images/stefano_santoro.jpg')">
	      <div class="team-employees__info">
	        <h3 class="team-employees__name">Stefano Santoro</h3>
	        <p class="team-employees__position">Sviluppatore Back-end</p>
	      </div>
	    </div>
	    <div class="team-employees__employee" style="background-image:url('images/giovanni_riccardi.jpg')">
	      <div class="team-employees__info">
	        <h3 class="team-employees__name">Giovanni Riccardi</h3>
	        <p class="team-employees__position">DataBase & JS Developer</p>
	      </div>
	    </div>
	  </section>
	</section>
    </main>
    
    <jsp:include page="footer.jsp" />
    <script src="js/navbar.js" defer></script>
</body>
</html>
