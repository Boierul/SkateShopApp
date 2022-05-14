package com.example.skateshopapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.skateshopapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView videoView;

    private TextView resetPass, registerUser, fetchDataFromServer;
    private EditText emailEditText, passwordEditText;
    private Button button;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make to run your application only in portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main_activity);

        fetchDataFromServer = findViewById(R.id.testDataTextView);
        fetchDataFromServer.setOnClickListener(this);

        registerUser = findViewById(R.id.registerTextView);
        registerUser.setOnClickListener(this);

        resetPass = findViewById(R.id.forgetPasswordTextView);
        resetPass.setOnClickListener(this);

        button = findViewById(R.id.loginButton);
        button.setOnClickListener(this);

        videoView = findViewById(R.id.startupVideo);
        String path = "android.resource://com.example.skateshopapp/" + R.raw.startup_video_slowed;
        Uri uri = Uri.parse(path);

        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(mediaPlayer -> {
            mediaPlayer.start();
        });

        videoView.setOnCompletionListener(mediaPlayer -> mediaPlayer.start());

        emailEditText = findViewById(R.id.emailEditTextMain);
        passwordEditText = findViewById(R.id.passwordEditTextMain);
        progressBar = findViewById(R.id.progressBarMain);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.forgetPasswordTextView) {
            startActivity(new Intent(this, ForgetPasswordActivity.class));
        } else if (view.getId() == R.id.registerTextView) {
            startActivity(new Intent(this, RegisterActivity.class));
        } else if (view.getId() == R.id.testDataTextView) {
            /* TODO to modify it
            *             startActivity(new Intent(this, HomePageActivity.class));
             * */
            startActivity(new Intent(this, TestActivity.class));
        } else if (view.getId() == R.id.loginButton) {
            userLogin();
        }
    }

    private void userLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (email.isEmpty()) {
                emailEditText.setError("Email field cannot be empty!");
                emailEditText.requestFocus();
                return;
            }
            emailEditText.setError("Please provide a valid email");
            emailEditText.requestFocus();
            return;
        } else if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
        } else if (password.length() < 6) {
            passwordEditText.setError("Password field should contain " +
                    "at least 6 characters");
            passwordEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Check if the email was verified
                        FirebaseUser user = FirebaseAuth.getInstance()
                                .getCurrentUser();

                        if (user.isEmailVerified()) {
                            startActivity(new Intent(MainActivity.this, HomePageActivity.class));
                        } else {
                            user.sendEmailVerification();
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this,
                                    "Check your email to verify your account",
                                    Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this,
                                "Failed to log in. Please check your credentials.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
