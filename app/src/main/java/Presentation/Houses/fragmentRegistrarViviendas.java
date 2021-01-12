package Presentation.Houses;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.sax.TextElementListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jras.R;
import com.google.android.material.textfield.TextInputLayout;

import BusinessLogic.BusinessConsumption;
import BusinessLogic.BusinessHouse;
import Data.Models.HousesModel;
import Data.Models.UsersModel;
import Data.Utility.Messages;
import Data.Utility.RegExValidations;
import Data.Utility.Validations;
import Presentation.Home.HomeFragment;

import static androidx.navigation.Navigation.findNavController;

public class fragmentRegistrarViviendas extends Fragment {

    //Variables
    private EditText owner,phoneNumber;
    private EditText email,street,houseNumber;
    private EditText zipCode,colony,city,state,measurer;
    private Button btnRegistrar;
    private Button bntEscaner;
    private TextInputLayout tilMeasurer,tilOwner,tilPhoneNum,tilEmail,tilStreet;
    private TextInputLayout tilHouseNum,tilPostalCode,tilColony,tilCity,tilState;

    //Variables del Scanner
    private static final int CODIGO_PERMISOS_CAMARA = 1, CODIGO_INTENT = 2;
    private boolean permisoCamaraConcedido = false, permisoSolicitadoDesdeBoton = false;

    //instancias de otras clases
    HousesModel house = new HousesModel();
    UsersModel users=new UsersModel();
    Validations validations = new Validations();
    Messages messages=new Messages();
    RegExValidations regEx = new RegExValidations();


    public fragmentRegistrarViviendas() {
        // Required empty public constructor
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_registrar_viviendas, container, false);
        verificaryPedirPermisosDeCamara();
        //Obtencion de los componentes y validacion de datos.
        getValues(view);

        btnRegistrar.setEnabled(false);
        btnRegistrar.setBackgroundResource(R.drawable.boton_desabilitado);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textboxEmpty();

                if(validations.isInvalid){
                    messages.messageToast(getContext(),"Debes llenar los campos con los formatos establecidos");
                }else{
                    setValues();
                    new BusinessHouse().BridgeHomeRegister(house);
                    if(!house.isStatusActivity()){
                        messages.messageAlert(getContext(),house.getMessage(),"Registro exitoso",view,R.id.fragmentViviendas);
                        CleanFields();
                    }else{
                        messages.messageToast(getContext(),house.getMessage());
                        findNavController(view).navigate(R.id.fragmentViviendas);
                    }

                }
            }
        });//fin btnRegistrar

        bntEscaner.setOnClickListener(new View.OnClickListener() {
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

        return view;
    }

    private void CleanFields(){
        owner.setText("");
        phoneNumber.setText("");
        email.setText("");
        street.setText("");
        houseNumber.setText("");
        zipCode.setText("");
        colony.setText("");
        colony.setText("");
        colony.setText("");
        city.setText("");
        state.setText("");
        measurer.setText("");
    }

    public void getValues(View view){

        owner = view.findViewById(R.id.txtPropietario);
        phoneNumber = view.findViewById(R.id.txtTelefonoViv);
        email = view.findViewById(R.id.txtEmail);
        street = view.findViewById(R.id.txtCalleViv);
        houseNumber = view.findViewById(R.id.txtNumViv);
        zipCode = view.findViewById(R.id.txtCPViv);
        colony= view.findViewById(R.id.txtColViv);
        colony.setText(users.getCurrentColony());
        colony.setEnabled(false);
        city= view.findViewById(R.id.txtCiudadViv);
        state= view.findViewById(R.id.txtEstadoViv);
        measurer=view.findViewById(R.id.txtMedidorViviendas);
        btnRegistrar= view.findViewById(R.id.btnregistrarViviendaM);
        bntEscaner=view.findViewById(R.id.btnEscanerViviendas);

        //**********TextInputLayout's**********
        tilMeasurer=view.findViewById(R.id.textInputLayout16);
        tilOwner=view.findViewById(R.id.textInputLayout18);
        tilPhoneNum=view.findViewById(R.id.textInputLayout19);
        tilEmail=view.findViewById(R.id.textInputLayout20);
        tilHouseNum=view.findViewById(R.id.textInputLayout23);
        tilStreet=view.findViewById(R.id.textInputLayout21);
        tilPostalCode=view.findViewById(R.id.textInputLayout24);
        tilColony=view.findViewById(R.id.textInputLayout26);
        tilCity=view.findViewById(R.id.textInputLayout27);
        tilState=view.findViewById(R.id.textInputLayout28);

        //**********Validations**********

        validations.IsValidTextboxOnClick(owner,tilOwner,regEx.validNamesComplete,"Ingresa el nombre completo del propietario correctamente",btnRegistrar);
        validations.IsValidTextboxOnClick(phoneNumber,tilPhoneNum,"^\\d{10}$","Ingresa un número de telefono valido",btnRegistrar);
        validations.IsValidTextboxOnClick(email,tilEmail,regEx.validEmail,"Ingresar un correo electronico valido",btnRegistrar);
        validations.IsValidTextboxOnClick(street,tilStreet,regEx.validStreet,"Ingresa la calle de la vivienda correctamente",btnRegistrar);
        validations.IsValidTextboxOnClick(houseNumber,tilHouseNum,"^\\d{1,8}$","Ingresa el numero de la vivienda correctamente",btnRegistrar);
        validations.IsValidTextboxOnClick(zipCode,tilPostalCode,"^\\d{5}$","Ingresar un código postal valido",btnRegistrar);
        validations.IsValidTextboxOnClick(city,tilCity,regEx.validCityState,"Ingresa el municipio donde se ubica la vivienda correctamente",btnRegistrar);
        validations.IsValidTextboxOnClick(state,tilState,regEx.validCityState,"Ingresa el Estado donde se ubica la vivienda correctamente",btnRegistrar);

    }//fin de getValues()

    private void textboxEmpty(){
        if (measurer.getText().length()==0 || owner.getText().length()==0 || phoneNumber.getText().length()==0 || email.getText().length()==0 ||
                street.getText().length()==0 || houseNumber.getText().length()==0 || zipCode.getText().length()==0 || city.getText().length()==0
                || state.getText().length()==0){
            messages.messageToast(getContext(),"Debes llenar todos los campos correctamente");
            validations.isInvalid=true;
        }
        else{
            validations.isInvalid=false;
        }
    }//fin textboxEmpty()

    private void setValues(){
        //house.setBarCode(colony.getText().toString().trim().substring(0,3).toUpperCase()+"-"
        // +street.getText().toString().substring(0,3).toUpperCase()+
        //"-"+houseNumber.getText().toString().substring(0,3));
        if (measurer.getText().length()!=0){
            house.setBarCode(measurer.getText().toString());
            house.setOwner(owner.getText().toString());
            house.setPhoneNumber(Long.parseLong(phoneNumber.getText().toString()) );
            house.setEmail(email.getText().toString());
            house.setStreet(street.getText().toString());
            house.setHouseNumber(Integer.parseInt(houseNumber.getText().toString()));
            house.setZipCode(Integer.parseInt(zipCode.getText().toString()));
            house.setColony(colony.getText().toString());
            house.setCity(city.getText().toString());
            house.setState(state.getText().toString());
            house.setStatusHouse("SIN ADEUDO");
        }
        else{
            messages.messageToast(getContext(),"Ingrese el numero del medidor");
        }
    }//fin de setValues()




    //************************************************Metodos para el scaner*******************************************************************
    private void escanear(){
        Intent i = new Intent(getContext(), activityScanner.class);
        startActivityForResult(i,CODIGO_INTENT);
    }//fin de escanear()

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CODIGO_INTENT){
            if (resultCode == Activity.RESULT_OK){
                if (data != null){
                    String codigo = data.getStringExtra("codigo");
                    measurer.setText(codigo);
                    measurer.setEnabled(false);
                }
            }
        }
    }//fin onActivityResult

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CODIGO_PERMISOS_CAMARA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (permisoSolicitadoDesdeBoton){
                        escanear();
                    }
                    permisoCamaraConcedido = true;
                }
                else
                    messages.messageToastShort(getContext(),"No puedes escanear si no das el permiso");
                break;
        }
    }//fin onRequestPermissionsResult

    private void verificaryPedirPermisosDeCamara(){
        int estadoDePermiso = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED)
            permisoCamaraConcedido = true;
        else
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},CODIGO_PERMISOS_CAMARA);
    }// fin verificaryPedirPermisosDeCamara()

}//fin class fragment