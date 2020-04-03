package com.example.gsbparam.Modele;

public class Praticien {

    private int num;
    private String nom, prenom, rue, cp, ville;

    public Praticien(int num, String prenom, String nom, String rue, String cp, String ville){
        this.num = num;
        this.prenom = prenom;
        this.nom = nom;
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String toString(){
        return  "Num = " + this.num + ", " +
                "Prénom = " + this.prenom + ", " +
                "Nom = " + this.nom + ", " +
                "Rue = " + this.rue + ", " +
                "CP = " + this.cp + ", " +
                "Ville = " + this.ville;
    }
}
