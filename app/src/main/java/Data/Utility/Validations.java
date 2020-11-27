package Data.Utility;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

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
    public void IsValidTextboxOnClick(EditText field, String parameterstovalidate,String errorMessage){
        field.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(!field.getText().toString().matches(parameterstovalidate)){
                    field.setError(errorMessage);
                }
                return false;

            }
        });


    }






}
