package com.example.superbearandroid.Modelo;

public class grupo {
    private Integer id_grp;
    private String cod_grp;
    private String nom_grp;
    private String nom_usu;
    private Integer id_priv;
public grupo(){

}
    public Integer getId_grp() {
        return id_grp;
    }

    public void setId_grp(Integer id_grp) {
        this.id_grp = id_grp;
    }

    public String getCod_grp() {
        return cod_grp;
    }

    public void setCod_grp(String cod_grp) {
        this.cod_grp = cod_grp;
    }

    public String getNom_grp() {
        return nom_grp;
    }

    public void setNom_grp(String nom_grp) {
        this.nom_grp = nom_grp;
    }

    public String getNom_usu() {
        return nom_usu;
    }

    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }

    public Integer getId_priv() {
        return id_priv;
    }

    public void setId_priv(Integer id_priv) {
        this.id_priv = id_priv;
    }
}
