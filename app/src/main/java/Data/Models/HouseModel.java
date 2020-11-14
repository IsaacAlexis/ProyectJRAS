package Data.Models;

import android.provider.ContactsContract;

import java.math.BigInteger;

public class HouseModel {
    public String barCode="VIV-2020";
    public String owner="Adrian Dominguez";
    public Long phoneNumber=Long.parseLong("6391722102");
    public String email="ebberdominguez@gmail.com";
    public String street="calle 24 de febrero";
    public Integer houseNumber=Integer.parseInt("203");
    public Integer zipCode=Integer.parseInt("33029");
    public String  colony="Tec";
    public String city="Delicias";
    public String state="Chihuahua";
    public String statusHouse="Sin adeudo";
    public boolean statusActivity;

    public HouseModel() {
    }
    public boolean getStatusActivity() {
        return statusActivity;
    }

    public void setStatusActivity(boolean statusActivity) {
        this.statusActivity = statusActivity;
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
}
