package DataAccess;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import Data.Models.PaymentsModel;
import Data.Utility.GenaratorPDF;
import Security.scPayments;

public class DAPayments {
    public List<PaymentsModel> getPayments() {
        return new scPayments().allPayments();
    }

    public boolean PostGetDataPay(PaymentsModel Pays) {
        if(new scPayments().ExistHouse(Pays)){
            if(new scPayments().ExistConsumption(Pays)){
                return new scPayments().getDebits(Pays);
            }
            return false;

        }
        return false;
    }

    public void PostRegisterPayment(PaymentsModel pays, int action, Context context) {
        List<PaymentsModel> datapays =new ArrayList<>();
        switch(action) {
            case 1:
                pays.setDebitTotal(pays.getTotal());
                pays.setTotal(pays.getTotal()-pays.getTotal());

                for (int i = (pays.getDebits().size() - 1); i >= 0; i--) {
                    pays.setPayTotal(pays.getDebits().get(i).getRate());
                    pays.setRate(Float.parseFloat("0"));
                    pays.setIdConsumption(pays.getDebits().get(i).getIdConsumption());
                    new scPayments().RegisterPayment(pays, action);
                    datapays.add(new PaymentsModel(pays.getDebits().get(i).getReadDate(),
                            pays.getDebits().get(i).getRate(),"PAGO TOTAL"));
                }
                pays.setDataPays(datapays);
                new GenaratorPDF().createTicketPDF(context, pays);
                break;

            case 2:
                float total = pays.getAmountPay();
                pays.setDebitTotal(pays.getAmountPay());
                pays.setTotal(pays.getTotal()-pays.getAmountPay());

               for (int i = (pays.getDebits().size() - 1); i >= 0; i--) {
                    if (total < 0) {
                        total += pays.getDebits().get(i).getRate();
                        if (total <= 0) {
                            pays.setPayTotal(pays.getDebits().get(i).getRate());
                            pays.setRate(Float.parseFloat("0"));
                            pays.setIdConsumption(pays.getDebits().get(i).getIdConsumption());
                            new scPayments().RegisterPayment(pays, action);
                            datapays.add(new PaymentsModel(pays.getDebits().get(i).getReadDate(),
                                    pays.getDebits().get(i).getRate(),"PAGO TOTAL"));

                        } else {
                            pays.setPayTotal((pays.getDebits().get(i).getRate() - total));
                            pays.setRate(total);
                            pays.setIdConsumption(pays.getDebits().get(i).getIdConsumption());
                            new scPayments().RegisterPayment(pays, action);
                            datapays.add(new PaymentsModel(pays.getDebits().get(i).getReadDate(),
                                    pays.getDebits().get(i).getRate() - total,"ABONO"));
                            total = 0;

                        }
                    } else if (total > 0) {
                        total = pays.getDebits().get(i).getRate() - pays.getAmountPay();
                        if (total <= 0) {
                            pays.setPayTotal(pays.getDebits().get(i).getRate());
                            pays.setRate(Float.parseFloat("0"));
                            pays.setIdConsumption(pays.getDebits().get(i).getIdConsumption());
                            new scPayments().RegisterPayment(pays, action);
                            datapays.add(new PaymentsModel(pays.getDebits().get(i).getReadDate(),
                                    pays.getDebits().get(i).getRate(),"PAGO TOTAL"));

                        } else {
                            pays.setPayTotal((pays.getDebits().get(i).getRate() - total));
                            pays.setRate(total);
                            pays.setIdConsumption(pays.getDebits().get(i).getIdConsumption());
                            new scPayments().RegisterPayment(pays, action);
                            datapays.add(new PaymentsModel(pays.getDebits().get(i).getReadDate(),
                                    pays.getDebits().get(i).getRate() - total,"ABONO"));
                            total = 0;

                        }
                    } else if (total == 0) {
                        pays.setDataPays(datapays);
                        new GenaratorPDF().createTicketPDF(context, pays);
                        return;
                    }
                }

               break;
            default:
                break;

        }



    }
}
