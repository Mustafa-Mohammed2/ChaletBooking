package com.project.chaletbooking.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.project.chaletbooking.R;
import com.project.chaletbooking.adapter.DateAdapter;
import com.project.chaletbooking.firebace.ChaletFirebase;
import com.project.chaletbooking.listener.OnClickDateAdapter;
import com.project.chaletbooking.model.Chalet;
import com.project.chaletbooking.other.Date_to_Reservation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Semaphore;


public class Res_Booking_Fragment extends Fragment {

  private static final String ARG_CHALET = "chalet_";

    // TODO: Rename and change types of parameters

    Chalet chalet;


    DateAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recycler_date;
    RadioButton rb_time_morning, rb_time_evening, rb_time_all;
    TextView   tv_price_morning, tv_price_evening, tv_price_all;


    Button time_btn_go;

    Date_to_Reservation reservation_date;


    public Res_Booking_Fragment() {
        // Required empty public constructor
    }


    public static Res_Booking_Fragment newInstance( Chalet chalet) {
        Res_Booking_Fragment fragment = new Res_Booking_Fragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CHALET, chalet);
         fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
             chalet = (Chalet) getArguments().getSerializable(ARG_CHALET);
             Log.d("tagzxxxx","chalet"+chalet.getId_firebase());
         }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
 
        View view = inflater.inflate(R.layout.fragment_res__booking_, container, false);

        recycler_date = view.findViewById(R.id.frag_res_book_recycler_date);
        rb_time_morning = view.findViewById(R.id.frag_res_book_rb_morning);
        rb_time_evening = view.findViewById(R.id.frag_res_book_rb_evening);
        rb_time_all = view.findViewById(R.id.frag_res_book_rb_all);
        tv_price_morning = view.findViewById(R.id.frag_res_book_tv_morning_price);
        tv_price_evening = view.findViewById(R.id.frag_res_book_tv_evening_price);
        tv_price_all = view.findViewById(R.id.frag_res_book_tv_24hour);
        time_btn_go = view.findViewById(R.id.frag_res_book_btn_go);


//        get array list special to Reservation Chalet from fireBase
        ArrayList<Date_to_Reservation> arrayList = new ArrayList<>();
        arrayList.add(new Date_to_Reservation("Tue", "Jun", "01", false, 3));
        arrayList.add(new Date_to_Reservation("Fri", "Jun", "04", false, 2));
        arrayList.add(new Date_to_Reservation("Mon", "Jun", "07", false, 2));
        arrayList.add(new Date_to_Reservation("Sat", "Jul", "31", false, 1));


        adapter = new DateAdapter(getActivity(), getAllDateOfMonth(arrayList), new OnClickDateAdapter() {
            @Override
            public void onClickDateAdapter(Date_to_Reservation date_toReservation) {
                reservation_date = date_toReservation;
            }
        });
        layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recycler_date.setLayoutManager(layoutManager);
        recycler_date.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recycler_date.setHasFixedSize(true);


        tv_price_morning.setText(chalet.getPrice()+"");
        tv_price_evening.setText(chalet.getPrice()+"");
        tv_price_all.setText(chalet.getPrice()+"");

        if (rb_time_morning.isSelected()){
//            price=rb_time_all
        }else if (rb_time_evening.isSelected()){

        }else if (rb_time_all.isSelected()){

        }

        time_btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Date_to_Reservation date_to_reservation=new Date_to_Reservation();

            }
        });
        return view;
    }
    ArrayList<Date_to_Reservation> getAllDateOfMonth(ArrayList<Date_to_Reservation> arrayList) {

        ArrayList<Date_to_Reservation> dailyArrayList = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int myMonth = cal.get(Calendar.MONTH);
//        int id_position = -1;
//        String[] array_date;
        String[] array_time;
        while (myMonth == cal.get(Calendar.MONTH)) {
            String s = String.valueOf(cal.getTime());
            array_time = s.split(" ");
            Log.d("TAGs", "size: " + array_time[0] + array_time[1] + array_time[2]);

            boolean found_date = false;
            if (arrayList != null) {
                for (Date_to_Reservation arrayList1 : arrayList) {
                    if (arrayList1.getDay().equals(array_time[0]) && arrayList1.getMonth().equals(array_time[1])
                            && arrayList1.getNumber_day().equals(array_time[2])) {

                        if (arrayList1.getReservation_time() == 1) {
                            dailyArrayList.add(new Date_to_Reservation(array_time[0], array_time[1], array_time[2],
                                    false, 1));
                            cal.add(Calendar.DAY_OF_MONTH, 1);
                            Log.d("TAGss", "morning =1 ");

                        } else if (arrayList1.getReservation_time() == 2) {
                            dailyArrayList.add(new Date_to_Reservation(array_time[0], array_time[1], array_time[2],
                                    false, 2));
                            cal.add(Calendar.DAY_OF_MONTH, 1);
                            Log.d("TAGss", "evening =2 ");

                        } else if (arrayList1.getReservation_time() == 3) {
                            dailyArrayList.add(new Date_to_Reservation(array_time[0], array_time[1], array_time[2],
                                    false, 3));
                            cal.add(Calendar.DAY_OF_MONTH, 1);
                            Log.d("TAGss", "all =3 ");
                        }
                        Log.d("TAGs", "****************True*****************");
                        found_date = true;
                        break;
                    }
                }
            }

            if (found_date == false) {
                dailyArrayList.add(new Date_to_Reservation(array_time[0], array_time[1], array_time[2],
                        false, 0));
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
        array_time = null;
// to get next month
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.MONTH, 1);
        cal2.set(Calendar.DAY_OF_MONTH, 1);
        int myMonth2 = cal2.get(Calendar.MONTH);
        Log.d("TAGs", cal2.get(Calendar.MONTH) + ":**************** month:" + myMonth2);
        while (myMonth2 == cal2.get(Calendar.MONTH)) {
            String s = String.valueOf(cal2.getTime());
            array_time = s.split(" ");
            Log.d("TAGs", "size: " + array_time[0] + array_time[1] + array_time[2]);

            boolean found_date = false;
            for (Date_to_Reservation arrayList1 : arrayList) {
                if (arrayList1.getDay().equals(array_time[0]) && arrayList1.getMonth().equals(array_time[1])
                        && arrayList1.getNumber_day().equals(array_time[2])) {

                    if (arrayList1.getReservation_time() == 1) {
                        dailyArrayList.add(new Date_to_Reservation(array_time[0], array_time[1], array_time[2],
                                false, 1));
                        cal2.add(Calendar.DAY_OF_MONTH, 1);
                        Log.d("TAGss", "morning =1 ");

                    } else if (arrayList1.getReservation_time() == 2) {
                        dailyArrayList.add(new Date_to_Reservation(array_time[0], array_time[1], array_time[2],
                                false, 2));
                        cal2.add(Calendar.DAY_OF_MONTH, 1);
                        Log.d("TAGss", "evening =2 ");

                    } else if (arrayList1.getReservation_time() == 3) {
                        dailyArrayList.add(new Date_to_Reservation(array_time[0], array_time[1], array_time[2],
                                false, 3));
                        cal2.add(Calendar.DAY_OF_MONTH, 1);
                        Log.d("TAGss", "all =3 ");
                    }
                    Log.d("TAGs", "****************True*****************");
                    found_date = true;
                    break;
                }
            }

            if (found_date == false) {
                dailyArrayList.add(new Date_to_Reservation(array_time[0], array_time[1], array_time[2], false, 0));
                cal2.add(Calendar.DAY_OF_MONTH, 1);
            }

        }

//        if (!rev_date.isEmpty()) {
//            array_date = rev_date.split(",");
//            for (int i = 0; i < dailyArrayList.size(); i++) {
//                if ((array_date[0] + array_date[1] + array_date[2]).
//                        equals(dailyArrayList.get(i).getMonth() + dailyArrayList.get(i).getNumber_day() + dailyArrayList.get(i).getDay())) {
//                    id_position = i;
//                    break;
//                }
//            }
//            dailyArrayList.get(id_position).setCheck_click(true);
//        }
        return dailyArrayList;
    }

}