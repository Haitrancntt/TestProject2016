package com.example.haitr.testproject2016.Main.FavoriteFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.haitr.testproject2016.Main.Adapter.Bar;
import com.example.haitr.testproject2016.Main.Adapter.ListviewAdapter;
import com.example.haitr.testproject2016.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavBarFragment extends Fragment {
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;
    private ArrayList<Bar> barArrayList = new ArrayList<Bar>();

    public FavBarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bar, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        adapter = new ListviewAdapter(barArrayList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
