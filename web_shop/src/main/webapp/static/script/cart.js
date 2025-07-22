const cartService=(function () {
    function initCart() {
        initAddToCart();
        updateCartCount();
        initCartLink();
    }

    function initCartLink() {
        const cartLink = document.getElementById('cart-link');
        if (cartLink) {
            cartLink.addEventListener('click', function (event) {
                event.preventDefault();
                loadCartFragment();
            });
        }
    }

    function loadCartFragment() {
        fetch('/cart')
            .then(function (response) {
                return response.text();
            })
            .then(function (html) {
                const content = document.getElementById('content');
                content.innerHTML = html;
                renderCartPage();
            });
    }

    function initAddToCart() {
        console.log(" initAddToCart used");
        const container=document.getElementById('content');
        if(container) {
            container.removeEventListener('click',handleAddToCartClick);
            container.addEventListener('click',handleAddToCartClick);
            console.log("handler attached on #content");
        }
    }

    function handleAddToCartClick(event) {
        const btn=event.target.closest('#add-to-cart');
        if(!btn) {
            return;
        }
        console.log("click Add To Cart button");
        event.preventDefault();
        const id = btn.dataset.id;
        const title = btn.dataset.title;
        let price = parseFloat(btn.dataset.price);
        const qtyInput = document.getElementById('quantity');
        const quantity = parseInt(qtyInput.value) || 1;


        const product = { id, title, price, quantity };
        addToCart(product);
        const message = document.getElementById('cartMessage');
        if (message) {
            message.textContent = `${title} added to cart (${quantity} pcs).`;
            setTimeout(() => message.textContent = '', 2000);
        }
    }

    function addToCart(product) {
        let cart = JSON.parse(localStorage.getItem('cart') || '[]');
        let existing = null;
        for (let i = 0; i < cart.length; i++) {
            if (cart[i].id === product.id) {
                existing = cart[i];
                break;
            }
        }

        if (existing) {
            existing.quantity += product.quantity;
        } else {
            cart.push(product);
        }

        localStorage.setItem('cart', JSON.stringify(cart));
        updateCartCount();
    }

    function updateCartCount() {
        let cart = JSON.parse(localStorage.getItem('cart')) || [];
        let count = 0;
        for (let i = 0; i < cart.length; i++) {
            count += cart[i].quantity;
        }
        let countSpan = document.getElementById('cart-count');
        if (countSpan) {
            countSpan.textContent = count.toString();
        }
    }

    function renderCartPage() {
        let cart = JSON.parse(localStorage.getItem('cart')) || [];
        let tbody = document.querySelector('#cart-table tbody');
        tbody.innerHTML = '';

        let total = 0;

        for (let i = 0; i < cart.length; i++) {
            let item = cart[i];
            let row = document.createElement('tr');

            row.innerHTML =
                "<td>" + item.title + "</td>" +
                "<td>" + item.price + " RON</td>" +
                "<td>" + item.quantity + "</td>" +
                "<td>" + (item.price * item.quantity) + " RON</td>" +
                "<td><button class=\"remove-btn\" data-id=\"" + item.id + "\">Remove</button></td>";

            tbody.appendChild(row);
            total += item.price * item.quantity;
        }

        let totalEl = document.getElementById('cart-total');
        if (totalEl) {
            totalEl.textContent = "Total: " + total + " RON";
        }

        let removeButtons = document.querySelectorAll('.remove-btn');
        for (let j = 0; j < removeButtons.length; j++) {
            removeButtons[j].addEventListener('click', handleRemoveItem);
        }

        let clearBtn = document.getElementById('clear-cart');
        if (clearBtn) {
            clearBtn.addEventListener('click', handleClearCart);
        }
    }

    function handleRemoveItem(event) {
        const id = event.target.getAttribute('data-id');
        removeItem(id);
    }

    function removeItem(id) {
        let cart = JSON.parse(localStorage.getItem('cart')) || [];
        let newCart = [];

        for (let i = 0; i < cart.length; i++) {
            if (cart[i].id !== id) {
                newCart.push(cart[i]);
            }
        }

        localStorage.setItem('cart', JSON.stringify(newCart));
        renderCartPage();
        updateCartCount();
    }

    function handleClearCart() {
        localStorage.removeItem('cart');
        renderCartPage();
        updateCartCount();
    }

    return {
        initCart: initCart
    }
})();
window.cartService=cartService;
document.addEventListener('DOMContentLoaded',cartService.initCart);
