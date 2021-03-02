package Presentation.Payments;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import com.example.jras.R;
import com.google.android.material.textfield.TextInputLayout;

import BusinessLogic.BusinessPayments;
import Data.Models.PaymentsModel;
import Data.Models.PaysModel;
import Data.Utility.Messages;
import Data.Utility.RegExValidations;
import Data.Utility.Validations;

import static androidx.navigation.Navigation.findNavController;


public class fragmentPagos extends Fragment {


    // variables
    public TextView Owner;
    private TextInputLayout tilAmountPay;
    public TextView Total;
    public TextView DebitPeriod;
    public TextView Status;
    public RecyclerView mRecycleView;
    public Switch TypePayment;
    public TextView NamePayment;
    public EditText AmountPay;
    public Button btnPay;
    private RecyclerView.LayoutManager mLayoutManger;
    private DebitsAdapter debitsAdapter;

    //instancias a otras clases
    PaysModel pays = new PaysModel();



    public fragmentPagos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_pagos, container, false);
       getValues(view);
        btnPay.setEnabled(false);
        btnPay.setBackgroundResource(R.drawable.boton_desabilitado);
        new Validations().IsValidTextboxOnClick(AmountPay,tilAmountPay,new RegExValidations().
                validNumberDecimal,"Debes escribir numeros, no se aceptan caracteres",btnPay);

       debitsAdapter=new DebitsAdapter(new PaymentsModel().getDebits(), getContext());
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.setLayoutManager(mLayoutManger);
        mRecycleView.setAdapter(debitsAdapter);
        TypePayment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    NamePayment.setText("ABONO");
                    AmountPay.setVisibility(View.VISIBLE);
                    tilAmountPay.setVisibility(View.VISIBLE);
                }else{
                    NamePayment.setText("PAGO TOTAL");
                    AmountPay.setVisibility(View.INVISIBLE);
                    tilAmountPay.setVisibility(View.INVISIBLE);
                }
            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TypePayment.isChecked()){
                    new BusinessPayments().RegisterPayment(Float.parseFloat(AmountPay.getText().toString()),2,fragmentPagos.this.getContext());

                }else{
                    new BusinessPayments().RegisterPayment(Float.parseFloat("0"),1,fragmentPagos.this.getContext());

                }
                new Messages().messageToast(getContext(),new PaymentsModel().getValidationMessage());
                findNavController(v).navigate(R.id.info_Pagos);
            }
        });


        return view;
    }

    // Relacionar las variables con los componentes
    public void getValues(View view)
    {
        Owner=view.findViewById(R.id.PropietarioPagos);
        Total=view.findViewById(R.id.TotalPagos);
        DebitPeriod=view.findViewById(R.id.PeriodosPagos);
        Status=view.findViewById(R.id.StatusPagos);
        mRecycleView=view.findViewById(R.id.RecycleViewAdeudoPagos);
        TypePayment=view.findViewById(R.id.TipoPago);
        NamePayment=view.findViewById(R.id.NombreTipoPago);
        AmountPay=view.findViewById(R.id.txtAbonoPagos);
        tilAmountPay=view.findViewById(R.id.tilAbonoPagos);
        btnPay=view.findViewById(R.id.btnPagar);
        mLayoutManger=new LinearLayoutManager(getContext());
        Owner.setText(new PaymentsModel().getOwner());
        Total.setText("$"+new PaymentsModel().getTotal()+"0");
        DebitPeriod.setText(DebitPeriod.getText()+" "+new PaymentsModel().getDebitPeriod());
        Status.setText(Status.getText()+" "+new PaymentsModel().getStatus());

    }

    public void setValues() {

    }




}