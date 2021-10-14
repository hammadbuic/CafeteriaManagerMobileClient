package com.example.project.Auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Domain.RegisterationDomain;
import com.example.project.Domain.RegisterationResponse;
import com.example.project.Interface.ItemAPIInterface;
import com.example.project.R;
import com.example.project.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText etFName,etUname,etEmail,etPassword,etCPassword;
    ConstraintLayout btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etFName=(EditText) findViewById(R.id.editTextTextPersonName2);
        etUname=(EditText) findViewById(R.id.editTextTextPersonName);
        etEmail=(EditText) findViewById(R.id.editTextTextEmailAddress);
        etPassword=(EditText) findViewById(R.id.editTextTextPassword);
        etCPassword=(EditText) findViewById(R.id.editTextTextPassword2);
        btnRegister=(ConstraintLayout) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateRegisterForm();
            }
        });
    }
    private void validateRegisterForm(){
        String fname=etFName.getText().toString();
        String uname=etUname.getText().toString();
        String email=etEmail.getText().toString();
        String pwd=etPassword.getText().toString();
        String cpwd=etCPassword.getText().toString();

        if(TextUtils.isEmpty(fname)){
         etFName.setError("Required");
        }
        if(TextUtils.isEmpty(uname)){
            etUname.setError("Required");
        }
        if(TextUtils.isEmpty(email)){
            etEmail.setError("Required");
        }
        if(TextUtils.isEmpty(pwd)){
            etPassword.setError("Required");
        }
        if(TextUtils.isEmpty(cpwd)){
            etCPassword.setError("Required");
        }
        if(pwd.matches(cpwd))
        {
            etPassword.setError("Password mismatch");
        }
        RegisterationDomain regDomain=new RegisterationDomain(fname,uname,email,pwd);
        requestRegister(regDomain);
    }
    private void requestRegister(RegisterationDomain rgDom){
        ItemAPIInterface registeration= RetrofitClient.getRetrofitInstance().create(ItemAPIInterface.class);
        Call<RegisterationResponse> call = registeration.registerUser(rgDom);
        call.enqueue(new Callback<RegisterationResponse>() {
            @Override
            public void onResponse(Call<RegisterationResponse> call, Response<RegisterationResponse> response) {
                RegisterationResponse rgRep=response.body();
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                Toast.makeText(RegisterActivity.this,"We have Send You a Confirmation Email",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<RegisterationResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}