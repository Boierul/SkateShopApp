package com.example.skateshopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.skateshopapp.activity.ItemDetailsActivity;
import com.example.skateshopapp.R;
import com.example.skateshopapp.model.Item;

import java.util.List;

public class NewReleaseRecyclerAdapter extends RecyclerView.Adapter<NewReleaseRecyclerAdapter.NewReleaseViewHolder> {

    private Context context;
    private List<Item> itemList;

    public NewReleaseRecyclerAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public NewReleaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.new_release_recycler_items, parent, false);

        final NewReleaseViewHolder viewHolder = new NewReleaseViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewReleaseViewHolder holder, int position) {
        holder.itemName.setText(itemList.get(position).getName());
        holder.size.setText(itemList.get(position).getSize());
        holder.price.setText(itemList.get(position).getPrice());

        Glide.with(context).load(itemList.get(position).getImageURL()).into(holder.itemImage);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ItemDetailsActivity.class);
            intent.putExtra("name", itemList.get(position).getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public static class NewReleaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView itemImage;
        TextView price, itemName, size;
        LinearLayout linear_layout_new_release;

        public NewReleaseViewHolder(@NonNull View itemView) {
            super(itemView);
            linear_layout_new_release = itemView.findViewById(R.id.new_release);
            itemImage = itemView.findViewById(R.id.card_image);
            itemName = itemView.findViewById(R.id.itemNameTextView);
            size = itemView.findViewById(R.id.sizeTextView);
            price = itemView.findViewById(R.id.priceTextView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}


