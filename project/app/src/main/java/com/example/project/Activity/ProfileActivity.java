package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.Domain.UserDetails;
import com.example.project.Helper.UserSharedPref;
import com.example.project.Interface.ItemAPIInterface;
import com.example.project.R;
import com.example.project.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private EditText etFname,etUname,etmail,etrole,etid;
    private UserDetails placeholderUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        etFname=findViewById(R.id.etFName);
        etUname=findViewById(R.id.etUName);
        etmail=findViewById(R.id.etMail);
        etrole=findViewById(R.id.etRole);

        placeholderUser=UserSharedPref.getInstance(ProfileActivity.this).loggedInUser();
        etFname.setText(placeholderUser.getFullName());
        etUname.setText(placeholderUser.getUserName());
        etmail.setText(placeholderUser.getEmail());
        etrole.setText(placeholderUser.getRole());


    }
}