package Presentation.Houses;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jras.R;

import BusinessLogic.BusinessHouse;
import Data.Models.HousesModel;
import Data.Models.UsersModel;
import Data.Utility.Messages;
import Data.Utility.Validations;

import static androidx.navigation.Navigation.findNavController;

public class fragmentRegistrarViviendas extends Fragment {

    //Variables
    private EditText owner,phoneNumber;
    private EditText email,street,houseNumber;
    private EditText zipCode,colony,city,state;
    private Button btnRegistrar;

    //instancias de otras clases
    HousesModel house = new HousesModel();
    UsersModel users=new UsersModel();
    public Validations validations = new Validations();
    Messages messages=new Messages();

    public fragmentRegistrarViviendas() {
        // Required empty public constructor
    }
    public void getValues(View view){

        owner = view.findViewById(R.id.txtPropietario);
        phoneNumber = view.findViewById(R.id.txtTelefonoViv);
        email = view.findViewById(R.id.txtEmail);
        street = view.findViewById(R.id.txtCalleViv);
        houseNumber = view.findViewById(R.id.txtNumViv);
        zipCode = view.findViewById(R.id.txtCPViv);
        colony= view.findViewById(R.id.txtColViv);
        colony.setText(users.getCurrentColony());
        colony.setEnabled(false);
        city= view.findViewById(R.id.txtCiudadViv);
        state= view.findViewById(R.id.txtEstadoViv);
        btnRegistrar= view.findViewById(R.id.btnregistrarViviendaM);
        validateFields();
        }
    public void validateFields(){
        validations.IsValidTextboxOnClick(owner,
                "^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})?(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})?$",
                "Debes ingresar un Nombre del propietario completo correctamente");
        validations.IsValidTextboxOnClick(phoneNumber,"^\\d{10,15}$","Debes ingresar un numero de telefono correctamente");
        validations.IsValidTextboxOnClick(email,"^[_a-zA-Z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(.[A-Za-z]{2,4})$",
                "Debes ingresar un correo electronico correctamente");
        validations.IsValidTextboxOnClick(street,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.0-9]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ0-9]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?$",
                "Debes ingresar una calle de la vivienda correctamente");
        validations.IsValidTextboxOnClick(houseNumber,"^\\d{1,8}$","Debes ingresar el numero de la vivienda correctamente");
        validations.IsValidTextboxOnClick(zipCode,"^\\d{5}$","Debes ingresar un codigo postal correctamente");
        validations.IsValidTextboxOnClick(colony,
                "^[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?$",
                "Debes ingresar la colonia de la vivienda correctamente");
        validations.IsValidTextboxOnClick(city,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?$",
                "Debes ingresar el municipio donde se ubica la vivienda correctamente");
        validations.IsValidTextboxOnClick(state,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?$",
                "Debes ingresar el Estado donde se ubica la vivienda correctamente ");

    }
    private void setValues(){
        house.setBarCode(colony.getText().toString().trim().substring(0,3).toUpperCase()+"-"+street.getText().toString().substring(0,3).toUpperCase()+
                "-"+houseNumber.getText().toString().substring(0,3));
        house.setOwner(owner.getText().toString());
        house.setPhoneNumber(Long.parseLong(phoneNumber.getText().toString()) );
        house.setEmail(email.getText().toString());
        house.setStreet(street.getText().toString());
        house.setHouseNumber(Integer.parseInt(houseNumber.getText().toString()));
        house.setZipCode(Integer.parseInt(zipCode.getText().toString()));
        house.setColony(colony.getText().toString());
        house.setCity(city.getText().toString());
        house.setState(state.getText().toString());
        house.setStatusHouse("SIN ADEUDO");
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_registrar_viviendas, container, false);
        //Obtencion de los componentes y validacion de datos.
        getValues(view);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validations.IsValidTextbox(owner,"^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})?(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,20})?$","Debes ingresar un Nombre del propietario completo correctamente")|
                        validations.IsValidTextbox(phoneNumber,"^\\d{10,15}$","Debes ingresar un numero de telefono correctamente")|
                        validations.IsValidTextbox(email,"^[A-Z_a-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(.[A-Za-z]{2,4})$","Debes ingresar un correo electronico correctamente")|
                        validations.IsValidTextbox(street,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.0-9]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ0-9]{1,15})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?$","Debes ingresar una calle de la vivienda correctamente")|
                        validations.IsValidTextbox(houseNumber,"^\\d{1,8}$","Debes ingresar el numero de la vivienda correctamente")|
                        validations.IsValidTextbox(zipCode,"^\\d{5}$","Debes ingresar un codigo postal correctamente")|
                        validations.IsValidTextbox(colony,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,15})?$","Debes ingresar la colonia de la vivienda correctamente")|
                        validations.IsValidTextbox(city,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?$","Debes ingresar el municipio donde se ubica la vivienda correctamente")|
                        validations.IsValidTextbox(state,"^[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10}(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?(\\s[a-zA-ZÁÉÍÓÚñáéíóúÑ.]{1,10})?$","Debes ingresar el Estado donde se ubica la vivienda correctamente ")){
                    messages.messageToast(getContext(),"Debes llenar los campos con los formatos establecidos");
                    }else{
                    setValues();
                    new BusinessHouse().BridgeHomeRegister(house);
                    if(!house.isStatusActivity()){
                        messages.messageAlert(getContext(),house.getMessage(),"Registro exitoso",view,R.id.fragmentViviendas);
                    }else{
                        messages.messageToast(getContext(),house.getMessage());
                        findNavController(view).navigate(R.id.fragmentViviendas);
                    }

                }
            }
        });

        return view;
    }



}//fin class fragment