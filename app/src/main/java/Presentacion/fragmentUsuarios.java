package Presentacion;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.jras.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static androidx.navigation.Navigation.findNavController;


public class fragmentUsuarios extends Fragment {

    public FloatingActionButton fabUsuario;
    public ImageView btnModificar, btnBuscar;
    public EditText txtNombre;

    public fragmentUsuarios() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_usuarios, container, false);

        fabUsuario = view.findViewById(R.id.fabAgregarUsuario);

        btnModificar=view.findViewById(R.id.btnModificarUsuario);

        txtNombre=view.findViewById(R.id.txtBuscarUsuario);

        txtNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                btnModificar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                findNavController(v).navigate(R.id.fragementModificarUsuarios);
            }
        });

        fabUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                findNavController(view).navigate(R.id.fragmentRegistroUsuarios);


            }
        });
        return view;




    }



}