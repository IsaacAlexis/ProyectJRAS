package Data.Models;

public class ConsumptionsModel {

    private long IDUser;
    private long IDCost;
    private String BarCode;
    private String ReadDate;
    private float M3;
    private float Rate;
    private String validationMessage;

    public ConsumptionsModel(long IDUser, long IDCost, String barCode, String readDate, float m3, float rate, String validationMessage) {
        this.IDUser = IDUser;
        this.IDCost = IDCost;
        BarCode = barCode;
        ReadDate = readDate;
        M3 = m3;
        Rate = rate;
        this.validationMessage = validationMessage;
    }

    public ConsumptionsModel() {
    }

    public long getIDUser() {
        return IDUser;
    }

    public void setIDUser(long IDUser) {
        this.IDUser = IDUser;
    }

    public long getIDCost() {
        return IDCost;
    }

    public void setIDCost(long IDCost) {
        this.IDCost = IDCost;
    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String barCode) {
        BarCode = barCode;
    }

    public String getReadDate() {
        return ReadDate;
    }

    public void setReadDate(String readDate) {
        ReadDate = readDate;
    }

    public float getM3() {
        return M3;
    }

    public void setM3(float m3) {
        M3 = m3;
    }

    public float getRate() {
        return Rate;
    }

    public void setRate(float rate) {
        Rate = rate;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }
}