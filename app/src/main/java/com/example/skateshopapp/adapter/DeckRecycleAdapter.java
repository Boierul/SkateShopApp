package com.example.skateshopapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.skateshopapp.R;
import com.example.skateshopapp.activity.ItemDetailsActivity;
import com.example.skateshopapp.model.Item;

import java.util.List;

public class DeckRecycleAdapter extends RecyclerView.Adapter<DeckRecycleAdapter.DeckViewHolder> {


    private Context context;
    private List<Item> deckList;

    public DeckRecycleAdapter(Context context, List<Item> deckList) {
        this.context = context;
        this.deckList = deckList;
    }

    @NonNull
    @Override
    public DeckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.deck_recycler_view, parent, false);

        final DeckViewHolder viewHolder = new DeckViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeckViewHolder holder, int position) {
        holder.itemName.setText(deckList.get(position).getName());
        holder.price.setText(deckList.get(position).getPrice());
        holder.price.setText(deckList.get(position).getPrice());

        Glide.with(context).load(deckList.get(position).getImageURL()).into(holder.itemImage);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ItemDetailsActivity.class);
            intent.putExtra("name", deckList.get(position).getName());
            intent.putExtra("price", deckList.get(position).getPrice());
            intent.putExtra("size", deckList.get(position).getSize());
            intent.putExtra("photo", deckList.get(position).getImageURL());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return deckList.size();
    }


    public static class DeckViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView itemImage;
        TextView price, itemName;
        LinearLayout linear_layout_decks;

        public DeckViewHolder(@NonNull View itemView) {
            super(itemView);
            linear_layout_decks = itemView.findViewById(R.id.deck_container);
            itemImage = itemView.findViewById(R.id.itemImageDeck);
            itemName = itemView.findViewById(R.id.itemNameDeck);
            price = itemView.findViewById(R.id.itemPriceTextViewDeck);
        }

        @Override
        public void onClick(View view) {
        }
    }
}
