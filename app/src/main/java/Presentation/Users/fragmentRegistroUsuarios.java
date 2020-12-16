package Presentation.Users;

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
import android.widget.Spinner;

import com.example.jras.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import BusinessLogic.BusinessUser;
import Data.Models.UsersModel;
import Data.Utility.LoadingDialog;
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
    private String spnSeleccion;
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
        validateFields();
        selectedRole();

        btnRegistrar.setEnabled(false);
        btnRegistrar.setBackgroundResource(R.drawable.boton_desabilitado);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textboxEmpty();

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
                }//fin de if(!validate.isInvalid)
            }
        });//fin de OnClick btnRegistrar
        return view;
    }//fin onCreateView

    // ****************************************************************Metodos***********************************************************

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
    }//fin de setValues()

    private void selectedRole() {
        spinnerRol.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinner_item_jraspinners, opciones));

        spinnerRol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spnSeleccion = spinnerRol.getSelectedItem().toString();

                switch (spnSeleccion){
                    case "Administrador":
                    case "Empleado":
                        data.setRole(spnSeleccion);
                        messages.messageToast(getContext(),spnSeleccion);
                        btnRegistrar.setEnabled(true);
                        btnRegistrar.setBackgroundResource(R.drawable.bordes_redondos_azul);
                        validate.isInvalid = false;
                        break;
                }//fin de switch
            }//fin onItemSelected Spinner

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });//fin setOnItemSelectedListener Spinner
    }//fin de selectedRole()

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
    }//fin de isPasswordEquals()

    private void getvalues(View view) {
        txtApellidos= view.findViewById(R.id.txtApellidos);
        txtNombre= view.findViewById(R.id.txtNombre);
        txtEmail= view.findViewById(R.id.txtEmail);
        txtPassConfirm= view.findViewById(R.id.txtPassConfirm);
        txtPassUsuario= view.findViewById(R.id.txtPassUsuario);
        txtUsuario= view.findViewById(R.id.txtUsuario);
        btnRegistrar=view.findViewById(R.id.btnregistrarUsuario);

        spinnerRol = view.findViewById(R.id.spnRolUser);

        //TextInputLayout's
        tilApellidos=view.findViewById(R.id.textInputLayout7);
        tilNombre=view.findViewById(R.id.textInputLayout8);
        tilEmail=view.findViewById(R.id.textInputLayout9);
        tilUsuario=view.findViewById(R.id.textInputLayout10);
        tilPass=view.findViewById(R.id.textInputLayout4);
        tilConfirmPass=view.findViewById(R.id.textInputLayout);
    }//fin de getValues()

    private void validateFields(){
        validate.IsValidTextboxOnClick(txtApellidos,    tilApellidos,   regEx.validNameLstName, "Ingresa un apellido correctamente",btnRegistrar);
        validate.IsValidTextboxOnClick(txtNombre,       tilNombre,      regEx.validNameLstName, "Ingresa un nombre correctamente",btnRegistrar);
        validate.IsValidTextboxOnClick(txtEmail,        tilEmail,       regEx.validEmail,       "Ingresa un correo electronico valido",btnRegistrar);
        validate.IsValidTextboxOnClick(txtUsuario,      tilUsuario,     regEx.validUser,        "Ingresa un usuario valido (entre 8 y 16 caracteres incluidas letras y números)",btnRegistrar);
        validate.IsValidTextboxOnClick(txtPassUsuario,  tilPass,        regEx.validPassword,    "Ingresar una contraseña valida(entre 8 y 16 caracteres, una mayuscula, numeros y un simbolo)",btnRegistrar);
        isPasswordEquals(txtPassConfirm);
    }//fin de validateFields()

    private void textboxEmpty(){
        if (txtApellidos.getText().length()==0 || txtNombre.getText().length()==0 || txtEmail.getText().length()==0 || txtUsuario.getText().length()==0 ||
        txtPassUsuario.getText().length()==0 || txtPassConfirm.getText().length()==0){
            messages.messageToast(getContext(),"Debes llenar todos los campos correctamente");
            validate.isInvalid=true;
        }
        else{
            if (spnSeleccion=="Seleccione un Rol de Usuario:"){
                messages.messageToast(getContext(),"Debes seleccionar un rol de usuario");
                btnRegistrar.setEnabled(false);
                btnRegistrar.setBackgroundResource(R.drawable.boton_desabilitado);
                validate.isInvalid=true;
            }else{
                validate.isInvalid=false;
            }
        }
    }//fin textboxEmpty()

}