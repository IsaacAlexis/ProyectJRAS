package Presentation.Houses;



import android.app.AlertDialog;
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

import com.example.jras.R;

import Data.Models.HousesModel;
import Data.Utility.Messages;
import Data.Utility.Validations;
import BusinessLogic.BusinessHouse;

import static androidx.navigation.Navigation.findNavController;

public class fragmentModificarViviendas extends Fragment {
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

    HousesModel house=new HousesModel();
    Validations validations=new Validations();
    Messages messages=new Messages();
    public void assignValues(){
        owner.setText(house.getModifyowner());
        phoneNumber.setText(house.getModifyphoneNumber().toString());
        email.setText(house.getModifyemail());
        street.setText(house.getModifystreet());
        street.setEnabled(false);
        houseNumber.setText(house.getModifyhouseNumber().toString());
        houseNumber.setEnabled(false);
        zipCode.setText(house.getModifyzipCode().toString());
        colony.setText(house.getModifycolony());
        colony.setEnabled(false);
        city.setText(house.getModifycity());
        state.setText(house.getModifystate());
        statusHouse.setText(house.getModifystatusHouse());
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
        statusHouse= view.findViewById(R.id.lbRolm);
        savechanges=view.findViewById(R.id.btnregistrarViviendaM);
        assignValues();
    }
    public void assignValuesModificate(){
        house.setBarCode(house.getModifybarCode());
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
    public void fieldsEnable(){
        owner.setEnabled(true);
        phoneNumber.setEnabled(true);
        email.setEnabled(true);
        zipCode.setEnabled(true);
        city.setEnabled(true);
        state.setEnabled(true);
        statusHouse.setEnabled(true);
        savechanges.setText("Guardar");
    }
    public static fragmentModificarViviendas newInstance() {
        return new fragmentModificarViviendas();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_modificar_viviendas, container, false);
        getvalues(view);
        savechanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!savechanges.getText().toString().equals("Editar")){
                    if(validations.IsValidTextbox(owner,"^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})?(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})?$","Debes ingresar un Nombre del propietario completo correctamente")|
                            validations.IsValidTextbox(phoneNumber,"^\\d{10,15}$","Debes ingresar un numero de telefono correctamente")|
                            validations.IsValidTextbox(email,"^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$","Debes ingresar un correo electronico correctamente")|
                            validations.IsValidTextbox(street,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.0-9]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ0-9]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?$","Debes ingresar una calle de la vivienda correctamente")|
                            validations.IsValidTextbox(houseNumber,"^\\d{1,8}$","Debes ingresar el numero de la vivienda correctamente")|
                            validations.IsValidTextbox(zipCode,"^\\d{5}$","Debes ingresar un codigo postal correctamente")|
                            validations.IsValidTextbox(colony,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?$","Debes ingresar la colonia de la vivienda correctamente")|
                            validations.IsValidTextbox(city,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?$","Debes ingresar el municipio donde se ubica la vivienda correctamente")|
                            validations.IsValidTextbox(state,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?$","Debes ingresar el Estado donde se ubica la vivienda correctamente ")){
                        messages.messageToast(getContext(),"Debes llenar los campos con los formatos establecidos");
                    }else{
                        assignValuesModificate();
                        new BusinessHouse().BridgeHouseUpdate(house);
                        if(house.isStatusActivity()){
                            messages.messageAlert(getContext(),house.getMessage(),"Cambios realizados con exito",view,R.id.fragmentViviendas);
                        }else{
                            messages.messageToast(getContext(),house.getMessage());
                            findNavController(view).navigate(R.id.fragmentViviendas);
                        }

                    }
                }else{
                    fieldsEnable();
                }


            }

        });
        return view;
    }



}