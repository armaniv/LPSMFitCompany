package com.example.vale.fitcompany.Oggetti;

import java.io.Serializable;


public class Trainer implements Serializable {

    public int getId() {
        return id;
    }

    int id;

    public String getNome() {
        return nome;
    }

    String nome;

    public String getCognome() {
        return cognome;
    }

    String cognome;

    public String getSpecialita() {
        return specialita;
    }

    String specialita;
    public Trainer()
    {}
    public Trainer (int id, String nome, String cognome, String specialita)
    {
        this.id=id;
        this.nome=nome;
        this.cognome=cognome;
        this.specialita=specialita;
    }

}
