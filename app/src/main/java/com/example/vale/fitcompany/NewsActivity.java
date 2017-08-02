package com.example.vale.fitcompany;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.vale.fitcompany.DataBase.DBOperations;
import com.example.vale.fitcompany.Oggetti.News;
import com.example.vale.fitcompany.Oggetti.Trainer;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    DBOperations db;
    private List<News> notizie;
    ArrayList<String> tmp ;

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
        final ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tmp);
        mylist.setAdapter(adapter);




    }
    private void formatting()
    {
        tmp=new ArrayList<String>();
        for (int i=0;i<notizie.size();i++){
            tmp.add(notizie.get(i).getDescrizone() +"\n" +notizie.get(i).getData());

        }
    }


}
