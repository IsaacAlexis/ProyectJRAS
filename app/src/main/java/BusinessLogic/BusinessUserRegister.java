package BusinessLogic;

import Data.Models.UsersModel;
import DataAccess.DAUsersRegister;

public class BusinessUserRegister {

    DAUsersRegister regist = new DAUsersRegister();

    public void BridgeUserRegister(UsersModel data){
        regist.DAUsersRegister(data);
    }

}
