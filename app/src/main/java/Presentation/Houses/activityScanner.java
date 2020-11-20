package Presentation.Houses;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class activityScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView escanerZXing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        escanerZXing = new ZXingScannerView(this);
        setContentView(escanerZXing);
    }

    @Override
    protected void onResume() {
        super.onResume();
        escanerZXing.setResultHandler(this);
        escanerZXing.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        escanerZXing.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        String codigo = rawResult.getText();
        Intent intentregreso = new Intent();
        intentregreso.putExtra("codigo",codigo);
        setResult(Activity.RESULT_OK,intentregreso);

        finish();
    }
}