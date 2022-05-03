package com.example.skateshopapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skateshopapp.R;
import com.example.skateshopapp.adapter.DecksRecycleAdapter;
import com.example.skateshopapp.adapter.NewReleaseRecyclerAdapter;
import com.example.skateshopapp.model.Decks;
import com.example.skateshopapp.model.NewRelease;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNewRelease, recyclerViewDecks;
    private NewReleaseRecyclerAdapter newReleaseRecyclerAdapter;
    private DecksRecycleAdapter decksRecycleAdapter;
    private List<NewRelease> newReleaseList;
    private List<Decks> decksList;

    private ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        decksList = new ArrayList<>();

        Decks decks_item_1 = new Decks("Primitive X Rick and Morty Exclusive Edition", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_2.png", "550 Kr.");
        Decks decks_item_2 = new Decks("Girl Andrew Brophy Design", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_8.png", "450 Kr.");
        Decks decks_item_3 = new Decks("Baker Old School Red", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_3.png", "500 Kr.");
        Decks decks_item_4 = new Decks("Paul Rodriguez Goku", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_7.png", "600 Kr.");
        Decks decks_item_5 = new Decks("Plan B Team OG Skateboard", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decksfull/deck_1.png", "550 Kr.");


        decksList.add(decks_item_1);
        decksList.add(decks_item_2);
        decksList.add(decks_item_3);
        decksList.add(decks_item_4);
        decksList.add(decks_item_5);

        decksRecycleAdapter = new DecksRecycleAdapter(this, decksList);
        recyclerViewDecks.setHasFixedSize(true);
        recyclerViewDecks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewDecks.setAdapter(decksRecycleAdapter);
        decksRecycleAdapter.notifyDataSetChanged();

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