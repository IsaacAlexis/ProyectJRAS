package Presentation.WaterBills;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jras.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.text.SimpleDateFormat;

import Data.Models.ConsumptionsModel;
import Data.Utility.Dates;
import Data.Utility.Messages;


public class FragmentInfoRecibo extends Fragment {

   PDFView pdfView;
   Button btnDownload;
   FirebaseStorage firebaseStorage;
   StorageReference storageReference;
   StorageReference reference;
   DownloadManager downloadManager;
   String DataOriginal;
   Long enque;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_info_recibo, container, false);
//        pdfView=view.findViewById(R.id.pdfView);
//        btnDownload=(Button)view.findViewById(R.id.btnDescargar);
//        DataOriginal="["+new Dates().getLastBill(new ConsumptionsModel().getPdfReadDate())+"]"+new ConsumptionsModel().getPdf();
//        String filename="RECIBO DE AGUA PD: "+
//                new Dates().NameMonth(Integer.parseInt(new SimpleDateFormat("MM").format(new ConsumptionsModel().getPdfReadDate())))+
//                "["+new Dates().getLastBill(new ConsumptionsModel().getPdfReadDate())+"]";
//        btnDownload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                storageReference=firebaseStorage.getInstance().getReference();
//                reference=storageReference.child("RECIBOS DE AGUA/"+DataOriginal);
//                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//
//
//
//                        DownloadFile(FragmentInfoRecibo.this.getContext(),filename,".pdf","",uri.toString(),2);
//
//                        Toast.makeText(FragmentInfoRecibo.this.getContext(),"Descargando Recibo de agua....",Toast.LENGTH_LONG).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(FragmentInfoRecibo.this.getContext(),"Ocurrio un error con el servidor",Toast.LENGTH_LONG).show();
//
//                    }
//                });
//
//            }
//        });



            download(DataOriginal);

            BroadcastReceiver receiver=new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action=intent.getAction();
                    if(DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)){
                        DownloadManager.Query req_query=new DownloadManager.Query();
                        req_query.setFilterById(enque);

                        Cursor cursor=downloadManager.query(req_query);
                        if(cursor.moveToFirst()){
                            int columnIndex=cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                            if(DownloadManager.STATUS_SUCCESSFUL==cursor.getInt(columnIndex)){
                                String uriString=cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                                File file=new File(FragmentInfoRecibo.this.getContext().getExternalFilesDir("/"),DataOriginal+".pdf");
                                if(file.exists()){
                                    pdfView.fromFile(file).load();

                                }else{
                                    new Messages().messageToast(getContext(),"No se encontro el archivo");
                                }



                            }
                        }

                    }
                }
            };
            FragmentInfoRecibo.this.getContext().registerReceiver(receiver,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));





        return view;
    }

    private void download(String filename) {
        //DataOriginal=new ConsumptionsModel().getPdf();
        storageReference=firebaseStorage.getInstance().getReference();
        reference=storageReference.child("RECIBOS DE AGUA/"+new ConsumptionsModel().getPdf());
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {



                DownloadFile(FragmentInfoRecibo.this.getContext(),filename,".pdf","",uri.toString(),1);

                Toast.makeText(FragmentInfoRecibo.this.getContext(),"Descargando informacion...",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(FragmentInfoRecibo.this.getContext(),"No se pudo descargar el archivo",Toast.LENGTH_LONG).show();

            }
        });


    }

    public void DownloadFile(Context context,String fileName,String fileExtension,String DestinationDirectory,String url,int action){
        switch (action){
            case 1:
                downloadManager=(DownloadManager) context.
                        getSystemService(context.DOWNLOAD_SERVICE);
                Uri uri =Uri.parse(url);
                DownloadManager.Request request=new DownloadManager.Request(uri);
                request.setDestinationInExternalFilesDir(context,DestinationDirectory,fileName+fileExtension);
                enque=downloadManager.enqueue(request);
                break;
            case 2:
                downloadManager=(DownloadManager) context.
                        getSystemService(context.DOWNLOAD_SERVICE);
                Uri uris =Uri.parse(url);
                DownloadManager.Request requests =new DownloadManager.Request(uris);
                requests.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                requests.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,fileName+fileExtension);
                //requests.setDestinationInExternalFilesDir(context,DestinationDirectory,fileName+fileExtension);
                downloadManager.enqueue(requests);
                break;
        }

    }
}