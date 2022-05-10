package com.example.skateshopapp.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.skateshopapp.R;
import com.example.skateshopapp.model.NewRelease;
import com.example.skateshopapp.viewmodel.NewReleaseViewModel;

public class TestActivity extends AppCompatActivity {

    private TextView textView;
    private NewReleaseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView = findViewById(R.id.textViewResult);

        viewModel = new ViewModelProvider(this).get(NewReleaseViewModel.class);
        viewModel.getAllNewReleases().observe(this, newReleases -> {
            if (!newReleases.isEmpty()) {
                textView.setText("");
                for (NewRelease nr : newReleases) {
                    String content = "";
                    content += "Name:  " + nr.getName() + "\n";
                    content += "ImageURL:  " + nr.getImageURL() + "\n";
                    content += "Size:  " + nr.getSize() + "\n";
                    content += "Price:  " + nr.getPrice() + "\n\n";
                    textView.append(content);
                }
            } else {
                textView.setText("Empty");
            }
        });

    }
}