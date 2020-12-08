package Data.Models;

import java.util.Date;

public class PaysModel {
    //Registro de gastos
        //Base de Datos
            private long FolioPay;
            private long IDUserPay;
            private long IDConsuPay;
            private Date DatePay;
            private String BarCodePay;
            private float TotalPay;
        //Interfaz de aplicación
            private String Owner;
            private int HouseNum;
    // Modificaciones de los pagos
            private static long modifyFolioPay;
            private static long modifyIDUserPay;
            private static long modifyIDConsuPay;
            private static Date modifyDatePay;
            private static String modifyBarCodePay;
            private static float modifyTotalPay;
            private static String modifyOwner;
            private static int modifyHouseNum;
    //Bandera
            private boolean isRegisterPay;
            private boolean isModifyPay;
    //Mensajes
            private String validationMessage;

    public PaysModel() {
    }

    public PaysModel(long folioPay, Date datePay, String barCodePay, float totalPay) {
        FolioPay = folioPay;
        DatePay = datePay;
        BarCodePay = barCodePay;
        TotalPay = totalPay;
    }

    public PaysModel(long IDUserPay, long IDConsuPay, String owner, int houseNum, boolean isRegisterPay, boolean isModifyPay, String validationMessage) {
        this.IDUserPay = IDUserPay;
        this.IDConsuPay = IDConsuPay;
        Owner = owner;
        HouseNum = houseNum;
        this.isRegisterPay = isRegisterPay;
        this.isModifyPay = isModifyPay;
        this.validationMessage = validationMessage;
    }

    public long getFolioPay() {
        return FolioPay;
    }

    public void setFolioPay(long folioPay) {
        FolioPay = folioPay;
    }

    public long getIDUserPay() {
        return IDUserPay;
    }

    public void setIDUserPay(long IDUserPay) {
        this.IDUserPay = IDUserPay;
    }

    public long getIDConsuPay() {
        return IDConsuPay;
    }

    public void setIDConsuPay(long IDConsuPay) {
        this.IDConsuPay = IDConsuPay;
    }

    public Date getDatePay() {
        return DatePay;
    }

    public void setDatePay(Date datePay) {
        DatePay = datePay;
    }

    public String getBarCodePay() {
        return BarCodePay;
    }

    public void setBarCodePay(String barCodePay) {
        BarCodePay = barCodePay;
    }

    public float getTotalPay() {
        return TotalPay;
    }

    public void setTotalPay(float totalPay) {
        TotalPay = totalPay;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public int getHouseNum() {
        return HouseNum;
    }

    public void setHouseNum(int houseNum) {
        HouseNum = houseNum;
    }

    public long getModifyFolioPay() {
        return modifyFolioPay;
    }

    public void setModifyFolioPay(long modifyFolioPay) {
        this.modifyFolioPay = modifyFolioPay;
    }

    public long getModifyIDUserPay() {
        return modifyIDUserPay;
    }

    public void setModifyIDUserPay(long modifyIDUserPay) {
        this.modifyIDUserPay = modifyIDUserPay;
    }

    public long getModifyIDConsuPay() {
        return modifyIDConsuPay;
    }

    public void setModifyIDConsuPay(long modifyIDConsuPay) {
        this.modifyIDConsuPay = modifyIDConsuPay;
    }

    public Date getModifyDatePay() {
        return modifyDatePay;
    }

    public void setModifyDatePay(Date modifyDatePay) {
        this.modifyDatePay = modifyDatePay;
    }

    public String getModifyBarCodePay() {
        return modifyBarCodePay;
    }

    public void setModifyBarCodePay(String modifyBarCodePay) {
        this.modifyBarCodePay = modifyBarCodePay;
    }

    public float getModifyTotalPay() {
        return modifyTotalPay;
    }

    public void setModifyTotalPay(float modifyTotalPay) {
        this.modifyTotalPay = modifyTotalPay;
    }

    public String getModifyOwner() {
        return modifyOwner;
    }

    public void setModifyOwner(String modifyOwner) {
        this.modifyOwner = modifyOwner;
    }

    public int getModifyHouseNum() {
        return modifyHouseNum;
    }

    public void setModifyHouseNum(int modifyHouseNum) {
        this.modifyHouseNum = modifyHouseNum;
    }

    public boolean isRegisterPay() {
        return isRegisterPay;
    }

    public void setRegisterPay(boolean registerPay) {
        isRegisterPay = registerPay;
    }

    public boolean isModifyPay() {
        return isModifyPay;
    }

    public void setModifyPay(boolean modifyPay) {
        isModifyPay = modifyPay;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }
}

