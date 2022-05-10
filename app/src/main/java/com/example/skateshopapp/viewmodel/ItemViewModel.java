package com.example.skateshopapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.skateshopapp.model.Item;
import com.example.skateshopapp.repository.ItemRepository;

import java.util.List;

public class ItemViewModel extends ViewModel {

    private final ItemRepository repository;

    public ItemViewModel() {
        repository = ItemRepository.getInstance();
    }

    public LiveData<List<Item>> getAllNewArrived() {
        return repository.getAllNewArrived();
    }

    public LiveData<List<Item>> getAllDecks() {
        return repository.getAllDecks();
    }

    public LiveData<List<Item>> getAllTrucks() {
        return repository.getAllTrucks();
    }

}
