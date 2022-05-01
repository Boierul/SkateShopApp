package com.example.skateshopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
