package Presentation.Users;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.jras.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import BusinessLogic.BusinessUserRegister;
import Data.Models.UsersModel;

import static androidx.navigation.Navigation.findNavController;


public class fragmentUsuarios extends Fragment {

    public FloatingActionButton fabUsuario;
    private List<UsersModel> users=new ArrayList<>();
    private RecyclerView mRecycleView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManger;
    private UsersModel mUsers;



    public fragmentUsuarios() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_usuarios, container, false);
        //Mostrar usuarios
        users= BusinessUserRegister.getallusers(mUsers);
        mRecycleView=(RecyclerView)view.findViewById(R.id.RecycleView);
        mLayoutManger=new LinearLayoutManager(getContext());
        adapter=new UsersAdapter(users,getContext(), new UsersAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(String firstName, String lastName, String role, String email, String username, String status, int position) {
                Toast.makeText(getContext(),firstName+" "+lastName,Toast.LENGTH_LONG).show();

            }
        });
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.setLayoutManager(mLayoutManger);
        mRecycleView.setAdapter(adapter);
        //Registrar Usuarios
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