package Presentation.Houses;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.jras.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import BusinessLogic.BusinessHouse;
import Data.Models.HousesModel;

import static androidx.navigation.Navigation.findNavController;

public class fragmentViviendas extends Fragment {

    public FloatingActionButton fabViviendas;
    private List<HousesModel> house=new ArrayList<>();
    private RecyclerView mRecycleView;
    private RecyclerView.LayoutManager mLayoutManger;
    private SearchView searchView;
    private AdapterHouses houseAdapter;
    private HousesModel mHouse =new HousesModel() ;



    public fragmentViviendas() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_viviendas, container, false);

        house= BusinessHouse.getallHouses(mHouse);
        mRecycleView=view.findViewById(R.id.RecycleViewViviendas);
        mLayoutManger= new LinearLayoutManager(getContext());
        searchView=view.findViewById(R.id.svSearchViviendas);
        houseAdapter=new AdapterHouses(house, getContext(), new AdapterHouses.OnItemClickListener() {
            @Override
            public void OnItemClick(String barCode, String owner, Long phoneNumber, String email,
                                    String street, int houseNumber, int zipcode, String colony, String State, String city, String status) {
               mHouse.setValuesToModify(barCode,owner,phoneNumber,email,street,houseNumber,zipcode,colony,city,State,status);
                findNavController(view).navigate(R.id.fragmentModificarViviendas);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                houseAdapter.filterHouse(newText);
                return false;
            }
        });
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.setLayoutManager(mLayoutManger);
        mRecycleView.setAdapter(houseAdapter);

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