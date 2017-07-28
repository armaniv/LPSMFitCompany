package com.example.vale.fitcompany;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.vale.fitcompany.DataBase.DBOperations;
import com.example.vale.fitcompany.Oggetti.Scheda;

import java.util.ArrayList;
import java.util.List;


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


        List<String> items = new ArrayList<String>();
        //creo intestazione tabella
        items.add("Id scheda");
        items.add("Data inizio");
        items.add("Obbiettivo");
        items.add("Numero sessioni");

        String str;

        //popolo la lista di stringhe con tutti i campi delle varie schede. Necessario per poter popolare la GridView
        for (int i = 0; i < ListaSchede.size(); i++) {
            str = ListaSchede.get(i).getId();
            items.add(str);

            str = ListaSchede.get(i).getData_inizio();
            items.add(str);

            str = ListaSchede.get(i).getObbiettivo();
            items.add(str);

            str = ListaSchede.get(i).getNVolte();
            items.add(str);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        grid.setAdapter(adapter);


        //quando preme una cella della griglia
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v,int position, long id)
            {
                //controllo che non prema sull'intestazione e controllo se ha premuto su un id
                if (id>0 && id%4==0)
                {
                   // Toast.makeText(getApplicationContext(), ((TextView)v).getText() + " id: " + id, Toast.LENGTH_SHORT).show();

                    //recupero l'indice della scheda (contenuta nell ArrayList di Schede) e lo passo attravrso l'intent
                    int indice=  (((int) (long) id)/4)-1;
                    Scheda schedaSel  = ListaSchede.get(indice);
                    Intent intent = new Intent(SchedeActivity.this,AllenamentoActivity.class).putExtra("SchedaSelezionata",schedaSel);
                    startActivity(intent);
                }
            }
        });
    }
}