package Presentation.Reports;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.jras.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;


public class fragmentReportes extends Fragment {

    //Variables
    private EditText Fecha;
    private EditText SegundaFecha;
    private String date1;
    private String date2;
    private FloatingActionButton fabFecha;


    DatePickerDialog.OnDateSetListener setListener;


    public fragmentReportes() {
        // Required empty public constructor
    }

    public void getValues(View view) {
        //Relacionar variables con los componentes
        Fecha = view.findViewById(R.id.txtFecha1);
        SegundaFecha = view.findViewById(R.id.txtFecha2);
        fabFecha = view.findViewById(R.id.fabFechaReportes);
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

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                date1 = dayOfMonth+"/"+month+"/"+year;
                if (month==12){
                    month=1;
                    year=year+1;
                    date2 = dayOfMonth+"/"+month+"/"+year;
                }else{
                    date2 = dayOfMonth+"/"+(month+1)+"/"+year;
                }
                Fecha.setText(date1);
                SegundaFecha.setText(date2);
            }
        };


        return view;
    }


}