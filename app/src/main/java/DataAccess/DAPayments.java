package DataAccess;

import android.widget.Switch;

import androidx.core.app.ActivityCompat;

import java.util.List;

import Data.Models.PaymentsModel;
import Security.scPayments;

public class DAPayments {
    public List<PaymentsModel> getPayments() {
        return new scPayments().allPayments();
    }

    public boolean PostGetDataPay(String Barcode) {
        if(new scPayments().ExistHouse(Barcode)){
            if(new scPayments().ExistConsumption(Barcode)){
                return new scPayments().getDebits(Barcode);
            }
            return false;

        }
        return false;
    }

    public void PostRegisterPayment(float amountPay,int action) {
        switch(action){
            case 1:

                for (PaymentsModel debit:new PaymentsModel().getaDebits()){
                    PaymentsModel paymentsModel=new PaymentsModel();
                    paymentsModel.setPayTotal(debit.getRate());
                    paymentsModel.setRate(Float.parseFloat("0"));
                    paymentsModel.setIdConsumption(debit.getIdConsumption());
                    new scPayments().RegisterPayment(paymentsModel,action);
                }
                break;

            case 2:
                float total=amountPay;
                //-------Pruebas de mejora en el algoritmo------
//                for(int  i=(new PaymentsModel().getaDebits().size()-1);i>=0;i--){
//                    if(total<0){
//                        total+=new PaymentsModel().getaDebits().get(i).getRate();
//                        if(total<=0){
//                            PaymentsModel paymentsModel=new PaymentsModel();
//                            paymentsModel.setPayTotal(new PaymentsModel().getaDebits().get(i).getRate());
//                            paymentsModel.setRate(Float.parseFloat("0"));
//                            paymentsModel.setIdConsumption(new PaymentsModel().getaDebits().get(i).getIdConsumption());
//                            new scPayments().RegisterPayment(paymentsModel,action);
//
//                        }else {
//                            PaymentsModel paymentsModel=new PaymentsModel();
//                            paymentsModel.setPayTotal((new PaymentsModel().getaDebits().get(i).getRate()-total));
//                            paymentsModel.setRate(total);
//                            paymentsModel.setIdConsumption(new PaymentsModel().getaDebits().get(i).getIdConsumption());
//                            new scPayments().RegisterPayment(paymentsModel,action);
//                            total=0;
//
//                        }
//                    }else if(total>0){
//                        total=new PaymentsModel().getaDebits().get(i).getRate()-amountPay;
//                        if(total<=0){
//                            PaymentsModel paymentsModel=new PaymentsModel();
//                            paymentsModel.setPayTotal(new PaymentsModel().getaDebits().get(i).getRate());
//                            paymentsModel.setRate(Float.parseFloat("0"));
//                            paymentsModel.setIdConsumption(new PaymentsModel().getaDebits().get(i).getIdConsumption());
//                            new scPayments().RegisterPayment(paymentsModel,action);
//
//                        }else {
//                            PaymentsModel paymentsModel=new PaymentsModel();
//                            paymentsModel.setPayTotal(amountPay);
//                            paymentsModel.setRate(total);
//                            paymentsModel.setIdConsumption(new PaymentsModel().getaDebits().get(i).getIdConsumption());
//                            new scPayments().RegisterPayment(paymentsModel,action);
//                            total=0;
//
//                        }
//                    }else if(total==0){return;}
//}
                for (PaymentsModel debit: new PaymentsModel().getaDebits()) {
                    if(total<0){
                        total+=debit.getRate();
                        if(total<=0){
                            PaymentsModel paymentsModel=new PaymentsModel();
                            paymentsModel.setPayTotal(debit.getRate());
                            paymentsModel.setRate(Float.parseFloat("0"));
                            paymentsModel.setIdConsumption(debit.getIdConsumption());
                            new scPayments().RegisterPayment(paymentsModel,action);

                        }else {
                            PaymentsModel paymentsModel=new PaymentsModel();
                            paymentsModel.setPayTotal((debit.getRate()-total));
                            paymentsModel.setRate(total);
                            paymentsModel.setIdConsumption(debit.getIdConsumption());
                            new scPayments().RegisterPayment(paymentsModel,action);
                            total=0;

                        }
                    }else if(total>0){
                        total=debit.getRate()-amountPay;
                        if(total<=0){
                            PaymentsModel paymentsModel=new PaymentsModel();
                            paymentsModel.setPayTotal(debit.getRate());
                            paymentsModel.setRate(Float.parseFloat("0"));
                            paymentsModel.setIdConsumption(debit.getIdConsumption());
                            new scPayments().RegisterPayment(paymentsModel,action);

                        }else {
                            PaymentsModel paymentsModel=new PaymentsModel();
                            paymentsModel.setPayTotal(amountPay);
                            paymentsModel.setRate(total);
                            paymentsModel.setIdConsumption(debit.getIdConsumption());
                            new scPayments().RegisterPayment(paymentsModel,action);
                            total=0;

                        }
                    }else if(total==0){return;}


                }
                break;
            default:

        }



    }
}
