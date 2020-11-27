package Data.Models;

public class HousesModel {
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
    //Modificar viviendas
    public static String modifybarCode;
    public static String modifyowner;
    public static Long modifyphoneNumber;
    public static String modifyemail;
    public static String modifystreet;
    public static Integer modifyhouseNumber;
    public static Integer modifyzipCode;
    public static String  modifycolony;
    public static String modifycity;
    public static String modifystate;
    public static String modifystatusHouse;

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
    public void setValuesToModify(String barCode, String owner,
                       Long phoneNumber, String email, String street, Integer houseNumber, Integer zipCode,
                       String colony, String city, String state, String statusHouse) {
        this.setModifybarCode(barCode);
        this.setModifyowner(owner);
        this.setModifyphoneNumber(phoneNumber);
        this.setModifyemail(email);
        this.setModifystreet(street);
        this.setModifyhouseNumber(houseNumber);
        this.setModifyzipCode(zipCode);
        this.setModifycolony(colony);
        this.setModifycity(city);
        this.setModifystate(state);
        this.setModifystatusHouse(statusHouse);
    }

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

    public static String getModifybarCode() {
        return modifybarCode;
    }

    public static void setModifybarCode(String modifybarCode) {
        HousesModel.modifybarCode = modifybarCode;
    }

    public static String getModifyowner() {
        return modifyowner;
    }

    public static void setModifyowner(String modifyowner) {
        HousesModel.modifyowner = modifyowner;
    }

    public static Long getModifyphoneNumber() {
        return modifyphoneNumber;
    }

    public static void setModifyphoneNumber(Long modifyphoneNumber) {
        HousesModel.modifyphoneNumber = modifyphoneNumber;
    }

    public static String getModifyemail() {
        return modifyemail;
    }

    public static void setModifyemail(String modifyemail) {
        HousesModel.modifyemail = modifyemail;
    }

    public static String getModifystreet() {
        return modifystreet;
    }

    public static void setModifystreet(String modifystreet) {
        HousesModel.modifystreet = modifystreet;
    }

    public static Integer getModifyhouseNumber() {
        return modifyhouseNumber;
    }

    public static void setModifyhouseNumber(Integer modifyhouseNumber) {
        HousesModel.modifyhouseNumber = modifyhouseNumber;
    }

    public static Integer getModifyzipCode() {
        return modifyzipCode;
    }

    public static void setModifyzipCode(Integer modifyzipCode) {
        HousesModel.modifyzipCode = modifyzipCode;
    }

    public static String getModifycolony() {
        return modifycolony;
    }

    public static void setModifycolony(String modifycolony) {
        HousesModel.modifycolony = modifycolony;
    }

    public static String getModifycity() {
        return modifycity;
    }

    public static void setModifycity(String modifycity) {
        HousesModel.modifycity = modifycity;
    }

    public static String getModifystate() {
        return modifystate;
    }

    public static void setModifystate(String modifystate) {
        HousesModel.modifystate = modifystate;
    }

    public static String getModifystatusHouse() {
        return modifystatusHouse;
    }

    public static void setModifystatusHouse(String modifystatusHouse) {
        HousesModel.modifystatusHouse = modifystatusHouse;
    }
}
