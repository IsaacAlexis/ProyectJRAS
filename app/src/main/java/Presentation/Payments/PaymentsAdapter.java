package Presentation.Payments;

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

import Data.Models.ExpensesModel;
import Data.Models.PaymentsModel;


public class PaymentsAdapter extends RecyclerView.Adapter<PaymentsAdapter.ViewHolder> {
    private List<PaymentsModel> gData;
    private List<PaymentsModel> gOriginal;
    private OnItemClickListener onClickListener;
    private LayoutInflater gInflater;
    public PaymentsAdapter(List<PaymentsModel> items, Context context, OnItemClickListener onItemClickListener){
        this.gInflater= LayoutInflater.from(context);
        this.gData=items;
        this.onClickListener=onItemClickListener;
        gOriginal=new ArrayList<>();
        gOriginal.addAll(items);
    }
    public  interface OnItemClickListener{
        void OnItemClick( String Owner, Date PayDate, float Total);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconView;
        TextView Owner,date,total;
        public ViewHolder(View itemView) {
            super(itemView);
            iconView=itemView.findViewById(R.id.iconimagePago);
            Owner =itemView.findViewById(R.id.OwnerPago);
            date=itemView.findViewById(R.id.fechaPago);
            total=itemView.findViewById(R.id.totalPago);
        }
        void bind(final PaymentsModel payment, final PaymentsAdapter.OnItemClickListener listener){
            String dateAdd = new SimpleDateFormat("dd-MM-yyyy").format(payment.getRpayDate());
            iconView.setColorFilter(Color.parseColor("#6DC36D"));
            Owner.setText(payment.getRowner());
            date.setText(dateAdd);
            date.setTextColor(Color.parseColor("#FF006F"));
            total.setTextColor(Color.parseColor("#0088FF"));
            total.setText("$ "+payment.getRtotal()+"0");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(payment.getRowner(), payment.getRpayDate(), payment.getRtotal());
                }
            });
        }
    }
    public void filterPayments(final String svSearch){
        if(svSearch.length()==0){
            gData.clear();
            gData.addAll(gOriginal);
        }else{
            gData.clear();
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                List<PaymentsModel> collect = gOriginal.stream()
                        .filter(i -> i.getRowner().toLowerCase().contains(svSearch.toLowerCase())||
                                        new SimpleDateFormat("dd-MM-yyyy").format(i.getRpayDate()).equals(svSearch.toLowerCase())||
                                        new SimpleDateFormat("dd").format(i.getRpayDate()).equals(svSearch.toLowerCase())||
                                        new SimpleDateFormat("MM").format(i.getRpayDate()).equals(svSearch.toLowerCase())||
                                        new SimpleDateFormat("yyyy").format(i.getRpayDate()).equals(svSearch.toLowerCase())).
                                collect(Collectors.toList());

                gData.addAll(collect);
            }


        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PaymentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=gInflater.inflate(R.layout.item_list_pagos,null);
        return new PaymentsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentsAdapter.ViewHolder holder, int position) {
        holder.bind(gData.get(position),onClickListener);

    }

    @Override
    public int getItemCount() {
        return gData.size();
    }

}
