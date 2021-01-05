package Security;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Data.BDConnection;
import Data.Models.ConsumptionsModel;
import Data.Models.UsersModel;
import Data.Models.WaterBillsModel;

public class scConsumption {
    BDConnection bd = new BDConnection();

    public boolean HouseScan(WaterBillsModel billsModel){
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call HomeExistConsumption(?,?)}");
            callableStatement.setString(1, billsModel.getBarCode());
            callableStatement.setString(2,new UsersModel().getCurrentColony());
            ResultSet Result= callableStatement.executeQuery();
            billsModel.setCorrectHouse(false);
            while (Result.next()){
                billsModel.setOwner(Result.getString("Owner"));
                billsModel.setStreet(Result.getString("Street"));
                billsModel.setColony(Result.getString("Colony"));
                billsModel.setHouseNumber(Result.getInt("HouseNum"));
                billsModel.setZipCode(Result.getInt("ZipCode"));
                billsModel.setStatus(Result.getString("StatusHouse"));
                billsModel.setEmail(Result.getString("Email"));
                billsModel.setCorrectHouse(true);
            }



            callableStatement.close();
            bd.CloseConnection();

        }catch(SQLException e){
            billsModel.setValidationMessage("Ocurrio un error al consultar la vivienda");
        }
        return billsModel.isCorrectHouse();
    }


    public void ConsumptionReading(ConsumptionsModel cm){
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement cs2 = bd.connection.prepareCall("{call ConsumptionReading(?,?,?,?,?)}");
            cs2.setLong(1,cm.getIDUser());
            cs2.setString(2,cm.getBarCode());
            cs2.setString(3,cm.getReadDate());
            cs2.setFloat(4,cm.getM3());
            cs2.setString(5,cm.getPdf());
            cs2.executeUpdate();
            cm.setValidationMessage("Registro realizado correctamente");
            cs2.close();
            bd.CloseConnection();

        }catch(SQLException e){
            cm.setValidationMessage("Ocurrio un error al intentar registrar");
        }
    }
    public void getDataBills(WaterBillsModel billsModel) {
        long IdConsumption =0;
        try{

            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement = bd.connection.prepareCall("{call getDataBills(?)}");
            callableStatement.setString(1, billsModel.getBarCode());
            ResultSet Result=callableStatement.executeQuery();


            billsModel.setExistFirstRegister(false);
            while (Result.next()){
                Date readDate;
                readDate=Result.getDate("ReadDate");
                int Month=Integer.parseInt(new SimpleDateFormat("MM").format(readDate));
                if(Integer.parseInt(new SimpleDateFormat("MM").format(new Date()))==Month){
                    billsModel.setCorrectHouse(false);
                    billsModel.setExistFirstRegister(false);
                    billsModel.setValidationMessage("No se encuentra en tiempo de realizar el consumo");
                }else{
                    IdConsumption =Result.getLong("IDConsu");
                    billsModel.setPreviousRate(Result.getFloat("LastRate"));
                    billsModel.setReadLast(Result.getFloat("LastRead"));
                    if(billsModel.getPreviousRate()==0){

                        callableStatement = bd.connection.prepareCall("{call getRateConsumptions(?)}");
                        callableStatement.setLong(1, IdConsumption);
                        ResultSet resultSet=callableStatement.executeQuery();
                        while(resultSet.next()){
                            billsModel.setPreviousRate(resultSet.getFloat("LastRate"));
                        }
                    }
                    billsModel.setCorrectHouse(true);
                    billsModel.setExistFirstRegister(true);
                    if(billsModel.getStatus().equals("SIN SERVICIO")){
                        billsModel.setCorrectHouse(false);
                        billsModel.setExistFirstRegister(false);
                        billsModel.setValidationMessage("La vivienda se encuentra sin servicio de agua");
                    }
                }

            }


            callableStatement.close();
            bd.CloseConnection();

        }catch(SQLException e){
            billsModel.setCorrectHouse(false);
            billsModel.setValidationMessage("Ocurrio un error a la hora de traer lo datos de la vivienda");
        }


    }
    public List<WaterBillsModel> waterBills(ConsumptionsModel cm) {
        List<WaterBillsModel> billsModels=new ArrayList<>();
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement cs2 = bd.connection.prepareCall("{call BillsConsumptions(?,?)}");
            cs2.setString(1,cm.getBarCode());
            cs2.setString(2,new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            ResultSet Result=cs2.executeQuery();
            while (Result.next()){
                billsModels.add(new WaterBillsModel(Result.getDate("ReadDate"), Result.getFloat("Rate")));
            }
            cs2.close();
            bd.CloseConnection();

        }catch(SQLException e){
            billsModels=null;
        }
        return billsModels;
    }
    public Float getRateNow(Float totalReadWater) {
        float RateNow =0;
        try{

            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement cs2 = bd.connection.prepareCall("{call GetRateNow(?)}");
            cs2.setFloat(1,totalReadWater);
            ResultSet Result=cs2.executeQuery();
            while (Result.next()){
                RateNow =Result.getFloat("Rate");
            }
            cs2.close();
            bd.CloseConnection();

        }catch(SQLException e){
            RateNow =0;
        }
        return RateNow;
    }

    public boolean FirstConsumptionReading(ConsumptionsModel cm, List<ConsumptionsModel> consumptionsModelList) {
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement cs2 = bd.connection.prepareCall("{call FirstConsumptionReading(?,?,?,?,?)}");
            cs2.setLong(1,cm.getIDUser());
            cs2.setString(2,cm.getBarCode());
            cs2.setFloat(3,cm.getRate());
            cs2.setFloat(4,consumptionsModelList.get(1).getM3());
            cs2.setString(5,consumptionsModelList.get(1).getReadDate());
            cs2.executeUpdate();
            cm.setValidationMessage("Registro realizado correctamente");
            cs2.close();
            bd.CloseConnection();
            return true;

        }catch(SQLException e){
            cm.setValidationMessage("Ocurrio un error al intentar registrar");
            return false;
        }
    }
}
