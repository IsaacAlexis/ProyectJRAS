package MantenimientoDatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Datos.ConexionBD;
import Datos.InicioSesionesGETSET;

public class MantenimientoSesiones {

    private Statement sentenciaSQL;
    private ConexionBD bd;
    private ResultSet rs;

    public void IniciarSesion(InicioSesionesGETSET ini) throws SQLException {
        String sql;

        try{
            bd = new ConexionBD();
            sentenciaSQL = bd.ConexionConSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            sql = "select * from Users where UserName = '"+ini.getUsuario()+"' and Passw = '"+ini.getContraseña()+"'";
            rs = sentenciaSQL.executeQuery(sql);

            if (rs.next()){
                ini.setStatus(true);
            }
            else{
                ini.setStatus(false);
            }

            rs.close();
            bd.CerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ConectarBD(){
        bd = new ConexionBD();
    }

}
