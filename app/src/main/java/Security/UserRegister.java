package Security;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Data.BDConnection;
import Data.Models.UsersModel;

public class UserRegister {


    BDConnection bd = new BDConnection();
    public void UserRegister(UsersModel data){

        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement cs =bd.connection.prepareCall("{call UsersRegister(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1,data.getLastName());
            cs.setString(2,data.getFirstName());
            cs.setString(3,data.getEmail());
            cs.setString(4,data.getUserName());
            cs.setString(5,data.getRole());
            cs.setString(6,data.getPassword());
            cs.setString(7,data.getExpirationDate().toString());
            cs.setString(8,data.getColony());
            cs.setString(9,data.getUserStatus());
            //cs.setString(10,data.getAddeddDate());
            //cs.setString(11,data.getModifiedDate());
            cs.executeUpdate();


            cs.close();
            bd.CloseConnection();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void UserRegisterExist(UsersModel data){



        try{
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement=bd.connection.prepareCall("{call UserExist(?)}");
            callableStatement.setString(1, data.getUserName());
            ResultSet Result= callableStatement.executeQuery();

            if (Result.next()){
                data.setUserExist(true);
            }
            else{
                data.setUserExist(false);
            }

        }catch(SQLException e){
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

