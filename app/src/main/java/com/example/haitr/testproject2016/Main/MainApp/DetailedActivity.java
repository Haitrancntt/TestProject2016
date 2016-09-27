package com.example.haitr.testproject2016.Main.MainApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.haitr.testproject2016.R;

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
    }

    public void btn_Click(View view) {
        switch (view.getId()) {
            case R.id.btn_Drunk:
                Intent intent_Drunk = new Intent(DetailedActivity.this, BarClubActivity.class);
                startActivity(intent_Drunk);
                break;
            case R.id.btn_About:
                Intent intent_About = new Intent(DetailedActivity.this, BarClubActivity.class);
                startActivity(intent_About);
                break;
            case R.id.btn_Favour:
                Intent intent_Favour = new Intent(DetailedActivity.this, BarClubActivity.class);
                startActivity(intent_Favour);
                break;
            case R.id.btn_Profile:
                Intent intent_Profile = new Intent(DetailedActivity.this, BarClubActivity.class);
                startActivity(intent_Profile);
                break;
            default:
                break;
        }
    }
}
