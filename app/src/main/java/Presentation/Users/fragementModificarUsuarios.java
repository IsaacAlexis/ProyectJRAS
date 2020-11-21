package Presentation.Users;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jras.R;

import Data.Models.UsersModel;

import static androidx.navigation.Navigation.findNavController;

public class fragementModificarUsuarios extends Fragment {

    EditText lastname,firstname,email,username,status;
    TextView tag;
    RadioButton roleAdmin,roleEmployer,roleInvited;
    Button save;
    private boolean edit;
    UsersModel users=new UsersModel();
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
        setvalues();
    }
    public void setvalues(){
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragement_modificar_usuarios, container, false);
        getvalues(view);
        fieldsavailability(true);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edit){
                    fieldsavailability(false);
                    save.setText("Guardar");
                    edit=true;
                }else{
                    Toast.makeText(getContext(),"Se guardaron correctamente los cambios",Toast.LENGTH_LONG).show();
                    findNavController(view).navigate(R.id.fragmentUsuarios);
                }
            }
        });
        return view;
    }
}