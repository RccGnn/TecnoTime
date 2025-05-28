// js/navbar.js
document.addEventListener('DOMContentLoaded', function() {
    const navLiElements = document.querySelectorAll('.main-nav > ul > li');
    const mobileBreakpoint = 768; // Corresponds to your CSS @media (max-width: 768px)

    navLiElements.forEach(li => {
        const link = li.querySelector('a:first-child'); // The main link (e.g., PRODOTTI)
        const dropdown = li.querySelector('ul.dropdown'); // The dropdown menu itself

        if (link && dropdown) {
            // Add ARIA attributes for accessibility
            link.setAttribute('aria-haspopup', 'true');
            link.setAttribute('aria-expanded', 'false');

            link.addEventListener('click', function(event) {
                if (window.innerWidth <= mobileBreakpoint) {
                    event.preventDefault(); // Prevent link navigation on mobile for parent items

                    const isCurrentlyOpen = dropdown.classList.contains('show');

                    // Close all other open dropdowns before opening/closing the current one
                    navLiElements.forEach(otherLi => {
                        const otherDropdown = otherLi.querySelector('ul.dropdown');
                        if (otherDropdown && otherDropdown !== dropdown) {
                            otherDropdown.classList.remove('show');
                            otherLi.querySelector('a:first-child').setAttribute('aria-expanded', 'false');
                        }
                    });

                    // Toggle the current dropdown
                    if (isCurrentlyOpen) {
                        dropdown.classList.remove('show');
                        link.setAttribute('aria-expanded', 'false');
                    } else {
                        dropdown.classList.add('show');
                        link.setAttribute('aria-expanded', 'true');
                    }
                }
                // On wider screens (> mobileBreakpoint), CSS hover will manage the dropdowns,
                // and the link will navigate as usual (e.g., to #products).
            });
        }
    });

    // Optional: Close dropdowns if clicking outside the main navigation on mobile
    document.addEventListener('click', function(event) {
        if (window.innerWidth <= mobileBreakpoint) {
            const mainNav = document.querySelector('.main-nav');
            // Check if the click was outside the main navigation area
            if (mainNav && !mainNav.contains(event.target)) {
                navLiElements.forEach(li => {
                    const dropdown = li.querySelector('ul.dropdown');
                    if (dropdown && dropdown.classList.contains('show')) {
                        dropdown.classList.remove('show');
                        li.querySelector('a:first-child').setAttribute('aria-expanded', 'false');
                    }
                });
            }
        }
    });
});