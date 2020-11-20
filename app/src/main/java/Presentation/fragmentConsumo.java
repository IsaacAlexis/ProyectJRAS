package Presentation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jras.R;

import BusinessLogic.BusinessHomesRegister;
import Data.Models.HomesModel;

public class fragmentConsumo extends Fragment {

    private TextView tvBarCode;
    private TextView tvOwner;
    private TextView tvHouseNum;
    private Button btnRegistrar;

    HomesModel home = new HomesModel();

    public fragmentConsumo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_consumo, container, false);

        tvBarCode = view.findViewById(R.id.txtBarCode);
        tvOwner = view.findViewById(R.id.txtPropietarioC);
        tvHouseNum = view.findViewById(R.id.txtNumeroCasa);
        btnRegistrar = view.findViewById(R.id.btnRegistrarConsumo);

        mostrarInfo();


        return view;
    }


    private void mostrarInfo(){
        tvBarCode.setText(home.getBarCode());
        new BusinessHomesRegister().BridgeHouseScanner(home);
        tvHouseNum.setText(home.getHouseNum());
        tvOwner.setText(home.getOwner());

    }

}