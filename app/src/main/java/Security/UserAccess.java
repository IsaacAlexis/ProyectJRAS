package Security;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import Data.BDConnection;
import Data.Models.UsersModel;

public class UserAccess {
    public UsersModel ValidateUser(UsersModel userData){
        BDConnection bd = new BDConnection();
        try{

            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call UsersLogin(?,?)}");
            callableStatement.setString(1, userData.getUserName());
            callableStatement.setString(2,userData.getPass());

            ResultSet userResultset = callableStatement.executeQuery();

            userData.setUserLoggedIn(false);
            boolean userAndPasswordIsValid = false;
            while (userResultset.next()) {
                userAndPasswordIsValid = true;
                userData.setFirstName(userResultset.getString("FirstName"));
                userData.setLastName(userResultset.getString("LastName"));
                userData.setEmail(userResultset.getString("Email"));
                userData.setRole(userResultset.getString("UserRole"));
                userData.setUserStatus(userResultset.getString("UserStatus"));
                userData.setUserLoggedIn(!userData.getUserStatus().isEmpty());
                userData.setExpirationDate(userResultset.getDate("ExpirationDate"));
                Date today = new Date();
                if(!userData.getUserStatus().toUpperCase().equals("ACTIVO")){
                    userData.setUserLoggedIn(false);
                    userData.setValidationMessage("Usuario esta inactivo. Favor de contactar al administrador del sistema.");
                }else if( userData.getExpirationDate().before(today)){
                    userData.setUserLoggedIn(false);
                    userData.setValidationMessage("Subscripcion del usuario se encuentra expirada. Favor de ponerse en contacto con el administrador del sistema.");
                }

            }
            if(!userAndPasswordIsValid){
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
}
