package com.project.chaletbooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.project.chaletbooking.R;
import com.project.chaletbooking.model.Service_Chalet;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    Context context;
    ArrayList<Service_Chalet> arrayList;

    public ServiceAdapter(Context context, ArrayList<Service_Chalet> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_service, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.service_chalet = arrayList.get(position);

        holder.tv_name.setText(holder.service_chalet.getName_service());
        holder.imageButton.setImageResource(holder.service_chalet.getImage_service());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageButton imageButton;
        TextView tv_name;
        Service_Chalet service_chalet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.recycler_home_image);
            tv_name = itemView.findViewById(R.id.recycler_home_name);
        }
    }
}

//public static void setDrawableFilterColor(Context context, int colorResource, Drawable drawable) {
//    //noinspection ResourceType
//    int filterColor = Color.parseColor(context.getResources().getString(colorResource));
//    drawable.setColorFilter(new PorterDuffColorFilter(filterColor, PorterDuff.Mode.MULTIPLY));
//}
