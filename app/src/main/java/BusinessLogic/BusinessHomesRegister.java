package BusinessLogic;

import Data.Models.HomesModel;
import DataAccess.DAHomesRegister;

public class BusinessHomesRegister {

    DAHomesRegister DAH = new DAHomesRegister();

    public void BridgeHomeExist(HomesModel home){
        DAH.HomeExist(home);
    }

    public void BridgeHomeRegister(HomesModel home){
        DAH.HomeRegister(home);
    }
}
