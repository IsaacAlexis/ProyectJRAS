package Data.Models;

import android.os.Parcel;
import android.os.Parcelable;

import net.sourceforge.jtds.jdbc.DateTime;

import java.util.Date;

public class UsersModel implements Parcelable {
    private long idUser;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String role;
    private String password;
    private Date expirationDate;
    private String colony;
    private String userStatus;
    private String addedDate;
    private String modDate;
    //Banderas
    private boolean isUserLoggedIn;
    private Boolean flagUser;
    private Boolean UserExist;
    private String validationMessage;
    private boolean isRegisterUser;
   //Datos del usuario que actualmente esta logeado
    private static long currentIdUser;
    private static String currentUserName;
    private static String currentRole;
    private static String currentFirstName;
    private static String currentLastName;
    private static String currentColony;
    private static Date currentExpirationDate;



    public UsersModel() {
    }

    public UsersModel(long idUser, String userName, String firstName, String lastName, String email, String userRole, String userStatus) {
        this.idUser=idUser;
        this.userName=userName;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.role=userRole;
        this.userStatus=userStatus;
    }

    protected UsersModel(Parcel in) {
        idUser = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        userName = in.readString();
        role = in.readString();
        password = in.readString();
        colony = in.readString();
        userStatus = in.readString();
        addedDate = in.readString();
        modDate = in.readString();
        isUserLoggedIn = in.readByte() != 0;
        byte tmpFlagUser = in.readByte();
        flagUser = tmpFlagUser == 0 ? null : tmpFlagUser == 1;
        byte tmpUserExist = in.readByte();
        UserExist = tmpUserExist == 0 ? null : tmpUserExist == 1;
        validationMessage = in.readString();
        isRegisterUser = in.readByte() != 0;
    }

    public static final Creator<UsersModel> CREATOR = new Creator<UsersModel>() {
        @Override
        public UsersModel createFromParcel(Parcel in) {
            return new UsersModel(in);
        }

        @Override
        public UsersModel[] newArray(int size) {
            return new UsersModel[size];
        }
    };

    public boolean isRegisterUser() {
        return isRegisterUser;
    }

    public void setRegisterUser(boolean registerUser) {
        isRegisterUser = registerUser;
    }
    public static Date getCurrentExpirationDate() {
        return currentExpirationDate;
    }

    public static void setCurrentExpirationDate(Date currentExpirationDate) {
        UsersModel.currentExpirationDate = currentExpirationDate;
    }
    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }
    public static String getCurrentLastName() {
        return currentLastName;
    }

    public static void setCurrentLastName(String currentLastName) {
        UsersModel.currentLastName = currentLastName;
    }

    public static String getCurrentColony() {
        return currentColony;
    }

    public static void setCurrentColony(String currentColony) {
        UsersModel.currentColony = currentColony;
    }
    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }



    public boolean isUserLoggedIn() {
        return isUserLoggedIn;
    }

    public void setUserLoggedIn(boolean userLoggedIn) {
        isUserLoggedIn = userLoggedIn;
    }

    public Boolean getFlagUser() {
        return flagUser;
    }

    public void setFlagUser(Boolean flagUser) {
        this.flagUser = flagUser;
    }

    public Boolean getUserExist() {
        return UserExist;
    }

    public void setUserExist(Boolean userExist) {
        UserExist = userExist;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public static long getCurrentIdUser() {
        return currentIdUser;
    }

    public static void setCurrentIdUser(long currentIdUser) {
        UsersModel.currentIdUser = currentIdUser;
    }

    public static String getCurrentUserName() {
        return currentUserName;
    }

    public static void setCurrentUserName(String currentUserName) {
        UsersModel.currentUserName = currentUserName;
    }

    public static String getCurrentRole() {
        return currentRole;
    }

    public static void setCurrentRole(String currentRole) {
        UsersModel.currentRole = currentRole;
    }

    public static String getCurrentFirstName() {
        return currentFirstName;
    }

    public static void setCurrentFirstName(String currentFirstName) {
        UsersModel.currentFirstName = currentFirstName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idUser);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(userName);
        dest.writeString(role);
        dest.writeString(password);
        dest.writeString(colony);
        dest.writeString(userStatus);
        dest.writeString(addedDate);
        dest.writeString(modDate);
        dest.writeByte((byte) (isUserLoggedIn ? 1 : 0));
        dest.writeByte((byte) (flagUser == null ? 0 : flagUser ? 1 : 2));
        dest.writeByte((byte) (UserExist == null ? 0 : UserExist ? 1 : 2));
        dest.writeString(validationMessage);
        dest.writeByte((byte) (isRegisterUser ? 1 : 0));
    }
}
