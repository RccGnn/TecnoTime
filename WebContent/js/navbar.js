document.addEventListener('DOMContentLoaded', function() {
	const mobileBreakpoint = 768;

	// NAV MOBILE + DROPDOWN
	const navLiElements = document.querySelectorAll('.main-nav > ul > li');
	navLiElements.forEach(li => {
		const link     = li.querySelector('a:first-child');
		const dropdown = li.querySelector('ul.dropdown');
		if (!link || !dropdown) return;

		link.setAttribute('aria-haspopup', 'true');
		link.setAttribute('aria-expanded', 'false');

		link.addEventListener('click', function(e) {
			if (window.innerWidth <= mobileBreakpoint) {
				e.preventDefault();
				const isOpen = dropdown.classList.toggle('show');
				link.setAttribute('aria-expanded', isOpen);

				// Chiude tutti gli altri menu a discesa aperti prima di aprire/chiudere quello corrente
        		navLiElements.forEach(otherLi => {
					const od = otherLi.querySelector('ul.dropdown');
					if (od && od !== dropdown) {
						od.classList.remove('show');
						otherLi.querySelector('a:first-child')
						.setAttribute('aria-expanded', 'false');
					}
				});
			}
		});
	});

	// HAMBURGER MENU
	const hamburgerBtn = document.getElementById('hamburgerBtn');
	const navMenu = document.querySelector('.main-nav ul');
	if (hamburgerBtn && navMenu) {
	hamburgerBtn.addEventListener('click', () => {
		navMenu.classList.toggle('show');
		hamburgerBtn.classList.toggle('active');
		});
	}

	// CLICK FUORI DAL NAV (mobile)
	document.addEventListener('click', e => {
		if (window.innerWidth <= mobileBreakpoint) {
			const mainNav = document.querySelector('.main-nav');
			if (mainNav && !mainNav.contains(e.target)) {
				navLiElements.forEach(li => {
					const dd = li.querySelector('ul.dropdown');
					if (dd) {
						dd.classList.remove('show');
						li.querySelector('a:first-child')
						.setAttribute('aria-expanded', 'false');
					}
				});
			}
		}
	});

	// ADMIN MENU
	const adminMenuBtn  = document.getElementById('adminMenuBtn');
	const adminDropdown = document.getElementById('adminDropdown');
	if (adminMenuBtn && adminDropdown) {
		adminMenuBtn.addEventListener('click', e => {
			e.stopPropagation();             // evita il click al documento
			adminDropdown.classList.toggle('show');
		});
	window.addEventListener('click', e => {
		if (adminDropdown.classList.contains('show') && !adminMenuBtn.contains(e.target)) {
			adminDropdown.classList.remove('show');
		}
		});
	}
});
