package com.example.gsbparam.Controleur;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gsbparam.Modele.GsbParamDAO;
import com.example.gsbparam.Modele.Praticien;
import com.example.gsbparam.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


public class ListePraticiens extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_liste_praticiens, container, false);

        GsbParamDAO praticienAcces = new GsbParamDAO(getActivity());
        EditText champRecherche = (EditText) root.findViewById(R.id.search_field);
        final ArrayList<Praticien> listePraticiens = praticienAcces.getPraticiens("");
        updateListePraticiens(root, listePraticiens,"");

        champRecherche.addTextChangedListener(new TextWatcher(){

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                updateListePraticiens(getView(), listePraticiens, ((EditText)getView().findViewById(R.id.search_field)).getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
                //Rien
            }

            @Override
            public void afterTextChanged(Editable s){
                //Rien
            }

        });

        return root;
    }

    public void updateListePraticiens(View root, ArrayList<Praticien> listePraticiens, String laRecherche){
        if(listePraticiens.size() > 0) {
            String[] recherche = laRecherche.toUpperCase(Locale.getDefault()).split(" ");
            ArrayList<HashMap<String, String>> listePraticiensRecherche = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            String prenom,nom,rue,cp,ville;

            int i;
            boolean trouve;

            //essai foreach
            for (Praticien unPraticien : listePraticiens) {
                trouve = false;
                i=0;
                prenom = unPraticien.getPrenom();
                nom = unPraticien.getNom();
                rue = unPraticien.getRue();
                cp = unPraticien.getCp();
                ville = unPraticien.getVille();

                while(i<recherche.length && !trouve) {
                    if (prenom.toUpperCase(Locale.getDefault()).contains(recherche[i]) ||
                        nom.toUpperCase(Locale.getDefault()).contains(recherche[i]) ||
                        rue.toUpperCase(Locale.getDefault()).contains(recherche[i]) ||
                        cp.toUpperCase(Locale.getDefault()).contains(recherche[i]) ||
                        ville.toUpperCase(Locale.getDefault()).contains(recherche[i])) {

                        trouve = true;
                        map = new HashMap<String, String>();
                        map.put("pra_num", Integer.toString(unPraticien.getNum()));
                        map.put("pra_prenom", prenom);
                        map.put("pra_nom", nom.toUpperCase(Locale.getDefault()));
                        map.put("pra_rue", rue);
                        map.put("pra_cp", cp);
                        map.put("pra_ville", ville);
                        map.put("pra_nomPrenom", nom.toUpperCase(Locale.getDefault())+" "+prenom);
                        map.put("pra_CpVille", cp+", "+ville);
                        listePraticiensRecherche.add(map);
                    }
                    i++;
                }
                Log.d("??",unPraticien.toString());
            }
            ((TextView) root.findViewById(R.id.texte_listePra)).setVisibility(View.INVISIBLE);
            SimpleAdapter mSchedule = new SimpleAdapter(getActivity(), listePraticiensRecherche, R.layout.list_layout,
                    new String[]{"pra_nomPrenom", "pra_rue", "pra_CpVille"}, new int[]{R.id.pra_nomPrenom, R.id.pra_rue, R.id.pra_CpVille});

            ListView resultatRecherche = (ListView) root.findViewById(R.id.resultatRecherche);
            resultatRecherche.setAdapter(mSchedule);
        }
    }
}
