package BusinessLogic;

import java.util.ArrayList;
import java.util.List;

import Data.Models.HomesModel;
import Data.Models.HousesModel;
import Data.Models.UsersModel;
import DataAccess.DAHomesRegister;

public class BusinessHomesRegister {
    public static List<HousesModel> mhouse=new ArrayList<>();

    public static DAHomesRegister DAH = new DAHomesRegister();

    public void BridgeHomeExist(HousesModel home){
        DAH.HomeExist(home);
    }

    public void BridgeHomeRegister(HousesModel home){
        DAH.HomeRegister(home);
    }

    public void BridgeHouseUpdate(HousesModel house) { DAH.HouseUpdate(house); }
    public static List<HousesModel> getallHouses(HousesModel mHouse) {
        mhouse=DAH.getallhouses(mHouse);
        return mhouse;}

}
