package Security;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Data.BDConnection;
import Data.Models.HomesModel;
import Data.Models.HousesModel;
import Data.Models.UsersModel;

public class HomesRegister {
    BDConnection bd = new BDConnection();
    public void HomeExist(HousesModel home){


        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call HomeExist(?)}");
            callableStatement.setString(1, home.getBarCode());
            ResultSet Result= callableStatement.executeQuery();

            if (Result.next()){
                home.setExistHouse(true);
            }
            else{
                home.setExistHouse(false);
            }
            callableStatement.close();
            bd.CloseConnection();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void HomeRegister(HousesModel home){

        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement cs =bd.connection.prepareCall("{call HomeRegister(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1,home.getBarCode());
            cs.setString(2,home.getOwner());
            cs.setLong(3,home.getPhoneNumber());
            cs.setString(4,home.getEmail());
            cs.setString(5,home.getStreet());
            cs.setInt(6,home.getHouseNumber());
            cs.setInt(7,home.getZipCode());
            cs.setString(8,home.getColony());
            cs.setString(9,home.getCity());
            cs.setString(10,home.getState());
            cs.setString(11,home.getStatusHouse());
            cs.executeUpdate();


            cs.close();
            bd.CloseConnection();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void HouseUpdate(HousesModel house) {
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


    public List<HousesModel> getallhouses(HousesModel mHouse) {
        List<HousesModel> houses=new ArrayList<>();
        try {
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call GetAllHouses}");
            ResultSet result=callableStatement.executeQuery();
            while (result.next()){
                houses.add(new HousesModel(result.getString("BarCode"),result.getString("Owner"),
                        result.getLong("PhoneNum"),result.getString("Email"),
                        result.getString("Street"),result.getInt("HouseNum"),result
                        .getInt("ZipCode"),result.getString("Colony"),result.getString("State"),
                        result.getString("City"),result.getString("StatusHouse")));
            }
            callableStatement.close();
            bd.CloseConnection();

        }catch (Exception e){
            houses=null;
            try {
                if(!bd.connection.isClosed()){
                    bd.CloseConnection();
                }
            } catch (SQLException e2) { }
            e.printStackTrace();
        }
        return houses;

    }

}
