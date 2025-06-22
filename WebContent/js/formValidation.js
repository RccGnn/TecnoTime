// js/formValidation.js
document.addEventListener('DOMContentLoaded', function() {
  const form = document.querySelector('.form-container form');

  // regex per i vari campi
  const nameRe     = /^[A-Za-zÀ-ÖØ-öø-ÿ' ]{2,50}$/;
  const addressRe  = /^[A-Za-z0-9À-ÖØ-öø-ÿ'.,\-\s]{5,100}$/;
  const postalRe   = /^\d{5}$/;
  const phoneRe    = /^\+?\d{7,15}$/;
  const usernameRe = /^[A-Za-z0-9_]{3,20}$/;
  const pwdRe      = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z\d]).{9,}$/;

  form.addEventListener('submit', function(e) {
    let errors = [];

    const firstName  = form.firstName.value.trim();
    const lastName   = form.lastName.value.trim();
    const birthDate  = form.birthDate.value;
    const address    = form.address.value.trim();
    const aptnumber  = form.aptnumber.value.trim();
    const postalCode = form.postalCode.value.trim();
    const email      = form.email.value.trim();
    const password   = form.password.value;
    const telNumb    = form.telNumb.value.trim();
    const username   = form.username.value.trim();

    // Nome e cognome
    if (!nameRe.test(firstName)) errors.push("Nome non valido");
    if (!nameRe.test(lastName))  errors.push("Cognome non valido");

    // Data di nascita
    if (!birthDate) errors.push("Inserisci una data di nascita valida");

    // Indirizzo e numero civico
    if (!addressRe.test(address)) errors.push("Indirizzo non valido");
    if (aptnumber && aptnumber.length > 20) errors.push("Numero civico troppo lungo");

    // CAP
    if (!postalRe.test(postalCode)) errors.push("CAP non valido (5 cifre)");

    // Email: HTML5 built-in (type="email")

    // Password: min 9 caratteri, almeno 1 maiuscola, 1 minuscola, 1 numero, 1 simbolo
    if (!pwdRe.test(password))
      errors.push("Password deve avere almeno 9 caratteri, con almeno una maiuscola, una minuscola, un numero e un simbolo");

    // Telefono (opzionale)
    if (telNumb && !phoneRe.test(telNumb)) errors.push("Telefono non valido");

    // Username
    if (!usernameRe.test(username)) errors.push("Username non valido (3-20 caratteri alfanumerici o underscore)");

    if (errors.length) {
      e.preventDefault();
      
      let errHtml = '<div class="error-message"><ul>';
      errors.forEach(msg => errHtml += `<li>${msg}</li>`);
      errHtml += '</ul></div>';

      
      const old = document.querySelector('.error-message');
      if (old) old.remove();

      form.insertAdjacentHTML('beforebegin', errHtml);
    }
  });
});
