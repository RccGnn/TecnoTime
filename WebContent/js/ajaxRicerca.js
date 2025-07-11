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
	const input = document.getElementById("search-bar").value.trim();
	const search = true;
	const url = "ProductFilter";
	let params = "name=" + encodeURIComponent(input) + "&fromSearchBar=" + encodeURIComponent(search);
	
	loadAjaxDoc(url, "GET", params, handleDisplaySearch);  
}


function handleDisplaySearch(xhr) {
	let searchResults = JSON.parse(xhr.responseText);
	console.log(typeof(searchResults)+"\nLenght: "+searchResults.lenght);
	// Recupera il display per gli articoli
	let root = document.getElementById("search-results");
	
	// Se la ricerca non ha portato a risultati, nascondi l'elemento root
	if (!searchResults || searchResults.lenght == 0) {
		root.style.display = "none";
		return;
	} else { // Altrimenti rendilo visibile
		root.style.display = "block";
	}
	
	// Cancella i risultati precedenti prima di mostrare quelli nuovi
	while(root.firstChild)
		root.removeChild(root.firstChild);

	searchResults.forEach(result => {
		let suggItem = document.createElement("div");
		suggItem.className = "suggestion-item";
		suggItem.innerHTML = result.nome;
		
		root.appendChild(suggItem);
	});
}





