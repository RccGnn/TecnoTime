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
  Carrello_id				VARCHAR(10) 	UNIQUE NOT NULL, -- Un carrello non può appartenere a più utenti
  usernameCarrello          VARCHAR(50)		NOT NULL,
  CONSTRAINT PRIMARY KEY (usernameCarrello, Carrello_id),
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
  nome                	VARCHAR(100)	NOT NULL,
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

CREATE TABLE Promozione (
  IDPromozione       VARCHAR(20) 	PRIMARY KEY NOT NULL,
  dataInizio       	 DATE           NOT NULL,
  durata             INT            NOT NULL, -- ORE/GIORNI
  percentualeSconto  DECIMAL(5,2)   NOT NULL,
  codicePromozione   VARCHAR(50)	DEFAULT NULL
);

CREATE TABLE Ordine (
  numeroTransazione  INT            PRIMARY KEY NOT NULL AUTO_INCREMENT,
  totale           	 DECIMAL(9,2)  	NOT NULL,
  dataTransazione    DATE           NOT NULL,
  oraTransazione     TIME           NOT NULL,
  username           VARCHAR(50)    NOT NULL,
  nazione       VARCHAR(50)     NOT NULL,
  provincia		VARCHAR(50)    	NOT NULL,
  citta			  VARCHAR(50)    	NOT NULL,
  via      		VARCHAR(100)   	NOT NULL,
  numeroCivico	VARCHAR(10)		NOT NULL,
  CAP         VARCHAR(5)    NOT NULL,
  CONSTRAINT FOREIGN KEY (username) REFERENCES Account(username)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Elemento_Ordine (
  numero            	INT            	NOT NULL,
  numeroTransazione 	INT            	NOT NULL,
  codiceArticolo     	VARCHAR(20)    	NOT NULL,
  quantita           	INT            	NOT NULL,
  prezzoUnitario   	 	DECIMAL(9,2)  	NOT NULL,
  CONSTRAINT PRIMARY KEY (numeroTransazione, numero),
  CONSTRAINT FOREIGN KEY (numeroTransazione) REFERENCES Ordine(numeroTransazione)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY (codiceArticolo) REFERENCES Articolo(codiceIdentificativo)
	ON UPDATE CASCADE
);

CREATE TABLE Contiene (
  codiceIdentificativo 	VARCHAR(20)		NOT NULL,
  usernameCarrello      VARCHAR(50)		NOT NULL,
  Carrello_Id			INT 			NOT NULL,
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
  IDPromozione			VARCHAR(20)	NOT NULL,
  codiceIdentificativo 	VARCHAR(20)	NOT NULL,
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
  IDPromozione     	VARCHAR(20)   	NOT NULL,
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
FROM Contiene AS con
	LEFT JOIN Carrello AS car USING (usernameCarrello, Carrello_Id)