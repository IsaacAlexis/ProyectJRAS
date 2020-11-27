package BusinessLogic;

import java.util.ArrayList;
import java.util.List;

import Data.Models.HousesModel;
import DataAccess.DAHouse;

public class BusinessHouse {
    public static List<HousesModel> mhouse=new ArrayList<>();

    public static DAHouse DAH = new DAHouse();

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
