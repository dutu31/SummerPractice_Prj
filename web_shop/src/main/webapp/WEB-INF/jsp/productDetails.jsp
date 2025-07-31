<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: dutu
  Date: 7/14/25
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="product-details">
    <div class="product-info">
        <div class="product-info-box">
            <h2>${product.title}</h2>
            <p class="product-description">${product.longDescription}</p>

            <div class="price-section">
                <p><strong>Price:</strong> <span class="price-value">${product.price} RON</span></p>
                <p class="stock-info"><strong>In stock:</strong> ${product.quantity}</p>
            </div>
        </div>

        <div class="add-to-cart-container">
            <input type="number" class="quantity-input" id="quantity"
                   value="1" min="1" max="${product.quantity}">
            <button type="button" class="add-to-cart-button" id="add-to-cart"
                    data-id="${product.id}"
                    data-title="${product.title}"
                    data-price="${product.price}"
                    data-image="${product.imageURL}"
                    data-stock="${product.quantity}">
                Add to Cart
            </button>
        </div>


        <a id="continueShopping" href="${pageContext.request.contextPath}/">
            <i class="fas fa-arrow-left"></i> Continue Shopping
        </a>
    </div>

    <div class="product-image">
        <img src="${product.imageURL}" alt="${product.title}">
    </div>
</div>