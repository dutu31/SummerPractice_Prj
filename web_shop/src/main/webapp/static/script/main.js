document.addEventListener('DOMContentLoaded', () => {
    const categoryLinks = document.querySelectorAll('.category-link');
    const mainContent = document.getElementById('content');

    function getProductsForCategory(event) {
        event.preventDefault();
        const categoryId=event.currentTarget.getAttribute('data-id');
        fetchProductsByCategory(categoryId);
        categoryLinks.forEach(link => link.classList.remove('active'));
        event.currentTarget.classList.add('active');
    }

    function fetchProductsByCategory(categoryId) {
        fetch(`/category/${categoryId}/products`)
            .then(response => response.text())
            .then(html => {
               updateMainContent(html);
            })
            .catch(error => {
                console.error('Error loading products:', error);
            });
    }
    function updateMainContent(html) {
        mainContent.innerHTML=html;
    }
    function initCategoryLinks() {
        categoryLinks.forEach(link => {
            link.addEventListener('click',getProductsForCategory);
        });
    }
    function selectDefaultCategory() {
        if(categoryLinks.length>0) {
            categoryLinks[0].click();
    }
    }
    function sortByPrice() {

    }

    initCategoryLinks();
    selectDefaultCategory();
});