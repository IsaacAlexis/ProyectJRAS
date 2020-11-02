package com.example.jras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class loginActivity extends AppCompatActivity {

    public EditText txtUser;
    public EditText txtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUser=findViewById(R.id.txtNoContrato);
        txtPass=findViewById(R.id.txtPassUsuarioL);
    }
    public void onClickIngresar(View view ) {


        if(txtUser.getText().toString().equals("Brayan")) {
            if (txtPass.getText().toString().equals("12345")) {
                Intent intent = new Intent(this, menuActivity.class);
                startActivity(intent);
                finish();

            } else {
                Snackbar.make(view, "Contraseña Incorrecta", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        }
        else{

            Snackbar.make(view, "Usuario Incorrecto", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }

    }
}