package com.example.skateshopapp.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skateshopapp.R;
import com.example.skateshopapp.adapter.AccessoriesRecycleAdapter;
import com.example.skateshopapp.adapter.DeckRecycleAdapter;
import com.example.skateshopapp.adapter.NewReleaseRecyclerAdapter;
import com.example.skateshopapp.adapter.TruckRecycleAdapter;
import com.example.skateshopapp.fragment.OrdersFragment;
import com.example.skateshopapp.fragment.SettingsFragment;
import com.example.skateshopapp.model.Item;
import com.example.skateshopapp.model.User;
import com.example.skateshopapp.viewmodel.ItemViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /* TODO implement search functionality */

    private RecyclerView recyclerViewNewRelease, recyclerViewDecks, recyclerViewTrucks, recyclerViewAccessories;

    private NewReleaseRecyclerAdapter newReleaseRecyclerAdapter;
    private DeckRecycleAdapter deckRecycleAdapter;
    private TruckRecycleAdapter truckRecycleAdapter;
    private AccessoriesRecycleAdapter accessoriesRecycleAdapter;

    private List<Item> newReleaseList, decksList, trucksList, accessoriesList;

    private ItemViewModel itemViewModel;

    private DrawerLayout drawer;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private TextView userName, userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_home_page);

        /* ----------------------------------------------------------------------------------------------------------------------------- */
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
        /* Navigation Drawer */

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.red_primary_darker));
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /* ----------------------------------------------------------------------------------------------------------------------------- */
        /* Nav Drawer data binding */
        View headerLayout = navigationView.getHeaderView(0);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        reference = FirebaseDatabase
                .getInstance()
                .getReference("Users").child(userID);

        userName = headerLayout.findViewById(R.id.profileName);
        userEmail = headerLayout.findViewById(R.id.profileEmail);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                Log.d("HOMEPAGE DATA BINDING - USER ID",userID);
                Log.d("HOMEPAGE DATA BINDING - USER",userProfile.getEmail());

                String firstName = userProfile.getFirstName();
                String lastName = userProfile.getLastName();
                String fullName = firstName + " " + lastName;

                Log.d("HOMEPAGE DATA BINDING - USER",fullName);

                String email = userProfile.getEmail();

                if (userProfile != null) {
                    userName.setText(fullName);
                    userEmail.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePageActivity.this,
                        "Can't fetch user data from server",
                        Toast.LENGTH_SHORT).show();
            }
        });

        /* ----------------------------------------------------------------------------------------------------------------------------- */
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_home) {
            Intent intent = new Intent(this, HomePageActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else if (item.getItemId() == R.id.nav_settings) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SettingsFragment()).commit();
        } else if (item.getItemId() == R.id.nav_orders) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new OrdersFragment()).commit();
        } else if ((item.getItemId() == R.id.nav_profile)) {
            startActivity(new Intent(HomePageActivity.this, ProfileActivity.class));
        } else if (item.getItemId() == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }
}