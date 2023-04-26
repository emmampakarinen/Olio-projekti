package com.example.olio_projekti;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FightMenuActivity extends AppCompatActivity {
    private String hexColor = "#54E4EDEF";
    private int color = Color.parseColor(hexColor), cbCounter = 0;
    private ArrayList<Lutemon> lutemons;
    private RecyclerView rv;
    private ImageView lutemon1, lutemon2;
    private Lutemon fighter1, fighter2;
    private Button confirm, toBattle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_menu);

        rv = findViewById(R.id.RecyclerViewFightMenu);

        lutemons = Storage.getInstance().getFightLutemons();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new CheckBoxAdapter(lutemons, this));
        lutemon1 = findViewById(R.id.imageViewFighter1);
        lutemon2 = findViewById(R.id.imageViewFighter2);
        lutemon1.setVisibility(View.GONE);
        lutemon2.setVisibility(View.GONE);

        confirm = findViewById(R.id.btnConfirmFighters);
        toBattle = findViewById(R.id.btnFight);
        toBattle.setVisibility(View.GONE);
    }

    private class CheckBoxAdapter extends RecyclerView.Adapter<FightMenuHolder> {
        private Context context;
        private ArrayList<Lutemon> lutemons;
        public CheckBoxAdapter(ArrayList<Lutemon> lutemons, Context context) {
            this.lutemons = lutemons;
            this.context = context;
        }

        @NonNull
        @Override
        public FightMenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new FightMenuHolder(LayoutInflater.from(context).inflate(R.layout.battlemenu_cb_view, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull FightMenuHolder holder, int position) {
            int pos = holder.getAdapterPosition();
            holder.cb.setText(lutemons.get(pos).getName() + " (" + lutemons.get(pos).getColor() + ")");

            holder.cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.cb.isChecked()) {
                        if (lutemon1.getVisibility() == View.GONE) {
                            lutemon1.setImageResource(lutemons.get(pos).getPhoto());
                            lutemon1.setVisibility(View.VISIBLE);
                            fighter1 = lutemons.get(pos);
                        } else if (lutemon2.getVisibility() == View.GONE) {
                            lutemon2.setImageResource(lutemons.get(pos).getPhoto());
                            lutemon2.setVisibility(View.VISIBLE);
                            fighter2 = lutemons.get(pos);
                        }
                        cbCounter++;
                    } else {
                        if (lutemon1.getVisibility() == View.VISIBLE && lutemon2.getVisibility() == View.GONE) {
                            lutemon1.setVisibility(View.GONE);
                            cbCounter = 0;
                        } else if (lutemon2.getVisibility() == View.VISIBLE && lutemon1.getVisibility() == View.GONE) {
                            lutemon2.setVisibility(View.GONE);
                            cbCounter = 0;
                        } else {
                            // if two lutemons are checked, how to know which lutemon was unchecked

                        }

                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return lutemons.size();
        }
    }

    public void saveFighters(View view) {
        BattleField.getInstance().setFighters(fighter1, fighter2);
        confirm.setVisibility(View.GONE);
        toBattle.setVisibility(View.VISIBLE);
        lutemons.remove(fighter1);
        lutemons.remove(fighter2);
    }

    public void switchToBattleActivity(View view) {
        Intent intent = new Intent(this, BattleFieldActivity.class);
        startActivity(intent);
    }

}
