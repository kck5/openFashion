package com.bmlmunjal.openfashion;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class homeCardAdaptorHomeFragment extends RecyclerView.Adapter<homeCardAdaptorHomeFragment.homeViewHolder> {

    Context context;
    ArrayList<HomeCard> homeCardArrayList;

    public homeCardAdaptorHomeFragment(Context context, ArrayList<HomeCard> homeCardArrayList){
        this.context = context;
        this.homeCardArrayList = homeCardArrayList;
    }
    @NonNull
    @Override
    public homeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.home_card_item_list,parent, false);

        return new homeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull homeViewHolder holder, int position) {
        HomeCard homeCard= homeCardArrayList.get(position);
        holder.textViewName1.setText(homeCard.item1NameString);
        holder.textViewName2.setText(homeCard.item2NameString);
        holder.textViewPrice1.setText(homeCard.item1PriceString);
        holder.textViewPrice2.setText(homeCard.item2PriceString);
        holder.imageViewItemPhoto1.setImageResource(homeCard.item1Image);
        holder.imageViewItemPhoto2.setImageResource(homeCard.item2Image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(context,ProductActivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeCardArrayList.size();
    }

    public static class homeViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName1,textViewName2,textViewPrice1,textViewPrice2;
        ImageView imageViewItemPhoto1,imageViewItemPhoto2;
        public homeViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName1= itemView.findViewById(R.id.one_text);
            textViewName2= itemView.findViewById(R.id.two_text);
            textViewPrice1= itemView.findViewById(R.id.one_price);
            textViewPrice2= itemView.findViewById(R.id.two_price);
            imageViewItemPhoto1= itemView.findViewById(R.id.pref1);
            imageViewItemPhoto2= itemView.findViewById(R.id.pref2);
        }
    }
}
