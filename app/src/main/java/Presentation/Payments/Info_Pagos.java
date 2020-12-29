package Presentation.Payments;

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

import BusinessLogic.BusinessPayments;
import Data.Models.PaymentsModel;

import static androidx.navigation.Navigation.findNavController;

public class Info_Pagos extends Fragment {

    private FloatingActionButton fabPago;
    private List<PaymentsModel> payments=new ArrayList<>();
    private RecyclerView mRecycleView;
    private RecyclerView.LayoutManager mLayoutManger;
    private SearchView searchView;
    private PaymentsAdapter paymentsAdapter;

    public Info_Pagos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_info_pagos, container, false);
        payments= BusinessPayments.getAllPayments();
        mRecycleView=view.findViewById(R.id.RecycleViewPagos);
        mLayoutManger= new LinearLayoutManager(getContext());
        searchView=view.findViewById(R.id.svSearchPagos);
        fabPago=view.findViewById(R.id.fabRegistrarPago);
        paymentsAdapter=new PaymentsAdapter(payments, getContext(), new PaymentsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick( String Owner, Date PayDate, float Total) {

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String Search) {
                paymentsAdapter.filterPayments(Search);
                return false;
            }
        });
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.setLayoutManager(mLayoutManger);
        mRecycleView.setAdapter(paymentsAdapter);


        fabPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController(view).navigate(R.id.fragmentPrePago);
            }
        });
        return view;
    }
}