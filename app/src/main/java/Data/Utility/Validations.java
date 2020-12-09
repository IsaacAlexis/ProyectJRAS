package Data.Utility;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class Validations {
   public boolean isInvalid;
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
        if(!field.getText().toString().matches(parameterstovalidate))
            return true;
        else
            return false;
    }

    public void IsValidTextboxOnClick(EditText field, TextInputLayout til, String parameterstovalidate, String errorMessage, Button btn){
        field.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(!field.getText().toString().matches(parameterstovalidate)){
                    til.setError(errorMessage);
                    btn.setEnabled(false);
                    return true;
                }
                else{
                    til.setErrorEnabled(false);
                    btn.setEnabled(true);
                    return false;
                }
            }
        });
    }
}
