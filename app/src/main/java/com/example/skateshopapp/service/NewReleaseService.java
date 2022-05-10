package com.example.skateshopapp.service;

import com.example.skateshopapp.api.NewReleaseAPI;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewReleaseService {
    private static NewReleaseAPI newReleaseAPI;

    public static synchronized NewReleaseAPI getNewReleaseAPI() {
        if (newReleaseAPI == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder().
                    addInterceptor(loggingInterceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.mocki.io/v2/51fcb03b/").
                            addConverterFactory(GsonConverterFactory.create()).
                            client(okHttpClient).
                            build();

            newReleaseAPI = retrofit.create(NewReleaseAPI.class);
        }
        return newReleaseAPI;
    }
}
