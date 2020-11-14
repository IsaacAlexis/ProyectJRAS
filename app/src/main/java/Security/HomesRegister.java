package Security;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Data.BDConnection;
import Data.Models.HomesModel;

public class HomesRegister {

    public void HomeExist(HomesModel home){
        BDConnection bd = new BDConnection();

        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call HomeExist(?)}");
            callableStatement.setString(1, home.getBarCode());
            ResultSet Result= callableStatement.executeQuery();

            if (Result.next()){
                home.setHouseExist(true);
            }
            else{
                home.setHouseExist(false);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void HomeRegister(HomesModel home){
        BDConnection bd = new BDConnection();
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement cs =bd.connection.prepareCall("{call HomeRegister(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1,home.getBarCode());
            cs.setString(2,home.getOwner());
            cs.setLong(3,Long.parseLong(home.getPhoneNum()));
            cs.setString(4,home.getEmail());
            cs.setString(5,home.getStreet());
            cs.setInt(6,Integer.parseInt(home.getHouseHum()));
            cs.setInt(7,Integer.parseInt(home.getZipCode()));
            cs.setString(8,home.getColony());
            cs.setString(9,home.getCity());
            cs.setString(10,home.getState());
            cs.setString(11,home.getHouseStatus());
            cs.executeUpdate();


            cs.close();
            bd.CloseConnection();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
