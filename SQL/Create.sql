-- Creazione del database 
CREATE DATABASE IF NOT EXISTS TecnoTimeDB;
USE TecnoTimeDB;

-- Definizione delle tabelle
CREATE TABLE Account (
  username		VARCHAR(50) 	PRIMARY KEY NOT NULL,
  hashedPassword  	VARCHAR(255)	NOT NULL,
  nome        VARCHAR(255)  NOT NULL,
  cognome     VARCHAR(255)  NOT NULL,
  sesso       VARCHAR(1)     NOT NULL,
  email     	VARCHAR(100)   	UNIQUE NOT NULL,
  numeroTelefono VARCHAR(13)  NOT NULL,
  nazione     VARCHAR(50)     NOT NULL,
  provincia		VARCHAR(50)    	NOT NULL,
  citta			  VARCHAR(50)    	NOT NULL,
  via      		VARCHAR(100)   	NOT NULL,
  numeroCivico	VARCHAR(10)		NOT NULL,
  CAP         VARCHAR(5)    NOT NULL,
  ruolo			  ENUM('amministratore','utente_registrato')	NOT NULL,
  dataNascita	DATE	NOT NULL
);

CREATE TABLE Carrello (
  numeroCarrello	INT				PRIMARY KEY NOT NULL AUTO_INCREMENT,
  subtotale     	DECIMAL(6,2)	NOT NULL DEFAULT 0.00,
  numeroProdotti	INT				NOT NULL DEFAULT 0,
  username          VARCHAR(50)		NOT NULL,
  CONSTRAINT FOREIGN KEY (username) REFERENCES Account(username)
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
  descrizione         	TEXT        	NOT NULL,
  nome                	VARCHAR(100)	NOT NULL,
  prezzo              	DECIMAL(6,2)  	NOT NULL,
  dataUltimaPromozione 	DATE          	NOT NULL,
  enteErogatore       	VARCHAR(100)   	NOT NULL,
  disponibilita       	BOOLEAN        	NOT NULL DEFAULT TRUE
);

CREATE TABLE Prodotto_Fisico (
  seriale          	 VARCHAR(50)    PRIMARY KEY NOT NULL,
  isPreassemblato    BOOLEAN        NOT NULL DEFAULT FALSE,
  quantitaMagazzino  INT            NOT NULL DEFAULT 0,
  codiceArticolo     VARCHAR(20)    NOT NULL,
  CONSTRAINT FOREIGN KEY (codiceArticolo) REFERENCES Articolo(codiceIdentificativo)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Servizio (
  codiceServizio     VARCHAR(20)    PRIMARY KEY NOT NULL,
  durata           	 INT            NOT NULL, -- ORE/GIORNI
  tipologia          VARCHAR(50)    NOT NULL,	
  codiceArticolo     VARCHAR(20)    NOT NULL,
  CONSTRAINT FOREIGN KEY (codiceArticolo) REFERENCES Articolo(codiceIdentificativo)
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
  codiceIdentificativo 	VARCHAR(20)	NOT NULL,
  numeroCarrello     	INT         NOT NULL,
  quantita           	INT       	NOT NULL,
  CONSTRAINT PRIMARY KEY (codiceIdentificativo, numeroCarrello),
  CONSTRAINT FOREIGN KEY (codiceIdentificativo) REFERENCES Articolo(codiceIdentificativo)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY (numeroCarrello) REFERENCES Carrello(numeroCarrello)
    ON DELETE CASCADE
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
