package com.bmlmunjal.openfashion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class cartDataAdapter extends RecyclerView.Adapter<cartDataAdapter.ViewHolder> implements View.OnClickListener{

    CartData[] cartData;
    Context context;
    public cartDataAdapter(CartData[] cartData,ActivityCart activity){
        this.cartData=cartData;
        this.context=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cart_item_list,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CartData cartDataList= cartData[position];
        holder.itemName.setText(cartDataList.getItemName());
        holder.itemPrice.setText(cartDataList.getItemPrice());
        holder.itemImage.setImageResource(cartDataList.getItemImage());

//        holder.itemSize.setAdapter(cartDataList.getItemSize());

    }

    @Override
    public int getItemCount() {
        return cartData.length;
    }

    @Override
    public void onClick(View view) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;
        TextView itemName,itemPrice;
        Spinner itemSize,noOfItems;
        Button deleteItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImageCartCard);
            itemName=itemView.findViewById(R.id.itemNameCartCard);
            itemPrice=itemView.findViewById(R.id.itemPriceCartCard);
            itemSize=itemView.findViewById(R.id.spinnerSize);
            noOfItems=itemView.findViewById(R.id.spinnerItem);
        }
    }
}
