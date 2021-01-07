package Presentation.Reports;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.jras.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import BusinessLogic.BusinessReports;
import Data.Models.ReportsModel;
import Data.Utility.Messages;
import Presentation.Payments.FragmentPrePago;

import static androidx.navigation.Navigation.findNavController;


public class fragmentReportes extends Fragment {

    //Variables
    private EditText firstDate;
    private EditText secondDate;
    private Button btngenerateReport;
    private String datemin;
    private String datemax;
    FragmentManager fragmentManager;

    private FloatingActionButton fabFecha;


    DatePickerDialog.OnDateSetListener setListener;
    ReportsModel reports=new ReportsModel();


    public fragmentReportes() {
        // Required empty public constructor
    }

    public void getValues(View view) {
        //Relacionar variables con los componentes
        firstDate = view.findViewById(R.id.txtFecha1);
        secondDate = view.findViewById(R.id.txtFecha2);
        fabFecha = view.findViewById(R.id.fabFechaReportes);
        btngenerateReport=view.findViewById(R.id.btnConsultar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_reportes, container, false);
        getValues(view);
        //crear instancia del calendario
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        fabFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext() ,android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        btngenerateReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reports.setDateMin(datemin);
                reports.setDateMax(datemax);
                if(new BusinessReports().generateRepor(fragmentReportes.this.getContext(),reports)){
                    new Messages().messageToast(fragmentReportes.this.getContext(),reports.getValidationMessage());
                    Bundle bundle=new Bundle();
                    bundle.putString("datemax",reports.getDateMax());
                    FragmentPrePago fragment=new FragmentPrePago();
                    fragment.setArguments(bundle);










                }else{
                    new Messages().messageToast(fragmentReportes.this.getContext(),reports.getValidationMessage());
                }
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                firstDate.setText(dayOfMonth+"/"+month+"/"+year);
                datemin = year+"/"+month+"/"+dayOfMonth;
                if (month==12){
                    month=1;
                    year+=1;
                    secondDate.setText(dayOfMonth+"/"+month+"/"+year);
                    datemax = year+"/"+month+"/"+dayOfMonth;
                }else{
                    secondDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    datemax = year+"/"+(month+1)+"/"+dayOfMonth;
                }


            }
        };


        return view;
    }


}