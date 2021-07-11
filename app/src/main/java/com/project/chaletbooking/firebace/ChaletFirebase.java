package com.project.chaletbooking.firebace;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.project.chaletbooking.model.Chalet;
import com.project.chaletbooking.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class ChaletFirebase {

    private static ChaletFirebase instance;

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    FirebaseAuth mAuth  ;
    Context context;

//    Login_User login_user;
//    SignUpUser signUpUser;


    private ChaletFirebase(Context context) {
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
        if (context instanceof AppCompatActivity)
//            signUpUser = new SignUpUser();

        if (context instanceof AppCompatActivity) {
//            login_user = new Login_User();
        }

    }


    public static ChaletFirebase getInstance(Context context) {
        if (instance == null) {
            instance = new ChaletFirebase(context);
        }
        return instance;
    }


//    public String signUp(String email, String password, final User user_chalet) {
//        final String[] isLoggend = {null};
//        final Semaphore semaphore = new Semaphore(0);
//
//         mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener( signUpUser, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            isLoggend[0] = mAuth.getUid().toString();
//                            user_chalet.setId_firebase(isLoggend[0]);
//
////                            sp = context.getSharedPreferences(SignUpUser.INFO_USER_REG, MODE_PRIVATE);
////                            editor = sp.edit();
////                            editor.putString(SignUpUser.USER_REG_ID, user.getUid());
////                            editor.apply();
//
//                            FancyToast.makeText(context, "تم إنشاء الحساب بنجاح", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
//                            semaphore.release();
//                        }
//                    }
//                });
//        try {
//            semaphore.acquire();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return isLoggend[0];
//    }


//    public String logIn(String email, String password) {
//        final String[] isLoggend = {null};
//        final Semaphore semaphore = new Semaphore(0);
//
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(login_user, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            isLoggend[0] = user.getUid();
//                            sp = context.getSharedPreferences(Login_User.INFO_USER_LOGIN, MODE_PRIVATE);
//                            editor = sp.edit();
//                            Log.d("aaaaa", "this is log in " + isLoggend[0]);
//                            editor.putString(Login_User.USER_LOGIN_ID, isLoggend[0]);
//                            editor.apply();
//
////                            sp = context.getSharedPreferences(SignUpUser.INFO_USER_REG, MODE_PRIVATE);
////                            editor = sp.edit();
////                            editor.clear();
////                            editor.commit();
//                            FancyToast.makeText(context, "تم تسجيل الدخول بنجاح.", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
//                            semaphore.release();
//                        }
//                    }
//                });
//        try {
//            semaphore.acquire();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return isLoggend[0];
//    }


//    *********************************************


//    public Chalet getChaletInfo(String userId) {
//
////        final DatabaseReference reference = rootNode.getReference("ChaletBooking").
////                child("Chalets").child(userId).child("Chalet_Information");
//
//        final Chalet[] chalets = {new Chalet()};
//        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
//        final DatabaseReference reference = rootNode.getReference("ChaletBooking").
//                child("Chalets").child(userId).child("Chalet_Information");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                chalets[0] = snapshot.getValue(Chalet.class);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//
//
//
//        return chalets[0];
//    }//جلب البيانات


    public ArrayList<Chalet> getAllChalet() {

//        final DatabaseReference reference = rootNode.getReference("ChaletBooking").
//                child("Chalets").child(userId).child("Chalet_Information");

        ArrayList<Chalet>arrayList=new ArrayList<>();
        final Semaphore semaphore = new Semaphore(0);
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        final DatabaseReference reference = rootNode.getReference("ChaletBooking").
                child("Chalets");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Log.d("rrrrr", snapshot1.getKey());

                    Chalet chalet = snapshot1.child("Chalet_Information").getValue(Chalet.class);

                    if (chalet != null) {
                        Log.d("rrrrr", chalet.getName() + " t : " + chalet.getId_firebase());

                        arrayList.add(chalet);
                    }
                }
                semaphore.release();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return arrayList;
    }//جلب البيانات




}


