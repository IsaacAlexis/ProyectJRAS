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
import android.view.View;
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

public class GenaratorPDF {

    private Bitmap vect;
    public boolean createPDFexample(Context context){
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
            canvas.drawText("COL. BELLAVISTA",(myPageInfo.getPageWidth()/2),70,mypaint);
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
            canvas.drawText("2535125456",110,155,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("BRAYAN ESPINOZA",30,170,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("CALLE FRANSICO #256",30,185,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("COL. BELLAVISTA",30,200,mypaint);
            //Parte dos de Datos Usuarios
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("PERIODO:",247,155,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("2020/12",298,155,mypaint);

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
            canvas.drawText("$150.00",298,185,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            mypaint.setTextSize(8.0f);
            canvas.drawText("FECHA DE PAGO:",223,200,mypaint);
            mypaint.setTypeface(Typeface.DEFAULT);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setTypeface(Typeface.DEFAULT_BOLD);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("01/01/2021",298,200,mypaint);
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
            canvas.drawText("13815",62,283,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("LECT. ACTUAL",105,268,mypaint);

            mypaint.setTextAlign(Paint.Align.CENTER);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("13830",130,283,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("CONSUMO",75,315,mypaint);

            mypaint.setTextAlign(Paint.Align.CENTER);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("15",95,330,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("FECHA DE EMISION:",30,460,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("03/12/2020:",120,460,mypaint);
            //-----Area de cobro----
            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("CONSUMO ACTUAL AGUA",220,268,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("$150.00",330,268,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("REZAGO AGUA",220,283,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("$80.00",330,283,mypaint);
            //Area de totales

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("SUBTOTAL:",250,530,mypaint);

            mypaint.setTextAlign(Paint.Align.LEFT);
            mypaint.setColor(Color.BLACK);
            mypaint.setTextSize(8.0f);
            canvas.drawText("$230.00",330,530,mypaint);

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
            canvas.drawText("$230.00",330,575,mypaint);
            mypaint.setTypeface(Typeface.DEFAULT);
            //Codigo de barras
            canvas.drawBitmap(GenerateCodeBar(context,"88332499"),15,myPageInfo.getPageHeight()-90,mypaint);
            mypaint.setColor(Color.parseColor("#354591"));
            mypaint.setStyle(Paint.Style.STROKE);
            mypaint.setStrokeWidth(9);
            canvas.drawLine(15,253,15,myPageInfo.getPageHeight()-15,mypaint);
            mypaint.setStrokeWidth(0);
            mypaint.setStyle(Paint.Style.FILL);

            mypdfDocument.finishPage(mypage1);

            File file=new File(context.getExternalFilesDir("/"),"/ReciboDeAgua.pdf");
            mypdfDocument.writeTo(new  FileOutputStream(file));
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
