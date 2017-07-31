package com.example.vale.fitcompany.DataBase;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.transition.Scene;
import android.util.Log;
import java.util.ArrayList;

import com.example.vale.fitcompany.Oggetti.Scheda;
import com.example.vale.fitcompany.Oggetti.Trainer;

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

    public List<String> RecuperaGiornoAllenamento(int idscheda,int day)
    {
        //recupero il giorno di allenamento all'interno della scheda
        Cursor c = mDb.rawQuery("SELECT Nome, Set_Rip, Peso, Note FROM Scheda_Esercizio, Esercizio WHERE Id=IdEsercizio AND IdScheda=? AND NGiorno=?",  new String[]{String.valueOf(idscheda),String.valueOf(day)});

        //creo un Arraylist di Stringhe
        List<String>  risultato= new ArrayList<String>();
        String str;

        //popolo arraylist con le informazioni provenienti dal DB
        if (c.moveToFirst()) {
            do {
                str = c.getString(c.getColumnIndex("Nome"));
                risultato.add(str);
                str = c.getString(c.getColumnIndex("Set_Rip"));
                risultato.add(str);
                str = c.getString(c.getColumnIndex("Peso"));
                risultato.add(str);
                str = c.getString(c.getColumnIndex("Note"));
                risultato.add(str);

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

}