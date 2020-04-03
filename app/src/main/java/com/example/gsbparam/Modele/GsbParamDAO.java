package com.example.gsbparam.Modele;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GsbParamDAO {

    private static String base = "BDD_GSBParam.db";
    private static int version = 1;
    private BdSQLiteOpenHelper accesBD;

    public GsbParamDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    /*--------------------------------   Début Medicament   --------------------------------*/

    public long addMedicament(Medicament unMedicament){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("med_depotLegal", unMedicament.getDepotLegal());
        value.put("med_nomCommercial", unMedicament.getNomCommercial());
        value.put("med_prixEchantillon", unMedicament.getPrixEchantillon());
        value.put("fam_code", unMedicament.getCodeFamille());
        ret = bd.insert("Medicament", null, value);

        return ret;
    }


    public Medicament getMedicament(String depotLegal){
        Medicament leMedicament = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("SELECT med_depotLegal, med_nomCommercial, med_prixEchantillon, fam_code FROM Medicament WHERE med_depotLegal="+depotLegal+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            leMedicament = new Medicament(curseur.getString(0),curseur.getString(1),curseur.getFloat(2),curseur.getString(3));
        }
        return leMedicament;
    }

    public ArrayList<Medicament> getMedicaments(String depotLegal){
        Cursor curseur;
        String req = "SELECT med_depotLegal, med_nomCommercial, med_prixEchantillon, fam_code FROM Medicament WHERE med_depotLegal LIKE '"+depotLegal+"';";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToMedicamentArrayList(curseur);
    }


    private ArrayList<Medicament> cursorToMedicamentArrayList(Cursor curseur) {
        ArrayList<Medicament> listeMedicaments = new ArrayList<Medicament>();
        String depotLegal;
        String nomCommercial;
        float prixEchantillon;
        String codeFamille;


        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            depotLegal = curseur.getString(0);
            nomCommercial = curseur.getString(1);
            prixEchantillon = curseur.getFloat(2);
            codeFamille = curseur.getString(3);
            listeMedicaments.add(new Medicament(depotLegal, nomCommercial, prixEchantillon, codeFamille));
            curseur.moveToNext();
        }

        return listeMedicaments;
    }
    /*---------------------------------   Fin Medicament   ---------------------------------*/



    /*---------------------------------   Début Praticien   --------------------------------*/
    public long addPraticien(Praticien unPraticien){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("pra_num", unPraticien.getNum());
        value.put("pra_prenom", unPraticien.getPrenom());
        value.put("pra_nom", unPraticien.getNom());
        value.put("pra_rue", unPraticien.getRue());
        value.put("pra_cp", unPraticien.getCp());
        value.put("pra_ville", unPraticien.getVille());
        ret = bd.insert("Praticien", null, value);

        return ret;
    }


    public Praticien getPraticien(String idPraticien){
        Praticien lePraticien = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("SELECT pra_num, pra_prenom, pra_nom, pra_rue, pra_cp, pra_ville FROM Praticien WHERE pra_num="+idPraticien+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            lePraticien = new Praticien(
                    curseur.getInt(0),
                    curseur.getString(1),
                    curseur.getString(2),
                    curseur.getString(3),
                    curseur.getString(4),
                    curseur.getString(5)
            );
        }
        return lePraticien;
    }

    public ArrayList<Praticien> getPraticiens(String recherche){
        Cursor curseur;
        String conditionRequete = "";
        if(recherche.length()>0) {
            String[] laRequeteEclatee = recherche.split(" ");
            int i = 0;
            for (String uneChaine : laRequeteEclatee) {

                if (i == 0) {
                    conditionRequete = "WHERE ";
                }

                conditionRequete += "pra_num LIKE '%" + uneChaine + "%' OR " +
                                    "pra_prenom LIKE '%" + uneChaine + "%' OR " +
                                    "pra_rue LIKE '%" + uneChaine + "%' OR " +
                                    "pra_cp LIKE '%" + uneChaine + "%' OR " +
                                    "pra_ville LIKE '%" + uneChaine + "%'";


                if (i < laRequeteEclatee.length) {
                    conditionRequete += " OR ";
                }
                i++;
            }
        }

        String req = "SELECT pra_num, pra_prenom, pra_nom, pra_rue, pra_cp, pra_ville FROM Praticien "+conditionRequete+" ;";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToPraticienArrayList(curseur);
    }


    private ArrayList<Praticien> cursorToPraticienArrayList(Cursor curseur) {
        ArrayList<Praticien> listePraticiens = new ArrayList<Praticien>();
        String depotLegal;
        String nomCommercial;
        float prixEchantillon;
        String codeFamille;


        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            depotLegal = curseur.getString(0);
            nomCommercial = curseur.getString(1);
            prixEchantillon = curseur.getFloat(2);
            codeFamille = curseur.getString(3);
            listePraticiens.add(
                    new Praticien(
                            curseur.getInt(0),
                            curseur.getString(1),
                            curseur.getString(2),
                            curseur.getString(3),
                            curseur.getString(4),
                            curseur.getString(5)
                    )
            );
            curseur.moveToNext();
        }

        return listePraticiens;
    }
    /*---------------------------------   Fin Praticien   ----------------------------------*/

}