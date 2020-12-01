package Security;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Data.BDConnection;
import Data.Models.ConsumptionsModel;
import Data.Models.HousesModel;

public class scConsumption {
    BDConnection bd = new BDConnection();

    public void HouseScan(HousesModel home){
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call HomeExistConsumption(?)}");
            callableStatement.setString(1, home.getBarCode());
            ResultSet Result= callableStatement.executeQuery();
            home.setExistHouse(false);
            while (Result.next()){
                home.setModifybarCode(Result.getString("BarCode"));
                home.setModifyowner(Result.getString("Owner"));
                home.setModifyhouseNumber(Result.getInt("HouseNum"));
                home.setExistHouse(true);
            }



            callableStatement.close();
            bd.CloseConnection();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    public void ConsumptionReading(ConsumptionsModel cm){
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement cs1 = bd.connection.prepareCall("{call CostRead(?)}");
            cs1.setFloat(1, cm.getM3());

            ResultSet res = cs1.executeQuery();

            while (res.next()){
                cm.setRate(res.getFloat("Rate"));
                cm.setIDCost(res.getLong("IDCost"));
            }

            CallableStatement cs2 = bd.connection.prepareCall("{call ConsumptionReading(?,?,?,?,?,?)}");
            cs2.setLong(1,cm.getIDUser());
            cs2.setLong(2,cm.getIDCost());
            cs2.setString(3,cm.getBarCode());
            cs2.setString(4,cm.getReadDate());
            cs2.setFloat(5,cm.getM3());
            cs2.setFloat(6,cm.getRate());

            cs2.executeUpdate();

            cm.setValidationMessage("Registro realizado correctamente");

            cs1.close();
            res.close();
            cs2.close();
            bd.CloseConnection();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }


}
