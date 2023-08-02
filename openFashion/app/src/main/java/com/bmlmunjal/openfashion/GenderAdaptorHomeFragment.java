package com.bmlmunjal.openfashion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class GenderAdaptorHomeFragment extends RecyclerView.Adapter<GenderAdaptorHomeFragment.GenderViewHolder> {

    Context context;
    ArrayList<GenderClass> newsArrayList;

    public GenderAdaptorHomeFragment(Context context, ArrayList<GenderClass> newsArrayList){
        this.context=context;
        this.newsArrayList=newsArrayList;

    }
    @NonNull
    @Override
    public GenderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.gender_item_list,parent,false);
        return new GenderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GenderViewHolder holder, int position) {
        GenderClass genderClass = newsArrayList.get(position);
        holder.textViewGender.setText(genderClass.genderText);
        holder.shapeableImageViewGenderPhotos.setImageResource(genderClass.genderImage);
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public static class GenderViewHolder extends RecyclerView.ViewHolder{

        ShapeableImageView shapeableImageViewGenderPhotos;
        TextView textViewGender;

        public GenderViewHolder(@NonNull View itemView) {
            super(itemView);
            shapeableImageViewGenderPhotos = itemView.findViewById(R.id.genderPicture);
            textViewGender = itemView.findViewById(R.id.textViewGender);
        }
    }
}
