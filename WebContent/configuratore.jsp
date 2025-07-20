<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/svg+xml" href="<%= request.getContextPath() %>/images/TecnoTimeIcon.svg">
    <title>Configuratore - TecnoTime</title>
    <!-- Riferimento al CSS comune -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/configuratore.css">
</head>
<body>
			<%
			Boolean isUser = (Boolean) session.getAttribute("user");
			Boolean isAdmin = (Boolean) session.getAttribute("admin");
			
			if (Boolean.TRUE.equals(isUser)) {
			%>
			<jsp:include page="utente/header-utente.jsp"  />
			<%
			} else if (Boolean.TRUE.equals(isAdmin)) {
			%>
			<jsp:include page="amministratore/header-amministratore.jsp" />
			<%
			} else {
			%>
			<jsp:include page="header.jsp" />
			<%
			}
			%>
<main>
    <div class="form-container">
      <h2>Inserire informazioni richieste per la configurazione</h2>

      <form id="pcConfigForm" method="GET" action="configure_pc.jsp">

        <fieldset>
            <legend>Componenti Principali</legend>

            <div class="input-with-icon">
                <label for="caseInput">Case:</label>
                <span class="input-icon-container">
			    <img src="<%= request.getContextPath() %>/images/case.svg" alt="" class="input-icon">
			    </span>
                <input type="text" id="caseInput" name="case"
                       placeholder="Es: NZXT H7 Flow" maxlength="100" required>
            </div>

            <div class="input-with-icon">
                <label for="processorInput">Processore:</label>
                <span class="input-icon-container">
			    <img src="<%= request.getContextPath() %>/images/cpu.svg" alt="" class="input-icon">
			    </span>
                <input type="text" id="processorInput" name="processor"
                       placeholder="Es: Intel Core i9-13900K" maxlength="100" required>
            </div>

            <div class="input-with-icon">
                <label for="motherboardInput">Scheda Madre:</label>
                <span class="input-icon-container">
			    <img src="<%= request.getContextPath() %>/images/mobo.svg" alt="" class="input-icon">
			    </span>
                <input type="text" id="motherboardInput" name="motherboard"
                       placeholder="Es: ASUS ROG MAXIMUS Z790 HERO" maxlength="100" required>
            </div>

            <div class="input-with-icon">
                <label for="ramInput">RAM:</label>
                <span class="input-icon-container">
			    <img src="<%= request.getContextPath() %>/images/ram.svg" alt="" class="input-icon">
			    </span>
                <input type="text" id="ramInput" name="ram"
                       placeholder="Es: Corsair Vengeance DDR5 32GB (2x16GB)" maxlength="100" required>
            </div>

            <div class="input-with-icon">
                <label for="gpuInput">Scheda Video:</label>
                <span class="input-icon-container">
			    <img src="<%= request.getContextPath() %>/images/gpu.svg" alt="" class="input-icon">
			    </span>
                <input type="text" id="gpuInput" name="gpu"
                       placeholder="Es: NVIDIA GeForce RTX 4090" maxlength="100" required>
            </div>

            <div class="input-with-icon">
                <label for="storageInput">Archiviazione:</label>
                <span class="input-icon-container">
			    <img src="<%= request.getContextPath() %>/images/storage.svg" alt="" class="input-icon">
			    </span>
                <input type="text" id="storageInput" name="storage"
                       placeholder="Es: Samsung 990 Pro 2TB NVMe SSD" maxlength="100" required>
            </div>

            <div class="input-with-icon">
                <label for="psuInput">Alimentatore:</label>
                <span class="input-icon-container">
			    <img src="<%= request.getContextPath() %>/images/psu.svg" alt="" class="input-icon">
			    </span>
                <input type="text" id="psuInput" name="psu"
                       placeholder="Es: Corsair RM1000e" maxlength="100" required>
            </div>

            <div class="input-with-icon">
                <label for="fansInput">Ventole (Opzionale):</label>
                <span class="input-icon-container">
			    <img src="<%= request.getContextPath() %>/images/fan.svg" alt="" class="input-icon">
			    </span>
                <input type="text" id="fansInput" name="fans"
                       placeholder="Es: Noctua NF-A12x25 PWM (3x)" maxlength="100">
            </div>

        </fieldset>

        <fieldset>
            <legend>Opzioni di Raffreddamento</legend>
            <div class="radio-group">
                <p>Tipo di Dissipatore:</p>
                <div>
                    <input type="radio" id="coolerAir" name="coolerType" value="air" required>
                    <label for="coolerAir">Ad Aria</label>
                </div>
                <div>
                    <input type="radio" id="coolerLiquid" name="coolerType" value="liquid" required>
                    <label for="coolerLiquid">A Liquido (AIO)</label>
                </div>
            </div>
        </fieldset>

        <fieldset>
            <legend>Richieste Aggiuntive</legend>
            <div>
                <label for="customLoopDetails">Impianto Custom Loop (Descrivi i dettagli):</label>
                <textarea id="customLoopDetails" name="customLoopDetails" rows="5" cols="50"
                          placeholder="Descrivi i dettagli del tuo impianto custom loop, come tipo di tubi e colore, radiatori, pompe"
                          maxlength="1000"></textarea>
            </div>
        </fieldset>

		<% Boolean admin = (Boolean) session.getAttribute("admin");
			String avviso = "bottone disabilitato per admin";
			if(admin!=null &&admin==true){%>
					<button type="submit" id="submitConfigBtn" disabled>Invia Configurazione</button>
					<div class="error-message"><%=avviso %></div>
			<%} else{%>
				<button type="submit" id="submitConfigBtn">Invia Configurazione</button>
        	<%}%>

    </form>
    </div>
    </main>
    

	<jsp:include page="footer.jsp" />
    <script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
</body>
</html>