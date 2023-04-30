package com.example.olio_projekti;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/* This holder is for listing all lutemons */
public class LutemonViewHolder extends RecyclerView.ViewHolder {

    ImageView photo;
    TextView name, attack, defence, health, experience, color;
    public LutemonViewHolder(@NonNull View itemView) {
        super(itemView);
        photo = itemView.findViewById(R.id.imageViewLutemon);
        name = itemView.findViewById(R.id.textViewName);
        attack = itemView.findViewById(R.id.textViewAttack);
        defence = itemView.findViewById(R.id.textViewDefence);
        health = itemView.findViewById(R.id.textViewHealth);
        experience = itemView.findViewById(R.id.textViewExperience);
        color = itemView.findViewById(R.id.textViewColor);
    }
}
