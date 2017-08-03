package com.example.vale.fitcompany;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;


import com.example.vale.fitcompany.DataBase.DBOperations;
import com.example.vale.fitcompany.Oggetti.News;


public class MainActivity extends AppCompatActivity
{
    TextView gymName;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (!checkCache()){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

        }
        else{
            Intent intent = new Intent(this, StarterActivity.class);
            startActivity(intent);
            finish();


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
