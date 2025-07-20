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

// helper che pulisce eventuale messaggio
function clearError() {
	let isError = document.getElementsByClassName("error-message")[0];
	if (isError != null) { // Già è presente un errore
		isError.remove(); // Elimina l'errore
	}
}

// helper che mostra messaggio errore
function showError(message) {
	clearError(); // Elimina l'errore precedente

	// Se non è presente un errore, mostralo
	let errorContainer = document.getElementById("filter-error-container");
	let error = document.createElement("div");
	error.className = "error-message";
	error.innerHTML = message;
	
	errorContainer.appendChild(error);
}

function checkDate() {
    clearError();

    const lowerDateInputEl = document.getElementById("dateLowerBound");
    const upperDateInputEl = document.getElementById("dateUpperBound");
        
    const lowerDateValue = lowerDateInputEl.value;
    const upperDateValue = upperDateInputEl.value;
	
	if (!lowerDateValue) {
		showError('Si prega di inserire la data finale!');
		return false;		
	}
	
	if (!lowerDateValue) {
		showError('Si prega di inserire la data iniziale!');
		return false;		
	}
	
	// Effettua il confronto fra date solo quando sono inserite entrambe
    if (lowerDateValue && upperDateValue) {
        const lowerDate = new Date(lowerDateValue);    
        const upperDate = new Date(upperDateValue);

        let currentDate = new Date();
        currentDate.setHours(0, 0, 0, 0); 
        
        if (isNaN(lowerDate.getTime()) || isNaN(upperDate.getTime())) {
            showError('Si prega di inserire date valide (YYYY-MM-DD)!');
            return false;
        }

		if (upperDate > currentDate) {
		    showError('La data finale non può essere successiva alla data odierna!');
		    return false;
		}

		if (lowerDate > upperDate) {
		    showError('La data iniziale non può essere successiva alla data finale!');
		    return false;
		}

		// L'utente mette una data del futuro
        if (lowerDate > currentDate) {
            showError('La data iniziale non può essere successiva alla data odierna!');
            return false;
        }
    }
	
	return true;
}


function sortedOrders() {
	const priceUbInput = parseFloat(document.getElementById("priceUpperBound").value);
	const priceLbInput = parseFloat(document.getElementById("priceLowerBound").value);
	const dateLbput = document.getElementById("dateLowerBound").value;
	const dateUpInput = document.getElementById("dateUpperBound").value;
	let usernameInput = "";
	
	if (window.location.pathname.includes("amministratore") )
		usernameInput = document.getElementById("username").value;
	
	
	let params = 	"min=" + encodeURIComponent(priceLbInput) +
	                "&max=" + encodeURIComponent(priceUbInput) +
	                "&dateLowerBound=" + encodeURIComponent(dateLbput) +
	                "&dateUpperBound=" + encodeURIComponent(dateUpInput) + 
					"&username=" + encodeURIComponent(usernameInput);
	
	if (checkDate()) 
		loadAjaxDoc("/TecnoTime/OrderFilter", "GET", params, handleFilter, "application/x-www-form-urlencoded");

}

function displaySlider(element) {
	
	console.log(element.id);
	let slider, output;
	if (element.id == "priceLowerBound") {
		slider = document.getElementById(element.id);
		output = document.getElementById("slider1");
	}
	if (element.id == "priceUpperBound") {
		slider = document.getElementById(element.id);
		output = document.getElementById("slider2");	
	}
	
	console.log(slider.value);
	output.innerHTML = slider.value + " €";
}

function cleanSection() {
	let articoli = document.getElementsByClassName('orders-list-page')[0];

	while (articoli.firstChild) {
		articoli.removeChild(articoli.firstChild);
	}
}

function handleFilter(xhr) {
	// Ottieni una lista di ordini 
	let response = JSON.parse(xhr.responseText);
	
	cleanSection(); // Rimuovi tutti gli elementi tranne i filtri
	// Crea il container per gli ordini
	let container = document.getElementsByClassName("orders-list-page")[0];
	
	// Se non ci sono prodotti, allora mostra un messaggio
	if(response[1] == null || (response[1] != null && response[1].length == 0) ) {
		let mess = document.createElement("p");
		mess.className = "no-orders-message";
		mess.innerHTML = "Nessun ordine per i criteri di ricerca selezionati";
		container.appendChild(mess);
		
		return;
	}
	
	let flag;
	if(response[0] == true) {
		flag = true;
	} else {
		flag = false;
	}
	
	// Altrimenti genera gli ordini
	response[1].forEach(ord => {
		// Summary generale
		let orderSummary = document.createElement("div");
		orderSummary.className = "orders-summary-section";
		
		// Header 
		let orderHeader = document.createElement("div");
		orderHeader.className = "order-header";
		
		// Dettaglio per header ordini		
		let orderGroup = document.createElement("div");
		orderGroup.className = "order-details-group";
		
		///				Header ordini
		
		// Dettaglio 1
		let orderDetail = document.createElement("div");
		orderDetail.className = "order-detail-item";
		
		// span 1
		let spanDetail = document.createElement("span");
		spanDetail.className = "detail-label";
		spanDetail.innerHTML = "Data ordine:";
		orderDetail.appendChild(spanDetail);
		
		// span 2
		spanDetail = document.createElement("span");
		spanDetail.className = "detail-value";
		spanDetail.innerHTML = ord.dataTransazione;
		orderDetail.appendChild(spanDetail);

		orderGroup.appendChild(orderDetail);
		
		// Dettaglio 2
		orderDetail = document.createElement("div");
		orderDetail.className = "order-detail-item";

		// span 1
		spanDetail = document.createElement("span");
		spanDetail.className = "detail-label";
		spanDetail.innerHTML = "Totale: ";
		orderDetail.appendChild(spanDetail);
		
		// span 2
		spanDetail = document.createElement("span");
		spanDetail.className = "detail-value";
		spanDetail.innerHTML = ord.totale + " €";
		orderDetail.appendChild(spanDetail);

		orderGroup.appendChild(orderDetail);

		if (flag) { // Opzionale: solo per ADMIN
	 		// Dettaglio 3
			orderDetail = document.createElement("div");
			orderDetail.className = "order-detail-item";
	
			// span 1
			spanDetail = document.createElement("span");
			spanDetail.className = "detail-label";
			spanDetail.innerHTML = "Username: ";
			orderDetail.appendChild(spanDetail);
	
			// span 2
			spanDetail = document.createElement("span");
			spanDetail.className = "detail-value";
			spanDetail.innerHTML = ord.username;
			orderDetail.appendChild(spanDetail);
	
			orderGroup.appendChild(orderDetail);
		}
		
		// Aggiungi all'header
		orderHeader.appendChild(orderGroup);
		
		
		// Nuovo Group
		
		orderGroup = document.createElement("div");
		orderGroup.className = "order-details-group";
		
		// Dettaglio
		orderDetail = document.createElement("div");
		orderDetail.className = "order-detail-item";

		// span 1
		spanDetail = document.createElement("span");
		spanDetail.className = "detail-label";
		spanDetail.innerHTML = "ID Ordine: ";
		orderDetail.appendChild(spanDetail);

		// span 2
		spanDetail = document.createElement("span");
		spanDetail.className = "detail-value";
		spanDetail.innerHTML = ord.numeroTransazione;
		orderDetail.appendChild(spanDetail);
		
		orderGroup.appendChild(orderDetail);

		// Aggiungi all'header
		orderHeader.appendChild(orderGroup);

		///			FINE - Header ordini

		// Aggiungi al summary
		orderSummary.appendChild(orderHeader);
		
		
		///			LISTA ELEMENTI ORDINE
		
		let orderItemsList = document.createElement("div");
		orderItemsList.className = "order-items-list"
		
		let listaElementi = ord.elementiOrdine;
		listaElementi.forEach(elem => {
			
			let itemCard = document.createElement("div");
			itemCard.className = "order-item-card";
			
			// Imposta url immagine
			let url;
			if (elem.urlImmagineArticolo === null || elem.urlImmagineArticolo === "") {
			    url = "/images/alt-prodotti.png"; // Fallback image path
			} else {
			    url = elem.urlImmagineArticolo; // Use the provided URL (which can already include context path)
			}
			
			let img = document.createElement("img");
			img.src = url;
			img.alt = elem.nomeArticolo;
			img.className = "order-item-img";
			itemCard.appendChild(img);
			
			// Dettaglio
			
			let elemDetail = document.createElement("div");
			elemDetail.className = "order-item-details";
			
			// 1
			let detail = document.createElement("div");
			detail.className = "order-item-name";
			detail.innerHTML = elem.nomeArticolo;
			elemDetail.appendChild(detail);
			
			// 2
			detail = document.createElement("div");
			detail.className = "order-item-qty";
			detail.innerHTML = "Qtà: " + elem.quantitaArticolo;
			elemDetail.appendChild(detail);

			// 3
			detail = document.createElement("div");
			detail.className = "order-item-price-per-unit";
			detail.innerHTML = "Prezzo Unitario: " + elem.prezzoUnitario.toFixed(2) + " €";
			elemDetail.appendChild(detail);
			
			//
			
			itemCard.appendChild(elemDetail);
			
			// Prezzo totale
			
			let totalPrice = document.createElement("div");
			totalPrice.className = "order-item-total-price";
			let temp = (elem.prezzoUnitario * elem.quantitaArticolo).toFixed(2);
			totalPrice.innerHTML = temp + " €";
			
			itemCard.appendChild(totalPrice);
			
			orderItemsList.appendChild(itemCard);
			
		});
		
		orderSummary.appendChild(orderItemsList);
		
		container.appendChild(orderSummary);
	});
}



