package Presentation.Home;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.jras.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import BusinessLogic.BusinessConsumption;
import Data.Models.HousesModel;
import Presentation.Houses.activityScanner;

import static androidx.navigation.Navigation.findNavController;

public class HomeFragment extends Fragment {

    public FloatingActionButton fabConsumo;


    HousesModel home = new HousesModel();

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        fabConsumo=view.findViewById(R.id.fabConsumo);


        fabConsumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Abrir pantalla consumo
                findNavController(view).navigate(R.id.fagmentConsumo);
            }
        });


        return view;
    }//fin onCreateView

}//Fin HomeFragment