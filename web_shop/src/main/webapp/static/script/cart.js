const cartService=(function () {

    let isAuthenticated=false;
    function setAuthenticated(value) {
        isAuthenticated = value;
        if (isAuthenticated) {
            mergeLocalCartToServer();
        }
    }

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
        fetch('/cart/page')
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

    function showBanner(message,type) {
        const banner = document.getElementById('cart-banner-message');
        if (banner) {
            banner.textContent = message;

            if (type === 'error') {
                banner.style.backgroundColor = '#dc3545';
            } else if (type === 'success') {
                banner.style.backgroundColor = '#28a745';
            } else {
                banner.style.backgroundColor = '#333';
            }

            banner.style.display = 'block';
            banner.style.opacity = '1';
            banner.style.transform = 'translateY(0)';

            setTimeout(function() {
                banner.style.opacity = '0';
                banner.style.transform = 'translateY(-20px)';
            }, 2000);

            setTimeout(function() {
                banner.style.display = 'none';
                banner.textContent = '';
            }, 2500);
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


        if (isNaN(quantity) || quantity < 1) {
            showBanner("Invalid quantity request!",'error')
            return;
        }

        if (quantity > stock) {
            showBanner("Quantity required too high!",'error')
            return;
        }

        const product = { id, title, price, quantity,stock,image };
        addToCart(product);

        showBanner(`${title} added to cart (${quantity} pcs).`,'success');
    }

    function addToCart(product) {
        if (!isAuthenticated) {

            let cart = JSON.parse(localStorage.getItem('cart') || '[]');
            let existing = cart.find(item => item.id === product.id);

            if (existing) {
                existing.quantity += product.quantity;
            } else {
                cart.push(product);
            }

            localStorage.setItem('cart', JSON.stringify(cart));
            updateCartCount();
        } else {
            fetch('/cart/add', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    productId: product.id,
                    title: product.title,
                    price: product.price,
                    quantity: product.quantity,
                    imageUrl: product.image
                })
            }).then(res => {
                if (res.ok) {
                    updateCartCountFromServer();
                }
            });
        }
    }

    function updateCartCount() {
        if (!isAuthenticated) {
            let cart = JSON.parse(localStorage.getItem('cart') || '[]');
            let count = cart.reduce((sum, item) => sum + item.quantity, 0);
            const countSpan = document.getElementById('cart-count');
            if (countSpan) countSpan.textContent = count.toString();
        } else {
            updateCartCountFromServer();
        }
    }

    function updateCartCountFromServer() {
        fetch('/cart')
            .then(res => res.json())
            .then(cartDTO => {
                let count = 0;
                if (cartDTO.items) {
                    count = cartDTO.items.reduce((sum, item) => sum + item.quantity, 0);
                }
                const countSpan = document.getElementById('cart-count');
                if (countSpan) countSpan.textContent = count.toString();
            });
    }

    function renderCartPage() {
        if (!isAuthenticated) {
            renderLocalCart();
        } else {
            renderServerCart();
        }
    }

    function renderLocalCart() {
        let cart = JSON.parse(localStorage.getItem('cart') || '[]');
        renderCartItems(cart);
    }

    function renderServerCart() {
        fetch('/cart')
            .then(res => res.json())
            .then(cartDTO => {
                renderCartItems(cartDTO.items || []);
            });
    }

    function renderCartItems(items) {
        let tbody = document.querySelector('#cart-table tbody');
        tbody.innerHTML = '';
        let total = 0;

        for (let item of items) {
            const imgSrc=item.imageUrl || item.image;
            let row = document.createElement('tr');
            row.innerHTML =
                `<td><a href='/product/${item.productId}/details' class='product-link'>
                    <img src='${imgSrc}' alt='${item.title}' width='50'>
                 </a></td>` +
                `<td>${item.title}</td>` +
                `<td>${item.price} RON</td>` +
                `<td>
                    <button class='decrease-btn' data-id='${item.productId}'>-</button> 
                    <span class='item-qty'>${item.quantity}</span> 
                    <button class='increase-btn' data-id='${item.productId}'>+</button>
                 </td>` +
                `<td>${item.quantity}</td>` +
                `<td>${(item.price * item.quantity).toFixed(2)} RON</td>` +
                `<td><button class="remove-btn" data-id="${item.productId}">Remove</button></td>`;

            tbody.appendChild(row);
            total += item.price * item.quantity;
        }

        const totalEl = document.getElementById('cart-total');
        if (totalEl) totalEl.textContent = total.toFixed(2) + " RON";

        const deliveryFee = 25;
        const grandTotal = total + deliveryFee;
        const grandTotalEl = document.getElementById('grand-total');
        if (grandTotalEl) grandTotalEl.textContent = "TOTAL GENERAL: " + grandTotal.toFixed(2) + " RON";

        attachCartEventHandlers();
    }

    function attachCartEventHandlers() {
        document.querySelectorAll('.increase-btn').forEach(btn => {
            btn.addEventListener('click', handleIncrease);
        });

        document.querySelectorAll('.decrease-btn').forEach(btn => {
            btn.addEventListener('click', handleDecrease);
        });

        document.querySelectorAll('.remove-btn').forEach(btn => {
            btn.addEventListener('click', handleRemoveItem);
        });

        const clearBtn = document.getElementById('clear-cart');
        if (clearBtn) {
            clearBtn.removeEventListener('click', handleClearCart);
            clearBtn.addEventListener('click', handleClearCart);
        }
    }



    function handleRemoveItem(event) {
        const id = event.target.getAttribute('data-id');
        removeItem(id);
    }

    function removeItem(id) {
        if (!isAuthenticated) {
            let cart = JSON.parse(localStorage.getItem('cart') || '[]');
            cart = cart.filter(item => item.id !== id);
            localStorage.setItem('cart', JSON.stringify(cart));
            renderLocalCart();
            updateCartCount();
        } else {
            fetch('/cart/remove', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ productId: id })
            }).then(res => {
                if (res.ok) {
                    renderServerCart();
                    updateCartCountFromServer();
                }
            });
        }
    }

    function handleClearCart() {
        if (!isAuthenticated) {
            localStorage.removeItem('cart');
            renderLocalCart();
            updateCartCount();
        } else {
            fetch('/cart/clear', {
                method: 'POST'
            }).then(res => {
                if (res.ok) {
                    renderServerCart();
                    updateCartCountFromServer();
                }
            });
        }
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
        if (!isAuthenticated) {
            let cart = JSON.parse(localStorage.getItem('cart') || '[]');
            for (let i = 0; i < cart.length; i++) {
                if (cart[i].id === id) {
                    let newQty = cart[i].quantity + delta;
                    if (newQty <= 0) {
                        cart.splice(i, 1);
                    } else if (newQty <= cart[i].stock) {
                        cart[i].quantity = newQty;
                    } else {
                        alert("Can't add more than stock!");
                    }
                    break;
                }
            }
            localStorage.setItem('cart', JSON.stringify(cart));
            renderLocalCart();
            updateCartCount();
        } else {
            //modify quantity with fetch
            fetch('/cart/updateQuantity', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ productId: id, delta: delta })
            }).then(res => {
                if (res.ok) {
                    renderServerCart();
                    updateCartCountFromServer();
                }
            });
        }
    }

    function mergeLocalCartToServer() {
        if (!isAuthenticated) return;

        const localCart = JSON.parse(localStorage.getItem('cart') || '[]');
        console.log('Merging local cart items:', localCart);
        if (localCart.length === 0) return;

        const cartDTOItems = localCart.map(item => ({
            productId: Number(item.id),
            title: item.title,
            price: Number(item.price),
            quantity: Number(item.quantity),
            imageUrl: item.image
        }));

        fetch('/cart/merge', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(cartDTOItems)
        })
            .then(res => {
                if (res.ok) {
                    localStorage.removeItem('cart');
                    updateCartCountFromServer();
                    loadCartFragment(); //if deleted the redirect will be on the main page not cart page
                } else {
                    console.error('Merge cart failed with status:', res.status);
                }
            })
            .catch(err => {
                console.error('Error during merge cart:', err);
            });
    }

    return {
        initCart,
        addToCart,
        mergeLocalCartToServer,
        setAuthenticated,
        showBanner
    };
})();
window.cartService=cartService;
document.addEventListener('DOMContentLoaded',cartService.initCart);



