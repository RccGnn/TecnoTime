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
	
	loadAjaxDoc("ProductFilter", "GET", params, handleFilter, "application/x-www-form-urlencoded");
}

function displaySlider() {
	let slider = document.getElementById("slider");
	let sliderValue = document.getElementById("duration");
	
	if(sliderValue.value == 1)
		slider.innerHTML = sliderValue.value + " giorno";
	else
		slider.innerHTML = sliderValue.value + " giorni";
}

