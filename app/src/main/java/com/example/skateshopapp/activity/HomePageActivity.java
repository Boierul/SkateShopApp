package com.example.skateshopapp.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skateshopapp.R;
import com.example.skateshopapp.adapter.AccessoriesRecycleAdapter;
import com.example.skateshopapp.adapter.DeckRecycleAdapter;
import com.example.skateshopapp.adapter.NewReleaseRecyclerAdapter;
import com.example.skateshopapp.adapter.TruckRecycleAdapter;
import com.example.skateshopapp.model.Item;
import com.example.skateshopapp.viewmodel.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    /* TODO implement search functionality */

    private RecyclerView recyclerViewNewRelease, recyclerViewDecks, recyclerViewTrucks, recyclerViewAccessories;

    private NewReleaseRecyclerAdapter newReleaseRecyclerAdapter;
    private DeckRecycleAdapter deckRecycleAdapter;
    private TruckRecycleAdapter truckRecycleAdapter;
    private AccessoriesRecycleAdapter accessoriesRecycleAdapter;

    private List<Item> newReleaseList, decksList, trucksList, accessoriesList;

    private ItemViewModel itemViewModel;

    private ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_home_page);

        /* New release cards */

        recyclerViewNewRelease = findViewById(R.id.newArrivalsRecycleView);
        newReleaseList = new ArrayList<>();

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        itemViewModel.getAllNewArrived().observe(this, newReleases -> {
            if (!newReleases.isEmpty()) {
                for (Item nr : newReleases) {
                    newReleaseList.add(nr);
                }
            } else {
                Toast.makeText(this,
                        "The list is empty",
                        Toast.LENGTH_SHORT).show();
            }
        });

        /*  TESTING NEW RELEASE FETCH FROM DATA SERVER
        Item new_release_item_1 = new Item("Carlos Ribeiro Frieza", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decks/card_photo_1.png", "8.5", "500 Kr.");
        Item new_release_item_2 = new Item("Miles Silvas Krillin", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decks/card_photo_2.png", "8.0", "500 Kr.");
        Item new_release_item_3 = new Item("Tiago Lemos Cell", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decks/card_photo_3.png", "7.75", "550 Kr.");
        Item new_release_item_4 = new Item("Paul Rodriguez Goku", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decks/card_photo_4.png", "8.25", "600 Kr.");

        newReleaseList.add(new_release_item_1);
        newReleaseList.add(new_release_item_2);
        newReleaseList.add(new_release_item_3);
        newReleaseList.add(new_release_item_4);
          */

        newReleaseRecyclerAdapter = new NewReleaseRecyclerAdapter(this, newReleaseList);
        recyclerViewNewRelease.setHasFixedSize(true);
        recyclerViewNewRelease.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNewRelease.setAdapter(newReleaseRecyclerAdapter);
        newReleaseRecyclerAdapter.notifyDataSetChanged();

        /* ----------------------------------------------------------------------------------------------------------------------------- */
        /* Decks */

        recyclerViewDecks = findViewById(R.id.decksRecyclerView);
        decksList = new ArrayList<>();

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        itemViewModel.getAllDecks().observe(this, decks -> {
            if (!decks.isEmpty()) {
                for (Item deck : decks) {
                    decksList.add(deck);
                }
            } else {
                Toast.makeText(this,
                        "The decks list is empty",
                        Toast.LENGTH_SHORT).show();
            }
        });

        /*  TESTING NEW RELEASE FETCH FROM DATA SERVER
        Item deck_item_1 = new Item("Primitive X Rick and Morty Exclusive Edition", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_2.png", "8.0", "550 Kr.");
        Item deck_item_2 = new Item("Girl Andrew Brophy Design", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_8.png", "8.0", "450 Kr.");
        Item deck_item_3 = new Item("Baker Old School Red", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_3.png", "8.0", "500 Kr.");
        Item deck_item_4 = new Item("Paul Rodriguez Goku", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_7.png", "8.0", "600 Kr.");
        Item deck_item_5 = new Item("Plan B Team OG Skateboard", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_1.png", "8.0", "550 Kr.");


        decksList.add(deck_item_1);
        decksList.add(deck_item_2);
        decksList.add(deck_item_3);
        decksList.add(deck_item_4);
        decksList.add(deck_item_5);
        */

        deckRecycleAdapter = new DeckRecycleAdapter(this, decksList);
        recyclerViewDecks.setHasFixedSize(true);
        recyclerViewDecks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewDecks.setAdapter(deckRecycleAdapter);
        deckRecycleAdapter.notifyDataSetChanged();

        /* ----------------------------------------------------------------------------------------------------------------------------- */
        /* Trucks */

        recyclerViewTrucks = findViewById(R.id.trucksRecyclerView);
        trucksList = new ArrayList<>();

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        itemViewModel.getAllDecks().observe(this, trucks -> {
            if (!trucks.isEmpty()) {
                for (Item truck : trucks) {
                    trucksList.add(truck);
                }
            } else {
                Toast.makeText(this,
                        "The list is empty",
                        Toast.LENGTH_SHORT).show();
            }
        });

        /*  TESTING NEW RELEASE FETCH FROM DATA SERVER
        Item truck_item_1 = new Item("Primitive X Rick and Morty Exclusive Edition", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_2.png", "8.0", "550 Kr.");
        Item truck_item_2 = new Item("Girl Andrew Brophy Design", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_8.png", "8.0", "450 Kr.");
        Item truck_item_3 = new Item("Baker Old School Red", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_3.png", "8.0", "500 Kr.");
        Item truck_item_4 = new Item("Paul Rodriguez Goku", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_7.png", "8.0", "600 Kr.");

        trucksList.add(truck_item_1);
        trucksList.add(truck_item_2);
        trucksList.add(truck_item_3);
        trucksList.add(truck_item_4);
        */

        truckRecycleAdapter = new TruckRecycleAdapter(this, trucksList);
        recyclerViewTrucks.setHasFixedSize(true);
        recyclerViewTrucks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewTrucks.setAdapter(truckRecycleAdapter);
        deckRecycleAdapter.notifyDataSetChanged();

        /* ----------------------------------------------------------------------------------------------------------------------------- */
        /* Accessories */

        recyclerViewAccessories = findViewById(R.id.accessoriesRecyclerView);
        accessoriesList = new ArrayList<>();

        /* TESTING NEW RELEASE FETCH FROM DATA SERVER
        Item accessories_item_1 = new Item("Primitive X Rick and Morty Exclusive Edition", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_2.png", "7.5", "550 Kr.");
        Item accessories_item_2 = new Item("Girl Andrew Brophy Design", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_8.png", "7.5", "450 Kr.");
        Item accessories_item_3 = new Item("Baker Old School Red", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_3.png", "7.5", "500 Kr.");
        Item accessories_item_4 = new Item("Paul Rodriguez Goku", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_7.png", "7.5", "600 Kr.");


        accessoriesList.add(accessories_item_1);
        accessoriesList.add(accessories_item_2);
        accessoriesList.add(accessories_item_3);
        accessoriesList.add(accessories_item_4);
        */

        accessoriesRecycleAdapter = new AccessoriesRecycleAdapter(this, accessoriesList);
        recyclerViewAccessories.setHasFixedSize(true);
        recyclerViewAccessories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewAccessories.setAdapter(accessoriesRecycleAdapter);
        accessoriesRecycleAdapter.notifyDataSetChanged();

        /* ----------------------------------------------------------------------------------------------------------------------------- */

        // TODO setup button path (it opens multiple HomePages and this might cause problems)
        home = findViewById(R.id.logo);
        home.setOnClickListener(view -> {
            if (this != HomePageActivity.this) {
                /* TODO change back*/
//                startActivity(new Intent(this, HomePageActivity.class));
            }
            startActivity(new Intent(this, ProfileActivity.class));
        });

    }
}