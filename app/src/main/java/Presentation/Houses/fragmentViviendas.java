package Presentation.Houses;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jras.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static androidx.navigation.Navigation.findNavController;

public class fragmentViviendas extends Fragment {

    public FloatingActionButton fabViviendas;

    public fragmentViviendas() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_viviendas, container, false);

        fabViviendas= view.findViewById(R.id.fabAgregarVivienda);

        fabViviendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                findNavController(view).navigate(R.id.fragmentRegistrasViviendas);


            }
        });
        return view;
    }
}