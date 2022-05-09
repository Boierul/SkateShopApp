package com.example.skateshopapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.skateshopapp.R;
import com.example.skateshopapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView backButton;
    private EditText firstName, lastName, street, country, postcode, email, password;
    private Button registerUserButton;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.register_activity);

        backButton = findViewById(R.id.back_button_register);
        backButton.setOnClickListener(this);

        firstName = findViewById(R.id.firstnameEditText);
        lastName = findViewById(R.id.lastnameEditText);
        street = findViewById(R.id.streetEditTextRegister);
        country = findViewById(R.id.countryEditText);
        postcode = findViewById(R.id.postalcodeEditText);
        email = findViewById(R.id.emailEdiTextRegister);
        password = findViewById(R.id.passwordEditText);
        progressBar = findViewById(R.id.progressBarRegister);

        registerUserButton = findViewById(R.id.createAccountButton);
        registerUserButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_button_register) {
            finish();
        } else if (view.getId() == R.id.createAccountButton) {
            registerUser();
        }
    }

    private void registerUser() {
        String firstNameTemp = firstName.getText().toString().trim();
        String lastNameTemp = lastName.getText().toString().trim();
        String streetTemp = street.getText().toString().trim();
        String countryTemp = country.getText().toString().trim();
        String postCodeTemp = postcode.getText().toString().trim();
        String emailTemp = email.getText().toString().trim();
        String passwordTemp = password.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(emailTemp).matches()) {
            if (emailTemp.isEmpty()) {
                email.setError("Email field cannot be empty!");
                email.requestFocus();
                return;
            }

            email.setError("Please provide a valid email");
            email.requestFocus();
            return;

        } else if (passwordTemp.isEmpty() && passwordTemp.length() < 6) {
            password.setError("Password field should contain " +
                    "at least 6 characters");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(emailTemp, passwordTemp)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(firstNameTemp, lastNameTemp, streetTemp, countryTemp, postCodeTemp, emailTemp);

                            FirebaseDatabase.getInstance("https://skate-application-default-rtdb.firebaseio.com")
                                    .getReference("Users")
                                    .child(FirebaseAuth.getInstance()
                                            .getCurrentUser()
                                            .getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this,
                                                "User registered successfully",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(RegisterActivity.this,
                                                "Failed to register user",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                        } else {
                            Toast.makeText(RegisterActivity.this,
                                    "Failed to register User",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}