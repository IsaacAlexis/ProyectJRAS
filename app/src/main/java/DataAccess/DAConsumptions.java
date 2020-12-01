package DataAccess;

import Data.Models.ConsumptionsModel;
import Data.Models.HousesModel;
import Security.scConsumption;

public class DAConsumptions {
    scConsumption C = new scConsumption();

    public void HouseScan(HousesModel home){
        C.HouseScan(home);
    }

    public void ConsumptionReading(ConsumptionsModel cm){
        C.ConsumptionReading(cm);
    }


}
