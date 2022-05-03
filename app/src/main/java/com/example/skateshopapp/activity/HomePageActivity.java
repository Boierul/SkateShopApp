package com.example.skateshopapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.skateshopapp.R;
import com.example.skateshopapp.adapter.NewReleaseRecyclerAdapter;
import com.example.skateshopapp.model.NewRelease;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewReleaseRecyclerAdapter newReleaseRecyclerAdapter;
    private List<NewRelease> newReleaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recyclerView = findViewById(R.id.recyclerView);
        newReleaseList = new ArrayList<>();

        NewRelease item_1 = new NewRelease("Carlos Ribeiro Frieza ", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decks/card_photo_1.png", "8.5", "500 kr.");
        NewRelease item_2 = new NewRelease("Miles Silvas Krillin", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decks/card_photo_2.png", "8.0", "500 kr.");
        NewRelease item_3 = new NewRelease("Tiago Lemos Cell", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decks/card_photo_3.png", "7.75", "550 kr.");
        NewRelease item_4 = new NewRelease("Paul Rodriguez Goku", "https://skateappandroid.s3.eu-west-2.amazonaws.com/decks/card_photo_4.png", "8.25", "600 kr.");

        newReleaseList.add(item_1);
        newReleaseList.add(item_2);
        newReleaseList.add(item_3);
        newReleaseList.add(item_4);

        newReleaseRecyclerAdapter = new NewReleaseRecyclerAdapter(this, newReleaseList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(newReleaseRecyclerAdapter);
        newReleaseRecyclerAdapter.notifyDataSetChanged();

//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_bigspin_logo_mk1);
    }
}