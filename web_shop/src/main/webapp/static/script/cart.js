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
        let quantity = parseInt(qtyInput.value) || 1;
        const image=btn.dataset.image;
        const stock=parseInt(btn.dataset.stock);

        if(quantity>stock) {
            quantity=stock;
            qtyInput.value=stock;
            alert('Quantity required too high. Auto-adjusting...');
        }

        const product = { id, title, price, quantity,stock,image };
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
                "<td>" + "<a href='/product/" + item.id + "/details' class='product-link'>" +
                "<img src='" + item.image + "' alt='" + item.title + "' width='50'></a></td>" +
                "<td>" + item.title + "</td>" +
                "<td>" + item.price + " RON</td>" +
                "<td>" +
                "<button class='decrease-btn' data-id='" + item.id + "'>-</button> " +
                "<span class='item-qty'>" + item.quantity + "</span> " +
                "<button class='increase-btn' data-id='" + item.id + "'>+</button>" +
                "</td>" +
                "<td>" + item.quantity + "</td>" +
                "<td>" + (item.price * item.quantity) + " RON</td>" +
                "<td><button class=\"remove-btn\" data-id=\"" + item.id + "\">Remove</button></td>";

            tbody.appendChild(row);
            total += item.price * item.quantity;
        }

        let totalEl = document.getElementById('cart-total');
        if (totalEl) {
            totalEl.textContent =  + total + " RON";
        }

        let deliveryFee=25;
        let grandTotal=total+deliveryFee;
        let grandTotalEl = document.getElementById('grand-total');
        if (grandTotalEl) {
            grandTotalEl.textContent = "TOTAL GENERAL: " + grandTotal.toFixed(2) + " RON";
        }

        document.querySelectorAll('.increase-btn').forEach(btn => {
            btn.addEventListener('click', handleIncrease);
        });

        document.querySelectorAll('.decrease-btn').forEach(btn => {
            btn.addEventListener('click', handleDecrease);
        });


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


    function handleIncrease(event) {
        const id = event.target.getAttribute('data-id');
        updateQuantity(id, 1);
    }

    function handleDecrease(event) {
        const id = event.target.getAttribute('data-id');
        updateQuantity(id, -1);
    }

    function updateQuantity(id, delta) {     //delta is the difference that was added or extracted from a product quantity
        let cart = JSON.parse(localStorage.getItem('cart')) || [];
        for (let i = 0; i < cart.length; i++) {
            if (cart[i].id === id) {
                let newQty=cart[i].quantity +delta;
                if (newQty > cart[i].stock) {
                    newQty = cart[i].stock;
                    alert(`Can't add more!`);
                }
                if (newQty <= 0) {
                    cart.splice(i, 1);
                }else {
                    cart[i].quantity=newQty;
                }
                break;
            }
        }
        localStorage.setItem('cart', JSON.stringify(cart));
        renderCartPage();
        updateCartCount();
    }


    return {
        initCart: initCart
    }
})();
window.cartService=cartService;
document.addEventListener('DOMContentLoaded',cartService.initCart);
