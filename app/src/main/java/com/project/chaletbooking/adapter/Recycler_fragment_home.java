package com.project.chaletbooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.project.chaletbooking.R;
import com.project.chaletbooking.listener.OnClickRecyclerHome;
import com.project.chaletbooking.model.Chalet;

import java.util.ArrayList;

public class Recycler_fragment_home extends RecyclerView.Adapter<Recycler_fragment_home.ViewHolder> {

    Context context;
    ArrayList<Chalet> arrayList;
    OnClickRecyclerHome onClick;

    public Recycler_fragment_home(Context context, OnClickRecyclerHome onClick) {
        this.context = context;
        this.arrayList = new ArrayList<>();
        this.onClick=onClick;
    }

    // to remove all arrayList from the rec
    public void removeAll() {
        this.arrayList = null;
        this.arrayList = new ArrayList<>();
    }
    //te set new arrayList (from new fragment)
    public void setArrayList(ArrayList<Chalet> arrayList) {
        this.arrayList.addAll(arrayList);
        notifyDataSetChanged();// to update the sutats
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_home, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.chalet=arrayList.get(position);
        holder.image.setImageResource(holder.chalet.getImage());
        holder.tv_name.setText(holder.chalet.getName());
        holder.tv_site.setText(holder.chalet.getSite());
        holder.tv_price_time.setText(holder.chalet.getPrice()+"$/");
        holder.tv_number_rate.setText(holder.chalet.getRate()+"");
        holder.rating.setRating(holder.chalet.getRate());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView tv_name, tv_site, tv_price_time, tv_number_rate;
        RatingBar rating;
        Chalet chalet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.recycler_home_image);
            tv_name=itemView.findViewById(R.id.recycler_home_name);
            tv_site=itemView.findViewById(R.id.recycler_home_site);
            tv_price_time=itemView.findViewById(R.id.recycler_home_price_time);
            tv_number_rate=itemView.findViewById(R.id.recycler_home_rate_num);
            rating=itemView.findViewById(R.id.recycler_home_rating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.onClickRecycler_home(chalet);
                }
            });
        }
    }
}
