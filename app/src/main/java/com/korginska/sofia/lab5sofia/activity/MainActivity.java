package com.korginska.sofia.lab5sofia.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.korginska.sofia.lab5sofia.DrinkInterface;
import com.korginska.sofia.lab5sofia.R;
import com.korginska.sofia.lab5sofia.adapter.RecyclerViewAdapter;
import com.korginska.sofia.lab5sofia.model.Drink;
import com.korginska.sofia.lab5sofia.model.Example;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private DrinkInterface drinkInterface;
    private Call<Example> call;
    private SwipeRefreshLayout swipeContainer;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Drink> mDrinks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initRetrofit();
        checkAndGetDrinks();

        initRecyclerView();
        initRefreshLayout();
    }

    private void checkAndGetDrinks() {
        if (isNetworkConnected()) {
            getDrinks();
        } else {
            Toast.makeText(getApplicationContext(), "No internet connection.",
                    Toast.LENGTH_SHORT).show();
            startErrorIntent();
        }
    }

    private void startErrorIntent() {
        Intent errorIntent = new Intent(MainActivity.this, ErrorActivity.class);
        startActivity(errorIntent);
    }
    private void initRetrofit() {
        Log.i("TAG", "init retrofit");
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        drinkInterface = retrofit.create(DrinkInterface.class);
    }


    private void initRefreshLayout() {
        swipeContainer = findViewById(R.id.swipe_layout);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initRetrofit();
                checkAndGetDrinks();
            }
        });
    }

    private void getDrinks() {
        call = drinkInterface.imageOfAlcohol();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                recyclerViewAdapter.clear();
                mDrinks.clear();
                Log.i("TAG", "Get" + response.body().toString());

                Example example = response.body();
                if (response.body() != null) {
                    List<Drink> drinkList = example.getDrinks();
                    mDrinks.addAll(drinkList);
                    Log.i("TAG", "getDrinks():" + Integer.toString(mDrinks.size()));
                    for (Drink drink : mDrinks) {
                        Log.i("f", "ingredient" + drink.getStrIngredient2());
                    }
                    recyclerViewAdapter.addAll(mDrinks);
                    swipeContainer.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.i("TAG", "error: " + t.toString());
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerViewAdapter = new RecyclerViewAdapter(mDrinks);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
