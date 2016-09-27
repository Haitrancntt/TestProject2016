package com.example.haitr.testproject2016.Main.MainApp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.haitr.testproject2016.Main.Adapter.ViewPageAdapter;
import com.example.haitr.testproject2016.Main.Fragment.BarFragment;
import com.example.haitr.testproject2016.Main.Fragment.ClubFragment;
import com.example.haitr.testproject2016.Main.Fragment.DrunkFragment;
import com.example.haitr.testproject2016.R;

public class BarClubActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_club);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ClubFragment(), "Club");
        adapter.addFragment(new BarFragment(), "Bar");
        adapter.addFragment(new DrunkFragment(), "Drunk");
        viewPager.setAdapter(adapter);
    }
}
