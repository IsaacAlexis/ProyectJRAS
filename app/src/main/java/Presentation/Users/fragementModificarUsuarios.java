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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jras.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import BusinessLogic.BusinessUser;
import Data.Models.UsersModel;
import Data.Utility.Messages;
import Data.Utility.RegExValidations;
import Data.Utility.Validations;

import static androidx.navigation.Navigation.findNavController;

public class fragementModificarUsuarios extends Fragment {
    //Comunes
    private EditText lastname,firstname,email,username;
    private Button save;
    private boolean edit;
    String currentDateandTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    //TextInputLayout
    private TextInputLayout tilLastName,tilFirstName,tilEmail,tilUsername;

    //Spinner*******
    private Spinner spinnerRole,spinnerStatus;
    private String spnSeleccionRole,spnSelectionStatus;
    String[] optionsRoles = {"Administrador","Empleado"};
    String[] optionsStatus = {"Activa","Inactiva"};


    //Instancias de otras clases
    UsersModel users=new UsersModel();
    Validations validations=new Validations();
    Messages  messages=new Messages();
    RegExValidations regEx = new RegExValidations();


    public fragementModificarUsuarios() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_modificar_usuarios, container, false);
        getvalues(view);
        validateFields();
        fieldsavailability(true);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edit){
                    fieldsavailability(false);
                    save.setText("Guardar");
                    edit=true;
                }else{
                    textboxEmpty();

                    if (!validations.isInvalid){
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


    //****************************************************************Metodos***********************************************************

    public void getvalues(View view){
        lastname=view.findViewById(R.id.txtApellidosM);
        firstname=view.findViewById(R.id.txtNombreM);
        email=view.findViewById(R.id.txtEmailM);
        username=view.findViewById(R.id.txtUsuarioM);
        save=view.findViewById(R.id.btnConfirmarUsuario);
        spinnerStatus = view.findViewById(R.id.spinnerStatusEditUser);
        spinnerRole = view.findViewById(R.id.spinnerRolEditUser);
        spinnerStatus.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinner_item_jraspinners, optionsStatus));
        spinnerRole.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinner_item_jraspinners, optionsRoles));

        //TextInputLayout
        tilLastName = view.findViewById(R.id.textInputLayout11);
        tilFirstName= view.findViewById(R.id.textInputLayout12);
        tilEmail= view.findViewById(R.id.textInputLayout14);
        tilUsername= view.findViewById(R.id.textInputLayout15);

        assignValues();
    }//fin de  getvalues(View view)

    public void assignValues(){
        if (users.getModifyStatus().equals("ACTIVO")||users.getModifyStatus().equals("ACTIVA")){
            selectedStatus();
            spinnerStatus.setSelection(0);
        }else{
            selectedStatus();
            spinnerStatus.setSelection(1);
        }
        if(users.getModifyRole().equals("Administrador")||users.getModifyRole().equals("Admin")){
            selectedRole();
            spinnerRole.setSelection(0);
        }
        else{
            selectedRole();
            spinnerRole.setSelection(1);
        }
        firstname.setText(users.getModifyFirstName());
        lastname.setText(users.getModifyLastName());
        email.setText(users.getModifyEmail());
        username.setText(users.getModifyUsername());

    }//fin de assignValues()

    private void selectedRole() {
        spinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spnSeleccionRole = spinnerRole.getSelectedItem().toString();

                switch (spnSeleccionRole){
                    case "Activo":
                    case "Inactivo":
                        users.setRole(spnSeleccionRole);
                        save.setEnabled(true);
                        save.setBackgroundResource(R.drawable.bordes_redondos_azul);
                        validations.isInvalid = false;
                        break;
                }//fin de switch
            }//fin onItemSelected Spinner

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });//fin setOnItemSelectedListener SpinnerRole
    }//fin de selectedRole()

    private void selectedStatus(){
        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spnSelectionStatus = spinnerStatus.getSelectedItem().toString();

                switch (spnSelectionStatus){
                    case "Activa":
                    case "Inactiva":
                        users.setUserStatus(spnSelectionStatus.toUpperCase());
                        save.setEnabled(true);
                        save.setBackgroundResource(R.drawable.bordes_redondos_azul);
                        validations.isInvalid = false;
                        break;
                }//fin del switch
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//fin setOnItemSelectedListener SpinnerStatus
    }//fin de selectedStatus()

    public void fieldsavailability(boolean isAvailable){
        if(!isAvailable){
            firstname.setEnabled(true);
            lastname.setEnabled(true);
            email.setEnabled(true);
            username.setEnabled(true);
            spinnerRole.setEnabled(true);
            spinnerStatus.setEnabled(true);

        }else{
            firstname.setEnabled(false);
            lastname.setEnabled(false);
            email.setEnabled(false);
            username.setEnabled(false);
            spinnerRole.setEnabled(false);
            spinnerStatus.setEnabled(false);

        }
    }

    public void setValues(){
        users.setRole(spnSeleccionRole);
        users.setUserStatus(spnSelectionStatus.toUpperCase());
        users.setIdUser(users.getModifyIdUser());
        users.setFirstName(firstname.getText().toString());
        users.setLastName(lastname.getText().toString());
        users.setEmail(email.getText().toString());
        users.setUserName(username.getText().toString());
        users.setExpirationDate(users.getCurrentExpirationDate());
        users.setColony(users.getCurrentColony());
        users.setModDate(currentDateandTime);

    }//fin de setValues()

    public void textboxEmpty(){
        if (lastname.getText().length()==0 || firstname.getText().length()==0 || email.getText().length()==0 || username.getText().length()==0){
            messages.messageToast(getContext(),"Debes llenar todos los campos correctamente");
            validations.isInvalid=true;
        }
        else{
            validations.isInvalid=false;
        }
    }//fin de textboxEmpty()

    public void validateFields(){
        validations.IsValidTextboxOnClick(lastname,tilLastName,regEx.validNameLstName, "Ingresa un apellido correctamente",save);
        validations.IsValidTextboxOnClick(firstname,tilFirstName,regEx.validNameLstName, "Ingresa un nombre correctamente",save);
        validations.IsValidTextboxOnClick(email,tilEmail,regEx.validEmail,"Ingresa un correo electronico valido",save);
        validations.IsValidTextboxOnClick(username,tilUsername,regEx.validUser,"Ingresa un usuario valido (entre 8 y 16 caracteres incluidas letras y números)",save);
    }//fin de validateFields()
}