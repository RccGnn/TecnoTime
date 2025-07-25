// Serve per far partire la funzione appena la pagina ha finito di caricare il DOM
document.addEventListener('DOMContentLoaded', () => {
	const form = document.querySelector('.form-container form');

	// regex per i vari campi
	const nameRe     = /^[A-Za-zÀ-ÖØ-öø-ÿ']{2,50}$/;
	const addressRe  = /^[A-Za-z0-9À-ÖØ-öø-ÿ'.,\-\s]{5,100}$/;
	const postalRe   = /^\d{5}$/;
	const phoneRe    = /^\+?\d{9,15}$/;
	const usernameRe = /^[A-Za-z0-9_]{3,20}$/;
	const pwdRe      = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z\d]).{9,}$/;
	const nationRe 	 = /^[A-Za-z\s\-]{2,100}$/;
	// regex per i vari campi
	const rules = {
		firstName:      { re: nameRe,    msg: 'Nome non valido, si prega di inserire almeno 2 lettere' },
		lastName:       { re: nameRe,    msg: 'Cognome non valido, si prega di inserire almeno 2 lettere' },
		address:        { re: addressRe, msg: 'Indirizzo non valido, si prega di inserire almeno 5 caratteri' },
		postalCode:     { re: postalRe,  msg: 'CAP non valido, si prega di inserire esattamente 5 cifre' },
		telNumb:        { re: phoneRe,   msg: 'Telefono non valido, si prega di inserire almeno 9 cifre' },     // opzionale
		username:       { re: usernameRe,msg: 'Username non valido, si prega di inserire da 3 a 20 caratteri, cifre o il simbolo _' },
		password:       { re: pwdRe,     msg: 'La password deve avere almeno 9 caratteri, una maiuscola, una minuscola, un numero e un simbolo' },
		nation:			{ re: nationRe,  msg: 'Si prega di inserire solo lettere (almeno 2) o il carattere -'}
	};

	// helper che pulisce eventuale messaggio
	function clearError(field) {
		const nxt = field.nextElementSibling;
		if (nxt && nxt.classList.contains('inline-error')) nxt.remove();
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
				showError(field, 'Inserisci una data di nascita');
				return false;
			}
			
			// Creo due oggetti Date per farne il confronto
			let currentDate = new Date();
			let inputDate = new Date(val);
			let minDate=new Date();
			
			const y = currentDate.getFullYear() - 13;
			const m = currentDate.getMonth();
			const d = currentDate.getDay();
			minDate.setFullYear(currentDate.getFullYear() - 100);
			minDate.setHours(0, 0, 0, 0);
			
			currentDate.setFullYear(y, m, d);
			// Voglio confrontare solo il giorno, non l'ora
			inputDate.setHours(0, 0, 0, 0); // (h, m, s, ms?)
			currentDate.setHours(0, 0, 0, 0);

			if (inputDate >= currentDate) {
				showError(field, 'Devi avere almeno 13 anni per poterti registrare al sito');
				return false;
			}else
			if(inputDate <= minDate){
				showError(field, 'Chiamo i Ghostbuster');
				return false;
			}
			clearError(field);
			return true;
		}

		// email (HTML5) con il TAG HTML
		if (name === 'email') {
			if (!field.checkValidity()) {
				showError(field, 'Email non valida');
				return false;
			}
			clearError(field);
			return true;
		}
	
		// telefono opzionale
		if (name === 'telNumb') {
			if (val && !rules.telNumb.re.test(val)) {
				showError(field, rules.telNumb.msg);
				return false;
			}
			clearError(field);
			return true;
		}
	
		// password
		if (name === 'password') {
			if (!rules.password.re.test(val)) {
				showError(field, rules.password.msg);
				return false;
			}
			clearError(field);
			return true;
		}
	
		// conferma password
		if (name === 'confirmPassword') {
			const pw = form.password.value.trim();
			if (val !== pw) {
				showError(field, 'Le due password non corrispondono');
				return false;
			}
			clearError(field);
			return true;
		}
	
		// controllo su tutti gli altri basati su regex
		if (rules[name]) {
			if (!rules[name].re.test(val)) {
				showError(field, rules[name].msg);
				return false;
			}
			clearError(field);
			return true;
		}
	
		// messaggio default OK
		clearError(field);
		return true;
	 }

	// aggiungo listener a tutti i campi che mi interessano
	Array.from(form.elements).forEach(el => {
		if (!el.id) return;
		// campi con regex + birthDate, email, telNumb, confirmPassword
		if (rules[el.id] || ['birthDate','email','telNumb','confirmPassword'].includes(el.id)) {
			el.addEventListener('change', () => validateField(el));
			el.addEventListener('input',  () => {
				if (el.classList.contains('invalid')) validateField(el);
			});
		}
	});

	// al submit, valido tutto e blocco se c'è almeno un errore
	form.addEventListener('submit', e => {
		let valid = true;
		Array.from(form.elements).forEach(el => {
			if (!el.id) return;
			if (rules[el.id] || ['birthDate','email','telNumb','confirmPassword'].includes(el.id)) {
				if (!validateField(el)) valid = false;
			}
		});
		if (!valid) e.preventDefault();
	});
});
