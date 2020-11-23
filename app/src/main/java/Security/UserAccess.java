package Security;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import Data.BDConnection;
import Data.Models.UsersModel;
import Presentation.Home.loginActivity;

public class UserAccess {
    public UsersModel ValidateUser(UsersModel userData){
        BDConnection bd = new BDConnection();
        try{
            loginActivity pErrorMessage=new loginActivity();
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call UsersLogin(?,?)}");
            callableStatement.setString(1, userData.getUserName());
            callableStatement.setString(2,userData.getPassword());

            ResultSet userResultset = callableStatement.executeQuery();

            userData.setUserLoggedIn(false);
            boolean userAndPasswordIsValid = false;
            while (userResultset.next()) {
                userAndPasswordIsValid = true;
                userData.setCurrentIdUser(userResultset.getLong("IDUser"));
                userData.setCurrentFirstName(userResultset.getString("FirstName"));
                userData.setCurrentLastName(userResultset.getString("LastName"));
                userData.setCurrentUserName(userResultset.getString("Username"));
                userData.setCurrentRole(userResultset.getString("UserRole"));
                userData.setCurrentColony(userResultset.getString("Colony"));
                userData.setUserStatus(userResultset.getString("UserStatus"));
                userData.setUserLoggedIn(!userData.getUserStatus().isEmpty());
                userData.setCurrentExpirationDate(userResultset.getDate("ExpirationDate"));
                Date today = new Date();
                if(!userData.getUserStatus().toUpperCase().equals("ACTIVO")){
                    userData.setUserLoggedIn(false);
                    userData.setFlagUser(true);
                    userData.setValidationMessage("Usuario inactivo. Favor de contactar al administrador del sistema.");
                }else if( userData.getCurrentExpirationDate().before(today)){
                    userData.setUserLoggedIn(false);
                    userData.setFlagUser(true);
                    userData.setValidationMessage("Suscripción del usuario se encuentra expirada. Favor de ponerse en contacto con el administrador del sistema.");

                }

            }
            if(!userAndPasswordIsValid){
                userData.setFlagUser(false);
                userData.setValidationMessage("Usuario y/o Contraseña es incorrecto");
            }
            callableStatement.close();
            bd.CloseConnection();
        } catch (SQLException e) {
            try {
                if(!bd.connection.isClosed()){
                    bd.CloseConnection();
                }
            } catch (SQLException e2) { }
            e.printStackTrace();
        }
        return userData;
    }
    public UsersModel BlocUser(UsersModel userData){
        BDConnection bd = new BDConnection();
        try{
            loginActivity pErrorMessage=new loginActivity();
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call BlockUser(?)}");
            callableStatement.setString(1, userData.getUserName());
            callableStatement.executeUpdate();
            userData.setValidationMessage("Cuenta desactivada. Para cualquier aclaracion consultar al administrador del sistema");
            userData.setUserLoggedIn(true);
            callableStatement.close();
            bd.CloseConnection();
        } catch (SQLException e) {
            try {
                if(!bd.connection.isClosed()){
                    bd.CloseConnection();
                }
            } catch (SQLException e2) { }
            e.printStackTrace();
        }
        return userData;
    }

    public void UserExist(UsersModel userData) {
        BDConnection bd = new BDConnection();
        try{
            loginActivity pErrorMessage=new loginActivity();
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call UserExist(?)}");
            callableStatement.setString(1, userData.getUserName());
            ResultSet Result= callableStatement.executeQuery();

            userData.setUserLoggedIn(false);
            Date today = new Date();
            while (Result.next()){
                userData.setUserStatus(Result.getString("UserStatus"));
                userData.setExpirationDate(Result.getDate("ExpirationDate"));
                userData.setUserLoggedIn(true);
                if(!userData.getUserStatus().toUpperCase().equals("ACTIVO")){


                    userData.setUserLoggedIn(false);
                    userData.setFlagUser(true);
                    userData.setValidationMessage("Usuario inactivo. Favor de contactar al administrador del sistema.");

                }else if( userData.getExpirationDate().before(today)){
                    userData.setUserLoggedIn(false);
                    userData.setFlagUser(true);
                    userData.setValidationMessage("Suscripción del usuario se encuentra expirada. Favor de ponerse en contacto con el administrador del sistema.");

                }
            }
            callableStatement.close();
            bd.CloseConnection();
        } catch (SQLException e) {
            try {
                if(!bd.connection.isClosed()){
                    bd.CloseConnection();
                }
            } catch (SQLException e2) { }
            e.printStackTrace();
        }

    }
}
