package Data.Models;

import java.util.Date;
import java.util.List;

public class WaterBillsModel {
    private  String email;
    private  String phone;
    private  String BarCode;
    private  String Owner;
    private  String Street;
    private  String Colony;
    private  String Status;
    private  Integer ZipCode;
    private  Integer HouseNumber;
    private  Float PreviousRate;
    private  Float ReadLast;
    private String ValidationMessage;
    private boolean CorrectHouse;
    private boolean existFirstRegister;
    private Float ReadNow;
    private Float NowRate;
    private String ReadDate;
    private List<WaterBillsModel> bills;
    //----Variables para descripcion de adeudo en recibo de agua
    private String bBarCode;
    private String bOwner;
    private String bStreet;
    private String bColony;
    private Integer bHouseNumber;
    private Date bReadDate;
    private Float bReadNow;
    private Float bNowRate;
    private String bNameFile;








//    private static Float ReadTotal;


    public WaterBillsModel() {
    }

    public WaterBillsModel(String bBarCode, String bOwner, String bStreet, String bColony, Integer bHouseNumber, Date bReadDate, Float bReadNow, Float bNowRate,String bNameFile) {
        this.bBarCode = bBarCode;
        this.bOwner = bOwner;
        this.bStreet = bStreet;
        this.bColony = bColony;
        this.bHouseNumber = bHouseNumber;
        this.bReadDate = bReadDate;
        this.bReadNow = bReadNow;
        this.bNowRate = bNowRate;
        this.bNameFile=bNameFile;
    }



    public WaterBillsModel(Date bReadDate, Float bNowRate) {
        this.bReadDate = bReadDate;
        this.bNowRate = bNowRate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getColony() {
        return Colony;
    }

    public void setColony(String colony) {
        Colony = colony;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Integer getZipCode() {
        return ZipCode;
    }

    public void setZipCode(Integer zipCode) {
        ZipCode = zipCode;
    }

    public Integer getHouseNumber() {
        return HouseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        HouseNumber = houseNumber;
    }

    public Float getPreviousRate() {
        return PreviousRate;
    }

    public void setPreviousRate(Float previousRate) {
        PreviousRate = previousRate;
    }

    public Float getReadLast() {
        return ReadLast;
    }

    public void setReadLast(Float readLast) {
        ReadLast = readLast;
    }

    public boolean isExistFirstRegister() {
        return existFirstRegister;
    }

    public void setExistFirstRegister(boolean existFirstRegister) {
        this.existFirstRegister = existFirstRegister;
    }

    public Float getReadNow() {
        return ReadNow;
    }

    public void setReadNow(Float readNow) {
        ReadNow = readNow;
    }

    public Float getNowRate() {
        return NowRate;
    }

    public void setNowRate(Float nowRate) {
        NowRate = nowRate;
    }

    public String getReadDate() {
        return ReadDate;
    }

    public void setReadDate(String readDate) {
        ReadDate = readDate;
    }


    public String getValidationMessage() {
        return ValidationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        ValidationMessage = validationMessage;
    }

    public boolean isCorrectHouse() {
        return CorrectHouse;
    }

    public void setCorrectHouse(boolean correctHouse) {
        CorrectHouse = correctHouse;
    }


    public Date getbReadDate() {
        return bReadDate;
    }

    public void setbReadDate(Date bReadDate) {
        this.bReadDate = bReadDate;
    }

    public Float getbNowRate() {
        return bNowRate;
    }

    public void setbNowRate(Float bNowRate) {
        this.bNowRate = bNowRate;
    }

    public String getbBarCode() {
        return bBarCode;
    }

    public void setbBarCode(String bBarCode) {
        this.bBarCode = bBarCode;
    }

    public String getbOwner() {
        return bOwner;
    }

    public void setbOwner(String bOwner) {
        this.bOwner = bOwner;
    }

    public String getbStreet() {
        return bStreet;
    }

    public void setbStreet(String bStreet) {
        this.bStreet = bStreet;
    }

    public String getbColony() {
        return bColony;
    }

    public void setbColony(String bColony) {
        this.bColony = bColony;
    }

    public Integer getbHouseNumber() {
        return bHouseNumber;
    }

    public void setbHouseNumber(Integer bHouseNumber) {
        this.bHouseNumber = bHouseNumber;
    }

    public Float getbReadNow() {
        return bReadNow;
    }

    public void setbReadNow(Float bReadNow) {
        this.bReadNow = bReadNow;
    }

    public String getbNameFile() {
        return bNameFile;
    }

    public void setbNameFile(String nameFile) {
        bNameFile = nameFile;
    }

    public List<WaterBillsModel> getBills() {
        return bills;
    }

    public void setBills(List<WaterBillsModel> bills) {
        this.bills = bills;
    }
}
