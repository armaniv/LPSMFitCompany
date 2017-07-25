package com.example.vale.fitcompany;

/**
 * Created by Vale on 24/07/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DBHelper extends SQLiteOpenHelper {

    Context myContext;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.myContext=context;
    }


    @Override
    public void onCreate(SQLiteDatabase _db)
    {

        //leggo il file CreateTable.sql dagli assets, creo in tal modo le tabelle
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(myContext.getAssets().open("CreateTable.sql"), "UTF-8"));

            String mLine;
            while ((mLine = reader.readLine()) != null)
                _db.execSQL(mLine);

        }
        catch (Exception e)
        {
            Log.e("ErroreCreate1", "Descrizione: " +e.toString() );
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (Exception e)
                {
                    Log.e("ErroreCreate2", "Descrizione: " +e.toString() );
                }
            }
        }


        //leggo il file Insert.sql dagli assets, popolo le tabelle
        try
        {
            reader = new BufferedReader(new InputStreamReader(myContext.getAssets().open("Insert.sql"), "UTF-8"));

            String mLine;
            while ((mLine = reader.readLine()) != null)
                _db.execSQL(mLine);
        }
        catch (Exception e)
        {
            Log.e("ErroreInsert1", "Descrizione: " +e.toString() );
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (Exception e)
                {
                    Log.e("ErroreInsert2", "Descrizione: " +e.toString() );
                }
            }
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        throw new UnsupportedOperationException("You have to implement this in order to upgrade database");

    }


}