package BusinessLogic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Data.Models.UsersModel;
import DataAccess.DAUser;

public class BusinessUser {
    public static List<UsersModel> musers=new ArrayList<>();
    public static DAUser users = new DAUser();
    //Login Usuarios
    public void BridgeLogin(UsersModel ini) throws SQLException {users.LoginUsers(ini);}
    public void BridgeBlockLogin(UsersModel ini) throws SQLException{users.BlockUsers(ini);}
    public void BridgeUserExistLog(UsersModel ini) {users.UserExist(ini);}
    //CRUD de Usuarios
    public static List<UsersModel> getallusers(UsersModel mUsers) {musers= users.getallusers(mUsers);return musers;}
    public void BridgeUserRegister(UsersModel data){
        users.DAUsersRegister(data);
    }
    public void BridgeUserExist(UsersModel data){
        users.DAUserExist(data);
    }
    public void BridgeUserUpdate(UsersModel users) {
        BusinessUser.users.updateUsers(users);
    }
}
