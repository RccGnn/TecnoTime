<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/svg+xml" href="images/TecnoTimeIcon.svg">
    <title>TecnoTime - Errore</title>
    <!-- Link al CSS esterno -->
    <link rel="stylesheet" href="styles.css">
</head>

<body>
<jsp:include page="../header.jsp" />
	
    <!-- Contenuto principale -->
    
		<main style="padding: 4rem; text-align: center; background-color: #F2F2F2; min-height: 70vh;">
	  <h1 style="font-size: 5rem; color: #021859; margin-bottom: 1rem;">
	    <c:choose>
	      <c:when test="${pageContext.errorData.statusCode == 404}">
	        404
	      </c:when>
	      <c:otherwise>
	        500
	      </c:otherwise>
	    </c:choose>
	  </h1>
	
	  <h2 style="color: #011140; font-size: 1.5rem; margin-bottom: 1.5rem;">
	    <c:choose>
	      <c:when test="${pageContext.errorData.statusCode == 404}">
	        La pagina non è stata trovata
	      </c:when>
	      <c:otherwise>
	        Si è verificato un errore interno al server
	      </c:otherwise>
	    </c:choose>
	  </h2>
	
	  <p style="font-size: 1rem; color: #333; margin-bottom: 2rem;">
	    <c:if test="${not empty exception}">
	      <strong>Dettaglio:</strong> ${exception.message}
	    </c:if>
	    <br>
	    Ritorna alla 
	    <a href="${pageContext.request.contextPath}/" 
	       style="
	         color: #F2762E;
	         font-weight: bold;
	         text-decoration: none;
	       "
	    >homepage</a>.
	  </p>
	
	  <button onclick="window.location.href='${pageContext.request.contextPath}/'" 
	          style="
	            background-color: #F2762E;
	            color: #fff;
	            border: none;
	            border-radius: 6px;
	            padding: 12px 24px;
	            font-size: 1rem;
	            cursor: pointer;
	            transition: background-color .3s;
	          "
	          onmouseover="this.style.backgroundColor='#d1641f'"
	          onmouseout="this.style.backgroundColor='#F2762E'">
	    Torna alla Home
	  </button>
	</main>

	<jsp:include page="../footer.jsp" />
	<script src="js/navbar.js" defer></script>
	
</body>
</html>