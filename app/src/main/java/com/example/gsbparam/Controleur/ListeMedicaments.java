package com.example.gsbparam.Controleur;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gsbparam.Modele.Medicament;
import com.example.gsbparam.Modele.GsbParamDAO;
import com.example.gsbparam.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ListeMedicaments extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_liste_medicaments, container, false);
        //ListView listViewMedicaments = (ListView) root.findViewById(R.id.lv_listeMedicaments);
        Spinner listeDeroulanteMed = (Spinner) root.findViewById(R.id.listeDeroulanteMed);
        TextView textePageMed = (TextView) root.findViewById(R.id.titre_listeDeroulante);

        // récupération des données
        ((TextView) root.findViewById(R.id.titre_listeDeroulante)).setText(getString(R.string.texte_listeMed));
        GsbParamDAO medicamentAcces = new GsbParamDAO(this.getContext());
        ArrayList<Medicament> listeMedicaments = medicamentAcces.getMedicaments("%");

        if(listeMedicaments.size() > 0) {
            //textePageMed.setVisibility(View.INVISIBLE);

            //Création de la ArrayList qui nous permettra de remplir la listView
            ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
            //On déclare la HashMap qui contiendra les informations pour un item
            HashMap<String, String> map;

            //essai foreach
            for (Medicament unMedicament : listeMedicaments) {
                map = new HashMap<String, String>();

                map.put("med_depotLegal", unMedicament.getDepotLegal());
                map.put("med_nomCommercial", unMedicament.getNomCommercial());
                map.put("med_prixEchantillon", Float.toString(unMedicament.getPrixEchantillon()));
                map.put("fam_code", unMedicament.getCodeFamille());
                map.put("med_nomcomplet", unMedicament.getNomCommercial()+" ("+unMedicament.getDepotLegal()+")");
                listItem.add(map);
            }

            /*ArrayList<HashMap<String, String>> item = new ArrayList<HashMap<String, String>>();
            item.add(listItem.get(listeDeroulanteMed.getSelectedItemPosition()));
            //Création d'un SimpleAdapter qui se chargera de mettre les items présents dans notre list (list_entree) dans la vue listeArticles
            SimpleAdapter mSchedule = new SimpleAdapter(this.getContext(), item, R.layout.informations_medicament,
                    new String[]{"med_depotLegal", "med_nomCommercial", "med_prixEchantillon", "fam_code"}, new int[]{R.id.depotLegal, R.id.nomCommercial, R.id.prixEchantillon, R.id.famCode});

            //On attribue à notre listView l'adapter que l'on vient de créer
            listViewMedicaments.setAdapter(mSchedule);
             */
            /*setInfosMed(root, listItem.get(listeDeroulanteMed.getSelectedItemPosition()));*/

            SimpleAdapter mSchedule = new SimpleAdapter(this.getContext(), listItem, R.layout.simple_spinner_item,
                    new String[]{"med_nomcomplet","med_depotLegal"}, new int[]{R.id.texteNomCommercial,R.id.texteDepotLegal});
            mSchedule.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            listeDeroulanteMed.setAdapter(mSchedule);

            listeDeroulanteMed.getSelectedItem();
            listeDeroulanteMed.getSelectedItemPosition();


            listeDeroulanteMed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                     if(selectedItemView != null) {
                         String depotLegal = ((TextView) selectedItemView.findViewById(R.id.texteDepotLegal)).getText().toString();
                         setInfosMed(depotLegal);
                         Toast.makeText(parentView.getContext(),
                                 ((TextView) selectedItemView.findViewById(R.id.texteDepotLegal)).getText(), Toast.LENGTH_LONG).show();
                     }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            //Toast.makeText(this.getContext(),listeDeroulanteMed.getSelectedItemPosition(), Toast.LENGTH_LONG).show();
        } else {
            //listViewMedicaments.setVisibility(View.INVISIBLE);
            //textePageMed.setText(textePageMed.getText()+"\nMalheureusement il n'y en a aucun pour le moment");
        }

        return root;
    }

    public void setInfosMed(String depotLegal){
        View vue = this.getView();
        GsbParamDAO medicamentAcces = new GsbParamDAO(this.getContext());
        ArrayList<Medicament> listeMedicaments = medicamentAcces.getMedicaments(depotLegal);
        Log.d("test",listeMedicaments.get(0).getDepotLegal());
        if(listeMedicaments.size() > 0) {
            ((TextView) vue.findViewById(R.id.texteTitre)).setText(getString(R.string.infosMed_titre)+" "+ listeMedicaments.get(0).getNomCommercial() + " (" + listeMedicaments.get(0).getDepotLegal() + ")");
            ((EditText) vue.findViewById(R.id.depotLegal)).setText(listeMedicaments.get(0).getDepotLegal());
            ((EditText) vue.findViewById(R.id.nomCommercial)).setText(listeMedicaments.get(0).getNomCommercial());
            ((EditText) vue.findViewById(R.id.prixEchantillon)).setText(Float.toString(listeMedicaments.get(0).getPrixEchantillon()));
            ((EditText) vue.findViewById(R.id.famCode)).setText(listeMedicaments.get(0).getCodeFamille());
        }
    }
}
