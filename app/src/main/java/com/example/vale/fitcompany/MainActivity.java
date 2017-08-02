package com.example.vale.fitcompany;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;


import com.example.vale.fitcompany.DataBase.DBOperations;


public class MainActivity extends AppCompatActivity
{
    TextView gymName;
    DBOperations db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        db = DBOperations.getInstance(getApplicationContext());

        db.open();


        setContentView(R.layout.activity_menu);


        if (!checkCache()){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        db.SetIdUtente(getApplicationContext());
        findGym();
        db.close();




    }
    private void findGym()
    {
        String gym=db.getGym();
        gymName=(TextView) findViewById(R.id.textGymname);
        gymName.setText(gym);
        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Gym",gym );
        editor.commit();
        db.SetGym(getApplicationContext());

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
    public void StartTrainers(View v )
    {
        Intent intent = new Intent(this, TrainersActivity.class);
        startActivity(intent);
    }

}
