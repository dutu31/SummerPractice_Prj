<%--
  Created by IntelliJ IDEA.
  User: dutu
  Date: 7/29/25
  Time: 2:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="login-wrapper">
    <div class="login-card">

        <form class="login-form" action="/perform_login" method="post">
            <div class="form-header">
                <h2>Welcome Back</h2>
                <p>Please enter your credentials to login</p>
            </div>

            <div class="form-group">
                <label for="username">Username</label>
                <i class="fas fa-user input-icon"></i>
                <input type="text" id="username" name="username" placeholder="Enter your username" required>
                <div class="error-message" id="username-error"></div>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <i class="fas fa-lock input-icon"></i>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
                <div class="error-message" id="password-error"></div>
            </div>

            <button type="submit" class="btn-submit">Sign In</button>

            <div class="register-section">
                <p>Don't have an account?</p>
                <a href="#"  id="register-link" class="btn-register">Create Account</a>
            </div>
        </form>
    </div>
</div>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">