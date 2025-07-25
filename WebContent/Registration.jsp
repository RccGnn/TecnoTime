<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/svg+xml" href="<%= request.getContextPath() %>/images/TecnoTimeIcon.svg">
    <title>Registrazione - TecnoTime</title>
    <!-- Riferimento al CSS comune -->

  	<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/login_registrazione.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="form-container">
        <h2>Registrazione Utente</h2>
        <form action="RegistrationPage" method="post">
        	<section>
        	<h4>SEZIONE DATI ANAGRAFICI</h4>
            <label for="firstName">Nome:</label>
            <input type="text" id="firstName" name="firstName" required>

            <label for="lastName">Cognome:</label>
            <input type="text" id="lastName" name="lastName" required>

            <label for="birthDate">Data di nascita:</label>
            <input type="date" id="birthDate" name="birthDate" required>

            <label for="address">Indirizzo:</label>
            <input type="text" id="address" name="address" required>

            <label for="aptnumber">Numero Civico / Appartamento:</label>
            <input type="text" id="aptnumber" name="aptnumber">

            <label for="postalCode">CAP:</label>
            <input type="text" id="postalCode" name="postalCode" required>

		    <label for="province">Provincia:</label>
			<input list="provinceList"
			       id="province"
			       name="province"
			       placeholder="Inizia a digitare…"
			       oninput="cercaProvincia()"
			       onchange="cercaComune()"
			       required>
			<datalist id="provinceList">
			  <!-- Popolato da ajaxCercaProvincia-->
			</datalist>

            <label for="city">Città:</label>
            <select id="city" name="city" required>
                <option value="">Seleziona città…</option>
            </select>
            <label for="gender">Genere:</label>
            <select id="gender" name="gender" required>
                <option value="M">Maschio</option>
                <option value="F">Femmina</option>
                <option value="A">Altro</option>
                <option value="N">Preferisco non specificare</option>
            </select>

            <label for="nation">Nazionalità:</label>
            <input type="text" id="nation" name="nation">
			</section>
			<section>
			<h4>SEZIONE ACCOUNT</h4>
			<label for="email">Email:</label>
			<input type="email" id="email" name="email" required>

			<label for="username">Username:</label>
			<input type="text" id="username" name="username" required>
			
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            
		    <label for="confirmPassword">Conferma Password:</label>
		    <input type="password" id="confirmPassword" name="confirmPassword" required>

            <label for="telNumb">Telefono:</label>
            <input type="text" id="telNumb" name="telNumb">

			</section>
            <div class="button-row">
  				<button type="reset">Reset</button>
  				<button type="submit">Registrati</button>
			</div>

            <% String error=null;
            	if(request.getAttribute("error")!=null && !error.isEmpty()){ %>
            	<div class="error-message"> <%= error = (String)request.getAttribute("error") %></div>
            <% } %>
            
            
        </form>
    </div>
	<jsp:include page="footer.jsp" />
    
    <script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
    <script src="<%= request.getContextPath() %>/js/ajaxCercaProvincia.js" defer></script>
    <script src="<%= request.getContextPath() %>/js/formValidation.js" defer></script>
    <script src="<%= request.getContextPath() %>/js/ajaxCercaComune.js" defer></script>
    <script src="<%= request.getContextPath() %>/js/fieldValidator.js" defer></script>
</body>
</html>
