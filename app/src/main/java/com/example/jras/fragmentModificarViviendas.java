package com.example.jras;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Data.Models.HouseModel;
import Data.Utility.Validations;
import BusinessLogic.BusinessHomesRegister;

import static androidx.navigation.Navigation.findNavController;

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
    private Button savechanges;

    HouseModel house=new HouseModel();
    Validations validations=new Validations();
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
    public void assignValuesModificate(){
        house.setOwner(owner.getText().toString());
        house.setPhoneNumber(Long.parseLong(phoneNumber.getText().toString()));
        house.setEmail(email.getText().toString());
        house.setStreet(street.getText().toString());
        house.setHouseNumber(Integer.parseInt(houseNumber.getText().toString()));
        house.setZipCode(Integer.parseInt(zipCode.getText().toString()));
        house.setColony(colony.getText().toString());
        house.setCity(city.getText().toString());
        house.setState(state.getText().toString());
        house.setStatusHouse(statusHouse.getText().toString());
    }
    public static fragmentModificarViviendas newInstance() {
        return new fragmentModificarViviendas();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_modificar_viviendas, container, false);
        getvalues(view);
        savechanges=view.findViewById(R.id.btnregistrarVivienda);
        savechanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                if(validations.IsTextboxEmpty(owner,"Debes ingresar un nombre del propietario valido")|
                        validations.IsTextboxEmpty(phoneNumber,"Debes ingresar un numero de telefono valido")|
                        validations.IsTextboxEmpty(email,"Debes ingresar un correo de electronico valido")|
                        validations.IsTextboxEmpty(street,"Debes ingresar la calle de la vivienda correctamente")|
                        validations.IsTextboxEmpty(houseNumber,"Debes ingresar el numero de la vivienda")|
                        validations.IsTextboxEmpty(zipCode,"Debes ingresar un codigo postal valido")|
                        validations.IsTextboxEmpty(colony,"Debes ingresar la colonia de la vivienda")|
                        validations.IsTextboxEmpty(city,"Debes ingresar la ciudad donde se ubica la vivienda")|
                        validations.IsTextboxEmpty(state,"Debes ingresar el estado donde se encuentra la vivienda")){
                    Toast.makeText(getContext(),"No debes dejar ningun campo vacio",Toast.LENGTH_LONG).show();

                }else{
                     assignValuesModificate();
                    new BusinessHomesRegister().BridgeHouseUpdate(house);
                    if(house.getStatusActivity()){
                        builder.setMessage("Los cambios se han guardado exitosamente")
                                .setIcon(android.R.drawable.ic_menu_save)
                                .setTitle("Cambios realizados con exito")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {  findNavController(view).navigate(R.id.fragmentViviendas);}
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }else{
                        Toast.makeText(getContext(),"Ocurrio un error al guardar los datos",Toast.LENGTH_LONG).show();
                    }

                }
            }

        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentModificarViviendasViewModel.class);
        // TODO: Use the ViewModel
    }

}