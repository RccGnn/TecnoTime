document.addEventListener('DOMContentLoaded', () => {
	document.querySelectorAll('.quantity-input').forEach(input => {
		input.addEventListener('change', (e) => {
			const form = e.target.closest('form');
			form.submit();
		});
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
		
		// Metodo usato: GET
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


function removeAll() {
	
	let userId = document.getElementById("usernameId").value;	
	let carrelloId = document.getElementById("cartId").value;

	const params = "username="+encodeURIComponent(userId)+"&carrelloId="+encodeURIComponent(carrelloId);
	const url = "EmptyCart";
	loadAjaxDoc(url, "GET", params, handleClean);
}

function handleClean(xhr) {
	let result = Boolean(xhr.responseText);
	console.log(result);
	if(!result)
		return; // Messaggio d'errore

	let root = document.getElementsByClassName("cart-layout")[0];		
	//let articoli = document.querySelector('.products-container');

	while (root.firstChild) {
		root.removeChild(root.firstChild);
	}
	
	// Recupera il riferimento al contenitore principale
	let cartConteiner = document.getElementsByClassName("cart-page-container")[0];
	
	// crea il cart vuoto
	let emptyCart = document.createElement("div");
	emptyCart.className = "empty-cart-container";
	
	let i = document.createElement("p");
	i.className = "fas fa-shopping-cart empty-cart-icon";
	emptyCart.appendChild(i);
	
	let h1 = document.createElement("h1"); 
	h1.innerHTML = "Il tuo carrello è vuoto";
	emptyCart.appendChild(h1);
	
	let p = document.createElement("p");
	p.innerHTML = "Aggiungi prodotti per vederli qui.";
	emptyCart.appendChild(p);
	
	let a = document.createElement("a");
	a.innerHTML = "Vai ai prodotti";
	a.href = "ProductServlet";
	a.className = "btn-primary";
	emptyCart.appendChild(a);
	
	cartConteiner.appendChild(emptyCart);
}
