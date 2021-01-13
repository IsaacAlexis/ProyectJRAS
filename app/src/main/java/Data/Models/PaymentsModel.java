package Data.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.FloatRange;

import java.io.File;
import java.util.Date;
import java.util.List;

public class PaymentsModel implements Parcelable {
    private long Rfolio;
    private String Rowner;
    private Date RpayDate;
    private Float Rtotal;

    private String owner;
    private String barcode;
    private Float total;
    private Float debitTotal;
    private Integer debitPeriod;
    private String status;
    private String street;
    private Integer houseNumber;
    private Float amountPay;
    private File filepath;

    //Informacion de adeudos
    private  List<PaymentsModel> Debits;//Ordenados de manera descendente
    private  List<PaymentsModel> dataPays;
    private Date ReadDate;
    private Float Rate;
    private String Description;
    private Float payTotal;
    private Long IdConsumption;






    //Banderas de validacion de acciones
    private  String ValidationMessage;






    public PaymentsModel() {
    }
//    Se agregan los valores para el arreglo del RecyclerView
    public PaymentsModel( String rowner, Date rpayDate, Float rtotal) {
//        Rfolio = rfolio;
        Rowner = rowner;
        RpayDate = rpayDate;
        Rtotal = rtotal;
    }
//Constructor de la vista de pagos
    public PaymentsModel(String owner, String barcode, Float total, Integer debitPeriod, String status,String street,Integer houseNumber) {
        this.owner = owner;
        this.barcode = barcode;
        this.total = total;
        this.debitPeriod = debitPeriod;
        this.status = status;
        this.street=street;
        this.houseNumber=houseNumber;
    }
//Constructor para los adeudos descente
    public PaymentsModel(Date readDate, Float rate,Long idConsumption) {
        ReadDate = readDate;
        Rate = rate;
        IdConsumption = idConsumption;
    }

    public PaymentsModel(Date readDate, Float rate, String description) {
        ReadDate = readDate;
        Rate = rate;
        Description = description;
    }


    protected PaymentsModel(Parcel in) {
        Rfolio = in.readLong();
        Rowner = in.readString();
        if (in.readByte() == 0) {
            Rtotal = null;
        } else {
            Rtotal = in.readFloat();
        }
        owner = in.readString();
        barcode = in.readString();
        if (in.readByte() == 0) {
            total = null;
        } else {
            total = in.readFloat();
        }
        if (in.readByte() == 0) {
            debitTotal = null;
        } else {
            debitTotal = in.readFloat();
        }
        if (in.readByte() == 0) {
            debitPeriod = null;
        } else {
            debitPeriod = in.readInt();
        }
        status = in.readString();
        street = in.readString();
        if (in.readByte() == 0) {
            houseNumber = null;
        } else {
            houseNumber = in.readInt();
        }
        Debits = in.createTypedArrayList(PaymentsModel.CREATOR);
        if (in.readByte() == 0) {
            Rate = null;
        } else {
            Rate = in.readFloat();
        }
        Description = in.readString();
        if (in.readByte() == 0) {
            payTotal = null;
        } else {
            payTotal = in.readFloat();
        }
        if (in.readByte() == 0) {
            IdConsumption = null;
        } else {
            IdConsumption = in.readLong();
        }
        ValidationMessage = in.readString();
    }

    public static final Creator<PaymentsModel> CREATOR = new Creator<PaymentsModel>() {
        @Override
        public PaymentsModel createFromParcel(Parcel in) {
            return new PaymentsModel(in);
        }

        @Override
        public PaymentsModel[] newArray(int size) {
            return new PaymentsModel[size];
        }
    };

    public long getRfolio() {
        return Rfolio;
    }

    public void setRfolio(long rfolio) {
        Rfolio = rfolio;
    }

    public String getRowner() {
        return Rowner;
    }

    public void setRowner(String rowner) {
        Rowner = rowner;
    }

    public Date getRpayDate() {
        return RpayDate;
    }

    public void setRpayDate(Date rpayDate) {
        RpayDate = rpayDate;
    }

    public Float getRtotal() {
        return Rtotal;
    }

    public void setRtotal(Float rtotal) {
        Rtotal = rtotal;
    }


    public Date getReadDate() {
        return ReadDate;
    }

    public void setReadDate(Date readDate) {
        ReadDate = readDate;
    }

    public Float getRate() {
        return Rate;
    }

    public void setRate(Float rate) {
        Rate = rate;
    }

    public List<PaymentsModel> getDebits() {
        return Debits;
    }

    public void setDebits(List<PaymentsModel> debits) {
       this.Debits = debits;
    }

    public Long getIdConsumption() {
        return IdConsumption;
    }

    public void setIdConsumption(Long idConsumption) {
        IdConsumption = idConsumption;
    }

    public Float getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(Float payTotal) {
        this.payTotal = payTotal;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getDebitTotal() {
        return debitTotal;
    }

    public void setDebitTotal(Float debitTotal) {
        this.debitTotal = debitTotal;
    }

    public Integer getDebitPeriod() {
        return debitPeriod;
    }

    public void setDebitPeriod(Integer debitPeriod) {
        this.debitPeriod = debitPeriod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getValidationMessage() {
        return ValidationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        ValidationMessage = validationMessage;
    }

    public Float getAmountPay() {
        return amountPay;
    }

    public void setAmountPay(Float amountPay) {
        this.amountPay = amountPay;
    }

    public List<PaymentsModel> getDataPays() {
        return dataPays;
    }

    public void setDataPays(List<PaymentsModel> dataPays) {
        this.dataPays = dataPays;
    }

    public File getFilepath() {
        return filepath;
    }

    public void setFilepath(File filepath) {
        this.filepath = filepath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(Rfolio);
        dest.writeString(Rowner);
        if (Rtotal == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(Rtotal);
        }
        dest.writeString(owner);
        dest.writeString(barcode);
        if (total == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(total);
        }
        if (debitTotal == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(debitTotal);
        }
        if (debitPeriod == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(debitPeriod);
        }
        dest.writeString(status);
        dest.writeString(street);
        if (houseNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(houseNumber);
        }
        dest.writeTypedList(Debits);
        if (Rate == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(Rate);
        }
        dest.writeString(Description);
        if (payTotal == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(payTotal);
        }
        if (IdConsumption == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(IdConsumption);
        }
        dest.writeString(ValidationMessage);
    }
}
