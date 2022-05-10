package com.example.skateshopapp.api;

import com.example.skateshopapp.model.Comment;
import com.example.skateshopapp.model.NewRelease;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NewReleaseAPI {
    @GET()
    Call<List<NewRelease>> getNewReleases(@Url String url);

}
