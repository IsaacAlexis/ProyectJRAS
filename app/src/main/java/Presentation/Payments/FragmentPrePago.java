package Presentation.Payments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jras.R;

import BusinessLogic.BusinessPayments;
import Data.Models.PaymentsModel;
import Data.Utility.Messages;
import Presentation.Houses.activityScanner;

import static androidx.navigation.Navigation.findNavController;

public class FragmentPrePago extends Fragment {
    EditText Barcode;
    Button btnSearch;
    FragmentManager fragmentManager;
    public static final int CODIGO_PERMISOS_CAMARA = 1, CODIGO_INTENT = 2;
    public boolean permisoCamaraConcedido = false, permisoSolicitadoDesdeBoton = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pre_pago, container, false);
        Barcode=view.findViewById(R.id.txtBarCodeRecibo);



//        Barcode.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                if(Barcode.length()==8){
//                    if(!new BusinessPayments().AccessToRegisterPayment(Barcode.getText().toString())){
//                        new Messages().messageToastShort(getContext(),new PaymentsModel().getValidationMessage());
//
//                    }else{
//                        Barcode.setText("");
//                        findNavController(getView()).navigate(R.id.fragmentPagos);
//                    }
//
//
//                }
//
//            }
//        });
                btnSearch = view.findViewById(R.id.btnBuscarRecibo);
        verificaryPedirPermisosDeCamara();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!permisoCamaraConcedido){
                    Toast.makeText(getContext(), "Por favor permite que la aplicacion acceda a la camara", Toast.LENGTH_SHORT).show();
                    permisoSolicitadoDesdeBoton = true;
                    verificaryPedirPermisosDeCamara();
                    return;
                }
                escanear();
                //findNavController(view).navigate(R.id.fragmentPagos);
            }
        });
        return view;
    }
    public void escanear(){
        Intent i = new Intent(getContext(), activityScanner.class);
        startActivityForResult(i,CODIGO_INTENT);
    }
//                             <<<<<<<<<<<<Scanner>>>>>>>>>>>>>
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CODIGO_PERMISOS_CAMARA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (permisoSolicitadoDesdeBoton) {
                    escanear();
                }
                permisoCamaraConcedido = true;
            } else {
                new Messages().messageToastShort(getContext(), "No puedes escanear si no das el permiso");
            }
        }
    }

    public void verificaryPedirPermisosDeCamara(){
        int estadoDePermiso = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED){
            permisoCamaraConcedido = true;
        }
        else{
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},CODIGO_PERMISOS_CAMARA);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CODIGO_INTENT){
            if (resultCode == Activity.RESULT_OK){
                if (data != null){
                    if(!new BusinessPayments().AccessToRegisterPayment(data.getStringExtra("codigo"))){
                        new Messages().messageToastShort(getContext(),new PaymentsModel().getValidationMessage());
                        return;
                    }
                    findNavController(getView()).navigate(R.id.fragmentPagos);

                }
            }
        }
    }//fin de onActivityResult
}