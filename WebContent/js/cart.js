document.addEventListener('DOMContentLoaded', () => {
  document.querySelectorAll('.quantity-input').forEach(input => {
    input.addEventListener('change', (e) => {
      const form = e.target.closest('form');
      form.submit();
    });
  });
});
