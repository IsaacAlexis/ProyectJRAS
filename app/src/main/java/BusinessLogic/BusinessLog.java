package BusinessLogic;

import java.sql.SQLException;

import Data.Models.UsersModel;
import DataAccess.DALog;

public class BusinessLog {

    DALog manto = new DALog();

    public void BridgeLogin(UsersModel ini) throws SQLException {
        manto = new DALog();
        manto.LoginUsers(ini);
    }



}
