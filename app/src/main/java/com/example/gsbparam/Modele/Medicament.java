package com.example.gsbparam.Modele;

public class Medicament {

    private String depotLegal;
    private String nomCommercial;
    private float prixEchantillon;
    private String codeFamille;

    public Medicament(String unDepotLegal, String unNomCommercial, float unPrixEchantillon, String unCodeFamille) {
        this.depotLegal = unDepotLegal;
        this.nomCommercial = unNomCommercial;
        this.prixEchantillon = unPrixEchantillon;
        this.codeFamille = unCodeFamille;
    }


    public String getDepotLegal() {
        return depotLegal;
    }

    public void setDepotLegal(String unDepotLegal) {
        this.depotLegal = unDepotLegal;
    }

    public String getNomCommercial() {
        return nomCommercial;
    }

    public void setNomCommercial(String unNomCommercial) {
        this.nomCommercial = unNomCommercial;
    }

    public float getPrixEchantillon() {
        return prixEchantillon;
    }

    public void setPrixEchantillon(float unPrixEchantillon) {
        this.prixEchantillon = unPrixEchantillon;
    }

    public String getCodeFamille() {
        return codeFamille;
    }

    public void setCodeFamille(String unCodeFamille) {
        this.codeFamille = unCodeFamille;
    }

    public String toString(){
        return  "Dépot légal =" + this.depotLegal + ", " +
                "Nom commercial =" + this.nomCommercial + ", "+
                "Prix d'un échantillon = " + this.prixEchantillon+"€"+ ", "+
                "Code de la famille = " + this.codeFamille;
    }
}