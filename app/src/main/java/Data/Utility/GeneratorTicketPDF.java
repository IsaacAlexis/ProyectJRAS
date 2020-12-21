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
import java.util.Hashtable;

public class GeneratorTicketPDF {
    private Bitmap vect;
    public boolean createTicketPDF(Context context){
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
            canvas.drawText("COL. BELLAVISTA",(myPageInfo.getPageWidth()/2),110,mypaint);
            mypaint.setTextAlign(Paint.Align.CENTER);
            mypaint.setTextSize(9.0f);
            canvas.drawText("CALLE QUINTA #254",(myPageInfo.getPageWidth()/2),125,mypaint);
            mypaint.setTextSize(12.0f);
            canvas.drawText("Comprobante de Pago",(myPageInfo.getPageWidth()/2),145,mypaint);
            mypaint.setTextSize(9.0f);
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("Folio de Pago:",50,170,mypaint);
            mypaint.setTypeface(Typeface.DEFAULT);
            canvas.drawText("12345",(myPageInfo.getPageWidth()-150),170,mypaint);

            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("ID de Usuario:",50,185,mypaint);
            mypaint.setTypeface(Typeface.DEFAULT);
            canvas.drawText("54321",(myPageInfo.getPageWidth()-150),185,mypaint);

            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("ID de Consumo:",50,200,mypaint);
            mypaint.setTypeface(Typeface.DEFAULT);
            canvas.drawText("6789",(myPageInfo.getPageWidth()-150),200,mypaint);

            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("Propietario de la vivienda:",50,220,mypaint);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            mypaint.setTypeface(Typeface.DEFAULT);
            canvas.drawText("Brayan Espinoza",(myPageInfo.getPageWidth()-20),220,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("Fecha: ",180,235,mypaint);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("09/12/2020",(myPageInfo.getPageWidth()-20),235,mypaint);
            canvas.drawText("07:11:00 p.m",(myPageInfo.getPageWidth()-20),245,mypaint);

            mypaint.setStyle(Paint.Style.FILL);
            mypaint.setColor(Color.BLACK);
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(2);
            canvas.drawLine(20,260,myPageInfo.getPageWidth()-20,260,mypaint);
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("Concepto",50,280,mypaint);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("Importe",(myPageInfo.getPageWidth()-20),280,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT);
            canvas.drawText("Pago "+"Parcial o Total ",50,300,mypaint);
            canvas.drawText("del Periodo "+"20/12/2020 ",50,310,mypaint);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("50.00",(myPageInfo.getPageWidth()-20),310,mypaint);

            mypaint.setStyle(Paint.Style.FILL);
            mypaint.setColor(Color.BLACK);
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(2);
            canvas.drawLine(20,330,myPageInfo.getPageWidth()-20,330,mypaint);
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);

            mypaint.setTextSize(12.0f);
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT);
            canvas.drawText("TOTAL:",50,365,mypaint);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("50.00",(myPageInfo.getPageWidth()-20),365,mypaint);

            mypaint.setTextAlign(Paint.Align.CENTER);
            mypaint.setTextSize(11.0f);
            canvas.drawText("Pago en Efectivo",(myPageInfo.getPageWidth()/2),410,mypaint);
            mypaint.setTextAlign(Paint.Align.CENTER);

            //Codigo de barras

            mypaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawBitmap(GenerateCodeBar(context,"6789"),50,myPageInfo.getPageHeight()-70,mypaint);


            mypdfDocument.finishPage(mypage1);

            File file=new File(context.getExternalFilesDir("/"),"/Ticket de pago.pdf");
            mypdfDocument.writeTo(new FileOutputStream(file));
            mypdfDocument.close();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
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
            BitMatrix byteMatrix = codeWriter.encode(code, BarcodeFormat.CODE_128,400, 70, hintMap);
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

