package com.example.vale.fitcompany.Oggetti;
import java.io.Serializable;


@SuppressWarnings("serial")
public class Scheda implements Serializable
{
    private String Id;  //id della scheda
    private String NVolte;
    private String Obbiettivo;
    private String Data_inizio;

    public String getId() {
        return Id;
    }

    public String getNVolte() {
        return NVolte;
    }

    public String getObbiettivo() {
        return Obbiettivo;
    }

    public String getData_inizio() {
        return Data_inizio;
    }

    public Scheda()
    {

    }

    public Scheda(String Id, String NVolte, String Obbiettivo, String Data_inizio)
    {
        this.Id=Id;
        this.NVolte=NVolte;
        this.Obbiettivo=Obbiettivo;
        this.Data_inizio=Data_inizio;
    }

    @Override
    public String toString()
    {
        return ""+ Id+ ", "+ Data_inizio+ ", "+ Obbiettivo+ ", "+ NVolte;
    }
}
