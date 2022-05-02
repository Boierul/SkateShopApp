package com.example.skateshopapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.skateshopapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView videoView;
    private Button button;
    private TextView resetPass, registerUser, noSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make to run your application only in portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main_activity);

        noSignUp = findViewById(R.id.continueWithoutSignupTextView);
        noSignUp.setOnClickListener(this);

        registerUser = findViewById(R.id.registerTextView);
        registerUser.setOnClickListener(this);

        resetPass = findViewById(R.id.forgetPasswordTextView);
        resetPass.setOnClickListener(this);

        videoView = findViewById(R.id.startupVideo);
        button = findViewById(R.id.loginButton);

        String path = "android.resource://com.example.skateshopapp/" + R.raw.startup_video_slowed;
        Uri uri = Uri.parse(path);

        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(mediaPlayer -> {
            mediaPlayer.start();
        });

        videoView.setOnCompletionListener(mediaPlayer -> mediaPlayer.start());

        button.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, RegisterActivity.class)));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.forgetPasswordTextView) {
            startActivity(new Intent(this, ForgetPasswordActivity.class));
        } else if (view.getId() == R.id.registerTextView) {
            startActivity(new Intent(this, RegisterActivity.class));
        } else if (view.getId() == R.id.continueWithoutSignupTextView) {
            startActivity(new Intent(this, HomePageActivity.class));
        }
    }
}
