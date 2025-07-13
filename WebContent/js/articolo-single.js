document.addEventListener('DOMContentLoaded', () => {
	const mainImage = document.getElementById('productsingle-main-image');
	const thumbs    = document.querySelectorAll('.productsingle-thumb');
	const form      = document.getElementById('productsingle-add-form');

	// Cambio immagine al click sulla miniatura
	thumbs.forEach(thumb => {
		thumb.addEventListener('click', () => {
			mainImage.src = thumb.src;
		});
	});

	// ZOOM desktop on hover
	if (window.innerWidth > 768) {
		mainImage.addEventListener('mousemove', e => {
			const { left, top, width, height } = mainImage.getBoundingClientRect();
			const x = (e.clientX - left) / width * 100;
			const y = (e.clientY - top) / height * 100;
		mainImage.style.transformOrigin = `${x}% ${y}%`;
		mainImage.style.transform       = 'scale(2)';
		});
		
	mainImage.addEventListener('mouseleave', () => {
		mainImage.style.transform = 'scale(1)';
	});
	
	} else {
	// ZOOM mobile on click
	let zoomed = false;
	mainImage.addEventListener('click', () => {
		zoomed = !zoomed;
		mainImage.style.transformOrigin = 'center center';
		mainImage.style.transform       = zoomed ? 'scale(2)' : 'scale(1)';
	});
  	}
});