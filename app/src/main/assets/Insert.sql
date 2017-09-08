INSERT INTO Orario VALUES ('Golds Gym', 'Da Lunedi a Venerdi',"6-24");
INSERT INTO Orario VALUES ('Golds Gym', 'Sabato e Domenica',"6-20");
INSERT INTO Chiusura VALUES('Golds Gym','2017-08-10','2017-08-20');
INSERT INTO Palestra VALUES ('Golds Gym', 'USA, Venice',2,5,45.954626 ,10.649378);
INSERT INTO Palestra VALUES ('Center Sport', 'Via Grossi 1',10,16,45.733625,10.940586);
INSERT INTO Notizia (IdPalestra, Descrizione, Data) VALUES ('Golds Gym', 'Dont wait check out Arnolds new workout plan, Blueprint for mass', '2017-02-22 15:00:05');
INSERT INTO Notizia (IdPalestra, Descrizione, Data) VALUES ('Center Sport', 'Con il mese di aprile si torna a pagare 35 euro al mese', '2017-02-22 12:00:05');
INSERT INTO Notizia (IdPalestra, Descrizione, Data) VALUES ('Center Sport', 'Il vincitore del torneo nazionale e Mario Rossi', '2017-04-22 19:00:05');
INSERT INTO Notizia (IdPalestra, Descrizione, Data) VALUES ('Golds Gym', 'New gainers in the store', '2016-02-22 19:00:05');
INSERT INTO Notizia (IdPalestra, Descrizione, Data) VALUES ('Center Sport', 'Palestra chiude oer vacanze di pasqua', '2016-02-22 19:00:05');
INSERT INTO Esercizio VALUES (1,'Panca piana','Petto');
INSERT INTO Esercizio VALUES (2,'Dist. manubrio','Petto');
INSERT INTO Esercizio VALUES (3,'Panca alta','Petto');
INSERT INTO Esercizio VALUES (4,'Dist. inclinate','Petto');
INSERT INTO Esercizio VALUES (5,'Dist. declinate','Petto');
INSERT INTO Esercizio VALUES (6,'Croci','Petto');
INSERT INTO Esercizio VALUES (7,'Croci inclinate','Petto');
INSERT INTO Esercizio VALUES (8,'Croci declinate','Petto');
INSERT INTO Esercizio VALUES (9,'Chest press','Petto');
INSERT INTO Esercizio VALUES (10,'Stacco da terra','Schiena');
INSERT INTO Esercizio VALUES (11,'Rematore','Schiena');
INSERT INTO Esercizio VALUES (12,'Rematore manubri','Schiena');
INSERT INTO Esercizio VALUES (13,'Trazioni sbarra','Schiena');
INSERT INTO Esercizio VALUES (14,'Low row','Schiena');
INSERT INTO Esercizio VALUES (15,'T bar','Schiena');
INSERT INTO Esercizio VALUES (16,'Pulley','Schiena');
INSERT INTO Esercizio VALUES (17,'Squat','Gambe');
INSERT INTO Esercizio VALUES (18,'Hach squat','Gambe');
INSERT INTO Esercizio VALUES (19,'Affondi bil.','Gambe');
INSERT INTO Esercizio VALUES (20,'Affondi manubri','Gambe');
INSERT INTO Esercizio VALUES (21,'Mezzo stacco','Gambe');
INSERT INTO Esercizio VALUES (22,'Leg exension','Gambe');
INSERT INTO Esercizio VALUES (23,'Leg curl','Gambe');
INSERT INTO Esercizio VALUES (24,'Pressa','Gambe');
INSERT INTO Esercizio VALUES (25,'Pressa inclinata','Gambe');
INSERT INTO Esercizio VALUES (27,'Curl manubri','Braccia');
INSERT INTO Esercizio VALUES (28,'Curl bilanciere','Braccia');
INSERT INTO Esercizio VALUES (29,'Curl hummer','Braccia');
INSERT INTO Esercizio VALUES (30,'Bicipiti conc.','Braccia');
INSERT INTO Esercizio VALUES (31,'Bicipiti pulley','Braccia');
INSERT INTO Esercizio VALUES (32,'Scott','Braccia');
INSERT INTO Esercizio VALUES (33,'Tricipiti pulley','Braccia');
INSERT INTO Esercizio VALUES (34,'French press','Braccia');
INSERT INTO Esercizio VALUES (35,'Dip','Braccia');
INSERT INTO Esercizio VALUES (36,'Arnold press','Spalle');
INSERT INTO Esercizio VALUES (37,'Lento avanti','Spalle');
INSERT INTO Esercizio VALUES (38,'Lento dietro','Spalle');
INSERT INTO Esercizio VALUES (39,'Shoulder press','Spalle');
INSERT INTO Esercizio VALUES (40,'Alzate frontali','Spalle');
INSERT INTO Esercizio VALUES (41,'Alzate laterali','Spalle');
INSERT INTO Esercizio VALUES (42,'Lat Machine','Schiena');
INSERT INTO Esercizio VALUES (43,'Panca piana stretta','Braccia');
INSERT INTO Esercizio VALUES (44,'Calf','Gambe');
INSERT INTO Esercizio VALUES (45,'Calf seduto','Gambe');
INSERT INTO Istruttore (Id, Nome, Cognome, Specializzazione, IdPalestra) VALUES (1, 'Marco', 'Bianchi', 'Specializzato in body-building, istruttore di livello 3', 'Center Sport');
INSERT INTO Istruttore (Id, Nome, Cognome, Specializzazione, IdPalestra) VALUES (2, 'Giovanni', 'Rossi', 'Specializzato in power lifting, istruttore di livello 3', 'Center Sport');
INSERT INTO Istruttore (Id, Nome, Cognome, Specializzazione, IdPalestra) VALUES (3, 'Chiara', 'Verdi', 'Specializzata in tecniche della riabilitazione e fitness-model', 'Golds Gym');
INSERT INTO Istruttore (Id, Nome, Cognome, Specializzazione, IdPalestra) VALUES (4, 'Mario', 'Neri', 'Personal trainer di livello 2, riconosciuto. Preparatore del campione italiano di Man physique', 'Golds Gym');
INSERT INTO Utente (Id, Nome, Cognome, Email, IAbb, FAbb, Day_corrente, Password, IdIstruttore, Palestra, Scheda_corrente) VALUES (1, 'Valentino', 'Armani', 'aaaa@bbbb.com', '2017-01-01', '2019-01-01', 1, 'P@ssword', 4, 'Golds Gym',3);
INSERT INTO Utente (Id, Nome, Cognome, Email, IAbb, FAbb, Day_corrente, Password, IdIstruttore, Palestra, Scheda_corrente) VALUES (2, 'Jacek', 'Zbudowski', 'cccc@dddd.com', '2017-01-01', '2018-01-01', 1, 'Password2', 2, 'Center Sport',4);
INSERT INTO Utente (Id, Nome, Cognome, Email, IAbb, FAbb, Day_corrente, Password, IdIstruttore, Palestra, Scheda_corrente) VALUES (3, 'Laura', 'Gialli', 'eeee@example.com', '2017-01-01', '2017-08-01', 1, 'Pass3', 3, 'Center Sport',5);
INSERT INTO Utente (Id, Nome, Cognome, Email, IAbb, FAbb, Day_corrente, Password, IdIstruttore, Palestra, Scheda_corrente) VALUES (4, 'Laura', 'Magenta', 'ppppp@example.com', '2017-01-01', '2017-08-01', 1, 'Pass4', 3, 'Golds Gym',6);
INSERT INTO Peso VALUES ('2017-01-01 17:30:22',72.50,1);
INSERT INTO Peso VALUES ('2017-01-16 17:41:13',73,1);
INSERT INTO Peso VALUES ('2017-04-01 17:37:33',73.50,1);
INSERT INTO Peso VALUES ('2017-04-16 19:00:05',72,1);
INSERT INTO Peso VALUES ('2017-05-01 17:37:33',71,1);
INSERT INTO Peso VALUES ('2017-05-16 19:00:05',70,1);
INSERT INTO Peso VALUES ('2017-06-01 17:37:33',70,1);
INSERT INTO Peso VALUES ('2017-06-16 19:00:05',69,1);
INSERT INTO Peso VALUES ('2017-07-01 17:37:33',68.5,1);
INSERT INTO Peso VALUES ('2017-07-16 17:37:33',68.5,1);
INSERT INTO Peso VALUES ('2017-08-01 17:37:33',69,1);
INSERT INTO Peso VALUES ('2017-08-08 08:08:08',70,2);
INSERT INTO Peso VALUES ('2017-09-01 10:10:10',50,3);
INSERT INTO Peso VALUES ('2017-09-08 09:09:09',48,4);
INSERT INTO Accesso VALUES ('2017-01-02 17:28:07',1);
INSERT INTO Accesso VALUES ('2017-01-03 17:27:45',1);
INSERT INTO Accesso VALUES ('2017-01-05 17:29:22',1);
INSERT INTO Accesso VALUES ('2017-01-07 17:30:23',1);
INSERT INTO Accesso VALUES ('2017-01-11 17:40:11',1);
INSERT INTO Accesso VALUES ('2017-01-22 17:15:59',1);
INSERT INTO Accesso VALUES ('2017-08-03 13:41:19',1);
INSERT INTO Accesso VALUES ('2017-08-03 13:41:20',1);
INSERT INTO Accesso VALUES ('2017-08-10 15:41:20',1);
INSERT INTO Accesso VALUES ('2017-08-10 15:41:00',4);
INSERT INTO Accesso VALUES ('2017-08-03 13:40:00',2);
INSERT INTO Accesso VALUES ('2017-08-03 13:40:22',3);
INSERT INTO Scheda VALUES (1,4,'massa','2017-01-01');
INSERT INTO Scheda VALUES (2,3,'massa','2017-02-01');
INSERT INTO Scheda VALUES (3,4,'massa','2017-03-01');
INSERT INTO Scheda VALUES (4,4,'massa','2017-03-01');
INSERT INTO Scheda VALUES (5,4,'massa','2017-03-01');
INSERT INTO Scheda VALUES (6,4,'def','2017-03-01');
INSERT INTO Utente_Scheda VALUES (1,1);
INSERT INTO Utente_Scheda VALUES (1,2);
INSERT INTO Utente_Scheda VALUES (1,3);
INSERT INTO Utente_Scheda VALUES (2,4);
INSERT INTO Utente_Scheda VALUES (3,5);
INSERT INTO Utente_Scheda VALUES (4,6);
INSERT INTO Scheda_Esercizio VALUES (1,1,'6x6',85,'negative 5 sec',1);
INSERT INTO Scheda_Esercizio VALUES (1,6,'2x12',12,"",1);
INSERT INTO Scheda_Esercizio VALUES (1,7,'2x8',14,'Ad esaurimento',1);
INSERT INTO Scheda_Esercizio VALUES (1,9,'4x4',14,"",1);
INSERT INTO Scheda_Esercizio VALUES (1,33,'3x8',20,'Fare leggero',1);
INSERT INTO Scheda_Esercizio VALUES (1,10,'6x6',160,"",2);
INSERT INTO Scheda_Esercizio VALUES (1,42,'3x8',50,"",2);
INSERT INTO Scheda_Esercizio VALUES (1,14,'4x4',45,"",2);
INSERT INTO Scheda_Esercizio VALUES (1,13,'2x max',"",'4 rip',2);
INSERT INTO Scheda_Esercizio VALUES (1,28,'3x8',10,"",2);
INSERT INTO Scheda_Esercizio VALUES (1,17,'6x6',120,"",3);
INSERT INTO Scheda_Esercizio VALUES (1,24,'4x4',120,"",3);
INSERT INTO Scheda_Esercizio VALUES (1,22,'2x12',15,"",3);
INSERT INTO Scheda_Esercizio VALUES (1,23,'3x12',20,"",3);
INSERT INTO Scheda_Esercizio VALUES (1,44,'2x30',90,"",3);
INSERT INTO Scheda_Esercizio VALUES (1,37,'6x6',30,"",4);
INSERT INTO Scheda_Esercizio VALUES (1,36,'4x4',14,"",4);
INSERT INTO Scheda_Esercizio VALUES (1,41,'2x12',25,'Cheat',4);
INSERT INTO Scheda_Esercizio VALUES (1,34,'4x4',15,"",4);
INSERT INTO Scheda_Esercizio VALUES (1,35,'2x max',0,"",4);
INSERT INTO Scheda_Esercizio VALUES (1,27,'4x4',20,"",4);
INSERT INTO Scheda_Esercizio VALUES (1,30,'2x max',10,"",4);
INSERT INTO Scheda_Esercizio VALUES (3,2,'6x6',40,'',1);
INSERT INTO Scheda_Esercizio VALUES (3,3,'2x12',50,"",1);
INSERT INTO Scheda_Esercizio VALUES (3,5,'4x6',35,'',1);
INSERT INTO Scheda_Esercizio VALUES (3,6,'4x4',14,"",1);
INSERT INTO Scheda_Esercizio VALUES (3,35,'3x8',0,'',1);
INSERT INTO Scheda_Esercizio VALUES (3,11,'6x6',80,"",2);
INSERT INTO Scheda_Esercizio VALUES (3,14,'3x8',50,"",2);
INSERT INTO Scheda_Esercizio VALUES (3,16,'4x4',45,"",2);
INSERT INTO Scheda_Esercizio VALUES (3,15,'2x max',"",'',2);
INSERT INTO Scheda_Esercizio VALUES (3,30,'3x8',10,"",2);
INSERT INTO Scheda_Esercizio VALUES (3,17,'4x6',120,"",3);
INSERT INTO Scheda_Esercizio VALUES (3,21,'4x4',120,"",3);
INSERT INTO Scheda_Esercizio VALUES (3,24,'2x12',90,"",3);
INSERT INTO Scheda_Esercizio VALUES (3,18,'3x12',90,"",3);
INSERT INTO Scheda_Esercizio VALUES (3,44,'8x8',100,"",3);
INSERT INTO Scheda_Esercizio VALUES (3,39,'6x6',30,"",4);
INSERT INTO Scheda_Esercizio VALUES (3,37,'4x4',30,"",4);
INSERT INTO Scheda_Esercizio VALUES (3,40,'2x12',10,'',4);
INSERT INTO Scheda_Esercizio VALUES (3,33,'4x4',20,"",4);
INSERT INTO Scheda_Esercizio VALUES (3,34,'2x max',15,"",4);
INSERT INTO Scheda_Esercizio VALUES (3,32,'4x4',20,"",4);
INSERT INTO Scheda_Esercizio VALUES (3,29,'2x max',10,"",4);
INSERT INTO Scheda_Esercizio VALUES (6,41,'4x4',20,"",1);
INSERT INTO Scheda_Esercizio VALUES (6,33,'2x max',10,"",1);
INSERT INTO Amici VALUES (2,3);