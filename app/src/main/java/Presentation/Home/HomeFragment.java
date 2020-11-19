package Presentation.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.jras.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static androidx.navigation.Navigation.findNavController;

public class HomeFragment extends Fragment {
    public FloatingActionButton fabConsumo;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        fabConsumo=view.findViewById(R.id.fabConsumo);
        fabConsumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findNavController(view).navigate(R.id.fagmentConsumo);

            }
        });

        return view;
    }
}