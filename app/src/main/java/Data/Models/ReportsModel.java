package Data.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportsModel {
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
}
