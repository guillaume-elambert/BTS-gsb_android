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


    public long addPraticien(Praticien unPraticien){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("med_depotLegal", unPraticien.getDepotLegal());
        value.put("med_nomCommercial", unPraticien.getNomCommercial());
        value.put("med_prixEchantillon", unPraticien.getPrixEchantillon());
        value.put("fam_code", unPraticien.getCodeFamille());
        ret = bd.insert("Medicament", null, value);

        return ret;
    }


    public Medicament getPraticien(String idPraticien){
        Medicament leMedicament = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("SELECT med_depotLegal, med_nomCommercial, med_prixEchantillon, fam_code FROM Medicament WHERE med_depotLegal="+depotLegal+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            leMedicament = new Medicament(curseur.getString(0),curseur.getString(1),curseur.getFloat(2),curseur.getString(3));
        }
        return leMedicament;
    }

    public ArrayList<Medicament> getPraticiens(String idPraticien){
        Cursor curseur;
        String req = "SELECT med_depotLegal, med_nomCommercial, med_prixEchantillon, fam_code FROM Medicament WHERE med_depotLegal LIKE '"+depotLegal+"';";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToPraticienArrayList(curseur);
    }


    private ArrayList<Medicament> cursorToPraticienArrayList(Cursor curseur) {
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
            listeMedicaments.add(new Medicament(depotLegal, nomCommercial, prixEchantillon, codeFamille));
            curseur.moveToNext();
        }

        return listeMedicaments;
    }

}