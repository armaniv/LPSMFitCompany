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

import com.example.vale.fitcompany.R;



public class TextViewAdapter extends BaseAdapter {
    private Context context;
    private  List<String> textViewValues = new ArrayList<String>();

    public TextViewAdapter(Context context,  List<String> textViewValues)
    {
        this.context = context;
        this.textViewValues = textViewValues;
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
                EditText textView = (EditText) gridView.findViewById(R.id.grid_item_label);
                textView.setText(textViewValues.get(position));
            }
            else
            {
                gridView = inflater.inflate(R.layout.item_textview, null);

                // set value into textview
               TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
                textView.setText(textViewValues.get(position));

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
        return textViewValues.size();
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