const categoryService = (function () {

    function initPage() {
        const categoryLinks = document.querySelectorAll('.category-link');
        const sortSelect=document.getElementById('sortSelect');
        initCategoryLinks(categoryLinks);
        initSortSelect(sortSelect);
        selectDefaultCategory(categoryLinks);

    }


    function getProductsForCategory(event) {
        const categoryLinks = document.querySelectorAll('.category-link');
        event.preventDefault();
        const categoryId = event.currentTarget.getAttribute('data-id');
        categoryLinks.forEach(link => link.classList.remove('active'));
        event.currentTarget.classList.add('active');
        const sortSelect=document.getElementById('sortSelect');
        const sortValue=sortSelect?sortSelect.value:'';
        fetchProductsByCategory(categoryId,sortValue);
    }

    function fetchProductsByCategory(categoryId,sortOrder) {
        let url=`/category/${categoryId}/products`;
        if(sortOrder) {
            url+=`?sort=${sortOrder}`;
        }
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error("No products for this category!");
                }
                return response.text();
            })

            .then(html => {
                updateMainContent(html);
            })
            .catch(error => {
                console.error('Error loading products:', error);
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

    function handleCategoryClick(event) {
        event.preventDefault();

        const categoryLinks = document.querySelectorAll('.category-link');
        for (let i = 0; i < categoryLinks.length; i++) {
            categoryLinks[i].classList.remove('active');
        }
        this.classList.add('active');

        const categoryId = this.getAttribute('data-id');
        const sortValue = getSortValue();
        fetchProductsByCategory(categoryId, sortValue);
    }

    function handleSortChange() {
        const activeCategory=document.querySelector('.category-link.active');
        if(activeCategory) {
            const categoryId = activeCategory.getAttribute('data-id');
            const sortValue=getSortValue();
            fetchProductsByCategory(categoryId, sortValue);
        }
    }
    function initSortSelect(sortSelect) {
        if(sortSelect) {
            sortSelect.addEventListener('change',handleSortChange);
        }
    }
    function getSortValue() {
        const sortSelect=document.getElementById('sortSelect')
        return sortSelect ? sortSelect.value:'';
    }


    return { //only initPage is public the rest is hidden, private
        initPage: initPage,
        handleSortChange:handleSortChange
    }
})();
document.addEventListener('DOMContentLoaded',categoryService.initPage);