package BusinessLogic;

import android.content.Context;

import Data.Models.ReportsModel;
import DataAccess.DAReports;

public class BusinessReports {
    public boolean generateRepor(Context context, ReportsModel reports) {
        return new DAReports().PostReport(context,reports);
    }
}
