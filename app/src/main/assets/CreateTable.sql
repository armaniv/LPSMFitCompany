CREATE TABLE Istruttore(Id INTEGER,Nome VARCHAR(50),Cognome VARCHAR(50),Specializzazione VARCHAR(500),PRIMARY KEY(Id));
CREATE TABLE Utente(Id INTEGER,Nome VARCHAR(50),Cognome VARCHAR(50),Email VARCHAR(50),IAbb DATE,FAbb DATE,Day_corrente TINYINT,Password VARCHAR(25),IdIstruttore INTEGER NOT NULL,PRIMARY KEY(Id),FOREIGN KEY (IdIstruttore) REFERENCES Istruttore(Id));
CREATE TABLE Accesso(Tempo TIMESTAMP,IdUtente INTEGER NOT NULL,PRIMARY KEY(Tempo),FOREIGN KEY (IdUtente) REFERENCES Utente(Id));
CREATE TABLE Peso(Tempo TIMESTAMP,Kg DECIMAL(6,3),IdUtente INTEGER NOT NULL,PRIMARY KEY(Tempo),FOREIGN KEY (IdUtente) REFERENCES Utente(Id));
CREATE TABLE Scheda(Id INTEGER,NVolte TINYINT,Obbiettivo VARCHAR(20),DataInizio DATE,PRIMARY KEY(Id));
CREATE TABLE Esercizio(Id INTEGER,Nome VARCHAR(25),Focus VARCHAR(20),PRIMARY KEY(Id));
CREATE TABLE Amici(IdUtente1 INTEGER,IdUtente2 INTEGER,PRIMARY KEY(IdUtente1,IdUtente2),FOREIGN KEY (IdUtente1) REFERENCES Utente(Id),FOREIGN KEY (IdUtente2) REFERENCES Utente(Id));
CREATE TABLE Utente_Scheda(IdUtente INTEGER,IdScheda INTEGER,PRIMARY KEY(IdUtente,IdScheda),FOREIGN KEY (IdUtente) REFERENCES Utente(Id),FOREIGN KEY (IdScheda) REFERENCES Scheda(Id));
CREATE TABLE Scheda_Esercizio(IdScheda INTEGER,IdEsercizio INTEGER,Set_Rip VARCHAR(25),Peso DECIMAL(7,3),Note VARCHAR(255),NGiorno TINYINT,PRIMARY KEY(IdScheda,IdEsercizio),FOREIGN KEY (IdScheda) REFERENCES Scheda(Id),FOREIGN KEY (IdEsercizio) REFERENCES Esercizio(Id));