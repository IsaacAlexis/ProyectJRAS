package Presentation.WaterBills;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jras.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static androidx.navigation.Navigation.findNavController;


public class recibosFragment extends Fragment {


    public FloatingActionButton fabrecibo;
    public recibosFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_recibos, container, false);

        fabrecibo=view.findViewById(R.id.fabInfoRecibo);
        fabrecibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController(view).navigate(R.id.fragmentInfoRecibo);
            }
        });
        return view;
    }
}