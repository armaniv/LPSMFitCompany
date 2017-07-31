package com.example.vale.fitcompany;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import com.example.vale.fitcompany.Adapter.AdapterVisuSchede;
import com.example.vale.fitcompany.DataBase.DBOperations;
import com.example.vale.fitcompany.Oggetti.Scheda;

import java.util.ArrayList;


public class SchedeActivity  extends AppCompatActivity
{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schede);


        GridView grid = (GridView) findViewById(R.id.GrwElencoSche);

        DBOperations db = DBOperations.getInstance(getApplicationContext());
        db.open();
        final ArrayList<Scheda> ListaSchede = db.RecuperaTutteSchedeUtenteConInfo();
        db.close();

        grid.setAdapter(new AdapterVisuSchede(this, ListaSchede));

        //quando preme una cella della griglia
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v,int position, long id)
            {
                Log.d("Borsa", "id " + position);
                //controllo che non prema sull'intestazione e controllo se ha premuto su un id
                if (position>0 && position%4==0)
                {
                   // Toast.makeText(getApplicationContext(), ((TextView)v).getText() + " id: " + id, Toast.LENGTH_SHORT).show();

                    //recupero l'indice della scheda (contenuta nell ArrayList di Schede) e lo passo attravrso l'intent
                    int indice=  (position/4)-1;
                    Scheda schedaSel  = ListaSchede.get(indice);
                    Intent intent = new Intent(SchedeActivity.this,AllenamentoActivity.class).putExtra("SchedaSelezionata",schedaSel);
                    startActivity(intent);
                }
            }
        });
    }
}