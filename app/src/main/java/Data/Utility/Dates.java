package Data.Utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates {
    public String getLastDate(){
        Integer Month=Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
        String Year=new SimpleDateFormat("yyyy").format(new Date());
        String Day=new SimpleDateFormat("dd").format(new Date());
        if((Month-1)==0){
            return (Integer.parseInt(Year)-1)+"-"+12+"-"+Day;
        }else{
            return Year+"-"+(Month-1)+"-"+Day;
        }
    }
    public String getDateBills(){
        Integer Month=Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
        String Year=new SimpleDateFormat("yyyy").format(new Date());
        String Day=new SimpleDateFormat("dd").format(new Date());
        if((Month-1)==0){
            return (Integer.parseInt(Year)-1)+"/"+12;
        }else{
            return Year+"/"+(Month-1);
        }
    }
    public String getLastBill(Date date){

        Integer Month=Integer.parseInt(new SimpleDateFormat("MM").format(date));
        String Year=new SimpleDateFormat("yyyy").format(date);

        if((Month-1)==0){

                return (Integer.parseInt(Year)-1)+"-"+12;


        }else{
            Month-=1;
            if(Month.toString().toCharArray().length>1){
                return Year+"-"+(Month);
            }else{
                return Year+"-0"+(Month);
            }

        }
    }

}
