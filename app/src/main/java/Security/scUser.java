package Security;

import android.icu.text.SimpleDateFormat;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.CallableStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Data.BDConnection;
import Data.Models.UsersModel;
import Presentation.Home.loginActivity;

public class scUser {

//Login usuarios
    BDConnection bd = new BDConnection();
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
                if(!userData.getUserStatus().toUpperCase().equals("ACTIVA")){
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
            CallableStatement callableStatement=bd.connection.prepareCall("{call UserExistAccess(?)}");
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

//CRUD de usuarios
    public void UserRegister(UsersModel data){

        try{
            UserRegisterExist(data);
            if(data.getUserExist()){
                data.setValidationMessage("Ya existe un usuario con el mismo Nombre de Usuario y/o Correo electronico");
                data.setRegisterUser(true);

            }else{
                bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                CallableStatement cs =bd.connection.prepareCall("{call UsersRegister(?,?,?,?,?,?,?,?,?,?,?)}");
                cs.setString(1,data.getLastName());
                cs.setString(2,data.getFirstName());
                cs.setString(3,data.getEmail());
                cs.setString(4,data.getUserName());
                cs.setString(5,data.getRole());
                cs.setString(6,data.getPassword());
                cs.setString(7,data.getExpirationDate().toString());
                cs.setString(8,data.getColony());
                cs.setString(9,data.getUserStatus());
                cs.setString(10,data.getAddedDate());
                cs.setString(11,data.getModDate());
                cs.executeUpdate();
                data.setValidationMessage("Se registro correctamente el usuario");
                data.setRegisterUser(false);
                cs.close();
                bd.CloseConnection();
            }


        }catch(SQLException e){
            e.printStackTrace();
            data.setRegisterUser(true);
            data.setValidationMessage("No se pudo registrar el usuario intenta de nuevo");
            try {
                if(!bd.connection.isClosed()){
                    bd.CloseConnection();
                }
            } catch (SQLException e2) { }
            e.printStackTrace();

        }
    }

    public void UserRegisterExist(UsersModel data){
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call UserExist(?,?)}");
            callableStatement.setString(1, data.getUserName());
            callableStatement.setString(2, data.getEmail());
            ResultSet Result= callableStatement.executeQuery();
            data.setUserExist(false);
            while(Result.next()){
                data.setUserExist(true);
            }
        }catch(SQLException e){
            data.setValidationMessage("Ocurrio un error al buscar existencia del usuario");
            data.setRegisterUser(true);
            try {
                if(!bd.connection.isClosed()){
                    bd.CloseConnection();
                }
            } catch (SQLException e2) { }
            e.printStackTrace();
        }


    }
    public void UserUpdateExist(UsersModel data){
        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call UserExist(?,?)}");
            callableStatement.setString(1, data.getUserName());
            callableStatement.setString(2, data.getEmail());
            ResultSet Result= callableStatement.executeQuery();
            data.setUserExist(false);
            while(Result.next()){
                if(data.getIdUser()==Result.getLong("IDUser")){
                    data.setUserExist(false);
                }else{
                    data.setUserExist(true);
                }
            }
        }catch(SQLException e){
            data.setValidationMessage("Ocurrio un error al buscar existencia del usuario");
            data.setRegisterUser(true);
            try {
                if(!bd.connection.isClosed()){
                    bd.CloseConnection();
                }
            } catch (SQLException e2) { }
            e.printStackTrace();
        }


    }


    public List<UsersModel> getallusers(UsersModel mUsers) {
        UsersModel usersModel=new UsersModel();
        List<UsersModel> users=new ArrayList<>();
        try {
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call GetAllUsers(?)}");
            callableStatement.setString(1,usersModel.getCurrentColony());
            ResultSet result=callableStatement.executeQuery();
            while (result.next()){
                if(!(usersModel.getCurrentIdUser()==result.getLong("IDUser"))){
                    users.add(new UsersModel(result.getLong("IDUser"),result.getString("UserName"),
                            result.getString("FirstName"),result.getString("LastName"),
                            result.getString("Email"),result.getString("UserRole"),result
                            .getString("UserStatus")));
                }

            }
            callableStatement.close();
            bd.CloseConnection();

        }catch (Exception e){
            users=null;
            try {
                if(!bd.connection.isClosed()){
                    bd.CloseConnection();
                }
            } catch (SQLException e2) { }
            e.printStackTrace();
        }
        return users;

        }

    public void updateUsers(UsersModel data) {
        try{
//            UserUpdateExist(data);
//            if(data.getUserExist()){
//                data.setValidationMessage("Ya existe un usuario con el mismo Nombre de Usuario y/o Correo electronico");
//                data.setRegisterUser(true);
//
//            }else{
                bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                CallableStatement cs =bd.connection.prepareCall("{call updateUsers(?,?,?,?,?,?,?,?,?,?)}");
                cs.setLong(1,data.getIdUser());
                cs.setString(2,data.getLastName());
                cs.setString(3,data.getFirstName());
                cs.setString(4,data.getEmail());
                cs.setString(5,data.getUserName());
                cs.setString(6,data.getRole());
                cs.setString(7,data.getExpirationDate().toString());
                cs.setString(8,data.getColony());
                cs.setString(9,data.getUserStatus());
                cs.setString(10,data.getModDate());
                cs.executeUpdate();
                data.setValidationMessage("Se registro correctamente el usuario");
                data.setRegisterUser(false);
                cs.close();
                bd.CloseConnection();
//            }


        }catch(SQLException e){
            e.printStackTrace();
            data.setRegisterUser(true);
            data.setValidationMessage("Ocurrio un error al guardar los cambios");
            try {
                if(!bd.connection.isClosed()){
                    bd.CloseConnection();
                }
            } catch (SQLException e2) { }
            e.printStackTrace();

        }
    }

}

