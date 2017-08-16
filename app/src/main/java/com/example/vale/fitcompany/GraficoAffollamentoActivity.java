package com.example.vale.fitcompany;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.vale.fitcompany.DataBase.DBOperations;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;


public class GraficoAffollamentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grafico_affollamento);

        PieChart pieChart = (PieChart) findViewById(R.id.idPieChart);
        TextView npers = (TextView) findViewById(R.id.txtNPersoneGrafici);

        DBOperations db = DBOperations.getInstance(getApplicationContext());
        db.open();
        int npersone = db.NumeroUtentiAttuali();
        Float[] valoriGrafico = db.RitornaGruppiMuscolariAllenatiAdesso();
        db.close();

        npers.setText("Persone in palestra: " + npersone);

        //set colore cerchio centrale grafico
        pieChart.setHoleColor(Color.rgb(228, 63, 63));//rosso
        pieChart.setTransparentCircleAlpha(0);

        //imposto descrizione nulla
        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);

        List<PieEntry> entries = new ArrayList<>();

        //setto i valori del grafico con i valori provenienti dal DB
        if (valoriGrafico[0]!=0)
            entries.add(new PieEntry(valoriGrafico[0], "Petto"));

        if (valoriGrafico[1]!=0)
            entries.add(new PieEntry(valoriGrafico[1], "Schiena"));

        if (valoriGrafico[2]!=0)
            entries.add(new PieEntry(valoriGrafico[2], "Gambe"));

        if (valoriGrafico[3]!=0)
        entries.add(new PieEntry(valoriGrafico[3], "Braccia"));

        if (valoriGrafico[4]!=0)
        entries.add(new PieEntry(valoriGrafico[4], "Spalle"));

        PieDataSet set = new PieDataSet(entries, "");
        PieData data = new PieData(set);


        //alcuni parametri per un visualizzazione carina
        set.setSliceSpace(7);
        set.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(159, 93, 181));//viola
        colors.add(Color.rgb(241, 196, 15));//giallo
        colors.add(Color.rgb(46, 204, 113));//verde
        colors.add(Color.rgb(52, 152, 219));//blu
        colors.add(Color.rgb(186, 154, 116));//marrone

        set.setColors(colors);

        pieChart.setData(data);
        pieChart.invalidate(); // refresh

    }
}