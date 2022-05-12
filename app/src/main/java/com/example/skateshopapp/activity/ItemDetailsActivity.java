package com.example.skateshopapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.skateshopapp.R;
import com.example.skateshopapp.helpers.ImageLoadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ItemDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageLoadTask loadTask;
    private ImageView backButton, itemImage;
    private TextView itemDetailsName, itemDetailsPrice, itemDetailsSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        backButton = findViewById(R.id.back_button_item_details);
        backButton.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        itemDetailsName = findViewById(R.id.itemDetailsName);
        itemDetailsPrice = findViewById(R.id.itemDetailsPrice);
        itemDetailsSize = findViewById(R.id.itemDetailsSize);
        itemImage = findViewById(R.id.itemDetailsImage);

        if (bundle != null) {
            String name, price, size, image;

            name = bundle.getString("name");
            price = bundle.getString("price");
            size = bundle.getString("size");
            image = bundle.getString("photo");


            itemDetailsName.setText(name);
            itemDetailsPrice.setText(price);
            itemDetailsSize.setText(size);
            Picasso.get().load(image).into(itemImage);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_button_item_details) {
            finish();
        }
    }
}