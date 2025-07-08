document.addEventListener('DOMContentLoaded', () => {
    const links = document.querySelectorAll('.category-link');
    const content = document.getElementById('content');

    links.forEach(link => {
        link.addEventListener('click', event => {
            event.preventDefault();
            const categoryId = link.getAttribute('data-id');

            fetch(`/category/${categoryId}/products`)
                .then(response => response.text())
                .then(html => {
                    content.innerHTML = html;
                })
                .catch(error => {
                    console.error('Error loading products:', error);
                });
        });
    });
    if(links.length>0) {
        links[0].click();
    }
});