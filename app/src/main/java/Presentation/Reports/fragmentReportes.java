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
import android.widget.TextView;

import com.example.jras.R;
import java.util.Calendar;


public class fragmentReportes extends Fragment {
    boolean bandera;
    //Variables
    EditText Fecha;
    EditText SegundaFecha;
    TextView MostrarFecha;
    TextView MostrarSegundaFecha;

    DatePickerDialog.OnDateSetListener setListener;


    public fragmentReportes() {
        // Required empty public constructor
    }

    public void getValues(View view) {

        //Relacionar variables con los componentes
        Fecha = view.findViewById(R.id.txtFecha);
        MostrarFecha = view.findViewById(R.id.txtCapturaFecha);
        SegundaFecha = view.findViewById(R.id.txtFecha2);
        MostrarSegundaFecha = view.findViewById(R.id.txtCapturaFecha2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reportes, container, false);
        getValues(view);
        //crear instancia del calendario
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        Calendar calendar2 = Calendar.getInstance();
        final int year2 = calendar2.get(Calendar.YEAR);
        final int month2 = calendar2.get(Calendar.MONTH);
        final int day2 = calendar2.get(Calendar.DAY_OF_MONTH);

        Fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext() ,android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                bandera = false;

            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if (!bandera){
                    month = month+1;
                    String date = day+"/"+month+"/"+year;
                    Fecha.setText(date);
                }
                else {
                    month = month+1;
                    String date = day+"/"+month+"/"+year;
                    SegundaFecha.setText(date);
                }
            }
        };

       /* MostrarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        MostrarFecha.setText(date);
                    }
                },year,month,day);

            }
        });*/

        //Segundo calendario
       SegundaFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext() ,android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
                bandera = true;

            }
        });



        /*MostrarSegundaFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        //month = month+1;
                        String date = day+"/"+month+"/"+year;
                        MostrarSegundaFecha.setText(date);
                    }
                },year,month,day);

            }
        });*/
        return view;



    }


}