package com.example.skateshopapp.service;

import com.example.skateshopapp.api.ItemAPI;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemAPIService {
    private static ItemAPI itemAPI;

    public static synchronized ItemAPI getItemAPI() {
        if (itemAPI == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder().
                    addInterceptor(loggingInterceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.mocki.io/v2/51fcb03b/").
                            addConverterFactory(GsonConverterFactory.create()).
                            client(okHttpClient).
                            build();

            itemAPI = retrofit.create(ItemAPI.class);
        }
        return itemAPI;
    }
}
