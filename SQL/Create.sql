-- Creazione del database 
CREATE DATABASE IF NOT EXISTS TecnoTimeDB;
USE TecnoTimeDB;

-- Definizione delle tabelle
CREATE TABLE Account (
  username			VARCHAR(50) 	PRIMARY KEY NOT NULL,
  hashedPassword  	VARCHAR(255)	NOT NULL,
  nome        		VARCHAR(255)  	NOT NULL,
  cognome     		VARCHAR(255)  	NOT NULL,
  sesso       		VARCHAR(1)     	NOT NULL,
  email     		VARCHAR(100)   	NOT NULL UNIQUE,
  numeroTelefono 	VARCHAR(13)  	NOT NULL,
  nazione     		VARCHAR(50)     NOT NULL,
  provincia			VARCHAR(50)    	NOT NULL,
  citta			  	VARCHAR(50)    	NOT NULL,
  via      			VARCHAR(100)   	NOT NULL,
  numeroCivico		VARCHAR(10)		NOT NULL,
  CAP         		VARCHAR(5)    	NOT NULL,
  ruolo			  	ENUM('amministratore','utente_registrato','guest'),
  dataNascita		DATE			NOT NULL,
  AccountId			INT 			UNIQUE AUTO_INCREMENT
);

CREATE TABLE Carrello (
  Carrello_Id				VARCHAR(36) 	UNIQUE NOT NULL, -- Un carrello non può appartenere a più utenti
  usernameCarrello          VARCHAR(50)		NOT NULL,
  CONSTRAINT PRIMARY KEY (usernameCarrello, Carrello_Id),
  CONSTRAINT FOREIGN KEY (usernameCarrello) REFERENCES Account(username)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Wishlist (
  nome               VARCHAR(100)   NOT NULL,
  dataCreazione      DATE           NOT NULL,
  username           VARCHAR(50)    NOT NULL,
  CONSTRAINT PRIMARY KEY (nome, username), -- Possono esistere due wishlist con lo stesso nome ma che appartengono ad account diversi
  CONSTRAINT FOREIGN KEY (username) REFERENCES Account(username)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Articolo (
  codiceIdentificativo	VARCHAR(20)		PRIMARY KEY NOT NULL,
  categoria				VARCHAR(50)		NOT NULL,
  nome                	VARCHAR(200)	NOT NULL,
  dataUltimaPromozione 	DATE          	NOT NULL,
  enteErogatore       	VARCHAR(100)   	NOT NULL,
  disponibilita       	BOOLEAN        	NOT NULL DEFAULT TRUE
);

CREATE TABLE Immagine ( 
	indice					INT				AUTO_INCREMENT NOT NULL,
	url						VARCHAR(400)	NOT NULL,
    codiceIdentificativo	VARCHAR(20)		NOT NULL,
    CONSTRAINT PRIMARY KEY (indice, codiceIdentificativo),
    CONSTRAINT FOREIGN KEY (codiceIdentificativo) REFERENCES Articolo(codiceIdentificativo)
    ON DELETE CASCADE
);

CREATE TABLE Prodotto_Fisico (
  seriale          	 	VARCHAR(50)    	NOT NULL UNIQUE,
  prezzo              	DECIMAL(6,2)  	NOT NULL,
  descrizione         	TEXT        	NOT NULL,
  isPreassemblato    	BOOLEAN        	NOT NULL DEFAULT FALSE,
  quantitaMagazzino  	INT            	NOT NULL DEFAULT 0,
  codiceIdentificativo 	VARCHAR(20)    	NOT NULL,
  CONSTRAINT PRIMARY KEY (seriale, codiceIdentificativo),
  CONSTRAINT FOREIGN KEY (codiceIdentificativo) REFERENCES Articolo(codiceIdentificativo)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Prodotto_Digitale (
  codiceSoftware 	VARCHAR(20) 	NOT NULL UNIQUE,
  descrizione 		TEXT NOT 		NULL,
  prezzo 			DECIMAL(6,2) 	NOT NULL,
  chiaviDisponibili	INT				NOT NULL DEFAULT 0,
  codiceIdentificativo VARCHAR(20) 	NOT NULL,
  CONSTRAINT PK_ProdottoDigitale PRIMARY KEY (codiceSoftware, codiceIdentificativo),
  CONSTRAINT FK_ProdottoDigitale_Articolo FOREIGN KEY (codiceIdentificativo)
    REFERENCES Articolo(codiceIdentificativo)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Servizio (
  codiceServizio     	VARCHAR(20)    	NOT NULL UNIQUE,
  prezzo              	DECIMAL(6,2)  	NOT NULL,
  descrizione         	TEXT        	NOT NULL,
  durata           	 	DECIMAL(6,2)    NOT NULL, -- GIORNI
  codiceIdentificativo	VARCHAR(20)    	NOT NULL,
  CONSTRAINT PRIMARY KEY (codiceServizio, codiceIdentificativo),
  CONSTRAINT FOREIGN KEY (codiceIdentificativo) REFERENCES Articolo(codiceIdentificativo)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

use tecnotimedb;
CREATE TABLE Promozione (
  IDPromozione       INT auto_increment PRIMARY KEY NOT NULL,
  nomesconto 		 VARCHAR(200) 	NOT NULL,
  descrizione 		 TEXT 			NOT NULL,
  dataInizio       	 DATE           NOT NULL,
  percentualeSconto  DECIMAL(5,2)   NOT NULL
);

CREATE TABLE Ordine (
  numeroTransazione INT            	NOT NULL UNIQUE,
  totale           	DECIMAL(9,2)  	NOT NULL,
  dataTransazione   DATE           	NOT NULL,
  oraTransazione    TIME           	NOT NULL,
  username         	VARCHAR(50)    	NOT NULL,
  nazione       	VARCHAR(50)     NOT NULL,
  provincia			VARCHAR(50)    	NOT NULL,
  citta			  	VARCHAR(50)    	NOT NULL,
  via      			VARCHAR(100)   	NOT NULL,
  numeroCivico		VARCHAR(10)		NOT NULL,
  CAP         		VARCHAR(5)    	NOT NULL,
  CONSTRAINT PRIMARY KEY (numeroTransazione, username), 
  CONSTRAINT FOREIGN KEY (username) REFERENCES Account(username)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Elemento_Ordine (
  numero            	INT            	NOT NULL,
  numeroTransazione 	INT            	NOT NULL,					
  codiceArticolo     	VARCHAR(20)    	NOT NULL, -- Può capitare che un articolo viene eliminato, in quel caso codice articolo viene mantenuto
  nomeArticolo			VARCHAR(200)	NOT NULL,
  urlImmagineArticolo	VARCHAR(400)	NOT NULL,
  quantitaArticolo      INT            	NOT NULL,
  prezzoUnitario   	 	DECIMAL(9,2)  	NOT NULL,
  CONSTRAINT PRIMARY KEY (numeroTransazione, numero, codiceArticolo),
  CONSTRAINT FOREIGN KEY (numeroTransazione) REFERENCES Ordine(numeroTransazione)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY (codiceArticolo) REFERENCES Articolo(codiceIdentificativo)
	ON UPDATE CASCADE 	-- Se cambia il codice di un articolo cambia, allora modifica.
						-- Se un articolo viene eliminato, non eliminare l'elemento dall'ordine
);

CREATE TABLE Contiene (
  codiceIdentificativo 	VARCHAR(20)		NOT NULL,
  usernameCarrello      VARCHAR(50)		NOT NULL,
  Carrello_Id			VARCHAR(36) 	NOT NULL,
  quantita           	INT       		NOT NULL,
  CONSTRAINT PRIMARY KEY (codiceIdentificativo, usernameCarrello, Carrello_Id),
  CONSTRAINT FOREIGN KEY (codiceIdentificativo) REFERENCES Articolo(codiceIdentificativo)
    ON UPDATE CASCADE, -- Un articolo viene cancellato, viene settato isDisponibile=false: nessun motivo per cancellare contiene
  CONSTRAINT FOREIGN KEY (usernameCarrello, Carrello_Id) REFERENCES Carrello(usernameCarrello, Carrello_Id)
    ON DELETE CASCADE -- Se l'username viene cancellato, allora si devono cancellare tutti gli elementi del carrello (e poi il carrello stesso)
    ON UPDATE CASCADE
);

CREATE TABLE Comprende (
  codiceIdentificativo 	VARCHAR(20)	NOT NULL,
  nome               VARCHAR(100)   NOT NULL,
  username           VARCHAR(50)    NOT NULL,
  quantita           	INT       	NOT NULL,
  CONSTRAINT PRIMARY KEY (codiceIdentificativo, nome, username),
  CONSTRAINT FOREIGN KEY (codiceIdentificativo) REFERENCES Articolo(codiceIdentificativo)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Composto_da (
  serialePreassemblato	VARCHAR(50)	NOT NULL,
  serialeComponente  	VARCHAR(50)	NOT NULL,
  quantita           	INT			NOT NULL,
  CONSTRAINT PRIMARY KEY (serialePreassemblato, serialeComponente),
  CONSTRAINT FOREIGN KEY (serialePreassemblato) REFERENCES Prodotto_Fisico(seriale)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY (serialeComponente) REFERENCES Prodotto_Fisico(seriale)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Riguarda (
  IDPromozione			INT	NOT NULL,
  codiceIdentificativo 	VARCHAR(20)	UNIQUE NOT NULL,
  CONSTRAINT PRIMARY KEY (IDPromozione, codiceIdentificativo),
  CONSTRAINT FOREIGN KEY (IDPromozione) REFERENCES Promozione(IDPromozione)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY (codiceIdentificativo) REFERENCES Articolo(codiceIdentificativo)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Associato_a (
  username			VARCHAR(50)		NOT NULL,
  IDPromozione     	INT  		 	NOT NULL,
  codicePromozione   VARCHAR(50) 	NOT NULL,
  CONSTRAINT PRIMARY KEY (username, IDPromozione),
  CONSTRAINT FOREIGN KEY (username) REFERENCES Account(username)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY (IDPromozione) REFERENCES Promozione(IDPromozione)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

USE tecnotimedb;
CREATE OR REPLACE VIEW Catalogo AS
SELECT 
	a.*,
    COALESCE (
		pf.prezzo,
		s.prezzo, 
		pd.prezzo 
	) AS prezzo,
    pf.seriale,
	pf.descrizione AS descrizione_prodotto_fisico,
	pf.isPreassemblato,
	pf.quantitaMagazzino,
    s.codiceServizio,
	s.descrizione AS descrizione_servizio,
	s.durata,
    pd.codiceSoftware,
    pd.descrizione AS descrizione_prodotto_digitale,
    pd.chiaviDisponibili
FROM Articolo AS a
	LEFT JOIN Prodotto_Fisico AS pf USING (codiceIdentificativo)
    LEFT JOIN Servizio AS s USING (codiceIdentificativo)
    LEFT JOIN Prodotto_Digitale AS pd USING (codiceIdentificativo);
-- DROP VIEW Catalogo;

USE tecnotimedb;
CREATE OR REPLACE VIEW CarrelloRiempito AS
SELECT
	car.usernameCarrello,
    car.Carrello_Id,
    con.codiceIdentificativo,
    con.quantita
FROM Carrello AS car
	LEFT JOIN Contiene AS con USING (usernameCarrello, Carrello_Id);

USE tecnotimedb;
CREATE or REPLACE VIEW OrdineCompleto AS 
SELECT
	ord.*,
    elem.numero,
    elem.codiceArticolo,
    elem.nomeArticolo,
    elem.urlImmagineArticolo,
    elem.quantitaArticolo,
    elem.prezzoUnitario
FROM
	Ordine as ord
LEFT JOIN Elemento_Ordine as elem USING (numeroTransazione);

USE tecnotimedb;
CREATE or REPLACE VIEW PromozioneCompleta AS
SELECT
	promo.*,
    rig.codiceIdentificativo,
    ass.username,
    ass.codicePromozione
FROM
	Promozione AS promo
LEFT JOIN Riguarda AS rig USING (IDPromozione)
LEFT JOIN Associato_a AS ass USING (IDPromozione);

USE tecnotimedb; 
CREATE TABLE PROCESSORE(
nomecompleto VARCHAR(200) PRIMARY KEY,
marca VARCHAR(200) NOT NULL,
socket VARCHAR(200) NOT NULL, 
datarilascio date NOT NULL,
Watt INT NOT NULL
);

USE tecnotimedb; 
CREATE TABLE SCHEDA_MADRE(
nomecompleto VARCHAR(200) PRIMARY KEY,
marca VARCHAR(200) NOT NULL,
socket VARCHAR(200) NOT NULL, 
dimensione VARCHAR(200) NOT NULL,
PCI decimal(2,1) NOT NULL,
SupportoRam VARCHAR(100) NOT NULL,
Watt INT NOT NULL
);

USE tecnotimedb;
CREATE TABLE RAM(
nomecompleto VARCHAR(200) PRIMARY KEY,
marca VARCHAR(200) NOT NULL,
capacita INT NOT NULL,
SupportoRam VARCHAR(100) NOT NULL
);

USE tecnotimedb; 
CREATE TABLE SCHEDA_VIDEO(
nomecompleto VARCHAR(200) PRIMARY KEY,
marca VARCHAR(200) NOT NULL,
PCI  DECIMAL(2,1) NOT NULL,
vram INT NOT NULL, 
tipoRam VARCHAR(100) NOT NULL,
Watt INT NOT NULL
);   
 
USE tecnotimedb;
CREATE TABLE ARCHIVIAZIONE(
nomecompleto VARCHAR(200) PRIMARY KEY,
marca VARCHAR(200) NOT NULL,
PCI VARCHAR(100) NOT NULL,
capacita VARCHAR(100) NOT NULL
);

CREATE TABLE CASE_PC(
nomecompleto VARCHAR(200) PRIMARY KEY,
dimensione VARCHAR(200) NOT NULL
);
CREATE TABLE ALIMENTATORI (
nomecompleto VARCHAR(200) PRIMARY KEY,
marca VARCHAR(200) NOT NULL,
watt INT NOT NULL
);

    