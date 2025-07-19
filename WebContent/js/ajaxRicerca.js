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

function search() {
	const context= document.getElementById("ContextPath").value;
	const input = document.getElementById("search-bar").value.trim();
	const search = true;
	const url = context+"/ProductFilter";
	let params = "name=" + encodeURIComponent(input) + "&fromSearchBar=" + encodeURIComponent(search);
	
	loadAjaxDoc(url, "GET", params, handleDisplaySearch);  
}

function hideSearchBar() {
	let root = document.getElementById("search-results");
	root.style.display = "none";
}


function handleDisplaySearch(xhr) {
	let searchResults = JSON.parse(xhr.responseText);

		// Recupera il display per gli articoli
	let res = document.getElementById("search-results");
		
	// Cancella i risultati precedenti prima di mostrare quelli nuovi
	while(res.firstChild)
		res.removeChild(res.firstChild);

	searchResults[0].forEach(result => { // Per ogni articolo trovato
		// Crea il risultato della ricerca
		let suggItem = document.createElement("div");
		suggItem.className = "suggestion-item";
		
		// Crea il link per caricare la pagina dei prodotti singoli
		let linkToDisplayPage = document.createElement("a");
		linkToDisplayPage.href = 'DisplayProductPage?id='+encodeURIComponent(result.codiceIdentificativo);
		linkToDisplayPage.innerHTML = result.nome;
		
		// Collega link <-- risultato
		suggItem.appendChild(linkToDisplayPage);
		// Collega risultato <-- display risultati
		res.appendChild(suggItem);
		
	});
	
	res.style.display = "block";
}
