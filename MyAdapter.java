package com.trolly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList cusid_id;
    private ArrayList barcode_id;
    private ArrayList title_id;
    private ArrayList price_id;
    private ArrayList quantity_id;

    public MyAdapter(Context context,ArrayList cusid_id,ArrayList barcode_id,ArrayList title_id,ArrayList price_id,ArrayList Quantity_id){
        this.context = context;
        this.cusid_id = cusid_id;
        this.barcode_id = barcode_id;
        this.title_id = title_id;
        this.price_id = price_id;
        this.quantity_id = Quantity_id;
    }
    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.barcode_idi.setText(String.valueOf(barcode_id.get(position)));
        holder.title_idi.setText(String.valueOf(title_id.get(position)));
        holder.price_idi.setText(String.valueOf(price_id.get(position)));
        holder.quantity_idi.setText(String.valueOf(quantity_id.get(position)));

    }

    @Override
    public int getItemCount() {
        return barcode_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView barcode_idi,title_idi,price_idi,quantity_idi;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            barcode_idi = itemView.findViewById(R.id.textbarcode);
            title_idi = itemView.findViewById(R.id.textIname);
            price_idi = itemView.findViewById(R.id.textprice);
            quantity_idi = itemView.findViewById(R.id.textquantity);
        }
    }
}
