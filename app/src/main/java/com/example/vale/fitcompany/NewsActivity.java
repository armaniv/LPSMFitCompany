package com.example.vale.fitcompany;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vale.fitcompany.DataBase.DBOperations;
import com.example.vale.fitcompany.Oggetti.News;
import com.example.vale.fitcompany.Oggetti.Trainer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    DBOperations db;
    private List<News> notizie;
    List<String> tmpTitle;
    List<String> tmpBody;
    List<String> tmpDate;
    LinkedList<News> format;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        db = DBOperations.getInstance(getApplicationContext());
        db.open();
        notizie = db.GetNews();
        db.close();

        formatting();
        final ListView mylist = (ListView) findViewById(R.id.listView1);
        final MyArrayAdapter adapter = new MyArrayAdapter(this,notizie);
        mylist.setAdapter(adapter);

    }

    private void formatting()
    {
        tmpTitle=new ArrayList<String>();
        tmpBody=new ArrayList<String>();
        tmpDate=new ArrayList<String>();

        for (int i=0;i<notizie.size();i++){
            String s=notizie.get(i).getDescrizone().substring(0,10) + "...";
            tmpTitle.add(s);
            s= "..."+ notizie.get(i).getDescrizone().substring(10);
            tmpBody.add(s);
            tmpDate.add(notizie.get(i).getData());

        }
    }


    public class MyArrayAdapter extends ArrayAdapter<News> {
        private final Context context;


        public MyArrayAdapter(Context context,
                              List<News> objects) {
            super(context, R.layout.listrow_layout,objects);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listrow_layout, null);
            formatting();
            TextView title = (TextView) convertView.findViewById(R.id.RowTitle);
            TextView body = (TextView) convertView.findViewById(R.id.RowDesc);
            TextView date = (TextView) convertView.findViewById(R.id.RowDate);
            title.setText(tmpTitle.get(position));
            body.setText(tmpBody.get(position));
            date.setText(tmpDate.get(position));
            return convertView;
        }
    }

}

