package com.example.skateshopapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.skateshopapp.dao.ItemDAO;
import com.example.skateshopapp.model.Item;

import java.util.List;

public class ItemRepository {

    private static ItemRepository instance;
    private final ItemDAO itemDAO;

    public ItemRepository() {
        itemDAO = ItemDAO.getInstance();
    }

    public static synchronized ItemRepository getInstance() {
        if (instance == null)
            instance = new ItemRepository();

        return instance;
    }

    public MutableLiveData<List<Item>> getAllNewArrived() {
        return itemDAO.getNewArrivedList();
    }

    public MutableLiveData<List<Item>> getAllDecks() {
        return itemDAO.getDecksList();
    }

    public MutableLiveData<List<Item>> getAllTrucks() {
        return itemDAO.getTrucksList();
    }

    public MutableLiveData<Item> getDeck(int itemId) {
        return itemDAO.getDeck(itemId);
    }
}
