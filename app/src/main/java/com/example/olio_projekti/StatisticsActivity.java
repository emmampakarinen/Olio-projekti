package com.example.olio_projekti;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { // reference for fragments: https://www.youtube.com/watch?v=tPV8xA7m-iw&t=603s
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_stats);
        bottomNav.setOnItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout_stats, new WinsFragment()).commit();
    }


    private NavigationBarView.OnItemSelectedListener navListener  =
            item -> {
                Fragment selectedFragment = null;
                int itemID = item.getItemId();
                if (itemID == R.id.wins) {
                    selectedFragment = new WinsFragment();
                } else if (itemID == R.id.losses) {
                    selectedFragment = new LossesFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout_stats, selectedFragment).commit();
                }
                return true;
            };
}
