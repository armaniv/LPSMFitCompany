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
INSERT INTO Esercizio VALUES (11,'Rematore bil.','Schiena');
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
INSERT INTO Istruttore VALUES (1,'Marco','Bianchi','Specializzato in body-building, istruttore di livello 3');
INSERT INTO Istruttore VALUES (2,'Giovanni','Rossi','Specializzato in power lifting, istruttore di livello 3');
INSERT INTO Istruttore VALUES (3,'Chiara','Verdi','Specializzata in tecniche della riabilitazione e fitness-model');
INSERT INTO Istruttore VALUES (4,'Mario','Neri','Personal trainer di livello 2, riconosciuto. Preparatore del campione italiano di Man physique');
INSERT INTO Utente VALUES (1,'Valentino','Armani','aaaa@bbbb.com','2017-01-01','2019-01-01',1,'P@ssword',4);
INSERT INTO Utente VALUES (2,'Jacek','Zbudowski','cccc@dddd.com','2017-01-01','2018-01-01',1,'Password2',2);
INSERT INTO Utente VALUES (3,'Laura','Gialli','eeee@example.com','2017-01-01','2017-08-01',1,'Pass3',3);
INSERT INTO Peso VALUES ('2017-01-02 17:30:22',72.50,1);
INSERT INTO Peso VALUES ('2017-01-12 17:41:13',73,1);
INSERT INTO Peso VALUES ('2017-01-22 17:37:33',73.50,1);
INSERT INTO Peso VALUES ('2017-02-22 19:00:05',74,1);
INSERT INTO Accesso VALUES ('2017-01-02 17:28:07',1);
INSERT INTO Accesso VALUES ('2017-01-03 17:27:45',1);
INSERT INTO Accesso VALUES ('2017-01-05 17:29:22',1);
INSERT INTO Accesso VALUES ('2017-01-07 17:30:23',1);
INSERT INTO Accesso VALUES ('2017-01-11 17:40:11',1);
INSERT INTO Accesso VALUES ('2017-01-22 17:15:59',1);
INSERT INTO Scheda VALUES (1,4,'massa','2017-01-01');
INSERT INTO Scheda VALUES (2,3,'massa','2017-02-01');
INSERT INTO Scheda VALUES (3,4,'massa','2017-03-01');
INSERT INTO Utente_Scheda VALUES (1,1);
INSERT INTO Utente_Scheda VALUES (1,2);
INSERT INTO Utente_Scheda VALUES (1,3);
INSERT INTO Scheda_Esercizio VALUES (1,1,'6x6',85,'Peso forzato',1);
INSERT INTO Scheda_Esercizio VALUES (1,6,'2x12',12,"",1);
INSERT INTO Scheda_Esercizio VALUES (1,7,'2x8',14,'Ad esaurimento',1);
INSERT INTO Scheda_Esercizio VALUES (1,9,'4x4',14,"",1);
INSERT INTO Scheda_Esercizio VALUES (1,33,'3x8',20,'Fare leggero',1);
INSERT INTO Scheda_Esercizio VALUES (1,10,'6x6',160,"",2);
INSERT INTO Scheda_Esercizio VALUES (1,42,'3x8',50,"",2);
INSERT INTO Scheda_Esercizio VALUES (1,14,'4x4',45,"",2);
INSERT INTO Scheda_Esercizio VALUES (1,13,'2x max',"",'4 movimenti',2);
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
INSERT INTO Amici VALUES (1,2);