package com.example.vale.fitcompany.Adapter;

import android.content.Context;
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
    private Context context;
    private  List<Allenamento> ListaValori = new ArrayList<Allenamento>();
    private  List<String> ListaStringheValori = new ArrayList<String>();

    public AdapterVisuAllenamenti(Context context, List<Allenamento> ListaValori)
    {
        this.context = context;
        this.ListaValori = ListaValori;

        ListaStringheValori.add("Es");
        ListaStringheValori.add("Set");
        ListaStringheValori.add("Kg");
        ListaStringheValori.add("Note");

        String str;
        for (int i = 0; i < ListaValori.size(); i++)
        {
            str = ListaValori.get(i).getNomeEs();
            ListaStringheValori.add(str);

            str = ListaValori.get(i).getSet();
            ListaStringheValori.add(str);

            str = ListaValori.get(i).getPeso();
            ListaStringheValori.add(str);

            str = ListaValori.get(i).getNote();
            ListaStringheValori.add(str);
        }
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        int indice=0;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null)
        {
            gridView = new View(context);

            if (position>3 && ((((position+2)%4)==0) || (((position+1)%4)==0)))
            {
                // get layout from mobile.xml
                gridView = inflater.inflate(R.layout.item_edittext, null);

                // set value into textview
                EditText editText = (EditText) gridView.findViewById(R.id.grid_item_label);
                editText.setText(ListaStringheValori.get(position));
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

}