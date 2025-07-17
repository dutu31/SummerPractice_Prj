const categoryService = (function () {

    function initPage() {
        const categoryLinks = document.querySelectorAll('.category-link');
        const sortSelect=document.getElementById('sortSelect');
        const urlParams=new URLSearchParams(window.location.search);
        const sortOrder=urlParams.get('sort');
        initCategoryLinks(categoryLinks);
        initSortSelect(sortSelect,sortOrder);
        initProductLinks();
        const path = window.location.pathname;
        if (path.startsWith("/product/")) {
            const productId = path.split("/")[2];
            fetchProductDetails(productId,false);
        } else if (path.startsWith("/category/")) {
            const categoryId = path.split("/")[2];
            fetchProductsByCategory(categoryId, sortOrder);
        } else if (path === "/products") {
            fetchProductsByCategory("ALL",sortOrder);
        } else {
            selectDefaultCategory(categoryLinks);
        }
    }




    function getProductsForCategory(event) {
        const categoryLinks = document.querySelectorAll('.category-link');
        event.preventDefault();
        const categoryId = event.currentTarget.getAttribute('data-id');
        console.log("Category ID:", categoryId);
        categoryLinks.forEach(link => link.classList.remove('active'));
        event.currentTarget.classList.add('active');
        const sortSelect=document.getElementById('sortSelect');
        const sortValue=sortSelect?sortSelect.value:'';
        fetchProductsByCategory(categoryId,sortValue);
    }

    function fetchProductsByCategory(categoryId,sortOrder) {
        let baseUrl;
        if(categoryId === "ALL") {
            baseUrl=`/products`;
        } else {
            baseUrl = `/category/${categoryId}/products`;
        }
        let url=baseUrl;
        if(sortOrder) {
            url = `${baseUrl}?sort=${sortOrder}`;
        }
        console.log("Fetching products from:", url);
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error("No products for this category!");
                }
                return response.text();
            })

            .then(html => {
                console.log("Loaded products html fragment");
                updateMainContent(html);
                const newSortSelect = document.getElementById('sortSelect');
                if (newSortSelect) {
                    newSortSelect.value = sortOrder || '';
                    if (!newSortSelect.dataset.listenerAttached) {
                        newSortSelect.addEventListener('change', handleSortChange);
                        newSortSelect.dataset.listenerAttached = "true";
                    }
                }
                console.log("Sort select reinjectat:", document.getElementById('sortSelect'));
                history.pushState({type:'category', categoryId, sortOrder, html }, null, url);
            })
            .catch(error => {
                console.error('Error loading products:', error);
            });
    }

function fetchProductDetails(productId) {
    const url = `/product/${productId}/details`;

    fetch(url)
        .then(response => {
            if (!response.ok) throw new Error("Product not found!");
            return response.text();
        })
        .then(html => {
            updateMainContent(html);
            clearActiveCategory();
            history.pushState({ type: 'product', productId, html }, null, url);
        })
        .catch(error => {
            updateMainContent(`<p style="color:red">${error.message}</p>`);
        });
}

    function updateMainContent(html) {
        const mainContent = document.getElementById('content');
        mainContent.innerHTML = html;
    }

    function initCategoryLinks(categoryLinks) {
        categoryLinks.forEach(link => {
            link.addEventListener('click', getProductsForCategory);
        });
    }

    function selectDefaultCategory(categoryLinks) {
        if (categoryLinks.length > 0) {
            categoryLinks[0].click();
        }
    }



    function handleSortChange() {
        const sortValue = getSortValue();
        const activeLink = document.querySelector('.category-link.active');
        let categoryId;

        if (activeLink) {
            categoryId = activeLink.getAttribute('data-id');
        } else {
            categoryId = 'ALL';
        }
        fetchProductsByCategory(categoryId, sortValue);
    }
    function initSortSelect(sortSelect, sortOrder) {
        if(sortSelect) {
            sortSelect.value=sortOrder || '';
        }

        if (!sortSelect.dataset.listenerAttached) {
            sortSelect.addEventListener('change', handleSortChange);
            sortSelect.dataset.listenerAttached = "true";
        }

    }
    function getSortValue() {
        const sortSelect=document.getElementById('sortSelect')
        return sortSelect ? sortSelect.value:'';
    }

    function handleProductLink(event) {
        const link = event.target.closest('.product-detail-link');
        if (!link) return;

        event.preventDefault();
        const productId = link.getAttribute('data-id');
        console.log("Fetching product details for ID:", productId);
        fetchProductDetails(productId);
    }

    function initProductLinks() {
      const content=document.getElementById('content');
      if(content.dataset.listenerAttached) return;
      content.addEventListener('click',handleProductLink);
      content.dataset.listenerAttached="true";
        }



    function updateActiveCategory(categoryId) {
        document.querySelectorAll('.category-link').forEach(link => {
            link.classList.remove('active');
            if (link.getAttribute('data-id') === categoryId) {
                link.classList.add('active');
            }
        });
    }

function clearActiveCategory() {
    document.querySelectorAll('.category-link').forEach(link => link.classList.remove('active'));
}

    function handlePopState(event) {
        if (event.state &&event.state.html) {
            updateMainContent(event.state.html);

            if (event.state.type === 'category') {
                updateActiveCategory(event.state.categoryId);
                if (event.state.sortOrder && document.getElementById('sortSelect')) {
                    document.getElementById('sortSelect').value = event.state.sortOrder;
                }
            } else if (event.state.type === 'product') {
                document.querySelectorAll('.category-link').forEach(link => {
                    link.classList.remove('active');
                });
            }

            initProductLinks();
        } else {
            const path = window.location.pathname;
            const urlParams = new URLSearchParams(window.location.search);
            const sortOrder = urlParams.get('sort');

            if (path.startsWith("/product/")) {
                const productId = path.split("/")[2];
                fetchProductDetails(productId);
            } else if (path.startsWith("/category/")) {
                const categoryId = path.split("/")[2];
                fetchProductsByCategory(categoryId, sortOrder);
            } else if (path === "/products") {
                fetchProductsByCategory("ALL",sortOrder);
            } else {
                selectDefaultCategory(document.querySelectorAll('.category-link'));
            }
        }
    }

window.addEventListener('popstate', handlePopState);


    return { //only initPage is public the rest is hidden, private
        initPage: initPage
    }
})();
document.addEventListener('DOMContentLoaded',categoryService.initPage);