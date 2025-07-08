<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%--
  Created by IntelliJ IDEA.
  User: dutu
  Date: 7/8/25
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <c:forEach items="${products}" var="product">
            <div>
                <h2>${product.title}</h2>
                <p>${product.description}</p>
                <p><strong>Price: </strong> ${product.price} RON </p>
                <img src="${product.imageURL}" alt="${product.title}" width="200"/>
            </div>
        </c:forEach>
</div>
