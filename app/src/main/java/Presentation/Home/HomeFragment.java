package Presentation.Home;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import Data.Models.WaterBillsModel;
import Data.Utility.Messages;
import Presentation.Houses.activityScanner;

import static android.content.ContentValues.TAG;
import static androidx.navigation.Navigation.findNavController;

public class HomeFragment extends Fragment {
    public static final int CODE_PERMISSION_CAMERA = 1,CODE_PERMISSION_INTERNET=3,
            CODE_PERMISSION_WRITE=4,CODE_PERMISSION_READ=5;
    public boolean permissionCamera;
    public boolean permissionWrite;
    public boolean permissionInternet;
    public boolean permissionRead;


    public FloatingActionButton fabConsumo;
    public boolean permisoCamaraConcedido = false;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        permissions();

        fabConsumo=view.findViewById(R.id.fabConsumo);
        permission();


        fabConsumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Abrir pantalla consumo
                findNavController(view).navigate(R.id.fagmentConsumo);
            }
        });


        return view;
    }//fin onCreateView
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==CODE_PERMISSION_CAMERA){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionCamera=true;
            } else {
                new Messages().messageToast(getContext(),"Debes permitir el acceso a la camara para obtner los beneficios de la aplicacion");
            }
        }else if(requestCode==CODE_PERMISSION_INTERNET){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionInternet=true;
            } else {
                new Messages().messageToast(getContext(),"Debes permitir el acceso a la camara para obtner los beneficios de la aplicacion");
            }

        }else if(requestCode==CODE_PERMISSION_READ){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionRead=true;
            } else {
                new Messages().messageToast(getContext(),"Debes permitir el acceso a la lectura para obtner los beneficios de la aplicacion");
            }

        }else if(requestCode==CODE_PERMISSION_WRITE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionWrite=true;
            } else {
                new Messages().messageToast(getContext(),"Debes permitir el acceso a la escritura para obtner los beneficios de la aplicacion");
            }

        }

    }
    public void permissions(){
        permissionCamera=isCameraPermissionGranted();
        permissionWrite=isWritePermissionGranted();
        permissionInternet=isInternetPermissionGranted();
        permissionRead=isReadPermissionGranted();
    }
    public  boolean isWritePermissionGranted() {
        int estadoDePermiso = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(HomeFragment.this.getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_PERMISSION_WRITE);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }
    public  boolean isCameraPermissionGranted() {
        int estadoDePermiso = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(HomeFragment.this.getActivity(), new String[]{Manifest.permission.CAMERA}, CODE_PERMISSION_CAMERA);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }
    public  boolean isReadPermissionGranted() {
        int estadoDePermiso = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(HomeFragment.this.getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CODE_PERMISSION_READ);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }
    public  boolean isInternetPermissionGranted() {
        int estadoDePermiso = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(HomeFragment.this.getActivity(), new String[]{Manifest.permission.INTERNET}, CODE_PERMISSION_INTERNET);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    private void permission() {
    }
//    public void verificaryPedirPermisosDeCamara(){
//        int estadoDePermiso = ContextCompat.checkSelfPermission(HomeFragment.this.getContext(), Manifest.permission.CAMERA);
//        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED){
//            permisoCamaraConcedido = true;
//        }
//        else{
//            ActivityCompat.requestPermissions(HomeFragment.this.getActivity(),new String[]{Manifest.permission.CAMERA},CODIGO_PERMISOS_CAMARA);
//        }
//    }

}//Fin HomeFragment