<%--
  Created by IntelliJ IDEA.
  User: dutu
  Date: 7/21/25
  Time: 10:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="cart-container">
    <div class="cart-header">
        <h1 class="cart-title">Your Shopping Cart</h1>
        <div class="cart-steps">
            <span class="step active">1. Cart</span>
            <span class="step">2. Delivery</span>
            <span class="step">3. Payment</span>
        </div>
    </div>

    <div class="cart-content">
        <div class="cart-items-wrapper">
        <table id="cart-table" class="cart-table">
            <thead>
            <tr class="table-header">
                <th class="product-image">Product</th>
                <th class="product-info">Details</th>
                <th class="product-price">Price</th>
                <th class="product-quantity">Quantity</th>
                <th class="product-actions"></th>
                <th class="product-total">Total</th>

            </tr>
            </thead>
            <tbody class="cart-items">
            <!-- Items will be populated by JavaScript -->
            </tbody>
        </table>
        </div>

        <div class="cart-summary">
            <div class="summary-card">
                <h3>Order Summary</h3>
                <div class="summary-row">
                    <span>Subtotal</span>
                    <span id="cart-total"></span>
                </div>
                <div class="summary-row">
                    <span>Delivery Fee</span>
                    <span>25.00 RON</span>
                </div>
                <div class="summary-divider"></div>
                <div class="summary-row grand-total">
                    <span id="grand-total"></span>
                </div>
                <button id="checkout" class="checkout-btn">Proceed to Checkout</button>
                <div class="payment-methods">
                    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Visa_Inc._logo.svg/2560px-Visa_Inc._logo.svg.png" alt="Visa" style="height: 20px">
                    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Mastercard-logo.svg/1280px-Mastercard-logo.svg.png" alt="Mastercard" style="height: 20px">
                    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/PayPal.svg/1280px-PayPal.svg.png" alt="PayPal" style="height: 20px">
                </div>
            </div>
        </div>
    </div>

    <div class="cart-footer">
        <button id="clear-cart" class="clear-cart-btn">
            <i class="fas fa-trash"></i> Empty Cart
        </button>
        <a id="continueShopping" href="${pageContext.request.contextPath}/" class="continue-shopping">
            <i class="fas fa-arrow-left"></i> Continue Shopping
        </a>
    </div>

    <p id="cartMessage" class="cart-message"></p>
</div>
<!-- TO DO: confirmation pop-up !-->

<!--<div id="confirm-modal" class="confirm-modal">
    <div class="modal-box">
        <p id="confirm-message">Are you sure?</p>
        <div class="modal-buttons">
            <button id="confirm-yes" class="modal-btn yes">Yes</button>
            <button id="confirm-no" class="modal-btn no">No</button>
        </div>
    </div>
</div>  !-->
