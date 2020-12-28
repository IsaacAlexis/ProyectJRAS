package BusinessLogic;

import java.util.List;

import Data.Models.WaterBillsModel;
import DataAccess.DABills;


public class BusinessBills {

    public static List<WaterBillsModel> getallConsumptions() {
       return new DABills().getallConsumptions();
    }
}
