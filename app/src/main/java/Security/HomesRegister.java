package Security;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Data.BDConnection;
import Data.Models.HomesModel;
import Data.Models.HouseModel;

public class HomesRegister {
    BDConnection bd = new BDConnection();
    public void HomeExist(HomesModel home){


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

        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement cs =bd.connection.prepareCall("{call HomeRegister(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1,home.getBarCode());
            cs.setString(2,home.getOwner());
            cs.setLong(3,Long.parseLong(home.getPhoneNum()));
            cs.setString(4,home.getEmail());
            cs.setString(5,home.getStreet());
            cs.setInt(6,Integer.parseInt(home.getHouseNum()));
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

    public void HouseUpdate(HouseModel house) {
        try {
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement cs =bd.connection.prepareCall("{call updateHouse(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1,house.getBarCode());
            cs.setString(2,house.getOwner());
            cs.setLong(3,house.getPhoneNumber());
            cs.setString(4,house.getEmail());
            cs.setString(5,house.getStreet());
            cs.setInt(6,house.getHouseNumber());
            cs.setInt(7,house.getZipCode());
            cs.setString(8,house.getColony());
            cs.setString(9,house.getCity());
            cs.setString(10,house.getState());
            cs.setString(11,house.getStatusHouse());
            cs.executeUpdate();
            house.setStatusActivity(true);
            cs.close();
            bd.CloseConnection();

        }catch (SQLException e){
            house.setStatusActivity(false);

        }
    }

    public void HouseScan(HomesModel home){
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call HomeQueryScan(?)}");
            callableStatement.setString(1, home.getBarCode());
            ResultSet Result= callableStatement.executeQuery();

            while (Result.next()){
                home.setOwner(Result.getString("Owner"));
                home.setHouseNum(Result.getString("HouseNum"));
                home.setHouseExist(true);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void HouseScanExist(HomesModel home){
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call HomeQueryScan(?)}");
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

}
