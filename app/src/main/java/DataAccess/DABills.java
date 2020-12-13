package DataAccess;

import java.util.List;

import Data.Models.WaterBillsModel;
import Security.scBills;

public class DABills {
    public List<WaterBillsModel> getallConsumptions() {
        return new scBills().getallconsumptions();
    }
}
