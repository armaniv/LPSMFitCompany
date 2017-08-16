package com.example.vale.fitcompany;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.example.vale.fitcompany.DataBase.DBOperations;
import com.example.vale.fitcompany.Oggetti.Scheda;
import com.example.vale.fitcompany.Oggetti.Allenamento;

import java.util.ArrayList;
import java.util.List;

import com.example.vale.fitcompany.Adapter.AdapterVisuAllenamenti;


public class AllenamentoActivity extends AppCompatActivity
{
    int GIORNOALLENAMENTOSCELTO;
    String SCHEDAID;
    AdapterVisuAllenamenti adapterGrid;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allenamento);

        Intent i = getIntent();
        Scheda scheda = (Scheda) i.getSerializableExtra("SchedaSelezionata");

        SCHEDAID= scheda.getId();


        //setto titolo
        TextView TestoTitolo = (TextView) findViewById(R.id.txtTitoloAllena);
        TestoTitolo.setText("Identificativo scheda: " + SCHEDAID);
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

        SettaTabella(GiornoPrevisto,SCHEDAID);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                int gg= ((int) (long) id)+1;
                SettaTabella(gg,SCHEDAID);
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
        GIORNOALLENAMENTOSCELTO=Giorno;

        int schedaid=Integer.parseInt(idscheda);

        DBOperations db = DBOperations.getInstance(getApplicationContext());
        db.open();

        List<Allenamento> items = new ArrayList<Allenamento>();

        items= db.RecuperaGiornoAllenamento(schedaid,Giorno );
        db.close();

        GridView grid = (GridView) findViewById(R.id.grwAllenamento);

        adapterGrid= new AdapterVisuAllenamenti(this, items);
        grid.setAdapter(adapterGrid);
    }


    public void SalvaScheda(View v )
    {
        List<String> CampiEdtTxt =  adapterGrid.RitornaEditTextList();

        DBOperations db = DBOperations.getInstance(getApplicationContext());
        db.open();
        boolean check= db.SalvaGiornoAllenamento(SCHEDAID,GIORNOALLENAMENTOSCELTO,CampiEdtTxt);
        db.close();

        if (check==true)
            Toast.makeText(getApplicationContext(), "Modifiche salvate", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Modifiche NON salvate", Toast.LENGTH_SHORT).show();
    }
}


