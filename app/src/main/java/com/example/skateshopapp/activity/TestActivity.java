package com.example.skateshopapp.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.skateshopapp.R;
import com.example.skateshopapp.model.Item;
import com.example.skateshopapp.viewmodel.ItemViewModel;

public class TestActivity extends AppCompatActivity {

    private TextView textView;
    private ItemViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView = findViewById(R.id.textViewResult);

        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        viewModel.getAllNewArrived().observe(this, newReleases -> {
            if (!newReleases.isEmpty()) {
                textView.setText("");
                for (Item nr : newReleases) {
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