package com.project.chaletbooking.model;

import androidx.fragment.app.Fragment;

public class MyTab {

    String name_category;
    Fragment fragment;


//   used  in home fragment
    public MyTab(String name_category, Fragment fragment) {
        this.name_category = name_category;
        this.fragment = fragment;
    }

    public String getName_category() {
        return name_category;
    }

    public void setName_category(String name_category) {
        this.name_category = name_category;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
