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
    <link href="${contextPath}/static/css/productDetails.css" rel="stylesheet" type="text/css">
    <link href="${contextPath}/static/css/cart.css" rel="stylesheet" type="text/css">
    <link href="${contextPath}/static/css/login.css" rel="stylesheet" type="text/css">
    <link href="${contextPath}/static/css/register.css" rel="stylesheet" type="text/css">
    <link href="${contextPath}/static/css/denied.css" rel="stylesheet" type="text/css">
    <link href="${contextPath}/static/css/admin.css" rel="stylesheet" type="text/css">
    <!-- font and icons !-->
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <title>Quick Phone</title>
</head>

<body>
    <div id="intro-overlay" class="video-overlay">
        <iframe
            src="https://www.youtube.com/embed/eDqfg_LexCQ?autoplay=1&mute=1&controls=0&start=10"
            frameborder="0"
            allow="autoplay; encrypted-media"
            allowfullscreen>
        </iframe>
    <button id="close-intro" class="video-overlay__button">See page</button>
    </div>
    <div id="main-content" class="site-content">
    <header>
        <h1>QuickPhone</h1>
        <nav>
            <div class="nav-left">
                <ul>
                    <li>
                        <a href="#" class="category-link" data-id="ALL">All</a>
                    </li>
                    <c:forEach items="${categories}" var="category">
                        <li>
                            <a href="#" class="category-link" data-id="${category.id}">${category.name}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div class="nav-right">


                <a href="#" id="cart-link" style="display: inline-flex; align-items: center;">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                    <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"></path>

                </svg>
                <span id="cart-count" style="margin-left: 5px;">0</span>
            </a>

                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal != null}">
                        <div class="nav-user-info">
            <span class="nav-greeting">
                <i class="fas fa-user-circle"></i>
                Hello, ${pageContext.request.userPrincipal.name}!
            </span>
                            <span>|</span>
                            <a href="${pageContext.request.contextPath}/perform_logout" class="nav-link logout-link">Logout</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <a href="#" class="nav-link" data-url="${pageContext.request.contextPath}/login">Login</a>
                    </c:otherwise>
                </c:choose>
                <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
                    <a href="#" id="admin-link" class="nav-link" style="margin-left: 15px;">Admin</a>
                </c:if>




            </div>

        </nav>
    </header>
        <div id="cart-banner-message" class="cart-banner-message"></div>


    <main id="content">
        <!-- this is where the products are shown !-->
    </main>

    <footer>
        <p>&copy; QuickPhone 2025</p>
    </footer>
    </div>

    <script type="text/javascript" src="${contextPath}/static/script/admin.js"></script>
    <script type="text/javascript" src="${contextPath}/static/script/main.js"></script>
    <script type="text/javascript" src="${contextPath}/static/script/cart.js"></script>
    <script type="text/javascript" src="${contextPath}/static/script/login.js"></script>
    <script type="text/javascript" src="${contextPath}/static/script/register.js"></script>

</body>
</html>
