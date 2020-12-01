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

    private Button btnAbrirConsumo;
    public FloatingActionButton fabConsumo;
    //Variables del Scanner
    private static final int CODIGO_PERMISOS_CAMARA = 1, CODIGO_INTENT = 2;
    private boolean permisoCamaraConcedido = false, permisoSolicitadoDesdeBoton = false;


    HousesModel home = new HousesModel();

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        fabConsumo=view.findViewById(R.id.fabConsumo);
        btnAbrirConsumo = view.findViewById(R.id.btnAbrirConsumo);
        verificaryPedirPermisosDeCamara();


        fabConsumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //abrir el escaner
                if (!permisoCamaraConcedido){
                    Toast.makeText(getContext(), "Por favor permite que la aplicacion acceda a la camara", Toast.LENGTH_SHORT).show();
                    permisoSolicitadoDesdeBoton = true;
                    verificaryPedirPermisosDeCamara();
                    return;
                }
                escanear();

            }
        });//fin btnFabConsumo

        btnAbrirConsumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Abrir pantalla consumo
                findNavController(view).navigate(R.id.fagmentConsumo);

            }
        });


        return view;
    }//fin onCreateView

    //Metodos para el scaner
    private void escanear(){
        Intent i = new Intent(HomeFragment.this.getContext(), activityScanner.class);
        startActivityForResult(i,CODIGO_INTENT);

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CODIGO_INTENT){
            if (resultCode == Activity.RESULT_OK){
                if (data != null){
                    String codigo = data.getStringExtra("codigo");
                    home.setBarCode(codigo);

                    new BusinessConsumption().BridgeHouseScanner(home);


                    if (!home.isExistHouse()){
                        Toast.makeText(getContext(), "El código no existe, escanee un código diferente", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        btnAbrirConsumo.callOnClick();
                    }
                }
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CODIGO_PERMISOS_CAMARA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (permisoSolicitadoDesdeBoton){
                        escanear();
                    }
                    permisoCamaraConcedido = true;
                }
                else{
                    permisoDeCamaraDenegado();
                }
                break;
        }
    }

    private void verificaryPedirPermisosDeCamara(){
        int estadoDePermiso = ContextCompat.checkSelfPermission(HomeFragment.this.getContext(), Manifest.permission.CAMERA);
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED){
            permisoCamaraConcedido = true;
        }
        else{
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},CODIGO_PERMISOS_CAMARA);
        }
    }

    private void permisoDeCamaraDenegado(){
        Toast.makeText(getContext(), "No puedes escanear si no das el permiso", Toast.LENGTH_SHORT).show();
    }


}//Fin HomeFragment