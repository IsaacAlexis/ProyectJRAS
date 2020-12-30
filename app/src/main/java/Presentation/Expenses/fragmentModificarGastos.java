package Presentation.Expenses;

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

import BusinessLogic.BusinessExpense;
import Data.Models.ExpensesModel;
import Data.Models.UsersModel;
import Data.Utility.Messages;
import Data.Utility.Validations;

import static androidx.navigation.Navigation.findNavController;


public class fragmentModificarGastos extends Fragment {

    //Variables
    public EditText NameExp;
    public EditText Descript;
    public EditText total;
    public Button save;
    public TextView Folio;
    public TextView Fecha;
    public TextView IDUser;

    //Instancias de clases
    ExpensesModel expen = new ExpensesModel();
    Validations validate = new Validations();
    UsersModel data = new UsersModel();


    public fragmentModificarGastos() {
        // Required empty public constructor
    }
    public void fieldsEnable(){
        NameExp.setEnabled(true);
        Descript.setEnabled(true);
        total.setEnabled(true);
        save.setText("Guardar");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_modificar_gastos, container, false);

        getValues(view);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!save.getText().toString().equals("Editar")){
                    if (validate.IsValidTextbox(NameExp, "^([A-Za-zÁÉÍÓÚñáéíóúÑ]((\\s[A-Za-zÁÉÍÓÚñáéíóúÑ])*)){1,30}?$",
                            "Debes ingresar un nombre de gasto")
                            | validate.IsValidTextbox(Descript, "^([A-Za-zÁÉÍÓÚñáéíóúÑ]((\\s[A-Za-zÁÉÍÓÚñáéíóúÑ])*)){1,30}?$",
                            "Debes ingresar una descripcion del gasto")
                            | validate.IsValidTextbox(total, "^[0-9]+(\\.[0-9]{1,4})?$", "Debes ingresar un monto")) {
                        new Messages().messageToast(getContext(), "Debes llenar todos los campos correctamente");
                    } else {
                        setValues();
                        new BusinessExpense().BridgeExpenModify(expen);
                        if (!expen.isRegisterExpens()) {
                            new Messages().messageAlert(getContext(), expen.getValidationMessage(), "Cambios guardados con exito", view, R.id.fragmentGastos2);
                        } else {
                            new Messages().messageAlert(getContext(), expen.getValidationMessage(), "Error en guardar los cambios", view, R.id.fragmentGastos2);

                        }
                    }
                }else{
                    fieldsEnable();
                }

            }
        });

        return view;
    }

    public void getValues(View view) {
        NameExp = view.findViewById(R.id.TxtNombreG);
        Descript = view.findViewById(R.id.TxtDescripcionG);
        total = view.findViewById(R.id.TxtTotalG);
        Folio = view.findViewById(R.id.txtFolio);
        Fecha = view.findViewById(R.id.txtFechaGasto);
        save = view.findViewById(R.id.btnConfirmarGasto);
        PastValues();
    }

    public void setValues() {
        expen.setFolioExp(expen.getModifyFolioExp());
        expen.setNameExp(NameExp.getText().toString());
        expen.setDescript(Descript.getText().toString());
        expen.setTotal(Float.parseFloat(total.getText().toString()));
        expen.setDateModified(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

    }

    public void PastValues() {
        Folio.setText("Folio: " + String.valueOf(expen.getModifyFolioExp()));
        Fecha.setText("Fecha:  " + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        NameExp.setText(expen.getModifyNameExp());
        Descript.setText(expen.getModifyDescript());
        total.setText(String.valueOf(expen.getModifyTotal()));
    }

}