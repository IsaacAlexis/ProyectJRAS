package BusinessLogic;

import java.sql.SQLException;

import Data.Models.UsersModel;
import DataAccess.DALog;

public class BusinessLog {

    DALog acceslog = new DALog();

    public void BridgeLogin(UsersModel ini) throws SQLException {
        acceslog = new DALog();
        acceslog.LoginUsers(ini);
    }
    public void BridgeBlockLogin(UsersModel ini) throws SQLException{
        acceslog = new DALog();
        acceslog.BlockUsers(ini);
    }


    public void BridgeUserExist(UsersModel ini) {
        acceslog=new DALog();
        acceslog.UserExist(ini);
    }
}
