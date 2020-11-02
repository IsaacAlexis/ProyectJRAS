package Negocios;

import java.sql.SQLException;

import Datos.InicioSesionesGETSET;
import MantenimientoDatos.MantenimientoSesiones;

public class NegocioSesiones {

    InicioSesionesGETSET ini = new InicioSesionesGETSET();

    MantenimientoSesiones manto = new MantenimientoSesiones();

    public void PuenteInicioSesion(InicioSesionesGETSET ini) throws SQLException {
        manto = new MantenimientoSesiones();
        manto.IniciarSesion(ini);
    }

    public void PuenteConectarBD(){
        manto = new MantenimientoSesiones();
        manto.ConectarBD();
    }

}
