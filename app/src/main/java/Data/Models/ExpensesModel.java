package Data.Models;

import android.os.Parcel;
import android.os.Parcelable;

import net.sourceforge.jtds.jdbc.DateTime;

import java.util.Date;

public class ExpensesModel implements Parcelable {
    //Registro gastos
    private long FolioExp;
    private long IDUser;
    private Date ExpDate;
    private String NameExp;
    private String Descript;
    private Float Total;
    private String DateModified;
    // Bandera
    private boolean isRegisterExpens;
    //Mensajes
    private String validationMessage;



    public ExpensesModel(){

    }

    public ExpensesModel(long folioExp, String nameExp, String descript, Float total) {
        this.FolioExp = folioExp;
        this.NameExp = nameExp;
        this.Descript = descript;
        this.Total = total;
    }

    public ExpensesModel(long folioExp, String nameExp, String descript, Float total, Date Expdate) {
        this.FolioExp = folioExp;
        this.NameExp = nameExp;
        this.Descript = descript;
        this.Total = total;
        this.ExpDate=Expdate;
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

    protected ExpensesModel(Parcel in) {
        FolioExp = in.readLong();
        IDUser = in.readLong();
        NameExp = in.readString();
        Descript = in.readString();
        if (in.readByte() == 0) {
            Total = null;
        } else {
            Total = in.readFloat();
        }
        DateModified = in.readString();
        isRegisterExpens = in.readByte() != 0;
        validationMessage = in.readString();
    }

    public static final Creator<ExpensesModel> CREATOR = new Creator<ExpensesModel>() {
        @Override
        public ExpensesModel createFromParcel(Parcel in) {
            return new ExpensesModel(in);
        }

        @Override
        public ExpensesModel[] newArray(int size) {
            return new ExpensesModel[size];
        }
    };


    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {this.validationMessage = validationMessage;}

    public String getDateModified() {
        return DateModified;
    }

    public void setDateModified(String dateModified) {
        DateModified = dateModified;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(FolioExp);
        dest.writeLong(IDUser);
        dest.writeString(NameExp);
        dest.writeString(Descript);
        if (Total == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(Total);
        }
        dest.writeString(DateModified);
        dest.writeByte((byte) (isRegisterExpens ? 1 : 0));
        dest.writeString(validationMessage);
    }
}
