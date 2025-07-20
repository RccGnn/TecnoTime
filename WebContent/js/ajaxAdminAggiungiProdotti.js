
// Per tutte le label di tipo testuale
function appendLabelText(labelName, placeHolder, displayElements) {
	let label = document.createElement("label");
	label.htmlFor = labelName;
	label.innerHTML = placeHolder;

	let input = document.createElement("input");
	input.type = "text";
	input.name = labelName;
	input.id = labelName;
	input.required = true;

	displayElements.appendChild(label);
	displayElements.appendChild(input);	
}

function appendLabelTextPH(labelName, placeHolder, displayElements, exampleText) {
	let label = document.createElement("label");
	label.htmlFor = labelName;
	label.innerHTML = placeHolder;

	let input = document.createElement("input");
	input.type = "text";
	input.name = labelName;
	input.id = labelName;
	input.placeholder = exampleText;
	input.required = true;

	displayElements.appendChild(label);
	displayElements.appendChild(input);	
}

// Per tutte le label di tipo numerico
function appendLabelNumber(labelName, placeHolder, displayElements, minValue) {
	let label = document.createElement("label");
	label.htmlFor = labelName;
	label.innerHTML = placeHolder;

	let input = document.createElement("input");
	input.type = "number";
	input.name = labelName;
	input.id = labelName;
	input.required = true;
	input.min = minValue;
	
	displayElements.appendChild(label);
	displayElements.appendChild(input);	
}

// Per tutte le label di tipo decimale
function appendLabelDecimail(labelName, placeHolder, displayElements, minValue) {
	let label = document.createElement("label");
	label.htmlFor = labelName;
	label.innerHTML = placeHolder;

	let input = document.createElement("input");
	input.type = "number";
	input.step = "any"
	input.name = labelName;
	input.id = labelName;
	input.required = true;
	input.min = minValue;
	
	displayElements.appendChild(label);
	displayElements.appendChild(input);	
}

function appendLabelDate(labelName, placeHolder, displayElements) {
	let label = document.createElement("label");
	label.htmlFor = labelName;
	label.innerHTML = placeHolder;

	let input = document.createElement("input");
	input.type = "date";
	input.name = labelName;
	input.id = labelName;
	input.required = true;
	
	displayElements.appendChild(label);
	displayElements.appendChild(input);	
}


function modifyForm() {
	// Recupera il tipo di prodotto selezionato
	let tipologia = document.getElementById("tipologia").value;
	
	let displayElements = document.getElementById("optionElements");
	while (displayElements.firstChild)
		displayElements.removeChild(displayElements.firstChild);
	
	
	// Aggiungi bottone per impostare la durata
	if (tipologia == "servizio") {
		appendLabelNumber("durata", "Durata (ora):", displayElements, 1);
		return;
	}

	// Aggiungi bottoni per impostare la quantità
	if (tipologia != "servizio") {
		appendLabelNumber("quantita", "Quantità: ", displayElements, 1);
	}
	
	if (tipologia != "servizio" && tipologia != "licenza") {
		appendLabelTextPH("seriale", "Aggiungi seriale: ", displayElements, "es: PDF-9999999");
	}
	
	if (tipologia == "processore") {

		appendLabelTextPH("marca", "Marca: ", displayElements, "es: Intel");
		appendLabelTextPH("socket", "Nome socket: ", displayElements, "es: AM4");
		appendLabelDate("dataRilascio", "Data di rilascio: ", displayElements);
		appendLabelNumber("watt", "Wattaggio: ", displayElements, 1);
	}
	// Scheda madre
	if (tipologia == "scheda_madre") {
				
		appendLabelTextPH("marca", "Marca: ", displayElements, "es: Intel");
		appendLabelTextPH("socket", "Nome socket: ", displayElements, "es: AM4");
		appendLabelTextPH("dimensione", "Dimensioni: ", displayElements, "es: E-ATX");
		appendLabelTextPH("SupportoRam", "Supporto RAM: ", displayElements, "es: DDR5");
		appendLabelNumber("Watt", "Wattaggio: ", displayElements, 1);
	}
	// RAM
	if (tipologia == "RAM") {
		appendLabelTextPH("marca", "Marca: ", displayElements, "es: Intel");
		appendLabelNumber("capacita", "Capacità: ", displayElements, 1);
		appendLabelTextPH("SupportoRam", "Supporto RAM: ", displayElements, "es: DDR5");
	}
	// Scheda Video
	if (tipologia == "scheda_video") {
		appendLabelTextPH("marca", "Marca: ", displayElements, "es: Intel");
		appendLabelDecimail("PCI", "Inserisci PCI: ", displayElements);
		appendLabelNumber("vram", "VRAM: ", displayElements, 1);
		appendLabelTextPH("tipoRam", "Tipo di RAM: ", displayElements, "GDDR7");
		appendLabelNumber("Watt", "Wattaggio: ", displayElements, 1);		
	}
	// _Case
	if (tipologia == "_case") {
		appendLabelTextPH("dimensione", "Dimensioni: ", displayElements, "es: E-ATX");
		appendLabelTextPH("marca", "Marca: ", displayElements, "es: Intel");
	}

	// Alimentatori
	if (tipologia == "alimentatori") {
		appendLabelTextPH("marca", "Marca: ", displayElements, "es: Intel");
		appendLabelNumber("watt", "Wattaggio: ", displayElements, 1);
	}
		
}

