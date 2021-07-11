package com.project.chaletbooking.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.chaletbooking.R;
import com.project.chaletbooking.adapter.HomePagerAdapter;
import com.project.chaletbooking.firebace.ChaletFirebase;
import com.project.chaletbooking.fragment.HomeFragment;
import com.project.chaletbooking.fragment.HomeFragment_Inner;
import com.project.chaletbooking.fragment.Res_Booking_Fragment;
import com.project.chaletbooking.fragment.Res_Map_Fragment;
import com.project.chaletbooking.fragment.Res_Review_Fragment;
import com.project.chaletbooking.model.Chalet;
import com.project.chaletbooking.model.MyTab;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Restaurant_Chalet extends AppCompatActivity {

    ImageView imageView;
    TabLayout tab;
    ViewPager viewPager;
    ArrayList<MyTab> arrayList;

    ChaletFirebase chaletFirebase;
    MyTab myTab;

    Chalet chalet;
    //    map
    boolean isPermissionGrander;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_chalet);

        imageView = findViewById(R.id.rest_chalet_image);
        tab = findViewById(R.id.rest_chalet_tab);
        viewPager = findViewById(R.id.rest_chalet_view_pager);


        arrayList = new ArrayList<>();

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        ArrayList<SlideModel> slideModels = new ArrayList<>();
//        slideModels.add(new SlideModel(R.drawable.chalet_1, ScaleTypes.FIT));
//        slideModels.add(new SlideModel(R.drawable.chalet_2,ScaleTypes.FIT));
//        slideModels.add(new SlideModel(R.drawable.chalet_3,ScaleTypes.FIT));
////                slideModels.add(new SlideModel("https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.chaletsauquebec.com%2F_photos%2FGrand%2F2364520200811223432265.jpg&imgrefurl=https%3A%2F%2Fwww.chaletsauquebec.com%2Fen%2F23645&tbnid=13zIny3GGvop3M&vet=12ahUKEwj86o-B14DxAhUHs6QKHeNtDzsQMygHegUIARC0AQ..i&docid=1Jk2Py6Ei4FGoM&w=800&h=600&q=image%20chalet&safe=strict&ved=2ahUKEwj86o-B14DxAhUHs6QKHeNtDzsQMygHegUIARC0AQ","asd"));
////                slideModels.add(new SlideModel("https://www.google.com/imgres?imgurl=https%3A%2F%2Fcf.bstatic.com%2Fimages%2Fhotel%2Fmax1024x768%2F246%2F246550827.jpg&imgrefurl=https%3A%2F%2Fwww.booking.com%2Fhotel%2Ffr%2Fblack-pearl-chalet.html&tbnid=x0K1lQbMFQdgHM&vet=12ahUKEwj86o-B14DxAhUHs6QKHeNtDzsQMygDegUIARCsAQ..i&docid=truQp1mX_zSBvM&w=1024&h=683&q=image%20chalet&safe=strict&ved=2ahUKEwj86o-B14DxAhUHs6QKHeNtDzsQMygDegUIARCsAQ","asd"));
//        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

//        Define  Array list  and put in it (Types and  fragment )
        Intent intent = getIntent();
        String chalet_id = intent.getStringExtra(HomeFragment_Inner.INTENT_CHALET_ID);

//        Get Chalet from Firebase
//        chaletFirebase = ChaletFirebase.getInstance(getApplicationContext());
//        chalet= chaletFirebase.getUserInfo(chalet_id);


        final Chalet[] chalets = {new Chalet()};
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        final DatabaseReference reference = rootNode.getReference("ChaletBooking").
                child("Chalets").child(chalet_id).child("Chalet_Information");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chalets[0] = snapshot.getValue(Chalet.class);
//        arrayList.add(new MyTab("Info", Res_Info_Fragment.newInstance("","")));
                Log.d("TAGzczxxz", "onCreate: " + chalets[0].getId_firebase());
                arrayList.add(new MyTab("Booking", Res_Booking_Fragment.newInstance(chalets[0])));
                arrayList.add(new MyTab("Map chalet", new Res_Map_Fragment()));
                arrayList.add(new MyTab("Review", Res_Review_Fragment.newInstance("", "")));


//        call  Adapter  special to fragment   and put Fragment Manager  and Array list
                HomePagerAdapter pagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), arrayList);
                tab.setupWithViewPager(viewPager);
                viewPager.setAdapter(pagerAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
//        check_Permission();

        if (isPermissionGrander) {
//            mapView.getMapAsync(this);
//            mapView.onCreate(savedInstanceState);
        }
    }
}