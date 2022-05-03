package com.example.skateshopapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.skateshopapp.R;
import com.example.skateshopapp.adapter.ItemRecycleAdapter;
import com.example.skateshopapp.adapter.NewReleaseRecyclerAdapter;
import com.example.skateshopapp.model.Item;
import com.example.skateshopapp.model.NewRelease;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNewRelease, recyclerViewDecks;
    private NewReleaseRecyclerAdapter newReleaseRecyclerAdapter;
    private ItemRecycleAdapter itemRecycleAdapter;
    private List<NewRelease> newReleaseList;
    private List<Item> itemList;

    private ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_home_page);

        recyclerViewNewRelease = findViewById(R.id.newArrivalsRecycleView);
        newReleaseList = new ArrayList<>();

        NewRelease new_release_item_1 = new NewRelease("Carlos Ribeiro Frieza ", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decks/card_photo_1.png", "8.5", "500 Kr.");
        NewRelease new_release_item_2 = new NewRelease("Miles Silvas Krillin", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decks/card_photo_2.png", "8.0", "500 Kr.");
        NewRelease new_release_item_3 = new NewRelease("Tiago Lemos Cell", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decks/card_photo_3.png", "7.75", "550 Kr.");
        NewRelease new_release_item_4 = new NewRelease("Paul Rodriguez Goku", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decks/card_photo_4.png", "8.25", "600 Kr.");

        newReleaseList.add(new_release_item_1);
        newReleaseList.add(new_release_item_2);
        newReleaseList.add(new_release_item_3);
        newReleaseList.add(new_release_item_4);

        newReleaseRecyclerAdapter = new NewReleaseRecyclerAdapter(this, newReleaseList);
        recyclerViewNewRelease.setHasFixedSize(true);
        recyclerViewNewRelease.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNewRelease.setAdapter(newReleaseRecyclerAdapter);
        newReleaseRecyclerAdapter.notifyDataSetChanged();

        /* ----------------------------------------------------------------------------------------------------------------------------- */

        recyclerViewDecks = findViewById(R.id.decksRecyclerView);
        itemList = new ArrayList<>();

        Item item_item_1 = new Item("Primitive X Rick and Morty Exclusive Edition", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_2.png", "550 Kr.");
        Item item_item_2 = new Item("Girl Andrew Brophy Design", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_8.png", "450 Kr.");
        Item item_item_3 = new Item("Baker Old School Red", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_3.png", "500 Kr.");
        Item item_item_4 = new Item("Paul Rodriguez Goku", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_7.png", "600 Kr.");
        Item item_item_5 = new Item("Plan B Team OG Skateboard", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_1.png", "550 Kr.");


        itemList.add(item_item_1);
        itemList.add(item_item_2);
        itemList.add(item_item_3);
        itemList.add(item_item_4);
        itemList.add(item_item_5);

        itemRecycleAdapter = new ItemRecycleAdapter(this, itemList);
        recyclerViewDecks.setHasFixedSize(true);
        recyclerViewDecks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewDecks.setAdapter(itemRecycleAdapter);
        itemRecycleAdapter.notifyDataSetChanged();

        /* ----------------------------------------------------------------------------------------------------------------------------- */

        // TODO setup button path (it opens multiple HomePages and this might cause problems)
        home = findViewById(R.id.logo);
        home.setOnClickListener(view -> {
            if (this != HomePageActivity.this) {
                startActivity(new Intent(this, HomePageActivity.class));
            }
        });

    }
}