package com.example.olio_projekti;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/* Menu activity is used before the actual battle. In this activity the user selects 2 fighters
* from the battlefield to have a battle. */
public class BattleMenuActivity extends AppCompatActivity {
    private ArrayList<Lutemon> lutemons;
    private RecyclerView rv;
    private ImageView lutemon1, lutemon2;
    private Lutemon fighter1, fighter2;
    private Button confirm, toBattle;
    private TextView title;
    private LinearLayoutManager lm = new LinearLayoutManager(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_menu);

        rv = findViewById(R.id.RecyclerViewStoreMenu);
        title = findViewById(R.id.textViewStoreTitle);

        lutemons = Storage.getInstance().getLutemonsAt(Location.BATTLEFIELD);
        rv.setLayoutManager(lm);
        rv.setAdapter(new CheckBoxAdapter(lutemons, this));

        lutemon1 = findViewById(R.id.imageViewFighter1);
        lutemon2 = findViewById(R.id.imageViewFighter2);

        lutemon1.setVisibility(View.GONE);
        lutemon2.setVisibility(View.GONE);

        confirm = findViewById(R.id.btnConfirmBuying);
        toBattle = findViewById(R.id.btnFight);

        toBattle.setVisibility(View.GONE);
        if (!lutemons.isEmpty()) {
            title.setVisibility(View.VISIBLE);
        }
    }

    /* Listing Lutemons for checkboxes was implemented with RecyclerView since we Don't know
    * how many Lutemons have been created */
    private class CheckBoxAdapter extends RecyclerView.Adapter<CheckBoxHolder> {
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
        public CheckBoxHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CheckBoxHolder(LayoutInflater.from(context).inflate(R.layout.cb_view, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CheckBoxHolder holder, int position) {
            pos = holder.getAdapterPosition();

            holder.cb.setText(lutemons.get(pos).getName() + " (" + lutemons.get(pos).getColor() + ")");

            holder.cb.setOnCheckedChangeListener((compoundButton, isChecked) -> { // reference: https://stackoverflow.com/questions/16220156/how-to-limit-number-of-checkboxes-that-can-be-checked
                pos = holder.getAdapterPosition();
                if (isChecked && checkedBoxes >= 2) {
                    holder.cb.setChecked(false);
                } else {
                    if (isChecked) {
                        checkedBoxes++;
                        if (lutemon1.getVisibility() == View.GONE) { // setting the first character picture
                            lutemon1.setImageResource(lutemons.get(pos).getPhoto());
                            lutemon1.setVisibility(View.VISIBLE);
                            checked1 = holder.cb.getId();
                            fighter1 = lutemons.get(pos);
                        } else if (lutemon2.getVisibility() == View.GONE) { // if the first is already selected then setting the second character picture
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

    public void switchToBattleActivity(View view) { // starting the battle activity
        Intent intent = new Intent(this, BattleFieldActivity.class);
        startActivity(intent);
    }


}
