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


    String currenteDataandTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    public void ExpenRegister(ExpensesModel expen){
        BDConnection bd = new BDConnection();
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
    public void ExpenModify(ExpensesModel expen)
    {
        BDConnection bd = new BDConnection();
        String currenteDataandTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        try {
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            CallableStatement cs = bd.connection.prepareCall("{call ExpenModify(?,?,?,?,?,?)}");
            cs.setInt(1,Integer.parseInt(String.valueOf(expen.getFolioExp())));
            cs.setInt(2,Integer.parseInt(String.valueOf(expen.getIDUser() ) ) );
            cs.setDate(3, java.sql.Date.valueOf(currenteDataandTime) );
            cs.setString(4,expen.getNameExp());
            cs.setString(5,expen.getDescript());
            cs.setFloat(6,expen.getTotal());
            cs.executeUpdate();
            cs.close();
            bd.CloseConnection();


        }
        catch (Exception e)
        {
            e.printStackTrace();
            expen.setRegisterExpens(true);

        }
    }
}
