package com.example.vale.fitcompany.Oggetti;

public class Scheda
{
    private String Id;
    private String NVolte;
    private String Obbiettivo;
    private String Data_inizio;

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
