package com.example.vale.fitcompany.Oggetti;


public class Peso
{
    String pesoKg,data;

    public String getPesoKg() {
        return pesoKg;
    }

    //restituisce solo la data in formato AA-MM-DD di memorizzazione del peso (vengono omesse:ore,minuti e secondo)
    public String getData()
    {
        String substr=data.substring(2,10);
        return substr;
    }

    public Peso()
    {}

    public Peso(String data,String pesoKg)
    {
        this.pesoKg=pesoKg;
        this.data=data;
    }
}
