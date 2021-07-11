package com.project.chaletbooking.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.chaletbooking.R;
import com.project.chaletbooking.model.Chalet;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Res_Info_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Res_Info_Fragment extends Fragment {


    private static final String ARG_CHALET = "arg_chalet";

    private Chalet chalet;

    public Res_Info_Fragment() {
        // Required empty public constructor
    }


    public static Res_Info_Fragment newInstance(Chalet chalet) {
        Res_Info_Fragment fragment = new Res_Info_Fragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_res__info_, container, false);
//        RecyclerView recyclerView = view.findViewById(R.id.fragment_info_recycler);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
//
//        ServiceAdapter adapter = new ServiceAdapter(getActivity(), chalet.getList_service_chalets());
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(layoutManager);
//        adapter.notifyDataSetChanged();
//        recyclerView.setHasFixedSize(true);
        TextView tv_name = view.findViewById(R.id.fragment_info_name);
        TextView tv_morning = view.findViewById(R.id.fragment_info_name);
        TextView tv_evening = view.findViewById(R.id.fragment_info_name);
        TextView tv_about = view.findViewById(R.id.fragment_info_tv_about_chalet);
        TextView btn_book = view.findViewById(R.id.fragment_info_btn_book);




        return view;
    }
}