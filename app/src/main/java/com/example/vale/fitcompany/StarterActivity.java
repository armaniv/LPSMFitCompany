package com.example.vale.fitcompany;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.vale.fitcompany.DataBase.DBOperations;

public class StarterActivity extends AppCompatActivity {

    TextView gymName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        DBOperations db = DBOperations.getInstance(getApplicationContext());
        db.open();
        db.SetIdUtente(getApplicationContext());
        db.SetGym(getApplicationContext());

        TextView infoStatoPalestra = (TextView) findViewById(R.id.textBusy);
        infoStatoPalestra.setText(db.RitornaStatoPalestra());

        db.close();

        String gym="";
        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        gym = prefs.getString("Gym","");

        gymName=(TextView) findViewById(R.id.textGymname);
        gymName.setText(gym);

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

    public void StartNews(View v )
    {
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }

    public void StartCalendar(View v )
    {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    public void StartUtente(View v )
    {
        Intent intent = new Intent(this, UtenteAcitivity.class);
        startActivity(intent);
    }
}
