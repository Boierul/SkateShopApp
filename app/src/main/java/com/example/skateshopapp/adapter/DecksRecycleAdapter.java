package com.example.skateshopapp.adapter;

import android.content.Context;
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
import com.example.skateshopapp.model.Decks;

import java.util.List;

public class DecksRecycleAdapter extends RecyclerView.Adapter<DecksRecycleAdapter.DecksViewHolder>  {


    private Context context;
    private List<Decks> decksList;

    public DecksRecycleAdapter(Context context, List<Decks> decksList) {
        this.context = context;
        this.decksList = decksList;
    }

    @NonNull
    @Override
    public DecksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.decks_recycler_items, parent, false);

        final DecksViewHolder viewHolder = new DecksViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DecksViewHolder holder, int position) {
        holder.itemName.setText(decksList.get(position).getName());
        holder.price.setText(decksList.get(position).getPrice());

        Glide.with(context).load(decksList.get(position).getImageURL()).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return decksList.size();
    }


    public static class DecksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView itemImage;
        TextView price, itemName;
        LinearLayout linear_layout_decks;

        public DecksViewHolder(@NonNull View itemView) {
            super(itemView);
            linear_layout_decks = itemView.findViewById(R.id.decks_with_details);
            itemImage = itemView.findViewById(R.id.deck_image);
            itemName = itemView.findViewById(R.id.deck_details);
            price = itemView.findViewById(R.id.priceDetailsTextView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
