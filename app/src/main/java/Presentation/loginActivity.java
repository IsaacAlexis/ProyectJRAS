package Presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jras.R;

import java.sql.SQLException;

import Data.BDConnection;
import Data.Models.UsersModel;
import BusinessLogic.BusinessLog;

public class loginActivity extends AppCompatActivity {

    public EditText txtUser;
    public EditText txtPass;
    public boolean bUser=false,bPass=false;

    UsersModel ini = new UsersModel();
   /* public void ValidateEmptyFields(EditText field,boolean ){

    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BDConnection BDConnection = new BDConnection();

        Button btnIngresar = findViewById(R.id.btnIngresar);
        txtUser=findViewById(R.id.txtUsuarioLogin);
        txtPass=findViewById(R.id.txtPassLogin);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtUser.getText().length() == 0 || txtPass.getText().length() == 0){
                    Toast.makeText(loginActivity.this,"Ningun Campo puede estar vacio",Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        ini.setUser(txtUser.getText().toString());
                        ini.setPass(txtPass.getText().toString());

                        new BusinessLog().BridgeLogin(ini);

                        if (ini.getUserLoggedIn() == true){
                            Toast.makeText(loginActivity.this,"Bienvenido",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(loginActivity.this,menuActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(loginActivity.this,ini.getValidationMessage(),Toast.LENGTH_SHORT).show();
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