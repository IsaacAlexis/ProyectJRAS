package DataAccess;

import java.util.List;

import Data.Models.ConsumptionsModel;
import Data.Models.HousesModel;
import Data.Models.WaterBillsModel;
import Security.scConsumption;

public class DAConsumptions {
    scConsumption C = new scConsumption();

    public void HouseScan(WaterBillsModel billsModel){
        if(C.HouseScan(billsModel)){
            C.getDataBills(billsModel);
        }else{
            billsModel.setValidationMessage("Vivienda no existente, intenta con otro codigo");
        }

    }

    public void ConsumptionReading(ConsumptionsModel cm){
        C.ConsumptionReading(cm);
    }


    public List<WaterBillsModel> waterBills(ConsumptionsModel cm) {
        return C.waterBills(cm);
    }


    public Float GetRateNow(Float totalReadWater) {
        return C.getRateNow(totalReadWater);
    }


    public boolean FirstConsumptionReading(ConsumptionsModel cm, List<ConsumptionsModel> consumptionsModelList) {
        return C.FirstConsumptionReading(cm,consumptionsModelList);
    }
}
