package com.project.chaletbooking.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.project.chaletbooking.R;
import com.project.chaletbooking.listener.OnClickDateAdapter;
import com.project.chaletbooking.other.Date_to_Reservation;

import java.util.ArrayList;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.View_Holder> {

    Context context;
    ArrayList<Date_to_Reservation> arrayList;
    OnClickDateAdapter onClick;
    int card_selected = -1;

    public DateAdapter(Context context, ArrayList<Date_to_Reservation> arrayList, OnClickDateAdapter onClick) {
        this.context = context;
        this.arrayList = arrayList;
        this.onClick = onClick;
    }

    void setArrayList() {
        for (Date_to_Reservation date : arrayList) {
            date.setCheck_click(false);
        }
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_day, null, false);
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {

        holder.date_to_reservation = arrayList.get(position);

        if (arrayList.get(position).getReservation_time()==1){
            holder.tv_morning.setVisibility(View.VISIBLE);
            holder.tv_evening.setVisibility(View.INVISIBLE);
        }else if (arrayList.get(position).getReservation_time()==2){
            holder.tv_morning.setVisibility(View.INVISIBLE);
            holder.tv_evening.setVisibility(View.VISIBLE);
        }else if (arrayList.get(position).getReservation_time()==3){
            holder.tv_morning.setVisibility(View.VISIBLE);
            holder.tv_evening.setVisibility(View.VISIBLE);
        }


        holder.relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setArrayList();
                arrayList.get(position).setCheck_click(true);
                card_selected = position;
                onClick.onClickDateAdapter(holder.date_to_reservation);
                notifyDataSetChanged();
            }
        });

        if (card_selected == position) {
            holder.relative_layout.setBackgroundColor(Color.parseColor("#CC03A9F4"));
            holder.tv_month.setTextColor(Color.parseColor("#ffffff"));
            holder.tv_day.setTextColor(Color.parseColor("#ffffff"));
            holder.tv_number_day.setTextColor(Color.parseColor("#ffffff"));

        } else {
            holder.relative_layout.setBackgroundColor(Color.parseColor("#26BDBDBD"));
            holder.tv_month.setTextColor(Color.parseColor("#000000"));
            holder.tv_day.setTextColor(Color.parseColor("#000000"));
            holder.tv_number_day.setTextColor(Color.parseColor("#000000"));
        }

//        هادي لما يجي يعدل يلاقي الوقت الي كان مختاروا
        if (arrayList.get(position).isCheck_click()) {
//            Log.d("TAGsx", "onBindViewHolder:check "+"true");
            holder.relative_layout.setBackgroundColor(Color.parseColor("#cccccc"));
            holder.tv_month.setTextColor(Color.parseColor("#ffffff"));
            holder.tv_day.setTextColor(Color.parseColor("#ffffff"));
            holder.tv_number_day.setTextColor(Color.parseColor("#ffffff"));
        } else {
            Log.d("TAGsx", "onBindViewHolder:check " + "false");
            holder.relative_layout.setBackgroundColor(Color.parseColor("#26BDBDBD"));
            holder.tv_month.setTextColor(Color.parseColor("#000000"));
            holder.tv_day.setTextColor(Color.parseColor("#000000"));
            holder.tv_number_day.setTextColor(Color.parseColor("#000000"));
        }

        holder.tv_month.setText(arrayList.get(position).getMonth());
        holder.tv_day.setText(arrayList.get(position).getDay());
        holder.tv_number_day.setText(arrayList.get(position).getNumber_day());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class View_Holder extends RecyclerView.ViewHolder {
        RelativeLayout relative_layout;
        TextView tv_month, tv_day, tv_number_day, tv_morning, tv_evening;
        Date_to_Reservation date_to_reservation;

        public View_Holder(@NonNull View itemView) {
            super(itemView);
            relative_layout = itemView.findViewById(R.id.relativelayout);
            tv_month = itemView.findViewById(R.id.customDay_month);
            tv_day = itemView.findViewById(R.id.customDay_day);
            tv_number_day = itemView.findViewById(R.id.customDay_number_day);
            tv_morning = itemView.findViewById(R.id.customDay_morning);
            tv_evening = itemView.findViewById(R.id.customDay_evening);

        }
    }
}
