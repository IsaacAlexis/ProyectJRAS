package Presentation.WaterBills;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.jras.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BusinessLogic.BusinessBills;
import BusinessLogic.BusinessHouse;
import Data.Models.ConsumptionsModel;
import Data.Models.HousesModel;
import Data.Models.WaterBillsModel;
import Data.Utility.Dates;
import Data.Utility.Messages;
import Presentation.Houses.AdapterHouses;

import static androidx.navigation.Navigation.findNavController;


public class recibosFragment extends Fragment {



    private List<WaterBillsModel> bills=new ArrayList<>();
    private RecyclerView mRecycleView;
    private RecyclerView.LayoutManager mLayoutManger;
    private SearchView searchView;
    private WaterBillsAdapter waterBillsAdapter;
    private WaterBillsModel mBills =new WaterBillsModel() ;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference reference;
    DownloadManager downloadManager;
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
            public void OnItemClick(String barCode, String owner, String street, String colony, Integer houseNumber,
                                    Date readDate, Float readNow, Float nowRate, String nameFile) {

                download("RECIBO DE AGUA PD: "+
                        new Dates().NameMonth(Integer.parseInt(new SimpleDateFormat("MM").format(readDate)))+
                        "["+new Dates().getLastBill(readDate)+"]"+""+new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(new Date()),nameFile);

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
    public void DownloadFile(Context context, String fileName, String fileExtension, String DestinationDirectory, String url) {


        downloadManager = (DownloadManager) context.
                getSystemService(context.DOWNLOAD_SERVICE);
        Uri uris = Uri.parse(url);
        DownloadManager.Request requests = new DownloadManager.Request(uris);
        requests.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        requests.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName + fileExtension);
        downloadManager.enqueue(requests);


    }
    private void download(String filename,String referenceId) {
        //DataOriginal=new ConsumptionsModel().getPdf();
        storageReference=firebaseStorage.getInstance().getReference();
        reference=storageReference.child("RECIBOS DE AGUA/"+referenceId);
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {



                DownloadFile(recibosFragment.this.getContext(),filename,".pdf","",uri.toString());

                Toast.makeText(recibosFragment.this.getContext(),"Descargando recibo de agua.....",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(recibosFragment.this.getContext(),"No se pudo descargar el archivo",Toast.LENGTH_LONG).show();

            }
        });


    }
}