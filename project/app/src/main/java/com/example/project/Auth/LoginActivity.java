package com.example.project.Auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Activity.MainActivity;
import com.example.project.Domain.FoodDomain;
import com.example.project.Domain.LoginDomain;
import com.example.project.Domain.LoginResponse;
import com.example.project.Domain.UserDetails;
import com.example.project.Helper.UserSharedPref;
import com.example.project.Interface.ItemAPIInterface;
import com.example.project.R;
import com.example.project.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    ConstraintLayout btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView registerNavigate=findViewById(R.id.tvRegister);
        username=(EditText)findViewById(R.id.etUsername);
        password=(EditText)findViewById(R.id.etPassword);
        btnLogin=(ConstraintLayout) findViewById(R.id.btnLogin);
        registerNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateLoginForm();
            }
        });
    }
    private void validateLoginForm(){
        final String userName=username.getText().toString();
        final String passcode=password.getText().toString();
        if(TextUtils.isEmpty(userName)){
            username.setError("Username Required");
            username.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(passcode)){
            password.setError("Password Required");
            password.requestFocus();
            return;
        }
        LoginDomain loginData=new LoginDomain(userName,passcode);
        requestLogin(loginData);
    }
    private void requestLogin(LoginDomain dataLogin){
        ItemAPIInterface login= RetrofitClient.getRetrofitInstance().create(ItemAPIInterface.class);
        Call<LoginResponse> call=login.loginUser(dataLogin);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse res=response.body(); //Token Acquired save it in shared preference
                Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_LONG).show();
                fetchUserDetails(res.getToken());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void fetchUserDetails(String token){
        ItemAPIInterface fetchDetails=RetrofitClient.getRetrofitInstance().create(ItemAPIInterface.class);
        Call<UserDetails> call=fetchDetails.getUserDetails("Bearer "+token);
        call.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                UserDetails details=response.body();
                Toast.makeText(LoginActivity.this,"Details Fetched",Toast.LENGTH_LONG).show();
                details.setToken(token);
                UserSharedPref.getInstance(LoginActivity.this).storeUserDetails(details);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Cannot Fetch Details",Toast.LENGTH_LONG).show();
            }
        });
    }
}