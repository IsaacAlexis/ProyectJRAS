package Data;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDConnection {

    public Connection connection = null;




    public Connection ConnectionwithSQL(){
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            String url = "jdbc:jtds:sqlserver://jras-2020.database.windows.net;databaseName=JRAS;user=serveradmin;password=JRAS123.;";
             connection = DriverManager.getConnection(url);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }



    public void CloseConnection() throws SQLException {
        if (connection != null) connection.close();
    }

}
