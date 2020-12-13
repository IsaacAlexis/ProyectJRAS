package BusinessLogic;

import java.util.List;

import Data.Models.ConsumptionsModel;
import Data.Models.HousesModel;
import Data.Models.UsersModel;
import Data.Models.WaterBillsModel;
import DataAccess.DAConsumptions;

public class BusinessConsumption {
    DAConsumptions DAC = new DAConsumptions();
    UsersModel user = new UsersModel();

    public void BridgeHouseScanner(WaterBillsModel billsModel){
        DAC.HouseScan(billsModel);
    }

    public void BridgeConsumptionReading(ConsumptionsModel cm){
        DAC.ConsumptionReading(cm);
    }



    public List<WaterBillsModel> BridgeWaterBills(ConsumptionsModel cm) {
        return DAC.waterBills(cm);
    }



    public Float BridgeGetRateNow(Float totalReadWater) {
        return DAC.GetRateNow(totalReadWater);
    }


}
