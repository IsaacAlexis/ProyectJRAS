package Data.Utility;

public class RegExValidations {

    public String validEmail = "^[A-Z_a-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(.[A-Za-z]{2,4})$";
    public String validNamesComplete = "^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})?(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})?$";
    public String validCityState = "^[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?$";
    public String validNameLstName = "^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,25}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,25})?$";
    public String validUser = "^(?=.*[0-9])[0-9a-zA-Z]{8,15}$";
    public String validPassword = "^(?=.*\\d)(?=.*[\\u0021-\\u002b\\u003c-\\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{8,16}$";
    public String validStreet = "^[a-zA-ZÁÉÍÓÚñáéíóúÑ.0-9]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ0-9]{1,15})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?$";

    public String ValidNumbers(int maxNum,int minNum){
        String validNumbers = "^\\d{"+minNum+","+maxNum+"}$";
        return validNumbers;
    }
}
