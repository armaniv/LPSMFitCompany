package com.example.vale.fitcompany;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;


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
        ArrayList<Scheda> ListaSchede = db.RecuperaTutteSchedeUtenteConInfo();
        db.close();

        String str = null;

        ArrayList<String> items = new ArrayList <String> ();

        for (int i = 0; i < ListaSchede.size(); i++)
        {
            str = ListaSchede.get(i).toString();
            items.add(str);
        }

        grid.setAdapter(new ArrayAdapter<String>(this,R.layout.cell,items));
    }
}