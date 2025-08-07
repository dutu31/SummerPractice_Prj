<%--
  Created by IntelliJ IDEA.
  User: dutu
  Date: 8/6/25
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div id="edit-product-form">
    <h2>Edit Product Quantity & Price</h2>
    <form id="form-edit-product" data-product-id="${product.id}">
        <label>Price:
            <input type="number" id="input-product-price" step="0.01" value="${product.price}" required />
        </label>
        <label>Quantity:
            <input type="number" id="input-product-quantity" value="${product.quantity}" required />
        </label>
        <button type="submit" class="admin-button admin-button-submit">Save Changes</button>
        <a href="${contextPath}/admin" class="admin-button btn-back-admin">Back to Products List</a>
    </form>
</div>
