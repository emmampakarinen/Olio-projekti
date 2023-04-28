package com.example.olio_projekti;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FightMenuActivity extends AppCompatActivity {
    private String hexColor = "#54E4EDEF";
    private int color = Color.parseColor(hexColor);
    private ArrayList<Lutemon> lutemons;
    private RecyclerView rv;
    private ImageView lutemon1, lutemon2;
    private Lutemon fighter1, fighter2;
    private Button confirm, toBattle;
    private TextView title;
    private LinearLayoutManager lm = new LinearLayoutManager(this);
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_menu);

        rv = findViewById(R.id.RecyclerViewFightMenu);
        title = findViewById(R.id.textViewBattleMenuTitle);

        lutemons = Storage.getInstance().getLutemonsAt(Location.BATTLEFIELD);
        rv.setLayoutManager(lm);
        rv.setAdapter(new CheckBoxAdapter(lutemons, this));
        lutemon1 = findViewById(R.id.imageViewFighter1);
        lutemon2 = findViewById(R.id.imageViewFighter2);
        lutemon1.setVisibility(View.GONE);
        lutemon2.setVisibility(View.GONE);

        confirm = findViewById(R.id.btnConfirmFighters);
        toBattle = findViewById(R.id.btnFight);

        toBattle.setVisibility(View.GONE);
        if (!lutemons.isEmpty()) {
            title.setVisibility(View.VISIBLE);
        }
    }


    private class CheckBoxAdapter extends RecyclerView.Adapter<FightMenuHolder> {
        private Context context;
        private ArrayList<Lutemon> lutemons;
        int checkedBoxes = 0;
        int checked1 = 0, checked2 = 0, pos = 0;

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
            pos = holder.getAdapterPosition();

            holder.cb.setText(lutemons.get(pos).getName() + " (" + lutemons.get(pos).getColor() + ")");

            holder.cb.setOnCheckedChangeListener((compoundButton, isChecked) -> { // reference: https://stackoverflow.com/questions/16220156/how-to-limit-number-of-checkboxes-that-can-be-checked
                pos = holder.getAdapterPosition();
                if (isChecked && checkedBoxes >= 2) {
                    holder.cb.setChecked(false);
                } else {
                    if (isChecked) {
                        checkedBoxes++;
                        if (lutemon1.getVisibility() == View.GONE) {
                            lutemon1.setImageResource(lutemons.get(pos).getPhoto());
                            lutemon1.setVisibility(View.VISIBLE);
                            checked1 = holder.cb.getId();
                            fighter1 = lutemons.get(pos);
                        } else if (lutemon2.getVisibility() == View.GONE) {
                            lutemon2.setImageResource(lutemons.get(pos).getPhoto());
                            lutemon2.setVisibility(View.VISIBLE);
                            checked2 = holder.cb.getId();
                            fighter2 = lutemons.get(pos);
                        }
                    } else {
                        checkedBoxes--;
                        if (checked1 == holder.cb.getId()) { /* the deselection of lutemons needs to be done in the same order as they were
                        chosen. (Couldn't figure out how to implement the deselection properly but it gets the work done) */
                            lutemon1.setVisibility(View.GONE);
                            checked1 = 0;
                        } else if (checked2 == holder.cb.getId()) {
                            lutemon2.setVisibility(View.GONE);
                            checked2 = 0;
                        }
                    }
                    if (checkedBoxes == 2) {
                        confirm.setVisibility(View.VISIBLE);
                    } else {
                        confirm.setVisibility(View.GONE);
                    }
                }
            });

            holder.home.setOnClickListener(view -> {
                pos = holder.getAdapterPosition();
                Storage.getInstance().moveLutemon(Location.BATTLEFIELD, Location.HOME, lutemons.get(pos));
                notifyItemRemoved(pos);
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
        rv.setVisibility(View.GONE);
        toBattle.setVisibility(View.VISIBLE);
        title.setVisibility(View.GONE);
        lutemons.remove(fighter1);
        lutemons.remove(fighter2);
    }

    public void switchToBattleActivity(View view) {
        Intent intent = new Intent(this, BattleFieldActivity.class);
        startActivity(intent);
    }


}
