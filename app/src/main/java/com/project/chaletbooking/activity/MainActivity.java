package com.project.chaletbooking.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.project.chaletbooking.R;
import com.project.chaletbooking.firebace.ChaletFirebase;
import com.project.chaletbooking.fragment.HomeFragment;
import com.project.chaletbooking.fragment.ReservationFragment;
import com.project.chaletbooking.model.Chalet;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottom_nav;
    FirebaseDatabase rootNode ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom_nav = findViewById(R.id.main_nav_bar);
        rootNode = FirebaseDatabase.getInstance();


//(String id_firebase, String name, String about_chalet, String site, int price, float rate, String time) {

//        Chalet chalet = new Chalet("9ML1kAobfHdA3KTaNIrTdQZdNqB3", "Mustafa Chalet", "Mustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa Chalet",
//                "KhanYounes", 500, 4, "TueJun01");
//
//        Chalet chalet1 = new Chalet("dAPlnTeL0kTa17gsjEJiO3vK1jc2", "Mustafa Chalet", "Mustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa Chalet",
//                "KhanYounes", 500, 4, "TueJun01");
//        Chalet chalet2 = new Chalet("9ML1kAobfHdA3KTaNIrTdQZdNq", "Mustafa Chalet", "Mustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa Chalet",
//                "KhanYounes", 500, 4, "TueJun01");
//        Chalet chalet3 = new Chalet("dAPlnTeL0kTa17gsjEJiO3vK1j", "Mustafa Chalet", "Mustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa ChaletMustafa Chalet",
//                "KhanYounes", 500, 4, "TueJun11");
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                ChaletFirebase chaletFirebase=ChaletFirebase.getInstance(getApplicationContext());
//                 ArrayList<Chalet>arrayList =  chaletFirebase.getAllChalet();
//                 Chalet chalet4=chaletFirebase.getUserInfo("dAPlnTeL0kTa17gsjEJiO3vK1j");
//                Log.d("TAGccx", "onCreate: "+chalet4.getAbout_chalet());
//            }
//        }).start();


        if (savedInstanceState == null) {
            bottom_nav.setSelectedItemId(R.id.menu_home);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

//            uploadChaletDataToFireBase1(chalet,"9ML1kAobfHdA3KTaNIrTdQZdNqB3");
//            uploadChaletDataToFireBase1(chalet1,"dAPlnTeL0kTa17gsjEJiO3vK1jc2");
//            uploadChaletDataToFireBase1(chalet2,"9ML1kAobfHdA3KTaNIrTdQZdNq");
//            uploadChaletDataToFireBase1(chalet3,"dAPlnTeL0kTa17gsjEJA3KTaNIrTd");

        }

        bottom_nav.setOnNavigationItemSelectedListener(nav_listener);

//        used this later
        Intent intent = getIntent();
        if (intent.getIntExtra("name", 0) == 1) {
            bottom_nav.setSelectedItemId(R.id.menu_home);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }
    }


    Fragment selectedFragment = null;

    BottomNavigationView.OnNavigationItemSelectedListener nav_listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//            if (item.getItemId()==R.id.menu_log_out){
//
//
////                Clear Shared Preferences
//
//            }else
            switch (item.getItemId()) {
                case R.id.menu_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.menu_basket:
                    selectedFragment = new ReservationFragment();
                    break;
//                case R.id.menu_profile:
//                    selectedFragment=new HomeProfileFragment();
//                    break;
//                case R.id.menu_chat:
//                    selectedFragment=new ChatFragment();
//                    break;
            }
            if (selectedFragment != null)
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };


    public void uploadChaletDataToFireBase1(Chalet chalet, String userId) {

        final DatabaseReference reference = rootNode.getReference("ChaletBooking").
                child("Chalets").child(userId).child("Chalet_Information");

        reference.setValue(chalet);
    }


}