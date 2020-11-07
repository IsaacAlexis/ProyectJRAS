package DataAccess;


import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Data.BDConnection;
import Data.Models.UsersModel;
import Security.UserAccess;

public class DALog {


    private BDConnection bd;

    UserAccess userAccess = new UserAccess();

    public void LoginUsers(UsersModel login) throws SQLException {

        login = userAccess.ValidateUser(login);

    }
}
