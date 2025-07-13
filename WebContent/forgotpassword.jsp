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
	<div class="form-container">
      <h2>Imposta una nuova password</h2>
      
	<form class="recuperopassword" action="ForgotPassword" method="post">
	<label for="username">Username</label>
		<input type="text" id="username" name="username" required>

	<label for="newPassword">Nuova Password</label>
        <input type="password" id="newPassword" name="pwd" 
          placeholder="Almeno 9 caratteri, 1 maiuscola, 1 minuscola, 1 numero, 1 simbolo" 
          required/>
          
	<label for="confirmPassword">Conferma Password</label>
        <input type="password" id="confirmPassword" name="confirmPassword" 
          placeholder="Riscrivi la nuova password" 
          required/>
          
		<button type="submit" id="resetBtn">Conferma</button>
      </form>
      
      	<%	String s = "errore recupero username";
			if((Boolean)request.getAttribute("error") != null && (Boolean)request.getAttribute("error") == true){
				%> 
				<%=s %> 
				<%
				}
				%>
      
      </div>
      
	</main>
      
	<jsp:include page="footer.jsp" />
    <script src="js/navbar.js" defer></script>
</body>
</html>