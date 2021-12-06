package com.example.fistproject.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fistproject.Click_item_page;
import com.example.fistproject.Click_item_page_order;
import com.example.fistproject.R;
import com.example.fistproject.classRecive.ReciveData;

import java.util.List;

public class CustomAdapter extends  RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    private Context mContext;
    private List<ReciveData>dataList;
    private Activity activity;

    private boolean click_item_page;
    private boolean click_item_page_order;



    public CustomAdapter(Context mContext,Activity activity ,List<ReciveData> dataList,boolean click_item,boolean click_item_page_order) {
        this.mContext = mContext;
        this.activity  = activity;
        this.dataList = dataList;
        this.click_item_page = click_item;
        this.click_item_page_order = click_item_page_order;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View viewItem  = LayoutInflater.from(mContext).inflate(R.layout.custom_item,parent,false);

       return new MyViewHolder(viewItem);



    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(dataList.get(position).getNamedata());
        holder.discription.setText(dataList.get(position).getDiscription());

        Glide.with(mContext).load(dataList.get(position).getImageLink()).into(holder.imageView);

        if(click_item_page ==true&&click_item_page_order==false){
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String  name = dataList.get(position).getNamedata();
                    String  disc = dataList.get(position).getDiscription();
                    String  imageLink  = dataList.get(position).getImageLink();
                    String  Id_item  =dataList.get(position).getId_item();

                    Intent intent  = new Intent(view.getContext(), Click_item_page.class);
                    intent.putExtra("name",name);
                    intent.putExtra("disc",disc);
                    intent.putExtra("link",imageLink);
                    intent.putExtra("id",Id_item);
                    activity.startActivity(intent);

                }
            });
        }
        else if (click_item_page==false&&click_item_page_order==true){
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String  name = dataList.get(position).getNamedata();
                    String  disc = dataList.get(position).getDiscription();
                    String  imageLink  = dataList.get(position).getImageLink();
                    String  Id_item  =dataList.get(position).getId_item();

                    Intent intent  = new Intent(view.getContext(), Click_item_page_order.class);
                    intent.putExtra("name",name);
                    intent.putExtra("disc",disc);
                    intent.putExtra("link",imageLink);
                    intent.putExtra("id",Id_item);
                    activity.startActivity(intent);
                    
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView  name,discription;
        ConstraintLayout layout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_id_lay);
            imageView =  itemView.findViewById(R.id.imageView25);
            name  = itemView.findViewById(R.id.nametext);
            discription = itemView.findViewById(R.id.disc_text);

        }
    }
}
