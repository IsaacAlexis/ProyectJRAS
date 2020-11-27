package BusinessLogic;

import Data.Models.ConsumptionsModel;
import Data.Models.HomesModel;
import Data.Models.UsersModel;
import DataAccess.DAConsumptions;
import Security.Consumpitons;

public class BusinessConsumptions {
    DAConsumptions DAC = new DAConsumptions();
    UsersModel user = new UsersModel();

    public void BridgeHouseScanner(HomesModel home){
        DAC.HouseScan(home);
    }

    public void BridgeConsumptionReading(ConsumptionsModel cm){
        DAC.ConsumptionReading(cm);
    }

}
