package com.example.haitr.testproject2016.Main.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.haitr.testproject2016.Main.Adapter.Bar;
import com.example.haitr.testproject2016.Main.Adapter.ListviewAdapter;
import com.example.haitr.testproject2016.R;
import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.like.LikeButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarFragment extends Fragment {
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;
    private ArrayList<Bar> barArrayList = new ArrayList<Bar>();
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference("bar");
    public BarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bar, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        /*for (int i = 0; i < 3; i++) {
            Bar bar = new Bar(Img[i], sName[i], sPrice[i], sDetailed[i]);
            barArrayList.add(bar);
        }*/


        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Bar bar = dataSnapshot.getValue(Bar.class);
                bar.setId(dataSnapshot.getKey());
                barArrayList.add(bar);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        adapter = new ListviewAdapter(barArrayList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
