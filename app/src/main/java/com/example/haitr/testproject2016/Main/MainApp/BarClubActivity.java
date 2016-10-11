package com.example.haitr.testproject2016.Main.MainApp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search,menu);
        MenuItem menuSearch = menu.findItem(R.id.menu_search);
        ImageButton btnSearch = (ImageButton)menuSearch.getActionView();
        btnSearch.setImageResource(R.drawable.ic_search_white_24dp);
        btnSearch.setBackgroundColor(Color.TRANSPARENT);
        btnSearch.setPadding(0,0,16,0);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return super.onCreateOptionsMenu(menu);

    }
}
