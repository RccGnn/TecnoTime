function createXMLHttpRequest() {
	var request;
	try {
		// Firefox 1+, Chrome 1+, Opera 8+, Safari 1.2+, Edge 12+, Internet Explorer 7+
		request = new XMLHttpRequest();
	} catch (e) {
		// Versioni precedenti di Internet Explorer 
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP");  
		} catch (e) {
			// Versioni che non supportano Ajax
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("Il browser non supporta AJAX");
				return null;
			}
		}
	}
	return request;
}

function loadAjaxDoc(url, method, params, cFuction) {
	var request = createXMLHttpRequest();
	
	if(request){
		// Richiesta creata correttamente		
		request.onreadystatechange = function() {
			if (this.readyState == 4) {
				if (this.status == 200) {
					// Esecuzione possibile
				    cFuction(this);
				} else {				
					if(this.status == 0){ // Abort della richiesta
						alert("Problemi nell'esecuzione della richiesta: nessuna risposta ricevuta nel tempo limite");
					} else { // Tutti gli altri casi
						alert("Problemi nell'esecuzione della richiesta:\n" + this.statusText);
					}
					return null;
				}
		    }
		};
		
		// Imposta il timeout
		setTimeout(function () {     // abort dopo 15 sec
        	if (request.readyState < 4) {
            	request.abort();
        	}
    	}, 15000/*ms*/); 
		
		// Metodo usato: GET
		if (method.toLowerCase() === "get") {
		  request.open("GET", params ? `${url}?${params}` : url, true); // Simile alle fString di pyhton
		  request.send(null);
		}

		// Metodo usato: POST
		 else {
			
				// Se i parametri sono presenti
				request.open("POST", url, true);
				request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				request.send(params);
		}
		
	}
}

/**
 * Funzione di callback per la verifica username.
 */
function handleUsernameResponse(xhr) {
	try {
        let response = JSON.parse(xhr.responseText);
        let errorMsg = response.errore;  // dal campo JSON "errore"
        let msgElem = document.getElementById("usernameMessage");
        if (!msgElem) return;
        
        // Se "None", l'username è libero; altrimenti è occupato
        if (errorMsg === "None" || !errorMsg) {
            msgElem.innerHTML = "<span class=inline-error style='color: white;'>Username</span>";
        } else {
            msgElem.innerHTML = "<span class=inline-error >" + errorMsg + "</span>";
        }
    } catch (e) {
        console.error("Errore nel parsing della risposta JSON per username:", e);
    }
}

/**
 * Funzione di callback per la verifica email.
 */
function handleEmailResponse(xhr) {
    try {
        let response = JSON.parse(xhr.responseText);
        let errorMsg = response.errore;  // dal campo JSON "errore"
        let msgElem = document.getElementById("emailMessage");
        if (!msgElem) return;
        
        if (errorMsg === "None" || !errorMsg) {
            msgElem.innerHTML = "<span class=inline-error style='color: white;'>Email</span>";
        } else {
            msgElem.innerHTML = "<span class=inline-error >" + errorMsg + "</span>";
        }
    } catch (e) {
        console.error("Errore nel parsing della risposta JSON per email:", e);
    }
}

/**
 * Imposta gli handler di evento onblur su window.onload.
 * Attacca le funzioni di verifica agli input "username" e "email".
 * Crea gli span per i messaggi di feedback se non esistono.
 */
window.onload = function() {
    // Seleziona gli elementi input
    let userInput = document.getElementById("username");
    let emailInput = document.getElementById("email");
    
    if (userInput) {
        // Crea uno span per mostrare il messaggio di username, se non esiste
        if (!document.getElementById("usernameMessage")) {
            userInput.insertAdjacentHTML('afterend', '<span id="usernameMessage";"></span>');
        }
        // Al termine dell'input (onblur) chiama la verifica AJAX
        userInput.onblur = function() {
            let username = userInput.value.trim();
            //if (username !== "") {
                // Parametro della richiesta
                let params = "control=" + encodeURIComponent(username);
                loadAjaxDoc("RegistrationFormValidator", "GET", params, handleUsernameResponse);
            //}
        };
    }
    
    if (emailInput) {
        // Crea uno span per mostrare il messaggio di email, se non esiste
        if (!document.getElementById("emailMessage")) {
            emailInput.insertAdjacentHTML('afterend', '<span id="emailMessage";"></span>');
        }
        // Al termine dell'input (onblur) chiama la verifica AJAX
        emailInput.onblur = function() {
            let email = emailInput.value.trim();
            //if (email !== "") {
                // Parametro della richiesta
                let params = "control=" + encodeURIComponent(email);
                loadAjaxDoc("RegistrationFormValidator", "GET", params, handleEmailResponse);
            //}
        };
    }
};