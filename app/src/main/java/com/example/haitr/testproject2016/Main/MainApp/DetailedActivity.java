package com.example.haitr.testproject2016.Main.MainApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.haitr.testproject2016.Main.Main.MainActivity;
import com.example.haitr.testproject2016.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        List<String> list = mAuth.getCurrentUser().getProviders();
    }

    public void btn_Click(View view) {
        switch (view.getId()) {
            case R.id.btn_Drunk:
                Intent intent_Drunk = new Intent(DetailedActivity.this, BarClubActivity.class);
                startActivity(intent_Drunk);
                break;
            case R.id.btn_About:
                Intent aboutIntent = new Intent(DetailedActivity.this, AboutUsActivity.class);
                startActivity(aboutIntent);
                break;
            case R.id.btn_Favour:

                break;
            case R.id.btn_Profile:
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String s = mAuth.getCurrentUser().getEmail();
                Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent loginIntent = new Intent(DetailedActivity.this,MainActivity.class);
                startActivity(loginIntent);
                break;
            default:
                break;
        }
    }
}
