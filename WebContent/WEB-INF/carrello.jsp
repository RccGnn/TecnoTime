<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" type="image/svg+xml" href="${pageContext.request.contextPath}/images/TecnoTimeIcon.svg">
  <title>TecnoTime – Errore</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
  <jsp:include page="../header.jsp" />

  <main class="assembly-guide">
    <h1>
      <c:choose>
        <c:when test="${pageContext.errorData.statusCode == 404}">
          404
        </c:when>
        <c:otherwise>
          500
        </c:otherwise>
      </c:choose>
    </h1>

    <h2>
      <c:choose>
        <c:when test="${pageContext.errorData.statusCode == 404}">
          La pagina non è stata trovata
        </c:when>
        <c:otherwise>
          Si è verificato un errore interno al server
        </c:otherwise>
      </c:choose>
    </h2>

    <c:if test="${not empty exception}">
      <p class="error-message">
        <strong>Dettaglio:</strong> ${exception.message}
      </p>
    </c:if>

    <p>
      Torna alla
      <a href="${pageContext.request.contextPath}/" class="login-text">
        homepage
      </a>.
    </p>

    <button type="button" onclick="location.href='${pageContext.request.contextPath}/'" class="config-btn">
      Torna alla Home
    </button>
  </main>

  <jsp:include page="../footer.jsp" />
  <script src="${pageContext.request.contextPath}/js/navbar.js" defer></script>
</body>
</html>