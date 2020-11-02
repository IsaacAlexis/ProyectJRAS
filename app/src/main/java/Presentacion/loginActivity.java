package Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jras.R;

import java.sql.SQLException;

import Datos.ConexionBD;
import Datos.InicioSesionesGETSET;
import Negocios.NegocioSesiones;

public class loginActivity extends AppCompatActivity {

    public EditText txtUser;
    public EditText txtPass;

    InicioSesionesGETSET ini = new InicioSesionesGETSET();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ConexionBD conexionBD = new ConexionBD();

        Button btnIngresar = findViewById(R.id.btnIngresar);
        txtUser=findViewById(R.id.txtUsuario);
        txtPass=findViewById(R.id.txtPassUsuarioL);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtUser.getText().length() == 0 || txtPass.getText().length() == 0){
                    Toast.makeText(loginActivity.this,"Ningun Campo puede estar vacio",Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        ini.setUsuario(txtUser.getText().toString());
                        ini.setContraseña(txtPass.getText().toString());

                        new NegocioSesiones().PuenteInicioSesion(ini);

                        if (ini.isStatus() == true){
                            Toast.makeText(loginActivity.this,"Bienvenido",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(loginActivity.this,menuActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(loginActivity.this,"Usuario y/o Contraseña incorrectos",Toast.LENGTH_SHORT).show();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }





//    public void onClickIngresar(View view ) {
//
//
//        if(txtUser.getText().toString().equals("Brayan")) {
//            if (txtPass.getText().toString().equals("12345")) {
//                Intent intent = new Intent(this, menuActivity.class);
//                startActivity(intent);
//                finish();
//
//            } else {
//                Snackbar.make(view, "Contraseña Incorrecta", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//            }
//        }
//        else{
//
//            Snackbar.make(view, "Usuario Incorrecto", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
//
//        }
//
//    }
}