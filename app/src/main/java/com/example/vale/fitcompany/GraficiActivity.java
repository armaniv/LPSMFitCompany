package com.example.vale.fitcompany;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        list.add("Peso corporeo");//quindi il peso ha indice 0
        list.add("Forza muscolare");//quindi la forza ha indice 1
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
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


    private void  SettaGrafico(Integer indice)
    {
        LineChart chart = (LineChart) findViewById(R.id.chart);

        Description description = new Description();
        description.setText("");
        chart.setDescription(description);

        List<Entry> valoriGrafico = new ArrayList<Entry>();
        Entry entry;

        if (indice==0)//se dallo spinner ho selezionato peso corporeo
        {
            DBOperations db = DBOperations.getInstance(getApplicationContext());
            db.open();
            List<Peso> ListaPesi = db.RecuperaValoriPeso();
            db.close();

            float n,pesoinserito;

            //lista che conterrà unicamente le date, necessaria per poter visualizare corretamente il grafico
            List<String> date = new ArrayList<String>();

            for (int i=0;i<ListaPesi.size();i++)
            {
                n = (float)i;
                pesoinserito= Float.valueOf(ListaPesi.get(i).getPesoKg());
                date.add(ListaPesi.get(i).getData());
                entry= new Entry(n,pesoinserito);
                valoriGrafico.add(entry);
            }


            LineDataSet setComp1 = new LineDataSet(valoriGrafico, "Peso corporeo");
            setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);

            final String[] valoriDate = new String[date.size()];
            date.toArray(valoriDate);

            IAxisValueFormatter formatter = new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis)
                {
                    return valoriDate[(int) value];
                }
            };


            XAxis xAxis = chart.getXAxis();
            xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
            xAxis.setValueFormatter(formatter);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            YAxis rightYAxis = chart.getAxisRight();
            rightYAxis.setEnabled(false);


            LineData data = new LineData(setComp1);
            chart.setData(data);
            chart.invalidate();
        }


        else if(indice==1)//se dallo spinner ho selezionato forza
        {

            for (int i=0;i<3;i++)
            {
                entry= new Entry(i,(i+15));
                valoriGrafico.add(entry);
            }

            LineDataSet setComp1 = new LineDataSet(valoriGrafico, "Forza muscolare");
            setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);


            XAxis xAxis = chart.getXAxis();
            xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            YAxis rightYAxis = chart.getAxisRight();
            rightYAxis.setEnabled(false);

            LineData data = new LineData(setComp1);
            chart.setData(data);
            chart.invalidate();

        }
    }

}
