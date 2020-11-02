package Datos;

public class InicioSesionesGETSET {

    private String Usuario, Contraseña;
    private boolean Status;

    public InicioSesionesGETSET(){

    }

    public InicioSesionesGETSET(String usuario, String contraseña, boolean status) {
        Usuario = usuario;
        Contraseña = contraseña;
        Status = status;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
