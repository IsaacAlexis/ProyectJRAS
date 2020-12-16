package Data.Utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;

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

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog,null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void startLoadingDialogFragment(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = fragment.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog,null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void dismissDialog(){
        dialog.dismiss();
    }
}
