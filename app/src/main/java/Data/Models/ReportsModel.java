package Data.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.List;

public class ReportsModel implements Parcelable {
    //Datos de los pagos
    private String owner;
    private Float payTotal;
    private Date payDate ;
    //Datos de los gastos
    private String title;
    private String description;
    private Float expenseTotal;
    private Date espenseDate;
    //Datos para realizar la consulta
    private String dateMin;
    private String dateMax;
    //Arreglos de pagos y gastos
    private List<ReportsModel> Pays;
    private List<ReportsModel> Expenses;
    //Administracion de mensajes
    private String validationMessage;
    public ReportsModel() {
    }

    public ReportsModel(String owner, Float payTotal, Date payDate) {
        this.owner = owner;
        this.payTotal = payTotal;
        this.payDate = payDate;
    }

    public ReportsModel(String title, String description, Float expenseTotal, Date espenseDate) {
        this.title = title;
        this.description = description;
        this.expenseTotal = expenseTotal;
        this.espenseDate = espenseDate;
    }

    protected ReportsModel(Parcel in) {
        owner = in.readString();
        if (in.readByte() == 0) {
            payTotal = null;
        } else {
            payTotal = in.readFloat();
        }
        title = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            expenseTotal = null;
        } else {
            expenseTotal = in.readFloat();
        }
        dateMin = in.readString();
        dateMax = in.readString();
        Pays = in.createTypedArrayList(ReportsModel.CREATOR);
        Expenses = in.createTypedArrayList(ReportsModel.CREATOR);
        validationMessage = in.readString();
    }

    public static final Creator<ReportsModel> CREATOR = new Creator<ReportsModel>() {
        @Override
        public ReportsModel createFromParcel(Parcel in) {
            return new ReportsModel(in);
        }

        @Override
        public ReportsModel[] newArray(int size) {
            return new ReportsModel[size];
        }
    };

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Float getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(Float payTotal) {
        this.payTotal = payTotal;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getExpenseTotal() {
        return expenseTotal;
    }

    public void setExpenseTotal(Float expenseTotal) {
        this.expenseTotal = expenseTotal;
    }

    public Date getEspenseDate() {
        return espenseDate;
    }

    public void setEspenseDate(Date espenseDate) {
        this.espenseDate = espenseDate;
    }

    public String getDateMin() {
        return dateMin;
    }

    public void setDateMin(String dateMin) {
        this.dateMin = dateMin;
    }

    public String getDateMax() {
        return dateMax;
    }

    public void setDateMax(String dateMax) {
        this.dateMax = dateMax;
    }

    public List<ReportsModel> getPays() {
        return Pays;
    }

    public void setPays(List<ReportsModel> pays) {
        Pays = pays;
    }

    public List<ReportsModel> getExpenses() {
        return Expenses;
    }

    public void setExpenses(List<ReportsModel> expenses) {
        Expenses = expenses;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    @Override
    public String toString() {
        return "ReportsModel{" +
                "owner='" + owner + '\'' +
                ", payTotal=" + payTotal +
                ", payDate=" + payDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", expenseTotal=" + expenseTotal +
                ", espenseDate=" + espenseDate +
                ", dateMin='" + dateMin + '\'' +
                ", dateMax='" + dateMax + '\'' +
                ", Pays=" + Pays +
                ", Expenses=" + Expenses +
                ", validationMessage='" + validationMessage + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(owner);
        if (payTotal == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(payTotal);
        }
        dest.writeString(title);
        dest.writeString(description);
        if (expenseTotal == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(expenseTotal);
        }
        dest.writeString(dateMin);
        dest.writeString(dateMax);
        dest.writeTypedList(Pays);
        dest.writeTypedList(Expenses);
        dest.writeString(validationMessage);
    }
}
