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
    private static Integer DebitPeriod;
    private static String Status;
    //Informacion de adeudos
    private static List<PaymentsModel> dDebits;//Ordenados de manera descendente
    private static List<PaymentsModel> aDebits;//Ordenados de manera Ascendente
    private Date ReadDate;
    private Float Rate;
    private Float payTotal;
    private Long IdConsumption;



    //Banderas de validacion de acciones
    private static String ValidationMessage;






    public PaymentsModel() {
    }
//    Se agregan los valores para el arreglo del RecyclerView
    public PaymentsModel(long rfolio, String rowner, Date rpayDate, Float rtotal) {
        Rfolio = rfolio;
        Rowner = rowner;
        RpayDate = rpayDate;
        Rtotal = rtotal;
    }
//Constructor de la vista de pagos
    public PaymentsModel(String owner, String barcode, Float total, Integer debitPeriod, String status) {
        Owner = owner;
        Barcode = barcode;
        Total = total;
        DebitPeriod = debitPeriod;
        Status = status;
    }
//Constructor para los adeudos descente
    public PaymentsModel(Date readDate, Float rate) {
        ReadDate = readDate;
        Rate = rate;
    }
//Constructor para los adeudos ascendente

    public PaymentsModel(Float rate, Long idConsumption) {
        Rate = rate;
        IdConsumption = idConsumption;
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

    public static List<PaymentsModel> getdDebits() {
        return dDebits;
    }

    public static void setdDebits(List<PaymentsModel> dDebits) {
        PaymentsModel.dDebits = dDebits;
    }

    public static List<PaymentsModel> getaDebits() {
        return aDebits;
    }

    public static void setaDebits(List<PaymentsModel> aDebits) {
        PaymentsModel.aDebits = aDebits;
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
}
