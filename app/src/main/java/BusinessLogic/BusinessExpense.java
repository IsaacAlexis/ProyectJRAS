package BusinessLogic;

import java.util.List;

import DataAccess.DAExpenses;
import Data.Models.ExpensesModel;

public class BusinessExpense {

    public static DAExpenses exp = new DAExpenses();

    public static List<ExpensesModel> getallexpenses() {return exp.getallexpense();}
    public void BridgeExpenRegister(ExpensesModel expens ) { exp.DAExpensRegister(expens); }
    public void BridgeExpenModify(ExpensesModel expens ) { exp.DAExpenModify(expens); }
    public  Long getLastFolio(){
        return exp.getLastFolio();
    }


}
