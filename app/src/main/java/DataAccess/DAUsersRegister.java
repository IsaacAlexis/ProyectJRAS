package DataAccess;

import java.util.ArrayList;
import java.util.List;

import Data.BDConnection;
import Data.Models.UsersModel;
import Security.UserRegister;

public class DAUsersRegister {

    private BDConnection bd;
    List<UsersModel> musers=new ArrayList<>();
    UserRegister register = new UserRegister();

    public void DAUsersRegister(UsersModel data){
        register.UserRegister(data);
    }
    public void DAUserExist(UsersModel data){
        register.UserRegisterExist(data);
    }
    public  List<UsersModel> getallusers(UsersModel mUsers) {musers=register.getallusers(mUsers);return musers;}
}
