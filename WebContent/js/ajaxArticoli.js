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

/*
<!-- GRID PRODOTTI -->
<section class="products-container">
    <div class="product-card">
      <img src="${p.imageURL}" alt="${p.name}" class="product-image"/>
      <h3 class="product-name">${p.name}</h3>
      <p class="product-price">€ ${p.price}</p>
      <p class="product-description">${p.description}</p>
      <button class="add-to-cart-btn">Aggiungi al carrello</button>
    </div>
</section>
*/

// Funzione di parsing dell'input dei filtri
function sortedProducts() {
	const minInput = parseFloat(document.getElementById("min").value);
	const maxInput = parseFloat(document.getElementById("max").value);
	const sortInput = document.getElementById("sort").value;	
	const nomeInput = document.getElementById("name").value;
	
	console.log(window.location.pathname);
	let params = 	"min="+ encodeURIComponent(minInput) +"&max="+ encodeURIComponent(maxInput)
					+"&sort="+ encodeURIComponent(sortInput) +"&name="+ encodeURIComponent(nomeInput);
	
	loadAjaxDoc("ProductFilter", "GET", params, handleFilter);
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
	// Eventuale errore
	// Si ripuliscono gli articoli già presenti
	cleanSection();
	
	let element = document.querySelector(".products-container"); // E' unico
	// Itera per ogni prodotto della lista response
	response.forEach(art => {
		let articolo = document.createElement("div");
		articolo.className = "product-card";
		
		let img = document.createElement("img");
		img.src = art.immagine;
		img.alt = "Immagine articolo"+art.nome;
		img.className = "product-image";
		img.onerror = function() {
			// rimuovi onerror per evitare loop se anche il sostituto manca
			img.onerror = null;
			// punto al placeholder
			img.src = '/TecnoTime/images/alt-prodotti.png';
		};	
		articolo.appendChild(img);
		
		let title = document.createElement("h3");
		title.innerHTML = art.nome;
		title.className = "product-name";
		articolo.appendChild(title);
		
		let price = document.createElement("p");
		price.innerHTML = art.prezzo+"€";
		price.className = "product-price";
		articolo.appendChild(price);

		let descr = document.createElement("p");
		descr.innerHTML = art.descrizione;
		descr.className = "product-description";
		articolo.appendChild(descr);
		
		let btn = document.createElement('button');
		btn.className = 'add-to-cart-btn';
		btn.innerHTML = 'Aggiungi al carrello';
		articolo.appendChild(btn);
		
		element.appendChild(articolo);
	});
	
	
}


