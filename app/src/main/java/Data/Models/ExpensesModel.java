package Data.Models;

import java.util.Date;

public class ExpensesModel {
    private  long FolioExp;
    private long IDUser;
    private Date ExpDate;
    private String NameExp;
    private String Descript;
    private float Total;

    // Bandera
    private boolean isRegisterExpens;

    public ExpensesModel(){

    }

    public ExpensesModel(long folioExp, long IDUser, Date expDate, String nameExp, String descript, float total, boolean isRegisterExpens) {
        FolioExp = folioExp;
        this.IDUser = IDUser;
        ExpDate = expDate;
        NameExp = nameExp;
        Descript = descript;
        Total = total;
        this.isRegisterExpens = isRegisterExpens;
    }

    public long getFolioExp() {
        return FolioExp;
    }

    public void setFolioExp(long folioExp) {
        FolioExp = folioExp;
    }

    public long getIDUser() {
        return IDUser;
    }

    public void setIDUser(long IDUser) {
        this.IDUser = IDUser;
    }

    public Date getExpDate() {
        return ExpDate;
    }

    public void setExpDate(Date expDate) {
        ExpDate = expDate;
    }

    public String getNameExp() {
        return NameExp;
    }

    public void setNameExp(String nameExp) {
        NameExp = nameExp;
    }

    public String getDescript() {
        return Descript;
    }

    public void setDescript(String descript) {
        Descript = descript;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float total) {
        Total = total;
    }

    public boolean isRegisterExpens() {
        return isRegisterExpens;
    }

    public void setRegisterExpens(boolean registerExpens) {
        isRegisterExpens = registerExpens;
    }
}
