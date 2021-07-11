package com.project.chaletbooking.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.project.chaletbooking.model.MyTab;

import java.util.ArrayList;

public class HomePagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<MyTab> arrayList;

    //construct that passing Fragment Manager  and List
    public HomePagerAdapter(@NonNull FragmentManager fm , ArrayList<MyTab> arrayList) {
        super(fm);
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayList.get(position).getName_category();
    }

}
