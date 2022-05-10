package com.example.skateshopapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.skateshopapp.model.NewRelease;
import com.example.skateshopapp.repository.NewReleaseRepository;

import java.util.List;

public class NewReleaseViewModel extends ViewModel {

    private final NewReleaseRepository repository;

    public NewReleaseViewModel() {
        repository = NewReleaseRepository.getInstance();
    }

    public LiveData<List<NewRelease>> getAllNewReleases() {
        return repository.getAllNewReleases();
    }

}
