package DataAccess;

import java.util.ArrayList;
import java.util.List;

import Data.BDConnection;
import Data.Models.ExpensesModel;
import Security.ExpensRegister;

public class DAExpens {

    private BDConnection bd;

    ExpensRegister expens = new ExpensRegister();

    public void DAExpensRegister(ExpensesModel expen){
        expens.ExpenRegister(expen);

    }
}
