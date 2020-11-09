package Data.Utility;

import android.widget.EditText;

public class Validations {
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
        }else {
            return false;
        }

    }
}
