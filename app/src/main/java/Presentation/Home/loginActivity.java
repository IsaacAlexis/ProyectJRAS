package Presentation.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.service.autofill.RegexValidator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jras.R;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.SQLException;

import BusinessLogic.BusinessUser;
import Data.Models.UsersModel;
import Data.Utility.LoadingDialog;
import Data.Utility.Messages;
import Data.Utility.RegExValidations;
import Data.Utility.Validations;
import Presentation.Users.fragmentRegistroUsuarios;

public class loginActivity extends AppCompatActivity {

    //Comunes
    public EditText txtUser;
    public EditText txtPass;
    private TextInputLayout tilUser,tilPassword;
    private Button btnUserLogIn;
    public int attemptsallowed=4;

    //Instncias de otras clases
    UsersModel ini = new UsersModel();
    Validations validations=new Validations();
    RegExValidations regEx = new RegExValidations();
    Messages messages = new Messages();
    final LoadingDialog loadingDialog = new LoadingDialog(loginActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getCommonValues();

        btnUserLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.startLoadingDialogActivity();
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (validations.IsValidTextboxMessage(txtUser,regEx.validUser) || validations.IsValidTextboxMessage(txtPass,regEx.validPassword)){
                            messages.messageToast(loginActivity.this,"Los datos ingresados son incorrectos");
                            loadingDialog.dismissDialog();
                        }
                        else{
                            try {

                                ini.setUserName(txtUser.getText().toString().trim());
                                ini.setPassword(txtPass.getText().toString().trim());

                                new BusinessUser().BridgeLogin(ini);

                                if (ini.isUserLoggedIn() == true){
                                    tilUser.setError(null);
                                    tilPassword.setError(null);
                                    loadingDialog.dismissDialog();
                                    messages.messageToastShort(loginActivity.this,"Bienvenido(a) "+txtUser.getText());
                                    Intent intent = new Intent(loginActivity.this, menuActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    if(ini.getFlagUser()==false){
                                        new BusinessUser().BridgeUserExist(ini);
                                        if(ini.isUserLoggedIn()){
                                            attemptsallowed--;
                                            messages.messageToastShort(loginActivity.this,"Contraseña incorrecta");
                                            if(attemptsallowed>0){
                                                messages.messageToastShort(loginActivity.this,"Tienes solo: "+attemptsallowed+" intentos");
                                            }
                                            tilPassword.setError("Contraseña invalida");
                                            tilUser.setError(null);
                                            txtPass.setFocusable(true);
                                            txtUser.setEnabled(false);

                                            if(attemptsallowed==0){
                                                new BusinessUser().BridgeBlockLogin(ini);
                                                if(ini.isUserLoggedIn()){
                                                    messages.messageToastShort(loginActivity.this,ini.getValidationMessage());
                                                    txtUser.setEnabled(true);
                                                    txtUser.setText("");
                                                    txtPass.setText("");
                                                    txtPass.setError(null);
                                                    attemptsallowed=4;
                                                }
                                            }
                                        }else{
                                            messages.messageToast(loginActivity.this,"Usuario y/o Contraseña es incorrecto");
                                            tilPassword.setError("Contraseña invalida");
                                            tilUser.setError("Usuario invalido");

                                        }
                                    }else{
                                        messages.messageToastShort(loginActivity.this,ini.getValidationMessage());
                                        tilPassword.setError("Contraseña invalida");
                                        tilUser.setError("Usuario invalido");
                                        txtUser.setEnabled(true);
                                        attemptsallowed=4;
                                    }
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        loadingDialog.dismissDialog();
                    }
                },500);
            }
        });//fin de btnUserLogin onClickListener

    }//fin de onCreate

    // ******************************Metodos******************************

    private void getCommonValues(){
        btnUserLogIn = findViewById(R.id.btnIngresar);
        txtUser=findViewById(R.id.txtUsuarioLogin);
        txtPass=findViewById(R.id.txtPassLogin);
        tilPassword=findViewById(R.id.textInputLayout4);
        tilUser=findViewById(R.id.textInputLayout3);

        validations.IsValidTextboxOnClick(txtUser,tilUser,regEx.validUser,"Ingresa un usuario valido",btnUserLogIn);
        validations.IsValidTextboxOnClick(txtPass,tilPassword,regEx.validPassword,"Ingresa una contraseña valida",btnUserLogIn);
    }//fin de getCommonValues()

}