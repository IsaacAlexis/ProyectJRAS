package DataAccess;

import java.util.ArrayList;
import java.util.List;

import Data.Models.HousesModel;
import Security.scHouse;

public class DAHouse {
    List<HousesModel> mhouses=new ArrayList<>();
    scHouse HR = new scHouse();

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
