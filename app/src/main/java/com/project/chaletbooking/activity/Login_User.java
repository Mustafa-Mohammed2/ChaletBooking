package com.project.chaletbooking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.project.chaletbooking.R;
import com.project.chaletbooking.firebace.UserFirebase;
import com.shashank.sony.fancytoastlib.FancyToast;

public class Login_User extends AppCompatActivity {

    public static final String INFO_USER_LOGIN = "info_reg";
    public static final String USER_LOGIN_ID = "id";

    CheckBox cb_remember;
    EditText et_email, et_password;
    Button btn_log;
    TextView tv_sign;


    UserFirebase userFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        tv_sign = findViewById(R.id.logUser_tv_SignUp);
        et_email = findViewById(R.id.logUser_et_Email);
        et_password = findViewById(R.id.logUser_et_Password);
        cb_remember = findViewById(R.id.logUser_cb_remember);
        btn_log = findViewById(R.id.logUser_btn_log);


        userFirebase=UserFirebase.getInstance(getApplicationContext());


        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log_in(et_email.getText().toString(),et_password.getText().toString());
            }
        });

    }


    private void log_in(final String email, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String userId = userFirebase.logIn(email, password);
                if (userId != null) {
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            et_email.setError("تأكد من الإيميل .");
                            et_password.setError("تأكد من كلمة المرور.");
                            FancyToast.makeText(getBaseContext(), "فشل في تسجيل الدخول.", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();//
                        }
                    });
                }
            }
        }).start();
    }

}