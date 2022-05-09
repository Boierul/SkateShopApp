package com.example.skateshopapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.skateshopapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView backButton;
    private EditText emailEditText;
    private Button resetButton;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.forget_password_activity);

        emailEditText = findViewById(R.id.emailEditTextReset);
        progressBar = findViewById(R.id.progressBarForgotPassword);

        backButton = findViewById(R.id.back_button_forgot);
        backButton.setOnClickListener(this);

        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_button_forgot)
        {
            finish();
        } else if (view.getId() == R.id.resetButton)
        {
            resetPassword();
        }
    }

    private void resetPassword() {
        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Please provide a valid email");
            emailEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgetPasswordActivity.this,
                                "Check your email to reset your password",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MainActivity.class));
                    } else {
                        Toast.makeText(ForgetPasswordActivity.this,
                                "The email is not attached to an account",
                                Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                    progressBar.setVisibility(View.GONE);
                });
    }
}