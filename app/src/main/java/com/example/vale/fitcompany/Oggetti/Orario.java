package com.example.vale.fitcompany.Oggetti;

import java.io.Serializable;


public class Orario implements Serializable {
    private String Giorni;
    private String Orario;

    public Orario(String giorni, String orario) {
        Giorni = giorni;
        Orario = orario;
    }

    public Orario() {
    }

    public String getGiorni() {
        return Giorni;
    }

    public String getOrario() {
        return Orario;
    }
}
