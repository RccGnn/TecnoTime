document.addEventListener('DOMContentLoaded', () => {
	const form     = document.getElementById('checkoutForm');
	const ncard    = document.getElementById('ncard');
	const expDate  = document.getElementById('expiringDate');
	const codecard = document.getElementById('codecard');

	// helper che pulisce l'eventuale messaggio d'errore
	function clearError(field) {
		const next = field.nextElementSibling;
		if (next && next.classList.contains('inline-error')) {
			next.remove();
		}
	field.classList.remove('invalid');
	}

	// helper che mostra il messaggio d'errore inline
	function showError(field, message) {
		clearError(field);
		field.classList.add('invalid');
		const span = document.createElement('span');
		span.className = 'inline-error';
		span.textContent = message;
		field.insertAdjacentElement('afterend', span);
	}

	// validazione del singolo campo
	function validateField(field) {
		const id = field.id;
		const value = field.value.trim();

		// 1) Numero carta: 16 cifre
		if (id === 'ncard') {
			const digits = value.replace(/\s+/g, '');
			if (!/^\d{16}$/.test(digits)) {
				showError(field, 'Inserisci un numero di carta di 16 cifre.');
				return false;
			}
		clearError(field);
		return true;
		}

		// 2) Data di scadenza: almeno il mese successivo, non si può inserire il mese attuale o una data precedente
		if (id === 'expiringDate') {
			if (!value) {
				showError(field, 'Inserisci la data di scadenza.');
				return false;
			}

			const [year, month] = value.split('-').map(Number);
			const now = new Date();
			const selected = new Date(year, month - 1);
			const min = new Date(now.getFullYear(), now.getMonth() + 1, 1);
			
			if (selected < min) {
				showError(field, 'La data deve essere almeno il mese successivo.');
				return false;
			}
		
			clearError(field);
			return true;
		}

		// 3) CVC: 3 cifre
		if (id === 'codecard') {
			if (!/^\d{3}$/.test(value)) {
				showError(field, 'Il codice di sicurezza deve essere di 3 cifre.');
				return false;
			}

		clearError(field);
		return true;
		}
		return true;
	}

	// formattazione del numero carta a gruppi di 4 cifre
	ncard.addEventListener('input', () => {
		let v = ncard.value.replace(/\D/g, '').slice(0, 16);
		let parts = v.match(/.{1,4}/g);
		ncard.value = parts ? parts.join(' ') : v;
		// se già invalido, rivedo la validazione in tempo reale
		if (ncard.classList.contains('invalid')) validateField(ncard);
	});

	// collego la convalida alla modifica
	[ncard, expDate, codecard].forEach(field => {
		field.addEventListener('change', () => validateField(field));
		field.addEventListener('input', () => {
			if (field.classList.contains('invalid')) validateField(field);
		});
	});

	// al submit, valido tutti i campi e blocco se almeno uno è errato
	form.addEventListener('submit', (e) => {
		let valid = true;
		[ncard, expDate, codecard].forEach(field => {
			if (!validateField(field)) valid = false;
		});

		if (!valid) {
			e.preventDefault();
		}
	});
});
