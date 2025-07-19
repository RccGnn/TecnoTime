<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // Ottieni anno corrente
    java.util.Calendar cal = java.util.Calendar.getInstance();
    int currentYear = cal.get(java.util.Calendar.YEAR);
    
    String retrive = (String) request.getAttribute("errorRetrive");
    String success = (String) request.getAttribute("eliminazioneSuccess");
    String error = (String) request.getAttribute("eliminazioneError");
    String lista = (String) request.getAttribute("lista");  // inserire la lista nel menu a tendina con jstl
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/svg+xml" href="<%= request.getContextPath() %>/images/TecnoTimeIcon.svg">
    <title>TecnoTime - aggOfferte</title>
  	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/configuratore.css">
</head>
<body>
    <jsp:include page="header-amministratore.jsp" />

<main>
<div class="form-container">
    <h2>RIMUOVI OFFERTA</h2>
    <form id="removeOfferForm" method="POST" action="<%= request.getContextPath() %>/AdminEliminaOfferta.java">

      <fieldset>
        <legend>Seleziona Offerta</legend>
        <div class="input-with-icon">
          <label for="offerSelect">Offerta da eliminare:</label>
          <select id="offerSelect" name="offerId" required>
            <option value="">-- Seleziona --</option>
            <c:forEach var="off" items="${offersList}">
              <option value="${off.id}">${off.name} - ${off.discount}%</option>
            </c:forEach>
          </select>
        </div>
      </fieldset>
      
     		<% if(retrive != null) { %>
	      		<div> <%=retrive %></div>
	      	<% } else if( success != null) {%>
				<div> <%=success %></div>
			<%} else if (error != null) {%>
				<div> <%=error %></div>
			<%} %>

      <button type="submit">Rimuovi Offerta</button>
		
 	  <a href="modifiche-offerte.jsp">
	  <button type="button">Torna a Modifiche Offerte</button>
	  </a>
	  
      <c:if test="${not empty successMessage}">
        <div class="success-message">${successMessage}</div>
      </c:if>
      <c:if test="${not empty errorMessage}">
        <div class="error-message">${errorMessage}</div>
      </c:if>
    </form>
  </div>
</main>
	<jsp:include page="footer-amministratore.jsp" />
    <script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
</body>
</html>