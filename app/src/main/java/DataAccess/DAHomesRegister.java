package DataAccess;

import java.util.ArrayList;
import java.util.List;

import Data.Models.HomesModel;
import Data.Models.HousesModel;
import Security.HomesRegister;

public class DAHomesRegister {
    List<HousesModel> mhouses=new ArrayList<>();
    HomesRegister HR = new HomesRegister();

    public  void HomeExist(HousesModel home){
        HR.HomeExist(home);
    }

    public void HomeRegister(HousesModel home){
        HR.HomeRegister(home);
    }

    public void HouseUpdate(HousesModel house) {
        HR.HouseUpdate(house);
    }

    public List<HousesModel> getallhouses(HousesModel mHouse) {
        mhouses=HR.getallhouses(mHouse);
        return mhouses;
    }
}
