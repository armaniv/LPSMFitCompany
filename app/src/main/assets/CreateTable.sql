CREATE TABLE Istruttore (Id INTEGER, Nome VARCHAR (50), Cognome VARCHAR (50), Specializzazione VARCHAR (500), IdPalestra VARCHAR (50) REFERENCES Palestra (Nome) NOT NULL, PRIMARY KEY (Id));
CREATE TABLE Utente (Id INTEGER, Nome VARCHAR (50), Cognome VARCHAR (50), Email VARCHAR (50), IAbb DATE, FAbb DATE, Day_corrente TINYINT, Password VARCHAR (25), IdIstruttore INTEGER NOT NULL, Palestra VARCHAR (50) REFERENCES Palestra (Nome) NOT NULL,Scheda_corrente INTEGER REFERENCES Scheda(Id) NOT NULL,PRIMARY KEY (Id), FOREIGN KEY (IdIstruttore) REFERENCES Istruttore (Id));
CREATE TABLE Accesso(Tempo TIMESTAMP,IdUtente INTEGER NOT NULL,PRIMARY KEY(Tempo),FOREIGN KEY (IdUtente) REFERENCES Utente(Id));
CREATE TABLE Peso(Tempo TIMESTAMP,Kg DECIMAL(6,3),IdUtente INTEGER NOT NULL,PRIMARY KEY(Tempo),FOREIGN KEY (IdUtente) REFERENCES Utente(Id));
CREATE TABLE Scheda(Id INTEGER,NVolte TINYINT,Obbiettivo VARCHAR(20),DataInizio DATE,PRIMARY KEY(Id));
CREATE TABLE Esercizio(Id INTEGER,Nome VARCHAR(25),Focus VARCHAR(20),PRIMARY KEY(Id));
CREATE TABLE Amici(IdUtente1 INTEGER,IdUtente2 INTEGER,PRIMARY KEY(IdUtente1,IdUtente2),FOREIGN KEY (IdUtente1) REFERENCES Utente(Id),FOREIGN KEY (IdUtente2) REFERENCES Utente(Id));
CREATE TABLE Utente_Scheda(IdUtente INTEGER,IdScheda INTEGER,PRIMARY KEY(IdUtente,IdScheda),FOREIGN KEY (IdUtente) REFERENCES Utente(Id),FOREIGN KEY (IdScheda) REFERENCES Scheda(Id));
CREATE TABLE Scheda_Esercizio(IdScheda INTEGER,IdEsercizio INTEGER,Set_Rip VARCHAR(25),Peso DECIMAL(7,3),Note VARCHAR(255),NGiorno TINYINT,PRIMARY KEY(IdScheda,IdEsercizio),FOREIGN KEY (IdScheda) REFERENCES Scheda(Id),FOREIGN KEY (IdEsercizio) REFERENCES Esercizio(Id));
CREATE TABLE Notizia (IdPalestra VARCHAR (50) NOT NULL REFERENCES Palestra (Nome), Descrizione VARCHAR (500), Data DATE, PRIMARY KEY (IdPalestra, Data));
CREATE TABLE Palestra (Nome VARCHAR (50),Indirizzo VARCHAR (50),SogliaMedioAffollato INTEGER,SogliaTantoAffollato INTEGER,PRIMARY KEY(Nome));
CREATE TABLE Orario (IdPalestra VARCHAR (50) NOT NULL REFERENCES Palestra (Nome), Giorno VARCHAR (50), Orario VARCHAR(50), PRIMARY KEY (IdPalestra, Giorno));
CREATE TABLE Chiusura (IdPalestra VARCHAR (50) NOT NULL REFERENCES Palestra (Nome), dataInizio DATE,dataFine DATE, PRIMARY KEY (IdPalestra, dataFine));
CREATE TABLE Domande (IdDomanda INTEGER AUTO_INCREMENT, IdUtente INTEGER NOT NULL REFERENCES Utente (Id), IdIstruttore INTEGER NOT NULL REFERENCES Istruttore (Id),Domanda VARCHAR(500), PRIMARY KEY (IdDomanda));