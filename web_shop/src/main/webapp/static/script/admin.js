const adminModule = (function () {

    function loadAdminPage() {
        fetch('/admin')
            .then(function (response) {
                if (!response.ok) {
                    throw new Error('Can t load admin page');
                }
                return response.text();
            })
            .then(function (html) {
                updateMainContent(html);
                init();
                if (window.location.pathname !== '/admin') {
                    history.pushState({page: 'admin', html: html}, null, '/admin');
                }

            })
            .catch(function (error) {
                cartService.showBanner(error.message,"error");
            });
    }

    function updateMainContent(html) {
        const mainContent = document.getElementById('content');
        mainContent.innerHTML = html;
    }

    function init() {
        attachAddUserHandler();
        attachButtonHandlers();
        attachAddProductHandler();
        attachDeleteProductHandler();
        attachInitAddProductHandler();
        attachEditProductHandlers();
    }

    function attachAddUserHandler() {
        const btnAdd = document.getElementById("btn-add-user");
        if (!btnAdd) return;
        btnAdd.addEventListener("click", handleAddUser);
    }

    function handleAddUser() {
        const username = document.getElementById("input-username").value.trim();
        const mail = document.getElementById("input-mail").value.trim();
        const password = document.getElementById("input-password").value.trim();
        const role = document.getElementById("select-role").value;

        if (!username || !mail || !password) {
            cartService.showBanner("Complete all fields!","error");
            return;
        }

        const user = { username, mail, password, role };

        fetch("/admin/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user),
            credentials:'include'
        })
            .then(function (response) {
                if (!response.ok) {
                    return response.json().then(err => { throw new Error(err.message || "Add error."); });
                }
                return response.json();
            })
            .then(function () {
                adminModule.loadAdminPage();

            })
            .catch(function (error) {
                cartService.showBanner("Error: " + error.message, "error");
            });
    }

    function attachButtonHandlers() {
        const table = document.getElementById("admin-user-table");
        if (!table) return;

        table.addEventListener("click", function (event) {
            const target = event.target;
            const row = target.closest("tr");
            if (!row) return;
            const userId = row.getAttribute("data-user-id");

            if (target.classList.contains("delete-user")) {
                if (confirm("Are you sure you want to delete this user?")) {
                    handleDelete(userId);
                }
            }

            if (target.classList.contains("promote-user")) {
                if (confirm("Are you sure you want to promote this user to admin?")) {
                    handlePromote(userId);
                }
            }
        });
    }

    function handleDelete(userId) {
        fetch(`/admin/delete/${userId}`, {
            method: "DELETE"
        })
            .then(function (response) {
                if (!response.ok) {
                    return response.json().then(err => { throw new Error(err.message || "Delete error."); });
                }
                adminModule.loadAdminPage();

            })
            .catch(function (error) {
                cartService.showBanner("Error: " + error.message, "error");
            });
    }

    function handlePromote(userId) {
        fetch(`/admin/promote/${userId}`, {
            method: "POST"
        })
            .then(function (response) {
                if (!response.ok) {
                    return response.json().then(err => { throw new Error(err.message || "Promo error."); });
                }
                adminModule.loadAdminPage();
            })
            .catch(function (error) {
                cartService.showBanner("Error: " + error.message, "error");
            });
    }

    function attachInitAddProductHandler() {
        const btn = document.getElementById("go-to-add-product");
        if (!btn) return;
        btn.addEventListener("click", loadAddProductPage);
    }

    function loadAddProductPage() {
        fetch('/admin/product/addPage')
            .then(function (response) {
                if (!response.ok) throw new Error('Cannot load edit product page');
                return response.text();
            })
            .then(function (html) {
                updateMainContent(html);
                attachAddProductHandler();
            })
            .catch(function (error) {
                cartService.showBanner("Error: " + error.message, "error");
            });
    }



    function attachAddProductHandler() {
        const btn = document.getElementById("btn-add-product");
        if (!btn) return;
        btn.addEventListener("click", handleAddProduct);
        cartService.showBanner("Added to products",'success');
    }

    function handleAddProduct() {
        const title= document.getElementById("input-product-name").value.trim();
        const description = document.getElementById("input-product-description").value.trim();
        const price = parseFloat(document.getElementById("input-product-price").value);
        const categoryId = document.getElementById("input-product-category").value;
        const imageURL = document.getElementById("input-product-imageURL").value.trim();
        const longDescription=document.getElementById("input-product-longDescription").value.trim();
        const quantity=document.getElementById("input-product-quantity").value;

        if (!title || !description || isNaN(price) || !categoryId || !imageURL || !longDescription || isNaN(quantity)) {
            cartService.showBanner("Complete all fields for the product!", "error");
            return;
        }

        const product = { title, description, price, categoryId, imageURL,quantity, longDescription };

        fetch("/admin/product/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(product),
            credentials: 'include'
        })
            .then(function (response) {
                if (!response.ok) {
                    return response.json().then(err => { throw new Error(err.message || "Add product error."); });
                }
                return response.json();
            })
            .then(function () {
                cartService.showBanner("Product added successfully!", "success");
                adminModule.loadAdminPage();
            })
            .catch(function (error) {
                cartService.showBanner("Error: " + error.message, "error");
            });
    }




    function attachEditProductHandlers() {
        const buttons = document.querySelectorAll(".edit-product-button");
        buttons.forEach(button => {
            button.addEventListener("click", () => {
                const productId = button.getAttribute("data-id");
                loadEditProductPage(productId);
            });
        });
    }

    function loadEditProductPage(productId) {
        fetch(`/admin/product/edit/${productId}`)
            .then(response => {
                if (!response.ok) throw new Error("Nu s-a putut încărca pagina de editare");
                return response.text();
            })
            .then(html => {
                updateMainContent(html);
                attachEditProductFormHandler();
            })
            .catch(err => {
                cartService.showBanner("Error: " + err.message, "error");
            });
    }

    function attachEditProductFormHandler() {
        const form = document.getElementById("form-edit-product");
        if (!form) return;

        form.addEventListener("submit", function (event) {
            event.preventDefault();
            const productId = form.getAttribute("data-product-id");
            const price = parseFloat(document.getElementById("input-product-price").value);
            const quantity = parseInt(document.getElementById("input-product-quantity").value);

            if (isNaN(price) || isNaN(quantity)) {
                cartService.showBanner("Completează corect prețul și cantitatea!", "error");
                return;
            }

            const productUpdate = { price, quantity };

            fetch(`/admin/product/edit/${productId}`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(productUpdate),
                credentials: 'include'
            })
                .then(response => {
                    if (!response.ok) {
                        return response.json().then(err => { throw new Error(err.message || "Eroare la salvare."); });
                    }
                    return response.json();
                })
                .then(() => {
                    cartService.showBanner("Produs actualizat cu succes!", "success");
                    adminModule.loadAdminPage();
                })
                .catch(err => {
                    cartService.showBanner("Error: " + err.message, "error");
                });
        });
    }




    function attachDeleteProductHandler() {
        const buttons = document.querySelectorAll(".delete-product-button");
        if (!buttons) return;

        for (let i = 0; i < buttons.length; i++) {
            buttons[i].addEventListener("click", handleDeleteProductClick);
        }
    }

    function handleDeleteProductClick() {
        const button = event.currentTarget;
        const productId = button.getAttribute("data-id");

        const confirmDelete = confirm("Are you sure you want to delete this product?");
        if (!confirmDelete) return;

        fetch("/admin/product/delete/" + productId, {
            method: "DELETE"
        })
            .then(handleDeleteResponse)
            .catch(handleDeleteError);
    }

    function handleDeleteResponse(response) {
        if (!response.ok) {
            response.json().then(function(error) {
                throw new Error(error.message || "Delete error");
            });
        } else {
            cartService.showBanner("Producted deleted!", "success");
            adminModule.loadAdminPage();
        }
    }

    function handleDeleteError(error) {
        cartService.showBanner("Error: " + error.message, "error");
    }






    return {
        loadAdminPage: loadAdminPage,
        init:init
    };
})();
window.adminModule=adminModule;

