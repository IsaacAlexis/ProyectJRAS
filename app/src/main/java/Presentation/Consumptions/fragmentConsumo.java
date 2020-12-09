package Presentation.Consumptions;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jras.R;

import java.util.Date;

import BusinessLogic.BusinessConsumption;
import Data.Models.ConsumptionsModel;
import Data.Models.HousesModel;
import Data.Models.UsersModel;
import Presentation.Home.HomeFragment;
import Presentation.Houses.activityScanner;

import static androidx.navigation.Navigation.findNavController;

public class fragmentConsumo extends Fragment {

    private EditText tvBarCode;
    private TextView tvOwner;
    private TextView tvHouseNum;
    private EditText etConsumption;
    private TextView tvFechaConsumo;
    private Button btnEscanear;
    private Button btnBuscar;
    private Button btnRegistrar;
    private String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    private String codigo;


    //Variables del Scanner
    private static final int CODIGO_PERMISOS_CAMARA = 1, CODIGO_INTENT = 2;
    private boolean permisoCamaraConcedido = false, permisoSolicitadoDesdeBoton = false;

    HousesModel home = new HousesModel();
    ConsumptionsModel cm = new ConsumptionsModel();
    UsersModel user = new UsersModel();

    public fragmentConsumo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_consumo, container, false);

        tvBarCode = view.findViewById(R.id.txtBarCodeConsumo);
        tvOwner = view.findViewById(R.id.txtPropietarioC);
        tvHouseNum = view.findViewById(R.id.txtNumeroCasaConsumo);
        tvFechaConsumo = view.findViewById(R.id.txFechaConsumo);
        etConsumption = view.findViewById(R.id.txtConsumo);
        btnRegistrar = view.findViewById(R.id.btnRegistrarConsumo);
        btnEscanear = view.findViewById(R.id.btnEscanearConsumo);
        btnBuscar = view.findViewById(R.id.btnBuscarConsumo);


        tvFechaConsumo.setText(currentDate);
        verificaryPedirPermisosDeCamara();


        btnEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //abrir el escaner
                if (!permisoCamaraConcedido){
                    Toast.makeText(getContext(), "Por favor permite que la aplicacion acceda a la camara", Toast.LENGTH_SHORT).show();
                    permisoSolicitadoDesdeBoton = true;
                    verificaryPedirPermisosDeCamara();
                    return;
                }
                escanear();
            }
        });

        tvBarCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (tvBarCode.getText().length()!=0){
                    btnEscanear.setVisibility(View.INVISIBLE);
                    btnBuscar.setVisibility(View.VISIBLE);
                }else{
                    btnEscanear.setVisibility(View.VISIBLE);
                    btnBuscar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualText();
            }
        });


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
                new BusinessConsumption().BridgeConsumptionReading(cm);
                Toast.makeText(getContext(), "Consumo registrado correctamente", Toast.LENGTH_SHORT).show();
                findNavController(view).navigate(R.id.nav_home);
            }
        });


        return view;
    }

    private void manualText(){
        home.setBarCode(tvBarCode.getText().toString());
        new BusinessConsumption().BridgeHouseScanner(home);

        if (!home.isExistHouse()){
            Toast.makeText(getContext(), "El código no existe, escanee un código diferente", Toast.LENGTH_SHORT).show();
            tvHouseNum.setText("");
            tvOwner.setText("");
        }
        else{
            mostrarInfoViv();
        }
    }

    private void mostrarInfoViv(){
        tvBarCode.setText(home.getModifybarCode());
        tvHouseNum.setText(home.getModifyhouseNumber().toString());
        tvOwner.setText(home.getModifyowner());
    }

    private void registrar(){
        cm.setIDUser(user.getCurrentIdUser());
        cm.setBarCode(tvBarCode.getText().toString());
        cm.setReadDate(currentDate);
        cm.setM3(Float.parseFloat(etConsumption.getText().toString()));
    }

    public void clearFields(){
        tvBarCode.setText("");
        tvHouseNum.setText("");
        tvOwner.setText("");
    }


    //Metodos para el scaner
    private void escanear(){
        Intent i = new Intent(fragmentConsumo.this.getContext(), activityScanner.class);
        startActivityForResult(i,CODIGO_INTENT);

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CODIGO_INTENT){
            if (resultCode == Activity.RESULT_OK){
                if (data != null){
                    codigo = data.getStringExtra("codigo");
                    home.setBarCode(codigo);

                    new BusinessConsumption().BridgeHouseScanner(home);


                    if (!home.isExistHouse()){
                        Toast.makeText(getContext(), "El código no existe, escanee un código diferente", Toast.LENGTH_SHORT).show();
                        clearFields();
                    }
                    else{
                        mostrarInfoViv();
                        btnEscanear.setVisibility(View.VISIBLE);
                        btnBuscar.setVisibility(View.INVISIBLE);
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
        int estadoDePermiso = ContextCompat.checkSelfPermission(fragmentConsumo.this.getContext(), Manifest.permission.CAMERA);
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


}