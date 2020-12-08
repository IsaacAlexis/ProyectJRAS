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
import Data.Models.ExpensesModel;
import Data.Models.UsersModel;
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



    //instancia
    ExpensesModel expens = new ExpensesModel();
    UsersModel data = new UsersModel();
    Validations validate = new Validations();
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
               if(validate.IsValidTextbox(NameExpens, "^([A-Za-zÁÉÍÓÚñáéíóúÑ]((\\s[A-Za-zÁÉÍÓÚñáéíóúÑ])*)){1,30}?$",
                       "Debes ingresar un nombre de gasto")
                |validate.IsValidTextbox(DescripExpens,"^([A-Za-zÁÉÍÓÚñáéíóúÑ]((\\s[A-Za-zÁÉÍÓÚñáéíóúÑ])*)){1,30}?$",
                       "Debes ingresar una descripcion del gasto")
                |validate.IsValidTextbox(total,"^[0-9]+(\\.[0-9]{1,4})?$", "Debes ingresar un monto")
                )
                {
                    new Messages().messageToast(getContext(),"Debes llenar todo los campos correctamente");
                }
                else {
                    setvalues();

                  new BusinessExpense().BridgeExpenRegister(expens);
                  
                    if(!expens.isRegisterExpens())
                    {
                        new Messages().messageAlert(getContext(),expens.getValidationMessage(),"Se completo con exito el registro",view,R.id.fragmentGastos2);
                    }
                    else
                    {
                        new Messages().messageAlert(getContext(),expens.getValidationMessage(),"Eror al registrar",view,R.id.fragmentGastos2);

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
        FOLIO = view.findViewById(R.id.txtFolio);
        FOLIO.setText("Folio: "+String.valueOf(new BusinessExpense().getLastFolio()+1));
        DateExp.setText( new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
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