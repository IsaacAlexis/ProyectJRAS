package Data;

public class LoginUsersGETSET {
    private String User;
    private String Pass;
    private boolean Status;

    public LoginUsersGETSET() {
    }

    public LoginUsersGETSET(String user, String pass, boolean status) {
        User = user;
        Pass = pass;
        Status = status;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPass() {
        return Pass;
    }



    public void setPass(String pass) {
        Pass = pass;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }



}
