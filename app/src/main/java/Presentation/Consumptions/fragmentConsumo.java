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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BusinessLogic.BusinessConsumption;
import Data.Models.ConsumptionsModel;
import Data.Models.UsersModel;
import Data.Models.WaterBillsModel;
import Data.Utility.Dates;
import Data.Utility.GenaratorPDF;
import Data.Utility.Messages;

import static androidx.navigation.Navigation.findNavController;

public class fragmentConsumo extends Fragment {

    private TextView tvBarCode;
    private TextView tvOwner;
    private TextView tvHouseNum;
    private EditText etConsumption;
    private EditText etLastConsumption;
    private EditText etLastRate;
    private Button btnRegistrar;
    private String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    WaterBillsModel waterBillsModel = new WaterBillsModel();
    ConsumptionsModel cm = new ConsumptionsModel();
    UsersModel user = new UsersModel();
    List<WaterBillsModel> bill=new ArrayList<>();
    List<ConsumptionsModel> consumptionsModelList=new ArrayList<>();


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
        etLastConsumption=view.findViewById(R.id.txtConsumoAnterior);
        etLastRate=view.findViewById(R.id.txtPagoAnterior);
        btnRegistrar = view.findViewById(R.id.btnRegistrarConsumo);

        mostrarInfoViv(view);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!waterBillsModel.isExistFirstRegister()){
                    registrar(false);
                    new BusinessConsumption().BridgeConsumptionFirstReading(getContext(),cm,consumptionsModelList);


                }else {
                    registrar(true);
                    if(!(new GenaratorPDF().createPDFWaterBills(getContext(),bill))){
                        new BusinessConsumption().BridgeConsumptionReading(cm);
                    }
                }


                Toast.makeText(getContext(), cm.getValidationMessage(), Toast.LENGTH_SHORT).show();
                findNavController(view).navigate(R.id.nav_home);
            }
        });


        return view;
    }


    private void mostrarInfoViv(View view){
        if(!waterBillsModel.isExistFirstRegister()){
            new Messages().messageAlert(getContext(),"Debes registrar el ultimo y el actual consumo de agua","No existe registros de consumo en esta vivienda",view);
            tvBarCode.setText(waterBillsModel.getBarCode());
            tvHouseNum.setText(""+waterBillsModel.getHouseNumber());
            tvOwner.setText(waterBillsModel.getOwner());
            etLastConsumption.setVisibility(View.VISIBLE);
            etLastRate.setVisibility(View.VISIBLE);


        }else{
            tvBarCode.setText(waterBillsModel.getBarCode());
            tvHouseNum.setText(""+waterBillsModel.getHouseNumber());
            tvOwner.setText(waterBillsModel.getOwner());
        }

    }
/*activity-> se refiere al camino que va tomar si seria el primer registro o realizar el registro con
con normalidad*/
    private void registrar(Boolean activity){
        if(!activity){
            cm.setIDUser(user.getCurrentIdUser());
            cm.setBarCode(tvBarCode.getText().toString());
            cm.setRate(Float.parseFloat(etLastRate.getText().toString()));
            consumptionsModelList.add(new ConsumptionsModel(currentDate,Float.parseFloat(etConsumption.getText().toString())));
            consumptionsModelList.add(new ConsumptionsModel(new Dates().getLastDate(),Float.parseFloat(etLastConsumption.getText().toString())));

        }else{
            cm.setIDUser(user.getCurrentIdUser());
            cm.setBarCode(tvBarCode.getText().toString());
            cm.setReadDate(currentDate);
            cm.setM3(Float.parseFloat(etConsumption.getText().toString()));
            waterBillsModel.setReadNow(Float.parseFloat(etConsumption.getText().toString()));
            waterBillsModel.setReadDate(new Dates().getDateBills());
            waterBillsModel.setNowRate(new BusinessConsumption().BridgeGetRateNow(waterBillsModel.getReadNow()-
                    waterBillsModel.getReadLast()));
            bill=new BusinessConsumption().BridgeWaterBills(cm);
        }

    }


}