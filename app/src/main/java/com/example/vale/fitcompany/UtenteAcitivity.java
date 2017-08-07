package com.example.vale.fitcompany;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vale.fitcompany.DataBase.DBOperations;
import com.example.vale.fitcompany.Oggetti.Utente;


public class UtenteAcitivity extends AppCompatActivity
{

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_utente);

        DBOperations db = DBOperations.getInstance(getApplicationContext());
        db.open();
        Utente utente= db.RitornaUtenteAttuale();
        String pesocorporeo= db.GetUltimoPesoPersona();
        db.close();

        TextView nome = (TextView) findViewById(R.id.txtUtenteNome);
        nome.setText(utente.getNome());

        TextView cognome = (TextView) findViewById(R.id.txtUtenteCognome);
        cognome.setText(utente.getCognome());

        TextView iabb = (TextView) findViewById(R.id.txtUtenteInizioAbb);
        iabb.setText(utente.getInizioAbb());

        TextView fabb = (TextView) findViewById(R.id.txtUtenteFineAbb);
        fabb.setText(utente.getFineAbb());

        EditText peso = (EditText) findViewById(R.id.edtPeso);
        peso.setText(pesocorporeo);

    }

    public void SalvaPeso(View v )
    {
        String newPeso="";
        EditText peso = (EditText) findViewById(R.id.edtPeso);
        newPeso= peso.getText().toString();

        DBOperations db = DBOperations.getInstance(getApplicationContext());
        db.open();
        boolean check= db.InserisciNuovoPeso(newPeso);
        db.close();

        if (check==true)
            Toast.makeText(getApplicationContext(), "Nuovo peso inserito", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Errore inserimento peso", Toast.LENGTH_SHORT).show();
    }
}
