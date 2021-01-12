package Security;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Data.BDConnection;
import Data.Models.PaymentsModel;
import Data.Models.ReportsModel;
import Data.Models.UsersModel;

public class scReports {
    BDConnection bd = new BDConnection();
    public void getPayments(ReportsModel reports) {
        List<ReportsModel> pays=new ArrayList<>();
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call ReportsPayments(?,?,?)}");
            callableStatement.setString(1, reports.getDateMin());
            callableStatement.setString(2, reports.getDateMax());
            callableStatement.setString(3, new UsersModel().getCurrentColony());
            ResultSet resultSet=callableStatement.executeQuery();
            while (resultSet.next()){
                pays.add(new ReportsModel(resultSet.getString(1),resultSet.getFloat(2),resultSet.getDate(3)));

            }
            reports.setPays(pays);


            callableStatement.close();
            bd.CloseConnection();
        } catch (SQLException e) {
            try {
                if(!bd.connection.isClosed()){
                    bd.CloseConnection();
                }
            } catch (SQLException e2) { }
            reports.setValidationMessage("Ocurrio un error al traer los datos de los pagos");
        }
    }

    public void getExpenses(ReportsModel reports) {
        List<ReportsModel> expenses=new ArrayList<>();
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call ReportsExpenses(?,?,?)}");
            callableStatement.setString(1, reports.getDateMin());
            callableStatement.setString(2, reports.getDateMax());
            callableStatement.setString(3, new UsersModel().getCurrentColony());
            ResultSet resultSet=callableStatement.executeQuery();
            while (resultSet.next()){
                expenses.add(new ReportsModel(resultSet.getString(1),resultSet.getString(2),resultSet.getFloat(3),
                        resultSet.getDate(4)));
            }
            reports.setExpenses(expenses);


            callableStatement.close();
            bd.CloseConnection();
        } catch (SQLException e) {
            try {
                if(!bd.connection.isClosed()){
                    bd.CloseConnection();
                }
            } catch (SQLException e2) { }
           reports.setValidationMessage("Ocurrio un erro al traer los datos de los gastos");
        }
    }
}
