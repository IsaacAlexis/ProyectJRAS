package BusinessLogic;

import DataAccess.DAExpens;
import Data.Models.ExpensesModel;

public class BusinessExpens {

    public static DAExpens exp = new DAExpens();

    public void BridgeExpenRegister(ExpensesModel expens ) { exp.DAExpensRegister(expens); }
    //public void BridgeExpenExist(ExpensesModel expens) {}
}
