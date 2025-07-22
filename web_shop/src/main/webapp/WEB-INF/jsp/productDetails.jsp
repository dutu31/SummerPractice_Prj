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
        <h2>${product.title}</h2>
        <p>${product.longDescription}</p>
        <p><strong>Price:</strong> ${product.price} RON</p>
        <p><strong>In stock:</strong> ${product.quantity}</p>

      <!--  <form id="addToCartForm"
            data-id="${product.id}"
            data-title="${product.title}"
            data-price="${product.price}">
            <input type="number" id="quantity" name="quantity" value="1" min="1" max="${product.quantity}">
            <button type="submit" id="add-to-cart">Add to Cart</button>
        </form>

        <p id="cartMessage" class="cart-message"></p>
        <a id="continueShopping" href="${pageContext.request.contextPath}/">Continue Shopping</a>   !-->

        <div class="add-to-cart-form">
            <input type="number" id="quantity" value="1" min="1" max="${product.quantity}">
            <button type="button" id="add-to-cart"
                    data-id="${product.id}"
                    data-title="${product.title}"
                    data-price="${product.price}"
                    data-image="${product.imageURL}">
                Add to Cart
            </button>
        </div>
        <p id="cartMessage"></p>


    <div class="product-image">
        <img src="${product.imageURL}" alt="${product.title}">
    </div>
</div>


</div>