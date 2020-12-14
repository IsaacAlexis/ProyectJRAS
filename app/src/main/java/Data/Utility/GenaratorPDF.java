package Data.Utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;

import android.util.Base64;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.jras.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Stream;

import BusinessLogic.BusinessConsumption;
import Data.Models.ConsumptionsModel;
import Data.Models.HousesModel;
import Data.Models.WaterBillsModel;

public class GenaratorPDF {

    private Bitmap vect;
    private HousesModel house=new HousesModel();
    float total=0;
    public boolean createPDFexample(Context context,List<WaterBillsModel> debits){
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
                canvas.drawText("REZAGO AGUA ["+new SimpleDateFormat("yyyy/MM").format(debit.getbReadDate())+"]",220,valueY,mypaint);

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

            String test=""+new WaterBillsModel().getOwner().toString().toUpperCase()+" PERIODO "+SpellNumber(Integer.parseInt(new SimpleDateFormat("MM").format(new Date())))+".pdf";
            new ConsumptionsModel().setPdf(test);




            File file=new File(context.getExternalFilesDir("/"),"/"+test);
            mypdfDocument.writeTo(new  FileOutputStream(file));
            mypdfDocument.close();
            return false;
            /*FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = new String(Base64.encodeToString(bytes,Base64.DEFAULT));
            byte[] fl=encodedBase64.getBytes(StandardCharsets.UTF_8);*/
//            byte[] bytes = loadFile(file);
//
//            String encodedString = new String(encoded);
//            /*byte[] buffer=new byte[(int) file.length()];
//            ByteArrayOutputStream os =new ByteArrayOutputStream();
//            FileInputStream fls=new FileInputStream(file);
//            int read;
//            while((read=fls.read(buffer))!=-1){
//                os.write(buffer,0,read);

//            }
//            fls.close();
//            os.close();
//            new ConsumptionsModel().setPdf(os.toByteArray());*/

            /*InputStream input=new FileInputStream(file);
            input.read(files);*/






        } catch (IOException e) {
            new ConsumptionsModel().setValidationMessage("Ocurrio un error al generar Recibo de agua");
            return true;
        }
    }

     public String SpellNumber(int number){
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
    public Bitmap redimensionarImagenMaximo(Bitmap mBitmap, float newWidth, float newHeigth){
        //Redimensionamos
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeigth) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        return Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, true);
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
    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
    }
}
