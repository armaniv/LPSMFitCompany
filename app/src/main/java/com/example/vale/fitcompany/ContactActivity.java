package com.example.vale.fitcompany;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.vale.fitcompany.Oggetti.Trainer;

public class ContactActivity extends AppCompatActivity {

    TextView txttitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Intent i = getIntent();
        Trainer persona = (Trainer) i.getSerializableExtra("trainer");
        txttitle= (TextView) findViewById(R.id.txtAsk);
        txttitle.setText("Fai domanda a " +persona.getNome() + " " +persona.getCognome());

    }
}
