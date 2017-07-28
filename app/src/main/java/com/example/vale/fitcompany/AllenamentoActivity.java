package com.example.vale.fitcompany;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vale.fitcompany.Oggetti.Scheda;

import java.util.ArrayList;
import java.util.List;


public class AllenamentoActivity extends AppCompatActivity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allenamento);

        Intent i = getIntent();
        Scheda scheda = (Scheda) i.getSerializableExtra("SchedaSelezionata");

        TextView TestoTitolo = (TextView) findViewById(R.id.txtTitoloAllena);
        TestoTitolo.setText("Identificativo scheda: " + scheda.getId());
        TestoTitolo.setKeyListener(null);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerDay);
        ArrayAdapter<String> adapter;

        List<String> list = new ArrayList<String>();

        int NGiorni= Integer.parseInt(scheda.getNVolte());

        for (int j=1;j<=NGiorni;j++)
            list.add(String.valueOf(j));

        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }


}
