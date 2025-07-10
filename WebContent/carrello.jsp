<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="icon" type="image/svg+xml" href="images/TecnoTimeIcon.svg">
  <title>Carrello - TecnoTime</title>
  <link rel="stylesheet" href="styles.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
  <jsp:include page="header.jsp"/>

  <div class="cart-page-container">
    <c:choose>
      <c:when test="${empty sessionScope.cartItems}">
        <div class="empty-cart-container">
            <i class="fas fa-shopping-cart empty-cart-icon"></i>
            <h1>Il tuo carrello è vuoto</h1>
            <p>Aggiungi prodotti per vederli qui.</p>
            <a href="ProductServlet" class="btn-primary">Vai ai Prodotti</a>
        </div>
      </c:when>
      <c:otherwise>
        <div class="cart-layout">
          <div class="cart-items-section">
            <div class="cart-header">
              <h1>IL TUO CARRELLO (${sessionScope.cartItems.size()})</h1>
              <form method="post" action="CarrelloDefinitivo">
                  <input type="hidden" name="action" value="clear"/>
                  <button type="submit" class="remove-all-btn">RIMUOVI TUTTO</button>
              </form>
            </div>

            <c:forEach var="item" items="${sessionScope.cartItems}">
              <div class="cart-item-card">
                <img src="${item.imageUrl}" alt="${item.name}" class="cart-item-img"/>
                <div class="cart-item-details">
                  <span class="cart-item-name">${item.name}</span>
                  <form method="post" action="CartServlet" class="quantity-form">
                    <input type="hidden" name="action" value="update"/>
                    <input type="hidden" name="productId" value="${item.productId}"/>
                    <label for="quantity-${item.productId}">Qtà</label>
                    <select name="quantity" id="quantity-${item.productId}" class="quantity-select" onchange="this.form.submit()">
                        <c:forEach var="i" begin="1" end="10">
                            <option value="${i}" ${i == item.quantity ? 'selected' : ''}>${i}</option>
                        </c:forEach>
                    </select>
                  </form>
                  <form method="post" action="CartServlet" class="remove-form">
                    <input type="hidden" name="action" value="remove"/>
                    <input type="hidden" name="productId" value="${item.productId}"/>
                    <button type="submit" class="remove-item-btn">RIMUOVI</button>
                  </form>
                </div>
                <div class="cart-item-price">
                  <%-- sezione per aggiungere il prezzo --%>
                  <%-- <span class="original-price"><fmt:formatNumber value="${item.originalPrice}" type="currency" currencySymbol="€"/></span> --%>
                  <span class="current-price"><fmt:formatNumber value="${item.price}" type="currency" currencySymbol="€"/></span>
                </div>
              </div>
            </c:forEach>
          </div>

          <div class="order-summary-section">
            <h2>RIEPILOGO ORDINE</h2>
            <div class="summary-line">
              <span>Subtotale</span>
              <span><fmt:formatNumber value="${sessionScope.cartTotal}" type="currency" currencySymbol="€"/></span>
            </div>
            <div class="summary-line">
              <span>Sconto Indicativo</span>
              <span class="discount-color">-€0,00</span>
            </div>
            <div class="summary-line">
              <span>Spedizione</span>
              <span>calcolata al checkout</span>
            </div>
            <div class="summary-line">
              <span>Imposte</span>
              <span>calcolata al checkout</span>
            </div>
            <hr class="summary-divider"/>
            <div class="summary-line total">
              <span>Totale</span>
              <span><fmt:formatNumber value="${sessionScope.cartTotal}" type="currency" currencySymbol="€"/></span>
            </div>
            <form action="CheckoutServlet" method="get">
              <button type="submit" class="checkout-btn">CHECKOUT</button>
            </form>
            <div class="account-links">
              <span>Hai già un account? <a href="LoginPage.jsp">Accedi</a></span>
              <span>Non hai ancora un account? <a href="Registration.jsp">Unisciti a noi</a></span>
            </div>
            <div class="extra-info">
              <i class="fas fa-shield-alt"></i>
              <span>RESI SENZA RISCHI ENTRO 60 GIORNI</span>
            </div>
            <div class="payment-methods">
              <i class="fab fa-cc-amex"></i>
              <i class="fab fa-cc-visa"></i>
              <i class="fab fa-cc-discover"></i>
              <i class="fab fa-cc-paypal"></i>
              <i class="fab fa-google-pay"></i>
              <i class="fab fa-apple-pay"></i>
            </div>
          </div>
        </div>
      </c:otherwise>
    </c:choose>
  </div>

  <jsp:include page="footer.jsp"/>
  <script src="js/cart.js" defer></script>
  <script src="js/navbar.js" defer></script>
</body>
</html>
