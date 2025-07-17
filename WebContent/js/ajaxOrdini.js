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


function sortedProducts() {
	const priceUbInput = parseFloat(document.getElementById("priceUpperBound").value);
	const priceLbInput = parseFloat(document.getElementById("priceLowerBound").value);
	const dateLbput = document.getElementById("dateLowerBound").value;	
	const dateUpInput = document.getElementById("dateUpperBound").value;
	
	//const contestoInput = window.location.pathname;
	
	let params = 	"min=" + encodeURIComponent(priceLbInput) +
	                "&max=" + encodeURIComponent(priceUbInput) +
	                "&dateLowerBound=" + encodeURIComponent(dateLbput) +
	                "&dateUpperBound=" + encodeURIComponent(dateUpInput);
	
	loadAjaxDoc("OrderFilter", "GET", params, handleFilter, "application/x-www-form-urlencoded");
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

	if(!response) {
		displayEmptyOrders();
	}
	
	cleanSection(); // Rimuovi tutti gli elementi tranne i filtri
	// Crea il container per gli ordini
	let container = document.createElement("div");
	container.className = "orders-list-page";
	
	// Se non ci sono prodotti, allora mostra un messaggio
	if(response == null || response.lenght == 0) {
		let mess = document.createElement("p");
		mess.className = "no-orders-message";
		container.appendChild(mess);
		
		return;
	}
	
	// Altrimenti genera gli ordini
	response.forEach(ord => {
		let orderSummary = document.createElement("div");
		orderSummary.className = "orders-summary-section";
		
		// Header 
		let orderHeader = document.createElement("div");
		orderHeader.className = "order-header";
		
		// Dettaglio per header ordini		
		let orderGroup = document.createElement("div");
		orderGroup.className = "order-details-group";
		
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
		spanDetail.innerHTML = "Data ordine: " + ord.dataTransazione;
		orderDetail.appendChild(spanDetail);

		orderGroup.appendChild(orderDetail);
		
		// Dettaglio 2
		orderDetail = document.createElement("div");
		orderDetail.className = "order-detail-item";

		// span 1
		spanDetail = document.createElement("span");
		spanDetail.className = "detail-label";
		spanDetail.innerHTML = "Data ordine:";
		orderDetail.appendChild(spanDetail);
				
	});
}



