package Presentation.Consumptions;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jras.R;

import java.util.Date;

import BusinessLogic.BusinessConsumption;
import Data.Models.ConsumptionsModel;
import Data.Models.HousesModel;
import Data.Models.UsersModel;

import static androidx.navigation.Navigation.findNavController;

public class fragmentConsumo extends Fragment {

    private TextView tvBarCode;
    private TextView tvOwner;
    private TextView tvHouseNum;
    private EditText etConsumption;
    private Button btnRegistrar;
    private String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    HousesModel home = new HousesModel();
    ConsumptionsModel cm = new ConsumptionsModel();
    UsersModel user = new UsersModel();

    public fragmentConsumo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_consumo, container, false);

        tvBarCode = view.findViewById(R.id.txtBarCodeConsumo);
        tvOwner = view.findViewById(R.id.txtPropietarioC);
        tvHouseNum = view.findViewById(R.id.txtNumeroCasaConsumo);
        etConsumption = view.findViewById(R.id.txtConsumo);
        btnRegistrar = view.findViewById(R.id.btnRegistrarConsumo);

        mostrarInfoViv();


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
                new BusinessConsumption().BridgeConsumptionReading(cm);
                Toast.makeText(getContext(), "Consumo registrado correctamente", Toast.LENGTH_SHORT).show();
                findNavController(view).navigate(R.id.nav_home);
            }
        });


        return view;
    }


    private void mostrarInfoViv(){
        tvBarCode.setText(home.getModifybarCode());
        tvHouseNum.setText(home.getModifyhouseNumber().toString());
        tvOwner.setText(home.getModifyowner());
    }

    private void registrar(){
        cm.setIDUser(user.getCurrentIdUser());
        cm.setBarCode(tvBarCode.getText().toString());
        cm.setReadDate(currentDate);
        cm.setM3(Float.parseFloat(etConsumption.getText().toString()));
    }


}