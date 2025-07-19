<%@page import="it.unisa.model.DAO.Order.OrdineCompletoDao"%>
<%@page import="it.unisa.model.beans.OrdineCompletoBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String username =request.getParameter("username");
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/svg+xml" href="<%= request.getContextPath() %>/images/TecnoTimeIcon.svg">
    <title>GestOrdini Utenti - TecnoTime</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/ordine.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/filters.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/admin-ordini.css">
</head>
<jsp:include page="header-amministratore.jsp" />

<main>
<div class="rieplOrdini">
    <h1>Gestione Ordini Utente</h1>

    <form method="get" class="admin-search-form">
        <label for="username">Cerca utente:</label>
        <input type="text" id="username" name="username" placeholder="Inserisci username" value="<%= username != null ? username : "" %>">
        <button type="submit">Cerca</button>
    </form>

    <div class="orders-list-page">
        <c:choose>
            <c:when test="${not empty ordiniUtente}">
                <c:forEach var="ordine" items="${ordiniUtente}">
                    <div class="orders-summary-section">
                        <div class="order-header">
                            <div class="order-details-group">
                                <div class="order-detail-item">
                                    <span class="detail-label">Transazione</span>
                                    <span class="detail-value">${ordine.numeroTransazione}</span>
                                </div>
                                <div class="order-detail-item">
                                    <span class="detail-label">Utente</span>
                                    <span class="detail-value">${ordine.username}</span>
                                </div>
                                <div class="order-detail-item">
                                    <span class="detail-label">Data</span>
                                    <span class="detail-value">${ordine.dataOrdine}</span>
                                </div>
                            </div>
                        </div>
                        <div class="order-items-list">
                            <c:forEach var="item" items="${ordine.elementiOrdine}">
                                <div class="order-item-card">
                                    <img class="order-item-img" src="${item.immagine}" alt="Prodotto">
                                    <div class="order-item-details">
                                        <div class="order-item-name">${item.nomeProdotto}</div>
                                        <div class="order-item-qty">Quantità: ${item.quantita}</div>
                                        <div class="order-item-price-per-unit">Prezzo unitario: €${item.prezzoUnitario}</div>
                                    </div>
                                    <div class="order-item-total-price">Totale: €${item.prezzoTotale}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="no-orders-message">
                    <c:choose>
                        <c:when test="${empty username}">
                            Inserisci uno username per visualizzare gli ordini.
                        </c:when>
                        <c:otherwise>
                            Nessun ordine trovato per l'utente "<%= username %>".
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

</main>
	<jsp:include page="footer-amministratore.jsp" />
    <script src="<%= request.getContextPath() %>/js/navbar.js" defer></script>
</body>
</html>
