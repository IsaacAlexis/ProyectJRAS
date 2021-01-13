package BusinessLogic;

import android.content.Context;

import java.util.List;

import Data.Models.PaymentsModel;
import Data.Utility.GenaratorPDF;
import DataAccess.DAPayments;

public class BusinessPayments {
    public static List<PaymentsModel> getAllPayments() {
        return new DAPayments().getPayments();
    }

    public boolean AccessToRegisterPayment(PaymentsModel pay) {
       return new DAPayments().PostGetDataPay(pay);
    }

    public void RegisterPayment(PaymentsModel pays, int action, Context context) {
        switch (action){
            case 1:
                new DAPayments().PostRegisterPayment(pays,action,context);
                new GenaratorPDF().createTicketPDF(context, pays);
                break;
            case 2:
              if(pays.getAmountPay().equals(pays.getTotal())){
                  RegisterPayment(pays,1,context);
                  return;
              }
                if(pays.getTotal()<pays.getAmountPay()){
                    pays.setValidationMessage("No se puede ingresar una cantidad mayor al total");
                    return;
                }
                if(pays.getTotal().equals(pays.getAmountPay())){
                    new DAPayments().PostRegisterPayment(pays,action,context);
                    new GenaratorPDF().createTicketPDF(context, pays);
                    return;
                }
                new DAPayments().PostRegisterPayment(pays,action,context);
                new GenaratorPDF().createTicketPDF(context, pays);
                break;
            default:
        }

    }


}
