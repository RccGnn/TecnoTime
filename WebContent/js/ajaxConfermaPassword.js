function passwordControl() {
	let newPsw = document.getElementById("newPassword").value;
	let cnfPsw = document.getElementById("confirmPassword").value;
	
	let errorMsg = document.getElementById("confirm-password-error-message");
		
	let textArea = document.getElementById("confirm-form");
	// Le password non coincido
	if(newPsw != cnfPsw) {
		// Se l'errore non c'è, crealo
		if(!errorMsg) {
			errorMsg = document.createElement("p");
			errorMsg.id = "confirm-password-error-message"
			errorMsg.className = "error-message";
			errorMsg.innerHTML = "Le password non coincidono";
			textArea.appendChild(errorMsg);
		}
	} else {
		// Al contrario, se l'errore c'è, rimuovilo
		if(errorMsg) {
			textArea.removeChild(errorMsg);
		}
	}
}

function passwordCheck() {
	let newPsw = document.getElementById("newPassword").value;
	const pwdPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z\d]).{9,}$/;

	let errorMsg = document.getElementById("password-error-message");

	let textArea = document.getElementById("password-form");

	// La password non rispetta le regole
	if(!pwdPattern.test(newPsw.trim())) {
		// Se l'errore non c'è, crealo
		if(!errorMsg) {
			errorMsg = document.createElement("p");
			errorMsg.id = "password-error-message"
			errorMsg.className = "error-message";
			errorMsg.innerHTML = "La password deve avere almeno 9 caratteri, una maiuscola, una minuscola, un numero e un simbolo";
			textArea.appendChild(errorMsg);
		}		
	} else {
		// Al contrario, se l'errore c'è, rimuovilo
		if(errorMsg) {
			textArea.removeChild(errorMsg);
		}
	}
}