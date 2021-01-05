package Data.Utility;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Notificaciones {

    private String mail = "projectjras@gmail.com";
    private String password = "jrasadmin123.";
    private Session session;
    private String fecha = new SimpleDateFormat("MM").format(new Date());
    private String periodo;


    public void createMailConsumptions(String correoDestino){
        getPeriodName();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Properties properties = new Properties();
        properties.put("mail.smtp.host","smtp.googlemail.com");
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port","465");

        try {

            session=Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mail,password);
                }
            });

            if (session!=null){
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(mail));
                //Asunto del correo
                message.setSubject("Recibo del consumo");
                //Correo de destino
                message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(correoDestino));
                //Texto del correo
                message.setContent("Su recibo del periodo "+periodo+" ya se encuentra disponible, consulte la " +
                        "aplicacion de JRAS para verlo.","text/html;charset=utf-8");
                Transport.send(message);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void checkSMSStatePermission(Context context, Activity activity) {
        int permissionCheck = ContextCompat.checkSelfPermission(
                context, Manifest.permission.SEND_SMS);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para enviar SMS.");
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS}, 225);
        } else {
            Log.i("Mensaje", "Se tiene permiso para enviar SMS!");
            enviaSMS("6391021228","Prueba de mensaje de texto",context);
        }
    }

    public void enviaSMS(String numero,String mensaje,Context context){
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numero, null, mensaje , null, null);

            Toast.makeText(context,"Mensaje Enviado",Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(context,"Mensaje no enviado, verifique los datos",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }


    private void getPeriodName(){
        switch (fecha){
            case "01":
                periodo="Enero/"+new SimpleDateFormat("yyyy").format(new Date());
                break;
            case "02":
                periodo="Febrero/"+new SimpleDateFormat("yyyy").format(new Date());
                break;
            case "03":
                periodo="Marzo/"+new SimpleDateFormat("yyyy").format(new Date());
                break;
            case "04":
                periodo="Abril/"+new SimpleDateFormat("yyyy").format(new Date());
                break;
            case "05":
                periodo="Mayo/"+new SimpleDateFormat("yyyy").format(new Date());
                break;
            case "06":
                periodo="Junio/"+new SimpleDateFormat("yyyy").format(new Date());
                break;
            case "07":
                periodo="Julio/"+new SimpleDateFormat("yyyy").format(new Date());
                break;
            case "08":
                periodo="Agosto/"+new SimpleDateFormat("yyyy").format(new Date());
                break;
            case "09":
                periodo="Septiembre/"+new SimpleDateFormat("yyyy").format(new Date());
                break;
            case "10":
                periodo="Octubre/"+new SimpleDateFormat("yyyy").format(new Date());
                break;
            case "11":
                periodo="Noviembre/"+new SimpleDateFormat("yyyy").format(new Date());
                break;
            case "12":
                periodo="Diciembre/"+new SimpleDateFormat("yyyy").format(new Date());
                break;
        }
    }


}
