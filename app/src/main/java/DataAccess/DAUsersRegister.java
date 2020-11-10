package DataAccess;

import Data.BDConnection;
import Data.Models.UsersModel;
import Security.UserRegister;

public class DAUsersRegister {

    private BDConnection bd;
    UserRegister register = new UserRegister();

    public void DAUsersRegister(UsersModel data){
        register.UserRegister(data);
    }
}
