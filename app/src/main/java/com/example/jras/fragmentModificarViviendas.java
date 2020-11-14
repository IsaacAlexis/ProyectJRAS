package com.example.jras;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class fragmentModificarViviendas extends Fragment {

    private FragmentModificarViviendasViewModel mViewModel;

    public static fragmentModificarViviendas newInstance() {
        return new fragmentModificarViviendas();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         final View view= inflater.inflate(R.layout.fragment_modificar_viviendas, container, false);
        EditText example=view.findViewById(R.id.txtPropietario);
        example.setText("Hola");
         return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FragmentModificarViviendasViewModel.class);
        // TODO: Use the ViewModel
    }

}