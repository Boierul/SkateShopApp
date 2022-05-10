package com.example.skateshopapp.api;

import com.example.skateshopapp.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ItemAPI {
    @GET()
    Call<List<Item>> getItems(@Url String url);
}
