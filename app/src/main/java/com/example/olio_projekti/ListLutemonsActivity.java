package com.example.olio_projekti;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListLutemonsActivity extends AppCompatActivity {
    private Storage LutemonStorage;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lutemons);

        LutemonStorage = Storage.getInstance();

        rv = findViewById(R.id.rvLutemonList);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new LutemonListAdapter(getApplicationContext(), LutemonStorage.getLutemons()));

    }



}
