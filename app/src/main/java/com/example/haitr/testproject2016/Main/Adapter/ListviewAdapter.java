package com.example.haitr.testproject2016.Main.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haitr.testproject2016.Main.Main.RegisterActivity;
import com.example.haitr.testproject2016.R;
import com.like.LikeButton;
import com.like.OnLikeListener;

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
        holder.img.setImageResource(bar.getImage());
        holder.txtName.setText(bar.getsName());
        holder.txtPrice.setText(bar.getsPrice());
        holder.txtDetailed.setText(bar.getsDetailed());
//        holder.btnLike.onClick(bar.getBtnLike());
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
        public TextView txtName, txtPrice, txtDetailed;

        public RecyclerViewHolder(final View itemView) {
            super(itemView);
            context = itemView.getContext();
           // btnLike = (LikeButton) itemView.findViewById(R.id.star_button);
            img = (ImageView) itemView.findViewById(R.id.imag_Bar);
            txtName = (TextView) itemView.findViewById(R.id.text_Name);
            txtPrice = (TextView) itemView.findViewById(R.id.text_Price);
            txtDetailed = (TextView) itemView.findViewById(R.id.text_Detailed);
            txtPrice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s = getBarName(ListviewAdapter.getBarArrayList());
                    Log.v("bar name: ",s);
                    Intent registerIntent = new Intent(context,RegisterActivity.class);
                    context.startActivity(registerIntent);


                }
            });

//            btnLike.setOnLikeListener(new OnLikeListener() {
//                @Override
//                public void liked(LikeButton likeButton) {
//
//                }
//
//                @Override
//                public void unLiked(LikeButton likeButton) {
//
//                }
//            });
        }
        public String getBarName(ArrayList<Bar> arrayList){
            Bar bar = arrayList.get(getAdapterPosition());
            return bar.getsName();
        }
    }
}
