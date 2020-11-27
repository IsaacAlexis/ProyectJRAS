package DataAccess;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Data.BDConnection;
import Data.Models.UsersModel;
import Security.scUser;

public class DAUser {


    List<UsersModel> musers=new ArrayList<>();
    scUser users = new scUser();
    //Puente para acceder al User access - Procedures(Login)
    public void LoginUsers(UsersModel login) throws SQLException {login = users.ValidateUser(login);}
    public void BlockUsers(UsersModel login){users.BlocUser(login);}
    public void UserExist(UsersModel ini) {
        users.UserExist(ini);
    }
    //CRUD usuarios
    public void DAUsersRegister(UsersModel data){
        users.UserRegister(data);
    }
    public void DAUserExist(UsersModel data){
        users.UserRegisterExist(data);
    }
    public  List<UsersModel> getallusers(UsersModel mUsers) {musers= users.getallusers(mUsers);return musers;}
    public void updateUsers(UsersModel users) {
        this.users.updateUsers(users);
    }
}
