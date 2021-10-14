package com.example.project.Interface;

import com.example.project.Domain.FoodDomain;
import com.example.project.Domain.LoginDomain;
import com.example.project.Domain.LoginResponse;
import com.example.project.Domain.Order;
import com.example.project.Domain.RegisterationDomain;
import com.example.project.Domain.RegisterationResponse;
import com.example.project.Domain.UserDetails;

import java.util.ArrayList;
import java.util.List;

import Models.Item;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ItemAPIInterface {
    @GET("api/item")
    Call<ArrayList<FoodDomain>> getItemsList();
    @POST("api/auth/login")
    Call<LoginResponse> loginUser(@Body LoginDomain loginData);
    @POST("api/auth/postuser")
    Call<RegisterationResponse> registerUser(@Body RegisterationDomain registerData);
    @GET("api/auth")
    Call<UserDetails> getUserDetails(@Header("Authorization") String authHeader);
    @POST("api/orders")
    Call<RegisterationResponse> postOrder(@Header("Authorization") String authHeader,@Body Order order);
    //Doesnt allow string so using registration class to get response
}
