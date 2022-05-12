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
    private MutableLiveData<List<Item>> newReleaseMutableList, decksMutableList, trucksMutableList;
    private MutableLiveData<Item> deckMutable;

    public ItemDAO() {
        newReleaseMutableList = new MutableLiveData<>();
        decksMutableList = new MutableLiveData<>();
        trucksMutableList = new MutableLiveData<>();
    }

    public static ItemDAO getInstance() {
        if (instance == null) {
            instance = new ItemDAO();
        }
        return instance;
    }

    public MutableLiveData<List<Item>> getNewArrivedList() {
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
                newReleaseMutableList.setValue(newList);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.d("TESTING FAILURE", t.getMessage());
            }
        });
        return newReleaseMutableList;
    }

    public MutableLiveData<List<Item>> getDecksList() {
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
                decksMutableList.setValue(newList);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.d("TESTING FAILURE", t.getMessage());
            }
        });
        return decksMutableList;
    }

    public MutableLiveData<List<Item>> getTrucksList() {
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
                trucksMutableList.setValue(newList);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.d("TESTING FAILURE", t.getMessage());
            }
        });
        return trucksMutableList;
    }

    public MutableLiveData<Item> getDeck(int itemId) {
        ItemAPI api = ItemAPIService.getItemAPI();
        Call<Item> call = api.getDeck(itemId);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (!response.isSuccessful()) {
                    Log.d("TESTING DECK", "Code: " + response.code());
                    return;
                }

                Item deck = response.body();
                deckMutable.setValue(deck);
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Log.d("TESTING FAILURE", t.getMessage());
            }
        });
        return deckMutable;
    }
}
