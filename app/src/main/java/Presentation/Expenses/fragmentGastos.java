package Presentation.Expenses;

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

import BusinessLogic.BusinessExpense;
import Data.Models.ExpensesModel;

import static androidx.navigation.Navigation.findNavController;

public class fragmentGastos extends Fragment {
    public FloatingActionButton fabGastos;
    private List<ExpensesModel> expenses=new ArrayList<>();
    private RecyclerView mRecycleView;
    private RecyclerView.LayoutManager mLayoutManger;
    private SearchView searchView;
    private ExpensesAdapter expensesAdapter;

    //private ExpensesModel mUsers =new ExpensesModel() ;

    public fragmentGastos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_gastos, container, false);
        expenses= BusinessExpense.getallexpenses();
        mRecycleView=view.findViewById(R.id.RecycleViewGastos);
        mLayoutManger= new LinearLayoutManager(getContext());
        searchView=view.findViewById(R.id.svSearchGastos);
        fabGastos = view.findViewById(R.id.fabAgregarGasto);
        expensesAdapter=new ExpensesAdapter(expenses, getContext(), new ExpensesAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Long IdExpenses, String NameExpenses, String Description, float Total) {
                new ExpensesModel().assignValuesModify(IdExpenses,NameExpenses,Description,Total);
                findNavController(view).navigate(R.id.fragmentModificarGastos);

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                expensesAdapter.filterExpenses(newText);
                return false;
            }
        });
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.setLayoutManager(mLayoutManger);
        mRecycleView.setAdapter(expensesAdapter);
        fabGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                findNavController(view).navigate(R.id.fragmentRegistrarGastos);


            }
        });

        return view;
    }
}