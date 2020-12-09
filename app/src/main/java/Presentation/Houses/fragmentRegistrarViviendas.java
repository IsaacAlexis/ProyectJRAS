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
    public Validations validations = new Validations();
    Messages messages=new Messages();

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

        measurer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validations.IsValidTextboxMessage(measurer,"^\\d{8}$")){
                    tilMeasurer.setError("Ingresa un numero de medidor valido");
                }
                else{
                    tilMeasurer.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        owner.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validations.IsValidTextboxMessage(owner,"^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})?(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})?$")){
                    tilOwner.setError("Ingresa el nombre completo del propietario correctamente");
                }
                else{
                    tilOwner.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validations.IsValidTextboxMessage(phoneNumber,"^\\d{10,15}$")){
                    tilPhoneNum.setError("Ingresa un número de telefono valido");
                }
                else{
                    tilPhoneNum.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validations.IsValidTextboxMessage(email,"^[A-Z_a-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(.[A-Za-z]{2,4})$")){
                    tilEmail.setError("Ingresar un correo electronico valido");
                }
                else{
                    tilEmail.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        street.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validations.IsValidTextboxMessage(street,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.0-9]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ0-9]{1,15})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?$")){
                    tilStreet.setError("Ingresa la calle de la vivienda correctamente");
                }
                else{
                    tilStreet.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        houseNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validations.IsValidTextboxMessage(houseNumber,"^\\d{1,8}$")){
                    tilHouseNum.setError("Ingresa el numero de la vivienda correctamente");
                }
                else{
                    tilHouseNum.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        zipCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validations.IsValidTextboxMessage(zipCode,"^\\d{5}$")){
                    tilPostalCode.setError("Ingresar un código postal valido");
                }
                else{
                    tilPostalCode.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validations.IsValidTextboxMessage(city,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?$")){
                    tilCity.setError("Ingresa el municipio donde se ubica la vivienda correctamente");
                    btnRegistrar.setEnabled(false);
                }
                else{
                    tilCity.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        state.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validations.IsValidTextboxMessage(state,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?$")){
                    tilState.setError("Ingresa el Estado donde se ubica la vivienda correctamente ");
                }
                else{
                    tilState.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validations.IsValidTextbox(measurer,"^\\d{8}$","Debes ingresar un numero de medidor correctamente")||
                        validations.IsValidTextbox(owner,"^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})?(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})?$","Debes ingresar un Nombre del propietario completo correctamente")|
                        validations.IsValidTextbox(phoneNumber,"^\\d{10,15}$","Debes ingresar un numero de telefono correctamente")|
                        validations.IsValidTextbox(email,"^[A-Z_a-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(.[A-Za-z]{2,4})$","Debes ingresar un correo electronico correctamente")|
                        validations.IsValidTextbox(street,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.0-9]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ0-9]{1,15})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?$","Debes ingresar una calle de la vivienda correctamente")|
                        validations.IsValidTextbox(houseNumber,"^\\d{1,8}$","Debes ingresar el numero de la vivienda correctamente")|
                        validations.IsValidTextbox(zipCode,"^\\d{5}$","Debes ingresar un codigo postal correctamente")|
                        validations.IsValidTextbox(city,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?$","Debes ingresar el municipio donde se ubica la vivienda correctamente")|
                        validations.IsValidTextbox(state,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?$","Debes ingresar el Estado donde se ubica la vivienda correctamente ")){
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
        //validateFields();

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
    }
//    public void validateFields(){
//        validations.IsValidTextboxOnClick(owner,
//                "^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})?(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})?$",
//                "Debes ingresar un Nombre del propietario completo correctamente");
//        validations.IsValidTextboxOnClick(phoneNumber,"^\\d{10,15}$","Debes ingresar un numero de telefono correctamente");
//        validations.IsValidTextboxOnClick(email,"^[_a-zA-Z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(.[A-Za-z]{2,4})$",
//                "Debes ingresar un correo electronico correctamente");
//        validations.IsValidTextboxOnClick(street,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.0-9]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ0-9]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?$",
//                "Debes ingresar una calle de la vivienda correctamente");
//        validations.IsValidTextboxOnClick(houseNumber,"^\\d{1,8}$","Debes ingresar el numero de la vivienda correctamente");
//        validations.IsValidTextboxOnClick(zipCode,"^\\d{5}$","Debes ingresar un codigo postal correctamente");
//        validations.IsValidTextboxOnClick(colony,
//                "^[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?$",
//                "Debes ingresar la colonia de la vivienda correctamente");
//        validations.IsValidTextboxOnClick(city,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?$",
//                "Debes ingresar el municipio donde se ubica la vivienda correctamente");
//        validations.IsValidTextboxOnClick(state,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?$",
//                "Debes ingresar el Estado donde se ubica la vivienda correctamente ");
//
//    }
    private void setValues(){
        //house.setBarCode(colony.getText().toString().trim().substring(0,3).toUpperCase()+"-"+street.getText().toString().substring(0,3).toUpperCase()+
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
    }

    //************************************************Metodos para el scaner*******************************************************************
    private void escanear(){
        Intent i = new Intent(fragmentRegistrarViviendas.this.getContext(), activityScanner.class);
        startActivityForResult(i,CODIGO_INTENT);

    }

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
        int estadoDePermiso = ContextCompat.checkSelfPermission(fragmentRegistrarViviendas.this.getContext(), Manifest.permission.CAMERA);
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



}//fin class fragment