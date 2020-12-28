package Presentation.WaterBills;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jras.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import Data.Models.WaterBillsModel;


public class WaterBillsAdapter  extends RecyclerView.Adapter<WaterBillsAdapter.ViewHolder> {
    private List<WaterBillsModel> bData;
    private List<WaterBillsModel> bOriginal;
    private OnItemClickListener onClickListener;
    private Context context;
    private LayoutInflater bInflater;
    public WaterBillsAdapter(List<WaterBillsModel> items, Context context, WaterBillsAdapter.OnItemClickListener onItemClickListener){
        this.bInflater = LayoutInflater.from(context);
        this.bData =items;
        this.onClickListener=onItemClickListener;
        bOriginal =new ArrayList<>();
        bOriginal.addAll(items);
    }
    public  interface OnItemClickListener{
        void OnItemClick(String barCode, String owner, String street, String colony,
                         Integer houseNumber, Date readDate, Float readNow, Float nowRate,String nameFile);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconView;
        TextView nameOwner, ReadDate,Total;
        public ViewHolder(View itemView) {
            super(itemView);
            iconView=itemView.findViewById(R.id.iconimageG);
            nameOwner =itemView.findViewById(R.id.propietarioRecibo);
            ReadDate =itemView.findViewById(R.id.fecheRecibo);
            Total=itemView.findViewById(R.id.consumoRecibo);
        }
        void bind(final WaterBillsModel bills, final WaterBillsAdapter.OnItemClickListener listener){
            String readDate = new SimpleDateFormat("yyyy-MM").format(bills.getbReadDate());
            iconView.setColorFilter(Color.parseColor("#6DC36D"));
            nameOwner.setText(bills.getbOwner());
            ReadDate.setText(readDate);
            Total.setText("$"+ String.valueOf(bills.getbNowRate())+"0");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(bills.getbBarCode(),bills.getbOwner(),
                            bills.getbStreet(),bills.getbColony(),bills.getbHouseNumber(),bills.getbReadDate(),
                            bills.getbReadNow(),bills.getbNowRate(),bills.getbNameFile());
                }
            });
        }
    }
    public void filterBills(final String svSearch){
        if(svSearch.length()==0){
            bData.clear();
            bData.addAll(bOriginal);
        }else{
            bData.clear();
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                List<WaterBillsModel> collect = bOriginal.stream()
                        .filter(i -> String.valueOf(i.getbBarCode()).contains(svSearch)||
                                (i.getbOwner().toLowerCase().contains(svSearch.toLowerCase()))).collect(Collectors.toList());


                bData.addAll(collect);
            }


        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WaterBillsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= bInflater.inflate(R.layout.item_list_recibos,null);
        return new WaterBillsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaterBillsAdapter.ViewHolder holder, int position) {
        holder.bind(bData.get(position),onClickListener);

    }

    @Override
    public int getItemCount() {
        return bData.size();
    }
}
