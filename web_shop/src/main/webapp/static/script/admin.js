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

    return {
        loadAdminPage: loadAdminPage,
        init:init
    };
})();
window.adminModule=adminModule;

