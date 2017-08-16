package com.example.vale.fitcompany;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vale.fitcompany.DataBase.DBOperations;
import com.example.vale.fitcompany.Oggetti.Trainer;

public class ContactActivity extends AppCompatActivity {

    TextView txttitle;
    Button sendMessage;
    EditText edtDomanda;
    Integer idTrainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Intent i = getIntent();
        Trainer persona = (Trainer) i.getSerializableExtra("trainer");
        idTrainer=persona.getId();

        txttitle= (TextView) findViewById(R.id.txtAsk);
        sendMessage =(Button) findViewById(R.id.btnSend);
        txttitle.setText("Fai domanda a " +persona.getNome() + " " +persona.getCognome());
        edtDomanda = (EditText) findViewById(R.id.edtContact);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String domanda= edtDomanda.getText().toString();

                DBOperations db = DBOperations.getInstance(getApplicationContext());
                db.open();
                db.InserisciDomandaDataBase(domanda, idTrainer);
                db.close();

                final ProgressDialog progressDialog = new ProgressDialog(ContactActivity.this,R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Sending...");
                progressDialog.show();


                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                progressDialog.dismiss();
                                Toast.makeText(getBaseContext(), "Message Sent!", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }, 2000);

            }
        });

    }
}
