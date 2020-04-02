package com.example.gsbparam.Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.gsbparam.R;

public class Accueil extends Fragment {

    //private com.example.gsbparam.Modele.Accueil accueil;
    private Button btnPra, btnMed, btnOff;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //accueil = ViewModelProviders.of(this).get(com.example.gsbparam.Modele.Accueil.class);
        View root = inflater.inflate(R.layout.fragment_accueil, container, false);

        initialisation(root);

        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    public void initialisation(View v){
        btnPra = (Button) v.findViewById(R.id.btnAccAffPra);
        btnMed = (Button) v.findViewById(R.id.btnAccAffMed);
        btnOff = (Button) v.findViewById(R.id.btnAccOffMed);

        btnPra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.gotoAffPra);
            }
        });

        btnMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.gotoAffMed);
            }
        });

        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.gotoOffrir);
            }
        });

    }
}
