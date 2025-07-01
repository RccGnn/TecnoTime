USE tecnotimedb;
INSERT INTO Account (
  username, hashedPassword, nome, cognome, sesso, email, numeroTelefono,
  nazione, provincia, citta, via, numeroCivico, CAP, ruolo, dataNascita
)
VALUES 
(
  'mrossi', 
  'hashed_pw_123', 
  'Mario', 
  'Rossi', 
  'M', 
  'mario.rossi@example.com', 
  '+391234567890', 
  'Italia', 
  'Milano', 
  'Milano', 
  'Via Roma', 
  '10', 
  '20100', 
  'utente_registrato', 
  '1990-05-20'
),
(
  'lbianchi', 
  'hashed_pw_456', 
  'Laura', 
  'Bianchi', 
  'F', 
  'laura.bianchi@example.com', 
  '+391112223334', 
  'Italia', 
  'Roma', 
  'Roma', 
  'Via Appia', 
  '21A', 
  '00100', 
  'amministratore', 
  '1985-09-15'
),
(
  'gverdi', 
  'hashed_pw_789', 
  'Giovanni', 
  'Verdi', 
  'M', 
  'giovanni.verdi@example.com', 
  '+393334445556', 
  'Italia', 
  'Napoli', 
  'Napoli', 
  'Via Napoli', 
  '33B', 
  '80100', 
  'utente_registrato', 
  '1992-03-08'
);

USE tecnotimedb;
-- Inserisco articoli
INSERT INTO Articolo (codiceIdentificativo, categoria, nome, dataUltimaPromozione, enteErogatore, disponibilita) VALUES
  ('ART100', 'Monitor', 'AOC CQ27G2U', '2025-06-01', 'AOC', TRUE),
  ('ART200', 'Tastiera', 'Keychron K8 I/O', '2025-05-15', 'Keychron', TRUE),
  ('ART300', 'Webcam', 'Logitech C920', '2025-03-20', 'Logitech', TRUE),
  ('ART400', 'Mouse', 'Logitech MX Master 3S', '2025-04-10', 'Logitech', TRUE),
  ('ART500', 'Stampante', 'HP LaserJet Pro M404dn', '2025-02-25', 'HP', FALSE),
  ('ART600', 'Cuffie', 'Sony WH-1000XM5', '2025-05-05', 'Sony', TRUE);
  
-- Inserisco servizi legati agli articoli corrispondenti
INSERT INTO Servizio (codiceServizio, prezzo, descrizione, durata, codiceIdentificativo) VALUES
  ('SRV100', 49.99, 'Installazione monitor AOC', 2, 'ART100'),
  ('SRV200', 19.99, 'Setup tastiera Keychron', 1, 'ART200'),
  ('SRV300', 29.99, 'Installazione webcam Logitech', 1, 'ART300');

-- Inserisco prodotti fisici basati sugli articoli
INSERT INTO Prodotto_Fisico (seriale, prezzo, descrizione, isPreassemblato, quantitaMagazzino, codiceIdentificativo) VALUES
  ('PF100A', 299.99, 'Monitor AOC CQ27G2U QHD 165Hz', FALSE, 15, 'ART400'),
  ('PF200B', 89.50, 'Tastiera meccanica RGB Keychron K8 I/O', FALSE, 30, 'ART500'),
  ('PF300C', 59.90, 'Webcam Logitech C920 Full HD', FALSE, 50, 'ART600');

-- Inserisco immagini per prodotti
INSERT INTO Immagine (url, codiceIdentificativo) VALUES 
( "https://www.dropbox.com/scl/fi/t6h8nzgtila0vqyzbcrsy/prova1.jpeg?rlkey=9euu5tm8rntz3td09cjq4dsx4&st=u5kas2lw&dl=0", "ART100"),
( "https://www.dropbox.com/scl/fi/x7c9tbqvxg1yof0keohkr/prova2.jpeg?rlkey=eryqlx9mtyax4ujhqibgd6lf3&st=4xgo5ez7&dl=0", "ART200"),
( "https://www.dropbox.com/scl/fi/obhqwh5ykppawn1i7l2pp/prova3.jpeg?rlkey=35ay786sq5r2p9mqysd5qpjox&st=6effh8o8&dl=0", "ART300");

INSERT INTO Articolo (codiceIdentificativo, categoria, nome, dataUltimaPromozione, enteErogatore, disponibilita) VALUES
  ('ART700', 'Software', 'Microsoft Office 365', '2025-05-20', 'Microsoft', TRUE),
  ('ART800', 'Software', 'Adobe Photoshop CC', '2025-04-10', 'Adobe', TRUE),
  ('ART900', 'Software', 'AutoCAD 2025', '2025-03-01', 'Autodesk', TRUE);

-- Inserimento prodotti digitali
INSERT INTO Prodotto_Digitale (codiceSoftware, descrizione, prezzo, codiceIdentificativo) VALUES
  ('SW-OFF365', 'Licenza annuale per Office 365, versione personal e business', 69.99, 'ART700'),
  ('SW-PSCC', 'Adobe Photoshop Creative Cloud, licenza mensile', 22.99, 'ART800'),
  ('SW-ACAD', 'AutoCAD 2025 per studenti e professionisti', 159.00, 'ART900');