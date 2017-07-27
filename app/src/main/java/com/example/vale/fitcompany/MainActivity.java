package com.example.vale.fitcompany;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.SharedPreferences;


import com.example.vale.fitcompany.DataBase.DBOperations;


public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        DBOperations db = DBOperations.getInstance(getApplicationContext());

        db.open();


        boolean checkPass= db.ControllaLogin("1","P@ssword");
        db.close();
        if (checkPass==true)
            Log.d("ProvaQuery", "Login effettuato");
        else
            Log.d("ProvaQuery", "Login fallito");

        setContentView(R.layout.activity_menu);


        if (!checkCache())
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }


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

}
