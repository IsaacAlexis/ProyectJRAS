package Presentation.Users;

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
import android.widget.TextView;

import com.example.jras.R;

import java.util.Date;

import BusinessLogic.BusinessUser;
import Data.Models.UsersModel;
import Data.Utility.Messages;
import Data.Utility.Validations;

import static androidx.navigation.Navigation.findNavController;

public class fragementModificarUsuarios extends Fragment {
    EditText lastname,firstname,email,username,status;
    TextView tag;
    RadioButton roleAdmin,roleEmployer,roleInvited;
    Button save;
    private boolean edit;
    UsersModel users=new UsersModel();
    Validations validations=new Validations();
    Messages  messages=new Messages();
    String currentDateandTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    public fragementModificarUsuarios() {
        // Required empty public constructor
    }
    public void getvalues(View view){
        lastname=view.findViewById(R.id.txtApellidosM);
        firstname=view.findViewById(R.id.txtNombreM);
        email=view.findViewById(R.id.txtEmailM);
        username=view.findViewById(R.id.txtUsuarioM);
        status=view.findViewById(R.id.txtStatusm);
        tag=view.findViewById(R.id.lbRolm);
        roleAdmin =view.findViewById(R.id.rbAdminM);
        roleEmployer =view.findViewById(R.id.rbEmpleadoM);
        roleInvited =view.findViewById(R.id.rbInvitadoM);
        save=view.findViewById(R.id.btnConfirmarUsuario);
        assignValues();
    }
    public void assignValues(){
        firstname.setText(users.getModifyFirstName());
        lastname.setText(users.getModifyLastName());
        email.setText(users.getModifyEmail());
        username.setText(users.getModifyUsername());
        status.setText(users.getModifyStatus());
        if(users.getModifyRole().toUpperCase().equals("ADMIN")){
           roleAdmin.setChecked(true);
        }else if(users.getModifyRole().toUpperCase().equals("EMPLEADO")){
            roleEmployer.setChecked(true);
        }else if(users.getModifyRole().toUpperCase().equals("INVITADO")){
            roleInvited.setChecked(true);
            }
    }



    public void fieldsavailability(boolean isAvailable){
        if(!isAvailable){
           firstname.setEnabled(true);
           lastname.setEnabled(true);
           email.setEnabled(true);
           username.setEnabled(true);
           status.setEnabled(true);
           tag.setEnabled(true);
           roleAdmin.setEnabled(true);
           roleEmployer.setEnabled(true);
           roleInvited.setEnabled(true);

        }else{
            firstname.setEnabled(false);
            lastname.setEnabled(false);
            email.setEnabled(false);
            username.setEnabled(false);
            status.setEnabled(false);
            tag.setEnabled(false);
            roleAdmin.setEnabled(false);
            roleEmployer.setEnabled(false);
            roleInvited.setEnabled(false);

        }
    }
    public void setValues(){
        users.setIdUser(users.getModifyIdUser());
        users.setFirstName(firstname.getText().toString());
        users.setLastName(lastname.getText().toString());
        users.setEmail(email.getText().toString());
        users.setUserName(username.getText().toString());
        users.setUserStatus(status.getText().toString());
        if(roleAdmin.isChecked()){
            users.setRole(roleAdmin.getText().toString());
        }
        if(roleEmployer.isChecked()){
            users.setRole(roleEmployer.getText().toString());

        }
        if(roleInvited.isChecked()){
            users.setRole(roleInvited.getText().toString());

        }
        users.setExpirationDate(users.getCurrentExpirationDate());
        users.setColony(users.getCurrentColony());
        users.setModDate(currentDateandTime);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragement_modificar_usuarios, container, false);
        getvalues(view);
        fieldsavailability(true);

        lastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validations.IsValidTextbox(lastname, "^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,12}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,13})?$",
                        "Debes ingresar un apellido");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        firstname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validations.IsValidTextbox(firstname, "^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,12}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,13})?$",
                        "Debes ingresar un nombre");
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
                validations.IsValidTextbox(email, "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$",
                        "Debes ingresar un correo electronico");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validations.IsValidTextbox(username, "^(?=.*[0-9])[0-9a-zA-Z]{8,15}$",
                        "Debes ingresar un usuario que contengan letras y numeros(entre 8 y 16 caracteres ");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edit){
                    fieldsavailability(false);
                    save.setText("Guardar");
                    edit=true;
                }else{
                    if (validations.IsValidTextbox(lastname, "^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,12}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,13})?$",
                            "Debes ingresar un apellido") |
                            validations.IsValidTextbox(firstname, "^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,12}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,13})?$",
                                    "Debes ingresar un nombre") |
                            validations.IsValidTextbox(email, "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$",
                                    "Debes ingresar un correo electronico") |
                            validations.IsValidTextbox(username, "^(?=.*[0-9])[0-9a-zA-Z]{8,15}$",
                                    "Debes ingresar un usuario que contengan letras y numeros(entre 8 y 16 caracteres ")){
                        messages.messageToast(getContext(),"Debes llenar todo los campos correctamente");

                    }else{
                        setValues();
                        new BusinessUser().BridgeUserUpdate(users);
                        if(!users.isRegisterUser()){
                            messages.messageAlert(getContext(),users.getValidationMessage(),"Los cambios se realizarion exito",view,R.id.fragmentUsuarios);
                        }else{
                            if(users.getUserExist()){
                                messages.messageToast(getContext(),users.getValidationMessage());
                            }else{
                                messages.messageToast(getContext(),users.getValidationMessage());
                                findNavController(view).navigate(R.id.fragmentUsuarios);
                            }
                        }
                    }
                }
            }
        });
        return view;
    }
}