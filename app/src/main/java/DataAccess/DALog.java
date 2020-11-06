package DataAccess;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import Data.BDConnection;
import Data.LoginUsersGETSET;

public class DALog {


    private BDConnection bd;



    public void LoginUsers(LoginUsersGETSET Login) throws SQLException {


        try{
            bd = new BDConnection();

            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call UsersLogin(?,?,?)}");
            callableStatement.setString(1,Login.getUser().toString());
            callableStatement.setString(2,Login.getPass().toString());
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.execute();
            callableStatement.getMoreResults();
            int value=callableStatement.getInt(3);
            if (value> 0) {
                Login.setStatus(true);
            } else {
                Login.setStatus(false);
            }


            callableStatement.close();
            bd.CloseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
