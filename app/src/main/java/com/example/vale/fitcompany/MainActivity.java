package com.example.vale.fitcompany;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        DBOperations db = DBOperations.getInstance(getApplicationContext());

        db.open();

        Cursor risultato = db.TrovaNomeUtente();

        String str="";

        if (risultato.moveToFirst())
        {
            str = risultato.getString(risultato.getColumnIndex("Nome"));
        }

        Log.d("PrimaQuery", "Nome utente: "+str);

        setContentView(R.layout.activity_main);
    }
}
