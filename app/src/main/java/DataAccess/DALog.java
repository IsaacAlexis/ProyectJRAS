package DataAccess;



import java.sql.SQLException;
import Data.BDConnection;
import Data.Models.UsersModel;
import Security.UserAccess;

public class DALog {


    private BDConnection bd;

    UserAccess userAccess = new UserAccess();

    //Puente para acceder al User access - Procedures
    public void LoginUsers(UsersModel login) throws SQLException {

        login = userAccess.ValidateUser(login);

    }
    public void BlockUsers(UsersModel login){
        userAccess.BlocUser(login);

    }

    public void UserExist(UsersModel ini) {
        userAccess.UserExist(ini);
    }
}
