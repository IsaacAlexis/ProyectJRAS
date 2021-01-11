package BusinessLogic;

import android.content.Context;

import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import Data.Models.ConsumptionsModel;
import Data.Models.UsersModel;
import Data.Models.WaterBillsModel;
import Data.Utility.Dates;
import Data.Utility.GenaratorPDF;
import DataAccess.DAConsumptions;

public class BusinessConsumption {
    DAConsumptions DAC = new DAConsumptions();
    UsersModel user = new UsersModel();

    public void BridgeHouseScanner(WaterBillsModel billsModel){
        DAC.HouseScan(billsModel);
    }

    public void BridgeConsumptionReading(Context context, ConsumptionsModel cm,WaterBillsModel waterBillsModel, StorageReference storageReference){
        List<WaterBillsModel> bill=new ArrayList<>();
        bill=new BusinessConsumption().BridgeWaterBills(cm);
        waterBillsModel.setBills(bill);
        if(!new GenaratorPDF().createPDFWaterBills(context,waterBillsModel,storageReference)) {
            DAC.ConsumptionReading(cm);
        }
    }



    public List<WaterBillsModel> BridgeWaterBills(ConsumptionsModel cm) {
        return DAC.waterBills(cm);
    }



    public Float BridgeGetRateNow(Float totalReadWater) {
        return DAC.GetRateNow(totalReadWater);
    }


    public void BridgeConsumptionFirstReading(Context context, ConsumptionsModel cm,WaterBillsModel waterBillsModel,
                                              List<ConsumptionsModel> consumptionsModelList,StorageReference storageReference) {
        if(DAC.FirstConsumptionReading(cm,consumptionsModelList)){
            /*WaterBillsModel waterBillsModel=new WaterBillsModel();
            BridgeHouseScanner(waterBillsModel);*/
            cm.setReadDate(consumptionsModelList.get(0).getReadDate());
            cm.setM3(consumptionsModelList.get(0).getM3());
            waterBillsModel.setReadNow(consumptionsModelList.get(0).getM3());
            waterBillsModel.setReadDate(new Dates().getDateBills());
            waterBillsModel.setNowRate(new BusinessConsumption().BridgeGetRateNow(waterBillsModel.getReadNow()-
                    waterBillsModel.getReadLast()));
            List<WaterBillsModel> bill=new ArrayList<>();
            bill=new BusinessConsumption().BridgeWaterBills(cm);
            waterBillsModel.setBills(bill);
            if(!new GenaratorPDF().createPDFWaterBills(context,waterBillsModel,storageReference)){
                DAC.ConsumptionReading(cm);
            }

        }
    }
}