<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String message = null;
	if (request.getAttribute("confirmMessage") != null)
		message = (String) request.getAttribute("confirmMessage");
	String error = null;
	if(request.getAttribute("error")!=null)
		error=(String)request.getAttribute("error");
	String success= null;
	if(request.getAttribute("success")!=null)
		success=(String)request.getAttribute("success");		
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/svg+xml" href="images/TecnoTimeIcon.svg">
    <title>Login - TecnoTime</title>
    <!-- Riferimento al CSS comune -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/login_registrazione.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <main>
    <div class="login-container">
    	<% if (message != null) {%>
    	<span class="success-message"> <%= message %></span>
    	<% } %>
    	<% if (success != null) {%>
    	<span class="success-message"> <%= success %></span>
    	<% } %>
    	
    	
        <h2>Effettua il Login</h2>
        <% if (error!=null){ %>
    	<div class="error-message"> <%= error %></div>
    	<%} %>
        <form action="LoginPage" method="post">
            <div>
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div>
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Accedi</button>
        </form>
        
        <div class="forgot-password">
        <a href="forgotpassword.jsp">Hai dimenticato la password?</a>
        </div>

        <div class="register-link">
            <span>Non hai un account?</span><br>
            <a href="Registration.jsp">Registrati</a>
        </div>
    </div>
	</main>
   
  	<jsp:include page="footer.jsp" />
   
    <script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
</body>
</html>
