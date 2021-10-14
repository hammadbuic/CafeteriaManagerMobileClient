package com.example.project.Helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.project.Auth.LoginActivity;
import com.example.project.Domain.UserDetails;

public class UserSharedPref {
    //Storage Filename
    public static final String SHARED_PREF_NAME="User_Information";
    public static final String ObjName_Shared_Pref="USER_DETAILS";
    public static UserSharedPref instance;
    public static Context context;
    UserSharedPref(Context context){
        this.context=context;
    }
    public static synchronized UserSharedPref getInstance(Context context){
        if(instance==null){
            instance=new UserSharedPref(context);
        }
        return instance;
    }
    public void storeUserDetails(UserDetails details){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("id",details.getId());
        editor.putString("fullname",details.getFullName());
        editor.putString("username",details.getUserName());
        editor.putString("email",details.getEmail());
        editor.putString("role",details.getRole());
        editor.putString("token",details.getToken());
        editor.commit();
    }
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("fullname",null) !=null;
    }
    public UserDetails loggedInUser(){
        String i,f,u,e,r,t;
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        i=sharedPreferences.getString("id",null);
        f=sharedPreferences.getString("fullname",null);
        u=sharedPreferences.getString("username",null);
        e=sharedPreferences.getString("email",null);
        r=sharedPreferences.getString("role",null);
        t=sharedPreferences.getString("token",null);
        UserDetails user=new UserDetails(i,f,u,e,r,t);
        return user;
        //return sharedPreferences.getString(ObjName_Shared_Pref,null);
    }
    public void clearDetailsForLogout(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.clear();
        editor.commit();
        //context.startActivity(new Intent(context, LoginActivity.class));
    }
}
