package DataAccess;

import android.content.Context;

import Data.Models.ReportsModel;
import Data.Utility.GenaratorPDF;
import Security.scReports;

public class DAReports {
    public boolean PostReport(Context context, ReportsModel reports) {
        new scReports().getPayments(reports);
        new scReports().getExpenses(reports);
        if(new GenaratorPDF().createReportPDF(context,reports)){
            reports.setValidationMessage("Se genero correctamente el reporte");
            return true;
        }else{
            reports.setValidationMessage("No se pudo generar correctamente el reporte");
            return false;
        }
    }
}
