package Security;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import Data.BDConnection;
import Data.Models.UsersModel;

public class UserRegister {

    public void UserRegister(UsersModel userData){
        BDConnection bd = new BDConnection();
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement cs=bd.connection.prepareCall("{call UsersRegister(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1,userData.getLastName());
            cs.setString(2,userData.getFirstName());
            cs.setString(3,userData.getEmail());
            cs.setString(4,userData.getUserName());
            cs.setString(5,userData.getRole());
            cs.setString(6,userData.getPass());
            cs.setString(7,userData.getExpDate());
            cs.setString(8,userData.getColony());
            cs.setString(9,userData.getUserStatus());
            cs.setString(10,userData.getAddeddDate());
            cs.setString(11,userData.getModifiedDate());
            cs.executeUpdate();

            cs.close();
            bd.CloseConnection();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
