package BusinessLogic;

import java.util.List;

import Data.Models.PaymentsModel;
import DataAccess.DAPayments;

public class BusinessPayments {
    public static List<PaymentsModel> getAllPayments() {
        return new DAPayments().getPayments();
    }

    public boolean AccessToRegisterPayment(String codigo) {
       return new DAPayments().PostGetDataPay(codigo);
    }

    public void RegisterPayment(float AmountPay,int action) {
        switch (action){
            case 1:
                new DAPayments().PostRegisterPayment(AmountPay,action);
                break;
            case 2:
                if(new PaymentsModel().getTotal()<AmountPay){
                    new PaymentsModel().setValidationMessage("No se puede ingresar una cantidad mayor al total");
                    return;
                }
                new DAPayments().PostRegisterPayment(AmountPay,action);
                break;
            default:
        }

    }


}
