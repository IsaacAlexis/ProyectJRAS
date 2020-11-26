package Presentation.Users;

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

import BusinessLogic.BusinessUserRegister;
import Data.Models.UsersModel;

import static androidx.navigation.Navigation.findNavController;


public class fragmentUsuarios extends Fragment  {

    public FloatingActionButton fabUsuario;
    private List<UsersModel> users=new ArrayList<>();
    private RecyclerView mRecycleView;
    private RecyclerView.LayoutManager mLayoutManger;
    private SearchView searchView;
    private UsersAdapter usersAdapter;
    private UsersModel mUsers =new UsersModel() ;



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
        searchView=view.findViewById(R.id.svSearch);
        usersAdapter=new UsersAdapter(users, getContext(), new UsersAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Long idUser, String firstName, String lastName, String role, String email, String username, String status, int position) {
                setvalues(idUser,firstName,lastName,role,email,username,status);
                findNavController(view).navigate(R.id.fragementModificarUsuarios);
            }
        });

       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                usersAdapter.filterUser(s);


                return false;
            }
        });
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.setLayoutManager(mLayoutManger);
        mRecycleView.setAdapter(usersAdapter);
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

    private void setvalues(Long idUser, String firstName, String lastName, String role, String email, String username, String status) {
        mUsers.setModifyIdUser(idUser);
        mUsers.setModifyFirstName(firstName);
        mUsers.setModifyLastName(lastName);
        mUsers.setModifyRole(role);
        mUsers.setModifyEmail(email);
        mUsers.setModifyUsername(username);
        mUsers.setModifyStatus(status);
    }


}