package com.example.project.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project.Adapter.CategoryAdapter;
import com.example.project.Adapter.PopularAdapter;
import com.example.project.Auth.LoginActivity;
import com.example.project.Domain.CategoryDomain;
import com.example.project.Domain.FoodDomain;
import com.example.project.Helper.ManagementCart;
import com.example.project.Helper.TinyDB;
import com.example.project.Helper.UserSharedPref;
import com.example.project.Interface.ItemAPIInterface;
import com.example.project.R;
import com.example.project.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Models.Item;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView tvName;
    ImageView ivProfile;
    private ManagementCart managementCart;
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    private LinearLayout homeBtn,profileBtn,supportBtn,logoutBtn;
    //private ArrayList<Item> itemArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        managementCart=new ManagementCart(this);
        tvName=(TextView)findViewById(R.id.textView4);
        homeBtn = findViewById(R.id.homeBtn);
        profileBtn=findViewById(R.id.btnProfile);
        supportBtn=findViewById(R.id.btnSupport);
        logoutBtn=findViewById(R.id.btnLogout);
        tvName.setText("Hello, "+UserSharedPref.getInstance(MainActivity.this).loggedInUser().getFullName());
        ivProfile=(ImageView)findViewById(R.id.imageView3);
        getItemsList();
        //recyclerViewCategory();
        //recyclerViewPopular();
        bottomNavigation();
    }

    private void getItemsList(){
       ItemAPIInterface items=RetrofitClient.getRetrofitInstance().create(ItemAPIInterface.class);
       Call<ArrayList<FoodDomain>> call=items.getItemsList();
       call.enqueue(new Callback<ArrayList<FoodDomain>>() {
           @Override
           public void onResponse(Call<ArrayList<FoodDomain>> call, Response<ArrayList<FoodDomain>> response) {
               ArrayList<FoodDomain> itemsFetched=response.body();
               ArrayList<FoodDomain> foodlist=itemsFetched;
               recyclerViewPopular(foodlist);
               Toast.makeText(getApplicationContext(),"Success"+response.code(),Toast.LENGTH_LONG).show();
           }

           @Override
           public void onFailure(Call<ArrayList<FoodDomain>> call, Throwable t) {
               Toast.makeText(getApplicationContext(),"Failed"+t.toString(),Toast.LENGTH_LONG).show();
           }
       });
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartListActivity.class));
            }
        });



        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ProfileActivity.class));
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               File deleteFilePref=new File("/data/data/com.example.project/shared_prefs/User_Information.xml");
                deleteFilePref.delete();
                managementCart.clearCartList();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    private void recyclerViewPopular(ArrayList<FoodDomain> food) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);


        //foodlist.add(new FoodDomain("Pepperoni pizza", "pizza1", "slices pepperoni ,mozzarella cheese, fresh oregano,  ground black pepper, pizza sauce", 9.76));
        //foodlist.add(new FoodDomain("Cheese Burger", "burger", "beef, Gouda Cheese, Special sauce, Lettuce, tomato ", 8.79));

        adapter2 = new PopularAdapter(food);
        recyclerViewPopularList.setAdapter(adapter2);

    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Pizza", "cat_1"));
        categoryList.add(new CategoryDomain("Burger", "cat_2"));
        categoryList.add(new CategoryDomain("Hotdog", "cat_3"));
        categoryList.add(new CategoryDomain("Drink", "cat_4"));
        categoryList.add(new CategoryDomain("Dount", "cat_5"));

        adapter = new CategoryAdapter(categoryList);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}