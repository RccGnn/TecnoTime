document.addEventListener('DOMContentLoaded', function() {
  const pwdInput = document.getElementById('password');
  const toggle   = document.getElementById('togglePassword');
  const iconShow = document.getElementById('icon-show');
  const iconHide = document.getElementById('icon-hide');

  if (!toggle || !pwdInput) {
    console.error('Elementi non trovati: ', { toggle, pwdInput });
    return;
  }

  toggle.addEventListener('click', function() {
    if (pwdInput.type === 'password') {
      pwdInput.type = 'text';
      iconShow.style.display = 'block';
      iconHide.style.display = 'none';
      toggle.setAttribute('aria-label', 'Nascondi password');
    } else {
      pwdInput.type = 'password';
      iconShow.style.display = 'none';
      iconHide.style.display = 'block';
      toggle.setAttribute('aria-label', 'Mostra password');
    }
  });
});
