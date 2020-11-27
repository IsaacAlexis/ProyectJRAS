package BusinessLogic;

import Data.Models.ConsumptionsModel;
import Data.Models.HousesModel;
import Data.Models.UsersModel;
import DataAccess.DAConsumptions;

public class BusinessConsumptions {
    DAConsumptions DAC = new DAConsumptions();
    UsersModel user = new UsersModel();

    public void BridgeHouseScanner(HousesModel home){
        DAC.HouseScan(home);
    }

    public void BridgeConsumptionReading(ConsumptionsModel cm){
        DAC.ConsumptionReading(cm);
    }

}
