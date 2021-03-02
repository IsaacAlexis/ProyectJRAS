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
    public String LastDates(){
        Integer Month=Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
        String Year=new SimpleDateFormat("yyyy").format(new Date());

        if((Month-1)==0){

            return NameMonthLast(12)+"/"+(Integer.parseInt(Year)-1);


        }else{

            return NameMonthLast(Month)+"/"+Year;


        }
    }
    public String LastDates(Date date){
        Integer Month=Integer.parseInt(new SimpleDateFormat("MM").format(date));
        String Year=new SimpleDateFormat("yyyy").format(date);

        if((Month-1)==0){

            return NameMonthLast(12)+"/"+(Integer.parseInt(Year)-1);


        }else{

            return NameMonthLast(Month)+"/"+Year;


        }
    }

    public String NameMonthLast(int number){

        String Name="";
        switch(number){
            case 1:
                Name="ENERO";
                break;
            case 2:
                Name="FEBRERO";
                break;
            case 3:
                Name="MARZO";
                break;
            case 4:
                Name="ABRIL";
                break;
            case 5:
                Name="MAYO";
                break;
            case 6:
                Name="JUNIO";
                break;
            case 7:
                Name="JULIO";
                break;
            case 8:
                Name="AGOSTO";
                break;
            case 9:
                Name="SEPTIEMBRE";
                break;
            case 10:
                Name="OCTUBRE";
                break;
            case 11:
                Name="NOVIEMBRE";
                break;
            case 12:
                Name="DICIEMBRE";
                break;
            default:

        }
        return  Name;
    }
    public String NameMonth(int number){
        number-=1;
        if(number==0){
            number=12;
        }
        String Name="";
        switch(number){
            case 1:
                Name="ENERO";
                break;
            case 2:
                Name="FEBRERO";
                break;
            case 3:
                Name="MARZO";
                break;
            case 4:
                Name="ABRIL";
                break;
            case 5:
                Name="MAYO";
                break;
            case 6:
                Name="JUNIO";
                break;
            case 7:
                Name="JULIO";
                break;
            case 8:
                Name="AGOSTO";
                break;
            case 9:
                Name="SEPTIEMBRE";
                break;
            case 10:
                Name="OCTUBRE";
                break;
            case 11:
                Name="NOVIEMBRE";
                break;
            case 12:
                Name="DICIEMBRE";
                break;
            default:

        }
        return  Name;
    }

}
