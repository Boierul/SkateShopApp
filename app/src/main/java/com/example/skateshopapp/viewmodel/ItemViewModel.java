package com.example.skateshopapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.skateshopapp.model.Item;
import com.example.skateshopapp.repository.ItemRepository;

import java.util.List;

public class ItemViewModel extends ViewModel {

    private static ItemViewModel instance;
    private final ItemRepository repository;

    public ItemViewModel() {
        repository = ItemRepository.getInstance();
    }

    public static synchronized ItemViewModel getInstance() {
        if (instance == null)
            instance = new ItemViewModel();

        return instance;
    }

    public MutableLiveData<List<Item>> getAllNewArrived() {
        return repository.getAllNewArrived();
    }

    public MutableLiveData<List<Item>> getAllDecks() {
        return repository.getAllDecks();
    }

    public MutableLiveData<List<Item>> getAllTrucks() {
        return repository.getAllTrucks();
    }

    public MutableLiveData<List<Item>> getAllAccessories() {
        return repository.getAllAccessories();
    }
    public MutableLiveData<Item> getDeck(int itemId) {
        return repository.getDeck(itemId);
    }

}
