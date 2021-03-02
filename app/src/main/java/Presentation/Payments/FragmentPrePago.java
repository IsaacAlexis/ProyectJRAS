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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
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
import Data.Utility.LoadingDialog;
import Data.Utility.Messages;
import Presentation.Expenses.fragmentRegistrarGastos;
import Presentation.Houses.activityScanner;

import static androidx.navigation.Navigation.findNavController;

public class FragmentPrePago extends Fragment {
    EditText Barcode;
    Button btnSearch;

    public static final int CODIGO_PERMISOS_CAMARA = 1, CODIGO_INTENT = 2;
    public boolean permisoCamaraConcedido = false, permisoSolicitadoDesdeBoton = false;
    LoadingDialog loadingDialog = new LoadingDialog(FragmentPrePago.this);
    Handler handler = new Handler();

    PaymentsModel pays=new PaymentsModel();


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController=Navigation.findNavController(view);
        Barcode=view.findViewById(R.id.txtBarCodeRecibo);

        Barcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {

                if(Barcode.length()==8){
                    loadingDialog.startLoadingDialogFragment(getContext(),"Buscando referencia...");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pays.setBarcode(Barcode.getText().toString());
                            if(!new BusinessPayments().AccessToRegisterPayment(pays)){
                                new Messages().messageToastShort(getContext(),pays.getValidationMessage());

                            }else{
                                Barcode.setText("");
                                FragmentPrePagoDirections.ActionFragmentPrePagoToFragmentPagos action= FragmentPrePagoDirections.actionFragmentPrePagoToFragmentPagos(pays);
                                findNavController(getView()).navigate(action);
                            }
                            loadingDialog.dismissDialog();
                        }
                    },50);



                }

            }
        });
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


            }
        });


//        btnSearch=view.findViewById(R.id.btnBuscarRecibo);
//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//             FragmentPrePagoDirections.ActionFragmentPrePagoToFragmentPagos action=FragmentPrePagoDirections.actionFragmentPrePagoToFragmentPagos(3);
//             navController.navigate(action);
//            }
//        }
//        );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pre_pago, container, false);

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
                    loadingDialog.startLoadingDialogFragment(FragmentPrePago.this.getContext(),"Buscando...");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pays.setBarcode(data.getStringExtra("codigo"));
                            if(!new BusinessPayments().AccessToRegisterPayment(pays)){
                                new Messages().messageToastShort(getContext(),pays.getValidationMessage());
                                return;
                            }
                            FragmentPrePagoDirections.ActionFragmentPrePagoToFragmentPagos action= FragmentPrePagoDirections.actionFragmentPrePagoToFragmentPagos(pays);
                            findNavController(getView()).navigate(action);
                            loadingDialog.dismissDialog();
                        }
                    },50);


                }
            }
        }
    }//fin de onActivityResult
}