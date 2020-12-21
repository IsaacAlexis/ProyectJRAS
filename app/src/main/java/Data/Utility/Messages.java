package Data.Utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import com.example.jras.R;

import static androidx.navigation.Navigation.findNavController;

public class Messages {
    public void messageToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
    public void messageToastShort(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
    public  void messageAlert(Context context, String messageDescription, String messageTitle, View view,final int fragment){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(messageDescription)
                .setIcon(android.R.drawable.ic_menu_save)
                .setTitle(messageTitle)
                .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {findNavController(view).navigate(fragment);}
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public  void messageAlert(Context context, String messageDescription, String messageTitle, View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(messageDescription)
                .setIcon(android.R.drawable.ic_menu_save)
                .setTitle(messageTitle)
                .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
