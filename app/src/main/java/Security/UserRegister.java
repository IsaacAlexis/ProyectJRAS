package Security;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import Data.BDConnection;
import Data.Models.UsersDataModel;
import Data.Models.UsersModel;

public class UserRegister {


    public void UserRegister(UsersDataModel data){
        BDConnection bd = new BDConnection();
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement cs =bd.connection.prepareCall("{call UsersRegister(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1,data.getLastName());
            cs.setString(2,data.getFirstName());
            cs.setString(3,data.getEmail());
            cs.setString(4,data.getUserName());
            cs.setString(5,data.getUserRole());
            cs.setString(6,data.getPassword());
            cs.setString(7,data.getExpirationDate());
            cs.setString(8,data.getColony());
            cs.setString(9,data.getUserStatus());
            //cs.setString(10,data.getAddeddDate());
            //cs.setString(11,data.getModifiedDate());
            cs.executeUpdate();


            cs.close();
            bd.CloseConnection();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void UserRegisterExist(UsersDataModel data){

        BDConnection bd = new BDConnection();

        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call UserExist(?)}");
            callableStatement.setString(1, data.getUserName());
            ResultSet Result= callableStatement.executeQuery();

            if (Result.next()){
                data.setUserExist(true);
            }
            else{
                data.setUserExist(false);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }


    }

}
