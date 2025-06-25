// js/navbar.js
document.addEventListener('DOMContentLoaded', function() {
    // ======================================================
    // LOGICA ESISTENTE PER LA NAVIGAZIONE PRINCIPALE (Mobile)
    // ======================================================
    const navLiElements = document.querySelectorAll('.main-nav > ul > li');
    const mobileBreakpoint = 768; // Corrisponde al CSS @media (max-width: 768px)

    navLiElements.forEach(li => {
        const link = li.querySelector('a:first-child');
        const dropdown = li.querySelector('ul.dropdown');

        if (link && dropdown) {
            link.setAttribute('aria-haspopup', 'true');
            link.setAttribute('aria-expanded', 'false');

            link.addEventListener('click', function(event) {
                if (window.innerWidth <= mobileBreakpoint) {
                    event.preventDefault(); 
                    const isCurrentlyOpen = dropdown.classList.contains('show');

                    navLiElements.forEach(otherLi => {
                        const otherDropdown = otherLi.querySelector('ul.dropdown');
                        if (otherDropdown && otherDropdown !== dropdown) {
                            otherDropdown.classList.remove('show');
                            otherLi.querySelector('a:first-child').setAttribute('aria-expanded', 'false');
                        }
                    });

                    if (isCurrentlyOpen) {
                        dropdown.classList.remove('show');
                        link.setAttribute('aria-expanded', 'false');
                    } else {
                        dropdown.classList.add('show');
                        link.setAttribute('aria-expanded', 'true');
                    }
                }
            });
        }
    });
	
	const hamburgerBtn = document.getElementById('hamburgerBtn');
	const navMenu = document.querySelector('.main-nav ul');

	hamburgerBtn.addEventListener('click', function () {
	    navMenu.classList.toggle('show');
	    hamburgerBtn.classList.toggle('active');
	});

    document.addEventListener('click', function(event) {
        if (window.innerWidth <= mobileBreakpoint) {
            const mainNav = document.querySelector('.main-nav');
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

    // =================================================
    // --- NUOVA LOGICA PER ADMIN MENU NELL'HEADER ---
    // =================================================
    const adminMenuBtn = document.getElementById('adminMenuBtn');
    const adminDropdown = document.getElementById('adminDropdown');

    // Assicurati che gli elementi esistano prima di aggiungere gli event listener
    if (adminMenuBtn && adminDropdown) {
        
        // Gestisce il click sul pulsante hamburger dell'admin
        adminMenuBtn.addEventListener('click', function(event) {
            // Ferma la propagazione per evitare che il click venga subito catturato 
            // dal listener sulla finestra (window) e chiuda il menu appena aperto.
            event.stopPropagation();
            
            // Aggiunge o rimuove la classe 'show' per mostrare/nascondere il menu.
            adminDropdown.classList.toggle('show');
        });
    }

    // Listener globale per chiudere il menu admin se si clicca altrove.
    // Questo funziona su QUALSIASI dimensione dello schermo.
    window.addEventListener('click', function(event) {
        // Controlla se il menu admin è aperto
        if (adminDropdown && adminDropdown.classList.contains('show')) {
            // Se il click NON è avvenuto sul pulsante del menu, chiudilo.
             if (!adminMenuBtn.contains(event.target)) {
                adminDropdown.classList.remove('show');
             }
        }
    });
});