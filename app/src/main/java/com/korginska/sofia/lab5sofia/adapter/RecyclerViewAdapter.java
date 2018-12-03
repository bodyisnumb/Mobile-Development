package com.korginska.sofia.lab5sofia.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.korginska.sofia.lab5sofia.R;
import com.korginska.sofia.lab5sofia.model.Drink;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Drink> mDrinks;

    public RecyclerViewAdapter(ArrayList<Drink> drinks) {
        mDrinks = drinks;
        Log.i("TAG", "Constructor");
        Log.i("TAG", "RSIZE:" + Integer.toString(mDrinks.size()));
        for (Drink drink : mDrinks) {
            Log.i("TAG", drink.getIdDrink());
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mTextViewHeader.setText(mDrinks.get(i).getStrDrink());
        viewHolder.mTextViewDesc.setText(
                mDrinks.get(i).getStrIngredient1() + "\n"
                + mDrinks.get(i).getStrIngredient2() + "\n"
                + mDrinks.get(i).getStrIngredient3() + "\n"
                + mDrinks.get(i).getStrIngredient4() + "\n"
                + mDrinks.get(i).getStrIngredient5());
        Picasso.get().load(mDrinks.get(i).getStrDrinkThumb()).resize(150, 150).centerCrop().into(viewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mDrinks.size();
    }

    public void addAll(List<Drink> drinks) {
        mDrinks.addAll(drinks);
    }

    public void clear() {
        mDrinks.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextViewHeader;
        TextView mTextViewDesc;
        RelativeLayout mRecyclerView;

        ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_image_view);
            mTextViewHeader = itemView.findViewById(R.id.item_image_header);
            mTextViewDesc = itemView.findViewById(R.id.item_image_description);
            mRecyclerView = itemView.findViewById(R.id.recycler_view);
        }
    }
}
