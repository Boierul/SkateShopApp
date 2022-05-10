package com.example.skateshopapp.dao;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.skateshopapp.api.ItemAPI;
import com.example.skateshopapp.model.Item;
import com.example.skateshopapp.service.ItemAPIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDAO {

    private static ItemDAO instance;
    private MutableLiveData<List<Item>> newReleaseLiveData, decksLiveData, trucksLiveData;

    public ItemDAO() {
        newReleaseLiveData = new MutableLiveData<>();
        decksLiveData = new MutableLiveData<>();
        trucksLiveData = new MutableLiveData<>();
    }

    public static ItemDAO getInstance() {
        if (instance == null) {
            instance = new ItemDAO();
        }
        return instance;
    }

    public LiveData<List<Item>> getNewArrivedList() {
        ItemAPI api = ItemAPIService.getItemAPI();
        Call<List<Item>> call = api.getItems("newrelease");
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (!response.isSuccessful()) {
                    Log.d("TESTING", "Code: " + response.code());
                    return;
                }

                List<Item> newList = response.body();
                newReleaseLiveData.setValue(newList);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.d("TESTING FAILURE", t.getMessage());
            }
        });
        return newReleaseLiveData;
    }

    public LiveData<List<Item>> getDecksList() {
        ItemAPI api = ItemAPIService.getItemAPI();
        Call<List<Item>> call = api.getItems("decks");
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (!response.isSuccessful()) {
                    Log.d("TESTING", "Code: " + response.code());
                    return;
                }

                List<Item> newList = response.body();
                decksLiveData.setValue(newList);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.d("TESTING FAILURE", t.getMessage());
            }
        });
        return decksLiveData;
    }

    public LiveData<List<Item>> getTrucksList() {
        ItemAPI api = ItemAPIService.getItemAPI();
        Call<List<Item>> call = api.getItems("trucks");
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (!response.isSuccessful()) {
                    Log.d("TESTING", "Code: " + response.code());
                    return;
                }

                List<Item> newList = response.body();
                trucksLiveData.setValue(newList);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.d("TESTING FAILURE", t.getMessage());
            }
        });
        return trucksLiveData;
    }
}
