package Security;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Data.BDConnection;
import Data.Models.ExpensesModel;
import Data.Models.UsersModel;

public class ExpensRegister {

    BDConnection bd = new BDConnection();

    public void ExpenRegister(ExpensesModel expen){
        String currenteDataandTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        try
        {
            Date cal = (Date) Calendar.getInstance().getTime();

         bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement cs = bd.connection.prepareCall("{call ExpensesRegister(?,?,?,?,?)}");
            cs.setInt(1,Integer.parseInt(String.valueOf(expen.getIDUser() ) ) );
            cs.setDate(2, java.sql.Date.valueOf(currenteDataandTime) );
            cs.setString(3,expen.getNameExp());
            cs.setString(4,expen.getDescript());
            cs.setFloat(5,expen.getTotal());
            cs.executeUpdate();
            cs.close();
            bd.CloseConnection();

        }
        catch (Exception e){

            e.printStackTrace();
            expen.setRegisterExpens(true);
        }

    }
    public void ExpensExist (ExpensesModel expen)
    {
        try
        {
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement =  bd.connection.prepareCall("{call ExpenExist(?)}");
            callableStatement.setLong(1,expen.getFolioExp());
            ResultSet Result = callableStatement.executeQuery();
            while(Result.next())
            {
                expen.setFolioExp(Result.getLong("FolioExp"));
            }
            Result = callableStatement.executeQuery();

        }
        catch (Exception e)
        {


        }

    }

}
