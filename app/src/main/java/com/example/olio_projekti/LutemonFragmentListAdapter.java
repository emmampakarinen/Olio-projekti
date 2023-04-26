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
    Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private Fragment currentFragment;


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
        if (lutemons.get(position) != null) {
            holder.name.setText(lutemons.get(position).getName());
            holder.photo.setImageResource(lutemons.get(position).getPhoto());
            holder.health.setText("Health: " + lutemons.get(position).getHealth().toString() + "/" + lutemons.get(position).getMaxHealth().toString());
            holder.attack.setText("Attack: " + lutemons.get(position).getAttack().toString());
            holder.exp.setText("Experience: " + lutemons.get(position).getExperience().toString());
        }


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
            int pos = holder.getAdapterPosition(); // tiedetään mikä lutemon kyseessä, jota halutaan siirtää
            Toast toast = Toast.makeText(context, "Lutemon " + lutemons.get(pos).getName() + " gained 2 experience and 2 attack points!", Toast.LENGTH_LONG);
            Toast spaToast = Toast.makeText(context, "Lutemon " + lutemons.get(pos).getName() + "  has full health after spa!", Toast.LENGTH_SHORT);
            if (currentFragment instanceof HomeFragment) {
                Storage.getInstance().moveToTraining(lutemons.get(pos));
            } else if (currentFragment instanceof TrainingFragment) {
                Storage.getInstance().moveToHome(lutemons.get(pos));
                toast.show();
            } else if (currentFragment instanceof SpaFragment) {
                Storage.getInstance().moveToHome(lutemons.get(pos));
                spaToast.show();
            }
            lutemons.remove(pos);
            notifyItemRemoved(pos);
        });

        holder.place2.setOnClickListener(view -> {
            int pos = holder.getAdapterPosition(); // tiedetään mikä lutemon kyseessä, jota halutaan siirtää
            Toast toast = Toast.makeText(context, "Lutemon " + lutemons.get(pos).getName() + "  has full health, no need for spa!", Toast.LENGTH_SHORT);
            Toast spaToast = Toast.makeText(context, "Lutemon " + lutemons.get(pos).getName() + "  has full health after spa!", Toast.LENGTH_SHORT);
            if (currentFragment instanceof HomeFragment) {
                if (lutemons.get(pos).getHealth() == lutemons.get(pos).getMaxHealth()) {
                    toast.show();
                    return;
                } else {
                    Storage.getInstance().moveToSpa(lutemons.get(pos));
                    lutemons.remove(pos);
                    notifyItemRemoved(pos);
                }
            } else if (currentFragment instanceof TrainingFragment) {
                if (lutemons.get(pos).getHealth() == lutemons.get(pos).getMaxHealth()) {
                    toast.show();
                    return;
                } else {
                    Storage.getInstance().moveToSpa(lutemons.get(pos));
                    lutemons.remove(pos);
                    notifyItemRemoved(pos);
                }
            } else if (currentFragment instanceof SpaFragment) {
                Storage.getInstance().moveToTraining(lutemons.get(pos));
                spaToast.show();
                lutemons.remove(pos);
                notifyItemRemoved(pos);
            }
        });

        holder.readyToFight.setVisibility(View.GONE);
        if (currentFragment instanceof HomeFragment) {
            holder.readyToFight.setVisibility(View.VISIBLE);
            holder.readyToFight.setOnClickListener(view -> {
                int pos = holder.getAdapterPosition();
                Toast toast = Toast.makeText(context, "Lutemon " + lutemons.get(pos).getName() + " moved to battlefield!", Toast.LENGTH_SHORT);
                Storage.getInstance().moveToBattlefield(lutemons.get(pos));
                lutemons.remove(pos);
                notifyItemRemoved(pos);
                toast.show();
            });
        }
    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
