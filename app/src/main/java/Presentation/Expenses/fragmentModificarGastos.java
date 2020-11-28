package Presentation.Expenses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jras.R;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import BusinessLogic.BusinessExpens;
import Data.BDConnection;
import Data.Models.ExpensesModel;
import Data.Models.UsersModel;
import Data.Utility.Validations;

import static androidx.navigation.Navigation.findNavController;


public class fragmentModificarGastos extends Fragment {

    //Variables
    public EditText NameExp;
    public EditText Descript;
    public EditText total;
    public Button Modify;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_modificar_gastos, container, false);

        getValues(view);
        setValues();
        PastValues();

        Modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate.IsValidTextbox(NameExp, "^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,10}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,10})(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,13})?$", "Debes ingresar un nombre de gasto")
                        | validate.IsValidTextbox(Descript, "^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,12}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,12})(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,12})(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,12})(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,12})?$", "Debes ingresar una descripcion del gasto")
                        | validate.IsValidTextbox(total, "^[0-9]{1,12}?$", "Debes ingresar un monto")
                ) {
                    message(getContext(), "Debes llenar todos los campos correctamente", "ERROR CAMPOS INCOMPLETOS", false, 1, view);
                } else {
                    setValues();
                    new BusinessExpens().BridgeExpenModify(expen);
                    if (!expen.isRegisterExpens()) {
                        message(getContext(), data.getValidationMessage(), "Se modifico con exito", true, R.id.fragmentModificarGastos, view);
                    } else {
                        message(getContext(), data.getValidationMessage(), "ERROR AL MODIFICAR", true, R.id.fragmentModificarGastos, view);

                    }
                }
            }
        });

        return view;
    }

    public void getValues(View view) {
        NameExp = view.findViewById(R.id.txtNombreGasto);
        Descript = view.findViewById(R.id.txtDescripcion);
        total = view.findViewById(R.id.txtTotal);
        Folio = view.findViewById(R.id.txtFolio);
        Fecha = view.findViewById(R.id.txtFechaGasto);
        Modify = view.findViewById(R.id.btnConfirmarGasto);
    }

    public void setValues() {
        expen.setFolioExp(expen.getFolioExp());
        expen.setIDUser(expen.getIDUser());
        expen.setExpDate(expen.getExpDate());
        expen.setNameExp(expen.getNameExp());
        expen.setDescript(expen.getDescript());
        expen.setTotal(expen.getTotal());

    }

    public void PastValues() {
        Folio.setText(String.valueOf(expen.getFolioExp()));
        Fecha.setText(String.valueOf(expen.getExpDate()));
        NameExp.setText(expen.getNameExp());
        Descript.setText(expen.getDescript());
        total.setText(String.valueOf(expen.getTotal()));
    }

    public void message(Context context, String message, String title, boolean isMoveFrgment, final int fragment, final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (!isMoveFrgment) {
            builder.setMessage(message)
                    .setIcon(android.R.drawable.ic_menu_save)
                    .setTitle(title)
                    .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            builder.setMessage(message)
                    .setIcon(android.R.drawable.ic_menu_save)
                    .setTitle(title)
                    .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            findNavController(v).navigate(fragment);
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
}