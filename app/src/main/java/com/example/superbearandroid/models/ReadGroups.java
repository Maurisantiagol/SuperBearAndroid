package com.example.superbearandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReadGroups{

    @SerializedName("id_grp")
    @Expose
    private Integer idGrp;
    @SerializedName("cod_grp")
    @Expose
    private String codGrp;
    @SerializedName("nom_grp")
    @Expose
    private String nomGrp;
    @SerializedName("nom_usu")
    @Expose
    private String nomUsu;
    @SerializedName("id_priv")
    @Expose
    private Integer idPriv;

    /**
     * No args constructor for use in serialization
     *
     */
    public ReadGroups() {
    }

    /**
     *
     * @param codGrp
     * @param nomGrp
     * @param idGrp
     * @param idPriv
     * @param nomUsu
     */
    public ReadGroups(Integer idGrp, String codGrp, String nomGrp, String nomUsu, Integer idPriv) {
        super();
        this.idGrp = idGrp;
        this.codGrp = codGrp;
        this.nomGrp = nomGrp;
        this.nomUsu = nomUsu;
        this.idPriv = idPriv;
    }

    public Integer getIdGrp() {
        return idGrp;
    }

    public void setIdGrp(Integer idGrp) {
        this.idGrp = idGrp;
    }

    public String getCodGrp() {
        return codGrp;
    }

    public void setCodGrp(String codGrp) {
        this.codGrp = codGrp;
    }

    public String getNomGrp() {
        return nomGrp;
    }

    public void setNomGrp(String nomGrp) {
        this.nomGrp = nomGrp;
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public Integer getIdPriv() {
        return idPriv;
    }

    public void setIdPriv(Integer idPriv) {
        this.idPriv = idPriv;
    }

}
