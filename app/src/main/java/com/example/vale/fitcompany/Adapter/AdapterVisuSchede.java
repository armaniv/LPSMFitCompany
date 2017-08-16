package com.example.vale.fitcompany.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;


import com.example.vale.fitcompany.Oggetti.Scheda;
import com.example.vale.fitcompany.R;

import java.util.ArrayList;
import java.util.List;


public class AdapterVisuSchede extends BaseAdapter
{
    private Context context;
    private List<Scheda> ListaValori = new ArrayList<Scheda>();//lista in input
    private  List<String> ListaStringheValori = new ArrayList<String>();

    String str;

    public AdapterVisuSchede(Context context,  List<Scheda> ListaValori)
    {
        this.context = context;
        this.ListaValori = ListaValori;

        //popolo la lista che conterrà singolarmente ogni valore delle schede, necessaria per la visualizzazioe
        //creo linea di intestazione
        ListaStringheValori.add("Id scheda");
        ListaStringheValori.add("Data inizio");
        ListaStringheValori.add("Obbiettivo");
        ListaStringheValori.add("Numero sessioni");

        for (int i = 0; i < ListaValori.size(); i++) {
            str = ListaValori.get(i).getId();
            ListaStringheValori.add(str);

            str = ListaValori.get(i).getData_inizio();
            ListaStringheValori.add(str);

            str = ListaValori.get(i).getObbiettivo();
            ListaStringheValori.add(str);

            str = ListaValori.get(i).getNVolte();
            ListaStringheValori.add(str);
        }
    }


    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null)
        {
            gridView = new View(context);

            gridView = inflater.inflate(R.layout.item_textview, null);

            // set value into textview
            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            textView.setText(ListaStringheValori.get(position));

        }
        else
        {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return ListaStringheValori.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
