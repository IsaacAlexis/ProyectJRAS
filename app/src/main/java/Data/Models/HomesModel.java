package Data.Models;

public class HomesModel {

    //Declaracion de variables
    private String BarCode;
    private String Owner;
    private String PhoneNum;
    private String Email;
    private String Street;
    private String HouseHum;
    private String ZipCode;
    private String Colony;
    private String City;
    private String State;
    private String HouseStatus;
    private boolean HouseExist;

    //Constructor
    public HomesModel(String barCode, String owner, String phoneNum, String email, String street, String houseHum, String zipCode, String colony, String city, String state, String houseStatus, boolean houseExist) {
        BarCode = barCode;
        Owner = owner;
        PhoneNum = phoneNum;
        Email = email;
        Street = street;
        HouseHum = houseHum;
        ZipCode = zipCode;
        Colony = colony;
        City = city;
        State = state;
        HouseStatus = houseStatus;
        HouseExist = houseExist;
    }

    public HomesModel(){

    }

    //Getter and Setter
    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String barCode) {
        BarCode = barCode;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getHouseHum() {
        return HouseHum;
    }

    public void setHouseHum(String houseHum) {
        HouseHum = houseHum;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getColony() {
        return Colony;
    }

    public void setColony(String colony) {
        Colony = colony;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getHouseStatus() {
        return HouseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        HouseStatus = houseStatus;
    }

    public boolean isHouseExist() {
        return HouseExist;
    }

    public void setHouseExist(boolean houseExist) {
        HouseExist = houseExist;
    }
}
