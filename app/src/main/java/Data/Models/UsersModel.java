package Data.Models;

import net.sourceforge.jtds.jdbc.DateTime;

import java.util.Date;

public class UsersModel {
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
    //Datos para la modifiacion de algun usuario
    private static long modifyIdUser;
    private static String modifyFirstName;
    private static String modifyLastName;
    private static String modifyEmail;
    private static String modifyUsername;
    private static String modifyRole;
    private static String modifyPassword;
    private static String modifyColony;
    private static String modifyStatus;
    private static Date modifyExpirationDate;
    private static String modifyDateAdded;
    private static String modifyDateModify;

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

    public static long getModifyIdUser() {
        return modifyIdUser;
    }

    public static void setModifyIdUser(long modifyIdUser) {
        UsersModel.modifyIdUser = modifyIdUser;
    }

    public static String getModifyFirstName() {
        return modifyFirstName;
    }

    public static void setModifyFirstName(String modifyFirstName) {
        UsersModel.modifyFirstName = modifyFirstName;
    }

    public static String getModifyLastName() {
        return modifyLastName;
    }

    public static void setModifyLastName(String modifyLastName) {
        UsersModel.modifyLastName = modifyLastName;
    }

    public static String getModifyEmail() {
        return modifyEmail;
    }

    public static void setModifyEmail(String modifyEmail) {
        UsersModel.modifyEmail = modifyEmail;
    }

    public static String getModifyUsername() {
        return modifyUsername;
    }

    public static void setModifyUsername(String modifyUsername) {
        UsersModel.modifyUsername = modifyUsername;
    }

    public static String getModifyRole() {
        return modifyRole;
    }

    public static void setModifyRole(String modifyRole) {
        UsersModel.modifyRole = modifyRole;
    }

    public static String getModifyPassword() {
        return modifyPassword;
    }

    public static void setModifyPassword(String modifyPassword) {
        UsersModel.modifyPassword = modifyPassword;
    }

    public static String getModifyColony() {
        return modifyColony;
    }

    public static void setModifyColony(String modifyColony) {
        UsersModel.modifyColony = modifyColony;
    }

    public static String getModifyStatus() {
        return modifyStatus;
    }

    public static void setModifyStatus(String modifyStatus) {
        UsersModel.modifyStatus = modifyStatus;
    }

    public static Date getModifyExpirationDate() {
        return modifyExpirationDate;
    }

    public static void setModifyExpirationDate(Date modifyExpirationDate) {
        UsersModel.modifyExpirationDate = modifyExpirationDate;
    }

    public static String getModifyDateAdded() {
        return modifyDateAdded;
    }

    public static void setModifyDateAdded(String modifyDateAdded) {
        UsersModel.modifyDateAdded = modifyDateAdded;
    }

    public static String getModifyDateModify() {
        return modifyDateModify;
    }

    public static void setModifyDateModify(String modifyDateModify) {
        UsersModel.modifyDateModify = modifyDateModify;
    }
}
