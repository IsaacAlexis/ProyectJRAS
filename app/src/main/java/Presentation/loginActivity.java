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
import Data.Utility.Validations;
import Data.BDConnection;
import Data.Models.UsersModel;
import BusinessLogic.BusinessLog;

public class loginActivity extends AppCompatActivity {

    public EditText txtUser;
    public EditText txtPass;
    public int attemptsallowed=4;

    UsersModel ini = new UsersModel();
    Validations validations=new Validations();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BDConnection BDConnection = new BDConnection();

        Button btnUserLogIn = findViewById(R.id.btnIngresar);
        txtUser=findViewById(R.id.txtUsuarioLogin);
        txtPass=findViewById(R.id.txtPassLogin);


        btnUserLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validations.IsValidTextbox(txtUser,"^[0-9a-zA-Z]+$","Usuario invalido")|
                        validations.IsTextboxEmpty(txtPass,"Contraseña invalida")){
                    Toast.makeText(loginActivity.this,"Los datos ingresados son incorrectos",Toast.LENGTH_LONG).show();
                }
                else{
                    try {
                            ini.setUser(txtUser.getText().toString().trim());
                            ini.setPass(txtPass.getText().toString().trim());

                            new BusinessLog().BridgeLogin(ini);

                            if (ini.getUserLoggedIn() == true){
                                txtUser.setError(null);
                                txtPass.setError(null);
                                Toast.makeText(loginActivity.this,"Bienvenido",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(loginActivity.this,menuActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                if(ini.getFlagUser()==false){
                                    new BusinessLog().BridgeUserExist(ini);
                                    if(ini.getUserLoggedIn()){
                                        attemptsallowed--;
                                        Toast.makeText(loginActivity.this,ini.getValidationMessage(),Toast.LENGTH_SHORT).show();
                                        if(attemptsallowed>0){ Toast.makeText(loginActivity.this,"Tienes solo: "+attemptsallowed+" intentos",
                                                Toast.LENGTH_SHORT).show();}
                                        txtPass.setError("Contraseña invalida");
                                        txtUser.setError(null);
                                        txtPass.setFocusable(true);
                                        txtUser.setEnabled(false);

                                        if(attemptsallowed==0){
                                            new BusinessLog().BridgeBlockLogin(ini);
                                            if(ini.getUserLoggedIn()){
                                                Toast.makeText(loginActivity.this,ini.getValidationMessage(),Toast.LENGTH_SHORT).show();
                                                txtUser.setEnabled(true);
                                                txtUser.setText("");
                                                txtPass.setText("");
                                                txtPass.setError(null);
                                                attemptsallowed=4;
                                            }

                                        }
                                    }else{
                                        Toast.makeText(loginActivity.this,"Usuario y/o Contraseña es incorrecto",Toast.LENGTH_SHORT).show();
                                        txtPass.setError("Contraseña invalida");
                                        txtUser.setError("Usuario invalido");

                                    }
                                }else{
                                    Toast.makeText(loginActivity.this,ini.getValidationMessage(),Toast.LENGTH_SHORT).show();
                                    txtPass.setError("Contraseña invalida");
                                    txtUser.setError("Usuario invalido");
                                    txtUser.setEnabled(true);
                                    attemptsallowed=4;
                                }




                            }


                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }






}