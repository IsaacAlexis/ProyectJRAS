package Presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.DrawableRes;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import BusinessLogic.BusinessUserRegister;
import Data.Models.UsersModel;
import Data.Utility.Validations;

public class fragmentRegistroUsuarios extends Fragment {


    //Variables
    private EditText txtApellidos,txtNombre,txtEmail;
    private EditText txtUsuario,txtPassUsuario,txtPassConfirm;
    private RadioButton rolAdmin,rolEmpleado,rolInvitado;
    private Date dat= (Date) Calendar.getInstance().getTime();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/ddHH:mm:ss");
    private Button btnRegistrar;
    private TextView tvDate;


    //Banderas
    private boolean flagEmptyField = false;
    private boolean flagRegEx = false;
    private boolean flagPasswords = false;

    //instancias de otras clases
    UsersModel data = new UsersModel();
    Validations validate = new Validations();

    public fragmentRegistroUsuarios() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_registro_usuarios, container, false);

        //Variables conectadas con los id de los componentes
        txtApellidos= view.findViewById(R.id.txtApellidos);
        txtNombre= view.findViewById(R.id.txtNombre);
        txtEmail= view.findViewById(R.id.txtEmail);
        txtPassConfirm= view.findViewById(R.id.txtPassConfirm);
        txtPassUsuario= view.findViewById(R.id.txtPassUsuario);
        txtUsuario= view.findViewById(R.id.txtUsuario);
        rolAdmin= view.findViewById(R.id.rbAdmin);
        rolEmpleado= view.findViewById(R.id.rbEmpleado);
        rolInvitado= view.findViewById(R.id.rbInvitado);
        //tvDate=view.findViewById(R.id.tvDate);

        btnRegistrar= view.findViewById(R.id.btnregistrarUsuario);

        //Para que el radio button de rol Empleado siempre este activo por defecto
        rolEmpleado.setChecked(true);
        //tvDate.setText(sdf.format(dat));

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                Banderas();

                //verificar banderas true
                if (!flagEmptyField && !flagRegEx){
                    //verificar si las contraseñas coinciden
                    if (txtPassConfirm.getText().toString().equals(txtPassUsuario.getText().toString())){

                        data.setUser(txtUsuario.getText().toString());
                        new BusinessUserRegister().BridgeUserExist(data);

                        //verificar si el usuario existe
                        if (!data.getUserExist()){

                            //validar el rol del usuario
                            if (rolAdmin.isChecked())
                                data.setRole("Admin");
                            if (rolEmpleado.isChecked())
                                data.setRole("Empleado");
                            if (rolInvitado.isChecked())
                                data.setRole("Invitado");

                            data.setFirstName(txtNombre.getText().toString());
                            data.setLastName(txtApellidos.getText().toString());
                            data.setEmail(txtEmail.getText().toString());
                            data.setUser(txtUsuario.getText().toString());
                            data.setColony("Tec");
                            data.setUserStatus("ACTIVO");
                            data.setExpDate("2020/12/07");
                            //data.setAddeddDate(sdf.format(dat));
                            //data.setModifiedDate(sdf.format(dat));
                            data.setPass(txtPassConfirm.getText().toString());

                            new BusinessUserRegister().BridgeUserRegister(data);

                            builder.setMessage("Usuario "+txtUsuario.getText().toString()+" registrado correctamente")
                                    .setIcon(android.R.drawable.ic_menu_save)
                                    .setTitle("Registro Satisfactorio")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                            AlertDialog dialog = builder.create();
                            dialog.show();

                            LimpiarCampos();
                            txtApellidos.setFocusable(true);

                        }//fin validar si existe usuario
                        else{
                            Toast.makeText(getContext(),"El usuario ya existe",Toast.LENGTH_SHORT).show();
                        }//fin else usuario ya existe
                    }//fin if contraseñas coinciden
                    else {
                        Toast.makeText(getContext(),"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
                        txtPassConfirm.setFocusable(true);
                    }//fin else las contraseñas no coinciden

                }
            }//fin onClick btnRegistrar
        });
        return view;
    }

    public void LimpiarCampos(){
        txtApellidos.setText("");
        txtNombre.setText("");
        txtEmail.setText("");
        txtUsuario.setText("");
        txtPassUsuario.setText("");
        txtPassConfirm.setText("");
    }//fin metodo Limpiar Campos

    public void Banderas(){

        flagEmptyField=false;
        flagRegEx=false;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //validar que los campos no esten vacios
        if (validate.IsTextboxEmpty(txtApellidos,"Campo vacío")||
                validate.IsTextboxEmpty(txtEmail,"Campo vacío")||
                validate.IsTextboxEmpty(txtUsuario,"Campo vacío")||
                validate.IsTextboxEmpty(txtPassUsuario,"Campo vacío")||
                validate.IsTextboxEmpty(txtNombre,"Campo vacío")||
                validate.IsTextboxEmpty(txtPassConfirm,"Campo vacío")){

            Toast.makeText(getContext(),"Llene todos los campos!!",Toast.LENGTH_SHORT).show();
            flagEmptyField=true;
        }//fin if campos vacios

        else{
            if (validate.IsValidTextbox(txtEmail,"^[^@]+@[^@]+\\.[a-zA-Z]{2,}$","Introduzca un correo valido")){
                builder.setMessage("Introduzca un correo valido")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Atención")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                flagRegEx=true;
            }//fin if validar correo

            else{
                if(validate.IsValidTextbox(txtPassUsuario,"^(?=.*\\d)(?=.*[\\u0021-\\u002b\\u003c-\\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{8,16}$","Contraseña Invalida")){
                    //Toast.makeText(getContext(),"Verifique los campos",Toast.LENGTH_SHORT).show();
                    builder.setMessage("La contraseña debe incluir entre 8 y 16 caracteres con una letra mayuscula, una minuscula y un simbolo...")
                            .setTitle("Atención")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    flagRegEx=true;
                }//fin if validar contraseña
                //else if(txtPassUsuario.getText().toString()==txtPassConfirm.getText().toString())
            }//fin else validar correo
        }//fin else validar correos
    }//fin metodo Banderas

}