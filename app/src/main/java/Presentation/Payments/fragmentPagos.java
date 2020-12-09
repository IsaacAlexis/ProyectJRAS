package Presentation.Payments;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.jras.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import Data.Models.PaysModel;


public class fragmentPagos extends Fragment {
    // variables
    public TextView Propietario;
    public TextView NumeroCasa;
    public TextView CodigoBarras;
    public TextView FechaPago;
    public TextView FolioPago;
    public EditText Pago;
    public Button RegistrarPago;

    //instancias a otras clases
    PaysModel pays = new PaysModel();



    public fragmentPagos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_registrar_gastos, container, false);
        getValues(view);


        return view;
    }

    // Relacionar las variables con los componentes
    public void getValues(View view)
    {
        Propietario = view.findViewById(R.id.txtPropietarioCasa);
        NumeroCasa = view.findViewById(R.id.txtNumeroCasaPago);
        CodigoBarras = view.findViewById(R.id.txtBarCodePagos);
        FechaPago = view.findViewById(R.id.txtFechaPago);
        FolioPago = view.findViewById(R.id.txtFolioPago);
        Pago = view.findViewById(R.id.txtPago);
        RegistrarPago = view.findViewById(R.id.btnRegistrarPago);
        FechaPago.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
    }

    public void setValues() {
        pays.setOwner(Propietario.getText().toString());
        pays.setHouseNum(Integer.parseInt(NumeroCasa.getText().toString()));
        pays.setBarCodePay(CodigoBarras.getText().toString());
    }




}