package com.example.olio_projekti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class LutemonListAdapter extends RecyclerView.Adapter<com.example.olio_projekti.LutemonViewHolder> {
    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    public LutemonListAdapter (Context context, ArrayList<Lutemon> lutemons) {
        this.lutemons = lutemons;
        this.context = context;
    }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        holder.name.setText(lutemons.get(position).getName());
        holder.attack.setText("Attack: " + lutemons.get(position).getAttack().toString());
        holder.defence.setText("Defence: " + lutemons.get(position).getDefense().toString());
        holder.health.setText("Health: " + lutemons.get(position).getHealth().toString() + "/" + lutemons.get(position).getMaxHealth().toString());
        holder.experience.setText("Experience: " + lutemons.get(position).getExperience().toString());
        holder.color.setText("(" + lutemons.get(position).getColor() + ")");
        holder.photo.setImageResource(lutemons.get(position).getPhoto());

    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
