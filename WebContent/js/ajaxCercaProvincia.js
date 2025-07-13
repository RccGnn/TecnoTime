// Array di oggetti { provincia, sigla }
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


// All'avvio, popola il datalist con tutte le province disponibili
(function() {
    let dataList = document.getElementById("provinceList");
    if (!dataList) {
        // console.error("Elemento datalist 'provinceList' non trovato.");
        return;
    }

    allProvincesSigles.forEach(function(item) { 	// Funzione che un'opzione per ogni provincia nel datalist (primo avvio)
        let option = document.createElement("option");
        option.value = item.provincia; // usa il nome della provincia come valore
        dataList.appendChild(option);
    });
})();

/**
 * Funzione che filtra le opzioni del datalist in base al testo inserito nell'input provincia.
 * Vengono mostrati solo i nomi delle province che iniziano con il testo digitato (case-insensitive).
 */
function cercaProvincia() {
    let inputElem = document.getElementById("province");
    let filter = inputElem.value.trim().toLowerCase(); // Per uniformare la stringa
    let dataList = document.getElementById("provinceList");
    dataList.innerHTML = "";
    // Ricerca tra tutte le province
    allProvincesSigles.forEach(function(item) { // Funzione per il confronto: si verifica se il nome della provincia inizia col testo inserito dall'utente
        if (item.provincia.toLowerCase().startsWith(filter)) {
            let option = document.createElement("option");
            option.value = item.provincia; // aggiunge come opzione il nome della provincia
            dataList.appendChild(option); // aggiunge opzione alla lista
        }
    });
}
