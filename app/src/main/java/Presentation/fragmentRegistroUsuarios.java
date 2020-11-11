package Presentation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.jras.R;

import java.util.Date;

import BusinessLogic.BusinessUserRegister;
import Data.Models.UsersModel;

public class fragmentRegistroUsuarios extends Fragment {


    //Variables
    public EditText txtApellidos,txtNombre,txtEmail;
    public EditText txtUsuario,txtPassUsuario,txtPassConfirm;
    public RadioButton rolAdmin,rolEmpleado,rolInvitado;
    public Date dat= new Date();
    public Button btnRegistrar;
    UsersModel data = new UsersModel();
    BusinessUserRegister busi;



    public fragmentRegistroUsuarios() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_registro_usuarios, container, false);



        txtApellidos= view.findViewById(R.id.txtApellidos);
        txtNombre= view.findViewById(R.id.txtNombre);
        txtEmail= view.findViewById(R.id.txtEmail);
        txtPassConfirm= view.findViewById(R.id.txtPassConfirm);
        txtPassUsuario= view.findViewById(R.id.txtPassUsuario);
        txtUsuario= view.findViewById(R.id.txtUsuario);
        rolAdmin= view.findViewById(R.id.rbAdmin);
        rolEmpleado= view.findViewById(R.id.rbEmpleado);
        rolInvitado= view.findViewById(R.id.rbInvitado);

        btnRegistrar= view.findViewById(R.id.btnregistrarUsuario);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if para validar que los campos no esten vacios
                if (txtApellidos.getText().length()!=0||txtNombre.getText().length()!=0){
                    //if para admin, empleado, invitado
                    if(rolAdmin.isChecked()){
                        //if para confirmar la contraseña
                        if(txtPassUsuario.getText().toString().equals(txtPassConfirm.getText().toString())){
                            data.setFirstName(txtNombre.getText().toString());
                            data.setLastName(txtApellidos.getText().toString());
                            data.setEmail(txtEmail.getText().toString());
                            data.setUser(txtUsuario.getText().toString());
                            data.setColony("Tec");
                            data.setUserStatus("ACTIVO");
                            data.setExpDate("2020/12/07");
                            data.setAddeddDate("2020/11/10");
                            data.setModifiedDate("2020/11/10");
                            data.setPass(txtPassConfirm.getText().toString());
                            data.setRole("Admin");

                            busi = new BusinessUserRegister();
                            busi.BridgeUserRegister(data);

                            Toast.makeText(getContext(),"Usuario Registrado",Toast.LENGTH_SHORT).show();
                        }//if contraseña rol admin
                        else{
                            Toast.makeText(getContext(),"Verificacion de contraseña incorrecta",Toast.LENGTH_SHORT).show();
                        }//else contraseña rol admin
                    }//if roladmin
                    else{
                        if (rolEmpleado.isChecked()){
                            if(txtPassUsuario.getText()==txtPassConfirm.getText()){
                                data.setFirstName(txtNombre.getText().toString());
                                data.setLastName(txtApellidos.getText().toString());
                                data.setEmail(txtEmail.getText().toString());
                                data.setUser(txtUsuario.getText().toString());
                                data.setColony("Tec");
                                data.setUserStatus("ACTIVO");
                                data.setExpDate("2020/12/07");
                                data.setAddeddDate(String.valueOf(dat.getTime()));
                                data.setModifiedDate(String.valueOf(dat.getTime()));
                                data.setPass(txtPassConfirm.getText().toString());
                                data.setRole("Empleado");
                                Toast.makeText(getContext(),"Usuario Registrado",Toast.LENGTH_SHORT).show();
                            }//if contraseña rol empleado
                            else{
                                Toast.makeText(getContext(),"Verificacion de contraseña incorrecta",Toast.LENGTH_SHORT).show();
                            }//else contraseña rol empleado
                        }//if rol Empleado
                        else{
                            if(txtPassUsuario.getText()==txtPassConfirm.getText()){
                                data.setFirstName(txtNombre.getText().toString());
                                data.setLastName(txtApellidos.getText().toString());
                                data.setEmail(txtEmail.getText().toString());
                                data.setUser(txtUsuario.getText().toString());
                                data.setColony("Tec");
                                data.setUserStatus("ACTIVO");
                                data.setExpDate("2020/12/07");
                                data.setAddeddDate(String.valueOf(dat.getTime()));
                                data.setModifiedDate(String.valueOf(dat.getTime()));
                                data.setPass(txtPassConfirm.getText().toString());
                                data.setRole("Invitado");


                                Toast.makeText(getContext(),"Usuario Registrado",Toast.LENGTH_SHORT).show();
                            }//if contraseña rol invitado
                            else{
                                Toast.makeText(getContext(),"Verificacion de contraseña incorrecta",Toast.LENGTH_SHORT).show();
                            }//else contraseña rol invitado
                        }//else rol empleado
                    }
                }//if datos
                else{
                    Toast.makeText(getContext(),"Llene todos los campos!!",Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
}