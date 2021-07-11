package com.project.chaletbooking.firebace;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.project.chaletbooking.activity.Login_User;
import com.project.chaletbooking.activity.SignUpUser;
import com.project.chaletbooking.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.concurrent.Semaphore;

import static android.content.Context.MODE_PRIVATE;

public class UserFirebase {

    private static UserFirebase instance;

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    FirebaseAuth mAuth  ;
    Context context;

    Login_User login_user;
    SignUpUser signUpUser;


    private UserFirebase(Context context) {
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
        if (context instanceof AppCompatActivity)
            signUpUser = new SignUpUser();

        if (context instanceof AppCompatActivity) {
            login_user = new Login_User();
        }

    }


    public static UserFirebase getInstance(Context context) {
        if (instance == null) {
            instance = new UserFirebase(context);
        }
        return instance;
    }


    public String signUp(String email, String password, final User user_chalet) {
        final String[] isLoggend = {null};
        final Semaphore semaphore = new Semaphore(0);

         mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( signUpUser, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            isLoggend[0] = mAuth.getUid().toString();
                            user_chalet.setId_firebase(isLoggend[0]);

//                            sp = context.getSharedPreferences(SignUpUser.INFO_USER_REG, MODE_PRIVATE);
//                            editor = sp.edit();
//                            editor.putString(SignUpUser.USER_REG_ID, user.getUid());
//                            editor.apply();

                            FancyToast.makeText(context, "تم إنشاء الحساب بنجاح", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                            semaphore.release();
                        }
                    }
                });
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return isLoggend[0];
    }


    public String logIn(String email, String password) {
        final String[] isLoggend = {null};
        final Semaphore semaphore = new Semaphore(0);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(login_user, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            isLoggend[0] = user.getUid();
                            sp = context.getSharedPreferences(Login_User.INFO_USER_LOGIN, MODE_PRIVATE);
                            editor = sp.edit();
                            Log.d("aaaaa", "this is log in " + isLoggend[0]);
                            editor.putString(Login_User.USER_LOGIN_ID, isLoggend[0]);
                            editor.apply();

//                            sp = context.getSharedPreferences(SignUpUser.INFO_USER_REG, MODE_PRIVATE);
//                            editor = sp.edit();
//                            editor.clear();
//                            editor.commit();
                            FancyToast.makeText(context, "تم تسجيل الدخول بنجاح.", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                            semaphore.release();
                        }
                    }
                });
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return isLoggend[0];
    }



    public User getUserInfo(String userId) {
        final User[] user = {new User()};
        final Semaphore semaphore = new Semaphore(0);
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        final DatabaseReference reference = rootNode.getReference("ChaletBooking").
                child("Users").child(userId).child("User_Information");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user[0] = snapshot.getValue(User.class);
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
        return user[0];
    }//جلب البيانات



    public void uploadUserDataToFireBase(User user, String userId) {

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        final DatabaseReference reference = rootNode.getReference("ChaletBooking").
                child("Users").child(userId).child("User_Information");

        reference.setValue(user);
    }


}


