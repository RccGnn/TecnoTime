<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    java.util.Calendar cal = java.util.Calendar.getInstance();
    int currentYear = cal.get(java.util.Calendar.YEAR);
    int currentMonth = cal.get(java.util.Calendar.MONTH) + 1; // gennaio=0
%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="icon" type="image/svg+xml" href="<%= request.getContextPath() %>/images/TecnoTimeIcon.svg">
  <title>TecnoTime: Ordine</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
</head>
<body>
  <jsp:include page="header-utente.jsp"/>

  <main>
    <div class="form-container">
      <h2>Inserire dati per il pagamento</h2>

      <form id="checkoutForm" method="GET" action="checkout.jsp">
        <!-- Numero carta (16 cifre) -->
        <label for="ncard">Numero carta:</label>
        <input type="text" id="ncard" name="ncard"
               placeholder="____ ____ ____ ____"
               maxlength="19" required>

        <!-- Data di scadenza -->
        <label for="expiringDate">Data scadenza (MM/YYYY):</label>
        <input type="month" id="expiringDate" name="expiringDate"
               min="${currentYear}-${currentMonth < 10 ? '0'+currentMonth : currentMonth}"
               required>

        <!-- CVC (3 cifre) -->
        <label for="codecard">Codice di sicurezza (CVC):</label>
        <input type="text" id="codecard" name="codecard"
               placeholder="___"
               maxlength="3" required>

        <button type="submit" id="payBtn">PAGAAAH</button>
      </form>
    </div>
  </main>

  <jsp:include page="footer-utente-registrato.jsp"/>
  <script src="<%= request.getContextPath() %>/js/ordine.js" defer></script>
  <script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
</body>
</html>
