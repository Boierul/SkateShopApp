package com.example.skateshopapp.repository;

import androidx.lifecycle.LiveData;

import com.example.skateshopapp.dao.ItemDAO;
import com.example.skateshopapp.model.Item;

import java.util.List;

public class ItemRepository {

    private static ItemRepository instance;
    private final ItemDAO itemDAO;

    private LiveData<List<Item>> listLiveData;

    public ItemRepository() {
        itemDAO = ItemDAO.getInstance();
    }

    public static synchronized ItemRepository getInstance() {
        if (instance == null)
            instance = new ItemRepository();

        return instance;
    }

    public LiveData<List<Item>> getAllNewArrived() {
        return itemDAO.getNewArrivedList();
    }

    public LiveData<List<Item>> getAllDecks() {
        return itemDAO.getDecksList();
    }

    public LiveData<List<Item>> getAllTrucks() {
        return itemDAO.getTrucksList();
    }
}
