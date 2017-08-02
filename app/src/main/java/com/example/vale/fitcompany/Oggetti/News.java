package com.example.vale.fitcompany.Oggetti;

import java.io.Serializable;

/**
 * Created by Jacek on 02/08/2017.
 */

public class News implements Serializable {
    private String Palestra,Descrizone,Data;

    public News(String palestra, String descrizone, String data) {
        Palestra = palestra;
        Descrizone = descrizone;
        Data = data;
    }

    public News() {
    }

    public String getPalestra() {
        return Palestra;
    }

    public String getDescrizone() {
        return Descrizone;
    }

    public String getData() {
        return Data;
    }

}
