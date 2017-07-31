package com.example.vale.fitcompany;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;

import com.example.vale.fitcompany.DataBase.DBOperations;
import com.example.vale.fitcompany.Oggetti.Scheda;

import java.util.ArrayList;
import java.util.List;

import com.example.vale.fitcompany.Adapter.TextViewAdapter;


public class AllenamentoActivity extends AppCompatActivity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allenamento);

        Intent i = getIntent();
        Scheda scheda = (Scheda) i.getSerializableExtra("SchedaSelezionata");
        final String schedaid= scheda.getId();


        //setto titolo
        TextView TestoTitolo = (TextView) findViewById(R.id.txtTitoloAllena);
        TestoTitolo.setText("Identificativo scheda: " + schedaid);
        TestoTitolo.setKeyListener(null);

        //setto elementi spinner (numero compoenti e numero selezionato)
        Spinner spinner = (Spinner) findViewById(R.id.spinnerDay);
        ArrayAdapter<String> adapter;
        List<String> list = new ArrayList<String>();
        int NGiorni= Integer.parseInt(scheda.getNVolte());
        for (int j=1;j<=NGiorni;j++)
            list.add(String.valueOf(j));
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        DBOperations db = DBOperations.getInstance(getApplicationContext());
        db.open();

        int GiornoPrevisto= db.GiornoCorrente();

        spinner.setSelection((GiornoPrevisto-1));

        SettaTabella(GiornoPrevisto,schedaid);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                int gg= ((int) (long) id)+1;
                SettaTabella(gg,schedaid);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
                return;
            }

        });

    }

    public void SettaTabella(int Giorno, String idscheda)
    {
        int schedaid=Integer.parseInt(idscheda);

        DBOperations db = DBOperations.getInstance(getApplicationContext());
        db.open();

        List<String> items = new ArrayList<String>();

        items= db.RecuperaGiornoAllenamento(schedaid,Giorno );
        db.close();


        GridView grid = (GridView) findViewById(R.id.grwAllenamento);
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);

        grid.setAdapter(new TextViewAdapter(this, items));

        /*
        adapter.notifyDataSetChanged();
        grid.invalidateViews();
        grid.setAdapter(adapter);*/
    }
}


