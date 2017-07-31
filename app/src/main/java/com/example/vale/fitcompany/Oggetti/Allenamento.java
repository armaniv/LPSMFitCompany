package com.example.vale.fitcompany.Oggetti;

/**
 * Created by Vale on 31/07/2017.
 */

public class Allenamento
{
    String IdEs,NomeEs,Set,Peso,Note;

    public String getIdEs() {
        return IdEs;
    }

    public String getNomeEs() {
        return NomeEs;
    }

    public String getSet() {
        return Set;
    }

    public String getPeso() {
        return Peso;
    }

    public String getNote() {
        return Note;
    }

    public Allenamento()
    {}

    public  Allenamento(String IdEs, String NomeEs, String Set, String Peso, String Note)
    {
        this.IdEs=IdEs;
        this.NomeEs=NomeEs;
        this.Set=Set;
        this.Peso=Peso;
        this.Note=Note;
    }
}
