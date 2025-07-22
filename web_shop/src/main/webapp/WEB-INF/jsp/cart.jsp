<%--
  Created by IntelliJ IDEA.
  User: dutu
  Date: 7/21/25
  Time: 10:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="cart-details">
    <h1>Your Shopping List</h1>
    <table id="cart-table">
        <thead>
        <tr>
            <th>Title</th>
            <th>Price</th>
            <th>Qty</th>
            <th>Total</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
    <p id="cartMessage" class="cart-message"></p>
    <button id="clear-cart">Empty Cart</button>
</div>