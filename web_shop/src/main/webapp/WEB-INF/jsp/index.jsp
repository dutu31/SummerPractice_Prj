<%@taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%--
  Created by IntelliJ IDEA.
  User: dutu
  Date: 7/7/2025
  Time: 10:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${contextPath}/static/css/main.css" rel="stylesheet" type="text/css">
    <title>Quick Phone</title>
</head>
<body>
    <header>
        <h1>QuickPhone</h1>
        <nav>

            <ul>
                <li>
                    <a href="#" class="category-link" data-id="ALL">All</a>
                </li>
                <c:forEach items="${categories}" var="category">
                    <li>
                        <a href="#" class="category-link" data-id="${category.id}">${category.name}</a>
                    </li>
                </c:forEach>
                <li>
                    <a href="#" id="cart-link" style="display: inline-flex; align-items: center;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                            <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"></path>

                        </svg>
                        <span id="cart-count" style="margin-left: 5px;">0</span>
                    </a>
                </li>
            </ul>
        </nav>
    </header>

    <main id="content">
        <!-- this is where the products are shown !-->
    </main>

    <footer>
        <p>&copy; QuickPhone 2025</p>
    </footer>

    <script type="text/javascript" src="${contextPath}/static/script/main.js"></script>
    <script type="text/javascript" src="${contextPath}/static/script/cart.js"></script>
</body>
</html>
