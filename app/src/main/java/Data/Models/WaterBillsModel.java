package Data.Models;

import java.util.Date;

public class WaterBillsModel {
    private static String BarCode;
    private static String Owner;
    private static String Street;
    private static String Colony;
    private static String Status;
    private static Integer ZipCode;
    private static Integer HouseNumber;
    private static Float PreviousRate;
    private static Float ReadLast;
    private String ValidationMessage;
    private boolean CorrectHouse;
    private static boolean existFirstRegister;
    private static Float ReadNow;
    private static Float NowRate;
    private static String ReadDate;
    //----Variables para descripcion de adeudo en recibo de agua
    private String bBarCode;
    private String bOwner;
    private String bStreet;
    private String bColony;
    private Integer bHouseNumber;
    private Date bReadDate;
    private Float bReadNow;
    private Float bNowRate;







//    private static Float ReadTotal;


    public WaterBillsModel() {
    }

    public WaterBillsModel(String bBarCode, String bOwner, String bStreet, String bColony, Integer bHouseNumber, Date bReadDate, Float bReadNow, Float bNowRate) {
        this.bBarCode = bBarCode;
        this.bOwner = bOwner;
        this.bStreet = bStreet;
        this.bColony = bColony;
        this.bHouseNumber = bHouseNumber;
        this.bReadDate = bReadDate;
        this.bReadNow = bReadNow;
        this.bNowRate = bNowRate;
    }



    public WaterBillsModel(Date bReadDate, Float bNowRate) {
        this.bReadDate = bReadDate;
        this.bNowRate = bNowRate;
    }

    public static String getBarCode() {
        return BarCode;
    }

    public static void setBarCode(String barCode) {
        BarCode = barCode;
    }

    public static String getOwner() {
        return Owner;
    }

    public static void setOwner(String owner) {
        Owner = owner;
    }

    public static String getStreet() {
        return Street;
    }

    public static void setStreet(String street) {
        Street = street;
    }

    public static String getColony() {return Colony;}

    public static void setColony(String colony) {
        Colony = colony;
    }

    public static String getStatus() {
        return Status;
    }

    public static void setStatus(String status) {
        Status = status;
    }

    public static Integer getZipCode() {
        return ZipCode;
    }

    public static void setZipCode(Integer zipCode) {
        ZipCode = zipCode;
    }

    public static Integer getHouseNumber() {
        return HouseNumber;
    }

    public static void setHouseNumber(Integer houseNumber) {
        HouseNumber = houseNumber;
    }

    public static Float getPreviousRate() {
        return PreviousRate;
    }

    public static void setPreviousRate(Float previousRate) {
        PreviousRate = previousRate;
    }

    public static Float getReadLast() {
        return ReadLast;
    }

    public static void setReadLast(Float readLast) {
        ReadLast = readLast;
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

    public static boolean isExistFirstRegister() {
        return existFirstRegister;
    }

    public static void setExistFirstRegister(boolean existFirstRegister) {
        WaterBillsModel.existFirstRegister = existFirstRegister;
    }

    public static Float getReadNow() {
        return ReadNow;
    }

    public static void setReadNow(Float readNow) {
        ReadNow = readNow;
    }

    public static Float getNowRate() {
        return NowRate;
    }

    public static void setNowRate(Float nowRate) {
        NowRate = nowRate;
    }

    public static String getReadDate() {
        return ReadDate;
    }

    public static void setReadDate(String readDate) {
        ReadDate = readDate;
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
}
