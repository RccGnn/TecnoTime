<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"    content="width=device-width, initial-scale=1.0">
  <link rel="icon" type="image/svg+xml" href="<%= request.getContextPath() %>/images/TecnoTimeIcon.svg">
  <title>TecnoTime: Checkout</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
</head>
<body>
  <jsp:include page="header-utente.jsp"/>

  <main>
    <div class="form-container" style="text-align:center;">
      <h2>PAGAMENTO COMPLETATO</h2>
      <form action="utente/index-utente.jsp" method="get">
        <button type="submit" class="checkout-btn">TORNA ALLA HOME</button>
      </form>
    </div>
  </main>

  <jsp:include page="footer-utente-registrato.jsp"/>
  <script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
</body>
</html>
