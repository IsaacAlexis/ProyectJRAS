package Presentation.Payments;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import Data.Models.WaterBillsModel;
import Data.Utility.Messages;
import Data.Utility.Notificaciones;
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



    PaymentsModel pays=new PaymentsModel();
//    Notificaciones notify = new Notificaciones();
//    WaterBillsModel waterBillsModel = new WaterBillsModel();






    public fragmentPagos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_pagos, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AmountPay=view.findViewById(R.id.txtAbonoPagos);
        if(getArguments()!=null){
            getValues(view);

            new Validations().IsValidTextboxOnClick(AmountPay,tilAmountPay,new RegExValidations().
                    validNumberDecimal,"Debes escribir numeros, no se aceptan caracteres",btnPay);
            AmountPay.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            debitsAdapter=new DebitsAdapter(pays.getDebits(), getContext());
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
                        btnPay.setEnabled(false);
                        btnPay.setBackgroundResource(R.drawable.boton_desabilitado);
                    }else{
                        NamePayment.setText("PAGO TOTAL");
                        AmountPay.setVisibility(View.INVISIBLE);
                        tilAmountPay.setVisibility(View.INVISIBLE);
                        btnPay.setEnabled(true);
                        btnPay.setBackgroundResource(R.drawable.bordes_redondos_rojo);
                    }
                }
            });
            btnPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    la acccion numero uno se refiere al pago total y el numero dos se refiere a un abono
                    if(TypePayment.isChecked()){
                        pays.setAmountPay(Float.parseFloat(AmountPay.getText().toString()));
                        new BusinessPayments().RegisterPayment(pays,2,fragmentPagos.this.getContext());
//                        notify.createMailPay(waterBillsModel.getEmail());
//                        notify.checkSMSStatePermissionPayments(getContext(),getActivity(),waterBillsModel.getPhone());

                    }else{
                        pays.setAmountPay(Float.parseFloat("0"));
                        new BusinessPayments().RegisterPayment(pays,1,fragmentPagos.this.getContext());
//                        notify.createMailPay(waterBillsModel.getEmail());
//                        notify.checkSMSStatePermissionPayments(getContext(),getActivity(),waterBillsModel.getPhone());

                    }
                    new Messages().messageToast(getContext(),pays.getValidationMessage());
                    findNavController(v).navigate(R.id.info_Pagos);
                }
            });


        }


    }

    // Relacionar las variables con los componentes
    public void getValues(View view)
    {
        fragmentPagosArgs args=fragmentPagosArgs.fromBundle(getArguments());
        pays=args.getPays();
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
        Owner.setText(pays.getOwner());
        Total.setText("$"+pays.getTotal()+"0");
        DebitPeriod.setText(DebitPeriod.getText()+" "+pays.getDebitPeriod());
        Status.setText(Status.getText()+" "+pays.getStatus());

    }






}