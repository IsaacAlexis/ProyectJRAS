package BusinessLogic;

import Data.Models.UsersDataModel;
import DataAccess.DAUsersRegister;

public class BusinessUserRegister {

    DAUsersRegister regist = new DAUsersRegister();

    public void BridgeUserRegister(UsersDataModel data){
        regist.DAUsersRegister(data);
    }

    public void BridgeUserExist (UsersDataModel data){
        regist.DAUserExist(data);
    }

}
