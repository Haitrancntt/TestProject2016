package com.example.haitr.testproject2016.Main.Detailed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haitr.testproject2016.Main.Adapter.Bar;
import com.example.haitr.testproject2016.Main.MainApp.MapsActivity;
import com.example.haitr.testproject2016.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BarDetailedActivity extends Activity {
    private TextView txtPrice, txtAddress, txtDetailed, txtTime, txtPhone, txtName;
    private Button btnLeft, btnRight, btnCall;
    private ImageView imgView;
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("picture").child("bar").child("b-1");
    private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("bar");
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<Bar> barArrayList = new ArrayList<>();
    //gs://testproject-55e62.appspot.com/Bar/B-1/
    private Context context;
    private Bar bar = new Bar();
    private Double dLong, dLat;
    private int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_deatailed);
        txtPrice = (TextView) findViewById(R.id.text_price);
        txtAddress = (TextView) findViewById(R.id.text_address);
        txtTime = (TextView) findViewById(R.id.text_time);
        txtDetailed = (TextView) findViewById(R.id.text_detailed);
        txtPhone = (TextView) findViewById(R.id.text_phone);
        txtName = (TextView) findViewById(R.id.text_Name);
        btnLeft = (Button) findViewById(R.id.button_left);
        btnRight = (Button) findViewById(R.id.button_right);
//        btnCall = (Button) findViewById(R.id.button_call);
        imgView = (ImageView) findViewById(R.id.img_View);

        context = this;
        String value = getIntent().getExtras().getString("ID");
        mRef.child(value).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bar = dataSnapshot.getValue(Bar.class);
                txtName.setText(bar.getName());
                txtPhone.setText(bar.getPhone());
                txtTime.setText(bar.getTime());
                txtAddress.setText(bar.getAddress());
                txtPrice.setText(bar.getPrice());
                txtDetailed.setText(bar.getDescription());

                dLong = bar.getLng();
                dLat = bar.getLat();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {


                    arrayList.add(dataSnapshot.getValue().toString());
                    if (arrayList.size() == 1) {
                        Picasso.with(context).setIndicatorsEnabled(true);
                        Picasso.with(context).load(arrayList.get(0)).into(imgView);
                        index = 0;
                    }
                    //Toast.makeText(BarDetailedActivity.this, "" + image.getsArray(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(BarDetailedActivity.this, "" + e, Toast.LENGTH_SHORT).show();

                }

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

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) index--;
                else index = arrayList.size() - 1;

                Picasso.with(context).setIndicatorsEnabled(true);
                Picasso.with(context).load(arrayList.get(index)).into(imgView);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index < arrayList.size() - 1) index++;
                else index = 0;

                Picasso.with(context).setIndicatorsEnabled(true);
                Picasso.with(context).load(arrayList.get(index)).into(imgView);

            }
        });
    }

    public void btnLocation_Click(View view) {
        Intent intent = new Intent(BarDetailedActivity.this, MapsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putDouble("Long", dLong);
        bundle.putDouble("Lat", dLat);
        intent.putExtras(bundle);
        startActivity(intent);

    }

}
