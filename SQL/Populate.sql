USE TecnoTime;

INSERT INTO Account (username, password, nome, cognome, sesso, email, numeroTelefono, nazione, provincia, citta, via, numeroCivico, CAP, ruolo, dataNascita) VALUES
('mrossi',   '$2y$10$hash1',  'Mario',     'Rossi',     'M', 'm.rossi@domain.it',   '0810000000', 'Italia', 'MI', 'Milano',   'Via Verdi',             '12', '20121', 'utente_registrato', '1985-07-23'),
('lgialli',  '$2y$10$hash2',  'Luca',      'Gialli',    'M', 'l.gialli@domain.it',  '0820000000', 'Italia', 'RM', 'Roma',     'Via Nazionale',         '5',  '00184', 'utente_registrato', '1990-02-14'),
('fbianchi', '$2y$10$hash3',  'Francesca', 'Bianchi',   'F', 'f.bianchi@domain.it', '0830000000', 'Italia', 'TO', 'Torino',   'Corso Galileo Ferraris','100','10128', 'utente_registrato', '1988-05-30'),
('aferrari', '$2y$10$hash4',  'Alessio',   'Ferrari',   'M', 'a.ferrari@domain.it', '0840000000', 'Italia', 'NA', 'Napoli',   'Via Toledo',            '20', '80134', 'utente_registrato', '1992-11-02'),
('gverdi',   '$2y$10$hash5',  'Giovanni',  'Verdi',     'M', 'g.verdi@domain.it',   '0850000000', 'Italia', 'FI', 'Firenze',  'Piazza Duomo',          '3',  '50122', 'utente_registrato', '1979-08-15'),
('srusso',   '$2y$10$hash6',  'Sara',      'Russo',     'F', 's.russo@domain.it',   '0860000000', 'Italia', 'VE', 'Venezia',  'Riva degli Schiavoni',  '7',  '30122', 'amministratore',    '1983-03-22'),
('dlongo',   '$2y$10$hash7',  'Debora',    'Longo',     'F', 'd.longo@domain.it',   '0870000000', 'Italia', 'BO', 'Bologna',  'Via Castiglione',       '45', '40124', 'utente_registrato', '1995-12-01'),
('mconti',   '$2y$10$hash8',  'Marco',     'Conti',     'M', 'm.conti@domain.it',   '0880000000', 'Italia', 'GE', 'Genova',   'Via Garibaldi',         '27', '16124', 'utente_registrato', '1987-06-10'),
('emancini', '$2y$10$hash9',  'Elena',     'Mancini',   'F', 'e.mancini@domain.it', '0890000000', 'Italia', 'PA', 'Palermo',  'Via Maqueda',           '88', '90133', 'utente_registrato', '1991-09-05'),
('rgallo',   '$2y$10$hash10', 'Riccardo',  'Gallo',     'X', 'r.gallo@domain.it',   '0811000000', 'Italia', 'CA', 'Cagliari', 'Via Garibaldi',         '14', '09125', 'utente_registrato', '1980-01-19');



INSERT INTO Articolo (codiceIdentificativo, descrizione, nome, prezzo, dataUltimaPromozione, enteErogatore, disponibilita) VALUES
('ART001', 'Processore Intel Core i7-13700K, 16 core, 5.4 GHz boost',           'CPU Intel i7-13700K',  429.00, '2025-04-10', 'Intel', TRUE),
('ART002', 'Scheda madre ASUS ROG Strix Z790-E Gaming WiFi',                     'MOBO ASUS Z790-E',     369.00, '2025-03-20', 'ASUS', TRUE),
('ART003', 'Kit RAM 32 GB (2×16 GB) DDR5-6000 CL36 Corsair Vengeance',           'RAM Corsair 32GB DDR5',259.00, '2025-05-05', 'Corsair', TRUE),
('ART004', 'Scheda video NVIDIA GeForce RTX 4070 Ti 12 GB GDDR6X',               'GPU RTX 4070 Ti',      899.00, '2025-02-28', 'NVIDIA', TRUE),
('ART005', 'SSD NVMe M.2 1 TB Samsung 990 Pro, lettura 7 450 MB/s',              'SSD Samsung 1TB 990 Pro',199.00,'2025-04-15','Samsung', TRUE),
('ART006', 'Hard disk interno 2 TB Seagate BarraCuda 7200 RPM',                  'HDD Seagate 2TB',       54.00, '2025-01-30', 'Seagate', TRUE),
('ART007', 'Alimentatore ATX 750 W Corsair RM750x, certificazione Gold',         'PSU Corsair RM750x',   119.00, '2025-03-10', 'Corsair', TRUE),
('ART008', 'Dissipatore a liquido NZXT Kraken X63 RGB, 280 mm',                  'Cooler NZXT X63',      159.00, '2025-04-01', 'NZXT', TRUE),
('ART009', 'Case mid-tower Phanteks Eclipse P400A, vetro temperato',             'Case Phanteks P400A',   89.00, '2025-03-25', 'Phanteks', TRUE),
('ART010', 'Licenza software antivirus 1 anno, 3 dispositivi',                   'Norton Antivirus 2025', 49.99, '2025-05-01', 'NortonLifeLock', TRUE),
('ART011', 'Servizio di backup cloud 1 anno con 1 TB di spazio',                 'Cloud Backup 1TB',      49.99, '2025-05-01', 'CloudSafe', TRUE),
('ART012', 'Licenza Adobe Photoshop CC 1 anno, download digitale',               'Adobe Photoshop CC',    239.00,'2025-04-15', 'Adobe', TRUE),
('ART013', 'Abbonamento VPN 1 anno, 5 dispositivi simultanei',                    'VPN Secure 1Y',         59.99, '2025-05-10', 'NetShield', TRUE);

INSERT INTO Prodotto_Fisico (seriale, isPreassemblato, quantitaMagazzino, codiceArticolo) VALUES
('PF1001', FALSE, 120, 'ART001'),
('PF1002', FALSE, 200, 'ART002'),
('PF1003', FALSE, 50,  'ART003'),
('PF1004', TRUE,  30,  'ART003'),  -- kit “Gaming Bundle” preassemblato
('PF1005', FALSE, 80,  'ART004'),
('PF1006', FALSE, 140, 'ART005'),
('PF1007', FALSE, 75,  'ART006'),
('PF1008', FALSE, 160, 'ART007'),
('PF1009', FALSE, 40,  'ART008'),
('PF1010', FALSE, 100, 'ART009');

INSERT INTO Servizio (codiceServizio, durata, tipologia, codiceArticolo) VALUES
('SRV011', 365, 'Backup Cloud Annuale',    'ART011'),
('SRV012', 365, 'Licenza Software Annuale','ART012'),
('SRV013', 365, 'Abbonamento VPN Annuale', 'ART013');

INSERT INTO Promozione (IDPromozione, dataInizio, durata, percentualeSconto, codicePromozione) VALUES
('PR001', '2025-04-01', 30, 10.00, 'SPRING10'),
('PR002', '2025-05-01', 14, 15.00, 'MAY15'),
('PR003', '2025-02-14', 7,  20.00, 'VAL20'),
('PR004', '2025-03-17', 10, 12.00, 'STP12'),
('PR005', '2025-04-22', 5,  25.00, 'EARTH25'),
('PR006', '2025-05-10', 20, 5.00,  NULL),
('PR007', '2025-05-15', 7,  30.00, NULL),
('PR008', '2025-01-01', 90, 8.00,  NULL),
('PR009', '2025-06-01', 30, 18.00, NULL),
('PR010', '2025-07-01', 31, 22.00, NULL);

INSERT INTO Riguarda (IDPromozione, codiceIdentificativo) VALUES
('PR006', 'ART001'),
('PR006', 'ART010'),
('PR007', 'ART002'),
('PR007', 'ART009'),
('PR008', 'ART003'),
('PR008', 'ART005'),
('PR009', 'ART004'),
('PR009', 'ART011'),
('PR010', 'ART012'),
('PR010', 'ART008');

INSERT INTO Associato_a (username, IDPromozione) VALUES
('mrossi',   'PR001'),
('lgialli',  'PR002'),
('fbianchi', 'PR003'),
('aferrari', 'PR004'),
('gverdi',   'PR005'),
('srusso',   'PR001'),
('srusso',   'PR002'),
('dlongo',   'PR003'),
('dlongo',   'PR004'),
('mconti',   'PR005');

INSERT INTO Carrello (numeroCarrello, subtotale, numeroProdotti, username) VALUES
(1,  100, 3, 'mrossi'),
(2,  200, 2, 'lgialli'),
(3,  300, 3, 'fbianchi'),
(4,  400, 3, 'aferrari'),
(5,  500, 2, 'gverdi'),
(6,  600, 3, 'srusso'),
(7,  700, 3, 'dlongo'),
(8,  800, 2, 'mconti'),
(9,  900, 2, 'emancini'),
(10, 1000,3, 'rgallo');

INSERT INTO Contiene (codiceIdentificativo, numeroCarrello, quantita) VALUES
('ART001', 1, 1),
('ART010', 1, 2),
('ART003', 2, 1),
('ART007', 2, 1),
('ART002', 3, 2),
('ART009', 3, 1),
('ART004', 4, 1),
('ART006', 4, 1),
('ART010', 4, 1),
('ART005', 5, 1),
('ART001', 5, 1),
('ART008', 6, 1),
('ART009', 6, 1),
('ART002', 6, 1),
('ART009', 7, 3),
('ART011', 8, 1),
('ART002', 8, 1),
('ART004', 9, 2),
('ART005',10, 1),
('ART013',10, 1),
('ART010',10, 1);

INSERT INTO Wishlist (nome, dataCreazione, username) VALUES
('Gadget Tech',  '2025-03-01', 'mrossi'),
('Regali Cultura','2025-03-05', 'lgialli'),
('Home Office',  '2025-04-10', 'fbianchi'),
('Fitness',      '2025-02-20', 'aferrari'),
('Audio',        '2025-01-15', 'gverdi'),
('Storage',      '2025-03-12', 'srusso'),
('Streaming',    '2025-02-28', 'dlongo'),
('Gaming',       '2025-04-01', 'mconti'),
('Viaggi',       '2025-03-20', 'emancini'),
('Utilità',      '2025-01-30', 'rgallo');

INSERT INTO Comprende (codiceIdentificativo, nome, username, quantita) VALUES
('ART001', 'Gadget Tech',   'mrossi',   1),
('ART002', 'Gadget Tech',   'mrossi',   1),
('ART003', 'Regali Cultura','lgialli',  1),
('ART004', 'Regali Cultura','lgialli',  1),
('ART008', 'Home Office',   'fbianchi', 1),
('ART007', 'Home Office',   'fbianchi', 1),
('ART005', 'Fitness',       'aferrari', 1),
('ART010', 'Fitness',       'aferrari', 2),
('ART002', 'Audio',         'gverdi',   1),
('ART007', 'Audio',         'gverdi',   1),
('ART006', 'Storage',       'srusso',   1),
('ART009', 'Storage',       'srusso',   1),
('ART010', 'Streaming',     'dlongo',   1),
('ART008', 'Streaming',     'dlongo',   1),
('ART003', 'Gaming',        'mconti',   1),
('ART001', 'Gaming',        'mconti',   1),
('ART004', 'Viaggi',        'emancini', 1),
('ART009', 'Viaggi',        'emancini', 1),
('ART005', 'Utilità',       'rgallo',   1),
('ART010', 'Utilità',       'rgallo',   2);

INSERT INTO Ordine (numeroTransazione, totale, dataTransazione, oraTransazione, username, nazione, citta, provincia, via, numeroCivico, CAP) VALUES
(1,  457.00, '2025-05-10', '10:15:00', 'mrossi',   'Italia', 'MI', 'Milano',   'Via Verdi',             '12', '20121'),
(2,  808.00, '2025-05-11', '11:20:00', 'lgialli',  'Italia', 'RM', 'Roma',     'Via Nazionale',         '5',  '00184'),
(3,  307.00, '2025-05-12', '12:25:00', 'fbianchi', 'Italia', 'TO', 'Torino',   'Corso Galileo Ferraris','100','10128'),
(4,  377.00, '2025-05-13', '13:30:00', 'aferrari', 'Italia', 'NA', 'Napoli',   'Via Toledo',            '20', '80134'),
(5,  548.00, '2025-05-14', '14:35:00', 'gverdi',   'Italia', 'FI', 'Firenze',  'Piazza Duomo',          '3',  '50122'),
(6,  203.00, '2025-05-15', '15:40:00', 'srusso',   'Italia', 'VE', 'Venezia',  'Riva degli Schiavoni',  '7',  '30122'),
(7,  147.00, '2025-05-16', '16:45:00', 'dlongo',   'Italia', 'BO', 'Bologna',  'Via Castiglione',       '45', '40124'),
(8,  878.00, '2025-05-17', '17:50:00', 'mconti',   'Italia', 'GE', 'Genova',   'Via Garibaldi',         '27', '16124'),
(9,  498.00, '2025-05-18', '18:55:00', 'emancini', 'Italia', 'PA', 'Palermo',  'Via Maqueda',           '88', '90133'),
(10, 777.00, '2025-05-19', '19:00:00', 'rgallo',   'Italia', 'CA', 'Cagliari', 'Via Garibaldi',         '14', '09125');

INSERT INTO Elemento_Ordine (numero, numeroTransazione, codiceArticolo, quantita, prezzoUnitario) VALUES
(1,1,'ART001',1,399.00),
(2,1,'ART010',2,29.00),
(1,2,'ART003',1,749.00),
(2,2,'ART007',1,59.00),
(1,3,'ART002',2,129.00),
(2,3,'ART009',1,49.00),
(1,4,'ART004',1,249.00),
(2,4,'ART006',1,99.00),
(1,5,'ART005',1,149.00),
(2,5,'ART001',1,399.00),
(1,6,'ART008',1,89.00),
(2,6,'ART010',1,29.00),
(1,7,'ART009',3,49.00),
(1,8,'ART003',1,749.00),
(2,8,'ART005',1,149.00),
(1,9,'ART004',2,249.00),
(1,10,'ART001',1,399.00),
(2,10,'ART003',1,749.00);

INSERT INTO Composto_da (serialePreassemblato, serialeComponente, quantita) VALUES
('PF1004','PF1003',1),
('PF1004','PF1007',1),
('PF1004','PF1002',1);
