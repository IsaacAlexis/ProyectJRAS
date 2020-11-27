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

public class UserRegister {


    BDConnection bd = new BDConnection();


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


    public List<UsersModel> getallusers(UsersModel mUsers) {
        List<UsersModel> users=new ArrayList<>();
        try {
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call GetAllUsers}");
            ResultSet result=callableStatement.executeQuery();
            while (result.next()){
                users.add(new UsersModel(result.getLong("IDUser"),result.getString("UserName"),
                        result.getString("FirstName"),result.getString("LastName"),
                        result.getString("Email"),result.getString("UserRole"),result
                .getString("UserStatus")));
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

    }

