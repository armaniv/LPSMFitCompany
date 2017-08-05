package com.example.vale.fitcompany.Oggetti;


public class Utente
{
    String Nome,Cognome,InizioAbb,FineAbb;

    public String getNome() {
        return Nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public String getInizioAbb() {
        return InizioAbb;
    }

    public String getFineAbb() {
        return FineAbb;
    }

    public Utente(){}

    public Utente(String Nome,String Cognome, String InizioAbb, String FineAbb)
    {
        this.Nome=Nome;
        this.Cognome=Cognome;
        this.InizioAbb=InizioAbb;
        this.FineAbb=FineAbb;
    }
}
