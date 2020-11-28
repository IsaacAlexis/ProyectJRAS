package Data.Models;

import java.util.Date;

public class ExpensesModel {
    //Registro gastos
    private  long FolioExp;
    private long IDUser;
    private Date ExpDate;
    private String NameExp;
    private String Descript;
    private float Total;
    //Modificaciones gastos
    private static long modifyFolioExp;
    private static long modifyIDUser;
    private static Date modifyExpDate;
    private static String modifyNameExp;
    private static String modifyDescript;
    private static float modifyTotal;
    // Bandera
    private boolean isRegisterExpens;

    public ExpensesModel(){

    }

    public ExpensesModel(long folioExp, long IDUser, Date expDate, String nameExp, String descript, float total, boolean isRegisterExpens) {
        this.FolioExp = folioExp;
        this.IDUser = IDUser;
        this.ExpDate = expDate;
        this.NameExp = nameExp;
        this.Descript = descript;
        this.Total = total;
        this.isRegisterExpens = isRegisterExpens;
    }
    public static long getModifyFolioExp() {
        return modifyFolioExp;
    }

    public static void setModifyFolioExp(long modifyFolioExp) {
        ExpensesModel.modifyFolioExp = modifyFolioExp;
    }

    public static long getModifyIDUser() {
        return modifyIDUser;
    }

    public static void setModifyIDUser(long modifyIDUser) {
        ExpensesModel.modifyIDUser = modifyIDUser;
    }

    public static Date getModifyExpDate() {
        return modifyExpDate;
    }

    public static void setModifyExpDate(Date modifyExpDate) {
        ExpensesModel.modifyExpDate = modifyExpDate;
    }

    public static String getModifyNameExp() {
        return modifyNameExp;
    }

    public static void setModifyNameExp(String modifyNameExp) {
        ExpensesModel.modifyNameExp = modifyNameExp;
    }

    public static String getModifyDescript() {
        return modifyDescript;
    }

    public static void setModifyDescript(String modifyDescript) {
        ExpensesModel.modifyDescript = modifyDescript;
    }

    public static float getModifyTotal() {
        return modifyTotal;
    }

    public static void setModifyTotal(float modifyTotal) {
        ExpensesModel.modifyTotal = modifyTotal;
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
