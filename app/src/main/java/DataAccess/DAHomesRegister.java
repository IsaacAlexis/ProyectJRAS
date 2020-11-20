package DataAccess;

import Data.Models.HomesModel;
import Data.Models.HouseModel;
import Security.HomesRegister;

public class DAHomesRegister {
    HomesRegister HR = new HomesRegister();

    public  void HomeExist(HomesModel home){
        HR.HomeExist(home);
    }

    public void HomeRegister(HomesModel home){
        HR.HomeRegister(home);
    }

    public void HouseUpdate(HouseModel house) {
        HR.HouseUpdate(house);
    }

    public void HouseScan(HomesModel home){
        HR.HouseScan(home);
    }

    public void HouseScanExist(HomesModel home){
        HR.HouseScanExist(home);
    }
}
