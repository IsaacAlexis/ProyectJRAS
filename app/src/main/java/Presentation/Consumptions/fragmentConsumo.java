package Presentation.Consumptions;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jras.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BusinessLogic.BusinessConsumption;
import Data.Models.ConsumptionsModel;
import Data.Models.UsersModel;
import Data.Utility.LoadingDialog;
import Presentation.Houses.activityScanner;
import Data.Models.WaterBillsModel;
import Data.Utility.Dates;
import Data.Utility.GenaratorPDF;
import Data.Utility.Messages;

import static androidx.navigation.Navigation.findNavController;

public class fragmentConsumo extends Fragment {

    //Comunes
    private EditText tvBarCode;
    private TextView tvOwner;
    private TextView tvHouseNum;
    private EditText etConsumption;
    private TextView tvFechaConsumo;
    private Button btnEscanear;
    public Button btnBuscar;
    private EditText etLastConsumption;
    private EditText etLastRate;
    private Button btnRegistrar;
    private String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    private String codigo;

    StorageReference storageReference;

    //Variables para el escaner
    public static final int CODIGO_PERMISOS_CAMARA = 1, CODIGO_INTENT = 2;
    public boolean permisoCamaraConcedido = false, permisoSolicitadoDesdeBoton = false;

    //Instancias de otras clases
    WaterBillsModel waterBillsModel = new WaterBillsModel();
    ConsumptionsModel cm = new ConsumptionsModel();
    UsersModel user = new UsersModel();
    List<WaterBillsModel> bill=new ArrayList<>();
    List<ConsumptionsModel> consumptionsModelList=new ArrayList<>();
    Messages messages = new Messages();

    //Clase de pantalla de carga
    LoadingDialog loadingDialog = new LoadingDialog(fragmentConsumo.this);
    Handler handler = new Handler();


    public fragmentConsumo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_consumo, container, false);
        getValues(view);


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
            }//fin de onClick
        });//fin btnEscanear.setOnClickListener()

        tvBarCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (tvBarCode.getText().length()!=0){
                    btnEscanear.setVisibility(View.INVISIBLE);
                    btnBuscar.setVisibility(View.VISIBLE);
                    waterBillsModel.setBarCode(tvBarCode.getText().toString());
                }else{
                    btnEscanear.setVisibility(View.VISIBLE);
                    btnBuscar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });//fin de tvBarCode.addTextChangedListener

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.startLoadingDialogFragment(getContext());

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mostrarInfoViv(view);

                        loadingDialog.dismissDialog();
                    }
                },50);
            }//fin de onClick btnBuscar
        });//fin de btnBuscar.setOnClickListener

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.startLoadingDialogFragment(getContext());

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(!waterBillsModel.isExistFirstRegister()){
                            registrar(false);
                            new BusinessConsumption().BridgeConsumptionFirstReading(getContext(),cm,waterBillsModel,consumptionsModelList,storageReference);


                        }else {
                            registrar(true);

                            new BusinessConsumption().BridgeConsumptionReading(getContext(),cm,waterBillsModel,storageReference);


                        }

                        Toast.makeText(getContext(), cm.getValidationMessage(), Toast.LENGTH_SHORT).show();
                        findNavController(view).navigate(R.id.nav_home);

                        loadingDialog.dismissDialog();

                    }//fin de run()
                },50);//fin de postDelayed
            }//fin de onClick btnRegistrar
        });//fin de btnRegistrar.setOnClickListener


        return view;
    }//fin de onCreateView




    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Metodos>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<

    public void getValues(View view){
        storageReference= FirebaseStorage.getInstance().getReference();
        tvBarCode = view.findViewById(R.id.txtBarCodeConsumo);
        tvOwner = view.findViewById(R.id.txtPropietarioC);
        tvHouseNum = view.findViewById(R.id.txtNumeroCasaConsumo);
        tvFechaConsumo = view.findViewById(R.id.txFechaConsumo);
        etConsumption = view.findViewById(R.id.txtConsumo);
        etLastConsumption=view.findViewById(R.id.txtConsumoAnterior);
        etLastRate=view.findViewById(R.id.txtPagoAnterior);
        btnRegistrar = view.findViewById(R.id.btnRegistrarConsumo);
        btnEscanear = view.findViewById(R.id.btnEscanearConsumo);
        btnBuscar = view.findViewById(R.id.btnBuscarConsumo);

        tvFechaConsumo.setText("Fecha:  " + new java.text.SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        verificaryPedirPermisosDeCamara();
    }//fin de getValues(View view)

    private void mostrarInfoViv(View view){
        new BusinessConsumption().BridgeHouseScanner(waterBillsModel);

        if (!waterBillsModel.isCorrectHouse()){
            new Messages().messageToast(getContext(),waterBillsModel.getValidationMessage());
        }else{
            if(!waterBillsModel.isExistFirstRegister()){
                new Messages().messageAlert(getContext(),"Debes registrar el ultimo y el actual consumo de agua","No existe registros de consumo en esta vivienda",view);
                tvBarCode.setText(waterBillsModel.getBarCode());
                tvHouseNum.setText(""+waterBillsModel.getHouseNumber());
                tvOwner.setText(waterBillsModel.getOwner());
                etLastConsumption.setVisibility(View.VISIBLE);
                etLastRate.setVisibility(View.VISIBLE);


            }else{
                tvBarCode.setText(waterBillsModel.getBarCode());
                tvHouseNum.setText(""+waterBillsModel.getHouseNumber());
                tvOwner.setText(waterBillsModel.getOwner());
                //new Emails().createMailConsumptions(waterBillsModel.getEmail());
            }
        }
    }//fin de mostrarInfoViv(View view)

    /*activity-> se refiere al camino que va tomar si seria el primer registro o realizar el registro con
    con normalidad*/
    private void registrar(Boolean activity){
        if(!activity){
            cm.setIDUser(user.getCurrentIdUser());
            cm.setBarCode(tvBarCode.getText().toString());
            cm.setRate(Float.parseFloat(etLastRate.getText().toString()));
            consumptionsModelList.add(new ConsumptionsModel(currentDate,Float.parseFloat(etConsumption.getText().toString())));
            consumptionsModelList.add(new ConsumptionsModel(new Dates().getLastDate(),Float.parseFloat(etLastConsumption.getText().toString())));

        }else{
            cm.setIDUser(user.getCurrentIdUser());
            cm.setBarCode(tvBarCode.getText().toString());
            cm.setReadDate(currentDate);
            cm.setM3(Float.parseFloat(etConsumption.getText().toString()));
            waterBillsModel.setReadNow(Float.parseFloat(etConsumption.getText().toString()));
            waterBillsModel.setReadDate(new Dates().getDateBills());
            waterBillsModel.setNowRate(new BusinessConsumption().BridgeGetRateNow(waterBillsModel.getReadNow()-
                    waterBillsModel.getReadLast()));
            bill=new BusinessConsumption().BridgeWaterBills(cm);
        }

    }//fin de registrar(Boolean activity)


    //************************************************Metodos para el scaner*******************************************************************
    public void escanear(){
        Intent i = new Intent(getContext(), activityScanner.class);
        startActivityForResult(i,CODIGO_INTENT);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CODIGO_PERMISOS_CAMARA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (permisoSolicitadoDesdeBoton) {
                    escanear();
                }
                permisoCamaraConcedido = true;
            } else {
                messages.messageToastShort(getContext(), "No puedes escanear si no das el permiso");
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
                    codigo = data.getStringExtra("codigo");
                    waterBillsModel.setBarCode(codigo);

                    btnBuscar.callOnClick();
                    btnBuscar.setVisibility(View.INVISIBLE);


                    if (!waterBillsModel.isCorrectHouse()){
                        Toast.makeText(getContext(), waterBillsModel.getValidationMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }//fin de onActivityResult
}//fin de fragmentConsumo
