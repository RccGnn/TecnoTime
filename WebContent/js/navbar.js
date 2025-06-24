document.addEventListener('DOMContentLoaded', function() {
    const navLiElements = document.querySelectorAll('.main-nav > ul > li');
    const mobileBreakpoint = 768; // Corrisponde al CSS @media (max-width: 768px)

    navLiElements.forEach(li => {
        const link = li.querySelector('a:first-child'); // Il link principale (es. PRODOTTI)
        const dropdown = li.querySelector('ul.dropdown'); // Il menu a discesa stesso

        if (link && dropdown) {
            // Aggiungere attributi ARIA per l'accessibilità
            link.setAttribute('aria-haspopup', 'true');
            link.setAttribute('aria-expanded', 'false');

            link.addEventListener('click', function(event) {
                if (window.innerWidth <= mobileBreakpoint) {
                    event.preventDefault(); 
					// Impedisce la navigazione tramite link su dispositivi mobili per gli elementi padre

                    const isCurrentlyOpen = dropdown.classList.contains('show');

                    // Chiudere tutti gli altri menu a discesa aperti prima di aprire/chiudere quello corrente
                    navLiElements.forEach(otherLi => {
                        const otherDropdown = otherLi.querySelector('ul.dropdown');
                        if (otherDropdown && otherDropdown !== dropdown) {
                            otherDropdown.classList.remove('show');
                            otherLi.querySelector('a:first-child').setAttribute('aria-expanded', 'false');
                        }
                    });

                    // Attiva/disattiva il menu a discesa corrente
                    if (isCurrentlyOpen) {
                        dropdown.classList.remove('show');
                        link.setAttribute('aria-expanded', 'false');
                    } else {
                        dropdown.classList.add('show');
                        link.setAttribute('aria-expanded', 'true');
                    }
                }
                // Su schermi più ampi (> mobileBreakpoint), CSS hover gestirà i menu a discesa,
                // e il collegamento proseguirà come di consueto (ad esempio, verso #prodotti).
            });
        }
    });
	
	const hamburgerBtn = document.getElementById('hamburgerBtn');
	const navMenu = document.querySelector('.main-nav ul');

	hamburgerBtn.addEventListener('click', function () {
	    navMenu.classList.toggle('show');

	    // Alterna l'animazione dell'hamburger
	    hamburgerBtn.classList.toggle('active');
	});

    // Chiudere i menu a discesa se si fa clic al di fuori della navigazione principale su dispositivi mobili
    document.addEventListener('click', function(event) {
        if (window.innerWidth <= mobileBreakpoint) {
            const mainNav = document.querySelector('.main-nav');
            // Controlla se il clic è avvenuto al di fuori dell'area di navigazione principale
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
