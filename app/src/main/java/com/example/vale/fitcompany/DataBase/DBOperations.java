package com.example.vale.fitcompany.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBOperations {

    private SQLiteDatabase mDb;
    private DBHelper dbHelper;
    private static String DB_PATH = null;
    private static final String DB_NAME = "GymDB.db";
    private static final int DB_VERSION = 1;

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

    public void CancellaTabelleaDB(String Param)
    {
        mDb.execSQL("DROP TABLE IF EXISTS "+ Param);
    }

    public boolean ControllaLogin(String UtenteId, String Password)
    {
        boolean risultato=false;
        Cursor curRisultato = null,checkId=null;

        //recupero la password dell'utente
        try
        {
            curRisultato = mDb.rawQuery("SELECT Id,Password FROM Utente WHERE Id=?", new String[]{UtenteId});

            //controllo se la password corrisponde
            if(curRisultato!=null)
            {
                curRisultato.moveToFirst();
                String str = curRisultato.getString(curRisultato.getColumnIndex("Password")).toString();

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

    public void open()
    {
        mDb = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        mDb.close();
    }

}