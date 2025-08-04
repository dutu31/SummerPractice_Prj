const loginService = (function() {
    function fetchLoginContent() {
        fetch('/login', {
            headers: {
                'X-Requested-With': 'XMLHttpRequest'
            }
        })
            .then(function(response) {
                if (!response.ok) throw new Error("Login page not found");
                return response.text();
            })
            .then(function(html) {
                document.getElementById('content').innerHTML = html;
                history.pushState({
                    type: 'login',
                    html: html,
                    title: 'Quick Phone - Login'
                }, null, '/login');
                document.title = 'Quick Phone - Login';
                initRegisterLink();
            })
            .catch(function(error) {
                console.error('Error loading login page:', error);
            });
    }




    function fetchRegisterContent() {
        fetch('/register/perform_register', {
            headers: {
                'X-Requested-With': 'XMLHttpRequest'
            }
        })
            .then(function(response) {
                if (!response.ok) throw new Error("Register page not found");
                return response.text();
            })
            .then(html => {
                if (html.includes('registrationForm')) {
                    document.getElementById('content').innerHTML = html;
                    history.pushState({
                        type: 'register',
                        html: html,
                        title: 'Quick Phone - Register'
                    }, null, '/register');
                    document.title = 'Quick Phone - Register';
                    if (window.registerService) {
                        registerService.init();
                    }
                } else {
                    window.location.href = '/register/perform_register';
                }
            })
            .catch(function(error) {
                console.error('Error loading register page:', error);
                window.location.href = '/register/perform_register';
            });
    }

    function handleRegisterLinkClick(event) {
        event.preventDefault();
        fetchRegisterContent();
    }

    function initRegisterLink() {
        const registerLink = document.getElementById('register-link');
        if (registerLink) {
            registerLink.addEventListener('click', handleRegisterLinkClick);
        }
    }

    function handleLoginClick(event) {
        event.preventDefault();
        fetchLoginContent();
    }

    function initLoginLink() {
        const loginLink = document.querySelector('.nav-link[data-url$="/login"]');
        if (loginLink) {
            loginLink.addEventListener('click', handleLoginClick);
        }
    }

    return {
        init: function() {
            initLoginLink();
            if (window.location.pathname === '/login') {
                initRegisterLink();
            }
        }
    };
})();

document.addEventListener('DOMContentLoaded', function() {
    loginService.init();
    if (window.categoryService) {
        categoryService.initPage();
    }
});