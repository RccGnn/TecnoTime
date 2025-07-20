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
function disableInput(){
	
	document.addEventListener('DOMContentLoaded', () => {
		
		const moboInput  = document.getElementById('motherboardInput');
		const cpuInput   = document.getElementById('processorInput');
		const ramInput   = document.getElementById('ramInput');
		const ctx        = document.getElementById('ContextPath').value;
		
		const raw      = moboInput.value.trim();
		const nomeM    = raw.split(" -")[0];
		const params   = "nameM=" + encodeURIComponent(nomeM);
		
		loadAjaxDoc("FetchProduct","GET",params,FilterProduct)
		
	});


function FilterProduct(xhr){
	
	
	document.getElementById('processorInput');
	
	 const data = JSON.parse(xhr.responseText);
	 const cpus = data.artcpu;   // lista di processori
	 const rams = data.artram;   // lista di RAM

	 
	 const cpuDatalist = document.getElementById('processoriList');
	 cpuDatalist.innerHTML = '';          
	 artcpu.forEach(cpu => {
	   const opt = document.createElement('option');
	   // imposto value come nome + prezzo
	   opt.value = `${cpu.nome} - €${cpu.pdFisico.prezzo}`;
	   // se vuoi tener traccia dell'ID:
	   opt.dataset.id = cpu.codiceIdentificativo;
	   cpuDatalist.appendChild(opt);
	 });
	 
	 const ramDatalist = document.getElementById('ramList');
	 ramDatalist.innerHTML= '';
	 artram.forEach(ram =>{
		const opt = document.createElement('option');
		   // imposto value come nome + prezzo
		   opt.value = `${ram.nome} - €${ram.pdFisico.prezzo}`;
		   // se vuoi tener traccia dell'ID:
		   opt.dataset.id = ram.codiceIdentificativo;
		   cpuDatalist.appendChild(opt);
		
		
		
	 }
	
}		
	
	
}
