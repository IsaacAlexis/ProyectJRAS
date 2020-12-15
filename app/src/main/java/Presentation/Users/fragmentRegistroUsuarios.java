package Presentation.Users;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jras.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import BusinessLogic.BusinessUser;
import Data.Models.UsersModel;
import Data.Utility.Messages;
import Data.Utility.RegExValidations;
import Data.Utility.Validations;

import static androidx.navigation.Navigation.findNavController;

public class fragmentRegistroUsuarios extends Fragment {


    //Variables
    private EditText txtApellidos, txtNombre, txtEmail;
    private EditText txtUsuario, txtPassUsuario, txtPassConfirm;
    private Button btnRegistrar;
    private TextInputLayout tilApellidos, tilNombre, tilEmail, tilUsuario, tilPass, tilConfirmPass;
    //Spinner
    private Spinner spinnerRol;
    String[] opciones = {"Seleccione un Rol de Usuario:","Administrador","Empleado"};

    //instancias de otras clases
    UsersModel data = new UsersModel();
    Validations validate = new Validations();
    Messages messages = new Messages();
    RegExValidations regEx = new RegExValidations();
    String currentDateandTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    public fragmentRegistroUsuarios() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_registro_usuarios, container, false);

        getvalues(view);
        ValidateFields();
//
//        btnRegistrar.setEnabled(false);
//        btnRegistrar.setBackgroundResource(R.drawable.boton_desabilitado);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate.finalValidation=true;
                ValidateFields();
                selectedRole();

                if (!validate.isInvalid){
                    setvalues();
                    new BusinessUser().BridgeUserRegister(data);
                    if(!data.isRegisterUser()){
                        messages.messageAlert(getContext(),data.getValidationMessage(),"Se completo con exito el registro",view,R.id.fragmentUsuarios);
                    }else{
                        if(data.getUserExist()){
                            messages.messageToast(getContext(),data.getValidationMessage());
                        }else{
                            messages.messageToast(getContext(),data.getValidationMessage());
                            findNavController(view).navigate(R.id.fragmentUsuarios);
                        }

                    }
                }else{
                    ValidateFields();
                    messages.messageToast(getContext(),"Debes llenar todos los campos correctamente");
                }
//                if (validate.IsValidTextbox(txtApellidos, "^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,12}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,13})?$", "Debes ingresar un apellido") |
//                        validate.IsValidTextbox(txtNombre, "^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,12}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,13})?$", "Debes ingresar un nombre") |
//                        validate.IsValidTextbox(txtEmail, "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", "Debes ingresar un correo electronico") |
//                        validate.IsValidTextbox(txtPassUsuario, "^(?=.*\\d)(?=.*[\\u0021-\\u002b\\u003c-\\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{8,16}$", "Debes ingresar una contraseña valida(entre 8 y 16 caracteres, una mayuscula,numeros y un simbolo)") |
//                        isPasswordEquals(txtPassConfirm, "Debes confirmar la contraseña ingresada") |
//                        validate.IsValidTextbox(txtUsuario, "^(?=.*[0-9])[0-9a-zA-Z]{8,15}$", "Debes ingresar un usuario que contengan letras y numeros(entre 8 y 16 caracteres ") |
//                        isSelectedRole()) {
//                    messages.messageToast(getContext(),"Debes llenar todos los campos correctamente");
//
//                   } else {
//                    setvalues();
//                    new BusinessUser().BridgeUserRegister(data);
//                    if(!data.isRegisterUser()){
//                        messages.messageAlert(getContext(),data.getValidationMessage(),"Se completo con exito el registro",view,R.id.fragmentUsuarios);
//                    }else{
//                        if(data.getUserExist()){
//                            messages.messageToast(getContext(),data.getValidationMessage());
//                        }else{
//                            messages.messageToast(getContext(),data.getValidationMessage());
//                            findNavController(view).navigate(R.id.fragmentUsuarios);
//                        }
//
//                    }
//                }
            }




        });


        return view;
    }//fin onCreateView
    public void setvalues(){
        data.setLastName(txtApellidos.getText().toString());
        data.setFirstName(txtNombre.getText().toString());
        data.setUserName(txtUsuario.getText().toString());
        data.setPassword(txtPassUsuario.getText().toString());
        data.setEmail(txtEmail.getText().toString());
        data.setColony(data.getCurrentColony());
        data.setExpirationDate(data.getCurrentExpirationDate());
        data.setUserStatus("ACTIVO");
        data.setAddedDate(currentDateandTime);
        data.setModDate(currentDateandTime);

    }
    private void selectedRole() {
        spinnerRol.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinner_item_jraspinners, opciones));

        spinnerRol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String seleccion = spinnerRol.getSelectedItem().toString();

                switch (seleccion){
                    case "Administrador":
                    case "Empleado":
                        data.setRole(seleccion);
                        messages.messageToast(getContext(),seleccion);
                        btnRegistrar.setEnabled(true);
                        validate.isInvalid = false;
                        break;
                    case "Seleccione un Rol de Usuario":
                        messages.messageToast(getContext(),"Debes seleccionar un rol de usuario");
                        btnRegistrar.setEnabled(false);
                        validate.isInvalid = true;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                messages.messageToast(getContext(),"Debes seleccionar un rol de usuario");
                btnRegistrar.setEnabled(false);
                validate.isInvalid = true;
            }
        });
    }

    private void isPasswordEquals(EditText field) {
        txtPassConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!field.getText().toString().equals(txtPassUsuario.getText().toString())){
                    tilConfirmPass.setError("Las contraseñas deben coincidir");
                    validate.isInvalid=true;
                }else{
                    tilConfirmPass.setErrorEnabled(false);
                    validate.isInvalid=false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void getvalues(View view) {
        txtApellidos= view.findViewById(R.id.txtApellidos);
        txtNombre= view.findViewById(R.id.txtNombre);
        txtEmail= view.findViewById(R.id.txtEmail);
        txtPassConfirm= view.findViewById(R.id.txtPassConfirm);
        txtPassUsuario= view.findViewById(R.id.txtPassUsuario);
        txtUsuario= view.findViewById(R.id.txtUsuario);
        btnRegistrar=view.findViewById(R.id.btnregistrarUsuario);

        spinnerRol = (Spinner)view.findViewById(R.id.spnRolUser);

        //TextInputLayout's
        tilApellidos=view.findViewById(R.id.textInputLayout7);
        tilNombre=view.findViewById(R.id.textInputLayout8);
        tilEmail=view.findViewById(R.id.textInputLayout9);
        tilUsuario=view.findViewById(R.id.textInputLayout10);
        tilPass=view.findViewById(R.id.textInputLayout4);
        tilConfirmPass=view.findViewById(R.id.textInputLayout);
    }

    private void ValidateFields(){
        validate.IsValidTextboxOnClick(txtApellidos,    tilApellidos,   regEx.validNameLstName, "Ingresa un apellido correctamente",btnRegistrar);
        validate.IsValidTextboxOnClick(txtNombre,       tilNombre,      regEx.validNameLstName, "Ingresa un nombre correctamente",btnRegistrar);
        validate.IsValidTextboxOnClick(txtEmail,        tilEmail,       regEx.validEmail,       "Ingresa un correo electronico valido",btnRegistrar);
        validate.IsValidTextboxOnClick(txtUsuario,      tilUsuario,     regEx.validUser,        "Ingresa un usuario valido (entre 8 y 16 caracteres incluidas letras y números)",btnRegistrar);
        validate.IsValidTextboxOnClick(txtPassUsuario,  tilPass,        regEx.validPassword,    "Ingresar una contraseña valida(entre 8 y 16 caracteres, una mayuscula, numeros y un simbolo)",btnRegistrar);
        isPasswordEquals(txtPassConfirm);
    }



}