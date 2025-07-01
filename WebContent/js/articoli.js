document.addEventListener('DOMContentLoaded', () => {
  const toggleBtn = document.getElementById('filter-toggle-btn');
  const sidebar   = document.getElementById('filters-sidebar');
  const mobileBp  = 768;

  toggleBtn.addEventListener('click', () => {
    sidebar.classList.toggle('visible');
  });

  document.addEventListener('click', e => {
    if (window.innerWidth <= mobileBp) {
      if (!sidebar.contains(e.target) && e.target !== toggleBtn) {
        sidebar.classList.remove('visible');
      }
    }
  });


  window.addEventListener('resize', () => {
    if (window.innerWidth > mobileBp) {
      sidebar.classList.remove('visible');
    }
  });
});