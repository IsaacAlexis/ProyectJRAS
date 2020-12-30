package Presentation.Expenses;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.EventLogTags;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.jras.R;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import Data.Models.ExpensesModel;
import Data.Models.UsersModel;
import Data.Utility.LoadingDialog;
import Data.Utility.Messages;
import Data.Utility.Validations;
import BusinessLogic.BusinessExpense;

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
    private TextInputLayout tilExpenseName,tilDescription,tilTotal;
    private String ExpenseNameRegEx="^([A-Za-zÁÉÍÓÚñáéíóúÑ]((\\s[A-Za-zÁÉÍÓÚñáéíóúÑ])*)){1,30}?$";
    private String DescriptionRegEx="^([A-Za-zÁÉÍÓÚñáéíóúÑ]((\\s[A-Za-zÁÉÍÓÚñáéíóúÑ])*)){1,100}?$";
    private String TotalRegEx="^[0-9]+(\\.[0-9]{1,4})?$";




    //instancias
    ExpensesModel expens = new ExpensesModel();
    UsersModel data = new UsersModel();
    Validations validate = new Validations();
    Messages messages = new Messages();

    //Clase de pantalla de carga
    LoadingDialog loadingDialog = new LoadingDialog(fragmentRegistrarGastos.this);
    Handler handler = new Handler();

    public fragmentRegistrarGastos() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_registrar_gastos, container, false);

        //Ejecucion del metodo para relacionar las variables con el componente
        getvalues(view);
        registerexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.startLoadingDialogFragment(getContext());

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textboxEmpty();

                        if(!validate.isInvalid){
                            new Messages().messageToast(getContext(),"Debes llenar todo los campos correctamente");
                        }
                        else {
                            setvalues();

                            new BusinessExpense().BridgeExpenRegister(expens);

                            if(!expens.isRegisterExpens()) {
                                new Messages().messageAlert(getContext(),expens.getValidationMessage(),"Se completo con exito el registro",view,R.id.fragmentGastos2);
                            }
                            else {
                                new Messages().messageAlert(getContext(),expens.getValidationMessage(),"Eror al registrar",view,R.id.fragmentGastos2);

                            }
                        }
                        loadingDialog.dismissDialog();
                    }//fin de run()
                },50);//fin de postDelayed
            }//fin de onClick registerexp
        });//fin de registerexp.setOnClickListener
        return view;
    }

    private void textboxEmpty() {
        if (NameExpens.getText().length()==0 || DescripExpens.getText().length()==0 || total.getText().length()==0){
            messages.messageToast(getContext(),"Debes llenar todos los campos correctamente");
            validate.isInvalid=true;
        }
        else{
            validate.isInvalid=false;
        }
    }

    // Metodo para relacionar las variables declaradas con los componentes del layout
    public void getvalues(View view){
        NameExpens = view.findViewById(R.id.TxtNombreG);
        DescripExpens = view.findViewById(R.id.TxtDescripcionG);
        registerexp = view.findViewById(R.id.btnregistrarGastos);
        total = view.findViewById(R.id.TxtTotalG) ;
        DateExp = view.findViewById(R.id.txtFechaGasto);
        FOLIO = view.findViewById(R.id.txtFolio);
        FOLIO.setText("Folio: "+String.valueOf(new BusinessExpense().getLastFolio()+1));
        DateExp.setText("Fecha:  " + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));

        //**********TextInputLayout's**********
        tilExpenseName = view.findViewById(R.id.textInputLayout39);
        tilDescription = view.findViewById(R.id.textInputLayout40);
        tilTotal = view.findViewById(R.id.textInputLayout41);

        //**********Validations**********
        validate.IsValidTextboxOnClick(NameExpens,tilExpenseName,ExpenseNameRegEx,"Debes ingresar un nombre de gasto",registerexp);
        validate.IsValidTextboxOnClick(DescripExpens,tilDescription,DescriptionRegEx,"Debes ingresar una descripcion del gasto",registerexp);
        validate.IsValidTextboxOnClick(total,tilTotal,TotalRegEx,"Debes ingresar un monto",registerexp);

    }

    //Metodo para obtener lo que hay dentro de los EditText
    public void setvalues(){
            //Cambiando los valores por los obtenidos
            expens.setNameExp(NameExpens.getText().toString());
            expens.setDescript((DescripExpens.getText().toString()));
            expens.setTotal(Float.parseFloat(total.getText().toString()));
            expens.setExpDate(new Date());
            expens.setIDUser(data.getCurrentIdUser());
            expens.setDateModified( new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
    }

}