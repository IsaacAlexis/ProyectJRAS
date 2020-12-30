package BusinessLogic;

import android.content.Context;

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

    public void RegisterPayment(float AmountPay, int action, Context context) {
        switch (action){
            case 1:
                new DAPayments().PostRegisterPayment(AmountPay,action,context);
                break;
            case 2:
              if(AmountPay==new PaymentsModel().getTotal()){
                  RegisterPayment(AmountPay,1,context);
                  return;
              }
                if(new PaymentsModel().getTotal()<AmountPay){
                    new PaymentsModel().setValidationMessage("No se puede ingresar una cantidad mayor al total");
                    return;
                }
                new DAPayments().PostRegisterPayment(AmountPay,action,context);
                break;
            default:
        }

    }


}
