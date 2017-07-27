package com.example.vale.fitcompany;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.SharedPreferences;
import android.view.View;


import com.example.vale.fitcompany.DataBase.DBOperations;


public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        DBOperations db = DBOperations.getInstance(getApplicationContext());

        db.open();

        setContentView(R.layout.activity_main);


        if (!checkCache()){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        db.SetIdUtente(getApplicationContext());
        db.close();

    }
    private boolean checkCache()
    {
        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        String username = prefs.getString("username","");
        String pwd = prefs.getString("password","");
        if(username=="" || pwd==""){
            return false;
        }
        return true;
    }

    public void StartSchede(View v )
    {
        Intent intent = new Intent(this, SchedeActivity.class);
        startActivity(intent);
    }

}
