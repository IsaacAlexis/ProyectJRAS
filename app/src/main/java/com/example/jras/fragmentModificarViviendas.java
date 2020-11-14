package com.example.jras;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import Data.Models.HouseModel;

public class fragmentModificarViviendas extends Fragment {

    private FragmentModificarViviendasViewModel mViewModel;
    private EditText owner;
    private EditText phoneNumber;
    private EditText email;
    private EditText street;
    private EditText houseNumber;
    private EditText zipCode;
    private EditText colony;
    private EditText city;
    private EditText state;
    private TextView statusHouse;
    HouseModel house=new HouseModel();
    public void assignValues(){
        owner.setText(house.getOwner());
        phoneNumber.setText(house.getPhoneNumber().toString());
        email.setText(house.getEmail());
        street.setText(house.getStreet());
        houseNumber.setText(house.getHouseNumber().toString());
        zipCode.setText(house.getZipCode().toString());
        colony.setText(house.getColony());
        city.setText(house.getCity());
        state.setText(house.getState());
        statusHouse.setText(house.getStatusHouse());
    }

    public void getvalues(View view){
        owner= view.findViewById(R.id.txtPropietario);
        phoneNumber=view.findViewById(R.id.txtTelefonoViv);
        email=view.findViewById(R.id.txtEmail);
        street=view.findViewById(R.id.txtCalleViv);
        houseNumber= view.findViewById(R.id.txtNumViv);
        zipCode= view.findViewById(R.id.txtCPViv);
        colony= view.findViewById(R.id.txtColViv);
        city= view.findViewById(R.id.txtCiudadViv);
        state= view.findViewById(R.id.txtEstadoViv);
        statusHouse= view.findViewById(R.id.lbStatus);
        assignValues();
    }
    public static fragmentModificarViviendas newInstance() {
        return new fragmentModificarViviendas();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_modificar_viviendas, container, false);
        getvalues(view);//Se obtienen los datos a modificar
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentModificarViviendasViewModel.class);
        // TODO: Use the ViewModel
    }

}