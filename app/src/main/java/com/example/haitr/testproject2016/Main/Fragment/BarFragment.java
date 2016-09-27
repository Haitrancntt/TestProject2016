package com.example.haitr.testproject2016.Main.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haitr.testproject2016.Main.Adapter.Bar;
import com.example.haitr.testproject2016.Main.Adapter.ListviewAdapter;
import com.example.haitr.testproject2016.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarFragment extends Fragment {
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;
    private String[] sName, sPrice, sDetailed;
    private int[] Img = {R.drawable.bar, R.drawable.bar, R.drawable.bar};
    private ArrayList<Bar> barArrayList = new ArrayList<Bar>();

    public BarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bar, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        sName = new String[]{"Bar1", "Bar2", "Bar3"};
        sPrice = new String[]{"2000$", "60000$", "52310$"};
        sDetailed = new String[]{"sssssssssssssss", "sfffffffffffffffffff", "fgh"};
        for (int i = 0; i < 3; i++) {
            Bar bar = new Bar(Img[i], sName[i], sPrice[i], sDetailed[i]);
            barArrayList.add(bar);
        }
        adapter = new ListviewAdapter(barArrayList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
