<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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
    <link rel="icon" type="image/svg+xml" href="images/TecnoTimeIcon.svg">
    <title>Ordini effettuati - TecnoTime</title>
    <!-- Link al CSS esterno -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
</head>
<body>
   <%if ((Boolean)session.getAttribute("user") !=null &&(Boolean)session.getAttribute("user")){
	  %><jsp:include page="utente/header-utente.jsp"/>
<% }else if ((Boolean)session.getAttribute("admin") !=null &&(Boolean)session.getAttribute("admin")) {
      %><jsp:include page="amministratore/header-amministratore.jsp"/>
<% }else{ 
	  %> <jsp:include page="header.jsp"/><%
   }%>
   
   
   
	<jsp:include page="footer.jsp" />
    
	<script src="js/navbar.js" defer></script>
	<script src="js/deals-slider.js" defer></script>
</html>