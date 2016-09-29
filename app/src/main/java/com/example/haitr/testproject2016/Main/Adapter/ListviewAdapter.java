package com.example.haitr.testproject2016.Main.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haitr.testproject2016.R;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;

/**
 * Created by haitr on 9/27/2016.
 */

public class ListviewAdapter extends RecyclerView.Adapter<ListviewAdapter.RecyclerViewHolder> {
    private ArrayList<Bar> barArrayList = new ArrayList<Bar>();

    public ListviewAdapter(ArrayList<Bar> barArrayList) {
        this.barArrayList = barArrayList;
    }

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

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView txtName, txtPrice, txtDetailed;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
           // btnLike = (LikeButton) itemView.findViewById(R.id.star_button);
            img = (ImageView) itemView.findViewById(R.id.imag_Bar);
            txtName = (TextView) itemView.findViewById(R.id.text_Name);
            txtPrice = (TextView) itemView.findViewById(R.id.text_Price);
            txtDetailed = (TextView) itemView.findViewById(R.id.text_Detailed);

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
    }
}
