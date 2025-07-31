<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link href="${contextPath}/static/css/register.css" rel="stylesheet" type="text/css">
<div class="register-wrapper" id="register-content">
    <div class="register-card">
        <form id="registrationForm"
              action="/register/perform_register"
              method="post">
            <div class="form-header">
                <h2>Create Account</h2>
                <p>Join us today!</p>
            </div>


            <div class="form-group">
                <label for="username">Username</label>
                <i class="fas fa-user input-icon"></i>
                <input type="text" id="username" name="username"
                       placeholder="Enter username (max 30 chars)" maxlength="30" required>
                <div class="error-message" id="username-error"></div>
            </div>


            <div class="form-group">
                <label for="email">Email</label>
                <i class="fas fa-envelope input-icon"></i>
                <input type="email" id="email" name="mail"
                       placeholder="Enter your email" required>
                <div class="error-message" id="email-error"></div>
            </div>


            <div class="form-group">
                <label for="password">Password</label>
                <i class="fas fa-lock input-icon"></i>
                <div class="password-wrapper">
                    <input type="password" id="password" name="password"
                           placeholder="Enter password" required>
                    <button type="button" id="toggle-password" class="show-btn">Show</button>
                </div>
                <div class="password-strength">
                    <div class="strength-meter">
                        <div class="strength-bar" id="strength-bar"></div>
                    </div>
                    <span class="strength-text" id="strength-text">Weak</span>
                </div>
                <div class="password-rules">
                    <ul>
                        <li id="rule-length" class="rule">Minimum 8 characters</li>
                        <li id="rule-capital" class="rule">At least 1 capital letter</li>
                        <li id="rule-digit" class="rule">At least 1 digit</li>
                        <li id="rule-special" class="rule">At least 1 special character</li>
                    </ul>
                </div>
            </div>


            <div class="form-group">
                <label for="confirm-password">Confirm Password</label>
                <i class="fas fa-lock input-icon"></i>
                <div class="password-wrapper">
                    <input type="password" id="confirm-password"
                           placeholder="Confirm password" required>
                    <button type="button" id="toggle-confirm">Show</button>
                </div>
                <div class="error-message" id="confirm-error"></div>
            </div>

            <button type="submit" id="register-btn" disabled>Create Account</button>

            <div class="login-section">
                <p>Already have an account?</p>
                <a href="${contextPath}/login" class="btn-login">Sign In</a>
            </div>
            <div class="error-message" id="form-error"></div>
        </form>
    </div>
</div>

<script type="text/javascript" src="${contextPath}/static/script/register.js"></script>
