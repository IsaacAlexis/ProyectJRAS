package BusinessLogic;

import java.sql.SQLException;

import Data.LoginUsersGETSET;
import DataAccess.DALog;

public class BusinessLog {

    LoginUsersGETSET ini = new LoginUsersGETSET();

    DALog manto = new DALog();

    public void BridgeLogin(LoginUsersGETSET ini) throws SQLException {
        manto = new DALog();
        manto.LoginUsers(ini);
    }



}
