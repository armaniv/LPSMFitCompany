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

    private static final String ARTICLE_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS );";

    @Override
    public void onCreate(SQLiteDatabase _db)
    {
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(
                    new InputStreamReader(myContext.getAssets().open("CreateTable.sql"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null)
            {
                _db.execSQL(mLine);
            }
        }  catch (Exception e)
        {
            Log.e("Errore", "Descrizione: " +e.toString() );
        } finally
        {
            if (reader != null) {
                try
                {
                    reader.close();
                } catch (Exception e)
                {
                    Log.e("Errore", "Descrizione: " +e.toString() );
                }
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        throw new UnsupportedOperationException("You have to implement this in order to upgrade database");

    }
}