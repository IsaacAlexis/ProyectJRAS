package Data.Models;

import java.util.Date;
import java.util.List;

public class PaymentsModel {
    private long Rfolio;
    private String Rowner;
    private Date RpayDate;
    private Float Rtotal;
   // ----Informacion de vista de Pagos-----
    private static String Owner;
    private static String Barcode;
    private static Float Total;
    private static Float DebitTotal;
    private static Integer DebitPeriod;
    private static String Status;
    private static String Street;
    private static Integer HouseNumber;
    //Informacion de adeudos
    private static List<PaymentsModel> Debits;//Ordenados de manera descendente
    private Date ReadDate;
    private Float Rate;
    private String Description;
    private Float payTotal;
    private Long IdConsumption;




    //Banderas de validacion de acciones
    private static String ValidationMessage;






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
        Owner = owner;
        Barcode = barcode;
        Total = total;
        DebitPeriod = debitPeriod;
        Status = status;
        Street=street;
        HouseNumber=houseNumber;
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
    // ----Informacion de vista de Pagos-----

    public static String getOwner() {
        return Owner;
    }

    public static void setOwner(String owner) {
        Owner = owner;
    }

    public static String getBarcode() {
        return Barcode;
    }

    public static void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public static Float getTotal() {
        return Total;
    }

    public static void setTotal(Float total) {
        Total = total;
    }

    public static Integer getDebitPeriod() {
        return DebitPeriod;
    }

    public static void setDebitPeriod(Integer debitPeriod) {
        DebitPeriod = debitPeriod;
    }

    public static String getStatus() {
        return Status;
    }

    public static void setStatus(String status) {
        Status = status;
    }

    public static String getValidationMessage() {
        return ValidationMessage;
    }

    public static void setValidationMessage(String validationMessage) {
        ValidationMessage = validationMessage;
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

    public static List<PaymentsModel> getDebits() {
        return Debits;
    }

    public static void setDebits(List<PaymentsModel> debits) {
        PaymentsModel.Debits = debits;
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

    public static String getStreet() {
        return Street;
    }

    public static void setStreet(String street) {
        Street = street;
    }

    public static Integer getHouseNumber() {
        return HouseNumber;
    }

    public static void setHouseNumber(Integer houseNumber) {
        HouseNumber = houseNumber;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public static Float getDebitTotal() {
        return DebitTotal;
    }

    public static void setDebitTotal(Float debitTotal) {
        DebitTotal = debitTotal;
    }
}
