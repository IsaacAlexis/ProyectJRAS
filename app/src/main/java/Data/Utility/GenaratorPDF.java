package Data.Utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;

import android.widget.Toast;

import com.example.jras.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import Data.Models.ConsumptionsModel;
import Data.Models.PaymentsModel;
import Data.Models.UsersModel;
import Data.Models.WaterBillsModel;

public class GenaratorPDF {


    float total=0;
    public boolean createPDFWaterBills(Context context, List<WaterBillsModel> debits){
        try {
            PdfDocument mypdfDocument=new PdfDocument();
            Paint mypaint=new Paint();
            PdfDocument.PageInfo myPageInfo=new PdfDocument.PageInfo.Builder(396,612,1).create();
            PdfDocument.Page mypage1=mypdfDocument.startPage(myPageInfo);
            Canvas canvas =mypage1.getCanvas();
            //-----Linea de inicio
            mypaint.setColor(Color.parseColor("#354591"));
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(3);
            canvas.drawLine(10,10,myPageInfo.getPageWidth()-10,10,mypaint);
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);

            //Agregamos la imagenes
            //canvas.drawBitmap(converterBitmap(R.drawable.test,context),15,21,mypaint);
            //canvas.drawBitmap(converterBitmap(R.drawable.logo_jras,context),myPageInfo.getPageWidth()-74,21,mypaint);

            //Agregamos el titulo y los subtitulos
            mypaint.setTextSize(12.0f);
            mypaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("JUNTA RURAL DE AGUA Y SANEAMIENTO ",(myPageInfo.getPageWidth()/2),40,mypaint);
            mypaint.setTextAlign(Paint.Align.CENTER);
            mypaint.setTextSize(9.0f);
            canvas.drawText(new WaterBillsModel().getColony().toUpperCase(),(myPageInfo.getPageWidth()/2),70,mypaint);
            mypaint.setTextAlign(Paint.Align.CENTER);
            mypaint.setTextSize(9.0f);
            canvas.drawText("CALLE QUINTA #254",(myPageInfo.getPageWidth()/2),85,mypaint);

            //-----Linea divisora Datos de Usuario
            mypaint.setColor(Color.parseColor("#354591"));
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(3);
            canvas.drawLine(10,105,myPageInfo.getPageWidth()-10,105,mypaint);
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);
            //Linea de encabezado de Datos Usuario
            mypaint.setColor(Color.parseColor("#354591"));
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(16);
            canvas.drawLine(10,125,105,125,mypaint);
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.WHITE);
            mypaint.setTextSize(9.0f);
            canvas.drawText("DATOS DEL USUARIO",11,128,mypaint);
            //Linea divisora de lados izquierda de Datos Usuario
            mypaint.setColor(Color.parseColor("#354591"));
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(9);
            canvas.drawLine(15,135,15,210,mypaint);
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("No. CUENTA/MEDIDOR:",30,155,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText(new WaterBillsModel().getBarCode(),120,155,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText(new WaterBillsModel().getOwner().toUpperCase(),30,170,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText(new WaterBillsModel().getStreet().toUpperCase()+" #"+new WaterBillsModel().getHouseNumber()+" CP:"+new WaterBillsModel().getZipCode(),30,185,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText(new WaterBillsModel().getColony().toUpperCase(),30,200,mypaint);
            //Parte dos de Datos Usuarios
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("PERIODO:",247,155,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText(new Dates().getDateBills(),298,155,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("TARIFA",258,170,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("DOMESTICO",298,170,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("SALDO ANTERIOR:",223,185,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("$"+new WaterBillsModel().getPreviousRate()+"0",298,185,mypaint);//----------------------------------------------REALIZAR PARTE POR PARTE

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            mypaint.setTextSize(8.0f);
            canvas.drawText("FECHA DE PAGO:",223,200,mypaint);
            mypaint.setTypeface(Typeface.DEFAULT);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("INMEDIATO",298,200,mypaint);
            mypaint.setTypeface(Typeface.DEFAULT);


            mypaint.setColor(Color.parseColor("#354591"));
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(3);
            canvas.drawLine(10,215,myPageInfo.getPageWidth()-10,215,mypaint);
            mypaint.setStrokeWidth(0);
            //---------------------Area de consumos------------------------

            mypaint.setStyle(Paint.Style.FILL);
            mypaint.setColor(Color.parseColor("#354591"));
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(16);
            canvas.drawLine(10,240,135,240,mypaint);
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);

            mypaint.setColor(Color.parseColor("#354591"));
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(16);
            canvas.drawLine(250,240,myPageInfo.getPageWidth()-10,240,mypaint);
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.WHITE);
            mypaint.setTextSize(9.0f);
            canvas.drawText("HISTORIAL DE CONSUMO",11,243,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.WHITE);
            mypaint.setTextSize(9.0f);
            canvas.drawText("CONCEPTO DE COBRO",285,243,mypaint);
            //Lineas horizonatales de area de consumo

            mypaint.setColor(Color.parseColor("#354591"));
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(9);
            canvas.drawLine(15,253,15,myPageInfo.getPageHeight()-15,mypaint);
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);

            mypaint.setColor(Color.parseColor("#354591"));
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(9);
            canvas.drawLine(myPageInfo.getPageWidth()-15,253,myPageInfo.getPageWidth()-15,myPageInfo.getPageHeight()-15,mypaint);
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);

            mypaint.setColor(Color.parseColor("#354591"));
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(9);
            canvas.drawLine(185,253,185,460,mypaint);
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);
            //Linea vertical de Area de consumos
            mypaint.setColor(Color.parseColor("#354591"));
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(3);
            canvas.drawLine(10,myPageInfo.getPageHeight()-10,myPageInfo.getPageWidth()-10,myPageInfo.getPageHeight()-10,mypaint);
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);
            //Texto de area de consumo
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("LECT. ANTERIOR",30,268,mypaint);

            mypaint.setTextAlign(Paint.Align.CENTER);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText(""+new WaterBillsModel().getReadLast(),62,283,mypaint);
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("LECT. ACTUAL",105,268,mypaint);

            mypaint.setTextAlign(Paint.Align.CENTER);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText(""+new WaterBillsModel().getReadNow(),130,283,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("CONSUMO",75,315,mypaint);

            mypaint.setTextAlign(Paint.Align.CENTER);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText(""+(new WaterBillsModel().getReadNow()-new WaterBillsModel().getReadLast()),95,330,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("FECHA DE EMISION:",30,460,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText(""+new SimpleDateFormat("dd/MM/yyyy").format(new Date()),120,460,mypaint);
            //-----Area de cobro----
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("CONSUMO ACTUAL AGUA",220,268,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("$"+new WaterBillsModel().getNowRate()+"0",330,268,mypaint);
            total+=new WaterBillsModel().getNowRate();
            int valueY=283;
            for(WaterBillsModel debit : debits){
                mypaint.setTextAlign(Paint.Align.LEFT);
                mypaint.setColor(Color.BLACK);
                mypaint.setTextSize(8.0f);
                canvas.drawText("REZAGO AGUA ["+new Dates().getLastBill(debit.getbReadDate())+"]",220,valueY,mypaint);

                mypaint.setTextAlign(Paint.Align.LEFT);
                mypaint.setColor(Color.BLACK);
                mypaint.setTextSize(8.0f);
                canvas.drawText("$"+debit.getbNowRate()+"0",330,valueY,mypaint);
                total+=debit.getbNowRate();
                valueY+=15;
            }

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("SUBTOTAL:",250,530,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("$"+total+"0",330,530,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("IVA:",279,545,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("$0.00",330,545,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("REDONDEO:",247,560,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("$0.00",330,560,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("TOTAL:",265,575,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("$"+total+"0",330,575,mypaint);
            mypaint.setTypeface(Typeface.DEFAULT);
            //Codigo de barras
            canvas.drawBitmap(GenerateCodeBar(context,new WaterBillsModel().getBarCode()),15,myPageInfo.getPageHeight()-90,mypaint);
            mypaint.setColor(Color.parseColor("#354591"));
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(9);
            canvas.drawLine(15,253,15,myPageInfo.getPageHeight()-15,mypaint);
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);

            mypdfDocument.finishPage(mypage1);

            String test=""+new WaterBillsModel().getOwner().toString().toUpperCase()+" PERIODO "+ NameMonth(Integer.parseInt(new SimpleDateFormat("MM").format(new Date())))+".pdf";
            new ConsumptionsModel().setPdf(test);
            File file=new File(context.getExternalFilesDir("/"),"/"+test);
            mypdfDocument.writeTo(new  FileOutputStream(file));
            mypdfDocument.close();
            return false;
        } catch (IOException e) {
            new ConsumptionsModel().setValidationMessage("Ocurrio un error al generar Recibo de agua");
            return true;
        }
    }
    public boolean createTicketPDF(Context context, List<PaymentsModel> pays){
        try {
            PdfDocument mypdfDocument=new PdfDocument();
            Paint mypaint=new Paint();
            PdfDocument.PageInfo myPageInfo=new PdfDocument.PageInfo.Builder(302,500,1).create();
            PdfDocument.Page mypage1=mypdfDocument.startPage(myPageInfo);
            Canvas canvas =mypage1.getCanvas();

            //Agregamos la imagenes
            mypaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawBitmap(converterBitmap(R.drawable.logo_jras_sm_black,context),(myPageInfo.getPageWidth()/2)-25,15,mypaint);
            //canvas.drawBitmap(converterBitmap(R.drawable.logo_jras,context),myPageInfo.getPageWidth()-74,21,mypaint);
            //Agregamos el titulo y los subtitulos
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(12.0f);
            mypaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("JUNTA RURAL DE AGUA Y SANEAMIENTO ",(myPageInfo.getPageWidth()/2),95,mypaint);
            mypaint.setTextAlign(Paint.Align.CENTER);
            mypaint.setTextSize(9.0f);
            canvas.drawText(new UsersModel().getCurrentColony(),(myPageInfo.getPageWidth()/2),110,mypaint);
            mypaint.setTextAlign(Paint.Align.CENTER);
            mypaint.setTextSize(9.0f);
            canvas.drawText("CALLE QUINTA #254",(myPageInfo.getPageWidth()/2),125,mypaint);
            mypaint.setTextSize(12.0f);
            canvas.drawText("Comprobante de Pago",(myPageInfo.getPageWidth()/2),145,mypaint);
            mypaint.setTextSize(9.0f);
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("Calle: ",50,170,mypaint);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            mypaint.setTypeface(Typeface.DEFAULT);
            canvas.drawText(new PaymentsModel().getStreet(),(myPageInfo.getPageWidth()-20),170,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);


            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("No de Vivienda:",50,185,mypaint);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            mypaint.setTypeface(Typeface.DEFAULT);
            canvas.drawText("#"+new PaymentsModel().getHouseNumber(),(myPageInfo.getPageWidth()-20),185,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);


            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("Nombre del Propietario:",50,200,mypaint);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            mypaint.setTypeface(Typeface.DEFAULT);
            canvas.drawText(new PaymentsModel().getOwner(),(myPageInfo.getPageWidth()-20),200,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);


            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("OPERACION REALIZADA POR:",50,220,mypaint);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            mypaint.setTypeface(Typeface.DEFAULT);
            canvas.drawText(new UsersModel().getCurrentFirstName()+" "+new UsersModel().getCurrentLastName(),(myPageInfo.getPageWidth()-20),220,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("FECHA: ",180,240,mypaint);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(new SimpleDateFormat("dd-MM-YYYY").format(new Date()),(myPageInfo.getPageWidth()-20),240,mypaint);
            canvas.drawText(new SimpleDateFormat("hh:mm a").format(new Date()),(myPageInfo.getPageWidth()-20),250,mypaint);

            mypaint.setStyle(Paint.Style.FILL);
            mypaint.setColor(Color.BLACK);
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(2);
            canvas.drawLine(20,260,myPageInfo.getPageWidth()-20,260,mypaint);
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("CONCEPTO",50,280,mypaint);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("IMPORTE",(myPageInfo.getPageWidth()-20),280,mypaint);
            int yfirst=300;
            int ysecond=310;


            for(PaymentsModel pay :pays){
                mypaint.setTextAlign(Paint.Align.LEFT);
                mypaint.setTypeface(Typeface.DEFAULT);
                canvas.drawText(pay.getDescription()+" DEL",50,yfirst,mypaint);
                canvas.drawText("PERIODO: "+new Dates().NameMonth(Integer.parseInt(new SimpleDateFormat("MM").format(pay.getReadDate())))+
                        "["+new Dates().getLastBill(pay.getReadDate())+"]",50,ysecond,mypaint);
                mypaint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText("$"+pay.getRate()+"0",(myPageInfo.getPageWidth()-20),ysecond,mypaint);
                yfirst+=20;
                ysecond+=20;
            }
            ysecond+=20;


            mypaint.setStyle(Paint.Style.FILL);
            mypaint.setColor(Color.BLACK);
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(2);
            canvas.drawLine(20,ysecond,myPageInfo.getPageWidth()-20,ysecond,mypaint);
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);
            ysecond+=30;

            mypaint.setTextSize(12.0f);
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT);
            canvas.drawText("TOTAL:",50,ysecond,mypaint);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("$"+new PaymentsModel().getDebitTotal()+"0",(myPageInfo.getPageWidth()-20),ysecond,mypaint);
            ysecond+=15;

            mypaint.setTextSize(12.0f);
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT);
            canvas.drawText("SALDO RESTANTE:",50,ysecond,mypaint);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("$"+new PaymentsModel().getTotal()+"0",(myPageInfo.getPageWidth()-20),ysecond,mypaint);
            ysecond+=30;

            mypaint.setTextAlign(Paint.Align.CENTER);
            mypaint.setTextSize(11.0f);
            canvas.drawText("PAGO EN EFECTIVO",(myPageInfo.getPageWidth()/2),ysecond,mypaint);
            mypaint.setTextAlign(Paint.Align.CENTER);

            //Codigo de barras

//            mypaint.setTextAlign(Paint.Align.CENTER);
//            canvas.drawBitmap(GenerateCodeBar(context,"6789"),50,myPageInfo.getPageHeight()-70,mypaint);



            mypdfDocument.finishPage(mypage1);

            File file=new File(context.getExternalFilesDir("/"),"/PAGO "+
                    new PaymentsModel().getOwner()+new Dates().NameMonth(Integer.parseInt(new SimpleDateFormat("MM").format(new Date())))+".pdf");
            mypdfDocument.writeTo(new FileOutputStream(file));
            mypdfDocument.close();
            return false;
        } catch (IOException e) {
            new PaymentsModel().setValidationMessage("No se pudo generar el recibo");
            return true;
        }
    }

    public boolean createReportPDF(Context context){
        try {
            PdfDocument mypdfDocument = new PdfDocument();
            Paint mypaint = new Paint();
            PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(612, 792, 1).create();
            PdfDocument.Page mypage1 = mypdfDocument.startPage(myPageInfo);
            Canvas canvas = mypage1.getCanvas();

            //Agregamos la imagenes
            mypaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawBitmap(converterBitmap(R.drawable.logo_jras1, context), 50, 15, mypaint);
            canvas.drawBitmap(converterBitmapCuadro(R.drawable.logo_chih, context), myPageInfo.getPageWidth() - 120, 15, mypaint);
            //canvas.drawBitmap(converterBitmap(R.drawable.logo_jras,context),myPageInfo.getPageWidth()-74,21,mypaint);
            //Agregamos el titulo y los subtitulos
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(12.0f);
            mypaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("JUNTA RURAL DE AGUA Y SANEAMIENTO ", (myPageInfo.getPageWidth() / 2), 30, mypaint);
            mypaint.setTextAlign(Paint.Align.CENTER);
            mypaint.setTextSize(12.0f);
            canvas.drawText("COL. BELLAVISTA", (myPageInfo.getPageWidth() / 2), 45, mypaint);
            mypaint.setTextAlign(Paint.Align.CENTER);
            mypaint.setTextSize(12.0f);
            canvas.drawText("CALLE QUINTA #254", (myPageInfo.getPageWidth() / 2), 60, mypaint);
            mypaint.setTextSize(12.0f);
            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("Reporte de Utilidad", (myPageInfo.getPageWidth() / 2), 90, mypaint);
            canvas.drawText("PERIODO " + "DICIEMBRE " + "[2020-12]", (myPageInfo.getPageWidth() / 2), 105, mypaint);


            mypaint.setTextSize(11.0f);
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("INGRESOS ", 50, 140, mypaint);

            mypaint.setTypeface(Typeface.DEFAULT);
            mypaint.setTextSize(10.0f);
            canvas.drawText("1.- " + " Brayan Espinoza Fernandez ", 50, 155, mypaint);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("$50.00", (myPageInfo.getPageWidth() - 50), 155, mypaint);

            mypaint.setTextSize(11.0f);
            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("TOTAL INGRESOS:   "+ " $50.00",(myPageInfo.getPageWidth()-50),180,mypaint);

            mypaint.setTextSize(11.0f);
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("EGRESOS ", 50, 210, mypaint);

            mypaint.setTypeface(Typeface.DEFAULT);
            mypaint.setTextSize(10.0f);
            canvas.drawText("1.- " + " Gasto en Purificador ", 50, 225, mypaint);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("$40.00", (myPageInfo.getPageWidth() - 50), 225, mypaint);

            mypaint.setTextSize(11.0f);
            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("TOTAL EGRESOS:   "+ " $40.00",(myPageInfo.getPageWidth()-50),250,mypaint);

            mypaint.setTextSize(12.0f);
            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("UTILIDAD NETA:   "+ " $10.00",(myPageInfo.getPageWidth()-50),300,mypaint);


            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT);
            mypaint.setTextSize(10.0f);
            canvas.drawText("Todos los Derechos Reservados ", 50, myPageInfo.getPageHeight()-10, mypaint);






            mypdfDocument.finishPage(mypage1);

            File file=new File(context.getExternalFilesDir("/"),"/Reporte.pdf");
            mypdfDocument.writeTo(new FileOutputStream(file));
            mypdfDocument.close();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
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

    private Bitmap converterBitmap(int image,Context context){
        Bitmap bmp;
        bmp= BitmapFactory.decodeResource(context.getResources(),image);
        bmp=Bitmap.createScaledBitmap(bmp,50,70,false);
        return bmp;
    }
    private Bitmap converterBitmapCuadro(int image,Context context){
        Bitmap bmp;
        bmp= BitmapFactory.decodeResource(context.getResources(),image);


        bmp=Bitmap.createScaledBitmap(bmp,70,70,false);
        return bmp;
    }

    private Bitmap GenerateCodeBar(Context context,String code) {
        try {
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            Writer codeWriter;
            codeWriter = new Code128Writer();
            BitMatrix byteMatrix = codeWriter.encode(code, BarcodeFormat.CODE_128,400, 200, hintMap);
            int width = byteMatrix.getWidth();
            int height = byteMatrix.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    bitmap.setPixel(i, j, byteMatrix.get(i, j) ? Color.BLACK : Color.WHITE);
                }
            }
            return Bitmap.createScaledBitmap(bitmap,200,50,true);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

}
