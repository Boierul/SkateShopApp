package com.example.skateshopapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.skateshopapp.R;
import com.example.skateshopapp.activity.HomePageActivity;
import com.example.skateshopapp.activity.MainActivity;
import com.example.skateshopapp.activity.ProfileActivity;
import com.example.skateshopapp.fragment.ItemDetailsFragment;
import com.example.skateshopapp.fragment.OrdersFragment;
import com.example.skateshopapp.fragment.SettingsFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ItemDetailsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_profile, new ItemDetailsFragment()).commit();

    }
}