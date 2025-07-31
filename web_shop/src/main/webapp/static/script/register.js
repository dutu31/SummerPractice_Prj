const registerService = (function() {


    let form;
    let usernameInput;
    let passwordInput;
    let confirmInput;
    let registerBtn;
    let strengthBar;
    let strengthText;
    const forbiddenChars = /[?!,^]/;
    const rules = {
        length: {regex: /.{8,}/, element: null},
        capital: {regex: /[A-Z]/, element: null},
        digit: {regex: /\d/, element: null},
        special: {regex: /[!@#$%^&*(),.?":{}|<>]/, element: null}
    };

    function showError(element, message) {
        element.textContent = message;
        element.style.display = 'block';
    }

    function clearError(element) {
        element.textContent = '';
        element.style.display = 'none';
    }

    function updateStrengthMeter(validRules) {
        strengthBar.className = 'strength-bar';
        strengthText.textContent = 'Weak';

        if (validRules === 0) {
            return;
        } else if (validRules === 1) {
            strengthBar.classList.add('weak');
            strengthText.textContent = 'Weak';
        } else if (validRules > 1 && validRules <= 3) {
            strengthBar.classList.add('medium');
            strengthText.textContent = 'Medium';
        } else if (validRules === 4) {
            strengthBar.classList.add('strong');
            strengthText.textContent = 'Strong';
        }
    }

    function validatePassword(password) {
        let validRules = 0;


        for (const key in rules) {
            rules[key].element.classList.remove('valid');
        }


        if (rules.length.regex.test(password)) {
            rules.length.element.classList.add('valid');
            validRules++;
        }
        if (rules.capital.regex.test(password)) {
            rules.capital.element.classList.add('valid');
            validRules++;
        }
        if (rules.digit.regex.test(password)) {
            rules.digit.element.classList.add('valid');
            validRules++;
        }
        if (rules.special.regex.test(password)) {
            rules.special.element.classList.add('valid');
            validRules++;
        }

        updateStrengthMeter(validRules);
        return validRules === 4;
    }

    function validateForm() {
        const usernameValid = usernameInput.value.length > 0 &&
            usernameInput.value.length <= 30 &&
            !forbiddenChars.test(usernameInput.value);

        let passwordValid = true;
        for (const key in rules) {
            if (rules.hasOwnProperty(key)) {
                if (!rules[key].regex.test(passwordInput.value)) {
                    passwordValid = false;
                    break;
                }
            }
        }

        const confirmValid = confirmInput.value === passwordInput.value;

        registerBtn.disabled = !(usernameValid && passwordValid && confirmValid);
    }

    function handleUsernameInput() {
        const username = usernameInput.value;
        const errorElement = document.getElementById('username-error');

        if (username.length > 30) {
            showError(errorElement, "Username cannot exceed 30 characters");
        } else if (forbiddenChars.test(username)) {
            showError(errorElement, "Username contains invalid characters");
        } else {
            clearError(errorElement);
        }
        validateForm();
    }

    function handlePasswordInput() {
        const password = passwordInput.value;
        validatePassword(password);
        validateForm();
    }

    function handleConfirmInput() {
        const errorElement = document.getElementById('confirm-error');

        if (confirmInput.value !== passwordInput.value) {
            showError(errorElement, "Passwords do not match");
        } else {
            clearError(errorElement);
        }
        validateForm();
    }

    function handleFormSubmit(event) {
        if (registerBtn.disabled) {
            event.preventDefault();
        }
    }


    function init() {
        form = document.getElementById('registrationForm');
        if (!form) return;
        usernameInput = document.getElementById('username');
        passwordInput = document.getElementById('password');
        confirmInput = document.getElementById('confirm-password');
        registerBtn = document.getElementById('register-btn');
        strengthBar = document.getElementById('strength-bar');
        strengthText = document.getElementById('strength-text');

        // Initialize rule elements
        rules.length.element = document.getElementById('rule-length');
        rules.capital.element = document.getElementById('rule-capital');
        rules.digit.element = document.getElementById('rule-digit');
        rules.special.element = document.getElementById('rule-special');

        // Add event listeners
        usernameInput.addEventListener('input', handleUsernameInput);
        passwordInput.addEventListener('input', handlePasswordInput);
        confirmInput.addEventListener('input', handleConfirmInput);
        form.addEventListener('submit', handleFormSubmit);

        const togglePasswordBtn = document.getElementById('toggle-password');
        const toggleConfirmBtn = document.getElementById('toggle-confirm');

        togglePasswordBtn.addEventListener('click', function () {
            const type = passwordInput.type === 'password' ? 'text' : 'password';
            passwordInput.type = type;
            togglePasswordBtn.textContent = type === 'password' ? 'Show' : 'Hide';
        });

        toggleConfirmBtn.addEventListener('click', function () {
            const confirmType = confirmInput.type === 'password' ? 'text' : 'password';
            confirmInput.type = confirmType;
            toggleConfirmBtn.textContent = confirmType === 'password' ? 'Show' : 'Hide';
        });


        validateForm();
    }


    return {
        init: init
    };

})();


document.addEventListener('DOMContentLoaded', function() {
    registerService.init();
});