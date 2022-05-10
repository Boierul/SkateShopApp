package com.example.skateshopapp.dao;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.skateshopapp.api.NewReleaseAPI;
import com.example.skateshopapp.model.NewRelease;
import com.example.skateshopapp.service.NewReleaseService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewReleaseDAO {

    private static NewReleaseDAO instance;
    private MutableLiveData<List<NewRelease>> listLiveData;

    public NewReleaseDAO() {
        listLiveData = new MutableLiveData<>();
    }

    public static NewReleaseDAO getInstance() {
        if (instance == null) {
            instance = new NewReleaseDAO();
        }
        return instance;
    }

    public LiveData<List<NewRelease>> getAllNotes() {
        NewReleaseAPI api = NewReleaseService.getNewReleaseAPI();
        Call<List<NewRelease>> call = api.getNewReleases("newrelease");
        call.enqueue(new Callback<List<NewRelease>>() {
            @Override
            public void onResponse(Call<List<NewRelease>> call, Response<List<NewRelease>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                List<NewRelease> newList = response.body();
                listLiveData.setValue(newList);

                Log.d("TESTING", "Response: " + response.body());
            }

            @Override
            public void onFailure(Call<List<NewRelease>> call, Throwable t) {
                Log.d("TESTING FAILURE", t.getMessage());
            }
        });
        return listLiveData;
    }

}
