package com.example.vale.fitcompany;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.vale.fitcompany.DataBase.DBOperations;
import com.example.vale.fitcompany.Oggetti.Peso;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;


public class GraficiActivity  extends AppCompatActivity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafici);

        Spinner spinner = (Spinner) findViewById(R.id.spinGrafici);
        ArrayAdapter<String> adapter;
        List<String> list = new ArrayList<String>();
        list.add("Ultimo mese");//quindi indice 0
        list.add("Ultimo trimestre");//quindi indice 1
        list.add("Ultimo semestre");//quindi indice 2
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int pos, long id)
            {
                int indice= ((int) (long) id);
                SettaGrafico(indice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
                return;
            }

        });

    }


    private void  SettaGrafico(Integer periodo)
    {
        LineChart chart = (LineChart) findViewById(R.id.chart);

        Description description = new Description();
        description.setText("");
        chart.setDescription(description);

        List<Entry> valoriGrafico = new ArrayList<Entry>();
        Entry entry;

        List<Peso> ListaPesi = new ArrayList<Peso>();

        DBOperations db = DBOperations.getInstance(getApplicationContext());
        db.open();
        ListaPesi = db.RecuperaValoriPeso(periodo);
        db.close();

        //se non ho dati a sufficienza per fare il grafico
        if (ListaPesi.size() == 0 || ListaPesi.size() == 1)
            return;         //esco ,altrimenti errore

        float n, pesoinserito;

        //lista che conterrà unicamente le date, necessaria per poter visualizare corretamente il grafico
        List<String> date = new ArrayList<String>();
        date.clear();

        for (int i = 0; i < ListaPesi.size(); i++) {
            n = (float) i;
            pesoinserito = Float.valueOf(ListaPesi.get(i).getPesoKg());
            date.add(ListaPesi.get(i).getData());
            entry = new Entry(n, pesoinserito);
            valoriGrafico.add(entry);
        }


        LineDataSet setComp1 = new LineDataSet(valoriGrafico, "Peso corporeo (kg)");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);

        //conterrà i valori delle date di inserimento pesi
        final String[] valoriDate = new String[date.size()];
        date.toArray(valoriDate);

        //setto sull'asse dell x le date di inserimento del peso
        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (valoriDate.length > (int) value) {
                    return valoriDate[(int) value];
                } else
                    return null;
            }
        };

        //parametri grafico
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis rightYAxis = chart.getAxisRight();
        rightYAxis.setEnabled(false);

        LineData lineaDati = new LineData(setComp1);

        lineaDati.notifyDataChanged();
        chart.setData(lineaDati);
        chart.notifyDataSetChanged();
        chart.invalidate();

    }

}
