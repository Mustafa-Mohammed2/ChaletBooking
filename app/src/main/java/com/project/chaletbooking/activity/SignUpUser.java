package com.project.chaletbooking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.project.chaletbooking.R;
import com.project.chaletbooking.firebace.UserFirebase;
import com.project.chaletbooking.model.User;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpUser extends AppCompatActivity {

    public static final String INFO_USER_REG = "info_reg";
    public static final String USER_REG_ID = "id";

    UserFirebase userFirebase;

    EditText et_name, et_phone, et_email, et_password;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);

        et_name=findViewById(R.id.user_sign_up_et_Name);
        et_phone=findViewById(R.id.user_sign_up_et_phone);
        et_email=findViewById(R.id.user_sign_up_et_Email);
        et_password=findViewById(R.id.user_sign_up_et_Password);
        btn_signup=findViewById(R.id.patient_btn_sign_up);

        userFirebase=UserFirebase.getInstance(getApplicationContext());


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_name.getText().toString().isEmpty()) {
                    et_name.setError("User name is required.");
                    return;
                } else if (et_phone.getText().toString().isEmpty()) {
                    et_phone.setError("User phone is required.");
                    return;
                } else if (et_email.getText().toString().isEmpty()) {
                    et_email.setError("Email is required.");
                    return;
                } else if (et_password.getText().toString().isEmpty()) {
                    et_password.setError("Password  is required.");
                    return;
                }

                sign_up(et_email.getText().toString(),et_password.getText().toString());
            }
        });




    }

    private void sign_up(final String email, final String password) {

        final User user = new User(null,et_name.getText().toString()
                , et_phone.getText().toString()
                , et_email.getText().toString()
                , et_password.getText().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                String userId = userFirebase.signUp(email, password, user);
                if (userId != null) {
                        user.setId_firebase(userId);
                    userFirebase.uploadUserDataToFireBase(user, userId);

                    startActivity(new Intent(getBaseContext(), Login_User.class));
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            et_phone.setError("تأكد من رقم الهاتف .");
                            et_email.setError("تأكد من الإيميل .");
                            et_password.setError("تأكد من كلمة المرور.");
                            FancyToast.makeText(getBaseContext(), "فشل في إنشاء الحساب.", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();//
                        }
                    });
                }
            }
        }).start();
    }




}