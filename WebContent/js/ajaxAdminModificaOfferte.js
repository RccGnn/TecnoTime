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



function displayOfferFields() {
	// Leggi il campo offerta per ricavare il nome il nome
	let offerSelector = document.getElementById("offerSelect");
	if(!offerSelector) {
		return;
	}

	const offerName = offerSelector.value;
	if(offerName == "-- Seleziona --") {
		clearAllFields();
		return;
	}
	
	const url = "RetrieveOrder";
	let params = "offername=" + encodeURIComponent(offerName);
	
	loadAjaxDoc(url, "GET", params, handleDisplayFields);
}


function clearAllFields() {
	// Elimina i campi in display
	let nome = document.getElementById("nome");
	nome.value = "";

	let descrizione = document.getElementById("descrizione");
	descrizione.innerHTML = "";

	let sconto = document.getElementById("sconto");
	sconto.value = "";
	
}

function handleDisplayFields(xhr) {
	// Recupera il l'oggetto promozione
	let response = JSON.parse(xhr.responseText);
	
	// Effettua il display
	let nome = document.getElementById("nome");
	nome.value = response.nomesconto;
	
	let descrizione = document.getElementById("descrizione");
	descrizione.innerHTML = response.descrizione;
	
	let sconto = document.getElementById("sconto");
	sconto.value = response.percentualeSconto;	
}




