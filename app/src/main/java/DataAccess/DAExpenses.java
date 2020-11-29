package DataAccess;

import java.util.List;

import Data.BDConnection;
import Data.Models.ExpensesModel;
import Security.scExpense;

public class DAExpenses {

    private BDConnection bd;

    scExpense expens = new scExpense();

    public void DAExpensRegister(ExpensesModel expen){
        expens.ExpenRegister(expen);
    }
    public void DAExpenModify(ExpensesModel expen){ expens.ExpenModify(expen); }

    public List<ExpensesModel> getallexpense() {
       return expens.getallexpenses();
    }

    public Long getLastFolio() {
        return expens.getLastFolio();
    }
}
