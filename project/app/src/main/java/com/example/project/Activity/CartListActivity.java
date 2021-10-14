package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Adapter.CartListAdapter;
import com.example.project.Auth.LoginActivity;
import com.example.project.Domain.FoodDomain;
import com.example.project.Domain.Order;
import com.example.project.Domain.RegisterationResponse;
import com.example.project.Helper.ManagementCart;
import com.example.project.Helper.TinyDB;
import com.example.project.Helper.UserSharedPref;
import com.example.project.Interface.ChangeNumberItemsListener;
import com.example.project.Interface.ItemAPIInterface;
import com.example.project.R;
import com.example.project.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;
    private TextView btnCheckout;
    private EditText etAddress;
    private LinearLayout btnLogout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        calculateCard();
        bottomNavigation();
        CheckOut();
    }
    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, CartListActivity.class));
            }
        });
        btnLogout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File deleteFilePref=new File("/data/data/com.example.project/shared_prefs/User_Information.xml");
                deleteFilePref.delete();
                managementCart.clearCartList();
                startActivity(new Intent(CartListActivity.this, LoginActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, MainActivity.class));
            }
        });
    }
    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCard(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCard();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCard().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCard() {
        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100.0) / 100.0;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100.0) / 100.0;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100.0) / 100.0;

        totalFeeTxt.setText("PKR " + itemTotal);
        taxTxt.setText("PKR " + tax);
        deliveryTxt.setText("PKR " + delivery);
        totalTxt.setText("PKR" + total);
    }

    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerview);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView4);
        btnCheckout=findViewById(R.id.textView16);
        etAddress=findViewById(R.id.etAddress);
        btnLogout2=findViewById(R.id.btnLogout2);
    }

    private void CheckOut(){
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String deliveryAddress=etAddress.getText().toString();
                if(TextUtils.isEmpty(deliveryAddress)){
                    etAddress.setError("Address is Required");
                    etAddress.requestFocus();
                }
                ArrayList<Order> ordersToPost=prepareOrder(deliveryAddress);
                PostOrders(ordersToPost);
                //Toast.makeText(CartListActivity.this,"Checking out",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private ArrayList<Order> prepareOrder(String address){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        ArrayList<Order> orders=new ArrayList<Order>();
      ArrayList<FoodDomain> listFoodToOrder=managementCart.getListCard();
      for(int i=0;i<listFoodToOrder.size();i++){
        Order order=new Order(UserSharedPref.getInstance(CartListActivity.this).loggedInUser().getFullName(),listFoodToOrder.get(i).getNumberInCart(),address,currentDateTime,listFoodToOrder.get(i).getId(),false);
        orders.add(order);
      }
      return orders;
    }
    private void PostOrders(ArrayList<Order> orders){
        String bearerToken="Bearer "+UserSharedPref.getInstance(CartListActivity.this).loggedInUser().getToken();
        ItemAPIInterface postOrders= RetrofitClient.getRetrofitInstance().create(ItemAPIInterface.class);
        for(int i=0;i<orders.size();i++) {
            Call<RegisterationResponse> res = postOrders.postOrder(bearerToken, orders.get(i));
            res.enqueue(new Callback<RegisterationResponse>() {
                @Override
                public void onResponse(Call<RegisterationResponse> call, Response<RegisterationResponse> response) {
                    RegisterationResponse placeResult=response.body();
                    managementCart.clearCartList();
                    Toast.makeText(CartListActivity.this,"Order Placed! Will be Delivered Soon",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CartListActivity.this,MainActivity.class));
                }

                @Override
                public void onFailure(Call<RegisterationResponse> call, Throwable t) {
                    managementCart.clearCartList();
                    Toast.makeText(CartListActivity.this,"Order Placed! Will be Delivered Soon",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CartListActivity.this,MainActivity.class));
                }
            });
        }
    }
}