package Presentation.Expenses;

import android.animation.TypeConverter;
import android.content.ContentValues;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.arch.core.executor.DefaultTaskExecutor;
import androidx.fragment.app.Fragment;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jras.R;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Data.BDConnection;
import Data.Models.ExpensesModel;
import Data.Models.UsersModel;
import Data.Utility.Validations;
import BusinessLogic.BusinessExpens;
import Security.ExpensRegister;

import static androidx.navigation.Navigation.findNavController;


public class fragmentRegistrarGastos extends Fragment {


    //variables
    public EditText NameExpens;
    public EditText DescripExpens;
    public Button registerexp;
    public float Total;
    public EditText total;
    public TextView DateExp;
    public Date fecha;
    public String folio;
    public long Folio;
    public TextView FOLIO;
    public int FolioReal;
    //String currenteDataandTime = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date());
    String ActualyTime = new SimpleDateFormat("yyy-MM-dd").format(new Date());

    //instancia
    ExpensesModel expens = new ExpensesModel();
    UsersModel data = new UsersModel();
    Date cal = (Date) Calendar.getInstance().getTime();


    public fragmentRegistrarGastos() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_registrar_gastos, container, false);

        //Ejecucion del metodo para relacionar las variables con el componente
        getvalues(view);
        ExpensExist();
        NFolio();

        registerexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validations validate = new Validations();

                if(validate.IsValidTextbox(NameExpens, "^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,10}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,10})(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,13})?$", "Debes ingresar un nombre de gasto")
                |validate.IsValidTextbox(DescripExpens,"^[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,13}(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,13})(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,13})(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,13})(\\s[A-Za-zÁÉÍÓÚñáéíóúÑ]{1,13})?$", "Debes ingresar una descripcion del gasto")
                |validate.IsValidTextbox(total,"^[0-9]{1,12}?$", "Debes ingresar un monto")
                )
                {
                     message(getContext(),"Debes llenar todo los campos correctamente","ERROR CAMPOS INCOMPLETOS",false, 1,view);
                }
                else {

                    setvalues();

                  new BusinessExpens().BridgeExpenRegister(expens);
                  
                    if(!expens.isRegisterExpens())
                    {
                        message(getContext(), data.getValidationMessage(), "Se completo el registro", true, R.id.fragmentRegistrarGastos, view);
                    }
                    else
                    {
                        message(getContext(), data.getValidationMessage(), "ERROR AL REGISTRAR", true, R.id.fragmentRegistrarGastos, view);

                    }
                     }

            }
        });



       // return inflater.inflate(R.layout.fragment_registrar_gastos, container, false);
        return view;


    }
    // Metodo para relacionar las variables declaradas con los componentes del layout
    public void getvalues(View view){
        NameExpens = view.findViewById(R.id.TxtNombreG);
        DescripExpens = view.findViewById(R.id.TxtDescripcionG);
        registerexp = view.findViewById(R.id.btnregistrarGastos);
        total = view.findViewById(R.id.TxtTotalG) ;
        DateExp = view.findViewById(R.id.txtFechaGasto);
        DateExp.setText(ActualyTime);
        FOLIO = view.findViewById(R.id.txtFolio);



        
    }

    //Metodo para obtener lo que hay dentro de los EditText
    public void setvalues(){
            //Cambiando los valores por los obtenidos
            expens.setNameExp(NameExpens.getText().toString());
            expens.setDescript((DescripExpens.getText().toString()));
            expens.setTotal(Total = Float.parseFloat(total.getText().toString()));
            expens.setExpDate(cal);
            data.getCurrentIdUser();
            expens.setIDUser(data.getCurrentIdUser());

    }

    public void message(Context context, String message, String title, boolean isMoveFrgment, final int fragment, final View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if(!isMoveFrgment){
            builder.setMessage(message)
                    .setIcon(android.R.drawable.ic_menu_save)
                    .setTitle(title)
                    .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }else{
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

    public static String FechaActual()
    {
        Date fecha = new Date();
        SimpleDateFormat FormatoFecha = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        return FormatoFecha.format(fecha);
    }

    public void ExpensExist ()
    {
        BDConnection bd = new BDConnection();
        try
        {
            bd.ConnectionwithSQL().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            CallableStatement callableStatement =  bd.connection.prepareCall("{call ExpenExist}");
            ResultSet Result = callableStatement.executeQuery();
            while(Result.next())
            {
                expens.setFolioExp(Result.getLong("FolioExp"));
            }
            Result = callableStatement.executeQuery();



        }
        catch (Exception e)
        {
            e.printStackTrace();

        }

    }
    public void NFolio(){
        expens.setFolioExp(expens.getFolioExp());
        Folio = expens.getFolioExp();
        folio = String.valueOf(Folio);
        FolioReal = Integer.parseInt(folio);
        FolioReal = FolioReal + 1;
        folio = String.valueOf(FolioReal);
        FOLIO.setText("Numero de Folio:" + folio);
    }
}