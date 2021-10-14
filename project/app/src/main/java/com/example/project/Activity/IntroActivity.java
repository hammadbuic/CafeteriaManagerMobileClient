package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project.Auth.LoginActivity;
import com.example.project.Helper.UserSharedPref;
import com.example.project.R;

public class IntroActivity extends AppCompatActivity {
    private ConstraintLayout startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        startBtn=findViewById(R.id.startbtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserSharedPref.getInstance(IntroActivity.this).isLoggedIn()){
                    startActivity(new Intent(IntroActivity.this,MainActivity.class));
                }else{
                    startActivity(new Intent(IntroActivity.this, LoginActivity.class));
                }
            }
        });
    }
}