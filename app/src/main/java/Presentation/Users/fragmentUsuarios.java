package Presentation.Users;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.example.jras.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static androidx.navigation.Navigation.findNavController;


public class fragmentUsuarios extends Fragment {

    public FloatingActionButton fabUsuario;
    public Button irModificar;
    public Button search;
    public EditText user;
    public ScrollView table;

    public fragmentUsuarios() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_usuarios, container, false);
        fabUsuario = view.findViewById(R.id.fabAgregarUsuario);
        fabUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                findNavController(view).navigate(R.id.fragmentRegistroUsuarios);


            }
        });
        return view;


    }



}