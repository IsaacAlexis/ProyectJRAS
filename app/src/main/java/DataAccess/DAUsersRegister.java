package DataAccess;

import Data.BDConnection;
import Data.Models.UsersDataModel;
import Security.UserRegister;

public class DAUsersRegister {

    private BDConnection bd;
    UserRegister register = new UserRegister();

    public void DAUsersRegister(UsersDataModel data){
        register.UserRegister(data);
    }

    public void DAUserExist(UsersDataModel data){
        register.UserRegisterExist(data);
    }
}
