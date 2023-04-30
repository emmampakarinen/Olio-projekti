package com.example.olio_projekti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LutemonFragmentListAdapter extends RecyclerView.Adapter<com.example.olio_projekti.LutemonFragmentViewHolder> {
    // reference for fragments: https://www.youtube.com/watch?v=tPV8xA7m-iw&t=603s

    Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private Fragment currentFragment;
    private int pos;


    public LutemonFragmentListAdapter (Context context, ArrayList<Lutemon> lutemons, Fragment currentFragment) {
        this.lutemons = lutemons;
        this.context = context;
        this.currentFragment = currentFragment;
    }


    @NonNull
    @Override
    public LutemonFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonFragmentViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_fragment_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonFragmentViewHolder holder, int position) {
        pos = holder.getAdapterPosition(); // to know which lutemon we want to move

        if (lutemons.get(pos) != null) {
            holder.name.setText(lutemons.get(pos).getName());
            holder.photo.setImageResource(lutemons.get(pos).getPhoto());
            holder.health.setText("Health: " + lutemons.get(pos).getHealth().toString() + "/" + lutemons.get(pos).getMaxHealth().toString());
            holder.attack.setText("Attack: " + lutemons.get(pos).getAttack().toString());
            holder.exp.setText("Experience: " + lutemons.get(pos).getExperience().toString());
        }

        // setting the button icons to correct places depending on which fragment is currently in use
        if (currentFragment instanceof HomeFragment) {
            holder.place1.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.baseline_sports_martial_arts_24, 0, 0, 0);
            holder.place2.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.baseline_spa_24, 0, 0, 0);
        } else if (currentFragment instanceof TrainingFragment) {
            holder.place1.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.baseline_home_24,0,0,0);
            holder.place2.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.baseline_spa_24,0,0,0);
        } else if (currentFragment instanceof SpaFragment) {
            holder.place1.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.baseline_home_24,0,0,0);
            holder.place2.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.baseline_sports_martial_arts_24,0,0,0);
        }

        holder.place1.setOnClickListener(view -> {
            pos = holder.getAdapterPosition(); // to know which lutemon we want to move

            // toasts for user information
            Toast toast = Toast.makeText(context, "Lutemon " + lutemons.get(pos).getName() + " gained 1 experience and 1 attack points after training!", Toast.LENGTH_LONG);
            Toast spaToast = Toast.makeText(context, "Lutemon " + lutemons.get(pos).getName() + " has full health after spa!", Toast.LENGTH_SHORT);
            Toast healthToast = Toast.makeText(context, "Lutemon " + lutemons.get(pos).getName() + " needs to go to the spa first!", Toast.LENGTH_SHORT);

            if (currentFragment instanceof HomeFragment) {
                if (lutemons.get(pos).getHealth() < lutemons.get(pos).getMaxHealth()) { // if lutemon does not have enough health to go training it won't be moved
                    healthToast.show();
                } else {
                    Storage.getInstance().moveLutemon(Location.HOME, Location.TRAINING, lutemons.get(pos));
                    notifyItemRemoved(pos);
                }
            } else if (currentFragment instanceof TrainingFragment) {
                Storage.getInstance().moveLutemon(Location.TRAINING, Location.HOME, lutemons.get(pos));
                notifyItemRemoved(pos);
                toast.show();
            } else if (currentFragment instanceof SpaFragment) {
                Storage.getInstance().moveLutemon(Location.SPA, Location.HOME, lutemons.get(pos));
                notifyItemRemoved(pos);
                spaToast.show();
            }

        });

        holder.place2.setOnClickListener(view -> {
            pos = holder.getAdapterPosition(); // to know which lutemon we want to move

            // toasts for user information
            Toast toast = Toast.makeText(context, "Lutemon " + lutemons.get(pos).getName() + " has full health, no need for spa!", Toast.LENGTH_SHORT);
            Toast spaToast = Toast.makeText(context, "Lutemon " + lutemons.get(pos).getName() + " has full health after spa!", Toast.LENGTH_SHORT);

            if (currentFragment instanceof HomeFragment) {
                if (lutemons.get(pos).getHealth() == lutemons.get(pos).getMaxHealth()) {
                    toast.show();
                    return;
                } else {
                    Storage.getInstance().moveLutemon(Location.HOME, Location.SPA, lutemons.get(pos));
                    notifyItemRemoved(pos);
                }
            } else if (currentFragment instanceof TrainingFragment) {
                if (lutemons.get(pos).getHealth() == lutemons.get(pos).getMaxHealth()) {
                    toast.show();
                    return;
                } else {
                    Storage.getInstance().moveLutemon(Location.TRAINING, Location.SPA, lutemons.get(pos));
                    notifyItemRemoved(pos);
                }
            } else if (currentFragment instanceof SpaFragment) {
                Storage.getInstance().moveLutemon(Location.SPA, Location.TRAINING, lutemons.get(pos));
                spaToast.show();
                notifyItemRemoved(pos);
            }
        });

        /* Lutemon can be moved to battlefield only from home. This was implemented this way because Home doesn't have
        * any activities for Lutemons (such as training or spa treatments) which is why I though it would make the most
        * sense to do it this way. */
        holder.readyToFight.setVisibility(View.GONE);
        if (currentFragment instanceof HomeFragment) {
            holder.readyToFight.setVisibility(View.VISIBLE);
            holder.readyToFight.setOnClickListener(view -> {
                pos = holder.getAdapterPosition(); // to know which lutemon we are moving
                Toast toast;
                if (lutemons.get(pos).getHealth() < lutemons.get(pos).getMaxHealth()) {
                    toast = Toast.makeText(context, "Lutemon " + lutemons.get(pos).getName() + " needs to go to the spa first!", Toast.LENGTH_SHORT);
                } else {
                    toast = Toast.makeText(context, "Lutemon " + lutemons.get(pos).getName() + " moved to battlefield!", Toast.LENGTH_SHORT);
                    Storage.getInstance().moveLutemon(Location.HOME, Location.BATTLEFIELD, lutemons.get(pos));
                    notifyItemRemoved(pos);
                }
                toast.show();
            });
        }
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
