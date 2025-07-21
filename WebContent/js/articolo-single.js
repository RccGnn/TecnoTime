document.addEventListener('DOMContentLoaded', () => {
	const mainImage = document.getElementById('productsingle-main-image');
	const thumbs    = document.querySelectorAll('.productsingle-thumb');
	const form      = document.getElementById('productsingle-add-form');

	// Cambio immagine al click sulla miniatura
	thumbs.forEach(thumb => {
		thumb.addEventListener('click', () => {
			mainImage.src = thumb.src;
		});
	});

	// ZOOM desktop on hover
	if (window.innerWidth > 768) {
		mainImage.addEventListener('mousemove', e => {
			const { left, top, width, height } = mainImage.getBoundingClientRect();
			const x = (e.clientX - left) / width * 100;
			const y = (e.clientY - top) / height * 100;
		mainImage.style.transformOrigin = `${x}% ${y}%`;
		mainImage.style.transform       = 'scale(2)';
		});
		
	mainImage.addEventListener('mouseleave', () => {
		mainImage.style.transform = 'scale(1)';
	});
	
	} else {
	// ZOOM mobile on click
	let zoomed = false;
	mainImage.addEventListener('click', () => {
		zoomed = !zoomed;
		mainImage.style.transformOrigin = 'center center';
		mainImage.style.transform       = zoomed ? 'scale(2)' : 'scale(1)';
	});
  	}
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

function addProducts() {
	const productId = document.getElementById("productId").value;
	const quantity = parseInt(document.getElementById("productsingle-quantity").value);
	
	const url = "ArticleSingleCartServlet";
	let params = "productId=" +encodeURIComponent(productId)+ "&quantity=" +encodeURIComponent(quantity);
	
	console.log(params);
	loadAjaxDoc(url, "GET", params, showNotification);
}


function showNotification(xhr) {
  const message = JSON.parse(xhr.responseText);
  const notifica = document.getElementById('notification');
  notifica.textContent = message;
  notifica.classList.add('show');
  setTimeout(() => notifica.classList.remove('show'), 10000);
}
