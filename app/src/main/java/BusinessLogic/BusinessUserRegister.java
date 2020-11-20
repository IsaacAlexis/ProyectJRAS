package BusinessLogic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Data.Models.UsersDataModel;
import Data.Models.UsersModel;
import DataAccess.DAUsersRegister;

public class BusinessUserRegister {
     public static List<UsersModel> musers=new ArrayList<>();
    public static DAUsersRegister regist = new DAUsersRegister();

    public static List<UsersModel> getallusers(UsersModel mUsers) {
       musers=regist.getallusers(mUsers);
       return musers;


    }

    public void BridgeUserRegister(UsersDataModel data){
        regist.DAUsersRegister(data);
    }

    public void BridgeUserExist (UsersDataModel data){
        regist.DAUserExist(data);
    }

}
