package Security;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Data.BDConnection;
import Data.Models.PaymentsModel;
import Data.Models.UsersModel;

public class scPayments {
    BDConnection bd = new BDConnection();
    public List<PaymentsModel> allPayments() {
        List<PaymentsModel> paymentsModels=new ArrayList<>();
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call getAllPayments(?)}");
            callableStatement.setString(1, new UsersModel().getCurrentColony());
            ResultSet resultSet=callableStatement.executeQuery();
            while (resultSet.next()){
                paymentsModels.add(new PaymentsModel(
                        resultSet.getString(1),resultSet.getDate(2),resultSet.getFloat(3)));
            }


            callableStatement.close();
            bd.CloseConnection();
        } catch (SQLException e) {
            try {
                if(!bd.connection.isClosed()){
                    bd.CloseConnection();
                }
            } catch (SQLException e2) { }
            e.printStackTrace();
        }
        return paymentsModels;
    }

    public boolean ExistConsumption(String barCode) {
        boolean action=false;
        try{

            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call getDatasConsumption(?)}");
            callableStatement.setString(1, barCode);
            ResultSet resultSet=callableStatement.executeQuery();

            while (resultSet.next()){

               new PaymentsModel(resultSet.getString(1),resultSet.getString(2),resultSet.getFloat(3),resultSet.getInt(4),resultSet.getString(5));
               action=true;
            }
            if(!action){
                new PaymentsModel().setValidationMessage("Referencia sin adeudo");
            }


            callableStatement.close();
            bd.CloseConnection();
        } catch (SQLException e) {
            try {
                if(!bd.connection.isClosed()){
                    bd.CloseConnection();
                }
            } catch (SQLException e2) { }
            new PaymentsModel().setValidationMessage("Error al obtener los datos de la vivienda");
        }
        return action;
    }

    public boolean ExistHouse(String barCode) {
        boolean action=false;
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call HomeExist(?,?,?,?)}");
            callableStatement.setString(1, barCode);
            callableStatement.setString(2, "");
            callableStatement.setString(3, "");
            callableStatement.setInt(4, 0);
            ResultSet resultSet=callableStatement.executeQuery();

            while (resultSet.next()){
               action=true;
            }
            if(!action){
                new PaymentsModel().setValidationMessage("Referencia no encontrada");
            }
            callableStatement.close();
            bd.CloseConnection();

        }catch(SQLException e){
            try {
                if(!bd.connection.isClosed()){
                    bd.CloseConnection();
                }
            } catch (SQLException e2) { }
            new PaymentsModel().setValidationMessage("Error al buscar la vivienda");
        }
        return action;
    }

    public boolean getDebits(String barcode) {
        boolean action=false;
        List<PaymentsModel> debits=new ArrayList<>();
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call getDebits(?)}");
            callableStatement.setString(1, barcode);
            ResultSet resultSet=callableStatement.executeQuery();
            while (resultSet.next()){
                debits.add(new PaymentsModel(resultSet.getDate("ReadDate"),resultSet.getFloat("Rate")));
                action=true;
            }
            if(action){
                new PaymentsModel().setdDebits(debits);
                getDebitsASC(barcode);
            }

            callableStatement.close();
            bd.CloseConnection();

        }catch(SQLException e){
            try {
                if(!bd.connection.isClosed()){
                    bd.CloseConnection();
                }
            } catch (SQLException e2) { }
            action=false;
            new PaymentsModel().setValidationMessage("Error al buscar los adeudos");
        }
        return action;
    }
    public boolean getDebitsASC(String barcode) {
        boolean action=false;
        List<PaymentsModel> debits=new ArrayList<>();
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call getDebitsAsc(?)}");
            callableStatement.setString(1, barcode);
            ResultSet resultSet=callableStatement.executeQuery();
            while (resultSet.next()){
                debits.add(new PaymentsModel(resultSet.getFloat("Rate"),resultSet.getLong("IDConsu")));
                action=true;
            }
            if(action){
                new PaymentsModel().setaDebits(debits);
            }

            callableStatement.close();
            bd.CloseConnection();

        }catch(SQLException e){
            try {
                if(!bd.connection.isClosed()){
                    bd.CloseConnection();
                }
            } catch (SQLException e2) { }
            action=false;
            new PaymentsModel().setValidationMessage("Error al buscar los adeudos");
        }
        return action;
    }

    public void RegisterPayment(PaymentsModel paymentsModel,int action) {
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call RegisterPayments(?,?,?,?,?,?,?)}");
            callableStatement.setLong(1, new UsersModel().getCurrentIdUser());
            callableStatement.setLong(2, paymentsModel.getIdConsumption());
            callableStatement.setString(3,new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            callableStatement.setString(4,paymentsModel.getBarcode());
            callableStatement.setFloat(5,paymentsModel.getPayTotal());
            callableStatement.setFloat(6,paymentsModel.getRate());
            callableStatement.setInt(7,action);//1 significa realizar pago total y 2 realizar abono
            callableStatement.executeUpdate();
            switch (action){
                case 1:
                    paymentsModel.setValidationMessage("Se ha realizo con exito el pago");
                    break;
                case 2:
                    paymentsModel.setValidationMessage("Se realizo con exito el abono");
                    break;
                default:
            }


            callableStatement.close();
            bd.CloseConnection();

        }catch(SQLException e){
            try {
                if(!bd.connection.isClosed()){
                    bd.CloseConnection();
                }
            } catch (SQLException e2) { }
            new PaymentsModel().setValidationMessage("No se pudo realizar la operacion de pago");
        }

    }
}
