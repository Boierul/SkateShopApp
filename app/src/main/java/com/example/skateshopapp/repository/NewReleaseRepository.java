package com.example.skateshopapp.repository;

import androidx.lifecycle.LiveData;

import com.example.skateshopapp.dao.NewReleaseDAO;
import com.example.skateshopapp.model.NewRelease;

import java.util.List;

public class NewReleaseRepository {

    private static NewReleaseRepository instance;
    private final NewReleaseDAO newReleaseDAO;

    private LiveData<List<NewRelease>> listLiveData;

    public NewReleaseRepository() {
        newReleaseDAO = NewReleaseDAO.getInstance();
    }

    public static synchronized NewReleaseRepository getInstance() {
        if (instance == null)
            instance = new NewReleaseRepository();

        return instance;
    }

    public LiveData<List<NewRelease>> getAllNewReleases() {
        return newReleaseDAO.getAllNotes();
    }
}
