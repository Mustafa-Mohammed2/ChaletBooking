package com.project.chaletbooking.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.chaletbooking.R;
import com.project.chaletbooking.activity.Restaurant_Chalet;
import com.project.chaletbooking.adapter.Recycler_fragment_home;
import com.project.chaletbooking.listener.OnClickRecyclerHome;
import com.project.chaletbooking.listener.OnSearchClicked;
import com.project.chaletbooking.model.Chalet;

import java.util.ArrayList;


public class HomeFragment_Inner extends Fragment implements OnSearchClicked {

    private static final String ARG_TAB_NUMBER = "tab_number";

    public static final String INTENT_CHALET_ID = "chalet_id";
    RecyclerView recycler;
    
    Recycler_fragment_home adapter;
    private int id_tab;


    public HomeFragment_Inner() {
    }

    public static HomeFragment_Inner newInstance(int tab_number) {
        HomeFragment_Inner fragment = new HomeFragment_Inner();
        Bundle args = new Bundle();
        args.putInt(ARG_TAB_NUMBER, tab_number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id_tab = getArguments().getInt(ARG_TAB_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_home__inner, container, false);
        recycler = view.findViewById(R.id.home_inner_recycler);
        adapter = new Recycler_fragment_home(getActivity(), new OnClickRecyclerHome() {
            @Override
            public void onClickRecycler_home(Chalet chalet) {
                Intent intent = new Intent(getActivity(), Restaurant_Chalet.class);
                Log.d("TAGxzczxczx", "onClickRecycler_home: "+chalet.getId_firebase());
                intent.putExtra(INTENT_CHALET_ID,chalet.getId_firebase());
                startActivity(intent);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
//All          1
//Site         2
//Time         3
//Price        4
//Evaluation   5
        switch (id_tab) {
            case 1:
                chalets_by_all();
                break;
            case 2:
                chalets_by_site();
                break;
            case 3:
                chalets_by_time();
                break;
            case 4:
                chalets_by_price();
                break;
            case 5:
                chalets_by_evaluation();
                break;
        }

        return view;

    }
    void chalets_by_all(){
        adapter.setArrayList(getChalets());
    }
    void chalets_by_site(){
        adapter.setArrayList(getChalets());
    }
    void chalets_by_time(){
        adapter.setArrayList(getChalets());
    }
    void chalets_by_price(){
        adapter.setArrayList(getChalets());
    }
    void chalets_by_evaluation(){
        adapter.setArrayList(getChalets());
    }

    public ArrayList<Chalet> getChalets() {
        ArrayList<Chalet> arrayList = new ArrayList<>();
        Chalet chalet = new Chalet("9ML1kAobfHdA3KTaNIrTdQZdNqB3", "Mustafa Chalet", "Mustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa Chalet",
                "KhanYounes", 800, 4, "TueJun01");

        Chalet chalet1 = new Chalet("dAPlnTeL0kTa17gsjEJiO3vK1jc2", "Mustafa Chalet", "Mustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa Chalet",
                "KhanYounes", 700, 4, "TueJun01");
        Chalet chalet2 = new Chalet("9ML1kAobfHdA3KTaNIrTdQZdNq", "Mustafa Chalet", "Mustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa Chalet",
                "KhanYounes", 600, 4, "TueJun01");
        Chalet chalet3 = new Chalet("dAPlnTeL0kTa17gsjEJiO3vK1j", "Mustafa Chalet", "Mustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa Chalet",
                "KhanYounes", 500, 4, "TueJun11");

        arrayList.add(chalet);
        arrayList.add(chalet1);
        arrayList.add(chalet2);
        arrayList.add(chalet3);
 //        arrayList.add(new Chalet("Mustafa", "Gaza - KhanYounes", 700, 4.5f, "morning", R.drawable.chalet));
//        arrayList.add(new Chalet("Ali", "Gaza - KhanYounes", 600, 4.5f, "morning", R.drawable.chalet));
//        arrayList.add(new Chalet("Omer", "Gaza - KhanYounes", 500, 4.5f, "morning", R.drawable.chalet));
//        arrayList.add(new Chalet("Ahmed", "Gaza - KhanYounes", 400, 4.5f, "morning", R.drawable.chalet));

        return arrayList;
    }


    @Override
    public void onSearchClick(ArrayList<Chalet> arrayList) {
        adapter.removeAll();
        adapter.setArrayList(arrayList);
        adapter.notifyDataSetChanged();
    }
}