<jsp:useBean id="product" scope="request" type="com.createq.web_shop.dto.ProductDTO"/>
<%--
  Created by IntelliJ IDEA.
  User: dutu
  Date: 8/6/25
  Time: 12:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div id="edit-product-form">
    <h2>Add New Product</h2>

    <label>Name:
        <input type="text" id="input-product-name" value="${product.title}" required autocomplete="off" />
    </label>

    <label>Description:
        <input type="text" id="input-product-description" value="${product.description}" required autocomplete="off" />
    </label>

    <label>Detailed Description:
        <input type="text" id="input-product-longDescription" value="${product.longDescription}" required autocomplete="off" />
    </label>

    <label>Price:
        <input type="number" id="input-product-price" step="0.01" value="${product.price}" required />
    </label>

    <label>Category ID:
        <input type="number" id="input-product-category" value="${product.categoryId}" required />
    </label>

    <label>Quantity:
        <input type="number" id="input-product-quantity" value="${product.quantity}" required />
    </label>

    <label>Image URL:
        <input type="text" id="input-product-imageURL" value="${product.imageURL}" required autocomplete="off" />
    </label>

    <button id="btn-add-product" type="button" class="admin-button admin-button-submit">
        Add Product
    </button>

    <a href="${pageContext.request.contextPath}/admin" class="admin-button btn-back-admin" id="btn-back-add-product">Back to products</a>
</div>

