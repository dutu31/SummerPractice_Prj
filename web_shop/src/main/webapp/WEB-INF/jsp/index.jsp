<%@taglib prefix="c" uri="jakarta.tags.core"%>
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
    <link href="<c:url value="/static/css/main.css"/>" rel="stylesheet" type="text/css">
    <title>Quick Phone</title>
</head>
<body>
    <header>
        <h1>QuickPhone</h1>
        <nav>
            <ul>
                <c:forEach items="${categories}" var="category">
                    <li>
                        <a href="#" class="category-link" data-id="${category.id}">${category.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </header>

    <main id="content">
        <!-- this is where the products are shown !-->
    </main>

    <footer>
        <p>&copy; QuickPhone 2025</p>
    </footer>

    <script type="text/javascript" src="<c:url value='/static/script/main.js' />"></script>
</body>
</html>
