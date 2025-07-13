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


// PULSANTE REMOVE ALL

function removeAll() {
	
	let userId = document.getElementById("usernameId").value;	
	let carrelloId = document.getElementById("cartId").value;

	const params = "username="+encodeURIComponent(userId)+"&carrelloId="+encodeURIComponent(carrelloId);
	const url = "ModifyCart";
	loadAjaxDoc(url, "GET", params, handleClean);
}

function handleClean(xhr) {
	let result = Boolean(xhr.responseText);
	if(!result)
		return; // Messaggio d'errore
	setEmptyCart();
}

function setEmptyCart (){
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

// PULSANTI + E -

function varyAmount(clickedElement) {
	
	let userId = document.getElementById("usernameId").value;	
	let carrelloId = document.getElementById("cartId").value;

	let elem = clickedElement.id // L'id è strutturato come [id_name][ARTXXX]
	let choice = "";
	let productId = "";
	
	if (elem.includes("decrement")) {
		choice = "giu"; // Imposta la stringa per determinare l'operazione da fare
		productId = elem.substring("decrement".length);	// Recupera il codice identificativo del prodotto
	}
	else if (elem.includes("increment")) {
		choice = "su";
		productId = elem.substring("increment".length);
	} else if (elem.includes("remove")) {
		choice = "rem";
		productId = elem.substring("remove".length);
	}
	
	const params = "username="+encodeURIComponent(userId)+"&carrelloId="+encodeURIComponent(carrelloId)
					+"&choice="+encodeURIComponent(choice)+"&productId="+encodeURIComponent(productId);
	const url = "ModifyCart";
	
	loadAjaxDoc(url, "GET", params, handleVary);
}


function handleVary(xhr) {
	let product = JSON.parse(xhr.responseText);
	const choice = product[0];
	const article = product[1];
	const remQuantity = product[2];
	
	let output = document.getElementById("current-quantity-"+article.codiceIdentificativo);
	
	if(choice == "err") { // notifica l'utente dell'errore
		output.style.color = 'red';
		output.style.fontWeight = 'bold';
		setTimeout(function() {
			output.style.color = '';		//
			output.style.fontWeight = ''; 	// default
		}, 500/*ms*/);
		
		return;
	}	
	
	// Modifica il numero di elementi nel carrello
	let cartSize = document.getElementById("your-cart");
	let numElements = parseInt(cartSize.innerHTML.replace("IL TUO CARRELLO ( ", "").replace(" )", ""));
	
	// Modfica il numero che mostra la quantità
    // let output = document.getElementById("current-quantity-"+article.codiceIdentificativo);
	let value1 = parseInt(output.innerHTML);
	
	// Modifica il numero che mostra il subtotale
	let subtotal = document.getElementById("subtotal");
	// Elimina spazi, € e sostituisci la virgola col punto
	let value2 = parseFloat(subtotal.innerHTML.replace("€", "").trim().replace(",", "."));
	
	// Modifica il totale
	let total = document.getElementById("total");
	
	let subClass = articoloEnum(article);

	if (choice == "down") {
		cartSize.innerHTML = numElements - 1;
		output.innerHTML = value1 - 1;
		subtotal.innerHTML = (value2 - subClass.prezzo).toFixed(2);
	} else if (choice == "up") {
		cartSize.innerHTML = numElements + 1;
		output.innerHTML = value1 + 1;
		subtotal.innerHTML = (value2 + subClass.prezzo).toFixed(2);
	} else if (choice == "rem") {
		console.log("numElements: " + numElements+"\nremQuantity" + remQuantity);
		if(numElements - remQuantity <= 0) // Caso: sono stati eliminati tutti gli articoli
			setEmptyCart();
		else {
			cartSize.innerHTML = numElements - remQuantity;
			subtotal.innerHTML = (value2 - (subClass.prezzo * remQuantity)).toFixed(2);
			
			// Elimimina la entry nel carrello class="cart-item-card<%=aID%>
			let cartItem = document.getElementById("cart-item"+subClass.codiceIdentificativo);
			cartItem.remove();
		}
	}

	// Formatta a due cifre decimali, il punto a virgola, e aggiungi €
	cartSize.innerHTML = "IL TUO CARRELLO ( " + cartSize.innerHTML + " )";
	subtotal.innerHTML = subtotal.innerHTML.replace(".", ",") + " €";
	total.innerHTML = subtotal.innerHTML;
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