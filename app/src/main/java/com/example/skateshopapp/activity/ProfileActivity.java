package com.example.skateshopapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skateshopapp.R;
import com.example.skateshopapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView firstNameTextView, lastNameTextView, streetTextView, countryTextView, postcodeTextView, emailTextView;
    private ImageView backButton;
    private Button logOutButton;

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        backButton = findViewById(R.id.back_button_profile);
        backButton.setOnClickListener(this);
        logOutButton = findViewById(R.id.logoutButton);
        logOutButton.setOnClickListener(this);


        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        reference = FirebaseDatabase
                .getInstance()
                .getReference("Users").child(userID);

        firstNameTextView = findViewById(R.id.firstNameTextViewProfile);
        lastNameTextView = findViewById(R.id.lastNameTextViewProfile);
        streetTextView = findViewById(R.id.streetTextViewProfile);
        countryTextView = findViewById(R.id.countryTextViewProfile);
        postcodeTextView = findViewById(R.id.postcodeTextViewProfile);
        emailTextView = findViewById(R.id.emailTextViewProfile);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                String firstName = userProfile.getFirstName();
                String lastName = userProfile.getLastName();
                String street = userProfile.getStreetAddress();
                String country = userProfile.getCountry();
                String postcode = userProfile.getPostCode();
                String email = userProfile.getEmail();

                if (userProfile != null) {
                    firstNameTextView.setText(firstName);
                    lastNameTextView.setText(lastName);
                    streetTextView.setText(street);
                    countryTextView.setText(country);
                    postcodeTextView.setText(postcode);
                    emailTextView.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this,
                        "Can't fetch the data from server",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_button_profile) {
            finish();
        } else if (view.getId() == R.id.logoutButton) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        }
    }
}