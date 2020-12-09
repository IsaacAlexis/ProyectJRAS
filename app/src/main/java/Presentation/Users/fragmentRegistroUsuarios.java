package Presentation.Users;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.jras.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import BusinessLogic.BusinessUser;
import Data.Models.UsersModel;
import Data.Utility.Messages;
import Data.Utility.Validations;

import static androidx.navigation.Navigation.findNavController;

public class fragmentRegistroUsuarios extends Fragment {


    //Variables
    private EditText txtApellidos,txtNombre,txtEmail;
    private EditText txtUsuario,txtPassUsuario,txtPassConfirm;
    private RadioButton rolAdmin,rolEmpleado,rolInvitado;
    private Button btnRegistrar;
    private TextInputLayout tilApellidos,tilNombre,tilEmail,tilUsuario,tilPass,tilConfirmPass;
    //instancias de otras clases
    UsersModel data = new UsersModel();
    Validations validate = new Validations();
    Messages messages=new Messages();
    String currentDateandTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    public fragmentRegistroUsuarios() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_registro_usuarios, container, false);
        getvalues(view);

        txtApellidos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validate.IsValidTextboxMessage(txtApellidos, "^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,25}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,25})?$")) {
                    tilApellidos.setError("Ingresa un apellido correctamente");
                }
                else {
                    tilApellidos.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validate.IsValidTextboxMessage(txtNombre, "^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,25}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,25})?$")) {
                    tilNombre.setError("Ingresa un nombre correctamente");

                }
                else
                    tilNombre.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validate.IsValidTextboxMessage(txtEmail, "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$")) {
                    tilEmail.setError("Ingresa un correo electronico valido");
                }
                else {
                    tilEmail.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validate.IsValidTextboxMessage(txtUsuario, "^(?=.*[0-9])[0-9a-zA-Z]{8,15}$")) {
                    tilUsuario.setError("Ingresa un usuario valido (entre 8 y 16 caracteres incluidas letras y números)");
                }
                else {
                    tilUsuario.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtPassUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validate.IsValidTextboxMessage(txtPassUsuario, "^(?=.*\\d)(?=.*[\\u0021-\\u002b\\u003c-\\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{8,16}$")) {
                    tilPass.setError("Ingresar una contraseña valida(entre 8 y 16 caracteres, una mayuscula, numeros y un simbolo)");
                }
                else {
                    tilPass.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtPassConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //isPasswordEquals(txtPassConfirm, "Las contraseñas deben coincidir")

                if(isPasswordEquals(txtPassConfirm, "")) {
                    tilConfirmPass.setError("Las contraseñas deben coincidir");

                }
                else{
                    tilConfirmPass.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate.IsValidTextbox(txtApellidos, "^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,12}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,13})?$", "Debes ingresar un apellido") |
                        validate.IsValidTextbox(txtNombre, "^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,12}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,13})?$", "Debes ingresar un nombre") |
                        validate.IsValidTextbox(txtEmail, "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", "Debes ingresar un correo electronico") |
                        validate.IsValidTextbox(txtPassUsuario, "^(?=.*\\d)(?=.*[\\u0021-\\u002b\\u003c-\\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{8,16}$", "Debes ingresar una contraseña valida(entre 8 y 16 caracteres, una mayuscula,numeros y un simbolo)") |
                        isPasswordEquals(txtPassConfirm, "Debes confirmar la contraseña ingresada") |
                        validate.IsValidTextbox(txtUsuario, "^(?=.*[0-9])[0-9a-zA-Z]{8,15}$", "Debes ingresar un usuario que contengan letras y numeros(entre 8 y 16 caracteres ") |
                        isSelectedRole()) {
                    messages.messageToast(getContext(),"Debes llenar todo los campos correctamente");

                   } else {
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
                }
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
    public void message(Context context, String message, String title, boolean isMoveFrgment, final int fragment, final View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if(!isMoveFrgment){
            builder.setMessage(message)
                    .setIcon(android.R.drawable.ic_menu_save)
                    .setTitle(title)
                    .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }else{
            builder.setMessage(message)
                    .setIcon(android.R.drawable.ic_menu_save)
                    .setTitle(title)
                    .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            findNavController(v).navigate(fragment);
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
    private boolean isSelectedRole() {
        if(rolAdmin.isChecked()){
            rolAdmin.setError(null);
            data.setRole(rolAdmin.getText().toString());
            return false;
        }else if(rolEmpleado.isChecked()){
            data.setRole(rolEmpleado.getText().toString());
            rolEmpleado.setError(null);
            return  false;
        }else if(rolInvitado.isChecked()){
            data.setRole(rolInvitado.getText().toString());
            rolInvitado.setError(null);
            return false;
        }else{
            rolAdmin.setError("Debes seleccionar un rol para el usuario");
            return true;
        }
    }
    private boolean isPasswordEquals(EditText field, String messageError) {
        if(!field.getText().toString().equals(txtPassUsuario.getText().toString())){
            field.setError(messageError);
            return true;
        }else{
            return false;
        }
    }
    private void getvalues(View view) {
        txtApellidos= view.findViewById(R.id.txtApellidos);
        txtNombre= view.findViewById(R.id.txtNombre);
        txtEmail= view.findViewById(R.id.txtEmail);
        txtPassConfirm= view.findViewById(R.id.txtPassConfirm);
        txtPassUsuario= view.findViewById(R.id.txtPassUsuario);
        txtUsuario= view.findViewById(R.id.txtUsuario);
        rolAdmin= view.findViewById(R.id.rbAdmin);
        rolEmpleado= view.findViewById(R.id.rbEmpleado);
        rolInvitado= view.findViewById(R.id.rbInvitado);
        btnRegistrar=view.findViewById(R.id.btnregistrarUsuario);

        //TextInputLayout's
        tilApellidos=view.findViewById(R.id.textInputLayout7);
        tilNombre=view.findViewById(R.id.textInputLayout8);
        tilEmail=view.findViewById(R.id.textInputLayout9);
        tilUsuario=view.findViewById(R.id.textInputLayout10);
        tilPass=view.findViewById(R.id.textInputLayout4);
        tilConfirmPass=view.findViewById(R.id.textInputLayout);
    }



}