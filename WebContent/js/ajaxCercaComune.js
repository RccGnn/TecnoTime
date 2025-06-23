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

// Array JSON contente tutte le provincie e le loro sigle
var allProvincesSigles = [
  { "provincia": "Agrigento", "sigla": "AG" },
  { "provincia": "Alessandria", "sigla": "AL" },
  { "provincia": "Ancona", "sigla": "AN" },
  { "provincia": "Aosta", "sigla": "AO" },
  { "provincia": "Arezzo", "sigla": "AR" },
  { "provincia": "Ascoli Piceno", "sigla": "AP" },
  { "provincia": "Asti", "sigla": "AT" },
  { "provincia": "Avellino", "sigla": "AV" },
  { "provincia": "Bari", "sigla": "BA" },
  { "provincia": "Belluno", "sigla": "BL" },
  { "provincia": "Benevento", "sigla": "BN" },
  { "provincia": "Bergamo", "sigla": "BG" },
  { "provincia": "Biella", "sigla": "BI" },
  { "provincia": "Bologna", "sigla": "BO" },
  { "provincia": "Bolzano", "sigla": "BZ" },
  { "provincia": "Brescia", "sigla": "BS" },
  { "provincia": "Brindisi", "sigla": "BR" },
  { "provincia": "Cagliari", "sigla": "CA" },
  { "provincia": "Caltanissetta", "sigla": "CL" },
  { "provincia": "Campobasso", "sigla": "CB" },
  { "provincia": "Caserta", "sigla": "CE" },
  { "provincia": "Catania", "sigla": "CT" },
  { "provincia": "Catanzaro", "sigla": "CZ" },
  { "provincia": "Chieti", "sigla": "CH" },
  { "provincia": "Como", "sigla": "CO" },
  { "provincia": "Cosenza", "sigla": "CS" },
  { "provincia": "Cremona", "sigla": "CR" },
  { "provincia": "Crotone", "sigla": "KR" },
  { "provincia": "Cuneo", "sigla": "CN" },
  { "provincia": "Enna", "sigla": "EN" },
  { "provincia": "Fermo", "sigla": "FM" },
  { "provincia": "Ferrara", "sigla": "FE" },
  { "provincia": "Firenze", "sigla": "FI" },
  { "provincia": "Foggia", "sigla": "FG" },
  { "provincia": "Forlì", "sigla": "FC" },
  { "provincia": "Frosinone", "sigla": "FR" },
  { "provincia": "Genova", "sigla": "GE" },
  { "provincia": "Gorizia", "sigla": "GO" },
  { "provincia": "Grosseto", "sigla": "GR" },
  { "provincia": "Imperia", "sigla": "IM" },
  { "provincia": "Isernia", "sigla": "IS" },
  { "provincia": "L'Aquila", "sigla": "AQ" },
  { "provincia": "La Spezia", "sigla": "SP" },
  { "provincia": "Latina", "sigla": "LT" },
  { "provincia": "Lecce", "sigla": "LE" },
  { "provincia": "Lecco", "sigla": "LC" },
  { "provincia": "Livorno", "sigla": "LI" },
  { "provincia": "Lodi", "sigla": "LO" },
  { "provincia": "Lucca", "sigla": "LU" },
  { "provincia": "Macerata", "sigla": "MC" },
  { "provincia": "Mantova", "sigla": "MN" },
  { "provincia": "Massa", "sigla": "MS" },
  { "provincia": "Matera", "sigla": "MT" },
  { "provincia": "Messina", "sigla": "ME" },
  { "provincia": "Milano", "sigla": "MI" },
  { "provincia": "Modena", "sigla": "MO" },
  { "provincia": "Monza", "sigla": "MB" },
  { "provincia": "Napoli", "sigla": "NA" },
  { "provincia": "Novara", "sigla": "NO" },
  { "provincia": "Nuoro", "sigla": "NU" },
  { "provincia": "Oristano", "sigla": "OR" },
  { "provincia": "Padova", "sigla": "PD" },
  { "provincia": "Palermo", "sigla": "PA" },
  { "provincia": "Parma", "sigla": "PR" },
  { "provincia": "Pavia", "sigla": "PV" },
  { "provincia": "Perugia", "sigla": "PG" },
  { "provincia": "Pesaro", "sigla": "PU" },
  { "provincia": "Pescara", "sigla": "PE" },
  { "provincia": "Piacenza", "sigla": "PC" },
  { "provincia": "Pisa", "sigla": "PI" },
  { "provincia": "Pistoia", "sigla": "PT" },
  { "provincia": "Pordenone", "sigla": "PN" },
  { "provincia": "Potenza", "sigla": "PZ" },
  { "provincia": "Prato", "sigla": "PO" },
  { "provincia": "Ragusa", "sigla": "RG" },
  { "provincia": "Ravenna", "sigla": "RA" },
  { "provincia": "Reggio di Calabria", "sigla": "RC" },
  { "provincia": "Reggio nell'Emilia", "sigla": "RE" },
  { "provincia": "Rieti", "sigla": "RI" },
  { "provincia": "Rimini", "sigla": "RN" },
  { "provincia": "Roma", "sigla": "RM" },
  { "provincia": "Rovigo", "sigla": "RO" },
  { "provincia": "Salerno", "sigla": "SA" },
  { "provincia": "Sassari", "sigla": "SS" },
  { "provincia": "Savona", "sigla": "SV" },
  { "provincia": "Siena", "sigla": "SI" },
  { "provincia": "Siracusa", "sigla": "SR" },
  { "provincia": "Sondrio", "sigla": "SO" },
  { "provincia": "Taranto", "sigla": "TA" },
  { "provincia": "Teramo", "sigla": "TE" },
  { "provincia": "Terni", "sigla": "TR" },
  { "provincia": "Torino", "sigla": "TO" },
  { "provincia": "Trani", "sigla": "BT" },
  { "provincia": "Trapani", "sigla": "TP" },
  { "provincia": "Trento", "sigla": "TN" },
  { "provincia": "Treviso", "sigla": "TV" },
  { "provincia": "Trieste", "sigla": "TS" },
  { "provincia": "Udine", "sigla": "UD" },
  { "provincia": "Varese", "sigla": "VA" },
  { "provincia": "Venezia", "sigla": "VE" },
  { "provincia": "Verbania", "sigla": "VB" },
  { "provincia": "Vercelli", "sigla": "VC" },
  { "provincia": "Verona", "sigla": "VR" },
  { "provincia": "Vibo Valentia", "sigla": "VV" },
  { "provincia": "Vicenza", "sigla": "VI" },
  { "provincia": "Viterbo", "sigla": "VT" }
]

/**
 * Dopo la selezione di una provincia (da datalist), vengono caricati i comuni tramite l'API ISTAT.
 * Se la provincia non è valida, disabilita il campo select dei comuni.
 */
function cercaComune() {
    var provName      = document.getElementById("province").value.trim();
    var selectComuni  = document.getElementById("city");

    // Disabilità campo City fintanto che non viene inserita una città valida
    selectComuni.innerHTML = '<option value="">Seleziona città…</option>';
    selectComuni.disabled  = true;

    // Trova la sigla associata alla provincia 
    var provinceSigle = allProvincesSigles.find(function(item) { // Funzione di 'find'
        return item.provincia.toLowerCase() === provName.toLowerCase();
    });
    if (!provinceSigle) {
		// Equivalente di error and output di System (java)
        console.warn("Provincia non valida:", provName);
        return;
    }

    var sigla  = provinceSigle.sigla;
    var apiUrl = "https://comuni-istat-api.belicedigital.com/api/provincia/"
               + encodeURIComponent(sigla)
               + "/comuni";

    // console.log("Chiamata ad API comuni:", apiUrl);

    //url, metodo, params, callback(xhr)
    loadAjaxDoc(apiUrl, "GET", null, function(xhr) {
        // console.log("Status comuni:", xhr.status);
        // console.log("ResponseText comuni:", xhr.responseText);

        var arr;
        try {
			// Conversione JSON --> OBJECT
            arr = JSON.parse(xhr.responseText);
        } catch (e) {
            return console.error("Impossibile parsare JSON:", e);
        }
        // console.log("Ricevuti comuni:", arr.length);

        // Popolamento select di City
        arr.forEach(function(c) { // Funzione appliacata ad ogni elemento di arr
            var o = document.createElement("option");
			o.text  = (typeof c === "string" ? c : (c.nome || c.comune || c.denominazione));
			o.value = o.text;
            selectComuni.add(o);
        });
        selectComuni.disabled = false;
    });
}