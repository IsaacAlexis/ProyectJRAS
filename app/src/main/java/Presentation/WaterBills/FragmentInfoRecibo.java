package Presentation.WaterBills;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jras.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

import Data.Models.ConsumptionsModel;
import Data.Utility.Messages;


public class FragmentInfoRecibo extends Fragment {

   PDFView pdfView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view= inflater.inflate(R.layout.fragment_info_recibo, container, false);
         pdfView=view.findViewById(R.id.pdfView);
        File file=new File(getContext().getExternalFilesDir("/"),"/"+new ConsumptionsModel().getPdf());
        if(file.exists()){
            pdfView.fromFile(file).load();
        }else{
            new Messages().messageToast(getContext(),"No se encontro el archivo");
        }


        return view;
    }
}