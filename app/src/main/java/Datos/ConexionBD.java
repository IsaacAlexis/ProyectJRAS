package Datos;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {

    private Connection conexion = null;
    private Statement sentenciaSQL;
    private ResultSet resultado;

    public void ConexiononSQL() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
        String cadena = "jdbc:jtds:sqlserver://jras-2020.database.windows.net;databaseName=JRAS;user=serveradmin;password=JRAS123.;";
        conexion = DriverManager.getConnection(cadena);
    }

    public Connection ConexionConSQL(){
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            String cadena = "jdbc:jtds:sqlserver://jras-2020.database.windows.net;databaseName=JRAS;user=serveradmin;password=JRAS123.;";
            conexion = DriverManager.getConnection(cadena);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return conexion;
    }

    //public Connection Conectarse(){
    //return conexion;
    //}

    public void CerrarConexion() throws SQLException {
        if (resultado != null) resultado.close();
        if (sentenciaSQL != null) sentenciaSQL.close();
        if (conexion != null) conexion.close();
    }

}
