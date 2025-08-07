<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dutu
  Date: 8/1/25
  Time: 3:09 PM
  To change this template use File | Settings | File Templates.
--%>
<link href="${pageContext.request.contextPath}/static/css/denied.css" rel="stylesheet" type="text/css" />
<div class="access-denied-container">
    <div class="access-denied-card">
        <h1 class="access-denied-title">403 - Access Denied!</h1>
        <p class="access-denied-message">Contact Staff if mistake</p>
        <a href="${pageContext.request.contextPath}/" class="btn-back-home">Back to homepage</a>
    </div>
</div>
