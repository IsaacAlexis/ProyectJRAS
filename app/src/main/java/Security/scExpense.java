package Security;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Data.BDConnection;
import Data.Models.ExpensesModel;
import Data.Models.UsersModel;
import Presentation.Expenses.ExpensesAdapter;

public class scExpense {

    BDConnection bd = new BDConnection();
    public void ExpenRegister(ExpensesModel expen){

        try
        {



         bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement cs = bd.connection.prepareCall("{call ExpensesRegister(?,?,?,?,?,?)}");
            cs.setLong(1,expen.getIDUser());
            cs.setString(2,new SimpleDateFormat("yyyy-MM-dd").format(expen.getExpDate()));
            cs.setString(3,expen.getNameExp());
            cs.setString(4,expen.getDescript());
            cs.setFloat(5,expen.getTotal());
            cs.setString(6,expen.getDateModified());
            cs.executeUpdate();
            expen.setRegisterExpens(false);
            expen.setValidationMessage("Se registro con exito el gasto");
            cs.close();
            bd.CloseConnection();



        }
        catch (Exception e){
            expen.setValidationMessage("Ocurrio un problema en registrar el gasto");
            expen.setRegisterExpens(true);
        }

    }
    public void ExpenModify(ExpensesModel expen)
    {
        String currenteDataandTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        try {
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            CallableStatement cs = bd.connection.prepareCall("{call ModifyExpens(?,?,?,?,?)}");
            cs.setLong(1,expen.getFolioExp());
            cs.setString(2, expen.getNameExp());
            cs.setString(3, expen.getDescript());
            cs.setFloat(4,expen.getTotal());
            cs.setString(5,expen.getDateModified());
            cs.executeUpdate();
            expen.setRegisterExpens(false);
            expen.setValidationMessage("Se ha modificado correctamente el gasto");
            cs.close();
            bd.CloseConnection();


        }
        catch (Exception e)
        {
            expen.setValidationMessage("Ocurrio un error en guardar los cambios");
            expen.setRegisterExpens(true);

        }
    }


    public List<ExpensesModel> getallexpenses() {
        UsersModel users=new UsersModel();
        List<ExpensesModel> expenses=new ArrayList<>();
        try {
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            CallableStatement cs = bd.connection.prepareCall("{call GetAllExpenses(?)}");
            cs.setString(1,users.getCurrentColony());
            ResultSet result=cs.executeQuery();
            while (result.next()){
                expenses.add(new ExpensesModel(result.getLong("FolioExp"),
                        result.getString("NameExp"),result.getString("Descript"),
                        result.getFloat("Total"),result.getDate("ExpDate")));

            }
            bd.CloseConnection();
            cs.close();
            return expenses;

        }catch (Exception e){
            e.printStackTrace();
            return null;

        }

    }

    public Long getLastFolio() {
        try
        {
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement =  bd.connection.prepareCall("{call ExpenExist}");
            ResultSet Result = callableStatement.executeQuery();
            Long Folio=null;
            while(Result.next())
            {
               Folio=Result.getLong("FolioExp");
            }
           return Folio;



        }
        catch (Exception e)
        {
           return null;


        }
    }
}
