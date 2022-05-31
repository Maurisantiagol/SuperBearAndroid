package com.example.superbearandroid.Modelo;

public class lista {
    private int IdLista;
    private int Id_eli;

    private String NombreLista;
    public lista(){

    }
    public String getNombreLista() {
        return NombreLista;
    }

    public void setNombreLista(String nombreLista) {
        NombreLista = nombreLista;
    }

    public int getIdLista() {
        return IdLista;
    }

    public void setIdLista(int idLista) {
        IdLista = idLista;
    }

    public int getId_eli() {
        return Id_eli;
    }

    public void setId_eli(int id_eli) {
        Id_eli = id_eli;
    }
}
