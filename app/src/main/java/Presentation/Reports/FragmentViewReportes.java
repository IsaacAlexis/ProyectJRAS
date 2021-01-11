package Presentation.Reports;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jras.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

import Data.Models.ReportsModel;
import Data.Utility.Messages;


public class FragmentViewReportes extends Fragment {
    private PDFView pdfView;
    ReportsModel reports =new ReportsModel();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_reportes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pdfView=view.findViewById(R.id.pdfViewReportes);
        if(getArguments()!=null){
            FragmentViewReportesArgs args=FragmentViewReportesArgs.fromBundle(getArguments());
            reports =args.getReports();
            File file=new File(getContext().getExternalFilesDir("/"), reports.getDateMin()+".pdf");
            if(file.exists()){
                pdfView.fromFile(file).load();
            }else{
                new Messages().messageToast(getContext(),"No se pudo abrir el archivo");
            }

        }

    }
}