package com.project.chaletbooking.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.SearchView;


import android.widget.Spinner;
import android.widget.TextView;

import com.project.chaletbooking.R;
import com.project.chaletbooking.adapter.DateAdapter;
import com.project.chaletbooking.adapter.HomePagerAdapter;
import com.project.chaletbooking.listener.OnClickDateAdapter;
import com.project.chaletbooking.model.Chalet;
import com.project.chaletbooking.model.MyTab;
import com.project.chaletbooking.other.Date_to_Reservation;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class HomeFragment extends Fragment {

    SearchView searchView;
    TabLayout tab;
    ViewPager viewPager;
    Button btn_filter;
    public static final String FILTER_ALL = "all";
    public static final String FILTER_SITE = "site";
    public static final String FILTER_TIME = "time";
    public static final String FILTER_PRICE = "price";
    public static final String FILTER_EVALUATION = "evaluation";

    BottomSheetDialog bottomSheetDialog;

    //     bottom sheet to All
    Button sheet_all_btn_go;
    RelativeLayout sheet_all_site_layout;
    RadioButton sheet_all_site_location, sheet_all_site_area;
    TextView sheet_all_site_tv_select_country;
    Spinner sheet_all_site_spinner_city;
    // bottom sheet all in Time
    DateAdapter sheet_all_time_adapter;
    RecyclerView.LayoutManager sheet_all_time_layoutManager;
    RecyclerView sheet_all_time_recycler_date;
    RadioButton sheet_all_time_morning, sheet_all_time_evening, sheet_all_time_all;
    //     bottom sheet all in Price
    EditText sheet_all_price_et_start;
    EditText sheet_all_price_et_end;
    //     bottom sheet to Evaluation
    RatingBar sheet_all_evaluation_rating;
    //        ************************************************************************
    //     bottom sheet to Site
    RelativeLayout sheet_site_layout;
    Button sheet_site_btn_go;
    RadioButton sheet_site_location, sheet_site_area;
    TextView sheet_site_tv_select_country;
    Spinner sheet_site_spinner_city;

    //     bottom sheet to Time
    DateAdapter sheet_time_adapter;
    RecyclerView.LayoutManager sheet_time_layoutManager;
    RecyclerView sheet_time_recycler_date;
    RadioButton sheet_time_morning, sheet_time_evening, sheet_time_all;
    Button sheet_time_btn_go;

    //     bottom sheet to Price
    EditText sheet_price_et_start;
    EditText sheet_price_et_end;
    Button sheet_price_btn_go;
    //     bottom sheet to Evaluation
    RatingBar sheet_evaluation_rating;
    Button sheet_evaluation_btn_go;

    HomeFragment_Inner fragment_all, fragment_site, fragment_price, fragment_time, fragment_evaluation;
    String current_tab = FILTER_ALL;
    ArrayList<Chalet> arrayList_toSearch;


    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //                  Inflate to view
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        searchView = view.findViewById(R.id.home_searchView);
        tab = view.findViewById(R.id.home_tab);
        viewPager = view.findViewById(R.id.home_view_pager);
        btn_filter = view.findViewById(R.id.home_filter);


//        Define  Array list  and put in it (Types and  fragment )
        ArrayList<MyTab> arrayList = new ArrayList<>();
        arrayList.add(new MyTab("All", fragment_all.newInstance(1)));
        arrayList.add(new MyTab("Site", fragment_site.newInstance(2)));
        arrayList.add(new MyTab("Time", fragment_time.newInstance(3)));
        arrayList.add(new MyTab("Price", fragment_price.newInstance(4)));
        arrayList.add(new MyTab("Evaluation", fragment_evaluation.newInstance(5)));

//        call  Adapter  special to fragment   and put Fragment Manager  and Array list
        HomePagerAdapter pagerAdapter = new HomePagerAdapter(getActivity().getSupportFragmentManager(), arrayList);
        tab.setupWithViewPager(viewPager);
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {// when page is selected passing the name of the selected page
                    case 0:
                        current_tab = FILTER_ALL;
                        break;
                    case 1:
                        current_tab = FILTER_SITE;
                        break;
                    case 2:
                        current_tab = FILTER_TIME;
                        break;
                    case 3:
                        current_tab = FILTER_PRICE;
                        break;
                    case 4:
                        current_tab = FILTER_EVALUATION;
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.AppBottomSheetDialogTheme);
                switch (current_tab) {
                    case FILTER_ALL:
                        getBottomSheetEAll();
                        break;
                    case FILTER_SITE:
                        getBottomSheetSite();
                        break;
                    case FILTER_TIME:
                        getBottomSheetTime();
                        break;
                    case FILTER_PRICE:
                        getBottomSheetPrice();
                        break;
                    case FILTER_EVALUATION:
                        getBottomSheetEvaluation();
                        break;
                }
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                arrayList_toSearch = dataBase.searchFoodFoodsBuyCategoryLive(current_tab, query);
                switch (current_tab) {// as the abve but this is live searching
                    case FILTER_ALL:
                        fragment_all.onSearchClick(arrayList_toSearch);
                        break;
                    case FILTER_SITE:
                        fragment_site.onSearchClick(arrayList_toSearch);
                        break;
                    case FILTER_TIME:
                        fragment_time.onSearchClick(arrayList_toSearch);
                        break;
                    case FILTER_PRICE:
                        fragment_price.onSearchClick(arrayList_toSearch);
                        break;
                    case FILTER_EVALUATION:
                        fragment_evaluation.onSearchClick(arrayList_toSearch);
                        break;
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                arrayList_toSearch = dataBase.searchFoodFoodsBuyCategoryLive(current_tab, query);
                switch (current_tab) {// as the abve but this is live searching
                    case FILTER_ALL:
                        fragment_all.onSearchClick(arrayList_toSearch);
                        break;
                    case FILTER_SITE:
                        fragment_site.onSearchClick(arrayList_toSearch);

                        break;
                    case FILTER_TIME:
                        fragment_time.onSearchClick(arrayList_toSearch);
                        break;
                    case FILTER_PRICE:
                        fragment_price.onSearchClick(arrayList_toSearch);
                        break;
                    case FILTER_EVALUATION:
                        fragment_evaluation.onSearchClick(arrayList_toSearch);
                        break;
                }
                return false;
            }
        });
//        SimpleDateFormat
        {
            Date date = new Date();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat Foramt_date = new SimpleDateFormat("dd-MM-yyyy");
            String date_now = Foramt_date.format(date);

            @SuppressLint("SimpleDateFormat") SimpleDateFormat yearForamt = new SimpleDateFormat("yyyy");
            int year = Integer.parseInt(yearForamt.format(date));

            @SuppressLint("SimpleDateFormat") SimpleDateFormat monthForamt = new SimpleDateFormat("MM");
            int month = Integer.parseInt(monthForamt.format(date));
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dayForamt = new SimpleDateFormat("dd");
            int day = Integer.parseInt(dayForamt.format(date));
        }

        return view;
    }

    public void getBottomSheetEAll() {
        NestedScrollView bottomSheetView = (NestedScrollView) LayoutInflater.from(getActivity()).
                inflate(R.layout.layout_bottom_sheet_all, getActivity().findViewById(R.id.bottomSheetContainer_all));

        sheet_all_site_area = bottomSheetView.findViewById(R.id.sheet_all_site__rb_area);
        sheet_all_site_location = bottomSheetView.findViewById(R.id.sheet_all_site__rb_location);
        sheet_all_site_layout = bottomSheetView.findViewById(R.id.sheet_all_site__relative_inner);
        sheet_all_site_tv_select_country = bottomSheetView.findViewById(R.id.sheet_all_site__tv_select_country);
        sheet_all_site_spinner_city = bottomSheetView.findViewById(R.id.sheet_all_site__sp_select_city);
        sheet_all_time_recycler_date = bottomSheetView.findViewById(R.id.sheet_all_time__recycler_date);
        sheet_all_time_all = bottomSheetView.findViewById(R.id.sheet_all_time__rb_morning);
        sheet_all_time_morning = bottomSheetView.findViewById(R.id.sheet_all_time__rb_evening);
        sheet_all_evaluation_rating = bottomSheetView.findViewById(R.id.sheet_all_eva__rating);


//                bottomSheetDialog.dismiss();
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    public void getBottomSheetSite() {
        View bottomSheetView = LayoutInflater.from(getActivity()).
                inflate(R.layout.layout_bottom_sheet_site, getActivity().findViewById(R.id.bottomSheetContainer_site));

        sheet_site_area = bottomSheetView.findViewById(R.id.sheet_site_rb_area);
        sheet_site_location = bottomSheetView.findViewById(R.id.sheet_site_rb_location);
        sheet_site_layout = bottomSheetView.findViewById(R.id.sheet_site_relative_inner);
        sheet_site_tv_select_country = bottomSheetView.findViewById(R.id.sheet_site_tv_select_country);
        sheet_site_spinner_city = bottomSheetView.findViewById(R.id.sheet_site_sp_select_city);
        sheet_site_btn_go = bottomSheetView.findViewById(R.id.sheet_site_btn_go);

        //country
//
//                CountryPicker picker = CountryPicker.newInstance("Select Country", Theme.DARK);  // dialog title and theme
//                picker.setListener(new CountryPickerListener() {
//                    @Override
//                    public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
//
//
//                        // Implement your code here
//                        Toast.makeText(getActivity(), name + "\t" + dialCode, Toast.LENGTH_SHORT).show();
////                YOU_EDITTEXT.setText(name);
////                YOUR_IMAGE_VIEW.setImageResource(flagDrawableResID);
//
//                        picker.dismiss();
//                    }
//                });
//                picker.show(getActivity().getSupportFragmentManager(), "COUNTRY_PICKER");

//                bottomSheetDialog.dismiss();
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }


    public void getBottomSheetTime() {
        View bottomSheetView = LayoutInflater.from(getActivity()).
                inflate(R.layout.layout_bottom_sheet_time, getActivity().findViewById(R.id.sheet_time_recycler_date));
        sheet_time_recycler_date = bottomSheetView.findViewById(R.id.sheet_time_recycler_date);
        sheet_time_all = bottomSheetView.findViewById(R.id.sheet_time_rb_morning);
        sheet_time_morning = bottomSheetView.findViewById(R.id.sheet_time_rb_evening);
        sheet_time_btn_go = bottomSheetView.findViewById(R.id.sheet_time_btn_go);


        ArrayList<Date_to_Reservation> arrayList = new ArrayList<>();
        arrayList.add(new Date_to_Reservation("Tue", "Jun", "01", false, 3));
        arrayList.add(new Date_to_Reservation("Fri", "Jun", "04", false, 2));
        arrayList.add(new Date_to_Reservation("Mon", "Jun", "07", false, 2));
        arrayList.add(new Date_to_Reservation("Sat", "Jul", "31", false, 1));


        sheet_time_adapter = new DateAdapter(getActivity(), getAllDateOfMonth(arrayList), new OnClickDateAdapter() {
            @Override
            public void onClickDateAdapter(Date_to_Reservation date_toReservation) {

            }
        });

        sheet_time_layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        sheet_time_recycler_date.setLayoutManager(sheet_time_layoutManager);
        sheet_time_recycler_date.setAdapter(sheet_time_adapter);
        sheet_time_adapter.notifyDataSetChanged();
        sheet_time_recycler_date.setHasFixedSize(true);

//        sheet_time_calendarView.sett

//                bottomSheetDialog.dismiss();
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
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


    public void getBottomSheetPrice() {
        View bottomSheetView = LayoutInflater.from(getActivity()).
                inflate(R.layout.layout_bottom_sheet_price, getActivity().findViewById(R.id.bottomSheetContainer_price));

        sheet_price_et_start = bottomSheetView.findViewById(R.id.sheet_site_rb_area);
        sheet_price_et_end = bottomSheetView.findViewById(R.id.sheet_site_rb_location);
        sheet_price_btn_go = bottomSheetView.findViewById(R.id.sheet_price_btn_go);

//                bottomSheetDialog.dismiss();
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    public void getBottomSheetEvaluation() {
        View bottomSheetView = LayoutInflater.from(getActivity()).
                inflate(R.layout.layout_bottom_sheet_evaluation, getActivity().findViewById(R.id.bottomSheetContainer_evaluation));

        sheet_evaluation_rating = bottomSheetView.findViewById(R.id.sheet_evaluation_rating);
        sheet_evaluation_btn_go = bottomSheetView.findViewById(R.id.sheet_evaluation_btn_go);

//                bottomSheetDialog.dismiss();
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

}