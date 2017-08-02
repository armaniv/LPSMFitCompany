package com.example.vale.fitcompany.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;

import com.example.vale.fitcompany.Oggetti.Scheda;
import com.example.vale.fitcompany.Oggetti.Trainer;
import com.example.vale.fitcompany.Oggetti.Allenamento;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DBOperations {

    private SQLiteDatabase mDb;
    private DBHelper dbHelper;
    private static String DB_PATH = null;
    private static final String DB_NAME = "GymDB.db";
    private static final int DB_VERSION = 1;
    private static String ID_UTENTE="";//conterrà l'ID dell utente una volta effettuato l'accesso

    private static DBOperations instance = null;

    private DBOperations(Context ctx)
    {
        dbHelper = new DBHelper(ctx, DB_NAME, null, DB_VERSION);
        DB_PATH = ctx.getDatabasePath(DB_NAME).getPath();
    }

    public static DBOperations getInstance(Context ctx)
    {
        if (instance == null)
        {
            instance = new DBOperations(ctx);
        }
        return instance;
    }

    //setto la variabile globale ID_UTENTE
    public void SetIdUtente(Context ctx)
    {
        SharedPreferences prefs = ctx.getSharedPreferences("UserData", MODE_PRIVATE);
        String username = prefs.getString("username","");
        ID_UTENTE=username;
    }

    public void open()
    {
        mDb = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        mDb.close();
    }


   //----------------------inizio operazioni db----------------------------------------------


    public ArrayList<Scheda> RecuperaTutteSchedeUtenteConInfo()
    {
        //recupero Id di tutte le schede dell'utente loggato con le relativo relazioni e  le ordino per data
        Cursor c = mDb.rawQuery("SELECT IdScheda, NVolte, Obbiettivo, DataInizio FROM Scheda, Utente_Scheda WHERE Id=IdScheda AND IdUtente=? ORDER BY DataInizio DESC;",  new String[]{ID_UTENTE});

        //creo un Arraylist di Schede
        ArrayList<Scheda> risultato=new ArrayList <Scheda> ();

        String Nvolte,IdScheda,Obbiettivo,Data_inizio;
        Scheda scheda=null;

        //popolo arraylist di schede con le informazioni provenienti dal DB
        if (c.moveToFirst())
        {
            do
            {
                IdScheda = c.getString(c.getColumnIndex("IdScheda"));
                Nvolte = c.getString(c.getColumnIndex("NVolte"));
                Obbiettivo = c.getString(c.getColumnIndex("Obbiettivo"));
                Data_inizio = c.getString(c.getColumnIndex("DataInizio"));

                scheda = new Scheda(IdScheda,Nvolte,Obbiettivo,Data_inizio);
                risultato.add(scheda);

            } while (c.moveToNext());
        }

        return risultato;
    }


    public boolean ControllaLogin(String UtenteId, String Password)
    {
        boolean risultato=false;
        Cursor curRisultato = null;

        //recupero la password dell'utente
        try
        {
            curRisultato = mDb.rawQuery("SELECT Id,Password FROM Utente WHERE Id=?", new String[]{UtenteId});

            //controllo se la password corrisponde
            if(curRisultato!=null)
            {
                curRisultato.moveToFirst();
                String str = curRisultato.getString(curRisultato.getColumnIndex("Password"));

                if (str.equals(Password))
                    risultato=true;
            }
        }
        catch (Exception e )
        {
            Log.e("Errore", "Errore esecuzione query "+ e.toString());
        }

        return risultato;
    }

    public int GiornoCorrente()
    {
        int ris=0;
        Cursor c = mDb.rawQuery("SELECT Day_corrente FROM  Utente WHERE Id=?;",  new String[]{ID_UTENTE});

        if (c.moveToFirst())
            ris= Integer.parseInt(c.getString(c.getColumnIndex("Day_corrente")));

        return ris;
    }

    public List<Allenamento> RecuperaGiornoAllenamento(int idscheda,int day)
    {
        //recupero il giorno di allenamento all'interno della scheda
        Cursor c = mDb.rawQuery("SELECT Esercizio.Id, Nome, Set_Rip, Peso, Note FROM Scheda_Esercizio, Esercizio WHERE Id=IdEsercizio AND IdScheda=? AND NGiorno=?",  new String[]{String.valueOf(idscheda),String.valueOf(day)});

        List<Allenamento>  risultato= new ArrayList<Allenamento>();
        String idEsercizio="", Nome, Set_Rip, Peso, Note;

        Allenamento allenamento=null;

        if (c.moveToFirst()) {
            do {
                idEsercizio = c.getString(c.getColumnIndex("Id"));
                Nome = c.getString(c.getColumnIndex("Nome"));
                Set_Rip = c.getString(c.getColumnIndex("Set_Rip"));
                Peso = c.getString(c.getColumnIndex("Peso"));
                Note = c.getString(c.getColumnIndex("Note"));

                allenamento= new Allenamento(idEsercizio,Nome,Set_Rip,Peso,Note);
                risultato.add(allenamento);

            } while (c.moveToNext());
        }
        return risultato;
    }


    public List<Trainer> GetTrainers()
    {
        //recupero il giorno di allenamento all'interno della scheda
        Cursor c = mDb.rawQuery("SELECT Id,Nome, Cognome,Specializzazione FROM Istruttore",new String[]{});

        //creo un Arraylist di Stringhe
        List<Trainer>  risultato= new ArrayList<Trainer>();
        int tmp1;
        String tmp2,tmp3,tmp4;

        //popolo arraylist con le informazioni provenienti dal DB
        if (c.moveToFirst()) {
            do {
                tmp1 = c.getInt(c.getColumnIndex("Id"));
                tmp2 = c.getString(c.getColumnIndex("Nome"));
                tmp3 = c.getString(c.getColumnIndex("Cognome"));
                tmp4 = c.getString(c.getColumnIndex("Specializzazione"));
                risultato.add(new Trainer(tmp1,tmp2,tmp3,tmp4));

            } while (c.moveToNext());
        }
        return risultato;
    }

    public boolean SalvaGiornoAllenamento(String schedaid, Integer dayallenamento,List<String> CampiEditText)
    {
        boolean risultato=true;
        int idscheda = Integer.parseInt(schedaid);

        List<Allenamento> schedaVisualizzata = RecuperaGiornoAllenamento(idscheda,dayallenamento);

        Cursor c;
        String idEsercizio,SetRip,Peso,Note;
        int indiceListaEditText=0;
        ContentValues cv = new ContentValues();

        try
        {
            for (int i=0;i<schedaVisualizzata.size();i++)
            {
                idEsercizio=schedaVisualizzata.get(i).getIdEs();
                SetRip=schedaVisualizzata.get(i).getSet();
                Peso=CampiEditText.get(indiceListaEditText);
                indiceListaEditText++;
                Note=CampiEditText.get(indiceListaEditText);
                indiceListaEditText++;

                cv.clear();
                cv.put("IdScheda",idscheda);
                cv.put("IdEsercizio",idEsercizio);
                cv.put("Set_Rip",SetRip);
                cv.put("Peso",Peso);
                cv.put("Note",Note);

                mDb.update("Scheda_Esercizio", cv, "IdScheda="+schedaid +" AND IdEsercizio="+idEsercizio,null);
            }
        }
        catch (Exception e)
        {
            risultato=false;
            Log.e("Errore", "SalvaGiornoAllenamento: "+ e.toString() );
        }

        return risultato;
    }

}