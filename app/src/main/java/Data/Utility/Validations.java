package Data.Utility;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jras.R;
import com.google.android.material.textfield.TextInputLayout;

public class Validations {
   public boolean isInvalid = false;
    public boolean IsTextboxEmpty(EditText field, String errorMessage){
        if(field.getText().toString().trim().equals("")){
            field.setError(errorMessage);


            return true;
        }else {
            return false;
        }

    }

    public boolean IsValidTextbox(EditText field, String parameterstovalidate,String errorMessage){
        if(!field.getText().toString().matches(parameterstovalidate)){
            field.setError(errorMessage);
            return true;
        } else{
            return false;
        }
    }

    public boolean IsValidTextboxMessage(EditText field, String parameterstovalidate){
        if(!field.getText().toString().matches(parameterstovalidate)){
            return true;
        }
        else
            return false;
    }

    public void IsValidTextboxOnClick(EditText field, TextInputLayout til, String parameterstovalidate, String errorMessage, Button btn){
        field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!field.getText().toString().matches(parameterstovalidate)){
                    til.setError(errorMessage);
                    btn.setEnabled(false);
                    btn.setBackgroundResource(R.drawable.boton_desabilitado);
                }
                else{
                    til.setErrorEnabled(false);
                    btn.setEnabled(true);
                    btn.setBackgroundResource(R.drawable.bordes_redondos_rojo);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
