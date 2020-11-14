package Presentation;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jras.R;

import java.text.Normalizer;

import BusinessLogic.BusinessHomesRegister;
import Data.Models.HomesModel;
import Data.Utility.Validations;

public class fragmentRegistrarViviendas extends Fragment {

    //Variables
    private EditText txtCodBarras,txtPropietario,txtTelefonoViv;
    private EditText txtEmail,txtCalleViv,txtNumViv;
    private EditText txtCPViv,txtColViv,txtCiudad,txtEstadoViv;
    private Button btnRegistrar;
    private boolean flagEmptyFields = false;
    private boolean flagRegEx = false;

    //instancias de otras clases
    HomesModel home = new HomesModel();
    public Validations validate = new Validations();

    public fragmentRegistrarViviendas() {
        // Required empty public constructor
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_registrar_viviendas, container, false);
        //Variables conectadas con los id de los componentes
        txtCodBarras = view.findViewById(R.id.CodBarras);
        txtPropietario = view.findViewById(R.id.txtPropietario);
        txtTelefonoViv = view.findViewById(R.id.txtTelefonoViv);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtCalleViv = view.findViewById(R.id.CalleViv);
        txtNumViv = view.findViewById(R.id.NumViv);
        txtCPViv = view.findViewById(R.id.txtCPViv);
        txtColViv= view.findViewById(R.id.ColViv);
        txtCiudad= view.findViewById(R.id.CiudadViv);
        txtEstadoViv= view.findViewById(R.id.EstadoViv);
        btnRegistrar= view.findViewById(R.id.btnregistrarVivienda);
        txtCodBarras.setEnabled(false);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                Banderas();
                //Verificar banderas
                if (!flagEmptyFields && !flagRegEx){
                    String barcode = txtColViv.getText().toString().substring(0,3).toUpperCase()+"-"+txtCalleViv.getText().toString().substring(0,3).toUpperCase()+
                            "-"+txtNumViv.getText().toString().substring(0,3);
                    home.setBarCode(barcode);

                    new BusinessHomesRegister().BridgeHomeExist(home);

                    if (!home.isHouseExist()){

                        home.setBarCode(barcode);
                        home.setOwner(txtPropietario.getText().toString());
                        home.setPhoneNum(txtTelefonoViv.getText().toString());
                        home.setEmail(txtEmail.getText().toString());
                        home.setStreet(txtCalleViv.getText().toString());
                        home.setHouseHum(txtNumViv.getText().toString());
                        home.setZipCode(txtCPViv.getText().toString());
                        home.setColony(txtColViv.getText().toString());
                        home.setCity(txtCiudad.getText().toString());
                        home.setState(txtEstadoViv.getText().toString());
                        home.setHouseStatus("AGREGADA");
                        txtCodBarras.setText(barcode);

                        new BusinessHomesRegister().BridgeHomeRegister(home);

                        builder.setMessage("Vivienda "+txtCodBarras.getText().toString()+" registrada correctamente")
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

                    }//fin house exist
                    else {
                        Toast.makeText(getContext(),"La vivienda ya existe",Toast.LENGTH_SHORT).show();
                    }


                }//fin verificar banderas

            }// fin onClick btn
        });//fin onClickListener

        return view;
    }//fin onCreateView

    private void LimpiarCampos(){
        txtPropietario.setText("");
        txtTelefonoViv.setText("");
        txtCalleViv.setText("");
        txtNumViv.setText("");
        txtCPViv.setText("");
        txtColViv.setText("");
        txtCiudad.setText("");
        txtEstadoViv.setText("");
    }//fin limpiar Campos


    private void Banderas(){
        flagEmptyFields=false;
        flagRegEx=false;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if (txtPropietario.getText().length()<=2 || txtTelefonoViv.getText().length()<=2 ||
                txtCalleViv.getText().length()<=2 || txtNumViv.getText().length()<=2 || txtCPViv.getText().length()<=2 ||
                txtColViv.getText().length()<=2 || txtCiudad.getText().length()<=2 || txtEstadoViv.getText().length()<=2){
            //validate.IsTextboxEmpty(txtCodBarras,"Campo vacío");
            validate.IsTextboxEmpty(txtPropietario,"Campo vacío");
            validate.IsTextboxEmpty(txtTelefonoViv,"Campo vacío");
            validate.IsTextboxEmpty(txtCalleViv,"Campo vacío");
            validate.IsTextboxEmpty(txtNumViv,"Campo vacío");
            validate.IsTextboxEmpty(txtCPViv,"Campo vacío");
            validate.IsTextboxEmpty(txtColViv,"Campo vacío");
            validate.IsTextboxEmpty(txtCiudad,"Campo vacío");
            validate.IsTextboxEmpty(txtEstadoViv,"Campo vacío");
            Toast.makeText(getContext(),"Llene todos los campos",Toast.LENGTH_SHORT).show();
            flagEmptyFields=true;
        }//fin if campos vacios
        else{
            if (txtEmail.getText().length()!=0){
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
                }
            }//fin if valida correo
        }//fin else campos vacios

    }//fin Banderas

}//fin class fragment