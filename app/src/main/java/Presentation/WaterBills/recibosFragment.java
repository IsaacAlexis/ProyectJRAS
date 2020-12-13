package Presentation.WaterBills;

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
import java.util.Date;
import java.util.List;

import BusinessLogic.BusinessBills;
import BusinessLogic.BusinessHouse;
import Data.Models.HousesModel;
import Data.Models.WaterBillsModel;
import Presentation.Houses.AdapterHouses;

import static androidx.navigation.Navigation.findNavController;


public class recibosFragment extends Fragment {



    private List<WaterBillsModel> bills=new ArrayList<>();
    private RecyclerView mRecycleView;
    private RecyclerView.LayoutManager mLayoutManger;
    private SearchView searchView;
    private WaterBillsAdapter waterBillsAdapter;
    private WaterBillsModel mBills =new WaterBillsModel() ;
    public recibosFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_recibos, container, false);
        bills= BusinessBills.getallConsumptions();
        mRecycleView=view.findViewById(R.id.RecycleViewRecibos);
        mLayoutManger= new LinearLayoutManager(getContext());
        searchView=view.findViewById(R.id.svSearchRecibos);
        waterBillsAdapter=new WaterBillsAdapter(bills, getContext(), new WaterBillsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(String barCode, String owner, String street, String colony,
                                    Integer houseNumber, Date readDate, Float readNow, Float nowRate) {

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                waterBillsAdapter.filterBills(newText);
                return false;
            }
        });
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.setLayoutManager(mLayoutManger);
        mRecycleView.setAdapter(waterBillsAdapter);


        return view;
    }
}