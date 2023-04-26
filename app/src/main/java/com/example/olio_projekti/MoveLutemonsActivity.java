package com.example.olio_projekti;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MoveLutemonsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_lutemons);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, new HomeFragment()).commit();
    }


    private NavigationBarView.OnItemSelectedListener navListener  =
            item -> {
                Fragment selectedFragment = null;
                int itemID = item.getItemId();
                if (itemID == R.id.home) {
                    selectedFragment = new HomeFragment();
                } else if (itemID == R.id.training) {
                    selectedFragment = new TrainingFragment();
                } else if (itemID == R.id.spa) {
                    selectedFragment = new SpaFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, selectedFragment).commit();
                }
                return true;
            };


}
