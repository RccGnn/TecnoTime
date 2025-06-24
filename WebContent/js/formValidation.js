document.addEventListener('DOMContentLoaded', () => {
  const form = document.querySelector('.form-container form');

  // regex per i vari campi
  const nameRe     = /^[A-Za-zÀ-ÖØ-öø-ÿ' ]{2,50}$/;
  const addressRe  = /^[A-Za-z0-9À-ÖØ-öø-ÿ'.,\-\s]{5,100}$/;
  const postalRe   = /^\d{5}$/;
  const phoneRe    = /^\+?\d{7,15}$/;
  const usernameRe = /^[A-Za-z0-9_]{3,20}$/;
  const pwdRe      = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z\d]).{9,}$/;

  // mappa field → { regex, message }
  const rules = {
    firstName: { re: nameRe,    msg: 'Nome non valido' },
    lastName:  { re: nameRe,    msg: 'Cognome non valido' },
    address:   { re: addressRe, msg: 'Indirizzo non valido' },
    postalCode:{ re: postalRe,  msg: 'CAP non valido (5 cifre)' },
    telNumb:   { re: phoneRe,   msg: 'Telefono non valido' },     // opzionale
    username:  { re: usernameRe,msg: 'Username non valido (3-20 caratteri)' },
    password:  { re: pwdRe,     msg: 'Password deve avere almeno 9 caratteri, con almeno una maiuscola, una minuscola, un numero e un simbolo' }
  };

  // helper che pulisce eventuale messaggio
  function clearError(field) {
    const next = field.nextElementSibling;
    if (next && next.classList.contains('inline-error')) {
      next.remove();
    }
    field.classList.remove('invalid');
  }

  // helper che mostra messaggio errore
  function showError(field, message) {
    clearError(field);
    field.classList.add('invalid');
    const span = document.createElement('span');
    span.className = 'inline-error';
    span.textContent = message;
    field.insertAdjacentElement('afterend', span);
  }

  // valida singolo campo basandosi su rules
  function validateField(field) {
    const name = field.id;
    const val  = field.value.trim();

    // data di nascita
    if (name === 'birthDate') {
      if (!val) {
        showError(field, 'Inserisci una data di nascita valida');
        return false;
      } else {
        clearError(field);
        return true;
      }
    }

    // email (HTML5) con il TAG HTML
    if (name === 'email') {
      if (!field.checkValidity()) {
        showError(field, 'Email non valida');
        return false;
      } else {
        clearError(field);
        return true;
      }
    }

    // telefono opzionale
    if (name === 'telNumb') {
      if (val && !rules.telNumb.re.test(val)) {
        showError(field, rules.telNumb.msg);
        return false;
      } else {
        clearError(field);
        return true;
      }
    }

    // controllo su tutti gli altri basati su regex
    if (rules[name]) {
      if (!rules[name].re.test(val)) {
        showError(field, rules[name].msg);
        return false;
      } else {
        clearError(field);
        return true;
      }
    }

    // messaggio default OK
    clearError(field);
    return true;
  }

  // aggiunge listener a ogni field interessato
  Array.from(form.elements).forEach(el => {
    if (el.id && (rules[el.id] || ['birthDate','email','telNumb'].includes(el.id))) {
      el.addEventListener('change', () => validateField(el));
      el.addEventListener('input',  () => {
        if (el.classList.contains('invalid')) validateField(el);
      });
    }
  });

  // submit
  form.addEventListener('submit', e => {
    let valid = true;
    // validazione di tutti campi
    Array.from(form.elements).forEach(el => {
      if (el.id && (rules[el.id] || ['birthDate','email','telNumb'].includes(el.id))) {
        if (!validateField(el)) valid = false;
      }
    });
    if (!valid) e.preventDefault();
  });
});
