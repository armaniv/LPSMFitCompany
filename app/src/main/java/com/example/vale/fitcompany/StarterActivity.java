package com.example.vale.fitcompany;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vale.fitcompany.DataBase.DBOperations;
import com.example.vale.fitcompany.Oggetti.MyLocation;

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

    public void StartGrafici(View v )
    {
        Intent intent = new Intent(this, GraficiPesoActivity.class);
        startActivity(intent);
    }

    public void StartGraficoAffolamento(View v)
    {
        Intent intent = new Intent(this, GraficoAffollamentoActivity.class);
        startActivity(intent);
    }

    public void StartCronometro(View v)
    {
        Intent intent = new Intent(this, CronometroActivity.class);
        startActivity(intent);
    }

    public void PulsanteInserisciAccesso(View v)
    {
        final DBOperations db = DBOperations.getInstance(getApplicationContext());
        db.open();
        final Location pale= db.PosizionePalestra();
        db.close();

        final Handler handler = new Handler();

        MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
            @Override
            public void gotLocation(Location location){

                float distanceInMeters =  pale.distanceTo(location);

                //se mi trovo a meno di 200 metri dalla posizione della  palestra  registro l'accesso, altrimenti
                //si tratta probabilmente di un valore corrotto e non viene memorizzato
                if(distanceInMeters<=200)
                {
                    db.open();
                    db.CreaAccesso();
                    db.close();

                    handler.post(new Runnable(){
                        public void run() {Toast.makeText(getApplicationContext(), "Accesso effettuato", Toast.LENGTH_LONG).show();}});
                }
                else
                {
                    handler.post(new Runnable(){
                        public void run() {Toast.makeText(getApplicationContext(), "Errore. Troppo distante dalla palestra!", Toast.LENGTH_LONG).show();}});
                }
            }
        };


        MyLocation myLocation = new MyLocation();
        boolean controlla = myLocation.getLocation(this, locationResult);

        //se la getLocation restituisce false significa che non è stato possibile rilevare la posizione, ad esempio perchè
        //l'utente ha disattivato il gps e il rilevamento attraverso la rete
        if (controlla==false)
            Toast.makeText(getApplicationContext(), "Abilita sensori posizione", Toast.LENGTH_LONG).show();
    }
}
