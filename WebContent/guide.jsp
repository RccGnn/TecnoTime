<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/svg+xml" href="images/TecnoTimeIcon.svg">
    <title>Guide - TecnoTime</title>
    <!-- Riferimento al CSS comune -->
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <jsp:include page="header.jsp" />

   	<main class="assembly-guide">
   		<div class="responsive-yt-wrapper" id="yt-player-wrapper">
		  <div id="ytplayer-placeholder">
		    <img src="https://img.youtube.com/vi/DC-Xn2C_L1U/hqdefault.jpg" alt="Anteprima video">
		    <div class="yt-play-button-overlay">&#9658;</div>
		  </div>
		</div>

		<script src="js/youtubeguide-lazy.js" defer></script>
	  <section>
	    <h1>Guida Completa all'Assemblaggio del Tuo PC: Dalla Scelta dei Componenti al Primo Avvio</h1>
	    <p class="intro">Assemblare un PC su misura è un'esperienza gratificante che ti permette di creare una macchina perfettamente adatta alle tue esigenze. Il processo richiede un'attenta pianificazione, una selezione oculata dei componenti e un assemblaggio metodico. Questa guida ti accompagnerà in ogni fase del percorso.</p>
	  </section>
	
	  <section>
	    <h2>Capitolo 1: Pianificazione e Preparazione</h2>
	    <p class="intro2">Prima di acquistare un singolo componente, è fondamentale dedicare del tempo alla pianificazione. Questa fase getterà le basi per una build di successo.</p>
	
	    <h3>1.1 Definisci le Tue Esigenze</h3>
	    <p class="intro3">Il primo passo è chiederti: "A cosa mi servirà questo PC?". Lo scopo principale guiderà ogni tua scelta successiva.</p>
	    <ul>
	      <li><strong>Uso generico e da ufficio:</strong> Navigazione web, email, suite Office, streaming. Non richiede componenti di fascia alta; spesso è sufficiente una grafica integrata nel processore (iGPU).</li>
	      <li><strong>Gaming:</strong> Il focus principale è sulla scheda video (GPU) e sul processore (CPU) per garantire frame rate elevati e fluidità di gioco.</li>
	      <li><strong>Lavoro e Produttività (Editing Video, Grafica 3D, Programmazione):</strong> Richiede una CPU con molti core, abbondante RAM e, a seconda del software, una GPU potente per l'accelerazione hardware.</li>
	      <li><strong>Machine Learning / AI:</strong> La priorità assoluta è una GPU con un'elevata quantità di VRAM e potenza di calcolo.</li>
	    </ul>
	
	    <h3>1.2 Imposta un Budget e Verifica la Compatibilità</h3>
	    <p class="intro3">Una volta definite le necessità, stabilisci un budget realistico.</p>
	    <ul>
	      <li><strong>Assegna i Fondi:</strong> Suddividi il budget tra i vari componenti. Per un PC da gaming, ad esempio, una buona regola è allocare una parte significativa del budget alla coppia CPU-GPU.</li>
	      <li><strong>Crea una Configurazione Bilanciata:</strong> Evita i "colli di bottiglia" (bottleneck), dove un componente troppo lento limita le prestazioni di uno più veloce. Un processore di fascia bassa, ad esempio, frenerà una scheda video di fascia alta.</li>
	      <li><strong>Non risparmiare sull'alimentatore(PSU):</strong> È il cuore del tuo PC. Un alimentatore di scarsa qualità può essere inefficiente, rumoroso e, nel peggiore dei casi, danneggiare i tuoi componenti. Scegli sempre un prodotto di marca con una buona certificazione di efficienza.</li>
	      <li><strong>Verifica la Compatibilità Fisica:</strong> Assicurati che i componenti entrino fisicamente nel case. Controlla l'altezza massima del dissipatore della CPU, la lunghezza della GPU e il formato della scheda madre (ATX, Micro-ATX, Mini-ITX) supportati dal tuo case.</li>
	    </ul>
	  </section>
	
	  <section>
	    <h2>Capitolo 2: La Scelta dei Componenti</h2>
	    <p class="intro2"> Questa è la fase più divertente, dove scegli i "mattoni" della tua build.</p>
	    <h3>Processore (CPU)</h3>
	    <p class="intro3"> Il cervello del tuo computer. I due produttori principali sono Intel e AMD. La scelta dipende dal budget e dall'uso. Assicurati che il socket della CPU sia compatibile con quello della scheda madre (es. AM5 per le nuove CPU AMD 7000/9000, LGA 1700 per le CPU Intel di 12ª/13ª/14ª gen/ core ultra). </p>
	    <h3> Scheda Madre (Motherboard) </h3>
	    <p class="intro3"> È la spina dorsale che connette tutto. Oltre a scegliere un modello con il socket giusto per la tua CPU, valuta: </p>
	    <ul>
	      <li><strong>Funzionalità Integrate:</strong> Hai bisogno di Wi-Fi e Bluetooth integrati?</li>
	      <li><strong>Porte e Connettività:</strong> Controlla il numero di porte USB, uscite video, slot M.2 per gli SSD e porte SATA.</li>
	      <li><strong>Supporto RAM:</strong> Verifica la compatibilità con la velocità e la tipologia di RAM che vuoi acquistare consultando la lista QVL (Qualified Vendor List) sul sito del produttore.</li>
	    </ul>
	    
	    <h3> RAM (Random Access Memory)</h3>
	    <p class="intro3"> La memoria a breve termine del PC.</p>
	    <ul>
	      <li><strong>Quantità:</strong> Per il 2025, <strong>16 GB</strong> è il minimo consigliato per un'esperienza fluida. Per gaming intenso o produttività, considera 32 GB o più.</li>
	      <li><strong>Tipo e Velocità:</strong> Gli standard attuali sono <strong>DDR4</strong> e <strong>DDR5</strong>. La velocità si misura in Megahertz (es. 3200 MHz, 6000 MHz). Le RAM DDR5 ad alte prestazioni offrono le migliori performance.</li>
	      <li><strong>Profili di Overclock:</strong> Cerca il supporto a <strong>Intel XMP</strong> (Extreme Memory Profile) o <strong>AMD EXPO</strong> (Extended Profiles for Overclocking) per configurare facilmente la massima velocità della RAM con un clic dal BIOS.</li>
	    </ul>
	    <h3>Scheda Video (GPU)</h3>
	    <p class="intro3"> Fondamentale per gaming e lavori grafici. Per un uso generale, la grafica integrata (iGPU) in molte CPU è più che sufficiente. Per tutto il resto, la scelta è tra NVIDIA e AMD, a seconda del budget e delle prestazioni desiderate nei tuoi giochi o software specifici.</p>
	    <h3>Archiviazione (Storage)</h3>
	    <p class="intro3"> Dove vengono salvati sistema operativo, programmi e file.</p>
	    <ul>
	      <li><strong>SSD (Solid State Drive): </strong> Essenziale per la reattività del sistema. Consiglio un <strong>SSD NVMe M.2</strong> da almeno 500 GB / 1 TB per il sistema operativo e i programmi principali, dato che si collega direttamente alla scheda madre e offre velocità elevatissime.</li>
	      <li><strong>SSD SATA:</strong> Un secondo SSD (anche in formato SATA da 2.5") è perfetto per installare i giochi</li>
	      <li><strong>HDD (Hard Disk Drive): </strong> Ideale per archiviare grandi quantità di dati a cui non accedi di frequente (foto, video, documenti) a un costo per gigabyte inferiore.</li>
	    </ul>
	    <h3>Alimentatore (PSU)</h3>
	    <p class="intro3">Scegli un alimentatore con il wattaggio adeguato a supportare tutti i componenti (puoi usare calcolatori online per stimare il consumo della tua build oppure il nostro sito ti consiglia alimentatori con wattaggio consigliato per la tua build nella sezione “crea la tua configurazione”). 
	    Cerca modelli con certificazione 80 Plus Gold o superiore per un'ottima efficienza e bassa rumorosità. Gli alimentatori modulari o semi-modulari permettono di usare solo i cavi necessari, semplificando l'assemblaggio e migliorando il flusso d'aria.</p>
	    <h3>Case</h3>
	    <p class="intro3">La "casa" dei tuoi componenti. La scelta dipende da:</p>
	    <ul>
	      <li><strong>Formato: </strong> Full Tower, Mid Tower, Mini Tower. Deve essere compatibile con il formato della tua scheda madre (es. un case Mid Tower ATX).</li>
	      <li><strong>Airflow: </strong> Un buon flusso d'aria è cruciale per mantenere basse le temperature. Cerca case con pannelli frontali a rete (mesh).</li>
	      <li><strong>Estetica: </strong> Dai case sobri e minimalisti a quelli con pannelli in vetro temperato e ventole RGB o ARGB (Addressable RGB), che puoi personalizzare via software.</li>
	    </ul>
	    <h3>Dissipatore della CPU e Ventole</h3>
	    <p class="intro3">Il dissipatore incluso con alcune CPU è sufficiente per un uso base. Per prestazioni superiori o overclock, considera:</p>
	    <ul>
	      <li><strong>Dissipatore ad Aria: </strong> Soluzione affidabile, efficiente e dal costo contenuto.</li>
	      <li><strong>Dissipatore a Liquido (AIO - All-In-One):  </strong> Offre ottime performance e un'estetica pulita.</li>
	      <li><strong>Impianto Custom Loop:  </strong> Per appassionati estremi. Costoso e richiede manutenzione, ma garantisce le migliori prestazioni di raffreddamento.</li>
	    </ul>
	    
	  </section>
	
	  <section>
	    <h2>Capitolo 3: Guida Pratica all'Assemblaggio</h2>
	    <p class="intro2">Con tutti i componenti pronti, è ora di costruire.</p>
	    <h3>Preparazione dell'Area di Lavoro e Attrezzi</h3>
	    <p class="intro3">Lavora su un tavolo stabile, pulito, ben illuminato e privo di umidità. Per prevenire danni da scariche elettrostatiche, indossa un <strong>braccialetto antistatico</strong> collegato a una parte metallica non verniciata del case. <strong> Attrezzi necessari</strong>:</p>
	    <ul>
	    	<li>Un cacciavite a croce (meglio se con punta magnetica).</li>
	    	<li>Fascette stringicavo o in velcro per la gestione dei cavi.</li>
	    </ul>
		<p class="intro3"><strong>CONSIGLIO D'ORO:</strong>Il <strong>manuale della scheda madre</strong>è il tuo migliore amico. Consultalo sempre! Contiene diagrammi e istruzioni specifiche per l'installazione di CPU, RAM e per il collegamento dei cavi del case.
	    <h3>3.2 Passaggi di Montaggio</h3>
	    <ol>
	      <li><strong>Preparazione del Case:</strong> Apri i pannelli laterali e installa i distanziali metallici nei fori corrispondenti al formato della tua scheda madre.</li>
	      <li><strong>Installazione della CPU:</strong> Apri il socket sulla scheda madre, allinea la CPU (cerca il piccolo triangolo indicatore su un angolo) e posizionala senza forzare. Chiudi la levetta per bloccarla.</li>
	      <li><strong>Installazione della RAM:</strong> Apri i gancetti degli slot sulla scheda madre. Allinea l'incavo sul modulo RAM con la tacca nello slot e premi con decisione su entrambi i lati finché non senti un "click".</li>
	      <li><strong>Installazione del Dissipatore:</strong> Se usi un dissipatore aftermarket, applica una piccola quantità di pasta termica sulla CPU (spesso è pre-applicata sul dissipatore) e fissa il sistema di raffreddamento seguendo le istruzioni del produttore.</li>
	      <li><strong>Installazione della Scheda Madre:</strong> Posiziona delicatamente la scheda madre sui distanziali nel case e fissala con le viti fornite.</li>
	      <li><strong>Installazione della Scheda Video:</strong> Inserisci la GPU nello slot PCI-Express primario (di solito quello più in alto e rinforzato) finché non scatta in posizione.</li>
	      <li><strong>Installazione delle Unità di Archiviazione:</strong> Monta gli SSD e gli HDD negli appositi alloggiamenti.</li>
	      <li><strong>Installazione dell'Alimentatore:</strong> Fissa il PSU nell'area designata e inizia a collegare i cavi.</li>
	      <li><strong>Collegamento dei Cavi:</strong> Collega i cavi di alimentazione principali (24-pin per la scheda madre e 8-pin per la CPU), i cavi di alimentazione per la GPU e le unità di archiviazione. Collega i piccoli cavi del case (pulsante di accensione, LED, USB frontali) alla scheda madre, usando il manuale come guida.</li>
	    </ol>
	  </section>
	
	  <section>
	    <h2>Capitolo 4: Primo Avvio e Finalizzazione</h2>

	    <p class="intro2">Il momento della verità è arrivato.</p>
	
	    <h3>4.1 Accensione e Controllo Iniziale</h3>
	
	    <div class="ritual-box">
	        <p class="intro3">Prima di collegare l'alimentazione, c'è un piccolo rito che molti PC builder seguono: <strong>lasciare aperto il pannello laterale del case per il primo avvio.</strong></p>
	        <p class="intro3">È considerata una specie di <em>scaramanzia portafortuna</em>, ma ha anche un'importante utilità pratica: in questo modo potrai verificare a colpo d'occhio che tutte le ventole (CPU, GPU, case) stiano girando correttamente e avrai accesso immediato ai componenti se qualcosa non dovesse andare per il verso giusto, senza dover svitare di nuovo tutto.</p>
	    </div>
	    
	    <p class="intro3">Quindi, con il case ancora aperto, procedi:</p>
	    <ul>
	        <li>Collega monitor, tastiera, mouse e il cavo di alimentazione.</li>
	        <li>Accendi il PC. Se tutto è stato collegato correttamente, il computer si avvierà e visualizzerà una schermata iniziale, permettendoti di entrare nel BIOS/UEFI (solitamente premendo <code>CANC/DEL</code> o <code>F2</code> all'avvio).</li>
	        <li>Nel BIOS, verifica che tutti i componenti (CPU, RAM, unità di archiviazione) vengano riconosciuti correttamente. Controlla anche le temperature iniziali della CPU per assicurarti che il dissipatore stia funzionando.</li>
	    </ul>
	
	    <h3>4.2 Installazione del Sistema Operativo</h3>
	    <p class="intro3">Installa il sistema operativo (es. Windows) da una chiavetta USB avviabile. Durante l'installazione, scegli come disco di destinazione il tuo SSD NVMe M.2 per la massima velocità.</p>
	
	    <h3>4.3 Installazione di Driver e Software</h3>
	    <p class="intro3">Una volta avviato il sistema operativo:</p>
	    <ul>
	        <li>Connettiti a Internet.</li>
	        <li>Installa i driver della scheda madre (chipset) scaricandoli dal sito del produttore.</li>
	        <li>Installa i driver della scheda video (GPU) dal sito di NVIDIA o AMD.</li>
	        <li>Esegui gli aggiornamenti di Windows.</li>
	        <li>Installa tutti gli altri software e programmi necessari.</li>
	    </ul>
	
	    <h3>4.4 Gestione dei Cavi e Ritocchi Finali</h3>
	    <p class="intro3">Una volta verificato che tutto funziona alla perfezione, spegni il PC e scollega l'alimentazione. Ora puoi procedere con il cable management finale.</p>
	    <ul>
	        <li>Organizza i cavi sul retro del case usando fascette o velcro. Un buon cable management non solo migliora l'estetica, ma è fondamentale per garantire un flusso d'aria ottimale.</li>
	        <li>Chiudi il pannello laterale che avevi lasciato aperto.</li>
	        <li>Ricollega tutto e goditi la tua nuova creazione!</li>
	    </ul>
	  </section>
	</main>
   	
   	<jsp:include page="footer.jsp" />
    <script src="js/navbar.js" defer></script>
</body>
</html>
