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
		  request.open("GET", params ? `${url}?${params}` : url, true);
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

// Array globale che conterrà tutte le province
var allProvinces = [];

// Funzione di callback AJAX: riceve tutte le province e popola il datalist
function handleProvincie(xhr) {
  	allProvinces = JSON.parse(xhr.responseText);
  	renderOptionsDatalist(allProvinces);
}

// Funzione che popola il datalist
function renderOptionsDatalist(list) {
  	var datalist = document.getElementById("provinceList");
  	datalist.innerHTML = "";
  	list.forEach(function(p) { // Per ogni elemento p della lista, esegui la funzione dove p è una provincia
    	var opt = document.createElement("option");
    	opt.value = p;
    	datalist.appendChild(opt);
  });
}

// Funzione chiamata all'“Oninput” dell’id<input> che filtra le province
function filterDatalist() {
	var x = document.getElementById("province").value.trim().toLowerCase();
  	if (!x) { // Se non sono presenti provincie, mostra tutte le opzioni
    renderOptionsDatalist(allProvinces);
    return;
  }
  // Altrimenti mostra solo la lista di provincie filtrate
  var filtered = allProvinces.filter(function(name) {
    	return name.toLowerCase().indexOf(x) === 0;
  });
  renderOptionsDatalist(filtered);
}

// Funzione che chiama loadAjax usando come url l'API comuni-istat-api
function caricaProvince() {
  loadAjaxDoc(
    'https://comuni-istat-api.belicedigital.com/api/province',
    "GET",
    null,
    handleProvincie
  );
}
caricaProvince();