package com.example.vale.fitcompany.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.text.style.TtsSpan;
import android.util.FloatProperty;
import android.util.Log;
import android.util.Pair;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.example.vale.fitcompany.Oggetti.Chiusura;
import com.example.vale.fitcompany.Oggetti.News;
import com.example.vale.fitcompany.Oggetti.Orario;
import com.example.vale.fitcompany.Oggetti.Peso;
import com.example.vale.fitcompany.Oggetti.Scheda;
import com.example.vale.fitcompany.Oggetti.Trainer;
import com.example.vale.fitcompany.Oggetti.Allenamento;
import com.example.vale.fitcompany.Oggetti.Utente;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class DBOperations {

    private SQLiteDatabase mDb;
    private DBHelper dbHelper;
    private static String DB_PATH = null;
    private static final String DB_NAME = "GymDB.db";
    private static final int DB_VERSION = 1;
    private static String ID_UTENTE = "";//conterrà l'ID dell utente una volta effettuato l'accesso
    private static String GYM;//conterrà il nome della palestra


    private static DBOperations instance = null;

    private DBOperations(Context ctx) {
        dbHelper = new DBHelper(ctx, DB_NAME, null, DB_VERSION);
        DB_PATH = ctx.getDatabasePath(DB_NAME).getPath();
    }

    public static DBOperations getInstance(Context ctx) {
        if (instance == null) {
            instance = new DBOperations(ctx);
        }
        return instance;
    }

    //setto la variabile globale ID_UTENTE
    public void SetIdUtente(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("UserData", MODE_PRIVATE);
        String username = prefs.getString("username", "");
        ID_UTENTE = username;
    }

    //setto la variabile globale GYM
    public void SetGym(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("UserData", MODE_PRIVATE);
        String gym = prefs.getString("Gym", "");
        GYM = gym;
    }

    public void open() {
        mDb = dbHelper.getWritableDatabase();
    }

    public void close() {
        mDb.close();
    }


    //----------------------inizio operazioni DB----------------------------------------------

    //procedura che fissa la palestra all'interno dell'app.
    public String getGym(String username) {
        /*In caso il db fosse esclusivo per una unica palestra la query da eseguire sarebbe semplicemnte:
        SELECT Nome FROM Palestra
        nel nostro caso invece, la query si "complica" perchè per fini dimostrativi nel nostro DB sono presenti due palestre*/

        Cursor c = mDb.rawQuery("SELECT Palestra FROM Utente WHERE Id=?;", new String[]{username});
        c.moveToFirst();
        return c.getString(c.getColumnIndex("Palestra"));
    }


    //procedura che recupera tutte le schede personali dell'utente loggato
    public ArrayList<Scheda> RecuperaTutteSchedeUtenteConInfo() {
        //recupero Id di tutte le schede dell'utente loggato con le relativo relazioni e  le ordino per data
        Cursor c = mDb.rawQuery("SELECT IdScheda, NVolte, Obbiettivo, DataInizio FROM Scheda, Utente_Scheda WHERE Id=IdScheda AND IdUtente=? ORDER BY DataInizio DESC;", new String[]{ID_UTENTE});

        //creo un Arraylist di Schede
        ArrayList<Scheda> risultato = new ArrayList<Scheda>();

        String Nvolte, IdScheda, Obbiettivo, Data_inizio;
        Scheda scheda = null;

        //popolo arraylist di schede con le informazioni provenienti dal DB
        if (c.moveToFirst()) {
            do {
                IdScheda = c.getString(c.getColumnIndex("IdScheda"));
                Nvolte = c.getString(c.getColumnIndex("NVolte"));
                Obbiettivo = c.getString(c.getColumnIndex("Obbiettivo"));
                Data_inizio = c.getString(c.getColumnIndex("DataInizio"));

                scheda = new Scheda(IdScheda, Nvolte, Obbiettivo, Data_inizio);
                risultato.add(scheda);

            } while (c.moveToNext());
        }

        return risultato;
    }

    //procedura che controlla se le informazioni inserite durante la fase di login corrispondono con quelle memorizzate nel DB
    public boolean ControllaLogin(String UtenteId, String Password) {
        boolean risultato = false;
        Cursor curRisultato = null;

        //recupero la password dell'utente
        try {
            curRisultato = mDb.rawQuery("SELECT Id,Password FROM Utente WHERE Id=?", new String[]{UtenteId});

            //controllo se la password corrisponde
            if (curRisultato != null) {
                curRisultato.moveToFirst();
                String str = curRisultato.getString(curRisultato.getColumnIndex("Password"));

                if (str.equals(Password))
                    risultato = true;
            }
        } catch (Exception e) {
            Log.e("Errore", "Errore esecuzione query " + e.toString());
        }

        return risultato;
    }


    //procedura che recupera dal DB il giorno di allenamento (della scheda) che l'utente dovrà farà (viene aggiornato ad ogni ingresso)
    public int GiornoCorrente() {
        int ris = 0;
        Cursor c = mDb.rawQuery("SELECT Day_corrente FROM  Utente WHERE Id=?;", new String[]{ID_UTENTE});

        if (c.moveToFirst())
            ris = Integer.parseInt(c.getString(c.getColumnIndex("Day_corrente")));

        return ris;
    }


    //procedura che recupero un giorno di allenamento di una determinata scheda. Ritorna una List<Allenamento>
    public List<Allenamento> RecuperaGiornoAllenamento(int idscheda, int day) {
        //recupero il giorno di allenamento all'interno della scheda
        Cursor c = mDb.rawQuery("SELECT Esercizio.Id, Nome, Set_Rip, Peso, Note FROM Scheda_Esercizio, Esercizio WHERE Id=IdEsercizio AND IdScheda=? AND NGiorno=?", new String[]{String.valueOf(idscheda), String.valueOf(day)});

        List<Allenamento> risultato = new ArrayList<Allenamento>();
        String idEsercizio = "", Nome, Set_Rip, Peso, Note;

        Allenamento allenamento = null;

        if (c.moveToFirst()) {
            do {
                idEsercizio = c.getString(c.getColumnIndex("Id"));
                Nome = c.getString(c.getColumnIndex("Nome"));
                Set_Rip = c.getString(c.getColumnIndex("Set_Rip"));
                Peso = c.getString(c.getColumnIndex("Peso"));
                Note = c.getString(c.getColumnIndex("Note"));
                allenamento = new Allenamento(idEsercizio, Nome, Set_Rip, Peso, Note);
                risultato.add(allenamento);

            } while (c.moveToNext());
        }
        return risultato;
    }

    //procedure che recupera dal DB i trainers presenti nella palestra e li restituisce sotto forma di List<Trainer>
    public List<Trainer> GetTrainers() {
        /*In caso il db fosse esclusivo per una unica palestra la query da eseguire sarebbe semplicemnte:
        SELECT Id,Nome, Cognome,Specializzazione FROM Istruttore
        nel nostro caso invece, la query si "complica" perchè per fini dimostrativi nel nostro DB sono presenti due palestre*/
        Cursor c = mDb.rawQuery("SELECT Id,Nome, Cognome,Specializzazione FROM Istruttore WHERE IdPalestra=?", new String[]{GYM});

        List<Trainer> risultato = new ArrayList<Trainer>();
        int tmp1;
        String tmp2, tmp3, tmp4;

        if (c.moveToFirst()) {
            do {
                tmp1 = c.getInt(c.getColumnIndex("Id"));
                tmp2 = c.getString(c.getColumnIndex("Nome"));
                tmp3 = c.getString(c.getColumnIndex("Cognome"));
                tmp4 = c.getString(c.getColumnIndex("Specializzazione"));
                risultato.add(new Trainer(tmp1, tmp2, tmp3, tmp4));

            } while (c.moveToNext());
        }
        return risultato;
    }


    //procedure che recupera dal DB le notizie relative alla palestra (le News) e le restituisce sotto forma di List<News>
    public List<News> GetNews() {
        /*In caso il db fosse esclusivo per una unica palestra la query da eseguire sarebbe semplicemnte:
        SELECT * FROM Notizia
        nel nostro caso invece, la query si "complica" perchè per fini dimostrativi nel nostro DB sono presenti due palestre*/
        Cursor c = mDb.rawQuery("SELECT * FROM Notizia WHERE IdPalestra=?", new String[]{GYM});

        List<News> risultato = new ArrayList<>();
        String tmp2, tmp3, tmp4;

        if (c.moveToFirst()) {
            do {
                tmp2 = c.getString(c.getColumnIndex("IdPalestra"));
                tmp3 = c.getString(c.getColumnIndex("Descrizione"));
                tmp4 = c.getString(c.getColumnIndex("Data"));
                risultato.add(new News(tmp2, tmp3, tmp4));

            } while (c.moveToNext());
        }
        return risultato;
    }


    //procedure che recupera dal DB orario della palestra e lo restituisce sotto forma di List<Orario>
    public List<Orario> GetOrario() {
        /*In caso il db fosse esclusivo per una unica palestra la query da eseguire sarebbe semplicemnte:
        SELECT * FROM Orario
        nel nostro caso invece, la query si "complica" perchè per fini dimostrativi nel nostro DB sono presenti due palestre*/
        Cursor c = mDb.rawQuery("SELECT * FROM Orario WHERE IdPalestra=?", new String[]{GYM});

        List<Orario> risultato = new ArrayList<>();
        String tmp2, tmp3;

        if (c.moveToFirst()) {
            do {
                tmp2 = c.getString(c.getColumnIndex("Giorno"));
                tmp3 = c.getString(c.getColumnIndex("Orario"));
                risultato.add(new Orario(tmp2, tmp3));

            } while (c.moveToNext());
        }
        return risultato;
    }

    //procedure che recupera dal DB le chiusure fouri dal ordinario della palestra e lo restituisce sotto forma di List<Chiusura>
    public List<Chiusura> GetChiusura() throws ParseException {
        /*In caso il db fosse esclusivo per una unica palestra la query da eseguire sarebbe semplicemnte:
        SELECT * FROM Chiusura
        nel nostro caso invece, la query si "complica" perchè per fini dimostrativi nel nostro DB sono presenti due palestre*/
        Cursor c = mDb.rawQuery("SELECT * FROM Chiusura WHERE IdPalestra=?", new String[]{GYM});

        List<Chiusura> risultato = new ArrayList<>();
        String tmp2, tmp3;

        if (c.moveToFirst()) {
            do {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                tmp2 = c.getString(c.getColumnIndex("dataInizio"));
                tmp3 = c.getString(c.getColumnIndex("dataFine"));
                Date date1 = format.parse(tmp2);
                Date date2 = format.parse(tmp3);

                risultato.add(new Chiusura(date1, date2));

            } while (c.moveToNext());
        }
        return risultato;
    }

    //procedura che salva eventuale modifiche apporta alle Editext nella schermata in cui è visibile  l'allenamento
    //List<String> CampiEditText è la lista di editext visualizzate a schermata
    public boolean SalvaGiornoAllenamento(String schedaid, Integer dayallenamento, List<String> CampiEditText) {
        boolean risultato = true;
        int idscheda = Integer.parseInt(schedaid);

        List<Allenamento> schedaVisualizzata = RecuperaGiornoAllenamento(idscheda, dayallenamento);

        Cursor c;
        String idEsercizio, SetRip, Peso, Note;
        int indiceListaEditText = 0;
        ContentValues cv = new ContentValues();

        try {
            //recupera i campi dei valori visualizzati a schermo
            for (int i = 0; i < schedaVisualizzata.size(); i++) {
                idEsercizio = schedaVisualizzata.get(i).getIdEs();
                SetRip = schedaVisualizzata.get(i).getSet();
                Peso = CampiEditText.get(indiceListaEditText);
                indiceListaEditText++;
                Note = CampiEditText.get(indiceListaEditText);
                indiceListaEditText++;

                //esecuzione UPDATE database
                cv.clear();
                cv.put("IdScheda", idscheda);
                cv.put("IdEsercizio", idEsercizio);
                cv.put("Set_Rip", SetRip);
                cv.put("Peso", Peso);
                cv.put("Note", Note);

                mDb.update("Scheda_Esercizio", cv, "IdScheda=" + schedaid + " AND IdEsercizio=" + idEsercizio, null);
            }
        } catch (Exception e) {
            risultato = false;
            Log.e("Errore", "SalvaGiornoAllenamento: " + e.toString());
        }

        return risultato;
    }


    //restituisce lo stato di affolamento della palestra confrontando: il numero di accessi effettuati un'ora e mezza prima di questo
    //momento con il numero di  soglia dentro il DB.
    public String RitornaStatoPalestra() {
        String stato = "";
        int soglia1, soglia2;
        String tmp;

        Cursor c = mDb.rawQuery("SELECT * FROM Palestra WHERE Nome=?", new String[]{GYM});
        c.moveToFirst();

        tmp = c.getString(c.getColumnIndex("SogliaMedioAffollato"));
        soglia1 = Integer.parseInt(tmp);

        tmp = c.getString(c.getColumnIndex("SogliaTantoAffollato"));
        soglia2 = Integer.parseInt(tmp);

        /*In caso il db fosse esclusivo per una unica palestra la query da eseguire sarebbe semplicemnte:
        SELECT Tempo FROM Accesso WHERE Tempo >=datetime('now', '-1.5 Hour')
        nel nostro caso invece, la query si "complica" perchè per fini dimostrativi nel nostro DB sono presenti due palestre*/
        Cursor curs = mDb.rawQuery("SELECT Tempo FROM Accesso, Utente WHERE Id=IdUtente AND Tempo >=datetime('now', '-1.5 Hour') AND Palestra=?", new String[]{GYM});

        curs.moveToFirst();
        int numeroaccessi = curs.getCount();

        if (numeroaccessi <= soglia1)
            stato = "Stao: Poco affollata";
        else if (soglia1 < numeroaccessi && numeroaccessi <= soglia2)
            stato = "Stao: Abbastanza affollata";
        else
            stato = "Stao: Molto affollata";

        return stato;
    }


    //metodo che resistuisce le informazioni provenenti dal db riguardo l'utente al momento loggato e restituendo
    //un oggetto Utente
    public Utente RitornaUtenteAttuale() {
        Cursor c = mDb.rawQuery("SELECT * FROM Utente WHERE Id=?", new String[]{ID_UTENTE});

        String Nome, Cognome, InizioAbb, FineAbb;
        c.moveToFirst();

        Nome = c.getString(c.getColumnIndex("Nome"));
        Cognome = c.getString(c.getColumnIndex("Cognome"));
        InizioAbb = c.getString(c.getColumnIndex("IAbb"));
        FineAbb = c.getString(c.getColumnIndex("FAbb"));

        Utente utente = new Utente(Nome, Cognome, InizioAbb, FineAbb);

        return utente;
    }


    //metodo che resistuisce l'ultimo peso inserito dell'utente loggato
    public String GetUltimoPesoPersona() {
        String peso = "";

        Cursor c = mDb.rawQuery("SELECT * FROM Peso WHERE IdUtente=? ORDER BY Tempo DESC LIMIT 1;", new String[]{ID_UTENTE});
        c.moveToFirst();
        peso = c.getString(c.getColumnIndex("Kg"));

        return peso;
    }


    //metodo che serve ad inserire nel DB un nuovo peso dell'utente già loggato
    public boolean InserisciNuovoPeso(String Peso) {
        boolean ris = true;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            ContentValues values = new ContentValues();
            values.put("Tempo", dateFormat.format(date));
            values.put("Kg", Peso);
            values.put("IdUtente", ID_UTENTE);

            mDb.insert("Peso", null, values);
        } catch (Exception e) {
            ris = false;
            Log.e("Errore", "InserisciNuovoPeso: " + e.toString());
        }

        return ris;
    }


    //procedure che recupera dal DB i valori del peso inseriti dall'utente  e li restituisce sotto forma di List<Peso>
    public List<Peso> RecuperaValoriPeso(Integer periodo) {
        Cursor c;
        //recupero il peso corporeo inserito dall utente

        if (periodo == 0) //ovvero ho selezionato "Ultimo mese"
            c = mDb.rawQuery("SELECT * FROM Peso WHERE Tempo>= datetime('now','-1 month') AND IdUtente=?", new String[]{ID_UTENTE});

        else if (periodo == 1) //ovvero ho selezionato "Ultimo trimestre"
            c = mDb.rawQuery("SELECT * FROM Peso WHERE Tempo>= datetime('now','-3 month') AND IdUtente=?", new String[]{ID_UTENTE});

        else //ovvero ho selezionato "Ultimo semestre"
            c = mDb.rawQuery("SELECT * FROM Peso WHERE Tempo>= datetime('now','-6 month') AND IdUtente=?", new String[]{ID_UTENTE});


        List<Peso> risultato = new ArrayList<Peso>();
        String Tempo, Kg;

        Peso valorePeso;

        if (c.moveToFirst()) {
            do {
                Tempo = c.getString(c.getColumnIndex("Tempo"));
                Kg = c.getString(c.getColumnIndex("Kg"));
                valorePeso = new Peso(Tempo, Kg);
                risultato.add(valorePeso);

            } while (c.moveToNext());
        }
        return risultato;
    }


    //procedura che restituisce il numero di utenti che hanno effettuato l'acesso un'ora e mezza prima rispetto a
    //quando il metodo viene invocato
    public Integer NumeroUtentiAttuali() {
        /*In caso il db fosse esclusivo per una unica palestra la query da eseguire sarebbe semplicemnte:
        SELECT Tempo FROM Accesso WHERE Tempo >=datetime('now', '-1.5 Hour')
        nel nostro caso invece, la query si "complica" perchè per fini dimostrativi nel nostro DB sono presenti due palestre*/
        Cursor curs = mDb.rawQuery("SELECT Tempo FROM Accesso, Utente WHERE Id=IdUtente AND Tempo >=datetime('now', '-1.5 Hour') AND Palestra=?", new String[]{GYM});

        int numeroaccessi = 0;

        if (curs.moveToFirst())
            numeroaccessi = curs.getCount();

        return numeroaccessi;
    }


    //metodo che ritorna sotto forma di vettore di percentuali, le percentuali (in ordine ) di: Petto,Schiena,Gambe,Braccia,Spalle
    //di gruppi muscolari che si stannno allendando in questo momento in palestra
    public  Float[] RitornaGruppiMuscolariAllenatiAdesso()
    {
        //query un'po arificiosa: in una sotto query si calcolanno tutte le tabelle con il relativo giorno di allenamento degli utenti che
        //hanno effettuato l'accesso e si trovano adesso in palestra. Grazie alla sotto quesi si trova per pgni utente cosa sta allenando in quel
        //giorno.
        Cursor c = mDb.rawQuery("SELECT DISTINCT Temp.Id, Focus " +
                "FROM Esercizio, Scheda_Esercizio, " +
                "(SELECT Utente.Id, Scheda_corrente, Day_corrente " +
                "FROM Accesso, Utente " +
                "WHERE Utente.Id=Accesso.IdUtente " +
                "AND Accesso.Tempo >=datetime('now', '-1.5 Hour') AND Palestra=? )AS Temp " +
                "WHERE IdScheda=Scheda_corrente and IdEsercizio=Esercizio.Id AND NGiorno=Day_corrente", new String[]{GYM});


        String idpers, Focus;
        Integer NumeroRighe;
        Float Petto = 0f, Gambe = 0f, Schiena = 0f, Braccia = 0f, Spalle = 0f;

        //conto quanta gente sta allenando cosa
        if (c.moveToFirst())
        {
            NumeroRighe = c.getCount();
            do {
                //idpers = c.getString(c.getColumnIndex("Temp.Id"));
                Focus = c.getString(c.getColumnIndex("Focus"));

                if (Focus.equals("Petto"))
                    Petto++;
                else if (Focus.equals("Schiena"))
                    Schiena++;
                else if (Focus.equals("Gambe"))
                    Gambe++;
                else if (Focus.equals("Braccia"))
                    Braccia++;
                else if (Focus.equals("Spalle"))
                    Spalle++;

            } while (c.moveToNext());


            //trasformo i valori in percentuale
            if (Petto!=0)
                Petto= (Petto/NumeroRighe)*100;

            if (Schiena!=0)
                Schiena= (Schiena/NumeroRighe)*100;

            if (Gambe!=0)
                Gambe= (Gambe/NumeroRighe)*100;

            if (Braccia!=0)
                Braccia= (Braccia/NumeroRighe)*100;

            if (Spalle!=0)
                Spalle= (Spalle/NumeroRighe)*100;
        }

        //compongo il vettore da restituire. ATTENZIONE ALL'ORDINE !!!!
        Float[] valori = new Float[5];
        valori[0]=Petto;
        valori[1]=Schiena;
        valori[2]=Gambe;
        valori[3]=Braccia;
        valori[4]=Spalle;

        return valori;
    }


    //metodo che inserisce nel DB la domanda che un utente fa ad un persona trainer
    public void InserisciDomandaDataBase(String domanda, Integer IdIstruttore)
    {
        //idDomanda PRIMARY KEY è AUTO_INCREMENT
        ContentValues values = new ContentValues();
        values.put("IdUtente", ID_UTENTE);
        values.put("IdIstruttore", IdIstruttore);
        values.put("Domanda", domanda);

        mDb.insert("Domande", null, values);
    }

    //metodo che restituisce la posizione, nel formato Location,  della palestra
    public Location PosizionePalestra()
    {
        Location posizione = new Location("");//provider name is unnecessary

        Cursor c = mDb.rawQuery("SELECT * FROM Palestra WHERE Nome=?", new String[]{GYM});
        c.moveToFirst();

        String lat,longi;

        lat= c.getString(c.getColumnIndex("Latitudine"));
        longi= c.getString(c.getColumnIndex("Longitudine"));

        Double dlat = Double.parseDouble(lat);
        Double dlong = Double.parseDouble(longi);


        posizione.setLatitude(dlat);
        posizione.setLongitude(dlong);

        return posizione;
    }


    //metodo per registrare un accesso all'interno del DB. Valido sia per eventuali tornelli che per il pulsante sostituitco
    //per l'accesso
    public void CreaAccesso() {
        //inserisco un accesso nella tebella Accesso del DB
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        ContentValues values = new ContentValues();
        values.put("Tempo", dateFormat.format(date));
        values.put("IdUtente", ID_UTENTE);

        mDb.insert("Accesso", null, values);


        //aggiorno il valore di Day_corrente, questo valore serve per prevedere in maniera quale seduta (o giorno
        //di allenamento) dovrà svolgere di volta in volta l'utente

        //recupero il giorno corrente e la id scheda dell'utente
        Cursor c = mDb.rawQuery("SELECT * FROM Utente WHERE Id=?", new String[]{ID_UTENTE});
        c.moveToFirst();
        String dayvecchio = c.getString(c.getColumnIndex("Day_corrente"));
        String scheda = c.getString(c.getColumnIndex("Scheda_corrente"));
        int seduta = (Integer.parseInt(dayvecchio)) + 1;//incremento di uno il valore

        //recupero il quanti giorni è strutturata la tabella corrente
        Cursor c2 = mDb.rawQuery("SELECT * FROM Scheda WHERE Id=?", new String[]{scheda});
        c2.moveToFirst();
        String quantevolte = c2.getString(c2.getColumnIndex("NVolte"));
        int max = Integer.parseInt(quantevolte);

        //se la seduta prevista supera il numero di suddivisione si deve ripartire con la prima prevista da scheda
        if (seduta > max)
            seduta = 1;

        ContentValues cv = new ContentValues();
        cv.put("Day_corrente", seduta);

        mDb.update("Utente", cv, "Id=" + ID_UTENTE, null);
    }


}
