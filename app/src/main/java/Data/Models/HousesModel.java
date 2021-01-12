package Data.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class HousesModel implements Parcelable {
    public String barCode;
    public String owner;
    public Long phoneNumber;
    public String email;
    public String street;
    public Integer houseNumber;
    public Integer zipCode;
    public String  colony;
    public String city;
    public String state;
    public String statusHouse;
    //Banderas
    public boolean statusActivity;
    public boolean existHouse;
    public String message;

    public HousesModel() {
    }
    public HousesModel(String barCode, String owner,
                       Long phoneNumber, String email, String street, Integer houseNumber, Integer zipCode,
                       String colony, String city, String state, String statusHouse) {
        this.barCode = barCode;
        this.owner = owner;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.colony = colony;
        this.city = city;
        this.state = state;
        this.statusHouse = statusHouse;
    }


    protected HousesModel(Parcel in) {
        barCode = in.readString();
        owner = in.readString();
        if (in.readByte() == 0) {
            phoneNumber = null;
        } else {
            phoneNumber = in.readLong();
        }
        email = in.readString();
        street = in.readString();
        if (in.readByte() == 0) {
            houseNumber = null;
        } else {
            houseNumber = in.readInt();
        }
        if (in.readByte() == 0) {
            zipCode = null;
        } else {
            zipCode = in.readInt();
        }
        colony = in.readString();
        city = in.readString();
        state = in.readString();
        statusHouse = in.readString();
        statusActivity = in.readByte() != 0;
        existHouse = in.readByte() != 0;
        message = in.readString();
    }

    public static final Creator<HousesModel> CREATOR = new Creator<HousesModel>() {
        @Override
        public HousesModel createFromParcel(Parcel in) {
            return new HousesModel(in);
        }

        @Override
        public HousesModel[] newArray(int size) {
            return new HousesModel[size];
        }
    };

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatusHouse() {
        return statusHouse;
    }

    public void setStatusHouse(String statusHouse) {
        this.statusHouse = statusHouse;
    }

    public boolean isStatusActivity() {
        return statusActivity;
    }

    public void setStatusActivity(boolean statusActivity) {
        this.statusActivity = statusActivity;
    }

    public boolean isExistHouse() {
        return existHouse;
    }

    public void setExistHouse(boolean existHouse) {
        this.existHouse = existHouse;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(barCode);
        dest.writeString(owner);
        if (phoneNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(phoneNumber);
        }
        dest.writeString(email);
        dest.writeString(street);
        if (houseNumber == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(houseNumber);
        }
        if (zipCode == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(zipCode);
        }
        dest.writeString(colony);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(statusHouse);
        dest.writeByte((byte) (statusActivity ? 1 : 0));
        dest.writeByte((byte) (existHouse ? 1 : 0));
        dest.writeString(message);
    }


}
