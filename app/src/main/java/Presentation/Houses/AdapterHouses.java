package Presentation.Houses;

import android.content.Context;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.jras.R;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import Data.Models.HousesModel;



public class AdapterHouses extends RecyclerView.Adapter<AdapterHouses.ViewHolder> {
    private List<HousesModel> hData;
    private List<HousesModel> hOriginal;
    private OnItemClickListener onClickListener;
    private Context context;
    private LayoutInflater mInflater;

    public AdapterHouses(List<HousesModel> hData, Context context, OnItemClickListener onClickListener) {
        this.mInflater=LayoutInflater.from(context);
        this.hData=hData;
        this.onClickListener=onClickListener;
        this.hOriginal =new ArrayList<>();
        this.hOriginal.addAll(this.hData);
    }
    public interface OnItemClickListener{
        void OnItemClick(String barCode,String owner,Long phoneNumber,
                         String email,String street,int houseNumber,int zipcode,
                         String colony,String State,String city, String status);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconView;
        TextView owner,address,status;
        public ViewHolder(View itemView) {
            super(itemView);
            iconView=itemView.findViewById(R.id.iconimageV);
            owner=itemView.findViewById(R.id.lbNombrePropietarioV);
            address=itemView.findViewById(R.id.lbDireccionesV);
            status=itemView.findViewById(R.id.lbStatusV);
        }
        void bind(final HousesModel house,final OnItemClickListener listener){
            iconView.setColorFilter(Color.BLUE);
            owner.setText(house.getOwner());
            address.setText(house.getStreet()+" #"+house.getHouseNumber());
            status.setText(house.getStatusHouse());
            if(status.getText().toString().toUpperCase().equals("ADEUDO PARCIAL")){
                status.setTextColor(Color.rgb(255,90,11));
            }else if (status.getText().toString().toUpperCase().equals("ADEUDO TOTAL")){
                status.setTextColor(Color.rgb(255,0,0));
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(house.getBarCode(),house.getOwner(),house.getPhoneNumber(),house.getEmail(),
                            house.getStreet(),house.getHouseNumber(),house.getZipCode(),house.getColony(),house.getState(),house.getCity(),house.getStatusHouse());
                }
            });
        }
    }
    public void filterHouse(final String svSearch){
        if(svSearch.length()==0){
            hData.clear();
            hData.addAll(hOriginal);
        }else {
            hData.clear();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<HousesModel> collect = hOriginal.stream()
                        .filter(i -> i.getBarCode().toLowerCase().contains(svSearch.toLowerCase()) ||
                                i.getOwner().toLowerCase().contains(svSearch.toLowerCase()) ||
                                i.getStreet().toLowerCase().contains(svSearch.toLowerCase())||
                                (i.getStreet()+" "+i.getHouseNumber()).toLowerCase().contains(svSearch.toLowerCase()))
                        .collect(Collectors.toList());

                hData.addAll(collect);
            }
        }
        notifyDataSetChanged();
    }



    @Override
    public AdapterHouses.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= mInflater.inflate(R.layout.list_items_viviendas,null);
        return new AdapterHouses.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( AdapterHouses.ViewHolder holder, int position) {
        holder.bind(hData.get(position),onClickListener);

    }

    @Override
    public int getItemCount() {return hData.size();}


}
