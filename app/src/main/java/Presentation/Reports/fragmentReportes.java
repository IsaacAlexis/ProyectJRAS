package Presentation.Reports;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    private FloatingActionButton fabFechafinal;
    int action=0;


    DatePickerDialog.OnDateSetListener setListener;
    ReportsModel reports=new ReportsModel();


    public fragmentReportes() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reportes, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                action=1;

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        fabFechafinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext() ,android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                action=2;

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
                    fragmentReportesDirections.ActionFragmentReportesToFragmentViewReportes action=
                            fragmentReportesDirections.actionFragmentReportesToFragmentViewReportes(reports);
                    findNavController(view).navigate(action);

                }else{
                    new Messages().messageToast(fragmentReportes.this.getContext(),reports.getValidationMessage());
                }
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                switch (action){
                    case 1:
                            firstDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                            datemin = year+"/"+(month+1)+"/"+dayOfMonth;
                            fabFechafinal.setVisibility(View.VISIBLE);


                        break;
                    case 2:
                        try {
                            Date datefirst=new SimpleDateFormat("dd/MM/yyyy").parse(firstDate.getText().toString());
                            Date datelast=new SimpleDateFormat("dd/MM/yyyy").parse(""+dayOfMonth+"/"+(month+1)+"/"+year);
                            if(datefirst.before(datelast)){

                                secondDate.setText(""+dayOfMonth+"/"+(month+1)+"/"+year);
                                datemax = year+"/"+(month+1)+"/"+dayOfMonth;
                            }else{
                                secondDate.setText("");
                                new Messages().messageToast(getContext(),"Debes ingresar una fecha mayor a la anterior");
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                        break;
                    default:
                        break;
                }



            }
        };

    }
    public void getValues(View view) {
        //Relacionar variables con los componentes
        firstDate = view.findViewById(R.id.txtFecha1);
        secondDate = view.findViewById(R.id.txtFecha2);
        fabFecha = view.findViewById(R.id.fabFechaReportes);
        fabFechafinal=view.findViewById(R.id.fabFechaReportesFinal);
        btngenerateReport=view.findViewById(R.id.btnConsultar);
    }
}