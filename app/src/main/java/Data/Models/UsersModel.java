package Data.Models;

import java.util.Date;

public class UsersModel {

    //Declaracion de variables
    private boolean isUserLoggedIn;
    private Boolean flagUser;
    private Boolean UserExist = false;

    private String userName;
    private String pass;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String colony;
    private String userStatus;
    private String validationMessage;

    private Date expirationDate;
    private String expDate;
    private String addeddDate;
    private String modifiedDate;

    //Constructor
    public UsersModel(boolean isUserLoggedIn, Boolean flagUser, String userName, String pass, String firstName, String lastName, String email, String role, String colony, String userStatus, String validationMessage, Date expirationDate, String expDate, String addeddDate, String modifiedDate) {
        this.isUserLoggedIn = isUserLoggedIn;
        this.flagUser = flagUser;
        this.userName = userName;
        this.pass = pass;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.colony = colony;
        this.userStatus = userStatus;
        this.validationMessage = validationMessage;
        this.expirationDate = expirationDate;
        this.expDate = expDate;
        this.addeddDate = addeddDate;
        this.modifiedDate = modifiedDate;
    }

    public UsersModel() {
    }
    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public UsersModel(String user, String pass) {
        this.userName = user;
        this.pass = pass;
    }


    //Getter and setter
    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUser(String userValue) {
        userName = userValue;
    }

    public String getPass() {
        return pass;
    }

    public Boolean getFlagUser() {
        return flagUser;
    }

    public void setFlagUser(Boolean flagUser) {
        this.flagUser = flagUser;
    }

    public void setPass(String pwdValue) {
        pass = pwdValue;
    }

    public boolean getUserLoggedIn() {
        return isUserLoggedIn;
    }

    public void setUserLoggedIn(boolean statusValue) {
        isUserLoggedIn = statusValue;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getAddeddDate() {
        return addeddDate;
    }

    public void setAddeddDate(String addeddDate) {
        this.addeddDate = addeddDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public Boolean getUserExist() {
        return UserExist;
    }

    public void setUserExist(Boolean userExist) {
        UserExist = userExist;
    }
}
