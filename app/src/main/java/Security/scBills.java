package Security;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Data.BDConnection;
import Data.Models.WaterBillsModel;

public class scBills {
    BDConnection bd=new BDConnection();
    public List<WaterBillsModel> getallconsumptions() {
        List<WaterBillsModel> bill = new ArrayList<>();
        try {
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement = bd.connection.prepareCall("{call GetAllConsumptions}");
            ResultSet Result = callableStatement.executeQuery();
            while (Result.next()) {
                bill.add(new WaterBillsModel(Result.getString("BarCode"), Result.getString("Owner"),
                        Result.getString("Street"), Result.getString("Colony"), Result.getInt("HouseNum"),
                        Result.getDate("ReadDate"), Result.getFloat("M3"), Result.getFloat("Rate")));

            }

            callableStatement.close();
            bd.CloseConnection();

        } catch (SQLException e) {
            try {
                if (!bd.connection.isClosed()) {

                    bd.CloseConnection();
                }
                bill=null;
            } catch (SQLException e2) {
                e.printStackTrace();
            }

        }
        return bill;
    }

}
