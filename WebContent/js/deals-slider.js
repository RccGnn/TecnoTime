document.addEventListener('DOMContentLoaded', () => {
  const slides = Array.from(document.querySelectorAll('.mobile-slider .slide'));
  let current = 0;

  function updateSlides() {
    slides.forEach((s, i) => {
      s.classList.remove('prev', 'active', 'next');
      if (i === current) {
        s.classList.add('active');
      } else if (i === (current - 1 + slides.length) % slides.length) {
        s.classList.add('prev');
      } else if (i === (current + 1) % slides.length) {
        s.classList.add('next');
      }
    });
  }

  // inizializza
  updateSlides();

  // click su una slide la porta attiva
  slides.forEach(s => {
    s.addEventListener('click', () => {
      const idx = parseInt(s.dataset.index, 10);
      if (idx !== current) {
        current = idx;
        updateSlides();
      }
    });
  });
});
