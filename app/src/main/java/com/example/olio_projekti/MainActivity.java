package com.example.olio_projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void switchToAddNewLutemon(View view) {
        Intent intent = new Intent(this, AddLutemonActivity.class);
        startActivity(intent);
    }

    public void switchToListLutemons(View view) {
        Intent intent = new Intent(this, ListLutemonsActivity.class);
        startActivity(intent);
    }

    public void switchToMoveLutemons(View view) {
        Intent intent = new Intent(this, MoveLutemonsActivity.class);
        startActivity(intent);
    }

    public void switchToFightMenu(View view) {
        Intent intent = new Intent(this, BattleMenuActivity.class);
        startActivity(intent);
    }


    public void switchToStatistics(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }

    public void saveLutemons(View view) {
        Storage.getInstance().saveLutemons(this);
    }

    public void loadLutemons(View view) {
        Storage.getInstance().loadLutemons(this);
    }

}