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
import com.example.skateshopapp.model.Item;

import java.util.List;

public class AccessoriesRecycleAdapter extends RecyclerView.Adapter<AccessoriesRecycleAdapter.AccessoriesViewHolder>{

    private Context context;
    private List<Item> itemList;

    public AccessoriesRecycleAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public AccessoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_recycler_view, parent, false);

        final AccessoriesViewHolder viewHolder = new AccessoriesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AccessoriesViewHolder holder, int position) {
        holder.itemName.setText(itemList.get(position).getName());
        holder.price.setText(itemList.get(position).getPrice());

        Glide.with(context).load(itemList.get(position).getImageURL()).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class AccessoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView itemImage;
        TextView price, itemName;
        LinearLayout linear_layout_trucks;


        public AccessoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            linear_layout_trucks = itemView.findViewById(R.id.item_container);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            price = itemView.findViewById(R.id.itemPriceTextView);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
