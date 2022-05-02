package com.example.skateshopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView videoView;
    private Button button;
    private TextView resetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make to run your application only in portrait mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main_activity);

        resetPass = findViewById(R.id.forgetPasswordTextView);
        resetPass.setOnClickListener(this);

        videoView = findViewById(R.id.startupVideo);
        button = findViewById(R.id.loginButton);

        String path = "android.resource://com.example.skateshopapp/" + R.raw.sratuo_video_slowed;
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
        }
    }
}
