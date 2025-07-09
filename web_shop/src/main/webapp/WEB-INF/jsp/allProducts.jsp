<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%--
  Created by IntelliJ IDEA.
  User: dutu
  Date: 7/8/25
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- TODO:sort by price implement !-->
<div class="sort-container">
    <label for="sortSelect">SortPrice</label>
</div>
<div class="products-grid">
    <c:forEach items="${products}" var="product">
            <div class="product-card">
                <img src="${product.imageURL}" alt="${product.title}" width="200"/>
                <div class="content">
                <h2>${product.title}</h2>
                <p class="description">${product.description}</p>
                <p class="price">${product.price} RON </p>
                </div>
            </div>
        </c:forEach>
</div>
