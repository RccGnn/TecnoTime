<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/svg+xml" href="images/TecnoTimeIcon.svg">
    <title>Login - TecnoTime</title>
    <!-- Riferimento al CSS comune -->
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="form-container">
        <h2>Registrazione Utente</h2>
        <form action="RegistrationPage" method="post">
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
			       oninput="filterDatalist()"
			       required>
			<datalist id="provinceList">
			  <!-- Sarà popolato da AJAX -->
			</datalist>

            <label for="city">Città:</label>
            <select id="city" name="city" required>
                <option value="">Seleziona città…</option>
            </select>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <label for="telNumb">Telefono:</label>
            <input type="text" id="telNumb" name="telNumb">

            <label for="gender">Genere:</label>
            <select id="gender" name="gender" required>
                <option value="M">Maschio</option>
                <option value="F">Femmina</option>
            </select>

            <label for="nation">Nazionalità:</label>
            <input type="text" id="nation" name="nation">

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="role">Ruolo (es. utente/admin):</label>
            <input type="text" id="role" name="role" required>

            <button type="submit">Registrati</button>
            <button type="reset">Reset</button>
        </form>

        <% String error = (String) request.getAttribute("error"); %>
        <% if (error != null && !error.isEmpty()) { %>
            <div class="error-message"><%= error %></div>
        <% } %>
        <% String message = (String) request.getAttribute("message"); %>
        <% if (message != null && !message.isEmpty()) { %>
            <div class="success-message"><%= message %></div>
        <% } %>
    </div>
	<jsp:include page="footer.jsp" />
    
    <script src="js/navbar.js" defer></script>
    <script src="js/ajaxCercaProvincia.js" defer></script>
    <script src="js/formValidation.js" defer></script>
</body>
</html>
