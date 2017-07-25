package com.example.vale.fitcompany;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.vale.fitcompany.DataBase.DBOperations;


public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        DBOperations db = DBOperations.getInstance(getApplicationContext());

        db.open();

        boolean checkPass= db.ControllaLogin(1,"P@ssword");

        if (checkPass==true)
            Log.d("ProvaQuery", "Login effettuato");
        else
            Log.d("ProvaQuery", "Login fallito");

        setContentView(R.layout.activity_main);
    }
}
