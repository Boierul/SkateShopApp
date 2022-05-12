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
import com.example.skateshopapp.R;
import com.example.skateshopapp.activity.ItemDetailsActivity;
import com.example.skateshopapp.model.Item;

import java.util.List;

public class AccessoriesRecycleAdapter extends RecyclerView.Adapter<AccessoriesRecycleAdapter.AccessoriesViewHolder>{

    private Context context;
    private List<Item> accessoriesList;

    public AccessoriesRecycleAdapter(Context context, List<Item> accessoriesList) {
        this.context = context;
        this.accessoriesList = accessoriesList;
    }

    @NonNull
    @Override
    public AccessoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.accessories_recycler_view, parent, false);

        final AccessoriesViewHolder viewHolder = new AccessoriesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AccessoriesViewHolder holder, int position) {
        holder.itemName.setText(accessoriesList.get(position).getName());
        holder.price.setText(accessoriesList.get(position).getPrice());

        Glide.with(context).load(accessoriesList.get(position).getImageURL()).into(holder.itemImage);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ItemDetailsActivity.class);
            intent.putExtra("name", accessoriesList.get(position).getName());
            intent.putExtra("price", accessoriesList.get(position).getPrice());
            intent.putExtra("size", accessoriesList.get(position).getSize());
            intent.putExtra("photo", accessoriesList.get(position).getImageURL());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return accessoriesList.size();
    }

    public static class AccessoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView itemImage;
        TextView price, itemName;
        LinearLayout linear_layout_accessories;


        public AccessoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            linear_layout_accessories = itemView.findViewById(R.id.accessories_container);
            itemImage = itemView.findViewById(R.id.accessoriesImage);
            itemName = itemView.findViewById(R.id.itemNameAccessories);
            price = itemView.findViewById(R.id.itemPriceTextViewImageAccessories);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
