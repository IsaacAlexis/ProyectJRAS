package Presentation.Expenses;

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


public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ViewHolder> {
    private List<ExpensesModel> gData;
    private List<ExpensesModel> gOriginal;
    private OnItemClickListener onClickListener;
    private Context context;
    private LayoutInflater gInflater;
    public ExpensesAdapter(List<ExpensesModel> items,Context context,OnItemClickListener onItemClickListener){
        this.gInflater= LayoutInflater.from(context);
        this.gData=items;
        this.onClickListener=onItemClickListener;
        gOriginal=new ArrayList<>();
        gOriginal.addAll(items);
    }
    public  interface OnItemClickListener{
        void OnItemClick(Long IdExpenses,String NameExpenses,String Description,float Total);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconView;
        TextView nameExpenses, description,date,total;
        public ViewHolder(View itemView) {
            super(itemView);
            iconView=itemView.findViewById(R.id.iconimageG);
            nameExpenses =itemView.findViewById(R.id.folioCV);
            description =itemView.findViewById(R.id.descriptionCV);
            date=itemView.findViewById(R.id.fechaGastoCV);
            total=itemView.findViewById(R.id.gastoTotalCV);
        }
        void bind(final ExpensesModel expenses, final OnItemClickListener listener){
            String dateAdd = new SimpleDateFormat("dd-MM-yyyy").format(expenses.getExpDate());
            iconView.setColorFilter(Color.parseColor("#6DC36D"));
            nameExpenses.setText(expenses.getNameExp());
            description.setText(expenses.getDescript());
            date.setText(dateAdd);
            date.setTextColor(Color.parseColor("#FF006F"));
            total.setTextColor(Color.parseColor("#0088FF"));
            total.setText("$ "+String.valueOf(expenses.getTotal())+"0");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(expenses.getFolioExp(),expenses.getNameExp(),expenses.getDescript(),expenses.getTotal());
                }
            });
        }
    }
    public void filterExpenses(final String svSearch){
        if(svSearch.length()==0){
            gData.clear();
            gData.addAll(gOriginal);
        }else{
            gData.clear();
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                List<ExpensesModel> collect = gOriginal.stream()
                        .filter(i -> String.valueOf(i.getFolioExp()).equals(svSearch.toLowerCase())||
                                (i.getNameExp().toLowerCase().contains(svSearch.toLowerCase())||
                                new SimpleDateFormat("dd-MM-yyyy").format(i.getExpDate()).equals(svSearch.toLowerCase())||
                                new SimpleDateFormat("dd").format(i.getExpDate()).equals(svSearch.toLowerCase())||
                                new SimpleDateFormat("MM").format(i.getExpDate()).equals(svSearch.toLowerCase())||
                                new SimpleDateFormat("yyyy").format(i.getExpDate()).equals(svSearch.toLowerCase()))).
                                collect(Collectors.toList());

                gData.addAll(collect);
            }


        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExpensesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=gInflater.inflate(R.layout.list_items_gastos,null);
        return new ExpensesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesAdapter.ViewHolder holder, int position) {
        holder.bind(gData.get(position),onClickListener);

    }

    @Override
    public int getItemCount() {
        return gData.size();
    }


}
