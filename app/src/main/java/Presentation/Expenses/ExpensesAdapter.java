package Presentation.Expenses;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jras.R;

import java.util.List;

import Data.Models.ExpensesModel;
import Data.Models.HousesModel;
import Presentation.Houses.AdapterHouses;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ViewHolder> {
    private List<ExpensesModel> gData;
    private List<ExpensesModel> gOriginal;
    private OnItemClickListener onClickListener;
    private Context context;
    private LayoutInflater gInflater;
    private interface OnItemClickListener{
        void OnItemClick(Long IdExpenses,String NameExpenses,String Description,float Total);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconView;
        TextView nameExpenses, description,status;
        public ViewHolder(View itemView) {
            super(itemView);
            iconView=itemView.findViewById(R.id.iconimageV);
            nameExpenses =itemView.findViewById(R.id.lbNombrePropietarioV);
            description =itemView.findViewById(R.id.lbDireccionesV);
            status=itemView.findViewById(R.id.lbStatusV);
        }
        void bind(final HousesModel house, final AdapterHouses.OnItemClickListener listener){
            iconView.setColorFilter(Color.BLUE);
            nameExpenses.setText(house.getOwner());
            description.setText(house.getStreet()+" #"+house.getHouseNumber());
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

    @NonNull
    @Override
    public ExpensesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return gOriginal.size();
    }
}
