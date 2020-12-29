package Presentation.Payments;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jras.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Data.Models.PaymentsModel;
import Data.Utility.Dates;

public class DebitsAdapter extends RecyclerView.Adapter<DebitsAdapter.ViewHolder> {
    private List<PaymentsModel> gData;
    private List<PaymentsModel> gOriginal;
    private LayoutInflater gInflater;
    public DebitsAdapter(List<PaymentsModel> items, Context context){
        this.gInflater= LayoutInflater.from(context);
        this.gData=items;
        gOriginal=new ArrayList<>();
        gOriginal.addAll(items);
    }
    public  interface OnItemClickListener{
        void OnItemClick();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView DebitPeriod,total;
        public ViewHolder(View itemView) {
            super(itemView);

            DebitPeriod =itemView.findViewById(R.id.PeriodosCard);
            total=itemView.findViewById(R.id.ImporteCard);
        }
        void bind(final PaymentsModel payment){

            DebitPeriod.setText(new Dates().NameMonth(Integer.parseInt(new SimpleDateFormat("MM").format(payment.getReadDate())))+
                    "["+new Dates().getLastBill(payment.getReadDate())+"]");
            total.setText("$"+payment.getRate()+"0");

        }
    }


    @NonNull
    @Override
    public DebitsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=gInflater.inflate(R.layout.items_adeudo_pagos,null);
        return new DebitsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DebitsAdapter.ViewHolder holder, int position) {
        holder.bind(gData.get(position));

    }

    @Override
    public int getItemCount() {
        return gData.size();
    }
}
