package com.example.vale.fitcompany.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import com.example.vale.fitcompany.Oggetti.Allenamento;
import com.example.vale.fitcompany.R;



public class AdapterVisuAllenamenti extends BaseAdapter
{
    //lista che conterra le edittext della griglia, necessaria per salvare i nuovi dati nel db
    static List<EditText> listaEdit = new ArrayList<EditText>();

    private Context context;
    private  List<Allenamento> ListaValori = new ArrayList<Allenamento>();
    private  List<String> ListaStringheValori = new ArrayList<String>();

    public AdapterVisuAllenamenti(Context context, List<Allenamento> ListaValori)
    {
        this.context = context;
        this.ListaValori = ListaValori;

        //popolo la lista che conterrà singolarmente ogni valore della scheda, necessaria per la visualizzazioe
        //creo linea di intestazione
        ListaStringheValori.add("Es");
        ListaStringheValori.add("Set");
        ListaStringheValori.add("Kg");
        ListaStringheValori.add("Note");

        //estraggo valori dalla lista di schede
        String str;
        for (int i = 0; i < ListaValori.size(); i++) {
            str = ListaValori.get(i).getNomeEs();
            ListaStringheValori.add(str);

            str = ListaValori.get(i).getSet();
            ListaStringheValori.add(str);

            str = ListaValori.get(i).getPeso();
            ListaStringheValori.add(str);

            str = ListaValori.get(i).getNote();
            ListaStringheValori.add(str);
        }

        //pulisco la lista , necessario altrimenti al cambio di valori per via dello spinne la lista conterrebbe anche i valori vecchi
        listaEdit.clear();

    }


    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null)
        {
            gridView = new View(context);

            if (position>3 && ((((position+2)%4)==0) || (((position+1)%4)==0)))
            {
                // get layout from item_edittext,
                gridView = inflater.inflate(R.layout.item_edittext, null);

                // set value into editext
                EditText editText = (EditText) gridView.findViewById(R.id.grid_item_label1);
                editText.setText(ListaStringheValori.get(position));
                listaEdit.add(editText);//inserisco la editext nella lista
            }
            else
            {
                gridView = inflater.inflate(R.layout.item_textview, null);

                // set value into textview
                TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
                textView.setText(ListaStringheValori.get(position));
            }
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

    //ritorna sotto forma di lista di stringhe il contenuto delle editext
    public List<String> RitornaEditTextList()
    {
        List<String> risultato = new ArrayList<String>();

        for(int i=0;i<listaEdit.size();i++)
            risultato.add(listaEdit.get(i).getText().toString());

        return risultato;
    }

}