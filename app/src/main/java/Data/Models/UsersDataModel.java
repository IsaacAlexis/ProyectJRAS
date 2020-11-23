package Data.Models;

public class UsersDataModel {

    //Declaracion de Variables
    private String IDUser;
    private String LastName;
    private String FirstName;
    private String Email;
    private String UserName;
    private String UserRole;
    private String Password;
    private String ExpirationDate;
    private String Colony;
    private String UserStatus;
    private String DateAdded;
    private String DateModified;

    private boolean UserExist = false;


    public UsersDataModel(){

    }

    //Constructor
    public UsersDataModel(String IDUser, String lastName, String firstName, String email, String userName, String userRole, String password, String expirationDate, String colony, String userStatus, String dateAdded, String dateModified, boolean userExist) {
        this.IDUser = IDUser;
        LastName = lastName;
        FirstName = firstName;
        Email = email;
        UserName = userName;
        UserRole = userRole;
        Password = password;
        ExpirationDate = expirationDate;
        Colony = colony;
        UserStatus = userStatus;
        DateAdded = dateAdded;
        DateModified = dateModified;
        UserExist = userExist;
    }



    //Getter and Setter
    public String getIDUser() {
        return IDUser;
    }

    public void setIDUser(String IDUser) {
        this.IDUser = IDUser;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        ExpirationDate = expirationDate;
    }

    public String getColony() {
        return Colony;
    }

    public void setColony(String colony) {
        Colony = colony;
    }

    public String getUserStatus() {
        return UserStatus;
    }

    public void setUserStatus(String userStatus) {
        UserStatus = userStatus;
    }

    public String getDateAdded() {
        return DateAdded;
    }

    public void setDateAdded(String dateAdded) {
        DateAdded = dateAdded;
    }

    public String getDateModified() {
        return DateModified;
    }

    public void setDateModified(String dateModified) {
        DateModified = dateModified;
    }

    public boolean isUserExist() {
        return UserExist;
    }

    public void setUserExist(boolean userExist) {
        UserExist = userExist;
    }
}
