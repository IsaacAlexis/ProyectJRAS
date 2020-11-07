package Data.Models;

import java.util.Date;

public class UsersModel {
    private String userName;
    private String pass;
    private boolean isUserLoggedIn;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String userStatus;
    private String validationMessage;

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    private Date expirationDate;

    public UsersModel(String userName, String pass, boolean isUserLoggedIn, String firstName, String lastName, String email, String role, String userStatus) {
        this.userName = userName;
        this.pass = pass;
        this.isUserLoggedIn = isUserLoggedIn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.userStatus = userStatus;
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



    public void setPass(String pwdValue) {
        pass = pwdValue;
    }

    public boolean getUserLoggedIn() {
        return isUserLoggedIn;
    }

    public void setUserLoggedIn(boolean statusValue) {
        isUserLoggedIn = statusValue;
    }



}
