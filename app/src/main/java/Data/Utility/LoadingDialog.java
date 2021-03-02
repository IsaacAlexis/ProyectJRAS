package Data.Utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.jras.R;

public class LoadingDialog {

    private Activity activity;
    private Fragment fragment;
    private AlertDialog dialog;

    public LoadingDialog(Fragment myFragment){
        fragment = myFragment;
    }
    public LoadingDialog(Activity myActivity){
        activity = myActivity;
    }

    public void startLoadingDialogActivity(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        TextView message;

        LayoutInflater inflater = activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.loading_dialog,null);
        message=view.findViewById(R.id.textViewmessage);
        message.setText("Iniciando...");

        builder.setView(view);
//        builder.setView(inflater.inflate(R.layout.loading_dialog,null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void startLoadingDialogFragment(Context context,String messages){
        TextView message;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = fragment.getLayoutInflater();
        View view=inflater.inflate(R.layout.loading_dialog,null);
        message=view.findViewById(R.id.textViewmessage);
        message.setText(messages);

        builder.setView(view);
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void dismissDialog(){
        dialog.dismiss();
    }
}
