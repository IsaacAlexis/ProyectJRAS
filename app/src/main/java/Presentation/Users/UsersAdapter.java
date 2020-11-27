package Presentation.Users;

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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Data.Models.UsersModel;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    private List<UsersModel> mData;
    private List<UsersModel> mOriginal;
    private OnItemClickListener onClickListener;
    private Context context;
    private LayoutInflater mInflater;

    public UsersAdapter(List<UsersModel> Items, Context context, OnItemClickListener onClickListener){
        this.mInflater=LayoutInflater.from(context);
        this.mData=Items;
        this.onClickListener=onClickListener;
        this.context=context;
        this.mOriginal=new ArrayList<>();
        this.mOriginal.addAll(Items);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView name,role,status;
        ViewHolder(View itemview){
            super(itemview);
            mImageView=itemview.findViewById(R.id.iconimage);
            name=itemview.findViewById(R.id.Name);
            role=itemview.findViewById(R.id.Role);
            status=itemview.findViewById(R.id.Status);
        }
        void bind(final UsersModel users, final OnItemClickListener listener){
            this.mImageView.setColorFilter(Color.parseColor("#FF006F"));
            this.name.setText(users.getLastName()+" "+users.getFirstName());
            this.role.setText(users.getRole());
            this.status.setText(users.getUserStatus());
            if(status.getText().toString().toUpperCase().equals("INACTIVO")){
                status.setTextColor(Color.rgb(255,0,0));
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(users.getIdUser(),users.getFirstName(),users.getLastName(),
                            users.getRole(),users.getEmail(),users.getUserName(),users.getUserStatus(),getAdapterPosition());

                }
            });

        }
    }
    public void filterUser(final String svSearch){
        if(svSearch.length()==0){
            mData.clear();
            mData.addAll(mOriginal);
        }else{
            mData.clear();
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                List<UsersModel> collect = mOriginal.stream()
                        .filter(i -> i.getUserName().toLowerCase().contains(svSearch.toLowerCase())||
                                i.getLastName().toLowerCase().contains(svSearch.toLowerCase())||
                                i.getFirstName().toLowerCase().contains(svSearch.toLowerCase())||
                                (i.getFirstName()+" "+i.getLastName()).toLowerCase().contains(svSearch.toLowerCase())||
                                (i.getLastName()+" "+i.getFirstName()).toLowerCase().contains(svSearch.toLowerCase()))
                        .collect(Collectors.<UsersModel>toList());
                mData.addAll(collect);
            }


        }
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.list_items,null);
        return new UsersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {
        holder.bind(mData.get(position),onClickListener);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemClickListener{
        void OnItemClick(Long idUser,String firstName,String lastName,
                         String role,String email,String username,String status,int position);
    }
}
