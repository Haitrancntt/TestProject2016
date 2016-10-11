package com.example.haitr.testproject2016.Main.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haitr.testproject2016.Main.Detailed.BarDetailedActivity;
import com.example.haitr.testproject2016.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by haitr on 9/27/2016.
 */

public class ListviewAdapter extends RecyclerView.Adapter<ListviewAdapter.RecyclerViewHolder> {
    private static ArrayList<Bar> barArrayList = new ArrayList<Bar>();

    public ListviewAdapter(ArrayList<Bar> barArrayList) {
        this.barArrayList = barArrayList;
    }

    private static Context context;


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bar, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(itemView);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Bar bar = barArrayList.get(position);
        holder.txtName.setText(bar.getName());
        holder.txtAddress.setText(bar.getAddress());
        holder.txtTime.setText("Open time: \n" + bar.getTime());
        Picasso.with(context).load(bar.getPicture()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return barArrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public static ArrayList<Bar> getBarArrayList() {
        return barArrayList;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView txtName, txtTime, txtAddress;
        public String sId;

        public RecyclerViewHolder(final View itemView) {
            super(itemView);
            context = itemView.getContext();
            img = (ImageView) itemView.findViewById(R.id.image_Bar);
            txtName = (TextView) itemView.findViewById(R.id.textView_Bar_Name);
            txtTime = (TextView) itemView.findViewById(R.id.textView_Bar_Time);
            txtAddress = (TextView) itemView.findViewById(R.id.textView_Bar_Address);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BarDetailedActivity.class);
                    sId = getBarName(barArrayList);
                    Bundle bundle = new Bundle();
                    bundle.putString("ID", sId);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });
        }

        public String getBarName(ArrayList<Bar> arrayList) {
            Bar bar = arrayList.get(getAdapterPosition());
            return bar.getId();
        }
    }
}
