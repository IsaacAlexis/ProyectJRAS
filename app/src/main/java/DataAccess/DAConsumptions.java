package DataAccess;

import Data.Models.ConsumptionsModel;
import Data.Models.HomesModel;
import Security.Consumpitons;

public class DAConsumptions {
    Consumpitons C = new Consumpitons();

    public void HouseScan(HomesModel home){
        C.HouseScan(home);
    }

    public void ConsumptionReading(ConsumptionsModel cm){
        C.ConsumptionReading(cm);
    }


}
