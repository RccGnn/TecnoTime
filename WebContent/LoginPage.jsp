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
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/login-toggle.css">
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
        <form action="LoginPage" method="post" autocomplete="off">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" required autocomplete="username">
            </div>
            <div class="form-group password-group">
                <label for="password">Password</label>
                <div class="password-wrapper">
                <input type="password" id="password" name="password" required autocomplete="current-password">
            <button type="button" id="togglePassword" aria-label="Mostra o nascondi password">
                <!-- default: occhi coperti -->
                <svg id="icon-hide" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="20" height="20" fill="#011140">
                  <path d="M12 5c-7 0-11 7-11 7s4 7 11 7 11-7 11-7-4-7-11-7zm0 12a5 5 0 110-10 5 5 0 010 10z"/>
                  <path d="M12 9a3 3 0 100 6 3 3 0 000-6z"/>
                </svg>
                <!-- show icon: occhi aperti -->
                <svg id="icon-show" class="hidden" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="20" height="20" fill="#011140">
                  <path d="M2 2l20 20M10.5 10.5a2.5 2.5 0 013.5 3.5M12 5c-7 0-11 7-11 7s2 3.5 6 5M23 12s-2-3.5-6-5"/>
                </svg>
              </button>
            </div>
          </div>

          <button type="submit" class="btn-primary">Accedi</button>
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
    <script src="<%= request.getContextPath() %>/js/login-toggle.js" defer></script>
</body>
</html>
