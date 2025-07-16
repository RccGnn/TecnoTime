document.addEventListener('DOMContentLoaded', () => {
  const toggleBtn = document.getElementById('filter-toggle-btn');
  const sidebar   = document.getElementById('filters-sidebar');
  const mobileBp  = 768;

  toggleBtn.addEventListener('click', () => {
    sidebar.classList.toggle('visible');
  });

  document.addEventListener('click', e => {
    if (window.innerWidth <= mobileBp) {
      if (!sidebar.contains(e.target) && e.target !== toggleBtn) {
        sidebar.classList.remove('visible');
      }
    }
  });


  window.addEventListener('resize', () => {
    if (window.innerWidth > mobileBp) {
      sidebar.classList.remove('visible');
    }
  });
});


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


function loadAjaxDoc(url, method, params, cFuction, reqHeader) {
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
		
		
		if (method.toLowerCase() === "get") {
		  request.open("GET", params ? `${url}?${params}` : url, true); // Simile alle fString di pyhton
		  request.send(null);
		}

		// Metodo usato: POST
		 else {
			
				// Se i parametri sono presenti
				request.open("POST", url, true);
				request.setRequestHeader("Content-Type", reqHeader);
				request.send(params);
		}
		
	}
}

// Funzione per la gestione delle pagine
function contexEnum(contex) {
	let flag = 0;
	if (contex.includes("articoliProdotti.jsp"))  
		flag = 1;
	if (contex.includes("articoliLicenze.jsp"))  
		flag = 2;
	if (contex.includes("articoliServizi.jsp"))  
		flag = 3;
	if (contex.includes("DisplaySubMenu1"))
		flag = 4;
	
	return flag;		
}

// Funzione per determinare le classi dei articoli 
function articoloEnum(articolo) {
	let subClass;
	
	if (articolo.pdFisico != null) 
		subClass = articolo.pdFisico;
	if (articolo.pdDigitale != null)  
		subClass = articolo.pdDigitale;
	if (articolo.servizio != null)  
		subClass = articolo.servizio;
	
	return subClass;		
}

// Funzione di parsing dell'input dei filtri
function sortedProducts() {
	const minInput = parseFloat(document.getElementById("min").value);
	const maxInput = parseFloat(document.getElementById("max").value);
	const sortInput = document.getElementById("sort").value;	
	const nomeInput = document.getElementById("name").value;
	const contestoInput = window.location.pathname;
	let durataInput = "";
	let categoriaInput = "";	
	let contexChoice = contexEnum(contestoInput);

	// In base a quale pagina effettua la chiamata ajax, si impostano i parametri da passare nel GET 
	if (contexChoice === 1 || contexChoice === 4) { // Prodotti fisici
		categoriaInput = document.getElementById("categoria").value;
	} else if (contexChoice === 2) { // Prodotti digitali
	} else if (contexChoice === 3) { // Servizi 
		durataInput = document.getElementById("duration").value;
	} 
	
	let params = 	"min="+ encodeURIComponent(minInput) +"&max="+ encodeURIComponent(maxInput)
					+"&sort="+ encodeURIComponent(sortInput) +"&name="+ encodeURIComponent(nomeInput)
					+"&contex="+ encodeURIComponent(contestoInput) +"&duration="+ encodeURIComponent(durataInput)
					+"&categoriaInput="+ encodeURIComponent(categoriaInput);
	
	loadAjaxDoc("ProductFilter", "GET", params, handleFilter, "application/x-www-form-urlencoded");
}


function cleanSection() {
	let articoli = document.querySelector('.products-container');

	while (articoli.firstChild) {
		articoli.removeChild(articoli.firstChild);
	}
}

// Funzione di gestione della visualizzazione degli articoli
function handleFilter(xhr) {
	let response = JSON.parse(xhr.responseText);
	// Si ripuliscono gli articoli già presenti
	cleanSection();
			
	// Eventuale errore se non ci sono articoli 
	if (!response[0] || (response[0] && response[0].length === 0)) {
		let noResults = document.createElement("p");
		noResults.textContent = "Nessun articolo trovato \n(._.)";
		noResults.className = "error-subtitle";
		element.appendChild(noResults);
		return; // Termina la funzione se non ci sono prodotti
	}

	let element = document.querySelector(".products-container"); // E' unico
	// Ricava la lista di articoli e di promozioni
	let articoli = response[0];
	let promozioni = response[1];

	// Itera per ogni prodotto della lista response
	articoli.forEach(art => {
		let articolo = document.createElement("div");
		let subClass = articoloEnum(art);
		
		articolo.className = "product-card";
		
		let linkImg = document.createElement("a");
		let img = document.createElement("img");

		let immagini = art.immagini;
		if (immagini.length != 0) {
			img.src = immagini[0].url;
		} else {
		    img.src = '/TecnoTime/images/alt-prodotti.png';
		}
		
		linkImg.href = 'DisplayProductPage?id='+encodeURIComponent(art.codiceIdentificativo);
		img.alt = "Immagine articolo: "+art.nome;
		img.className = "product-image";
		img.onerror = function() {
			// rimuovi onerror per evitare loop se anche il sostituto manca
			img.onerror = null;
			// punto al placeholder
			img.src = '/TecnoTime/images/alt-prodotti.png';
		};
		linkImg.appendChild(img); // Aggiungi l'immagine come figlio del link 
		articolo.appendChild(linkImg);
		
		let title = document.createElement("h3");
		title.innerHTML = art.nome;
		title.className = "product-name";
		articolo.appendChild(title);
		
		// <p class="price">€400 <span class="old-price">€450</span></p>
		
			let price = document.createElement("p");
			price.innerHTML = subClass.prezzo.toFixed(2) +" €";
			price.className = "product-price";
			articolo.appendChild(price);
		
		let btn = document.createElement('button');		
		if(subClass.disponibilita) {
			btn.className = 'add-to-cart-btn';
			btn.innerHTML = 'Aggiungi al carrello';
			btn.onclick = function () {
				let articolo = JSON.stringify(art); // Chiama la servlet per aggiungere l'elemento al carrello
				loadAjaxDoc("CartServlet", "POST", articolo, showNotification, "application/json");
			}
		} else {
			btn.className = 'out-of-stock-cart-btn';
			btn.innerHTML = 'Scorte esaurite';			
		}
		articolo.appendChild(btn);
		
		element.appendChild(articolo);
	});
}

window.onload = sortedProducts;


function displaySlider() {
	let slider = document.getElementById("slider");
	let sliderValue = document.getElementById("duration");
	
	if(sliderValue.value == 1)
		slider.innerHTML = sliderValue.value + " giorno";
	else
		slider.innerHTML = sliderValue.value + " giorni";
}


function showNotification(xhr) {
	let response = xhr.responseText;
	console.log(response);
	let notifica = document.getElementById('notification');
  	if (notifica) {
    	notifica.innerHTML = "Aggiunto al carrello con successo";
    	notifica.classList.add('show'); // Aggiungi più classi
		
    	setTimeout(() => { // Imposta il tempo di visualizzazione
      		notifica.classList.remove('show');
    	}, 1000); // 3000 ms 
  	}
}