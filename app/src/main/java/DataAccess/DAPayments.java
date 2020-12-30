package DataAccess;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import Data.Models.PaymentsModel;
import Data.Utility.GenaratorPDF;
import Data.Utility.Messages;
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

    public void PostRegisterPayment(float amountPay, int action, Context context) {
        List<PaymentsModel> pays=new ArrayList<>();
        switch(action) {
            case 1:

                for (int i = (new PaymentsModel().getDebits().size() - 1); i >= 0; i--) {
                    PaymentsModel paymentsModel = new PaymentsModel();
                    paymentsModel.setPayTotal(new PaymentsModel().getDebits().get(i).getRate());
                    paymentsModel.setRate(Float.parseFloat("0"));
                    paymentsModel.setIdConsumption(new PaymentsModel().getDebits().get(i).getIdConsumption());
                    new scPayments().RegisterPayment(paymentsModel, action);
                }
                break;

            case 2:
                float total = amountPay;
                new PaymentsModel().setDebitTotal(amountPay);
                new PaymentsModel().setTotal(new PaymentsModel().getTotal()-amountPay);

               for (int i = (new PaymentsModel().getDebits().size() - 1); i >= 0; i--) {
                    if (total < 0) {
                        total += new PaymentsModel().getDebits().get(i).getRate();
                        if (total <= 0) {
                            PaymentsModel paymentsModel = new PaymentsModel();
                            paymentsModel.setPayTotal(new PaymentsModel().getDebits().get(i).getRate());
                            paymentsModel.setRate(Float.parseFloat("0"));
                            paymentsModel.setIdConsumption(new PaymentsModel().getDebits().get(i).getIdConsumption());
                            new scPayments().RegisterPayment(paymentsModel, action);
                            pays.add(new PaymentsModel(new PaymentsModel().getDebits().get(i).getReadDate(),
                                    new PaymentsModel().getDebits().get(i).getRate(),"PAGO TOTAL"));

                        } else {
                            PaymentsModel paymentsModel = new PaymentsModel();
                            paymentsModel.setPayTotal((new PaymentsModel().getDebits().get(i).getRate() - total));
                            paymentsModel.setRate(total);
                            paymentsModel.setIdConsumption(new PaymentsModel().getDebits().get(i).getIdConsumption());
                            new scPayments().RegisterPayment(paymentsModel, action);
                            pays.add(new PaymentsModel(new PaymentsModel().getDebits().get(i).getReadDate(),
                                    new PaymentsModel().getDebits().get(i).getRate() - total,"ABONO"));
                            total = 0;

                        }
                    } else if (total > 0) {
                        total = new PaymentsModel().getDebits().get(i).getRate() - amountPay;
                        if (total <= 0) {
                            PaymentsModel paymentsModel = new PaymentsModel();
                            paymentsModel.setPayTotal(new PaymentsModel().getDebits().get(i).getRate());
                            paymentsModel.setRate(Float.parseFloat("0"));
                            paymentsModel.setIdConsumption(new PaymentsModel().getDebits().get(i).getIdConsumption());
                            new scPayments().RegisterPayment(paymentsModel, action);
                            pays.add(new PaymentsModel(new PaymentsModel().getDebits().get(i).getReadDate(),
                                    new PaymentsModel().getDebits().get(i).getRate(),"PAGO TOTAL"));

                        } else {
                            PaymentsModel paymentsModel = new PaymentsModel();
                            paymentsModel.setPayTotal(amountPay);
                            paymentsModel.setRate(total);
                            paymentsModel.setIdConsumption(new PaymentsModel().getDebits().get(i).getIdConsumption());
                            new scPayments().RegisterPayment(paymentsModel, action);
                            pays.add(new PaymentsModel(new PaymentsModel().getDebits().get(i).getReadDate(),
                                    new PaymentsModel().getDebits().get(i).getRate() - total,"ABONO"));
                            total = 0;

                        }
                    } else if (total == 0) {
                        new GenaratorPDF().createTicketPDF(context,pays);
                        return;
                    }
                }

               break;
            default:
                break;

        }



    }
}
