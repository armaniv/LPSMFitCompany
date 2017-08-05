package com.example.vale.fitcompany.Oggetti;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jacek on 05/08/2017.
 */

public class Chiusura implements Serializable {
    Date inizio;
    Date fine;

    public Chiusura(Date inizio, Date fine) {
        this.inizio = inizio;
        this.fine = fine;
    }

    public Chiusura() {
    }

    public Date getInizio() {
        return inizio;
    }

    public Date getFine() {
        return fine;
    }
}
