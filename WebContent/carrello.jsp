<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="it.unisa.model.beans.*" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/svg+xml" href="images/TecnoTimeIcon.svg">
    <title>Carrello - TecnoTime</title>
    <!-- Riferimento al CSS comune -->
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <jsp:include page="header.jsp" />

	<main class="cart-container">
        <h1>Il tuo carrello</h1>

        <c:choose>
            <c:when test="${empty sessionScope.cartItems}">
                <p class="empty-cart-msg">Il carrello è vuoto.</p>
            </c:when>
            
            <c:otherwise>
                <table class="cart-table">
                    <thead>
                        <tr>
                            <th>Prodotto</th>
                            <th>Prezzo unitario</th>
                            <th>Quantità</th>
                            <th>Totale</th>
                            <th></th>
                        </tr>
                    </thead>
                    
                    <tbody>
                        <c:forEach var="item" items="${sessionScope.cartItems}">
					        <tr>
					        
					        -1    <td>
					                <div class="cart-product-info">
					                    <img src="${item.product.imageUrl}" alt="${item.product.name}" class="cart-product-img">
					                    <span class="cart-product-name">${item.product.name}</span>
					                </div>
					            </td>
					
					          2  <td>
					                <fmt:formatNumber value="${item.product.price}" type="currency" currencySymbol="€"/>
					            </td>
					
					          3  <td>
					                <form method="post" action="CartServlet" class="quantity-form">
					                    <input type="hidden" name="action" value="update">
					                    <input type="hidden" name="productId" value="${item.product.id}">
					                    <input type="number" name="quantity" value="${item.quantity}" min="1" class="quantity-input">
					                </form>
					            </td>
					
					          4  <td>
					                <fmt:formatNumber value="${item.product.price * item.quantity}" type="currency" currencySymbol="€"/>
					            </td>
					
					          5  <td>
					                <form method="post" action="CartServlet">
					                    <input type="hidden" name="action" value="remove">
					                    <input type="hidden" name="productId" value="${item.product.id}">
					                    <button type="submit" class="remove-btn">Rimuovi</button>
					                </form>
					            </td>
					        </tr>
					    </c:forEach>
                    </tbody>
                </table>
                <div class="cart-summary">
                    <p><strong>Totale Carrello:</strong> € <c:out value="${sessionScope.cartTotal}"/></p>
                    <form action="CheckoutServlet" method="get">
    				<button type="submit" class="checkout-btn">Procedi al Checkout</button>
					</form>
                </div>
            </c:otherwise>
        </c:choose>
    </main>
	
	<jsp:include page="footer.jsp" />
	<script src="js/cart.js" defer></script>
    <script src="js/navbar.js" defer></script>
</body>
</html>
